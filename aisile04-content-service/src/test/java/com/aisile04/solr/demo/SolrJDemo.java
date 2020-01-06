package com.aisile04.solr.demo;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.junit.Test;

public class SolrJDemo {

	//查询
	@Test
	public void listSolr() throws SolrServerException{
		//httpClient
		SolrServer solrServer = new HttpSolrServer("http://192.168.254.128:8088/solr/collection1");
		// 第二步：创建一个SolrQuery对象。
		SolrQuery query = new SolrQuery();
		// 第三步：向SolrQuery中添加查询条件、过滤条件。。。
		//query.setQuery("*:*");
		query.set("q", "*:*");
		// 第四步：执行查询。得到一个Response对象。
		QueryResponse queryResponse = solrServer.query(query);
		// 第五步：取查询结果。取文档列表。取查询结果的总记录数
		SolrDocumentList solrDocumentList = queryResponse.getResults();
		//System.out.println("查询结果总记录数：" + solrDocumentList.getNumFound());
		// 第六步：遍历结果并打印。遍历文档列表，从取域的内容。
		for (SolrDocument solrDocument : solrDocumentList) {
				//System.out.println(solrDocument.get("id"));
				System.out.println(solrDocument.get("item_title"));
				//System.out.println(solrDocument.get("item_sell_point"));
				//System.out.println(solrDocument.get("item_price"));
				//System.out.println(solrDocument.get("item_image"));
				//System.out.println(solrDocument.get("item_category_name"));
			}
		}
	
	//高亮显示
	@Test
	public void listSolr1() throws SolrServerException{
		//httpClient
		SolrServer solrServer = new HttpSolrServer("http://192.168.254.128:8088/solr/collection1");
		// 第二步：创建一个SolrQuery对象。
		SolrQuery query = new SolrQuery();
		// 第三步：向SolrQuery中添加查询条件、过滤条件。。。
		//query.setQuery("*:*");
		query.setQuery("苹果");
		//拼接条件
		query.set("df", "item_keywords");
		//开启高亮显示
		query.setHighlight(true);
		//域
		query.addHighlightField("item_title");
		//前缀
		query.setHighlightSimplePre("<span color='red'>");
		//后缀
		query.setHighlightSimplePost("</span>");
		// 第四步：执行查询。得到一个Response对象。
		QueryResponse response = solrServer.query(query);
		// 第五步：取查询结果。取文档列表。取查询结果的总记录数
		SolrDocumentList solrDocumentList = response.getResults();
		//System.out.println("查询结果总记录数：" + solrDocumentList.getNumFound());
		//取高亮显示
		Map<String, Map<String, List<String>>> highlighting = response.getHighlighting();
		// 第六步：遍历结果并打印。遍历文档列表，从取域的内容。
		for (SolrDocument solrDocument : solrDocumentList) {
				List<String> list2 = highlighting.get(solrDocument.get("id")).get("item_title");
				String title = (String)solrDocument.get("item_title");
				
				if(list2!=null){
					//证明要替换
					title = list2.get(0);
				}
			
				System.out.println(title);
			}
		}
		
	
	//删除
	@Test
	public void delete() throws SolrServerException, IOException{
		//httpClient
		SolrServer solrServer = new HttpSolrServer("http://192.168.254.128:8088/solr/collection1");
		//solrServer.deleteById("doc01");
		//*:*全部
		solrServer.deleteByQuery("id:doc01");
		solrServer.commit();
	}
	
	//添加
	@Test
	public void add(){
		//httpClient
		SolrServer solrServer = new HttpSolrServer("http://192.168.254.128:8088/solr/collection1");
		//新建文档对象
		SolrInputDocument inputs = new SolrInputDocument();
		//向文档对象中添加域。文档中必须包含一个id域，所有的域的名称必须在schema.xml中定义
		inputs.addField("id", "doc04");
		inputs.addField("item_title", "手机33");
		inputs.addField("item_seller", "苹果33");
		inputs.addField("item_price", 10000);
		try {
			//把文档写入索引库
			solrServer.add(inputs);
			//提交
			solrServer.commit();
		} catch (SolrServerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
