package bbs;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.hxy.bbs.mapper.ContentMapper;
import cn.hxy.bbs.service.impl.ContentServiceImpl;
import cn.hxy.bbs.service.impl.FriendServiceImpl;
import cn.hxy.bbs.service.impl.ImageServiceImpl;
import cn.hxy.bbs.service.impl.SectionServiceImpl;
import cn.hxy.bbs.service.impl.TitleServiceImpl;
import cn.hxy.bbs.service.impl.UserServiceImpl;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:application-mybatis.xml","classpath:application-mail.xml" })
public class TestMabatis {
	@Autowired
	public SectionServiceImpl h;
	@Autowired
	public ContentServiceImpl c;
	@Autowired
	public UserServiceImpl u;
	@Autowired
	private TitleServiceImpl t;
	@Autowired
	private ImageServiceImpl i;
	@Autowired
	private ContentMapper cm;
	@Autowired
	private FriendServiceImpl f;
	
	
	@Test
	public void test1() {
		//System.out.println(h.getSections());
		//System.out.println(u.getUserByID(1));
		//System.out.println(c.getContentById(1));
		//System.out.println(c.getContent(1,1,5));
		//System.out.println(t.getTitleById(1));
		//System.out.println(c.getAllUp(1));
		/*Up up = new Up();
		up.setUserId(1);
		up.setContentId(1);*/
		//System.out.println(c.getRecordFromUp(up));
		//System.out.println(c.getAllUpAfterClick(up));
		//System.out.println(c.getTotalContentByTitleId(1));
		//System.out.println(i.getContentImageByContentId(6));
		//System.out.println(u.getUserByID(1));
		//System.out.println(u.getAllContentByUserId(2));
		//System.out.println(h.getSectionByName("≤‚ ‘"));
		//System.out.println(t.getTitleBySectionName("≤‚ ‘"));
		//System.out.println(f.getAttentionIdList(2));
		//System.out.println(f.getFansIdList(2));
		//System.out.println(u.doUserLogin("alice", "yuigahama"));
		//u.modifyPassword("1019633748@qq.co", null);
		//System.out.println(h.getSections());
		//System.out.println(t.getTitles());
		/*System.out.println(h.findSectionByName("1"));
		System.out.println(c.findContentByName("1"));
		System.out.println(t.findTitleByname("1"));
		System.out.println(u.findUserByName("1"));*/
		System.out.println(u.findUserByName("1").get(0).getCreateDate());
	}
}
