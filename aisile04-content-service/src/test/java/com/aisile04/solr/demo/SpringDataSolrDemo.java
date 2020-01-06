package com.aisile04.solr.demo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.solr.core.SolrTemplate;
import org.springframework.data.solr.core.query.Criteria;
import org.springframework.data.solr.core.query.Query;
import org.springframework.data.solr.core.query.SimpleQuery;
import org.springframework.data.solr.core.query.result.ScoredPage;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.aisile04.pojo.TbItem;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:spring/applicationContext-solr.xml")
public class SpringDataSolrDemo {

	@Autowired
	SolrTemplate solrTemplate;
	
	//删除所有数据
	@Test
	public void testDeleteAll(){
		Query query=new SimpleQuery("*:*");
		solrTemplate.delete(query);
		solrTemplate.commit();
	}

	
	
	@Test
	public void testPageQuery(){
		Query query=new SimpleQuery("*:*");
		query.setOffset(0);//开始索引（默认0）
		query.setRows(20);//每页记录数(默认10)
		
		//查询包含2的数据
		Criteria criteria=new Criteria("item_title").contains("2");
		query.addCriteria(criteria);
		
		ScoredPage<TbItem> page = solrTemplate.queryForPage(query, TbItem.class);
		List<TbItem> list = page.getContent();
		for (TbItem tbItem : list) {
			System.out.println(tbItem.getTitle()+" "+page.getTotalPages());
		}
	}	

	
	
	//循环添加100条数据
	@Test
	public void testAddList(){
		List<TbItem> list=new ArrayList();
		
		for(int i=0;i<100;i++){
			TbItem item=new TbItem();
			item.setId(i+1L);
			item.setBrand("华为");
			item.setCategory("手机");
			item.setGoodsId(1L);
			item.setSeller("华为2号专卖店");
			item.setTitle("华为Mate"+i);
			item.setPrice(new BigDecimal(2000+i));	
			list.add(item);
		}
		
		solrTemplate.saveBeans(list);
		solrTemplate.commit();
	}

	
	//删除id
	@Test
	public void findById(){
		//TbItem item = solrTemplate.getById(10, TbItem.class);
		solrTemplate.deleteById("doc04");
		solrTemplate.commit();
		//System.out.println(item.getBrand());
	}

	
	//添加单条数据
	@Test
	public void add(){
		TbItem item=new TbItem();
		item.setId(10L);
		item.setBrand("奔驰");
		
		solrTemplate.saveBean(item);
		
		solrTemplate.commit();
	}

}
