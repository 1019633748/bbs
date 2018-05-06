package cn.hxy.bbs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;

import cn.hxy.bbs.dto.TableData;
import cn.hxy.bbs.log.adminLog;
import cn.hxy.bbs.model.Sensitive;
import cn.hxy.bbs.service.impl.SensitiveServiceImpl;

@Controller
public class SensitiveController {
	@Autowired
	private SensitiveServiceImpl sensitiveService;

	@PostMapping("get/admin/sensitive")
	@ResponseBody
	public TableData<Sensitive> adminSnsitive(int pageSize, int pageIndex, String param) {
		TableData<Sensitive> tableData = new TableData<Sensitive>();
		PageHelper.startPage(pageIndex, pageSize);
		tableData.setRows(sensitiveService.getSensitiveByWord(param));
		tableData.setTotal(sensitiveService.getSensitiveByWord(param).size());
		return tableData;
	}

	@adminLog
	@PostMapping("hide/sensitives/{id}")
	@ResponseBody
	public String hideSensitiveById(@PathVariable("id") int id) {
		sensitiveService.hideSensitiveById(id, 1);
		return null;
	}

	@adminLog
	@PostMapping("show/sensitives/{id}")
	@ResponseBody
	public String showSensitiveById(@PathVariable("id") int id) {
		sensitiveService.hideSensitiveById(id, 0);
		return null;
	}

	@PostMapping("verify/sensitive/{sensitive}")
	@ResponseBody
	public int verifySensitive(@PathVariable("sensitive") String sensitive) {
		if (sensitiveService.getSensitiveByName(sensitive) == null) {
			return 0;
		}
		return 1;
	}

	@adminLog
	@PostMapping("post/sensitive")
	@ResponseBody
	public void addSensitive(Sensitive sensitive) {
		sensitiveService.addSensitive(sensitive.getWord());
	}
}
