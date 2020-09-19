package btn.chess;

import btn.utils.Vector2i;

public class Centaur extends Piece {
	
	public Centaur(ChessColor color) {
		super(color);
	}
	
	/*
	 * Checks whether a position is reachable for the Centaur at its current position.
	 * @param board: Board that the Centaur currently resides on
	 * @param newPos: Vector2i specifying the position to check to see if the Centaur can reach
	 * @return Whether the move is valid
	 */
	@Override
	public boolean isMoveValid(Board board, Vector2i newPos) {
		if (!super.isMoveValid(board, newPos)) {
			return false;
		}
		
		Vector2i displacement = newPos.subtract(this.position);

		// NOTE: Centaur can jump over other pieces
		return displacement.equals(new Vector2i(2, 2)) ||
			displacement.equals(new Vector2i( 2, -2)) ||
			displacement.equals(new Vector2i(-2,  2)) ||
			displacement.equals(new Vector2i(-2, -2));
	}
}
