package btn.chess;

import btn.utils.Vector2i;
import java.util.ArrayList;
import java.util.List;

public class King extends Piece {

	public King(ChessColor color) {
		super(color);
	}

	/*
	 * Checks whether a position is reachable for the King at its current position.
	 * @param board: Board that the King currently resides on
	 * @param newPos: Vector2i specifying the position to check to see if the King can reach
	 * @return Whether the move is valid
	 */
	@Override
	public boolean isMoveValid(Board board, Vector2i newPos) {
		if (!super.isMoveValid(board, newPos)) {
			return false;
		}
		
		Vector2i displacement = newPos.subtract(this.position);

		return displacement.equals(new Vector2i(1, 1)) ||
			displacement.equals(new Vector2i( 1, -1)) ||
			displacement.equals(new Vector2i(-1,  1)) ||
			displacement.equals(new Vector2i(-1, -1)) ||
			displacement.equals(new Vector2i( 0,  1)) ||
			displacement.equals(new Vector2i( 0, -1)) ||
			displacement.equals(new Vector2i( 1,  0)) ||
			displacement.equals(new Vector2i(-1,  0));
	}

	/*
	 * Get a list of valid positions for the King at its current position for a given board.
	 * @param board: Board that the King currently resides on
	 * @return a list of valid moves
	 */
	public List<Vector2i> getValidNextPositions(Board board) {
		List<Vector2i> validMoves = new ArrayList<Vector2i>();
		Vector2i possibleNextPositions[] = new Vector2i[] {
			this.position.add(new Vector2i( 1,  1)),
			this.position.add(new Vector2i( 1, -1)),
			this.position.add(new Vector2i( 1,  0)),
			this.position.add(new Vector2i(-1,  1)),
			this.position.add(new Vector2i(-1, -1)),
			this.position.add(new Vector2i(-1,  0)),
			this.position.add(new Vector2i( 1,  0)),
			this.position.add(new Vector2i(-1,  0))
		};

		for (Vector2i possibleNextPos : possibleNextPositions) {
			Piece pieceAtPos = board.getPieceAt(possibleNextPos);
			if (board.isPositionInBounds(possibleNextPos) &&
				(pieceAtPos == null || pieceAtPos.getColor() != this.color)) {
				validMoves.add(possibleNextPos);
			}
		}

		return validMoves;
	}
};
