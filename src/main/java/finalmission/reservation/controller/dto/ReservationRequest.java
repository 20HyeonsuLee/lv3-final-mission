package finalmission.reservation.controller.dto;

import jakarta.validation.constraints.NotNull;

public record ReservationRequest(@NotNull Long scheduleId) {
}
