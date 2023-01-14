package me.euichan.inflearnjavatest;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME) //이 애노테이션을 사용한 코드가 애노테이션 정보를 런타임까지 유지해야 한다.
@Test //메타 애노테이션
@Tag("fast") //메타 애노테이션
public @interface FastTest { //Composed Annotation

}
