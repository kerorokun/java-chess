package btn.chess;

import btn.utils.Vector2i;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;

public class StandardBoard extends Board {
	private static final int WIDTH = 8;
	private static final int HEIGHT = 8;

	// Where should these constants live (global?)
	private final int WHITE_DIRECTION = 1;
	private final int BLACK_DIRECTION = -1;

	public StandardBoard() {
		super(WIDTH, HEIGHT);
		
		super.addPiece(new Rook(ChessColor.WHITE),     new Vector2i(0, 0));
		super.addPiece(new Rook(ChessColor.WHITE),     new Vector2i(7, 0));
		super.addPiece(new Knight(ChessColor.WHITE),   new Vector2i(1, 0));
		super.addPiece(new Knight(ChessColor.WHITE),   new Vector2i(6, 0));
		super.addPiece(new Bishop(ChessColor.WHITE),   new Vector2i(2, 0));
		super.addPiece(new Bishop(ChessColor.WHITE),   new Vector2i(5, 0));
		super.addPiece(new Queen(ChessColor.WHITE),    new Vector2i(3, 0));
		super.addPiece(new King(ChessColor.WHITE),     new Vector2i(4, 0));

		super.addPiece(new Rook(ChessColor.BLACK),     new Vector2i(0, 7));
		super.addPiece(new Rook(ChessColor.BLACK),     new Vector2i(7, 7));
		super.addPiece(new Knight(ChessColor.BLACK),   new Vector2i(1, 7));
		super.addPiece(new Knight(ChessColor.BLACK),   new Vector2i(6, 7));
		super.addPiece(new Bishop(ChessColor.BLACK),   new Vector2i(2, 7));
		super.addPiece(new Bishop(ChessColor.BLACK),   new Vector2i(5, 7));
		super.addPiece(new Queen(ChessColor.BLACK),    new Vector2i(3, 7));
		super.addPiece(new King(ChessColor.BLACK),     new Vector2i(4, 7));

		for (int i = 0; i < WIDTH; i++) {
			super.addPiece(new Pawn(ChessColor.WHITE, WHITE_DIRECTION), new Vector2i(i, 1));
			super.addPiece(new Pawn(ChessColor.BLACK, BLACK_DIRECTION), new Vector2i(i, 6));
		}
	}

	/*
	 * Tests if a given position on the board is in check by enemy pieces
	 * @param pos: Vector2i specifying the position to test
	 * @param enemyPieces: List of Pieces that are enemies of the position to test
	 * @return whether the position is in check
	 */
	public boolean isPositionInCheck(Vector2i pos, List<Piece> enemyPieces) {
		for (Piece piece : enemyPieces) {
			if (piece.isMoveValid(this, pos)) {
				return true;
			}
		}
		return false;
	}

	public boolean isKingInCheck(King king) {
		List<Piece> enemyPieces = getPiecesByColor().get(ChessColor.BLACK);
		if (king.getColor() == ChessColor.BLACK) {
			enemyPieces = getPiecesByColor().get(ChessColor.WHITE);
		}
		return isPositionInCheck(king.getPosition(), enemyPieces);
	}
	
	/*
	 * Checks if a King has no valid moves from its current position
	 * @param king: King to test
	 * @return: Whether the King is in check for all its valid moves from its current position
	 */
	private boolean isKingInCheckForAllValidMoves(King king) {
		List<Piece> enemyPieces = getPiecesByColor().get(ChessColor.BLACK);
		if (king.getColor() == ChessColor.BLACK) {
			enemyPieces = getPiecesByColor().get(ChessColor.WHITE);
		}
		
		List<Vector2i> validNextPositions = king.getValidNextPositions(this);
		if (validNextPositions.isEmpty()) {
			return false;
		}

		boolean returnFalse = false;
		for (Vector2i validNextPos : validNextPositions) {
			if (returnFalse) {
				break;
			}
			// Simulate the movement
			Piece newPosPiece = getPieceAt(validNextPos);
			if (newPosPiece != null) {
				piecesByColor.get(newPosPiece.getColor()).remove(newPosPiece);
				enemyPieces.remove(newPosPiece);
			}

			board[king.getX()][king.getY()] = null;
			board[validNextPos.getX()][validNextPos.getY()] = king;
			
			if (!isPositionInCheck(validNextPos, enemyPieces)) {
				returnFalse = true;
			}

			// Undo any changes
			board[validNextPos.getX()][validNextPos.getY()] = null;
			if (newPosPiece != null) {
				board[validNextPos.getX()][validNextPos.getY()] = newPosPiece;
				piecesByColor.get(newPosPiece.getColor()).add(newPosPiece);
				enemyPieces.add(newPosPiece);
			}

			board[king.getX()][king.getY()] = king;
		}
		return !returnFalse;
	}

	/*
	 * Returns the enemy pieces of a given king
	 * @param king: King to get the enemies of
	 * @return: List of enemy pieces of the given king
	 */
	private List<Piece> getEnemiesOfKing(King king) {
		List<Piece> enemyPieces = getPiecesByColor().get(ChessColor.BLACK);
		if (king.getColor() == ChessColor.BLACK) {
			enemyPieces = getPiecesByColor().get(ChessColor.WHITE);
		}
		return enemyPieces;
	}
	
	/*
	 * Checks if a King is in checkmate
	 * @param king: King to test
	 * @return: Whether the King is in checkmate
	 */
	public boolean isKingInCheckmate(King king) {
		if (!isPositionInCheck(king.getPosition(), getEnemiesOfKing(king))) {
			return false;
		}
		return isKingInCheckForAllValidMoves(king);
	}

	/*
	 * Checks if a King is in stalemate
	 * @param king: King to test
	 * @return: Whether the King is in stalemate
	 */
	public boolean isKingInStalemate(King king) {
		if (isPositionInCheck(king.getPosition(), getEnemiesOfKing(king))) {
			return false;
		}

		return isKingInCheckForAllValidMoves(king);
	}

	public King getWhiteKing() {
		for (Piece p : getWhitePieces()) {
			if (p instanceof King) {
				return (King)p;
			}
		}
		return null;
	}

	public King getBlackKing() {
		for (Piece p : getBlackPieces()) {
			if (p instanceof King) {
				return (King)p;
			}
		}
		return null;
	}
	
	public List<Piece> getWhitePieces() {
		return this.piecesByColor.get(ChessColor.WHITE);
	}

	public List<Piece> getBlackPieces() {
		return this.piecesByColor.get(ChessColor.BLACK);
	}
}
