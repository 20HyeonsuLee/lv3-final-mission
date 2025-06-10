package finalmission.Schedule.service;

import finalmission.Schedule.model.ReservationDate;
import finalmission.Schedule.model.ReservationTime;
import finalmission.Schedule.model.Schedule;
import finalmission.Schedule.repository.ScheduleRepository;
import finalmission.member.model.Trainer;
import java.util.List;
import java.util.NoSuchElementException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
@Transactional(readOnly = true)
public class ScheduleQuery {

    private final ScheduleRepository scheduleRepository;

    public ReservationTime getReservationTime(ReservationTime time) {
        return scheduleRepository.findByTime(time)
                .orElseThrow(() -> new IllegalArgumentException("이미 존재하는 예약 시간 일정입니다."));
    }

    public List<Schedule> findAllScheduleByTrainer(Trainer trainer) {
        return scheduleRepository.findAllByTrainer(trainer);
    }

    public Schedule getById(final Long id) {
        return scheduleRepository.findById(id).orElseThrow(() -> new NoSuchElementException("존재하지 않는 일정입니다."));
    }

    public List<Schedule> findAllScheduleByTrainerAndDate(final Trainer trainer, final ReservationDate reservationDate) {
        return scheduleRepository.findAllByTrainerAndDate(trainer, reservationDate);
    }
}
