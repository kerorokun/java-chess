package btn.chess;

import btn.utils.Vector2i;

public class Pawn extends Piece {

	private boolean firstMove;
	private int moveNum;
	private int forwardDirection;

	/*
	 * Construct a Pawn.
	 * @param color: color of the Pawn piece
	 * @param forwardDirection: the vertical direction that the Pawn considers forward
	 */
	public Pawn(ChessColor color, int forwardDirection) {
		super(color);
		this.firstMove = true;
		this.moveNum = 0;
		this.forwardDirection = Integer.signum(forwardDirection);
	}
	
	/*
	 * Checks whether a position is reachable for the Pawn at its current position.
	 * @param board: Board that the Pawn currently resides on
	 * @param newPos: Vector2i specifying the position to check to see if the Pawn can reach
	 * @return Whether the move is valid
	 */
	@Override
	public boolean isMoveValid(Board board, Vector2i newPos) {
		if (!super.isMoveValid(board, newPos)) {
			return false;
		}

		Vector2i displacement = newPos.subtract(this.position);
		
		if (firstMove && displacement.equals(new Vector2i(0, 2 * forwardDirection))) {
			return board.getPieceAt(new Vector2i(this.position.getX(), this.position.getY() + 1 * forwardDirection)) == null &&
				board.getPieceAt(new Vector2i(this.position.getX(), this.position.getY() + 2 * forwardDirection)) == null;
		}

		if (displacement.equals(new Vector2i(0, forwardDirection))) {
			return board.getPieceAt(newPos) == null; 
		}

		if (displacement.equals(new Vector2i(1, forwardDirection)) ||
			displacement.equals(new Vector2i(-1, forwardDirection))) {
			return board.getPieceAt(newPos) != null;
		}

		return false;
	}

	/*
	 * Notify that the Pawn that it has been moved on the board
	 */
	@Override
	public void notifyMove(boolean moved) {
		if (moved) {
			firstMove = false;
			moveNum++;
		} else {
			moveNum--;
			firstMove = moveNum <= 0 ? true : false;
		}
	}

	public boolean getFirstMove() {
		return this.firstMove;
	}

	public int getForwardDirection() {
		return this.forwardDirection;
	}
}
