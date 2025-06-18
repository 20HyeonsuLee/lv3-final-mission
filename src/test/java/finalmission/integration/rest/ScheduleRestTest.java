package finalmission.integration.rest;

import static org.assertj.core.api.Assertions.*;
import static org.hamcrest.Matchers.is;

import finalmission.reservation.repository.ReservationRepository;
import finalmission.schedule.model.ReservationDate;
import finalmission.schedule.model.ReservationTime;
import finalmission.schedule.model.Schedule;
import finalmission.schedule.repository.ScheduleRepository;
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
import java.util.Optional;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

class ScheduleRestTest extends RestAssuredTestBase {

    private Customer customer;
    private Schedule schedule;
    private Trainer trainer;
    private String sessionId;

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private ScheduleRepository scheduleRepository;

    @BeforeEach
    void setUp() {
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
    void 트레이너의_특정_날짜의_예약_일정을_조회한다() {
        RestAssured.given().log().all()
                .contentType(ContentType.JSON)
                .cookie("JSESSIONID", sessionId)
                .when().get("/schedules/{trainer-id}/{date}", trainer.getId(), schedule.getDate().date().toString())
                .then().log().all()
                .statusCode(200)
                .body("size()", is(1));
    }

    @Test
    void 트레이너가_특정_날짜의_일정을_수정한다() {
        RestAssured.given().log().all()
                .contentType(ContentType.JSON)
                .cookie("JSESSIONID", sessionId)
                .body(Map.of("modifyTime", "11:00"))
                .when().post("/schedules/{trainer-id}/{date}/{time}",
                        trainer.getId(),
                        schedule.getDate().date().toString(),
                        schedule.getTime().time().toString())
                .then().log().all()
                .statusCode(200);

        SoftAssertions.assertSoftly(softly -> {
            Optional<Schedule> findSchedule = scheduleRepository.findById(schedule.getId());
            softly.assertThat(findSchedule).isPresent();
            softly.assertThat(findSchedule.get().getTime().time()).isEqualTo(LocalTime.of(11, 0));
        });
    }

    @Test
    void 트레이너가_특정_날짜의_일정을_삭제한다() {
        System.out.println(schedule.getTime().time().toString());
        RestAssured.given().log().all()
                .contentType(ContentType.JSON)
                .cookie("JSESSIONID", sessionId)
                .when().delete("/schedules/{trainer-id}/{date}/{time}",
                        trainer.getId(),
                        schedule.getDate().date().toString(),
                        schedule.getTime().time().toString())
                .then().log().all()
                .statusCode(204);
        Optional<Schedule> findSchedule = scheduleRepository.findById(schedule.getId());
        assertThat(findSchedule).isNotPresent();
    }
}
