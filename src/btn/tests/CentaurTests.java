package btn.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import btn.chess.ChessColor;
import org.junit.Test;

import btn.utils.Vector2i;
import btn.chess.Centaur;
import btn.chess.Board;

public class CentaurTests {

	/*
	 * Test the constructor to make sure it properly assigns the color
	 */
	@Test
	public void testConstructor1() {
		Centaur centaur = new Centaur(ChessColor.WHITE);
		assertEquals(centaur.getColor(), ChessColor.WHITE);
	}

	/*
	 * Test whether moving no spaces is considered valid. Should return false
	 */
	@Test
	public void testIsMoveValidZero1() {
		Board board = new EmptyBoard();
		Centaur whiteCentaur = new Centaur(ChessColor.WHITE);
		board.addPiece(whiteCentaur, new Vector2i(0, 0));

		assertFalse(whiteCentaur.isMoveValid(board, new Vector2i(0, 0)));
	}

	/*
	 * Test if a centaur can move to a position 2 right, 2 up that has an ally piece.
	 */
	@Test
	public void testIsMoveValidObstacle1() {
		Board board = new EmptyBoard();
		Centaur centaur1 = new Centaur(ChessColor.WHITE);
		Centaur centaur2 = new Centaur(ChessColor.WHITE);
		board.addPiece(centaur1, new Vector2i(0, 0));
		board.addPiece(centaur2, new Vector2i(2, 2));

		assertFalse(centaur1.isMoveValid(board, new Vector2i(2, 2)));
	}

	/*
	 * Test if a centaur can move to a position 2 right, 2 up that is occupied by an enemy
	 */
	@Test
	public void testIsMoveValidCapturable1() {
		Board board = new EmptyBoard();
		Centaur centaur1 = new Centaur(ChessColor.WHITE);
		Centaur centaur2 = new Centaur(ChessColor.BLACK);
		board.addPiece(centaur1, new Vector2i(0, 0));
		board.addPiece(centaur2, new Vector2i(2, 2));

		assertTrue(centaur1.isMoveValid(board, new Vector2i(2, 2)));
	}

	/*
	 * Test if a centaur can move to an empty position 2 right, 2 up
	 */
	@Test
	public void testIsMoveValidTrue1() {
		Board board = new EmptyBoard();
		Centaur centaur = new Centaur(ChessColor.WHITE);
		board.addPiece(centaur, new Vector2i(0, 0));

		assertTrue(centaur.isMoveValid(board, new Vector2i(2, 2)));
	}

	/*
	 * Test if a centaur can move to an empty position 2 left, 2 up 
	 */
	@Test
	public void testIsMoveValidTrue2() {
		Board board = new EmptyBoard();
		Centaur centaur = new Centaur(ChessColor.WHITE);
		board.addPiece(centaur, new Vector2i(2, 0));

		assertTrue(centaur.isMoveValid(board, new Vector2i(0, 2)));
	}

	/*
	 * Test if a centaur can move to an empty position 2 left, 2 down 
	 */
	@Test
	public void testIsMoveValidTrue3() {
		Board board = new EmptyBoard();
		Centaur centaur = new Centaur(ChessColor.WHITE);
		board.addPiece(centaur, new Vector2i(2, 2));

		assertTrue(centaur.isMoveValid(board, new Vector2i(0, 0)));
	}

	/*
	 * Test if a centaur can move to an empty position 2 right, 2 up
	 */
	@Test
	public void testIsMoveValidTrue4() {
		Board board = new EmptyBoard();
		Centaur centaur = new Centaur(ChessColor.WHITE);
		board.addPiece(centaur, new Vector2i(2, 2));

		assertTrue(centaur.isMoveValid(board, new Vector2i(4, 4)));
	}

}
