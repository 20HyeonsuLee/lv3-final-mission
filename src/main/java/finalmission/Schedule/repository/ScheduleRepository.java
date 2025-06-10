package finalmission.Schedule.repository;

import finalmission.Schedule.model.ReservationDate;
import finalmission.Schedule.model.ReservationTime;
import finalmission.Schedule.model.Schedule;
import finalmission.member.model.Trainer;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

    Optional<ReservationTime> findByTime(ReservationTime time);

    List<Schedule> findAllByTrainer(Trainer trainer);

    List<Schedule> findAllByTrainerAndDate(Trainer trainer, ReservationDate reservationDate);
}
