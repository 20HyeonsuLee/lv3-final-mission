package finalmission.Schedule.service;

import finalmission.Schedule.repository.DailyScheduleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
@Transactional(readOnly = true)
public class DailyScheduleQuery {

    private final DailyScheduleRepository dailyScheduleRepository;


}
