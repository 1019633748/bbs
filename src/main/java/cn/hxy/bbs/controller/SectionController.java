package cn.hxy.bbs.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;

import cn.hxy.bbs.dto.SectionSize;
import cn.hxy.bbs.dto.TableData;
import cn.hxy.bbs.log.adminLog;
import cn.hxy.bbs.model.Section;
import cn.hxy.bbs.service.impl.SectionServiceImpl;

@Controller
public class SectionController {
	
	
	@Autowired
	private SectionServiceImpl sectionService;
	
	//管理版块
	@PostMapping("get/admin/section")
	@ResponseBody
	public TableData<SectionSize> adminSection(int pageSize,int pageIndex,String param){
		TableData<SectionSize> tableData = new TableData<SectionSize>();
		PageHelper.startPage(pageIndex, pageSize);
		tableData.setRows(sectionService.getAdminSectionByParam(param));
		tableData.setTotal(sectionService.getAdminSectionByParam(param).size());
		return tableData;
	}
	
	@adminLog
	@PostMapping("hide/sections/{id}")
	@ResponseBody
	public String hideSectionById(@PathVariable("id")int id){
		sectionService.hideSectionById(id,1);
		return null;
	}
	
	@adminLog
	@PostMapping("show/sections/{id}")
	@ResponseBody
	public String showSectionById(@PathVariable("id")int id){
		sectionService.hideSectionById(id,0);
		return null;
	}
	
	@PostMapping("verify/section/{section}")
	@ResponseBody
	public int verifySection(@PathVariable("section")String section){
		if(sectionService.getSectionByName(section)==null){
			return 0;
		}
		return 1;
	}
	
	@adminLog
	@PostMapping("post/section")
	@ResponseBody
	public void addSection(Section section){
		sectionService.addSection(section.getSection());
	}
	
	//版块列表
	@PostMapping("get/sections")
	@ResponseBody
	public List<SectionSize> getSections(){
		return sectionService.findAllSection();
	}
}
