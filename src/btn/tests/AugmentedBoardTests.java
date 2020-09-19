package btn.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import btn.utils.Vector2i;
import btn.chess.*;

public class AugmentedBoardTests {

	@Test
	public void testConstructor1() {
		AugmentedBoard board = new AugmentedBoard();
		assertEquals(board.getWidth(), 8);
		assertEquals(board.getHeight(), 8);
		assertEquals(board.getRemovedPieces().size(), 0);
		assertNotEquals(board.getPieceAt(new Vector2i(4, 2)), null);
		assertNotEquals(board.getPieceAt(new Vector2i(5, 2)), null);
		assertNotEquals(board.getPieceAt(new Vector2i(4, 5)), null);
		assertNotEquals(board.getPieceAt(new Vector2i(5, 5)), null);
	}
}
