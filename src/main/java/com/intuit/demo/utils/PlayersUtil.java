package com.intuit.demo.utils;

import com.google.gson.Gson;
import com.intuit.demo.model.Player;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class PlayersUtil
{
    List<Player> allPlayers;
    Map<String, Player> playerMap;
    String[] keysSplit;

    @PostConstruct
    public void init () throws IOException
    {
        String keys =
            "playerID,birthYear,birthMonth,birthDay,birthCountry,birthState,birthCity,deathYear,deathMonth,deathDay,deathCountry,deathState,deathCity,nameFirst,nameLast,nameGiven,weight,height,bats,throws,debut,finalGame,retroID,bbrefID";
        keysSplit = keys.split(",");
        allPlayers = new ArrayList<>();
        playerMap = new HashMap<>();

        try (BufferedReader br = new BufferedReader(
            new FileReader("src/main/resources/People.csv")))
        {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");

                Map<String, String> map = new HashMap<>();

                for (int i = 0; i < values.length; i++) {

                    map.put(keysSplit[i], values[i]);
                }

                String valueAsJson = new Gson().toJson(map);

                Player player = new Gson().fromJson(valueAsJson, Player.class);
                allPlayers.add(player);
                playerMap.put(player.getPlayerID(), player);
            }
        }
    }

    public List<Player> getAllPlayers ()
    {
        return allPlayers;
    }

    public Player getPlayerForPlayerId (String playerId)
    {
        return playerMap.get(playerId);
    }
}
