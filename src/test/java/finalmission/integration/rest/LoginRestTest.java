package finalmission.integration.rest;

import finalmission.integration.RestAssuredTestBase;
import finalmission.member.model.Customer;
import finalmission.member.model.member.BirthDate;
import finalmission.member.model.member.Email;
import finalmission.member.model.member.Gender;
import finalmission.member.model.member.Name;
import finalmission.member.model.member.Password;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import java.time.LocalDate;
import java.util.Map;
import org.junit.jupiter.api.Test;

class LoginRestTest extends RestAssuredTestBase {

    @Test
    void 회원이_로그인을_한다() {
        // given
        Customer customer = memberRepository.save(Customer.builder()
                .email(new Email("test@gmail.com"))
                .password(new Password("password"))
                .name(new Name("test"))
                .birthDate(new BirthDate(LocalDate.of(2024, 1, 1)))
                .gender(Gender.M)
                .build());

        // when & then
        RestAssured.given().log().all()
                .contentType(ContentType.JSON)
                .body(Map.of(
                        "email", customer.getEmail().toString(),
                        "password", customer.getPassword().toString()
                ))
                .when().post("/login")
                .then().log().all()
                .statusCode(200);
    }
}
