package btn.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import btn.chess.ChessColor;
import btn.chess.ChessPlayer;

public class ChessPlayerTests {

	@Test
	public void testConstructor() {
		ChessPlayer player = new ChessPlayer(ChessColor.WHITE, "White");
		assertEquals(player.getScore(), 0);
		assertEquals(player.getColor(), ChessColor.WHITE);
	}

	@Test
	public void testIncrementScore1() {
		ChessPlayer player = new ChessPlayer(ChessColor.WHITE, "White");
		assertEquals(player.getScore(), 0);
		player.incrementScore();
		assertEquals(player.getScore(), 1);
		assertEquals(player.getColor(), ChessColor.WHITE);
	}

	@Test
	public void testIncrementScore2() {
		ChessPlayer player = new ChessPlayer(ChessColor.BLACK, "Black");
		assertEquals(player.getScore(), 0);
		player.incrementScore();
		assertEquals(player.getScore(), 1);
		player.incrementScore();
		assertEquals(player.getScore(), 2);
		assertEquals(player.getColor(), ChessColor.BLACK);
	}

	@Test
	public void testSetName() {
		ChessPlayer player = new ChessPlayer(ChessColor.BLACK, "Black");
		player.setName("Test");
		assertEquals(player.getName(), "Test");
	}
}
