package btn.views;

import java.awt.*;
import javax.swing.*;
import btn.controllers.ChessController;
import btn.chess.ChessPlayer;

public class UserOptionsView {

	private JSplitPane splitPane;
	private PlayerOptionsView player1View;
	private PlayerOptionsView player2View;

	private ChessController controller;

	public UserOptionsView(ChessController controller) {
		this.controller = controller;
		
		this.player1View = new PlayerOptionsView(controller.getPlayer1(), controller);
		this.player2View = new PlayerOptionsView(controller.getPlayer2(), controller);

		this.splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT,
										this.player1View.getPanel(),
										this.player2View.getPanel());
		this.splitPane.setOneTouchExpandable(false);
		this.splitPane.setResizeWeight(0.5);
	}

	public void draw() {
		player1View.draw();
		player2View.draw();
	}

	public JSplitPane getPanel() {
		return this.splitPane;
	}
}
