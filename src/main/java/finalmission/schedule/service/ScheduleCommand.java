package finalmission.schedule.service;

import finalmission.member.model.Trainer;
import finalmission.member.service.TrainerQuery;
import finalmission.schedule.model.DailySchedule;
import finalmission.schedule.model.DateRange;
import finalmission.schedule.model.ReservationDate;
import finalmission.schedule.model.ReservationTime;
import finalmission.schedule.model.Schedule;
import finalmission.schedule.repository.ScheduleRepository;
import java.time.Clock;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
@Transactional
public class ScheduleCommand {

    private final ScheduleRepository scheduleRepository;
    private final ScheduleQuery scheduleQuery;
    private final TrainerQuery trainerQuery;
    private final Clock clock;
    private final DailyScheduleQuery dailyScheduleQuery;

    public void changeReservationTime(Schedule schedule, ReservationTime time) {
        schedule.changeTime(time);
    }

    public void delete(final Schedule schedule) {
        scheduleRepository.delete(schedule);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void init() {
        generateNextMonthSchedules();
    }

    @Scheduled(cron = "0 0 0 * * *")
    private void generateNextMonthSchedules() {
        DateRange nextTwoMonths = DateRange.createNextMonthRange(clock, 2);

        trainerQuery.findAll().forEach(trainer -> {
            Map<DayOfWeek, List<ReservationTime>> weeklySchedule = loadWeeklyScheduleMap(trainer);
            Set<LocalDate> alreadySavedDates = getScheduledDates(trainer);

            createMissingSchedules(trainer, weeklySchedule, nextTwoMonths, alreadySavedDates);
        });
    }

    private Map<DayOfWeek, List<ReservationTime>> loadWeeklyScheduleMap(Trainer trainer) {
        return dailyScheduleQuery.findAllByTrainer(trainer).stream()
                .collect(Collectors.groupingBy(
                        DailySchedule::getDayOfWeek,
                        Collectors.mapping(DailySchedule::getTime, Collectors.toList())
                ));
    }

    private Set<LocalDate> getScheduledDates(Trainer trainer) {
        return scheduleRepository.findAllByTrainer(trainer).stream()
                .map(schedule -> schedule.getDate().date())
                .collect(Collectors.toSet());
    }

    private void createMissingSchedules(
            Trainer trainer,
            Map<DayOfWeek, List<ReservationTime>> weeklySchedule,
            DateRange dateRange,
            Set<LocalDate> alreadyScheduledDates
    ) {
        dateRange.differenceTo(alreadyScheduledDates).forEach(date -> {
            List<ReservationTime> times = weeklySchedule.get(date.getDayOfWeek());
            if (times == null || times.isEmpty()) {
                return;
            }
            times.forEach(time -> scheduleRepository.save(
                    Schedule.builder()
                            .date(new ReservationDate(date))
                            .time(time)
                            .trainer(trainer)
                            .build()
            ));
        });
    }
}
