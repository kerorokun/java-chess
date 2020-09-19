package btn.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import btn.chess.ChessColor;
import org.junit.Test;

import btn.utils.Vector2i;
import btn.chess.Rook;
import btn.chess.Board;

public class RookTests {

	@Test
	public void testConstructor1() {
		Rook rook = new Rook(ChessColor.WHITE);
		assertEquals(rook.getColor(), ChessColor.WHITE);
	}

	@Test
	public void testIsMoveValidZero1() {
		Board board = new EmptyBoard();
		Rook whiteRook = new Rook(ChessColor.WHITE);
		board.addPiece(whiteRook, new Vector2i(0, 0));

		assertFalse(whiteRook.isMoveValid(board, new Vector2i(0, 0)));
	}

	
	@Test
	public void testIsMoveValidOutOfBounds1() {
		Board board = new EmptyBoard();
		Rook whiteRook = new Rook(ChessColor.WHITE);
		board.addPiece(whiteRook, new Vector2i(0, 0));

		assertFalse(whiteRook.isMoveValid(board, new Vector2i(0, -1)));
	}

	@Test
	public void testIsMoveValidOutOfBounds2() {
		Board board = new EmptyBoard();
		Rook whiteRook = new Rook(ChessColor.WHITE);
		board.addPiece(whiteRook, new Vector2i(0, 0));

		assertFalse(whiteRook.isMoveValid(board, new Vector2i(10, 0)));
	}

	@Test
	public void testIsMoveValidCapturable1() {
		Board board = new EmptyBoard();
		Rook whiteRook = new Rook(ChessColor.WHITE);
		Rook blackRook = new Rook(ChessColor.BLACK);
		board.addPiece(whiteRook, new Vector2i(0, 0));
		board.addPiece(blackRook, new Vector2i(0, 4));

		assertTrue(whiteRook.isMoveValid(board, new Vector2i(0, 4)));
	}

	@Test
	public void testIsMoveValidObstacle1() {
		Board board = new EmptyBoard();
		Rook rook1 = new Rook(ChessColor.WHITE);
		Rook rook2 = new Rook(ChessColor.WHITE);
		board.addPiece(rook1, new Vector2i(0, 0));
		board.addPiece(rook2, new Vector2i(0, 4));

		assertFalse(rook1.isMoveValid(board, new Vector2i(0, 4)));
	}

	@Test
	public void testIsMoveValidUp1() {
		Board board = new EmptyBoard();
		Rook rook = new Rook(ChessColor.WHITE);
		board.addPiece(rook, new Vector2i(0, 0));

		assertTrue(rook.isMoveValid(board, new Vector2i(0, 1)));
	}

	@Test
	public void testIsMoveValidUp2() {
		Board board = new EmptyBoard();
		Rook rook = new Rook(ChessColor.WHITE);
		board.addPiece(rook, new Vector2i(0, 0));

		assertTrue(rook.isMoveValid(board, new Vector2i(0, 7)));
	}

	@Test
	public void testIsMoveValidDown1() {
		Board board = new EmptyBoard();
		Rook rook = new Rook(ChessColor.WHITE);
		board.addPiece(rook, new Vector2i(0, 5));

		assertTrue(rook.isMoveValid(board, new Vector2i(0, 4)));
	}

	@Test
	public void testIsMoveValidDown2() {
		Board board = new EmptyBoard();
		Rook rook = new Rook(ChessColor.WHITE);
		board.addPiece(rook, new Vector2i(0, 3));

		assertTrue(rook.isMoveValid(board, new Vector2i(0, 0)));
	}

	@Test
	public void testIsMoveValidRight1() {
		Board board = new EmptyBoard();
		Rook rook = new Rook(ChessColor.WHITE);
		board.addPiece(rook, new Vector2i(0, 0));

		assertTrue(rook.isMoveValid(board, new Vector2i(1, 0)));
	}

	@Test
	public void testIsMoveValidLeft1() {
		Board board = new EmptyBoard();
		Rook rook = new Rook(ChessColor.WHITE);
		board.addPiece(rook, new Vector2i(1, 0));

		assertTrue(rook.isMoveValid(board, new Vector2i(0, 0)));
	}
}
