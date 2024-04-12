package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import tdd.Game;

class GameTest {

	Game game;
	
	@BeforeEach
	public void init() {
		game=new Game();
	}
	
	
	@Test
	public void testAucuneQuille() {
		for(int i = 0; i < 20; i++)
		      game.roll(0);
		assertEquals(0, game.score());
	}
	
}
