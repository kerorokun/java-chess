package btn.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import btn.chess.ChessColor;
import org.junit.Test;

import btn.utils.Vector2i;
import btn.chess.King;
import btn.chess.Board;

public class KingTests {

	/*
	 * Test the constructor to make sure it properly assigns the color
	 */
	@Test
	public void testConstructor1() {
		King king = new King(ChessColor.WHITE);
		assertEquals(king.getColor(), ChessColor.WHITE);
	}

	/*
	 * Test whether moving no spaces is considered valid. Should return false
	 */
	@Test
	public void testIsMoveValidZero1() {
		Board board = new EmptyBoard();
		King whiteKing = new King(ChessColor.WHITE);
		board.addPiece(whiteKing, new Vector2i(0, 0));

		assertFalse(whiteKing.isMoveValid(board, new Vector2i(0, 0)));
	}

	@Test
	public void testIsMoveValidZero2() {
		Board board = new EmptyBoard();
		King whiteKing = new King(ChessColor.WHITE);
		board.addPiece(whiteKing, new Vector2i(5, 6));

		assertFalse(whiteKing.isMoveValid(board, new Vector2i(5, 6)));
	}

	/*
	 * Tests whether moving to a space that is already occupied by an ally piece is valid.
	 * Should return false
	 */
	@Test
	public void testIsMoveValidObstacle() {
		Board board = new EmptyBoard();
		King whiteKing1 = new King(ChessColor.WHITE);
		King whiteKing2 = new King(ChessColor.WHITE);
		board.addPiece(whiteKing1, new Vector2i(5, 6));
		board.addPiece(whiteKing1, new Vector2i(5, 5));

		assertFalse(whiteKing1.isMoveValid(board, new Vector2i(5, 5)));
	}

	/*
	 * Tests whether moving to a space that is already occupied by an enemy piece is valid.
	 * Should return true.
	 */
	@Test
	public void testIsMoveValidCapturable() {
		Board board = new EmptyBoard();
		King whiteKing = new King(ChessColor.WHITE);
		King blackKing = new King(ChessColor.BLACK);
		board.addPiece(whiteKing, new Vector2i(5, 6));
		board.addPiece(blackKing, new Vector2i(5, 5));

		assertTrue(whiteKing.isMoveValid(board, new Vector2i(5, 5)));
	}

	/*
	 * Tests whether moving to a space outside the board is valid.
	 * Should return false
	 */
	@Test
	public void testIsMoveValidOutOfBounds() {
		Board board = new EmptyBoard();
		King whiteKing = new King(ChessColor.WHITE);
		board.addPiece(whiteKing, new Vector2i(7, 7));

		assertFalse(whiteKing.isMoveValid(board, new Vector2i(8, 8)));
	}

	@Test
	public void testIsMoveValidUp() {
		Board board = new EmptyBoard();
		King whiteKing = new King(ChessColor.WHITE);
		board.addPiece(whiteKing, new Vector2i(5, 5));

		assertTrue(whiteKing.isMoveValid(board, new Vector2i(5, 6)));
	}

	@Test
	public void testIsMoveValidDown() {
		Board board = new EmptyBoard();
		King whiteKing = new King(ChessColor.WHITE);
		board.addPiece(whiteKing, new Vector2i(3, 3));

		assertTrue(whiteKing.isMoveValid(board, new Vector2i(3, 2)));
	}

	@Test
	public void testIsMoveValidLeft() {
		Board board = new EmptyBoard();
		King whiteKing = new King(ChessColor.WHITE);
		board.addPiece(whiteKing, new Vector2i(1, 1));

		assertTrue(whiteKing.isMoveValid(board, new Vector2i(0, 1)));
	}

	@Test
	public void testIsMoveValidRight() {
		Board board = new EmptyBoard();
		King whiteKing = new King(ChessColor.WHITE);
		board.addPiece(whiteKing, new Vector2i(2, 3));

		assertTrue(whiteKing.isMoveValid(board, new Vector2i(2, 4)));
	}

	@Test
	public void testIsMoveValidUpRight() {
		Board board = new EmptyBoard();
		King whiteKing = new King(ChessColor.WHITE);
		board.addPiece(whiteKing, new Vector2i(5, 5));

		assertTrue(whiteKing.isMoveValid(board, new Vector2i(6, 6)));
	}

	@Test
	public void testIsMoveValidUpLeft() {
		Board board = new EmptyBoard();
		King whiteKing = new King(ChessColor.WHITE);
		board.addPiece(whiteKing, new Vector2i(5, 5));

		assertTrue(whiteKing.isMoveValid(board, new Vector2i(4, 6)));
	}

	@Test
	public void testIsMoveValidDownLeft() {
		Board board = new EmptyBoard();
		King whiteKing = new King(ChessColor.WHITE);
		board.addPiece(whiteKing, new Vector2i(2, 2));

		assertTrue(whiteKing.isMoveValid(board, new Vector2i(1, 1)));
	}

	@Test
	public void testIsMoveValidDownRight() {
		Board board = new EmptyBoard();
		King whiteKing = new King(ChessColor.WHITE);
		board.addPiece(whiteKing, new Vector2i(5, 5));

		assertTrue(whiteKing.isMoveValid(board, new Vector2i(6, 4)));
	}

	@Test
	public void testGetValidNextPositions1() {
		// TODO
	}
}
