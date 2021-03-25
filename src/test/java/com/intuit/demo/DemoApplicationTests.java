package com.intuit.demo;

import com.intuit.demo.model.Player;
import com.intuit.demo.utils.PlayersUtil;
import com.opencsv.exceptions.CsvValidationException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.List;

import static org.springframework.test.util.AssertionErrors.assertEquals;

@SpringBootTest
class DemoApplicationTests {

	@Autowired PlayersUtil playersUtil;

	@Test
	void contextLoads() {
	}

	@Test
	public void testGetAllPlayers()
	{
		List<Player> allPlayers = playersUtil.getAllPlayers();
		assertEquals("PlayerId found", "aardsda01", allPlayers.get(0).getPlayerID());
	}

	@Test
	public void testGetPlayerForId()
	{
		Player playerFound = playersUtil.getPlayerForPlayerId("aardsda01");
		assertEquals("Player found", "David Allan", playerFound.getNameGiven());
	}
}
