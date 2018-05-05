package cn.hxy.bbs.log;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.hxy.bbs.service.impl.LogServiceImpl;

@Aspect
@Component
public class ArchivesLog {
	@Autowired
	private LogServiceImpl logService;

	@Pointcut("@annotation(cn.hxy.bbs.log.adminLog)")
	public void adminLog() {
	}

	@After("adminLog()")
	public void print(JoinPoint joinPoint) {
		logService.addLog(joinPoint.getSignature().getName());
		//System.out.println(joinPoint.getSignature().getName());
	}
}
