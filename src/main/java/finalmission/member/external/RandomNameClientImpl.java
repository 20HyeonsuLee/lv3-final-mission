package finalmission.member.external;

import finalmission.member.controller.RandomNameClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import org.springframework.web.util.UriComponentsBuilder;

@Component
public class RandomNameClientImpl implements RandomNameClient {
    private final RestClient restClient;
    private final String namePath;
    private final String secretkey;
    private final String host;

    public RandomNameClientImpl(
            @Value("${api.randommer.path.name}") String namePath,
            @Value("${api.randommer.host}") String host,
            @Value("${api.randommer.secret-key}") String secretkey,
            RestClient restclient
    ) {
        this.restClient = restclient;
        this.secretkey = secretkey;
        this.namePath = namePath;
        this.host = host;
    }

    @Override
    public String getRandomName() {
        String uriString = UriComponentsBuilder
                .fromPath(namePath)
                .scheme("https")
                .host(host)
                .queryParam("nameType", "fullName")
                .queryParam("quantity", 1)
                .toUriString();
        String[] names = restClient.get()
                .uri(uriString)
                .header("X-Api-Key", secretkey)
                .retrieve()
                .onStatus(status -> status.value() == 404, (req, res) -> {
                    throw new RuntimeException("외부 API 오류");
                })
                .body(String[].class);
        return names[0];
    }
}
