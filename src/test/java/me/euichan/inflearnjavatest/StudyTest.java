package me.euichan.inflearnjavatest;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumeTrue;
import static org.junit.jupiter.api.Assumptions.assumingThat;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.RepetitionInfo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.aggregator.AggregateWith;
import org.junit.jupiter.params.aggregator.ArgumentsAccessor;
import org.junit.jupiter.params.aggregator.ArgumentsAggregationException;
import org.junit.jupiter.params.aggregator.ArgumentsAggregator;
import org.junit.jupiter.params.converter.ArgumentConversionException;
import org.junit.jupiter.params.converter.SimpleArgumentConverter;
import org.junit.jupiter.params.provider.CsvSource;

import me.euichan.inflearnjavatest.domain.Study;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class StudyTest {

	@RegisterExtension
	static FindSlowTestExtension findSlowTestExtension =
		new FindSlowTestExtension(1000L);

	@Order(1)
	@Test
	@DisplayName("스터디 만들기 slow")
	void create_new_study_again() throws InterruptedException {
		Thread.sleep(1005L);
		System.out.println("create1");
	}

	@Order(3)
	@DisplayName("스터디 만들기")
	@RepeatedTest(value = 10, name = "{displayName}, {currentRepetition}/{totalRepetitions}")
	void repeatTest(RepetitionInfo repetitionInfo) {
		System.out.println("test " + repetitionInfo.getCurrentRepetition() + "/" +
			repetitionInfo.getTotalRepetitions());
	}

	@Order(4)
	@DisplayName("스터디 만들기")
	@ParameterizedTest(name = "{index} {displayName} message={0}")
	@CsvSource({"10, '자바 스터디'", "20, 스프링"})
	void parameterizedTest(@AggregateWith(StudyAggregator.class) Study study) {
		System.out.println(study);
	}

	static class StudyAggregator implements ArgumentsAggregator {

		@Override
		public Object aggregateArguments(ArgumentsAccessor argumentsAccessor,
			ParameterContext parameterContext) throws ArgumentsAggregationException {
			return new Study(argumentsAccessor.getInteger(0), argumentsAccessor.getString(1));
		}
	}

	static class StudyConverter extends SimpleArgumentConverter {

		@Override
		protected Object convert(Object o, Class<?> aClass) throws ArgumentConversionException {
			assertEquals(Study.class, aClass, "Can only convert to Study");
			return new Study(Integer.parseInt(o.toString()));
		}
	}

	@BeforeAll
	static void beforeAll() {
		System.out.println("before all");
	}

	@AfterAll
	static void afterAll() {
		System.out.println("after all");
	}

	@BeforeEach
	void beforeEach() {
		System.out.println("before each");
	}

	@AfterEach
	void afterEach() {
		System.out.println("after each");
	}

}