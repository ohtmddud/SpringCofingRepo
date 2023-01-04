package com.multi.erp.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

// 공통관심 사항을 별도로 정의 할 클래스 - Advice
@Component
// aop 를 적용할 Advice 임을 나타내는 어노테이션 기호(aop 로 서비스할 클래스 - 공통모듈이라는 의미)
@Aspect
public class TimeCheckAopObj {
//	@After
//	@Before
//	공통 로직이 어떤 핵심 로직에서 실행 될 것인지 정의, 어느 시점에 실행 될 것인지 정의
//	@Around 는 메소드 실행 전과 메소드 실행 후에 모두 호출
	// board 패키지의 모든 클래스의 모든 메소드에 적용 하겠다.
	@Around("execution(* com.multi.erp.board..*Service*.*(..))")
	public Object execute(ProceedingJoinPoint joinpoint) throws Throwable {
		long start = System.currentTimeMillis();
		Object obj = null;
		
		try {
			System.out.println("START======================="+joinpoint.toString());
			// 핵심 로직이 구현 된 메소드가 호출 된다.
			obj = joinpoint.proceed();
			return obj;
		} finally {
			long end = System.currentTimeMillis();
			System.out.println("END===========================" + joinpoint.toString()+":"+(end-start));
		}
	}
	
}
