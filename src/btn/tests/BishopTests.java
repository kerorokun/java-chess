package btn.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import btn.chess.ChessColor;
import org.junit.Test;

import btn.utils.Vector2i;
import btn.chess.Bishop;
import btn.chess.Board;

public class BishopTests {

	@Test
	public void testConstructor1() {
		Bishop bishop = new Bishop(ChessColor.WHITE);
		assertEquals(bishop.getColor(), ChessColor.WHITE);
	}

	/*
	 * Test what the bishop will do when asked to move nowhere.
	 */
	@Test
	public void testIsMoveValidZero1() {
		Board board = new EmptyBoard();
		Bishop whiteBishop = new Bishop(ChessColor.WHITE);
		board.addPiece(whiteBishop, new Vector2i(0, 0));

		assertFalse(whiteBishop.isMoveValid(board, new Vector2i(0, 0)));
	}

	/*
	 * Test what the bishop will do when asked to move nowhere.
	 */
	@Test
	public void testIsMoveValidZero2() {
		Board board = new EmptyBoard();
		Bishop whiteBishop = new Bishop(ChessColor.WHITE);
		board.addPiece(whiteBishop, new Vector2i(5, 5));

		assertFalse(whiteBishop.isMoveValid(board, new Vector2i(5, 5)));
	}
	
	@Test
	public void testIsMoveValidOutOfBounds1() {
		Board board = new EmptyBoard();
		Bishop whiteBishop = new Bishop(ChessColor.WHITE);
		board.addPiece(whiteBishop, new Vector2i(0, 0));

		assertFalse(whiteBishop.isMoveValid(board, new Vector2i(-1, -1)));
	}
	@Test
	public void testIsMoveValidFalse1() {
		Board board = new EmptyBoard();
		Bishop whiteBishop = new Bishop(ChessColor.WHITE);
		board.addPiece(whiteBishop, new Vector2i(0, 0));

		assertFalse(whiteBishop.isMoveValid(board, new Vector2i(0, 1)));
	}

    /*
	 * Test to make sure that the Bishop will not move to a valid position that is occupied by an ally piece.
	 */	
	@Test
	public void testIsMoveValidFalse2() {
		Board board = new EmptyBoard();
		Bishop whiteBishop = new Bishop(ChessColor.WHITE);
		Bishop whiteBishop2 = new Bishop(ChessColor.WHITE);
		board.addPiece(whiteBishop, new Vector2i(0, 0));
		board.addPiece(whiteBishop2, new Vector2i(5, 5));

		assertFalse(whiteBishop.isMoveValid(board, new Vector2i(5, 5)));
	}
	
	@Test
	public void testIsMoveValidTrue1() {
		Board board = new EmptyBoard();
		Bishop whiteBishop = new Bishop(ChessColor.WHITE);
		board.addPiece(whiteBishop, new Vector2i(0, 0));

		assertTrue(whiteBishop.isMoveValid(board, new Vector2i(3, 3)));
	}

	@Test
	public void testIsMoveValidTrue2() {
		Board board = new EmptyBoard();
		Bishop whiteBishop = new Bishop(ChessColor.WHITE);
		board.addPiece(whiteBishop, new Vector2i(1, 1));

		assertTrue(whiteBishop.isMoveValid(board, new Vector2i(0, 2)));
	}

	@Test
	public void testIsMoveValidTrue3() {
		Board board = new EmptyBoard();
		Bishop whiteBishop = new Bishop(ChessColor.WHITE);
		board.addPiece(whiteBishop, new Vector2i(3, 3));

		assertTrue(whiteBishop.isMoveValid(board, new Vector2i(0, 0)));
	}

	@Test
	public void testIsMoveValidTrue4() {
		Board board = new EmptyBoard();
		Bishop whiteBishop = new Bishop(ChessColor.WHITE);
		board.addPiece(whiteBishop, new Vector2i(2, 2));

		assertTrue(whiteBishop.isMoveValid(board, new Vector2i(4, 0)));
	}

	/*
	 * Test to make sure that the Bishop will move to a valid position that is occupied by an enemy piece.
	 */
	@Test
	public void testIsMoveValidTrue5() {
		Board board = new EmptyBoard();
		Bishop blackBishop = new Bishop(ChessColor.BLACK);
		Bishop whiteBishop = new Bishop(ChessColor.WHITE);
		board.addPiece(whiteBishop, new Vector2i(0, 0));
		board.addPiece(blackBishop, new Vector2i(5, 5));

		assertTrue(whiteBishop.isMoveValid(board, new Vector2i(5, 5)));
	}
}
