package com.aisile04.content.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import com.aisile04.content.service.ContentService;
import com.aisile04.mapper.TbContentMapper;
import com.aisile04.mapper.TbItemCatMapper;
import com.aisile04.pojo.TbContent;
import com.aisile04.pojo.TbContentExample;
import com.aisile04.pojo.TbContentExample.Criteria;
import com.aisile04.pojo.TbItemCat;
import com.aisile04.pojo.entity.PageResult;
import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
@Service
public class ContentServiceImpl implements ContentService {

	@Autowired
	private TbContentMapper tbContentMapper;
	@Autowired
	private RedisTemplate redisTemplate;
	
	@Autowired
	TbItemCatMapper tbItemCatMapper;

	@Override
	public PageResult search(int index, int pageSize, TbContent tbContent) {
		// TODO Auto-generated method stub
		PageHelper.startPage(index, pageSize);
		PageHelper.orderBy("sort_order");
		TbContentExample example=new TbContentExample();
		PageInfo<TbContent> list=new PageInfo<TbContent>(tbContentMapper.selectByExample(example));
		return new PageResult(list.getTotal(),list.getList());
	}

	@Override
	public boolean add(TbContent tbContent) {
		// TODO Auto-generated method stub
		try {
			if(tbContent.getId()!=null){
				tbContentMapper.updateByPrimaryKey(tbContent);
				return true;
			}
			else{
				tbContent.setStatus("0");
				tbContentMapper.insert(tbContent);
				//清除缓存
				redisTemplate.boundHashOps("content").delete(tbContent.getCategoryId());
				return true;
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
	}

	@Override
	public TbContent show(long id) {
		// TODO Auto-generated method stub
		return tbContentMapper.selectByPrimaryKey(id);
	}

	@Override
	public boolean del(long[] ids) {
		// TODO Auto-generated method stub
		try {
			for (long l : ids) {
				tbContentMapper.deleteByPrimaryKey(l);
			}
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean updateStatus(long[] ids) {
		// TODO Auto-generated method stub
		try {
			for (long l : ids) {
				TbContent tbContent = tbContentMapper.selectByPrimaryKey(l);
			if(tbContent.getStatus().equals("0")){
				tbContent.setStatus("1");
			}
			tbContentMapper.updateByPrimaryKey(tbContent);
		}
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean updateStatus1(long[] ids) {
		// TODO Auto-generated method stub
		try {
			for (long l : ids) {
				TbContent tbContent = tbContentMapper.selectByPrimaryKey(l);
			if(tbContent.getStatus().equals("1")){
				tbContent.setStatus("0");
			}
			tbContentMapper.updateByPrimaryKey(tbContent);
		}
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public List<TbContent> findByCategoryId(Long categoryId) {
		
		List<TbContent> contentList= (List<TbContent>) redisTemplate.boundHashOps("content").get(categoryId);
		
		if(contentList==null){
			//根据广告分类ID查询广告列表		
			TbContentExample contentExample=new TbContentExample();
			Criteria criteria2 = contentExample.createCriteria();
			criteria2.andCategoryIdEqualTo(categoryId);
			criteria2.andStatusEqualTo("1");//开启状态		
			contentExample.setOrderByClause("sort_order");//排序
			contentList = tbContentMapper.selectByExample(contentExample);
			redisTemplate.boundHashOps("content").put(categoryId, contentList);//存入缓存
		}
		return contentList; 
	}

	@Override
	public TbContent findContent(Long id) {
		// TODO Auto-generated method stub
		return tbContentMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<TbItemCat> findAll() {
		// TODO Auto-generated method stub
		
		return tbItemCatMapper.selectByExample(null);
	}

}
