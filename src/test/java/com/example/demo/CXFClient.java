package com.example.demo;

import com.example.demo.soap.service.ws.UserServiceWS;
import com.example.demo.soap.vo.User;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;

public class CXFClient {

	public static void main(String[] args) {
		wsClient();
		rsClient();
	}
	public static void wsClient()
	{
		try
		{
			// 接口地址
			String address = "http://localhost:8080/spring_demo/services/UserServiceWS?wsdl";
			// 代理工厂
			JaxWsProxyFactoryBean jaxWsProxyFactoryBean = new JaxWsProxyFactoryBean();
			// 设置代理地址
			jaxWsProxyFactoryBean.setAddress(address);
			// 设置接口类型
			jaxWsProxyFactoryBean.setServiceClass(UserServiceWS.class);
			// 创建一个代理接口实现
			UserServiceWS userServiceWS = (UserServiceWS) jaxWsProxyFactoryBean.create();

			// 调用代理接口的方法调用并返回结果
			User result = userServiceWS.findUserById("123");
			System.out.println("WS接口返回结果:" + result.getName());
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	public static void rsClient()
	{
		try
		{
//			String url="http://localhost:8080/spring_demo/services/customerServiceRS/1/info?_type=xml";
			String url="http://localhost:8080/spring_demo/services/UserServiceRS/1/info?_type=json";
			HttpClient client = new HttpClient();
			GetMethod method = new GetMethod(url);
			int statusCode = client.executeMethod(method);
			if (statusCode != HttpStatus.SC_OK) {
				System.err.println("Method failed: " + method.getStatusLine());
			}
			byte[] responseBody = method.getResponseBody();
			System.out.println("RS接口返回结果："+new String(responseBody));
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
