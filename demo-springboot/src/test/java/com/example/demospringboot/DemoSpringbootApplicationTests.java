package com.example.demospringboot;

import com.example.demospringboot.dao.JMTestMapper;
import com.example.demospringboot.model.JMTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoSpringbootApplicationTests {
    
    @Autowired
    private JMTestMapper jm;

    @Test
    public void contextLoads() {
        JMTest jmTest = jm.selectByPrimaryKey("1");
        jm.insert(new JMTest("2","3"));
        System.out.println(jmTest.getDd());
    }

}
