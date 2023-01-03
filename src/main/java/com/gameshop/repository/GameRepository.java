package com.gameshop.repository;

import com.gameshop.model.Game;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.relational.core.sql.In;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GameRepository extends CrudRepository<Game, Integer> {

    List<Game> findAll();

}
