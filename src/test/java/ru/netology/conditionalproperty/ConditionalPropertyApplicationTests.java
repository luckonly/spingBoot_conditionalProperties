package ru.netology.conditionalproperty;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Testcontainers
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ConditionalPropertyApplicationTests {

    @Autowired
    public TestRestTemplate restTemplate;

    @Container
    public static GenericContainer myappdev = new GenericContainer("myappdev").withExposedPorts(8082);

    @Container
    public static GenericContainer myappprod = new GenericContainer("myappprod").withExposedPorts(8081);

    @BeforeAll
    public static void setUp() {
        myappdev.start();
        myappprod.start();
    }

    @Test
    void contextLoads() {

        ResponseEntity<String> forEntityDev = restTemplate.getForEntity("http://localhost:" + myappdev.getMappedPort(8082) + "/profile", String.class);
        assertEquals("Initiating developer's profile", forEntityDev.getBody());

        ResponseEntity<String> forEntityProd = restTemplate.getForEntity("http://localhost:" + myappprod.getMappedPort(8081) + "/profile", String.class);
        assertEquals("Initiating production profile", forEntityProd.getBody());


    }

}
