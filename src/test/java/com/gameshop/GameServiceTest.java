package com.gameshop;

import com.gameshop.model.Game;
import com.gameshop.repository.GameRepository;
import com.gameshop.service.GameService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
@ExtendWith(MockitoExtension.class)
public class GameServiceTest {

//    private GameRepository gameRepository = Mockito.mock(GameRepository.class);

    @Mock
    public GameRepository gameRepository;

    @Test
    public void addTest() {
        GameService gameService = new GameService(gameRepository);
        Game gta = Game.builder().id(12).name("gta").build();

        Mockito.when(gameRepository.save(any())).thenReturn(gta);

        Game game = gameService.addGame(new Game());
        Assertions.assertEquals(gta,game);
    }


    @Test
    public void getByIdTest() {
        GameService gameService = new GameService(gameRepository);
        Game gta = Game.builder().id(12).name("gta").build();
        Mockito.when(gameRepository.findById(any())).thenReturn(Optional.of(gta));
        Game game = gameService.getById(anyInt());
        Assertions.assertEquals(gta,game);
    }

    @Test
    public void getByIdNotFoundTest() {
        GameService gameService = new GameService(gameRepository);

        Mockito.when(gameRepository.findById(any())).thenReturn(Optional.empty());
        ResponseStatusException responseStatusException = Assertions.assertThrows(ResponseStatusException.class, () -> gameService.getById(anyInt()));
        Assertions.assertEquals("404 NOT_FOUND", responseStatusException.getMessage());
    }
}
