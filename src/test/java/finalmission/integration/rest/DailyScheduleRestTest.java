package finalmission.integration.rest;

import static org.assertj.core.api.Assertions.*;
import static org.hamcrest.Matchers.is;

import finalmission.integration.RestAssuredTestBase;
import finalmission.member.model.Customer;
import finalmission.member.model.Trainer;
import finalmission.member.model.member.BirthDate;
import finalmission.member.model.member.Email;
import finalmission.member.model.member.Gender;
import finalmission.member.model.member.Name;
import finalmission.member.model.member.Password;
import finalmission.reservation.repository.ReservationRepository;
import finalmission.schedule.model.DailySchedule;
import finalmission.schedule.model.ReservationDate;
import finalmission.schedule.model.ReservationTime;
import finalmission.schedule.model.Schedule;
import finalmission.schedule.repository.DailyScheduleRepository;
import finalmission.schedule.repository.ScheduleRepository;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

class DailyScheduleRestTest extends RestAssuredTestBase {

    private Customer customer;
    private Schedule schedule;
    private Trainer trainer;
    private String sessionId;

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private DailyScheduleRepository dailyScheduleRepository;

    @BeforeEach
    void setUp() {
        trainer = memberRepository.save(Trainer.builder()
                .email(new Email("trainer@gmail.com"))
                .password(new Password("password"))
                .name(new Name("test"))
                .birthDate(new BirthDate(LocalDate.of(2024, 1, 1)))
                .gender(Gender.M)
                .build());
        sessionId = generateSession(trainer);
    }

    @Test
    void 트레이너가_특정_요일의_일정을_추가한다() {
        RestAssured.given().log().all()
                .contentType(ContentType.JSON)
                .cookie("JSESSIONID", sessionId)
                .body(Map.of(
                        "dayOfWeek", "MONDAY",
                        "time", "10:00"
                ))
                .when().post("/daily/schedules")
                .then().log().all()
                .statusCode(200);

        List<DailySchedule> dailySchedules = dailyScheduleRepository.findAll();
        assertThat(dailySchedules).hasSize(1);
    }

    @Test
    void 트레이너가_특정_요일의_일정을_삭제한다() {
        DailySchedule dailySchedule = dailyScheduleRepository.save(DailySchedule.builder()
                .dayOfWeek(DayOfWeek.MONDAY)
                .time(new ReservationTime(LocalTime.of(11, 0)))
                .trainer(trainer)
                .build()
        );
        RestAssured.given().log().all()
                .contentType(ContentType.JSON)
                .cookie("JSESSIONID", sessionId)
                .when().delete("/daily/schedules/{id}", dailySchedule.getId())
                .then().log().all()
                .statusCode(204);

        List<DailySchedule> dailySchedules = dailyScheduleRepository.findAll();
        assertThat(dailySchedules).hasSize(0);
    }
}
