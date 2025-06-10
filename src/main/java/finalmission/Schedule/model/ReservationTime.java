package finalmission.Schedule.model;

import jakarta.persistence.Embeddable;
import java.time.LocalDate;
import java.time.LocalTime;

@Embeddable
public record ReservationTime(LocalTime time) {
}
