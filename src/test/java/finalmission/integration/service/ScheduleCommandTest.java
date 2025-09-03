package finalmission.integration.service;

import static org.assertj.core.api.Assertions.*;

import finalmission.member.model.Trainer;
import finalmission.member.model.member.BirthDate;
import finalmission.member.model.member.Email;
import finalmission.member.model.member.Gender;
import finalmission.member.model.member.Name;
import finalmission.member.model.member.Password;
import finalmission.member.repository.TrainerRepository;
import finalmission.schedule.model.DailySchedule;
import finalmission.schedule.model.ReservationTime;
import finalmission.schedule.repository.DailyScheduleRepository;
import finalmission.schedule.repository.ScheduleRepository;
import finalmission.schedule.service.ScheduleCommand;
import finalmission.schedule.service.ScheduleQuery;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
public class ScheduleCommandTest {

    @Autowired
    private ScheduleCommand scheduleCommand;

    @Autowired
    private ScheduleQuery scheduleQuery;

    private Trainer trainer1;
    private Trainer trainer2;
    @Autowired
    private ScheduleRepository scheduleRepository;

    @BeforeEach
    void setUp(
            @Autowired TrainerRepository trainerRepository,
            @Autowired DailyScheduleRepository dailyScheduleRepository
    ) {
        trainer1 = trainerRepository.save(Trainer.builder()
                .email(new Email("trainer1@gmail.com"))
                .password(new Password("password"))
                .name(new Name("test"))
                .birthDate(new BirthDate(LocalDate.of(2024, 1, 1)))
                .gender(Gender.M)
                .build());
        trainer2 = trainerRepository.save(Trainer.builder()
                .email(new Email("trainer2@gmail.com"))
                .password(new Password("password"))
                .name(new Name("test"))
                .birthDate(new BirthDate(LocalDate.of(2024, 1, 1)))
                .gender(Gender.M)
                .build());
        ReservationTime reservationTime = new ReservationTime(LocalTime.of(11, 0));
        dailyScheduleRepository.save(DailySchedule.builder()
                .dayOfWeek(DayOfWeek.MONDAY)
                .trainer(trainer1)
                .time(reservationTime)
                .build());
        dailyScheduleRepository.save(DailySchedule.builder()
                .dayOfWeek(DayOfWeek.TUESDAY)
                .trainer(trainer1)
                .time(reservationTime)
                .build());
        dailyScheduleRepository.save(DailySchedule.builder()
                .dayOfWeek(DayOfWeek.MONDAY)
                .trainer(trainer2)
                .time(reservationTime)
                .build());
    }

    @Test
    void 두_달치_예약을_생성한다() {
        // when
        scheduleCommand.init();

        // then
        assertThat(scheduleRepository.findAllByTrainer(trainer1)).hasSize(16);
        assertThat(scheduleRepository.findAllByTrainer(trainer2)).hasSize(8);
    }
}
