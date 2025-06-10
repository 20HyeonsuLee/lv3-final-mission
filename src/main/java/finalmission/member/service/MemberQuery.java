package finalmission.member.service;

import finalmission.member.model.member.Email;
import finalmission.member.model.member.Member;
import finalmission.member.model.member.Password;
import finalmission.member.repository.MemberRepository;
import java.util.NoSuchElementException;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MemberQuery {

    private final MemberRepository memberRepository;

    public Member getByEmailAndPassword(final Email email, final Password password) {
        return memberRepository.findMemberByEmailAndPassword(email, password)
                .orElseThrow(() -> new NoSuchElementException("존재하지 않는 유저입니다."));
    }

    public Optional<Member> findByEmailAndPassword(final Email email, final Password password) {
        return memberRepository.findMemberByEmailAndPassword(email, password);
    }

    public boolean existsByEmailAndPassword(final Email email, final Password password) {
        return memberRepository.existsByEmailAndPassword(email, password);
    }
}
