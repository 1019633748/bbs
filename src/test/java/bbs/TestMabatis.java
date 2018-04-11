package bbs;
import java.util.List;
import java.util.Set;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.hxy.bbs.dto.SectionSize;
import cn.hxy.bbs.service.impl.PostServiceImpl;
import cn.hxy.bbs.service.impl.SectionServiceImpl;
import cn.hxy.bbs.service.impl.UserServiceImpl;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:application-mybatis.xml"})
public class TestMabatis {
	
	@Autowired
	private UserServiceImpl userService;
	
	@Autowired
	private SectionServiceImpl sectionService;
	
	@Autowired
	private PostServiceImpl postService;
	
	
	public void loginTest() {
		System.out.println(userService.doLogin("1","1" ));
	}
	
	
	public void registerTest(){
		System.out.println(new SimpleHash("MD5","1","1",2).toHex());
	}

	@Test
	public void findAllSectionTest(){
		System.out.println(new SimpleHash("MD5","1","admin",2).toHex());
	}
	
	public void findHotPost(){
		System.out.println(postService.findHotPost());
	}
}
