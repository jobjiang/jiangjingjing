package com.aisile04.content.service;

import java.util.List;

import com.aisile04.pojo.TbContent;
import com.aisile04.pojo.TbItemCat;
import com.aisile04.pojo.entity.PageResult;

public interface ContentService {

	public PageResult search(int index,int pageSize,TbContent tbContent);
	
	public boolean add(TbContent tbContent);
	
	public TbContent show(long id);

	public boolean del(long[] ids);
	
	public boolean updateStatus(long[] ids);

	public boolean updateStatus1(long[] ids);
	
	public List<TbContent> findByCategoryId(Long categoryId);
	
	public TbContent findContent(Long id);

	public List<TbItemCat> findAll();
}
