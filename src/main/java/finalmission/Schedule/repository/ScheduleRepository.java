package finalmission.Schedule.repository;

import finalmission.Schedule.model.ReservationTime;
import finalmission.Schedule.model.Schedule;
import finalmission.member.model.Trainer;
import finalmission.member.model.member.Member;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

    Optional<ReservationTime> findByTime(ReservationTime time);

    List<Schedule> findAllByTrainer(Trainer trainer);
}
