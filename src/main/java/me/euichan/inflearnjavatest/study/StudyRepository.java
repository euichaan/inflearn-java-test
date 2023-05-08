package me.euichan.inflearnjavatest.study;

import org.springframework.data.jpa.repository.JpaRepository;

import me.euichan.inflearnjavatest.domain.Study;

public interface StudyRepository extends JpaRepository<Study, Long> {
}
