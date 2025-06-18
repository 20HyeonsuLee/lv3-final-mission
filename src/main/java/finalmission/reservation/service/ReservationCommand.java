package finalmission.reservation.service;

import finalmission.reservation.model.Reservation;
import finalmission.reservation.repository.ReservationRepository;
import finalmission.schedule.model.Schedule;
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
