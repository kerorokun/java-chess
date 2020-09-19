package btn.chess;

import btn.utils.Vector2i;

public class ChessModel {

	private StandardBoard board;
	private ChessPlayer player1;
	private ChessPlayer player2;

	private ChessColor currColor;
	private ChessState previousState;
	private ChessState currState;
	
	private Vector2i selectedPos;
	private Vector2i invalidPos;
	private final Vector2i NULL_SPACE_POS = new Vector2i(-1, -1);

	/*
	 * Create a new ChessModel. A ChessModel stores the data related to the chess game
	 * as a whole.
	 */
	public ChessModel() {
		player1 = new ChessPlayer(ChessColor.WHITE, "White");
		player2 = new ChessPlayer(ChessColor.BLACK, "Black");
		startNewGame();
	}

	public void startNewGame() {
		board = new AugmentedBoard();
		currColor = ChessColor.WHITE;
		this.selectedPos = NULL_SPACE_POS;
		this.invalidPos = NULL_SPACE_POS;
		this.currState = ChessState.WAITING_FOR_START_POS_SELECTION;
		this.previousState = ChessState.WAITING_FOR_START_POS_SELECTION;
	}

	/*
	 * Attempt to selected a space.
	 * @param pos: Vector2i specifying the position of the space to attempt to select
	 * @return whether or not the space was successfully selected
	 */
	public boolean attemptSpaceSelect(Vector2i pos) {
		if (pos == null || isSpaceSelectable(pos)) {
			selectedPos = pos;
			invalidPos = NULL_SPACE_POS;
			currState = this.currState = ChessState.WAITING_FOR_DESTINATION_POS_SELECTION;
			return true;
		}
		invalidPos = pos;
		return false;
	}

	/*
	 * Attempt to move the selected piece to a destination position.
	 * @param destPos: Vector2i specifying the position to move the selected piece to.
	 * @return whether or not the piece was successfully moved
	 */
	public boolean attemptMove(Vector2i destPos) {
		if (selectedPos == NULL_SPACE_POS || destPos == null ||
			!makeMove(selectedPos, destPos)) {
			invalidPos = destPos;
			selectedPos = NULL_SPACE_POS;
			this.currState = ChessState.WAITING_FOR_START_POS_SELECTION;
			return false;
		}

		changeTurns();
		selectedPos = NULL_SPACE_POS;
		invalidPos = NULL_SPACE_POS;
		currState = ChessState.WAITING_FOR_START_POS_SELECTION;
		return true;
	}

	/*
	 * Attempt to undo the last move.
	 * @return whether or not the move was successfully undone.
	 */
	public boolean undoMove() {
		if (!board.undoMove()) {
			return false;
		}
		changeTurns();
		selectedPos = NULL_SPACE_POS;
		invalidPos = NULL_SPACE_POS;
		currState = ChessState.WAITING_FOR_START_POS_SELECTION;
		return true;
	}

	/*
	 * Whether or not a position is selectable for the current player.
	 * @param pos: Vector2i specifying the position to select.
	 * @return whether or not the position is selectable for the current player.
	 */
	private boolean isSpaceSelectable(Vector2i pos) {
		Piece pieceAtPos = board.getPieceAt(pos);
		if (pieceAtPos == null || pieceAtPos.getColor() != currColor) {
			return false;
		}
		selectedPos = pos;
		return true;
	}

	/*
	 * Move a piece from a starting position to a final position.
	 * @param startPos: Vector2i specifying the starting position
	 * @param newPos: Vector2i specifying the destination position
	 * @return whether the move was performed successfully or not
	 */
	private boolean makeMove(Vector2i startPos, Vector2i newPos) {
		Piece pieceToMove = board.getPieceAt(startPos);
		if (pieceToMove == null) {
			return false;
		}
		
		return board.movePieceTo(pieceToMove, newPos);
	}

	/*
	 * Change the current player.
	 */
	private void changeTurns() {
		if (currColor == ChessColor.WHITE) {
			currColor = ChessColor.BLACK;
		} else {
			currColor = ChessColor.WHITE;
		}
	}

	/*
	 * Check if the given player is in checkmate.
	 * @param color: Color of the player to check.
	 * @return whether the player is in checkmate or not
	 */
	public boolean isPlayerInCheckmate(ChessColor color) {
		if (color == ChessColor.WHITE) {
			return board.isKingInCheckmate(board.getWhiteKing());
		} else {
			return board.isKingInCheckmate(board.getBlackKing());
		}
	}

	/*
	 * Check if the given player is in check.
	 * @param color: Color of the player to check.
	 * @return whether the player is in check or not
	 */
	public boolean isPlayerInCheck(ChessColor color) {
		if (color == ChessColor.WHITE) {
			return board.isKingInCheck(board.getWhiteKing());
		} else {
			return board.isKingInCheck(board.getBlackKing());
		}
	}

	/*
	 * Check if the given player is in stalemate.
	 * @param color: Color of the player to check.
	 * @return whether the player is in stalemate or not
	 */
	public boolean isPlayerInStalemate(ChessColor color) {
		if (color == ChessColor.WHITE) {
			return board.isKingInStalemate(board.getWhiteKing());
		} else {
			return board.isKingInStalemate(board.getBlackKing());
		}
	}

	/*
	 * End the game.
	 */
	public void endGame() {
		currState = ChessState.GAME_END;
	}

	/*
	 * Forfeit the game for the player of the given color.
	 * @param color: Color of the player forfeiting.
	 */
	public void forfeit(ChessColor color) {
		if (color == ChessColor.WHITE) {
			incrementPlayerScore(ChessColor.BLACK);
		} else {
			incrementPlayerScore(ChessColor.WHITE);
		}
		endGame();
	}

	/*
	 * Win the game for the player of the given color.
	 * @param color: Color of the player who won.
	 */
	public void winGame(ChessColor color) {
		incrementPlayerScore(color);
		endGame();
	}

	/*
	 * Begin the restart process.
	 * @param color: ChessColor of the player initiating the restart process.
	 */
	public void beginRestart(ChessColor color) {
		if (currState == ChessState.WAITING_FOR_RESTART_RESPONSE_BLACK ||
			currState == ChessState.WAITING_FOR_RESTART_RESPONSE_WHITE) {
			return;
		}

		previousState = currState;
		
		if (color == ChessColor.WHITE) {
			currState = ChessState.WAITING_FOR_RESTART_RESPONSE_BLACK;
		} else {
			currState = ChessState.WAITING_FOR_RESTART_RESPONSE_WHITE;
		}
	}

	/*
	 * End the restart process.
	 * @param agreement: boolean specifying whether the other player agreed to restart the game or not.
	 */
	public void endRestart(boolean agreement) {
		if (currState != ChessState.WAITING_FOR_RESTART_RESPONSE_BLACK &&
			currState != ChessState.WAITING_FOR_RESTART_RESPONSE_WHITE) {
			return;
		}

		if (agreement) {
			startNewGame();
		} else {
			currState = previousState;
		}
	}

	/*
	 * Increment the player score.
	 * @param color: Color of the player whose score to increment
	 */
	public void incrementPlayerScore(ChessColor color) {
		if (color == ChessColor.WHITE) {
			player1.incrementScore();
		} else {
			player2.incrementScore();
		}
	}

	
	public Vector2i getSelectedSpace() {
		return selectedPos;
	}

	public Vector2i getInvalidSpace() {
		return invalidPos;
	}

	public ChessState getCurrState() {
		return currState;
	}

	public ChessPlayer getPlayer1() {
		return player1;
	}

	public ChessPlayer getPlayer2() {
		return player2;
	}

	public ChessPlayer getCurrPlayer() {
		return (currColor == ChessColor.WHITE ? player1 : player2);
	}
	
	public ChessColor getCurrPlayersColor() {
		return currColor;
	}

	public StandardBoard getBoard() {
		return this.board;
	}

}
