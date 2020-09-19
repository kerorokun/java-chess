package btn.tests;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import btn.utils.Vector2i;
import org.junit.Test;

import btn.chess.ChessColor;
import btn.chess.ChessPlayer;
import btn.chess.ChessModel;
import btn.chess.ChessState;

public class ChessModelTests {

	@Test
	public void testChessModelConstructor() {
		ChessModel model = new ChessModel();
		assertEquals(model.getPlayer1().getColor(), ChessColor.WHITE);
		assertEquals(model.getPlayer2().getColor(), ChessColor.BLACK);
	}

	/*
	 * Test to make sure that starting a new game works.
	 */ 
	@Test
	public void testStartNewGame() {
		ChessModel model = new ChessModel();
		assertEquals(model.getPlayer1().getColor(), ChessColor.WHITE);
		assertEquals(model.getPlayer2().getColor(), ChessColor.BLACK);
		assertEquals(model.getCurrState(), ChessState.WAITING_FOR_START_POS_SELECTION);
		assertEquals(model.getCurrPlayersColor(), ChessColor.WHITE);
		assertNotEquals(model.getBoard().getWhiteKing(), null);
		assertNotEquals(model.getBoard().getBlackKing(), null);
	}
	

	/*
	 * Test to make sure incrementing player score works for the WHITE color player
	 */
	@Test
	public void testIncrementPlayerScoreWhite() {
		ChessModel model = new ChessModel();
		model.incrementPlayerScore(ChessColor.WHITE);
		assertEquals(model.getPlayer1().getScore(), 1);
		assertEquals(model.getPlayer2().getScore(), 0);
	}

	/*
	 * Test to make sure incrementing player score works for the BLACK color player
	 */
	@Test
	public void testIncrementPlayerScoreBlack() {
		ChessModel model = new ChessModel();
		model.incrementPlayerScore(ChessColor.BLACK);
		assertEquals(model.getPlayer1().getScore(), 0);
		assertEquals(model.getPlayer2().getScore(), 1);
	}

	/*
	 * Test to make sure consecutive calls to incrementing players work.
	 */
	@Test
	public void testIncrementPlayerScoreWhiteBlack() {
		ChessModel model = new ChessModel();
		model.incrementPlayerScore(ChessColor.WHITE);
		assertEquals(model.getPlayer1().getScore(), 1);
		assertEquals(model.getPlayer2().getScore(), 0);
		model.incrementPlayerScore(ChessColor.BLACK);
		assertEquals(model.getPlayer1().getScore(), 1);
		assertEquals(model.getPlayer2().getScore(), 1);
	}

	@Test
	public void testBeginRestartWhite() {
		ChessModel model = new ChessModel();
		model.beginRestart(ChessColor.WHITE);
		assertEquals(model.getCurrState(), ChessState.WAITING_FOR_RESTART_RESPONSE_BLACK);
	}

	@Test
	public void testBeginRestartBlack() {
		ChessModel model = new ChessModel();
		model.beginRestart(ChessColor.BLACK);
		assertEquals(model.getCurrState(), ChessState.WAITING_FOR_RESTART_RESPONSE_WHITE);
	}

	@Test
	public void testBeginRestartWhiteConsecutive() {
		ChessModel model = new ChessModel();
		model.beginRestart(ChessColor.BLACK);
		assertEquals(model.getCurrState(), ChessState.WAITING_FOR_RESTART_RESPONSE_WHITE);
		model.beginRestart(ChessColor.WHITE);
		assertNotEquals(model.getCurrState(), ChessState.WAITING_FOR_RESTART_RESPONSE_BLACK);
	}

	/*
	 * Test to make sure endRestart doesn't do anything if beginRestart wasn't called first.
	 */
	@Test
	public void testEndRestartPremature() {
		ChessModel model = new ChessModel();
		ChessState currState = model.getCurrState();
		model.endRestart(true);
		assertEquals(model.getCurrState(), model.getCurrState()); 
	}

	/*
	 * Test to make sure restart starts a new game if agreed upon
	 */
	@Test
	public void testEndRestartNewGame() {
		ChessModel model = new ChessModel();

		model.beginRestart(ChessColor.BLACK);
		assertEquals(model.getCurrState(), ChessState.WAITING_FOR_RESTART_RESPONSE_WHITE);

		model.endRestart(true);
		assertEquals(model.getPlayer1().getColor(), ChessColor.WHITE);
		assertEquals(model.getPlayer2().getColor(), ChessColor.BLACK);
		assertEquals(model.getCurrState(), ChessState.WAITING_FOR_START_POS_SELECTION);
		assertEquals(model.getCurrPlayersColor(), ChessColor.WHITE);
		assertNotEquals(model.getBoard().getWhiteKing(), null);
		assertNotEquals(model.getBoard().getBlackKing(), null);
	}

	/*
	 * Test to make sure endRestart doesn't start a new game if not agreed upon
	 */
	@Test
	public void testEndRestartNoNewGame() {
		ChessModel model = new ChessModel();

		model.beginRestart(ChessColor.BLACK);
		assertEquals(model.getCurrState(), ChessState.WAITING_FOR_RESTART_RESPONSE_WHITE);

		model.endRestart(false);
		assertEquals(model.getCurrState(), ChessState.WAITING_FOR_START_POS_SELECTION);
	}

	@Test
	public void testWinGameWhite() {
		ChessModel model = new ChessModel();
		model.winGame(ChessColor.WHITE);
		assertEquals(model.getPlayer1().getScore(), 1);
		assertEquals(model.getPlayer2().getScore(), 0);
		assertEquals(model.getCurrState(), ChessState.GAME_END);
	}

	@Test
	public void testWinGameBlack() {
		ChessModel model = new ChessModel();
		model.winGame(ChessColor.BLACK);
		assertEquals(model.getPlayer1().getScore(), 0);
		assertEquals(model.getPlayer2().getScore(), 1);
		assertEquals(model.getCurrState(), ChessState.GAME_END);
	}

	@Test
	public void testForfeitWhite() {
		ChessModel model = new ChessModel();
		model.forfeit(ChessColor.WHITE);
		assertEquals(model.getPlayer1().getScore(), 0);
		assertEquals(model.getPlayer2().getScore(), 1);
		assertEquals(model.getCurrState(), ChessState.GAME_END);
	}

	@Test
	public void testForfeitBlack() {
		ChessModel model = new ChessModel();
		model.forfeit(ChessColor.BLACK);
		assertEquals(model.getPlayer1().getScore(), 1);
		assertEquals(model.getPlayer2().getScore(), 0);
		assertEquals(model.getCurrState(), ChessState.GAME_END);
	}

	@Test
	public void testIsPlayerInStalemateWhiteFalse() {
		ChessModel model = new ChessModel();
		assertFalse(model.isPlayerInStalemate(ChessColor.WHITE));
	}

	@Test
	public void testIsPlayerInStalemateBlackFalse() {
		ChessModel model = new ChessModel();
		assertFalse(model.isPlayerInStalemate(ChessColor.BLACK));
	}

	@Test
	public void testIsPlayerInCheckWhiteFalse() {
		ChessModel model = new ChessModel();
		assertFalse(model.isPlayerInCheck(ChessColor.WHITE));
	}
	
	@Test
	public void testIsPlayerInCheckBlackFalse() {
		ChessModel model = new ChessModel();
		assertFalse(model.isPlayerInCheck(ChessColor.BLACK));
	}

	@Test
	public void testIsPlayerInCheckmateWhiteFalse() {
		ChessModel model = new ChessModel();
		assertFalse(model.isPlayerInCheckmate(ChessColor.WHITE));
	}
	
	@Test
	public void testIsPlayerInCheckmateBlackFalse() {
		ChessModel model = new ChessModel();
		assertFalse(model.isPlayerInCheckmate(ChessColor.BLACK));
	}

	@Test
	public void testUndoMoveNoMoves() {
		ChessModel model = new ChessModel();
		assertFalse(model.undoMove());
		assertEquals(model.getCurrPlayersColor(), ChessColor.WHITE);
		assertEquals(model.getCurrState(), ChessState.WAITING_FOR_START_POS_SELECTION);
	}

	@Test
	public void testUndoMovesOneMove() {
		ChessModel model = new ChessModel();

		model.attemptSpaceSelect(new Vector2i(0, 1));
		model.attemptMove(new Vector2i(0, 2));
		assertTrue(model.undoMove());
		assertEquals(model.getCurrPlayersColor(), ChessColor.WHITE);
		assertEquals(model.getCurrState(), ChessState.WAITING_FOR_START_POS_SELECTION);
		assertEquals(model.getBoard().getPieceAt(new Vector2i(0, 2)), null);
	}

	@Test
	public void testAttemptMoveInvalid() {
		ChessModel model = new ChessModel();
		
		assertTrue(model.attemptSpaceSelect(new Vector2i(0, 1)));
		assertFalse(model.attemptMove(new Vector2i(0, 1)));
	}

	@Test
	public void testAttemptSpaceSelectOutOfBounds() {
		ChessModel model = new ChessModel();

		assertFalse(model.attemptSpaceSelect(new Vector2i(-1, 0)));
	}
}
