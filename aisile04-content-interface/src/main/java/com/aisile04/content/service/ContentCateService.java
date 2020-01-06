package com.aisile04.content.service;

import java.util.List;

import com.aisile04.pojo.TbContentCategory;
import com.aisile04.pojo.entity.PageResult;

public interface ContentCateService {

	public List<TbContentCategory> findAll();

	public PageResult search(int index,int pageSize,TbContentCategory tbContentCategory);
	
	public boolean add(TbContentCategory tbContentCategory);
	
	public TbContentCategory show(long id);

	public boolean del(long[] ids);
	
}
