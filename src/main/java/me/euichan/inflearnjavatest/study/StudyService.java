package me.euichan.inflearnjavatest.study;

import java.util.Optional;

import me.euichan.inflearnjavatest.domain.Member;
import me.euichan.inflearnjavatest.domain.Study;
import me.euichan.inflearnjavatest.member.MemberService;

public class StudyService {

	private final MemberService memberService;
	private final StudyRepository repository;

	public StudyService(final MemberService memberService, final StudyRepository repository) {
		assert memberService != null;
		assert repository != null;
		this.memberService = memberService;
		this.repository = repository;
	}

	public Study createNewStudy(Long memberId, Study study) {
		Optional<Member> member = memberService.findById(memberId);
		study.setOwner(member.orElseThrow(() -> new IllegalArgumentException("Member doesn't exists")));
		Study newStudy =  repository.save(study);
		memberService.notify(newStudy);
		return newStudy;
	}

	public Study openStudy(Study study) {
		study.open();
		Study openedStudy = repository.save(study);
		memberService.notify(openedStudy);
		return openedStudy;
	}
}
