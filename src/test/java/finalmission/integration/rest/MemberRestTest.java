package finalmission.integration.rest;

import static org.hamcrest.Matchers.is;

import finalmission.integration.RestAssuredTestBase;
import finalmission.member.model.Customer;
import finalmission.member.model.Trainer;
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

class MemberRestTest extends RestAssuredTestBase {

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
                .when().post("/members/login")
                .then().log().all()
                .statusCode(200);
    }

    @Test
    void 모든_트레이너를_조회한다() {
        Trainer trainer1 = memberRepository.save(Trainer.builder()
                .email(new Email("test@gmail.com"))
                .password(new Password("password"))
                .name(new Name("test"))
                .birthDate(new BirthDate(LocalDate.of(2024, 1, 1)))
                .gender(Gender.M)
                .build());

        Trainer trainer2 = memberRepository.save(Trainer.builder()
                .email(new Email("trainer2@gmail.com"))
                .password(new Password("password"))
                .name(new Name("test"))
                .birthDate(new BirthDate(LocalDate.of(2024, 1, 1)))
                .gender(Gender.M)
                .build());

        // when & then
        RestAssured.given().log().all()
                .contentType(ContentType.JSON)
                .when().get("/members/trainers")
                .then().log().all()
                .statusCode(200)
                .body("size()", is(2));


    }
}
