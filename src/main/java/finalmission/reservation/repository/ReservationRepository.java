package finalmission.reservation.repository;

import finalmission.reservation.model.Reservation;
import finalmission.member.model.Customer;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    List<Reservation> findAllByCustomer(Customer customer);
}
