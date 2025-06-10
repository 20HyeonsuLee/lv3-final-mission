package finalmission.Reservation.service;

import finalmission.Reservation.model.Reservation;
import finalmission.Reservation.repository.ReservationRepository;
import finalmission.Schedule.model.Schedule;
import finalmission.member.model.Customer;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
@Transactional
public class ReservationCommand {

    private final ReservationRepository reservationRepository;

    public Reservation createReservation(Customer customer, Schedule schedule) {
        Reservation reservation = Reservation.builder()
                .customer(customer)
                .schedule(schedule)
                .build();
        return reservationRepository.save(reservation);
    }

    public void deleteById(final Long id) {
        reservationRepository.deleteById(id);
    }
}
