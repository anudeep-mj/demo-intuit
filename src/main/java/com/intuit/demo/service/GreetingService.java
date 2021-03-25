package com.intuit.demo.service;

import com.google.gson.Gson;
import com.intuit.demo.model.Employee;
import com.intuit.demo.persistence.EmployeeRepository;
import com.intuit.demo.utils.TraceInfo;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.config.SocketConfig;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.InputStreamBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class GreetingService
{
    @Autowired
    EmployeeRepository employeeRepository;

    private static final String GREETING = "Hello world!";

    public String hello () throws IOException, URISyntaxException
    {
//        TraceInfo traceInfo = new TraceInfo();
//        traceInfo.setAppId("name");
//        traceInfo.setRealmId("p2p");
//        traceInfo.setTraceId("tId");
        Map<String, String> traceInfo = new HashMap<>();
        traceInfo.put("appId", "app");
        traceInfo.put("realmId", "p2p");
        traceInfo.put("traceId", "tId");

        File initialFile = new File("/Users/i851226/Downloads/900result.csv");
        InputStream targetStream = new FileInputStream(initialFile);

        SocketConfig socketConfig = SocketConfig.custom().setSoTimeout(1000000000).build();
        HttpClient client = HttpClients.custom().setDefaultSocketConfig(socketConfig).build();

        URIBuilder builder = new URIBuilder("https://garnet.cobalt.ariba.com/filefortificationservice/v1/validate");
        builder.setParameter("compressed", "false");

        HttpPost post = new HttpPost(builder.build());
        MultipartEntityBuilder multipartEntityBuilder = MultipartEntityBuilder.create();
        multipartEntityBuilder.addPart("traceinfo", new StringBody(new Gson().toJson(traceInfo), ContentType.APPLICATION_JSON));
        //multipartEntityBuilder.addPart("file", new InputStreamBody(targetStream, initialFile.getName()));
        multipartEntityBuilder.addPart("file", new FileBody(initialFile));
        HttpEntity entity = multipartEntityBuilder.build();
        post.setEntity(entity);
        post.setHeader("Authorization", "Bearer c48f9576-23e7-468c-a93e-633942c2d86a");
        HttpResponse response = client.execute(post);

        HttpEntity responseEntity = response.getEntity();
        String responseString = EntityUtils.toString(responseEntity, "UTF-8");
        System.out.println(responseString);
        return GREETING;
    }

    public Long addEmployee (final Employee employee)
    {
        return employeeRepository.save(employee).getId();
    }

    public Employee getEmployee (final Long id)
    {
        Optional<Employee> value = employeeRepository.findById(id);
        return value.orElse(null);
    }
}
