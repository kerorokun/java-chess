package btn.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import btn.chess.ChessColor;
import org.junit.Test;

import btn.utils.Vector2i;
import btn.chess.Pawn;
import btn.chess.Board;

public class PawnTests {

	private final int WHITE_DIR = 1;
	private final int BLACK_DIR = -1;

	@Test
	public void testConstructor1() {
		Pawn pawn = new Pawn(ChessColor.WHITE, WHITE_DIR);
		assertEquals(pawn.getColor(), ChessColor.WHITE);
		assertEquals(pawn.getForwardDirection(), WHITE_DIR);
		assertTrue(pawn.getFirstMove());
	}

	@Test
	public void testConstructor2() {
		Pawn pawn = new Pawn(ChessColor.BLACK, BLACK_DIR);
		assertEquals(pawn.getColor(), ChessColor.BLACK);
		assertEquals(pawn.getForwardDirection(), BLACK_DIR);
		assertTrue(pawn.getFirstMove());
	}


	/*
	 * Check that a Pawn will change its firstmove state when it's first notified that it moved.
	 */
	@Test
	public void testNotifyMove() {
		Board board = new EmptyBoard();
		Pawn pawn = new Pawn(ChessColor.WHITE, WHITE_DIR);
		board.addPiece(pawn, new Vector2i(0, 1));

		pawn.notifyMove(true);
		assertFalse(pawn.getFirstMove());
	}

	/*
	 * Check that a Pawn will not validate no movement.
	 */
	@Test
	public void testIsMoveValidZero() {
		Board board = new EmptyBoard();
		Pawn pawn = new Pawn(ChessColor.WHITE, WHITE_DIR);
		board.addPiece(pawn, new Vector2i(0, 0));

		assertFalse(pawn.isMoveValid(board, new Vector2i(0, 0)));
	}

	/*
	 * Check that a Pawn will not validate an invalid movement.
	 */
	@Test
	public void testIsMoveValidFalse1() {
		Board board = new EmptyBoard();
		Pawn pawn = new Pawn(ChessColor.WHITE, WHITE_DIR);
		board.addPiece(pawn, new Vector2i(0, 0));

		assertFalse(pawn.isMoveValid(board, new Vector2i(2, 3)));
	}
	
	@Test
	public void testIsMoveValidFalse2() {
		Board board = new EmptyBoard();
		Pawn pawn = new Pawn(ChessColor.WHITE, WHITE_DIR);
		board.addPiece(pawn, new Vector2i(5, 5));

		assertFalse(pawn.isMoveValid(board, new Vector2i(1, 3)));
	}

	/*
	 * Check that a Pawn will not validate moving to a position occupied by an ally piece.
	 */
	@Test
	public void testIsMoveValidObstacle1() {
		Board board = new EmptyBoard();
		Pawn pawn1 = new Pawn(ChessColor.WHITE, WHITE_DIR);
		Pawn pawn2 = new Pawn(ChessColor.WHITE, WHITE_DIR);
		board.addPiece(pawn1, new Vector2i(5, 5));
		board.addPiece(pawn2, new Vector2i(4, 5));

		assertFalse(pawn1.isMoveValid(board, new Vector2i(4, 5)));
	}
	/*
	 * Check that a Pawn will not validate moving to a forward position that is occupied.
	 */
	@Test
	public void testIsMoveValidObstacle2() {
		Board board = new EmptyBoard();
		Pawn pawn1 = new Pawn(ChessColor.WHITE, WHITE_DIR);
		Pawn pawn2 = new Pawn(ChessColor.BLACK, BLACK_DIR);
		board.addPiece(pawn1, new Vector2i(5, 5));
		board.addPiece(pawn2, new Vector2i(4, 5));

		assertFalse(pawn1.isMoveValid(board, new Vector2i(4, 5)));
	}

	/*
	 * Check that a Pawn will not validate moving to a  position that is not on the board.
	 */
	@Test
	public void testIsMoveValidOutOfBounds1() {
		Board board = new EmptyBoard();
		Pawn pawn = new Pawn(ChessColor.WHITE, WHITE_DIR);
		board.addPiece(pawn, new Vector2i(5, 5));

		assertFalse(pawn.isMoveValid(board, new Vector2i(10, 5)));
	}

	/*
	 * Check out of bounds movmenet
	 */
	@Test
	public void testIsMoveValidOutOfBounds2() {
		Board board = new EmptyBoard();
		Pawn pawn = new Pawn(ChessColor.WHITE, WHITE_DIR);
		board.addPiece(pawn, new Vector2i(7, 0));

		assertFalse(pawn.isMoveValid(board, new Vector2i(8, 0)));
	}

	/*
	 * Check that a Pawn can move 2 spaces forward on its first move.
	 */
	@Test
	public void testIsMoveValidDoubleForward1() {
		Board board = new EmptyBoard();
		Pawn pawn = new Pawn(ChessColor.WHITE, WHITE_DIR);
		board.addPiece(pawn, new Vector2i(0, 1));

		assertTrue(pawn.isMoveValid(board, new Vector2i(0, 3)));
	}


	/*
	 * Should not be able to move double forward twice in a row
	 */
	@Test
	public void testIsMoveValidDoubleForward2() {
		Board board = new EmptyBoard();
		Pawn pawn = new Pawn(ChessColor.WHITE, WHITE_DIR);
		board.addPiece(pawn, new Vector2i(0, 1));

		assertTrue(pawn.isMoveValid(board, new Vector2i(0, 3)));
		assertTrue(pawn.getFirstMove());
		pawn.notifyMove(true);
		assertFalse(pawn.isMoveValid(board, new Vector2i(0, 3)));
		assertFalse(pawn.getFirstMove());
	}

	/*
	 * Check when moving forward is moving up the board
	 */
	@Test
	public void testIsMoveValidForward1() {
		Board board = new EmptyBoard();
		Pawn pawn = new Pawn(ChessColor.WHITE, WHITE_DIR);
		board.addPiece(pawn, new Vector2i(0, 0));

		assertTrue(pawn.isMoveValid(board, new Vector2i(0, 1)));
	}

	/*
	 * Check when moving forward is moving down the board
	 */
	@Test
	public void testIsMoveValidForward2() {
		Board board = new EmptyBoard();
		Pawn pawn = new Pawn(ChessColor.BLACK, BLACK_DIR);
		board.addPiece(pawn, new Vector2i(0, 5));

		assertTrue(pawn.isMoveValid(board, new Vector2i(0, 4)));
	}

	/*
	 * Check when moving forward is moving up the board and piece in the way
	 */
	@Test
	public void testIsMoveValidForwardObstacle1() {
		Board board = new EmptyBoard();
		Pawn pawn1 = new Pawn(ChessColor.BLACK, BLACK_DIR);
		Pawn pawn2 = new Pawn(ChessColor.WHITE, WHITE_DIR);
		board.addPiece(pawn2, new Vector2i(0, 4));
		board.addPiece(pawn1, new Vector2i(0, 5));

		assertFalse(pawn2.isMoveValid(board, new Vector2i(0, 5)));
	}

	/*
	 * Check when moving forward is moving down the board and piece in the way
	 */
	@Test
	public void testIsMoveValidForwardObstacle2() {
		Board board = new EmptyBoard();
		Pawn pawn1 = new Pawn(ChessColor.BLACK, BLACK_DIR);
		Pawn pawn2 = new Pawn(ChessColor.WHITE, WHITE_DIR);
		board.addPiece(pawn1, new Vector2i(0, 5));
		board.addPiece(pawn2, new Vector2i(0, 4));

		assertFalse(pawn1.isMoveValid(board, new Vector2i(0, 4)));
	}

	/*
	 * Check diagonal left when the enemy piece is diagonally left
	 */
	@Test
	public void testIsMoveValidDiagonalLeftValid1() {
		Board board = new EmptyBoard();
		Pawn pawn1 = new Pawn(ChessColor.WHITE, WHITE_DIR);
		Pawn pawn2 = new Pawn(ChessColor.BLACK, BLACK_DIR);
		board.addPiece(pawn1, new Vector2i(2, 5));
		board.addPiece(pawn2, new Vector2i(1, 6));

		assertTrue(pawn1.isMoveValid(board, new Vector2i(1, 6)));
	}

	/*
	 * Check diagonal left when an ally piece is diagonally left
	 */
	@Test
	public void testIsMoveValidDiagonalLeftInvalid1() {
		Board board = new EmptyBoard();
		Pawn pawn1 = new Pawn(ChessColor.WHITE, WHITE_DIR);
		Pawn pawn2 = new Pawn(ChessColor.WHITE, BLACK_DIR);
		board.addPiece(pawn1, new Vector2i(2, 5));
		board.addPiece(pawn2, new Vector2i(1, 6));

		assertFalse(pawn1.isMoveValid(board, new Vector2i(1, 6)));
	}

	/*
	 * Check diagonal right when the enemy piece is diagonally right
	 */
	@Test
	public void testIsMoveValidDiagonalRightValid1() {
		Board board = new EmptyBoard();
		Pawn pawn1 = new Pawn(ChessColor.WHITE, WHITE_DIR);
		Pawn pawn2 = new Pawn(ChessColor.BLACK, BLACK_DIR);
		board.addPiece(pawn1, new Vector2i(2, 5));
		board.addPiece(pawn2, new Vector2i(3, 6));

		assertTrue(pawn1.isMoveValid(board, new Vector2i(3, 6)));
	}

	/*
	 * Check diagonal right when an ally piece is diagonally right
	 */
	@Test
	public void testIsMoveValidDiagonalRightInvalid1() {
		Board board = new EmptyBoard();
		Pawn pawn1 = new Pawn(ChessColor.WHITE, WHITE_DIR);
		Pawn pawn2 = new Pawn(ChessColor.WHITE, BLACK_DIR);
		board.addPiece(pawn1, new Vector2i(2, 5));
		board.addPiece(pawn2, new Vector2i(3, 6));

		assertFalse(pawn1.isMoveValid(board, new Vector2i(3, 6)));
	}
}
