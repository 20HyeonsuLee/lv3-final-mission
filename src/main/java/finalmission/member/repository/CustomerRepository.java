package finalmission.member.repository;

import finalmission.member.model.Customer;
import finalmission.member.model.member.Email;
import finalmission.member.model.member.Member;
import finalmission.member.model.member.Password;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
