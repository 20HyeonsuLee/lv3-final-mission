package finalmission.schedule.model;

import jakarta.persistence.Embeddable;
import java.time.LocalTime;

@Embeddable
public record ReservationTime(LocalTime time) {
}
