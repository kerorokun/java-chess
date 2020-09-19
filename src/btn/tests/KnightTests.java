package btn.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import btn.chess.ChessColor;
import org.junit.Test;

import btn.utils.Vector2i;
import btn.chess.Knight;
import btn.chess.Board;

public class KnightTests {

	/*
	 * Test the constructor to make sure it properly assigns the color
	 */
	@Test
	public void testConstructor1() {
		Knight knight = new Knight(ChessColor.WHITE);
		assertEquals(knight.getColor(), ChessColor.WHITE);
	}


	/*
	 * Test whether moving no spaces is considered valid. Should return false
	 */
	@Test
	public void testIsMoveValidZero1() {
		Board board = new EmptyBoard();
		Knight whiteKnight = new Knight(ChessColor.WHITE);
		board.addPiece(whiteKnight, new Vector2i(0, 0));

		assertFalse(whiteKnight.isMoveValid(board, new Vector2i(0, 0)));
	}

	/*
	 * Test if a knight can move to a position 2 right, 1 up that has an ally piece.
	 */
	@Test
	public void testIsMoveValidObstacle1() {
		Board board = new EmptyBoard();
		Knight knight1 = new Knight(ChessColor.WHITE);
		Knight knight2 = new Knight(ChessColor.WHITE);
		board.addPiece(knight1, new Vector2i(0, 0));
		board.addPiece(knight2, new Vector2i(2, 1));

		assertFalse(knight1.isMoveValid(board, new Vector2i(2, 1)));
	}

	/*
	 * Test if a knight can move to a position 2 right, 1 up that is occupied by an enemy
	 */
	@Test
	public void testIsMoveValidCapturable1() {
		Board board = new EmptyBoard();
		Knight knight1 = new Knight(ChessColor.WHITE);
		Knight knight2 = new Knight(ChessColor.BLACK);
		board.addPiece(knight1, new Vector2i(0, 0));
		board.addPiece(knight2, new Vector2i(2, 1));

		assertTrue(knight1.isMoveValid(board, new Vector2i(2, 1)));
	}

	/*
	 * Test if a knight can move to an empty position 2 right, 1 up
	 */
	@Test
	public void testIsMoveValidTrue1() {
		Board board = new EmptyBoard();
		Knight knight = new Knight(ChessColor.WHITE);
		board.addPiece(knight, new Vector2i(0, 0));

		assertTrue(knight.isMoveValid(board, new Vector2i(2, 1)));
	}

	/*
	 * Test if a knight can move to an empty position 1 right, 2 up
	 */
	@Test
	public void testIsMoveValidTrue2() {
		Board board = new EmptyBoard();
		Knight knight = new Knight(ChessColor.WHITE);
		board.addPiece(knight, new Vector2i(0, 0));

		assertTrue(knight.isMoveValid(board, new Vector2i(1, 2)));
	}

	/*
	 * Test if a knight can move to an empty position 1 left, 2 down 
	 */
	@Test
	public void testIsMoveValidTrue3() {
		Board board = new EmptyBoard();
		Knight knight = new Knight(ChessColor.WHITE);
		board.addPiece(knight, new Vector2i(2, 1));

		assertTrue(knight.isMoveValid(board, new Vector2i(0, 0)));
	}

	/*
	 * Test if a knight can move to an empty position 2 left, 1 down 
	 */
	@Test
	public void testIsMoveValidTrue4() {
		Board board = new EmptyBoard();
		Knight knight = new Knight(ChessColor.WHITE);
		board.addPiece(knight, new Vector2i(2, 2));

		assertTrue(knight.isMoveValid(board, new Vector2i(1, 0)));
	}

	/*
	 * Test if a knight can move to an empty position 2 left, 1 up
	 */
	@Test
	public void testIsMoveValidTrue5() {
		Board board = new EmptyBoard();
		Knight knight = new Knight(ChessColor.WHITE);
		board.addPiece(knight, new Vector2i(2, 2));

		assertTrue(knight.isMoveValid(board, new Vector2i(0, 3)));
	}

	/*
	 * Test if a knight can move to an empty position 1 left, 2 up
	 */
	@Test
	public void testIsMoveValidTrue6() {
		Board board = new EmptyBoard();
		Knight knight = new Knight(ChessColor.WHITE);
		board.addPiece(knight, new Vector2i(2, 2));

		assertTrue(knight.isMoveValid(board, new Vector2i(1, 4)));
	}

	/*
	 * Test if a knight can move to an empty position 2 right, 1 up
	 */
	@Test
	public void testIsMoveValidTrue7() {
		Board board = new EmptyBoard();
		Knight knight = new Knight(ChessColor.WHITE);
		board.addPiece(knight, new Vector2i(2, 2));

		assertTrue(knight.isMoveValid(board, new Vector2i(4, 3)));
	}

	/*
	 * Test if a knight can move to an empty position 1 right, 2 up
	 */
	@Test
	public void testIsMoveValidTrue8() {
		Board board = new EmptyBoard();
		Knight knight = new Knight(ChessColor.WHITE);
		board.addPiece(knight, new Vector2i(2, 2));

		assertTrue(knight.isMoveValid(board, new Vector2i(3, 4)));
	}

	/*
	 * Test if a knight can move to an empty position 2 right, 2 up
	 */
	@Test
	public void testIsMoveValidFalse1() {
		Board board = new EmptyBoard();
		Knight knight = new Knight(ChessColor.WHITE);
		board.addPiece(knight, new Vector2i(2, 2));

		assertFalse(knight.isMoveValid(board, new Vector2i(4, 4)));
	}
}
