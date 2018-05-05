package cn.hxy.bbs.service.impl;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import cn.hxy.bbs.mapper.LogMapper;
import cn.hxy.bbs.model.Log;
import cn.hxy.bbs.model.User;
import cn.hxy.bbs.realm.UserRealm;
import cn.hxy.bbs.service.LogService;

@Service
public class LogServiceImpl implements LogService {
	@Autowired
	private LogMapper logMapper;

	@Override
	public void addLog(String method) {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		User user = (User)request.getSession().getAttribute(UserRealm.SESSION_USER_KEY);
		Log log = new Log();
		log.setAdminId(user.getId());
		log.setMehtod(method);
		logMapper.insertSelective(log);
	}

}
