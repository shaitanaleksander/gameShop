package com.gameshop.controller;

import com.gameshop.model.Game;
import com.gameshop.service.GameService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/game")
public class GameController {


    private final GameService gameService;

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @GetMapping("/{id}")
    public Game getGame(@PathVariable int id){
        return this.gameService.getById(id);
    }

    @GetMapping
    public List<Game> getAll() {
        return this.gameService.getAll();
    }

    @PostMapping
    public Game save(@RequestBody Game game) {
        return this.gameService.addGame(game);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        this.gameService.remove(id);
    }

}
