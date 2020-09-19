package btn.chess;

import btn.utils.Vector2i;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;

public abstract class Board {
	protected int width;
	protected int height;
	protected Piece board[][];
	protected HashMap<ChessColor, List<Piece>> piecesByColor;
	protected List<Piece> removedPieces;

	protected List<GameTurn> turns;
    
	public Board(int width, int height) {
		this.width = width;
		this.height = height;
		this.board = new Piece[width][height];
		this.removedPieces = new ArrayList<>();
		this.piecesByColor = new HashMap<>();

		this.turns = new ArrayList<>();
	}

	/*
	 * Undo a move and reset the board back to the original position.
	 */
	public boolean undoMove() {
		if (turns.isEmpty()) {
			return false;
		}

		GameTurn turn = turns.remove(turns.size() - 1);
		if (turn.pieceKilled != null) {
			removedPieces.remove(turn.pieceKilled);
			turn.pieceKilled.setPosition(turn.finalPosition);
		}
		board[turn.finalPosition.getX()][turn.finalPosition.getY()] = turn.pieceKilled;
		board[turn.initialPosition.getX()][turn.initialPosition.getY()] = turn.pieceMoved;
		turn.pieceMoved.setPosition(turn.initialPosition);
		turn.pieceMoved.notifyMove(false);
		return true;
	}

	/*
	 * Add a piece to the board at a given position
	 * @param piece: Piece to add to the board
	 * @param position: Vector2i specifying the position to place the piece at
	 */
	public void addPiece(Piece piece, Vector2i position) {
		// TODO: Check if there is a piece there already?
		piece.setPosition(position);
		board[piece.getX()][piece.getY()] = piece;
        
		if (!piecesByColor.containsKey(piece.getColor())) {
			piecesByColor.put(piece.getColor(), new ArrayList<Piece>());
		}
		piecesByColor.get(piece.getColor()).add(piece);
	}

	/*
	 * Remove a piece from a board
	 * @param piece: Piece to remove
	 */
	public void removePiece(Piece piece) {
		if (piece == null ||
			!isPositionInBounds(piece.getPosition()) ||
			board[piece.getX()][piece.getY()] == null) {
			return;
		}
		
		board[piece.getX()][piece.getY()] = null;
		removedPieces.add(piece);
		piecesByColor.get(piece.getColor()).remove(piece);
	}

	/*
	 * Move a piece to a specific position
	 * @param piece: Piece to move
	 * @param newPos: Vector2i specifying the new position to move the piece to
	 * @return Whether the move succeeded or not
	 */
	public boolean movePieceTo(Piece piece, Vector2i newPos) {
		if (!piece.isMoveValid(this, newPos)) {
			return false;
		}
        
		Piece newPosPiece = getPieceAt(newPos);
		if (newPosPiece != null) {
			removePiece(newPosPiece);
		}

		turns.add(new GameTurn(piece.getPosition(), newPos, piece, newPosPiece));
		
		board[piece.getX()][piece.getY()] = null;
		board[newPos.getX()][newPos.getY()] = piece;
		piece.setPosition(newPos);
		piece.notifyMove(true);
		return true;
	}
    
	//==== Helper functions =============================================================
	public boolean isPositionInBounds(Vector2i pos) {
	    return pos.getX() >= 0 && pos.getX() < width && pos.getY() >= 0 && pos.getY() < height;
	}
    
	public Piece getPieceAt(Vector2i pos) {
		if (!isPositionInBounds(pos)) {
			return null;
		}
		return board[pos.getX()][pos.getY()];
	}

	public HashMap<ChessColor, List<Piece>> getPiecesByColor() {
		return piecesByColor;
	}
    
	public List<Piece> getRemovedPieces() {
		return removedPieces;
	}
    
    public int getHeight() {
        return width;
    }
    
    public int getWidth() {
        return height;
    }
}
