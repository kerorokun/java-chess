package btn.chess;

import btn.utils.Vector2i;

public class GameTurn {

	public Vector2i initialPosition;
	public Vector2i finalPosition;
	public Piece pieceMoved;
	public Piece pieceKilled;
	
	public GameTurn(Vector2i initialPos, Vector2i finalPos, Piece pieceMoved, Piece pieceKilled) {
		this.initialPosition = initialPos;
		this.finalPosition = finalPos;
		this.pieceMoved = pieceMoved;
		this.pieceKilled = pieceKilled;
	}
}
