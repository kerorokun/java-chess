package btn.chess;

import btn.utils.Vector2i;
import java.lang.Math;

public class Bishop extends Piece {
    
	public Bishop(ChessColor color) {
		super(color);
	}
    
	/*
	 * Checks whether a position is reachable for the Bishop at its current position.
	 * @param board: Board that the Bishop currently resides on
	 * @param newPos: Vector2i specifying the position to check to see if the Bishop can reach
	 * @return Whether the move is valid
	 */
	@Override
	public boolean isMoveValid(Board board, Vector2i newPos) {
		if (!super.isMoveValid(board, newPos)) {
			return false;
		}
		
		Vector2i displacement = newPos.subtract(this.position);
		int xDisp = displacement.getX();
		int yDisp = displacement.getY();
        
		if ((xDisp == 0 && yDisp == 0) || (Math.abs(xDisp) != Math.abs(yDisp))) {
			return false;
		}
        
		return !isObstacleInStraightLine(board, newPos);
	}
}
