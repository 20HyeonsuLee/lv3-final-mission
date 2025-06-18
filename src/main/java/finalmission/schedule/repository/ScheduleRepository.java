package finalmission.schedule.repository;

import finalmission.schedule.model.ReservationDate;
import finalmission.schedule.model.ReservationTime;
import finalmission.schedule.model.Schedule;
import finalmission.member.model.Trainer;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

    Optional<ReservationTime> findByTime(ReservationTime time);

    List<Schedule> findAllByTrainer(Trainer trainer);

    List<Schedule> findAllByTrainerAndDate(Trainer trainer, ReservationDate reservationDate);

    Schedule findByTrainerAndDateAndTime(
            Trainer trainer,
            ReservationDate reservationDate,
            ReservationTime reservationTime
    );
}
