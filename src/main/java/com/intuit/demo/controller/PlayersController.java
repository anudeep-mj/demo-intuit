package com.intuit.demo.controller;

import com.intuit.demo.model.Player;
import com.intuit.demo.service.PlayersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PlayersController
{
    @Autowired PlayersService playersService;

    @GetMapping(value = "/api/players")
    public List<Player> getAllPlayers()
    {
        return playersService.getPlayers();
    }

    @GetMapping(value = "/api/players/{playerId}")
    public Player getEmployee (@PathVariable final String playerId)
    {
        return playersService.getPlayerForId(playerId);
    }
}
