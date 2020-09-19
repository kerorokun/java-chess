package btn.controllers;

import btn.chess.ChessColor;
import btn.chess.ChessPlayer;
import btn.chess.ChessState;
import btn.utils.Vector2i;
import btn.chess.ChessModel;
import btn.views.ChessView;
import btn.views.GameStateView;


public class ChessController {

	private ChessModel model;
	private ChessView view;

	public ChessController() {
		this.model = null;
		this.view = null;
	}

	public ChessController(ChessModel model) {
		this.model = model;
	}

	public ChessController(ChessModel model, ChessView view) {
		this.model = model;
		this.view = view;
	}

	public void setView(ChessView view) {
		this.view = view;
	}

	public void setModel(ChessModel model) {
		this.model = model;
	}

	/*
	 * Set a ChessPlayer's name.
	 * @param player: ChessPlayer whose name to edit
	 * @param name: String representing the name to change it to
	 */
	public void setPlayerName(ChessPlayer player, String name) {
		player.setName(name);
	}

	/*
	 * The main game loop.
	 */
	public void mainLoop() {
		GameStateView gameOut = view.getGameStateView();
		((GameStateView) gameOut).appendOutput(model.getPlayer1().getName() + "'s turn.");
		gameOut.appendOutput("Choose a piece to move.\n");
		while(true) {
			view.draw();
		}
	}

	/*
	 * Attempt to undo the last move on the board.
	 */
	public void undoMove() {
		if (!model.undoMove()) {
			return;
		}
		
		GameStateView gameOut = view.getGameStateView();
		if (model.getCurrPlayersColor() == ChessColor.WHITE) {
			gameOut.appendOutput("Move undone. " + model.getPlayer1().getName() + "'s turn.\n");
		} else {
			gameOut.appendOutput("Move undone. " + model.getPlayer2().getName() + "'s turn.\n");
		}
	}

	/*
	 * Forfeit the game.
	 * @param player: ChessPlayer representing the player forfeiting.
	 */
	public void forfeit(ChessPlayer player) {
		model.forfeit(player.getColor());
		GameStateView gameOut = view.getGameStateView();
		gameOut.appendOutput(player.getName() + " has forfeited. Game ended");
		gameOut.appendOutput("-------------------------------");
	}

	
	/*
	 * Attempt to select a space on the board.
	 * @param pos: Vector2i position of the space to select
	 * @param whether the space was successfully selected or not
	 */
	public boolean attemptSpaceSelect(Vector2i pos) {
		GameStateView gameOut = view.getGameStateView();
		
		if (!model.attemptSpaceSelect(pos)) {
			gameOut.appendOutput("Not a valid piece.");
			gameOut.appendOutput("Choose another space.\n");
			return false;
		}
		gameOut.appendOutput("Piece selected.");
		gameOut.appendOutput("Choose a space to move the piece.\n");
		return true;
	}

	/*
	 * Attempt to make a piece movement.
	 * @param pos: Vector2i position to output
	 * @return whether the move was performed successfully
	 */
	public boolean attemptMove(Vector2i pos) {
		GameStateView gameOut = view.getGameStateView();
		
		if (!model.attemptMove(pos)) {
			gameOut.appendOutput("Not a valid move.");
			gameOut.appendOutput("Try again.\n");
			return false;
		}

		gameOut.appendOutput("Piece moved.");

		ChessPlayer player = model.getCurrPlayer();
		if (handleCheckmate(player) || handleStalemate(player)) {
			return true;
		}
		handleCheck(player);
		gameOut.appendOutput(player.getName() + "'s turn.\n");
		return true;
	}

	/*
	 * Handle cases where a player is in check
	 * @param player: ChessPlayer of the player to check for checkmate
	 * @return whether the player was in check or not.
	 */
	private boolean handleCheck(ChessPlayer player) {
		GameStateView gameOut = view.getGameStateView();
		if (model.isPlayerInCheck(player.getColor())) {
			gameOut.appendOutput(player.getName() + "'s king is in check.");
			return true;
		}
		return false;
	}

	/*
	 * Handle cases where the player is in checkmate.
	 * @param player: ChessPlayer of the player to check for checkmate
	 * @return whether the player was in checkmate or not.
	 */
	private boolean handleCheckmate(ChessPlayer player) {
		GameStateView gameOut = view.getGameStateView();
		if (model.isPlayerInCheckmate(player.getColor())) {
			gameOut.appendOutput(player.getName() + "'s king is in checkmate. Game is won.");
			model.winGame(player.getColor());
			return true;
		}
		return false;
	}

	/*
	 * Handle cases where the player is in stalemate.
	 * @param player: ChessPlayer of the player to check for stalemate
	 * @return whether the player was in stalemate or not.
	 */
	private boolean handleStalemate(ChessPlayer player) {
		GameStateView gameOut = view.getGameStateView();
		if (model.isPlayerInStalemate(player.getColor())) {
			gameOut.appendOutput(player.getName() + "'s king is in stalemate. Game is ended.");
			model.endGame();
			return true;
		}
		return false;
	}

	/*
	 * Begin the restart process.
	 * @param player: ChessPlayer initiating the reset process.
	 */
	public void beginRestart(ChessPlayer player) {
		model.beginRestart(player.getColor());
		GameStateView gameOut = view.getGameStateView();
		
		gameOut.appendOutput("Restart initiated. Yes? No?");
	}

	/*
	 * End the restart process.
	 * @param agreement: boolean specifying whether the other player agreed to restart the game or not.
	 */
	public void endRestart(boolean agreement) {
		GameStateView gameOut = view.getGameStateView();
		if (agreement) {
			gameOut.appendOutput("Restart agreed. Game restarting...");
			gameOut.appendOutput("-------------------------------");
		} else {
			gameOut.appendOutput("Restart disagreed.");
		}
		model.endRestart(agreement);
	}
	
	public ChessColor getCurrPlayersColor() {
		return model.getCurrPlayersColor();
	}

	public ChessPlayer getPlayer1() {
		return model.getPlayer1();
	}

	public ChessPlayer getPlayer2() {
		return model.getPlayer2();
	}
	
	public Vector2i getSelectedSpace() {
		return model.getSelectedSpace();
	}

	public Vector2i getInvalidSpace() {
		return model.getInvalidSpace();
	}

	public ChessState getCurrGameState() {
		return model.getCurrState();
	}
}
