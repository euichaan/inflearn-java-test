package me.euichan.inflearnjavatest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

class StudyTest {

  @Test
  void create() {
    Study study = new Study();
    assertNotNull(study);
    System.out.println("create");
  }

  @Test
  void create1() {
    System.out.println("create1");
  }

  /**
   * 모든 테스트 실행하기 전 딱 한번만 호출
   * private, return type 안됨. default는 가능
   */
  @BeforeAll
  static void beforeAll() {
    System.out.println("before all");
  }

  /**
   * 모든 테스트가 실행된 이후 딱 한번만 호출
   */
  @AfterAll
  static void afterAll() {
    System.out.println("after all");
  }

  /**
   * 모든 테스트를 실행할 때 각각의 테스트 실행하기 이전에 한번씩 호출된다.
   */
  @BeforeEach
  void beforeEach() {
    System.out.println("before each");
  }

  /**
   * 모든 테스트를 실행할 때 각각의 테스트 실행하기 이후에 한번씩 호출된다.
   */
  @AfterEach()
  void afterEach() {
    System.out.println("after each");
  }
}