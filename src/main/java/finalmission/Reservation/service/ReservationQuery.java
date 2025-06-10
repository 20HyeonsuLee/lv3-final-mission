package finalmission.Reservation.service;

import finalmission.Reservation.model.Reservation;
import finalmission.Reservation.repository.ReservationRepository;
import finalmission.Schedule.model.Schedule;
import finalmission.member.model.Customer;
import java.util.List;
import java.util.NoSuchElementException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
@Transactional
public class ReservationQuery {

    private final ReservationRepository reservationRepository;

    public List<Reservation> findAllMyReservation(Customer customer) {
        return reservationRepository.findAllByCustomer(customer);
    }

    public Reservation getById(final Long id) {
        return reservationRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("존재하지 않는 예약입니다."));
    }
}
