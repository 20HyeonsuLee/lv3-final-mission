package finalmission.Reservation.repository;

import finalmission.Reservation.model.Reservation;
import finalmission.member.model.Customer;
import finalmission.member.model.member.Member;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    List<Reservation> findAllByCustomer(Customer customer);
}
