package btn.tests;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import btn.utils.Vector2i;
import btn.chess.*;

public class StandardBoardTests {

	@Test
	public void testConstructor1() {
		StandardBoard board = new StandardBoard();
		assertEquals(board.getWidth(), 8);
		assertEquals(board.getHeight(), 8);
		assertEquals(board.getRemovedPieces().size(), 0);
	}

	/*
	 * Test to make sure removing a valid piece works.
	 */
	@Test
	public void testRemovePieceValid() {
		StandardBoard board = new StandardBoard();
		board.removePiece(board.getPieceAt(new Vector2i(0, 0)));
		assertEquals(board.getRemovedPieces().size(), 1);
	}

	/*
	 * Try to remove a null piece.
	 */
	@Test
	public void testRemovePieceNull() {
		StandardBoard board = new StandardBoard();
		board.removePiece(null);
		assertEquals(board.getRemovedPieces().size(), 0);
	}

	/*
	 * Try to get a null white king.
	 */
	@Test
	public void testGetWhiteKingNull() {
		StandardBoard board = new StandardBoard();
		board.removePiece(board.getPieceAt(new Vector2i(4, 0)));
		assertEquals(board.getWhiteKing(), null);
	}
	
	/*
	 * Try to get a null white king.
	 */
	@Test
	public void testGetBlackKingNull() {
		StandardBoard board = new StandardBoard();
		board.removePiece(board.getPieceAt(new Vector2i(4, 7)));
		assertEquals(board.getBlackKing(), null);
	}
	
	
	/*
	 * Try to move a piece to an invalid place.
	 */
	@Test
	public void testMovePieceToInvalid1() {
		StandardBoard board = new StandardBoard();
		assertFalse(board.movePieceTo(board.getPieceAt(new Vector2i(0, 0)),  new Vector2i(-1, -1)));
	}

	/*
	 * Helper function to get a standard board emptied of all pieces
	 */
	private StandardBoard getEmptyStandardBoard() {
		StandardBoard board = new StandardBoard();
		for (int i = 0; i < 8; i++) {
			board.removePiece(board.getPieceAt(new Vector2i(i, 0)));
			board.removePiece(board.getPieceAt(new Vector2i(i, 1)));
			board.removePiece(board.getPieceAt(new Vector2i(i, 6)));
			board.removePiece(board.getPieceAt(new Vector2i(i, 7)));
		}
		return board;
	}

	private final int WHITE_DIR = 1;
	private final int BLACK_DIR = -1;

	/*
	 * Check to make sure the board rules a stalemate properly.
	 */
	@Test
	public void testIsKingInStalemateTrue1() {
		StandardBoard board = getEmptyStandardBoard();
		King whiteKing = new King(ChessColor.WHITE);
		King blackKing = new King(ChessColor.BLACK);
		Pawn whitePawn = new Pawn(ChessColor.WHITE, WHITE_DIR);

		board.addPiece(whiteKing, new Vector2i(5, 5));
		board.addPiece(whitePawn, new Vector2i(5, 6));
		board.addPiece(blackKing, new Vector2i(5, 7));
		assertTrue(board.isKingInStalemate(board.getBlackKing()));
	}

	/*
	 * Check to make sure the board doesn't rule a stalemate when there is none.
	 */
	@Test
	public void testIsKingInStalemateFalse1() {
		StandardBoard board = getEmptyStandardBoard();
		King whiteKing = new King(ChessColor.WHITE);
		King blackKing = new King(ChessColor.BLACK);

		board.addPiece(whiteKing, new Vector2i(5, 5));
		board.addPiece(blackKing, new Vector2i(5, 7));
		assertFalse(board.isKingInStalemate(board.getWhiteKing()));
	}
	
	@Test
	public void testIsKingInStalemateFalse2() {
		StandardBoard board = getEmptyStandardBoard();
		King whiteKing = new King(ChessColor.WHITE);
		King blackKing = new King(ChessColor.BLACK);
		Pawn whitePawn = new Pawn(ChessColor.WHITE, WHITE_DIR);

		board.addPiece(whiteKing, new Vector2i(5, 5));
		board.addPiece(whitePawn, new Vector2i(5, 6));
		board.addPiece(blackKing, new Vector2i(5, 7));
		assertFalse(board.isKingInStalemate(board.getWhiteKing()));
	}

	/*
	 * Check to make sure the board rules a Fool's Mate a checkmate.
	 */
	@Test
	public void testIsKingInCheckmateFoolsMate() {
		StandardBoard board = new StandardBoard();
		assertTrue(board.movePieceTo(board.getPieceAt(new Vector2i(5, 1)), new Vector2i(5, 2)));
		assertTrue(board.movePieceTo(board.getPieceAt(new Vector2i(4, 6)), new Vector2i(4, 4)));
		assertTrue(board.movePieceTo(board.getPieceAt(new Vector2i(6, 1)), new Vector2i(6, 3)));
		assertTrue(board.movePieceTo(board.getPieceAt(new Vector2i(3, 7)), new Vector2i(7, 3)));
		assertTrue(board.isKingInCheckmate(board.getWhiteKing()));
	}

	@Test
	public void testIsKingInCheckmateFalse() {
		StandardBoard board = new StandardBoard();
		assertTrue(board.movePieceTo(board.getPieceAt(new Vector2i(5, 1)), new Vector2i(5, 2)));
		assertTrue(board.movePieceTo(board.getPieceAt(new Vector2i(4, 6)), new Vector2i(4, 4)));
		assertTrue(board.movePieceTo(board.getPieceAt(new Vector2i(6, 1)), new Vector2i(6, 3)));
		assertFalse(board.isKingInCheckmate(board.getWhiteKing()));
	}

	@Test
	public void testUndoMove() {
		StandardBoard board = getEmptyStandardBoard();
		Pawn pawn1 = new Pawn(ChessColor.BLACK, BLACK_DIR);
		Pawn pawn2 = new Pawn(ChessColor.WHITE, WHITE_DIR);

		board.addPiece(pawn1, new Vector2i(5, 5));
		board.addPiece(pawn2, new Vector2i(4, 4));

		assertTrue(board.movePieceTo(board.getPieceAt(new Vector2i(4, 4)), new Vector2i(5, 5)));
		assertTrue(board.undoMove());
		assertNotEquals(board.getPieceAt(new Vector2i(4, 4)), null);
		assertNotEquals(board.getPieceAt(new Vector2i(5, 5)), null);
	}
}
