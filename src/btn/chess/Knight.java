package btn.chess;

import btn.utils.Vector2i;

public class Knight extends Piece {
	
	public Knight(ChessColor color) {
		super(color);
	}
	
	/*
	 * Checks whether a position is reachable for the Knight at its current position.
	 * @param board: Board that the Knight currently resides on
	 * @param newPos: Vector2i specifying the position to check to see if the Knight can reach
	 * @return Whether the move is valid
	 */
	@Override
	public boolean isMoveValid(Board board, Vector2i newPos) {
		if (!super.isMoveValid(board, newPos)) {
			return false;
		}
		
		Vector2i displacement = newPos.subtract(this.position);

		return displacement.equals(new Vector2i(2, 1)) ||
			displacement.equals(new Vector2i( 2, -1)) ||
			displacement.equals(new Vector2i(-2,  1)) ||
			displacement.equals(new Vector2i(-2, -1)) ||
			displacement.equals(new Vector2i( 1,  2)) ||
			displacement.equals(new Vector2i( 1, -2)) ||
			displacement.equals(new Vector2i(-1,  2)) ||
			displacement.equals(new Vector2i(-1, -2));
	}
};
