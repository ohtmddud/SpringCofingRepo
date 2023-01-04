package com.multi.erp.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;


// BoardController 의 insert() 메소드는 파일첨부를 하지 않으면 오류가 발생한다. 그러면 글 작성도 되지 않아야 하는데, 글 작성은 된다.
// 따라서 트랜잭션을 처리하면, 오류가 발생했을 때 글 작성도 안되게 막을 수 있다.

//@Component
//@Aspect
public class TxAdvice {
	@Autowired
	private PlatformTransactionManager transactionManager;
	
	@Pointcut("execution(* com.multi.erp.board.BoardService*.insert(..))")
	public void insertTx() {
		
	}
	
	@Around("insertTx()")
	public Object applyTx(ProceedingJoinPoint joinPoint) throws Throwable {
		TransactionStatus transactionStatus = transactionManager.getTransaction(new DefaultTransactionDefinition());
		try {
			Object obj = joinPoint.proceed();
			transactionManager.commit(transactionStatus);
			return obj;
		} catch (RuntimeException e) {
			// 오류 상황
			System.out.println("rollback");
			transactionManager.rollback(transactionStatus);
			throw e;
		}
	}

}
