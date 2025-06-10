package finalmission.integration;

import finalmission.member.model.member.Member;
import finalmission.member.repository.MemberRepository;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
public class RestAssuredTestBase {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    protected MemberRepository memberRepository;

    @LocalServerPort
    int port;

    @BeforeEach
    void setPort() {
        RestAssured.port = port;
    }


    public String generateSession(Member member) {
        String sessionId = RestAssured.given().log().all()
                .contentType(ContentType.JSON)
                .body(Map.of(
                        "email", member.getEmail().toString(),
                        "password", member.getPassword().toString()
                ))
                .when().post("/login")
                .then().log().all()
                .statusCode(200)
                .extract()
                .cookie("JSESSIONID");
        return sessionId;
    }
}
