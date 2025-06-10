package finalmission.integration.rest;

import static org.hamcrest.Matchers.is;

import finalmission.Reservation.model.Reservation;
import finalmission.Reservation.repository.ReservationRepository;
import finalmission.Schedule.model.ReservationDate;
import finalmission.Schedule.model.ReservationTime;
import finalmission.Schedule.model.Schedule;
import finalmission.Schedule.repository.ScheduleRepository;
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
import java.time.LocalTime;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

class ReservationRestTest extends RestAssuredTestBase {

    private Customer customer;
    private Schedule schedule;
    private Trainer trainer;
    private String sessionId;

    @Autowired
    private ReservationRepository reservationRepository;

    @BeforeEach
    void setUp(@Autowired ScheduleRepository scheduleRepository) {
        trainer = memberRepository.save(Trainer.builder()
                .email(new Email("trainer@gmail.com"))
                .password(new Password("password"))
                .name(new Name("test"))
                .birthDate(new BirthDate(LocalDate.of(2024, 1, 1)))
                .gender(Gender.M)
                .build());
        customer = memberRepository.save(Customer.builder()
                .email(new Email("customer@gmail.com"))
                .password(new Password("password"))
                .name(new Name("test"))
                .birthDate(new BirthDate(LocalDate.of(2024, 1, 1)))
                .gender(Gender.M)
                .build());
        schedule = scheduleRepository.save(Schedule.builder()
                .date(new ReservationDate(LocalDate.of(2024, 1, 1)))
                .time(new ReservationTime(LocalTime.of(10, 0)))
                .trainer(trainer)
                .build());
        sessionId = generateSession(customer);
    }

    @Test
    void 회원이_자신의_예약을_조회한다() {
        reservationRepository.save(Reservation.builder()
                .customer(customer)
                .schedule(schedule)
                .build());
        RestAssured.given().log().all()
                .contentType(ContentType.JSON)
                .cookie("JSESSIONID", sessionId)
                .when().get("/reservations/mine")
                .then().log().all()
                .statusCode(200)
                .body("size()", is(1));

    }

    @Test
    void 회원이_예약을_한다() {
        RestAssured.given().log().all()
                .contentType(ContentType.JSON)
                .cookie("JSESSIONID", sessionId)
                .body(Map.of("scheduleId", schedule.getId()))
                .when().post("/reservations")
                .then().log().all()
                .statusCode(201);
    }

    @Test
    void 회원이_예약을_취소한다() {
        Reservation reservation = reservationRepository.save(Reservation.builder()
                .customer(customer)
                .schedule(schedule)
                .build());
        RestAssured.given().log().all()
                .contentType(ContentType.JSON)
                .cookie("JSESSIONID", sessionId)
                .when().delete("/reservations/{id}", reservation.getId())
                .then().log().all()
                .statusCode(204);
    }
}
