package com.gameshop.integration;

import com.gameshop.controller.GameController;
import com.gameshop.model.Game;
import com.gameshop.repository.GameRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;

import static org.mockito.ArgumentMatchers.any;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith(MockitoExtension.class)
public class GameServiceTest {
    @Value(value="${local.server.port}")
    private int port;

    @Autowired
    private GameRepository gameRepository;
    @Autowired
    private TestRestTemplate restTemplate;



    @Test
    public void contextLoad() {
        Assertions.assertThat(gameRepository).isNotNull();
        Assertions.assertThat(restTemplate).isNotNull();
    }

    @Test
    public void getGameById() {
        Game doom = Game.builder().id(17).name("Doom").type("Shooter").rating(18).build();
        Game game = restTemplate.getForObject("http://localhost:" + port + "/game/"+ doom.getId(), Game.class);
        Assertions.assertThat(game).isEqualTo(doom);
    }

    @Test
    public void getGameTest() {

        Game doom2 = Game.builder().name("Doom ||").type("Shooter").rating(18).build();
        doom2 = this.gameRepository.save(doom2);

        Game game = restTemplate.getForObject("http://localhost:" + port + "/game/" +doom2.getId(), Game.class);
        Assertions.assertThat(game).isEqualTo(doom2);
        this.gameRepository.deleteById(doom2.getId());
    }
@Test
    public void getSaveTest() {
        Game doom2 = Game.builder().name("Doom ||").type("Shooter").rating(18).build();
//        Mockito.when(gameRepository.save(any())).thenReturn(doom2);
        Game game = restTemplate.postForEntity("http://localhost:" + port + "/game", doom2, Game.class).getBody();
        doom2.setId(game.getId());
        Assertions.assertThat(game).isEqualTo(doom2);


        //this.gameRepository.deleteById(doom2.getId());
    }
}
