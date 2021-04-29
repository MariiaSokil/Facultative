package com.epam;


import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserServiceAppTests {

    @LocalServerPort
    private int port;

    @Autowired
    TestRestTemplate testRestTemplate;

   /* @Test
    void findById(){
        testRestTemplate.getForEntity("http://localhost:" + port +"$/users/{id}");
    }*/
}
