package finalmission.member.repository;

import finalmission.member.model.member.Email;
import finalmission.member.model.member.Member;
import finalmission.member.model.member.Password;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findMemberByEmailAndPassword(Email email, Password password);

    boolean existsByEmailAndPassword(Email email, Password password);
}
