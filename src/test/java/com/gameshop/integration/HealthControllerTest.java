package com.gameshop.integration;

import com.gameshop.controller.HealthController;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith(MockitoExtension.class)
public class HealthControllerTest {

    @Value(value="${local.server.port}")
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void healthTest() throws Exception {
        Assertions.assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/health", String.class)).contains("I am alive");
    }

}
