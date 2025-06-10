package finalmission.member.external;

import finalmission.member.controller.RandomNameClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
public class RandomNameClientImpl implements RandomNameClient {
    private final RestClient restClient;
    private final String url;
    private final String secretkey;

    public RandomNameClientImpl(
            @Value("${api.randommer.uri}") String url,
            @Value("${api.randommer.secret-key}") String secretkey,
            RestClient restclient
    ) {
        this.restClient = restclient;
        this.secretkey = secretkey;
        this.url = url;
    }

    @Override
    public String getRandomName() {
        return restClient.get()
                .uri(url)
                .retrieve()
                .onStatus(status -> status.value() == 404, (req, res) -> {
                    throw new RuntimeException("외부 API 오류");
                })
                .body(String.class);
    }
}
