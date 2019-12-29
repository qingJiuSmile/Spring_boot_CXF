package com.example.demo.soap.service.action;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.soap.vo.User;
import com.fasterxml.jackson.jaxrs.json.JacksonJaxbJsonProvider;
import org.apache.cxf.endpoint.Client;
import org.apache.cxf.jaxrs.client.WebClient;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/action")
public class WebServiceConller {


    // 注入配置的转json工具
    @Autowired
    private List<JacksonJaxbJsonProvider> jsonProvider;
    @PostMapping("/gotoService")
    public String gotoService(String name){
        JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance();
        Client client = dcf.createClient("http://localhost:8080/spring_demo/myWebservice/UserServiceWS?wsdl");
        // 需要密码的情况需要加上用户名和密码
        // client.getOutInterceptors().add(new ClientLoginInterceptor(USER_NAME,PASS_WORD));
        Object[] objects = new Object[0];
        try {
            // invoke("方法名",参数1,参数2,参数3....);
            objects = client.invoke("findUserByName", name);
            System.out.println("返回数据:" + JSONObject.toJSONString(objects));
            String str = JSONObject.toJSONString(objects[0]);
            JSONObject jb = JSON.parseObject(str);
            System.out.println(jb.getString("name"));
        } catch (java.lang.Exception e) {
            e.printStackTrace();
        }
        return objects[0].toString();
    }

    @PostMapping("/getRs")
    public List<User> getRs(String name){
        //调用webservice获取查询数据
        User customer = WebClient
                .create("http://localhost:8080/spring_demo/myWebservice/UserServiceRS/search?name=name"+name, jsonProvider)
                .accept(MediaType.APPLICATION_JSON).get(User.class);
        List<User> ls = new ArrayList<>();
        ls.add(customer);
        return ls;
    }
}
