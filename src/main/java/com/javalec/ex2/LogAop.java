package com.javalec.ex2;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class LogAop {
	
	@Pointcut("within(com.javalec.ex2.*)")
	private void pointcutMethod() {}
	
	@Around("pointcutMethod()")
	public Object loggerAop(ProceedingJoinPoint joinpoint) throws Throwable {
		String signatureStr = joinpoint.getSignature().toShortString();
		System.out.println(signatureStr + "is start");
		long st = System.currentTimeMillis();
		
		try {
			/*
			 * 	원래 실행하려던 메소드 실행 
			 * 	student.getStudentInfo()
			 * 	worker.getWorkerInfo()
			 * 	만약 joinpoint.proceed(); 를 하지않으면 메소드 실행이 안된다.
			 */
			Object obj = joinpoint.proceed();
			return obj;
		} finally {
			long et = System.currentTimeMillis();
			System.out.println(signatureStr + "is finished");
			System.out.println(signatureStr + " 경과시간 " + (et - st));
		}
	}
	
	@Before("within(com.javalec.ex2.*)")
	public void beforeAdvice() {
		System.out.println("메소드 실행 전 호출");
	}
}
