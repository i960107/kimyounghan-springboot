package hello.hellospring.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

//hello.hellospring 패키지 하위에 다 적용
@Aspect
public class TimeTraceAop {

    //joinpoint: 리턴타입 패키지경로 클래스명 메소드명(매개변수)
    //순환 참조 문제 해결하기 위해 AOP 빈 설정 메소드 제외.
    @Around("execution(* hello.hellospring..*(..)) &&"
            + "!target(hello.hellospring.SpringConfig)")
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        System.out.println("start: " + joinPoint.toString());
        try {
            return joinPoint.proceed();
        } finally {
            long finish = System.currentTimeMillis();
            long time = finish - start;
            System.out.println("end: " + joinPoint.toString() + " " + time + "ms");
        }
    }
}
