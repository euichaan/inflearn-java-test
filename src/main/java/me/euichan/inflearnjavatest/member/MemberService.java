package me.euichan.inflearnjavatest.member;

import java.util.Optional;

import me.euichan.inflearnjavatest.domain.Member;
import me.euichan.inflearnjavatest.domain.Study;

public interface MemberService {

	Optional<Member> findById(Long memberId) throws MemberNotFoundException;

	void validate(Long memberId);

	void notify(Study newStudy);

	void notify(Member member);
}
