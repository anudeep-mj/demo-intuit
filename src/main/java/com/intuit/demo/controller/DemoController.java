package com.intuit.demo.controller;

import com.intuit.demo.model.Employee;
import com.intuit.demo.service.GreetingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.URISyntaxException;

@RestController
public class DemoController
{
    @Autowired
    GreetingService greetingService;

    @GetMapping(value = "/greeting")
    public String healthCheck () throws IOException, URISyntaxException
    {
        return greetingService.hello();
    }

    @PostMapping(value = "/employee")
    public Long addEmployee (@RequestBody final Employee employee)
    {
        return greetingService.addEmployee(employee);
    }

    @GetMapping(value = "/employee/{employeeId}")
    public Employee getEmployee (@PathVariable final Long employeeId)
    {
        return greetingService.getEmployee(employeeId);
    }
}
