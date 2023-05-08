package me.euichan.inflearnjavatest.study;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.*;
import static org.mockito.Mockito.*;

import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import me.euichan.inflearnjavatest.domain.Member;
import me.euichan.inflearnjavatest.domain.Study;
import me.euichan.inflearnjavatest.member.MemberService;

@ExtendWith(MockitoExtension.class)
class StudyServiceTest {

	@Mock
	MemberService memberService;

	@Mock
	StudyRepository studyRepository;

	@Test
	void createNewStudy() {
		// Given
		StudyService studyService = new StudyService(memberService, studyRepository);
		assertThat(studyService).isNotNull();

		Member member = new Member();
		member.setId(1L);
		member.setEmail("keesun@email.com");

		Study study = new Study(10, "테스트");

		// when(memberService.findById(1L)).thenReturn(Optional.of(member));
		// when(studyRepository.save(study)).thenReturn(study);

		given(memberService.findById(1L)).willReturn(Optional.of(member));
		given(studyRepository.save(study)).willReturn(study);

		// When
		studyService.createNewStudy(1L, study);

		// Then
		assertThat(member).isEqualTo(study.getOwner());
		// verify(memberService, times(1)).notify(study);
		// verifyNoMoreInteractions(memberService);

		then(memberService).should(times(1)).notify(study);
		then(memberService).shouldHaveNoMoreInteractions();
	}

	@DisplayName("다른 사용자가 볼 수 있도록 스터디를 공개한다.")
	@Test
	void openStudy() {
		// Given
		StudyService studyService = new StudyService(memberService, studyRepository);
		Study study = new Study(10, "더 자바, 테스트");

		// When
		// TODO studyRepository Mock 객체의 save 호출 시 study 리턴하도록 만들기
		given(studyRepository.save(study)).willReturn(study);
		Study openStudy = studyService.openStudy(study);

		// Then
		// TODO study의 status가 OPENED로 변경됐는지 확인
		// TODO study의 openedDateTime이 null이 아닌지 확인
		// TODO memberService notify(study)가 호출 됐는지 확인
		assertThat(openStudy.getStatus()).isEqualTo(StudyStatus.OPENED);
		assertThat(openStudy.getOpenedDateTime()).isNotNull();
		then(memberService).should().notify(openStudy);
	}
}