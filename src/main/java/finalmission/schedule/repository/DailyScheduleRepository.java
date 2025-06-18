package finalmission.schedule.repository;

import finalmission.member.model.Trainer;
import finalmission.schedule.model.DailySchedule;
import finalmission.schedule.model.ReservationTime;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DailyScheduleRepository extends JpaRepository<DailySchedule, Long> {

    Optional<DailySchedule> findByTime(ReservationTime time);

    List<DailySchedule> findAllByTrainer(Trainer trainer);
}
