package btn.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import btn.chess.ChessColor;
import org.junit.Test;

import btn.utils.Vector2i;
import btn.chess.Queen;
import btn.chess.Board;

public class QueenTests {

	@Test
	public void testConstructor1() {
		Queen queen = new Queen(ChessColor.WHITE);
		assertEquals(queen.getColor(), ChessColor.WHITE);
	}

	@Test
	public void testIsMoveValidZero() {
		Board board = new EmptyBoard();
		Queen queen = new Queen(ChessColor.WHITE);
		board.addPiece(queen, new Vector2i(0, 0));

		assertFalse(queen.isMoveValid(board, new Vector2i(0, 0)));
	}

	@Test
	public void testIsMoveValidFalse1() {
		Board board = new EmptyBoard();
		Queen queen = new Queen(ChessColor.WHITE);
		board.addPiece(queen, new Vector2i(0, 0));

		assertFalse(queen.isMoveValid(board, new Vector2i(2, 3)));
	}
	
	@Test
	public void testIsMoveValidFalse2() {
		Board board = new EmptyBoard();
		Queen queen = new Queen(ChessColor.WHITE);
		board.addPiece(queen, new Vector2i(5, 5));

		assertFalse(queen.isMoveValid(board, new Vector2i(1, 3)));
	}

	@Test
	public void testIsMoveValidObstacle1() {
		Board board = new EmptyBoard();
		Queen queen1 = new Queen(ChessColor.WHITE);
		Queen queen2 = new Queen(ChessColor.WHITE);
		board.addPiece(queen1, new Vector2i(5, 5));
		board.addPiece(queen2, new Vector2i(4, 5));

		assertFalse(queen1.isMoveValid(board, new Vector2i(4, 5)));
	}

	/*
	 * Check that a Queen will not validate moving off the board.
	 */
	@Test
	public void testIsMoveValidOutOfBounds1() {
		Board board = new EmptyBoard();
		Queen queen = new Queen(ChessColor.WHITE);
		board.addPiece(queen, new Vector2i(5, 5));

		assertFalse(queen.isMoveValid(board, new Vector2i(10, 5)));
	}

	/*
	 * Check that a Queen will validate capturing a piece below it.
	 */
	@Test
	public void testIsMoveValidCapturable1() {
		Board board = new EmptyBoard();
		Queen queen1 = new Queen(ChessColor.WHITE);
		Queen queen2 = new Queen(ChessColor.BLACK);
		board.addPiece(queen1, new Vector2i(5, 5));
		board.addPiece(queen2, new Vector2i(4, 5));

		assertTrue(queen1.isMoveValid(board, new Vector2i(4, 5)));
	}

	@Test
	public void testIsMoveValidUp1() {
		Board board = new EmptyBoard();
		Queen queen = new Queen(ChessColor.WHITE);
		board.addPiece(queen, new Vector2i(0, 0));

		assertTrue(queen.isMoveValid(board, new Vector2i(0, 5)));
	}

	@Test
	public void testIsMoveValidDown1() {
		Board board = new EmptyBoard();
		Queen queen = new Queen(ChessColor.WHITE);
		board.addPiece(queen, new Vector2i(0, 5));

		assertTrue(queen.isMoveValid(board, new Vector2i(0, 0)));
	}

	@Test
	public void testIsMoveValidLeft1() {
		Board board = new EmptyBoard();
		Queen queen = new Queen(ChessColor.WHITE);
		board.addPiece(queen, new Vector2i(3, 3));

		assertTrue(queen.isMoveValid(board, new Vector2i(1, 3)));		
	}

	@Test
	public void testIsMoveValidRight1() {
		Board board = new EmptyBoard();
		Queen queen = new Queen(ChessColor.WHITE);
		board.addPiece(queen, new Vector2i(0, 0));

		assertTrue(queen.isMoveValid(board, new Vector2i(5, 0)));		
	}

	@Test
	public void testIsMoveValidUpRight1() {
		Board board = new EmptyBoard();
		Queen queen = new Queen(ChessColor.WHITE);
		board.addPiece(queen, new Vector2i(0, 0));

		assertTrue(queen.isMoveValid(board, new Vector2i(5, 5)));
	}

	@Test
	public void testIsMoveValidUpLeft1() {
		Board board = new EmptyBoard();
		Queen queen = new Queen(ChessColor.WHITE);
		board.addPiece(queen, new Vector2i(5, 0));

		assertTrue(queen.isMoveValid(board, new Vector2i(0, 5)));
	}

	@Test
	public void testIsMoveValidDownRight1() {
		Board board = new EmptyBoard();
		Queen queen = new Queen(ChessColor.WHITE);
		board.addPiece(queen, new Vector2i(1, 1));

		assertTrue(queen.isMoveValid(board, new Vector2i(2, 0)));
	}

	@Test
	public void testIsMoveValidDownLeft1() {
		Board board = new EmptyBoard();
		Queen queen = new Queen(ChessColor.WHITE);
		board.addPiece(queen, new Vector2i(1, 1));

		assertTrue(queen.isMoveValid(board, new Vector2i(0, 0)));
	}
}
