package finalmission.schedule.repository;

import finalmission.schedule.model.DailySchedule;
import finalmission.schedule.model.ReservationTime;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DailyScheduleRepository extends JpaRepository<DailySchedule, Long> {

    Optional<DailySchedule> findByTime(ReservationTime time);
}
