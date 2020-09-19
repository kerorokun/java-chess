package btn.chess;

import btn.utils.Vector2i;

public abstract class Piece {
	protected Vector2i position;
	protected ChessColor color;
		
	public Piece(ChessColor color) {
		this.color = color;
		this.position = new Vector2i(-1, -1);
	}

	/*
	 * Checks whether a position is reachable for the piece at its current position.
	 * @param board: Board that the piece currently resides on
	 * @param newPos: Vector2i specifying the position to check to see if the piece can reach
	 * @return Whether the move is valid
	 */
	public boolean isMoveValid(Board board, Vector2i newPos) {
		if (newPos.equals(this.position) || !board.isPositionInBounds(newPos)) {
			return false;
		}
		
		Piece newPosPiece = board.getPieceAt(newPos);
		if (newPosPiece != null && newPosPiece.getColor() == color) {
			return false;
		}

		return true;
	}

	/*
	 * Checks whether a Piece is obstructed on a straight line path from its current position to a target position.
	 * @param board: Board that the piece currently resides on
	 * @param newPos: Vector2i specifying the position to check to see if piece can reach in a straight line without obstruction
	 * @return Whether there is an obstacle in the way of the straight line path
	 */	
	protected boolean isObstacleInStraightLine(Board board, Vector2i newPos) {
		Vector2i displacement = newPos.subtract(this.position);
		int xDisp = displacement.getX();
		int yDisp = displacement.getY();
		int xDir = Integer.signum(xDisp);
		int yDir = Integer.signum(yDisp);
		int loopVar = Math.max(Math.abs(xDisp), Math.abs(yDisp));
		for (int i = 1; i <= loopVar; i++) {
			Piece pieceAtPos = board.getPieceAt(this.position.add(new Vector2i(i * xDir, i * yDir)));
			if (i == loopVar && pieceAtPos != null && pieceAtPos.getColor() == this.color) {
				return true;
			} else if (i != loopVar && pieceAtPos != null) {
				return true;
			}
		}
				
		return false;
	}

	/*
	 * Notify the piece that it has been moved
	 */
	public void notifyMove(boolean moved) {
	}

	public Vector2i getPosition() {
		return this.position;
	}

	public int getX() {
		return this.position.getX();
	}

	public int getY() {
		return this.position.getY();
	}

	public ChessColor getColor() {
		return this.color;
	}

	public void setPosition(Vector2i newPos) {
		this.position = newPos;
	}
} 
