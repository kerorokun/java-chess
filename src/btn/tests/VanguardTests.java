package btn.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import btn.chess.ChessColor;
import org.junit.Test;

import btn.utils.Vector2i;
import btn.chess.Vanguard;
import btn.chess.Board;

public class VanguardTests {

	@Test
	public void testConstructor1() {
		Vanguard vanguard = new Vanguard(ChessColor.WHITE);
		assertEquals(vanguard.getColor(), ChessColor.WHITE);
	}

	/*
	 * Test what the vanguard will do when asked to move nowhere.
	 */
	@Test
	public void testIsMoveValidZero1() {
		Board board = new EmptyBoard();
		Vanguard whiteVanguard = new Vanguard(ChessColor.WHITE);
		board.addPiece(whiteVanguard, new Vector2i(0, 0));

		assertFalse(whiteVanguard.isMoveValid(board, new Vector2i(0, 0)));
	}

	/*
	 * Test what the vanguard will do when asked to move nowhere.
	 */
	@Test
	public void testIsMoveValidZero2() {
		Board board = new EmptyBoard();
		Vanguard whiteVanguard = new Vanguard(ChessColor.WHITE);
		board.addPiece(whiteVanguard, new Vector2i(5, 5));

		assertFalse(whiteVanguard.isMoveValid(board, new Vector2i(5, 5)));
	}
	
	@Test
	public void testIsMoveValidOutOfBounds1() {
		Board board = new EmptyBoard();
		Vanguard whiteVanguard = new Vanguard(ChessColor.WHITE);
		board.addPiece(whiteVanguard, new Vector2i(0, 0));

		assertFalse(whiteVanguard.isMoveValid(board, new Vector2i(-1, -1)));
	}

	/*
	 * Test to make sure that the Vanguard will move to a valid position that is occupied by an enemy piece.
	 */
	@Test
	public void testIsMoveValidCapturable1() {
		Board board = new EmptyBoard();
		Vanguard blackVanguard = new Vanguard(ChessColor.BLACK);
		Vanguard whiteVanguard = new Vanguard(ChessColor.WHITE);
		board.addPiece(whiteVanguard, new Vector2i(0, 0));
		board.addPiece(blackVanguard, new Vector2i(2, 2));

		assertTrue(whiteVanguard.isMoveValid(board, new Vector2i(2, 2)));
	}
	
	@Test
	public void testIsMoveValidFalse1() {
		Board board = new EmptyBoard();
		Vanguard whiteVanguard = new Vanguard(ChessColor.WHITE);
		board.addPiece(whiteVanguard, new Vector2i(0, 0));

		assertFalse(whiteVanguard.isMoveValid(board, new Vector2i(0, 1)));
	}

    /*
	 * Test to make sure that the Vanguard will not move to a valid position that is occupied by an ally piece.
	 */	
	@Test
	public void testIsMoveValidObstacle1() {
		Board board = new EmptyBoard();
		Vanguard whiteVanguard = new Vanguard(ChessColor.WHITE);
		Vanguard whiteVanguard2 = new Vanguard(ChessColor.WHITE);
		board.addPiece(whiteVanguard, new Vector2i(0, 0));
		board.addPiece(whiteVanguard2, new Vector2i(2, 2));

		assertFalse(whiteVanguard.isMoveValid(board, new Vector2i(2, 2)));
	}

	/*
	 * Test 2 right, 2 up
	 */
	@Test
	public void testIsMoveValidTrue1() {
		Board board = new EmptyBoard();
		Vanguard whiteVanguard = new Vanguard(ChessColor.WHITE);
		board.addPiece(whiteVanguard, new Vector2i(0, 0));

		assertTrue(whiteVanguard.isMoveValid(board, new Vector2i(2, 2)));
	}

	/*
	 * Test 0 right, 2 up
	 */
	@Test
	public void testIsMoveValidTrue2() {
		Board board = new EmptyBoard();
		Vanguard whiteVanguard = new Vanguard(ChessColor.WHITE);
		board.addPiece(whiteVanguard, new Vector2i(0, 0));

		assertTrue(whiteVanguard.isMoveValid(board, new Vector2i(0, 2)));
	}

	/*
	 * Test 2 left, 2 down
	 */
	@Test
	public void testIsMoveValidTrue3() {
		Board board = new EmptyBoard();
		Vanguard whiteVanguard = new Vanguard(ChessColor.WHITE);
		board.addPiece(whiteVanguard, new Vector2i(2, 2));

		assertTrue(whiteVanguard.isMoveValid(board, new Vector2i(0, 0)));
	}

	/*
	 * Test 2 right, 2 down
	 */
	@Test
	public void testIsMoveValidTrue4() {
		Board board = new EmptyBoard();
		Vanguard whiteVanguard = new Vanguard(ChessColor.WHITE);
		board.addPiece(whiteVanguard, new Vector2i(2, 2));

		assertTrue(whiteVanguard.isMoveValid(board, new Vector2i(4, 0)));
	}

	/*
	 * Test 0 right, 2 down
	 */
	@Test
	public void testIsMoveValidTrue5() {
		Board board = new EmptyBoard();
		Vanguard whiteVanguard = new Vanguard(ChessColor.WHITE);
		board.addPiece(whiteVanguard, new Vector2i(2, 2));

		assertTrue(whiteVanguard.isMoveValid(board, new Vector2i(2, 0)));
	}

	/*
	 * Test 2 left, 2 down
	 */
	@Test
	public void testIsMoveValidTrue6() {
		Board board = new EmptyBoard();
		Vanguard whiteVanguard = new Vanguard(ChessColor.WHITE);
		board.addPiece(whiteVanguard, new Vector2i(2, 2));

		assertTrue(whiteVanguard.isMoveValid(board, new Vector2i(0, 0)));
	}

	/*
	 * Test 2 left, 2 up
	 */
	@Test
	public void testIsMoveValidTrue7() {
		Board board = new EmptyBoard();
		Vanguard whiteVanguard = new Vanguard(ChessColor.WHITE);
		board.addPiece(whiteVanguard, new Vector2i(2, 2));

		assertTrue(whiteVanguard.isMoveValid(board, new Vector2i(0, 4)));
	}

	/*
	 * Test 2 left, 0 up
	 */
	@Test
	public void testIsMoveValidTrue8() {
		Board board = new EmptyBoard();
		Vanguard whiteVanguard = new Vanguard(ChessColor.WHITE);
		board.addPiece(whiteVanguard, new Vector2i(2, 2));

		assertTrue(whiteVanguard.isMoveValid(board, new Vector2i(0, 2)));
	}
}
