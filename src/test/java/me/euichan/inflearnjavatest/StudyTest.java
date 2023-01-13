package me.euichan.inflearnjavatest;

import static org.junit.jupiter.api.Assertions.*;

import java.time.Duration;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class StudyTest {

  @Test
  @DisplayName("스터디 만들기")
  void create() {
    assertTimeoutPreemptively(Duration.ofMillis(100), () -> {
      new Study(10);
      Thread.sleep(300);
    });
  }
  // TODO ThreadLocal



}