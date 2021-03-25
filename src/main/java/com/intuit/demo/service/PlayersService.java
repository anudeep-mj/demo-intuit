package com.intuit.demo.service;

import com.google.gson.Gson;
import com.intuit.demo.model.Player;
import com.intuit.demo.utils.PlayersUtil;
import com.intuit.demo.utils.TraceInfo;
import com.opencsv.exceptions.CsvValidationException;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlayersService
{
    @Autowired
    PlayersUtil playersUtil;

    public List<Player> getPlayers ()
    {
        return playersUtil.getAllPlayers();
    }

    public Player getPlayerForId (String playerId)
    {
        return playersUtil.getPlayerForPlayerId(playerId);
    }
}
