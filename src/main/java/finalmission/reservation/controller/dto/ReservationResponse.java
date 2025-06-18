package finalmission.reservation.controller.dto;

import finalmission.reservation.model.Reservation;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public record ReservationResponse(
        LocalDate date,
        LocalTime time,
        String trainerName
) {
    public static ReservationResponse from(Reservation reservation) {
        return new ReservationResponse(
                reservation.getSchedule().getDate().date(),
                reservation.getSchedule().getTime().time(),
                reservation.getSchedule().getTrainer().getName().name()
        );
    }

    public static List<ReservationResponse> from(List<Reservation> reservations) {
        return reservations.stream()
                .map(ReservationResponse::from)
                .toList();
    }
}
