package gdut.ff.solr;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.beans.Field;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.client.solrj.response.UpdateResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.apache.solr.common.params.MapSolrParams;
import org.junit.Test;

/**
 * Solr测试
 * @author liuffei
 *
 */
public class TestSolrJ {

	//本地的solr服务端
	private final String solrUrl = "http://127.0.0.1:8983/solr";
	
	/**
	 * 定义一个实体
	 * @author liuffei
	 *
	 */
	public static class SolrBean{
		@Field
		private String id;
		@Field
		private String[] name;
		
		public SolrBean(String id,String[] name) {
			this.id = id;
			this.name = name;
		}
		
		public SolrBean() {
			
		}
	}
	
	/**
	 * 建立连接
	 * @return
	 */
	public HttpSolrClient getHttpSolrClient() {
		return new HttpSolrClient.Builder(solrUrl)
		.withConnectionTimeout(1000)
		.withSocketTimeout(60000)
	    .build();
	}
	
	/**
	 * 查询服务端指定集合的内容
	 * @throws Exception
	 */
	@Test
	public void testQuery() throws Exception {
		SolrClient client = getHttpSolrClient();
		
		Map<String,String> queryParamMap = new HashMap<String,String>();
		queryParamMap.put("q", "*:*");
		//过滤字段
		queryParamMap.put("f1", "name");
		MapSolrParams queryParams = new MapSolrParams(queryParamMap);
		
		//集合是test
		QueryResponse response = client.query("test", queryParams);
		
        SolrDocumentList documents = response.getResults();
        for(SolrDocument document:documents) {
        	ArrayList list =  (ArrayList) document.get("name");
        	System.out.println(list);
        }
	}
	
	/**
	 * 向test集合内添加内容
	 * @throws Exception
	 */
	@Test
	public void testAdd() throws Exception {
		SolrClient client = getHttpSolrClient();
		
		SolrInputDocument doc = new SolrInputDocument();
		doc.addField("name", "hello");
		doc.addField("id", UUID.randomUUID().toString());
		
		UpdateResponse updateResponse = client.add("test", doc);
		client.commit("test");
		System.out.println(updateResponse.getStatus());
	}
	
	/**
	 * 向test集合添加实体
	 * @throws IOException
	 * @throws SolrServerException
	 */
	@Test
	public void testSolrBean() throws IOException, SolrServerException {
		SolrClient client = getHttpSolrClient();
		
		SolrBean bean = new SolrBean(UUID.randomUUID().toString(),new String[] {"solrBean"});
				
		client.addBean("test", bean);
		
		client.commit("test");
		client.close();
	}
	/**
	 * 将从集合test中查询出的内容转成实体
	 * @throws IOException
	 * @throws SolrServerException
	 */
	@Test
	public void testConvertToBean() throws IOException, SolrServerException {
		SolrClient client = getHttpSolrClient();
		SolrQuery query = new SolrQuery("*:*");
		query.addField("id");
		query.addField("name");
		
		QueryResponse response = client.query("test",query);
		List<SolrBean> list = response.getBeans(SolrBean.class);
	    if(null != list && list.size() > 0) {
	    	for(int i = 0;i < list.size();i++) {
	    		System.out.println("id="+list.get(i).id+",name="+list.get(i).name[0]);
	    	}
	    }
	}
}
