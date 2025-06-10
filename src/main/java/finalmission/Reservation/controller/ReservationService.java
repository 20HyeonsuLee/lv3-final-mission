package finalmission.Reservation.controller;

import finalmission.Reservation.controller.dto.ReservationRequest;
import finalmission.Reservation.controller.dto.ReservationResponse;
import finalmission.Reservation.model.Reservation;
import finalmission.Reservation.service.ReservationCommand;
import finalmission.Reservation.service.ReservationQuery;
import finalmission.Schedule.model.Schedule;
import finalmission.Schedule.service.ScheduleQuery;
import finalmission.golbal.exception.AccessDeniedException;
import finalmission.member.model.Customer;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ReservationService {

    private final ReservationCommand reservationCommand;
    private final ScheduleQuery scheduleQuery;
    private final ReservationQuery reservationQuery;

    public void createReservation(final ReservationRequest request, final Customer customer) {
        Schedule schedule = scheduleQuery.getById(request.scheduleId());
        reservationCommand.createReservation(customer, schedule);
    }

    public void deleteById(final Long id, Customer customer) {
        Reservation reservation = reservationQuery.getById(id);
        if (!reservation.isSameCustomer(customer)) {
            throw new AccessDeniedException("자신의 예약만 삭제할 수 있습니다.");
        }
        reservationCommand.deleteById(id);
    }

    public List<ReservationResponse> findAllMyReservation(final Customer customer) {
        return ReservationResponse.from(reservationQuery.findAllMyReservation(customer));
    }
}
