package com.aisile.portal.controller;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.aisile04.content.service.ContentService;
import com.aisile04.pojo.TbContent;
import com.aisile04.pojo.TbItemCat;
import com.alibaba.dubbo.config.annotation.Reference;


@RestController
@RequestMapping("/content")
public class ContentController {

	
	
	@Reference
	private ContentService contentService;
	@RequestMapping("/findByCategoryId")
	public List<TbContent> findByCategoryId(Long categoryId) {
		return contentService.findByCategoryId(categoryId);
	}	
	
	@RequestMapping("/findZTree")
	public List<TbItemCat> findZTree() {
		List<TbItemCat> itemCatList = contentService.findAll();
		return itemCatList;
	}
}