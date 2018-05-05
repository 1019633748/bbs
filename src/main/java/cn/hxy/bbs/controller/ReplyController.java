package cn.hxy.bbs.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;

import cn.hxy.bbs.dto.ReplyDetail;
import cn.hxy.bbs.dto.TableData;
import cn.hxy.bbs.log.adminLog;
import cn.hxy.bbs.model.Reply;
import cn.hxy.bbs.model.User;
import cn.hxy.bbs.realm.UserRealm;
import cn.hxy.bbs.service.impl.PostServiceImpl;
import cn.hxy.bbs.service.impl.ReplyServiceImpl;
import cn.hxy.bbs.service.impl.SectionServiceImpl;
import cn.hxy.bbs.service.impl.SensitiveServiceImpl;
import cn.hxy.bbs.service.impl.TopServiceImpl;

@Controller
public class ReplyController {
	@Autowired
	private ReplyServiceImpl replyService;
	@Autowired
	private SensitiveServiceImpl sensitiveService;
	@Autowired
	private SectionServiceImpl sectionService;
	@Autowired
	private TopServiceImpl topService;
	@Autowired
	private PostServiceImpl postService;
	
	//
	@GetMapping("get/posts/{id}/{replyId}")
	public String getPostsbyReplyId(@PathVariable("id")int id,@RequestParam(defaultValue="1")int pageNum,@RequestParam(defaultValue="5")int pageSize,@PathVariable("replyId")int replyId,Model model,HttpSession session){
		User user = (User) session.getAttribute(UserRealm.SESSION_USER_KEY);
		model.addAttribute("section",sectionService.getSectionByPostId(id));
		model.addAttribute("post", postService.getPostById(id));
		int pageIndex = (int) Math.ceil((double)replyService.getFollor(id, replyId)/pageSize);
		PageHelper.startPage(pageIndex, pageSize);
		List<ReplyDetail> replyDetails = replyService.getReplyDetailByPostId(id);
		if(user != null){
			for(ReplyDetail replyDetail:replyDetails){
				replyDetail.setTopOrDown(topService.getStatus(user.getId(), replyDetail.getId())+"");
			}
		}
		model.addAttribute("reply", replyDetails);
		
		int total = replyService.getReplyDetailByPostId(id).size();
		model.addAttribute("total",total );
		model.addAttribute("totalPage",(int)Math.ceil((double)total/pageSize) );
		model.addAttribute("pageNum", pageIndex);
		return "post";
	}
	
	// ªÿ∏¥
	@PostMapping("post/reply")
	@ResponseBody
	public String reply(Reply reply, HttpSession session) {
		for (String word : sensitiveService.getAllWord()) {
			reply.setReply(reply.getReply().replace(word, "***"));
		}
		return replyService.reply(reply, session);
	}

	@PostMapping("/get/replys/{userId}")
	@ResponseBody
	public TableData<ReplyDetail> getReplyByUserId(@PathVariable("userId") int userId, int pageIndex, int pageSize,
			String param) {
		TableData<ReplyDetail> td = new TableData<ReplyDetail>();
		PageHelper.startPage(pageIndex, pageSize);
		td.setRows(replyService.getReplyByUserId(userId, param));
		td.setTotal(replyService.getReplyByUserId(userId, param).size());
		return td;
	}
	
	//chart
	@SuppressWarnings("rawtypes")
	@PostMapping("get/reply/create")
	@ResponseBody
	public List<Map> getReplyCreateDate(){
		return replyService.getReplyCreate();
	}
	
	//π‹¿Ì
	@PostMapping("get/admin/reply")
	@ResponseBody
	public  TableData<ReplyDetail> getReplydetail(int pageSize,int pageIndex,String param){
		TableData<ReplyDetail> tableData = new TableData<ReplyDetail>();
		PageHelper.startPage(pageIndex, pageSize);
		tableData.setRows(replyService.getReplyDetailByName(param));
		tableData.setTotal(replyService.getReplyDetailByName(param).size());
		return tableData;
	}
	
	@adminLog
	@PostMapping("hide/replys/{id}")
	@ResponseBody
	public String hideReplyById(@PathVariable("id")int id){
		replyService.hideReplyById(id,1);
		return null;
	}
	
	@adminLog
	@PostMapping("show/replys/{id}")
	@ResponseBody
	public String showReplyById(@PathVariable("id")int id){
		replyService.hideReplyById(id,0);
		return null;
	}
}
