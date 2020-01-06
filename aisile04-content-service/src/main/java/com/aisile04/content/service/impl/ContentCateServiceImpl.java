package com.aisile04.content.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.aisile04.content.service.ContentCateService;
import com.aisile04.mapper.TbContentCategoryMapper;
import com.aisile04.pojo.TbContentCategory;
import com.aisile04.pojo.TbContentCategoryExample;
import com.aisile04.pojo.entity.PageResult;
import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
@Service
public class ContentCateServiceImpl implements ContentCateService {

	@Autowired
	private TbContentCategoryMapper tbContentCategoryMapper;
	@Override
	public List<TbContentCategory> findAll() {
		// TODO Auto-generated method stub
		return tbContentCategoryMapper.selectByExample(null);
	}
	@Override
	public PageResult search(int index, int pageSize, TbContentCategory tbContentCategory) {
		// TODO Auto-generated method stub
		PageHelper.startPage(index, pageSize);
		TbContentCategoryExample example=new TbContentCategoryExample();
		if(tbContentCategory.getName()!=null&&!"".equals(tbContentCategory.getName())){
			example.createCriteria().andNameLike("%"+tbContentCategory.getName()+"%");
		}
		PageInfo<TbContentCategory> list=new PageInfo<TbContentCategory>(tbContentCategoryMapper.selectByExample(example));
		return new PageResult(list.getTotal(),list.getList());
	}
	@Override
	public boolean add(TbContentCategory tbContentCategory) {
		// TODO Auto-generated method stub
		try {
			if(tbContentCategory.getId()!=null){
				tbContentCategoryMapper.updateByPrimaryKey(tbContentCategory);
				return true;
			}else{
				tbContentCategoryMapper.insert(tbContentCategory);
				return true;
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public TbContentCategory show(long id) {
		// TODO Auto-generated method stub
		return tbContentCategoryMapper.selectByPrimaryKey(id);
	}

	@Override
	public boolean del(long[] ids) {
		// TODO Auto-generated method stub
		try {
			for (long l : ids) {
				tbContentCategoryMapper.deleteByPrimaryKey(l);
			}
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}



}
