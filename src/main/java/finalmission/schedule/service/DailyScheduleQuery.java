package finalmission.schedule.service;

import finalmission.schedule.repository.DailyScheduleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
@Transactional(readOnly = true)
public class DailyScheduleQuery {

    private final DailyScheduleRepository dailyScheduleRepository;


}
