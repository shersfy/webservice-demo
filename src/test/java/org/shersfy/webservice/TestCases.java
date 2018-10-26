package org.shersfy.webservice;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.apache.cxf.endpoint.Client;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.InputStreamEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.Test;

import com.alibaba.fastjson.JSON;

public class TestCases {
	
	private static int connectionTimeout = 20000;
	private static int socketTimeout 	 = 20000;
    
	/**XML application/soap+xml方式**/
    @Test
    public void test01() throws IOException{
    	String url = "http://localhost:8080/service/user";
    	File file  = new File("D:\\data\\webservice\\test.xml");
		
		HttpPost post = new HttpPost(url);
		
		InputStream input = new FileInputStream(file);
		ContentType type  = ContentType.create("application/soap+xml", "utf-8");
		InputStreamEntity entity = new InputStreamEntity(input, type);
		post.setEntity(entity);
		
		RequestConfig requestConfig = RequestConfig.custom()
				.setConnectTimeout(connectionTimeout)
				.setSocketTimeout(socketTimeout)
				.build();
		
		CloseableHttpClient client = HttpClientBuilder.create()
		.setDefaultRequestConfig(requestConfig)
		.build();
		
		CloseableHttpResponse res = client.execute(post);
		String content = IOUtils.toString(res.getEntity().getContent());
		System.out.println(content);
		
    }
    
    /**返回对象**/
    @Test
    public void test02() throws Exception {
    	Client client = JaxWsDynamicClientFactory.newInstance().createClient("http://localhost:8080/service/user?wsdl");
    	Object[] res = client.invoke("findList");
    	System.out.println(JSON.toJSONString(res));
    }
    

}
