package btn.views;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import btn.chess.ChessPlayer;
import btn.chess.ChessColor;
import btn.chess.ChessState;
import btn.controllers.ChessController;

public class PlayerOptionsView implements ActionListener {

	private JPanel panel;
	private GridLayout layout;
	private JButton forfeitButton;
	private JButton undoButton;
	private JButton restartButton;
	private JButton yesButton;
	private JButton noButton;
	private JLabel scoreLabel;
	private JTextField nameField;
	private final Color BACKGROUND_COLOR = new Color(201, 205, 207);

	private ChessController controller;
	private ChessPlayer player;

	public PlayerOptionsView(ChessPlayer player, ChessController controller) {
		this.player = player;
		this.controller = controller;
		createGUI();
	}

	/*
	 * Create the GUI for the player
	 */
	private void createGUI() {
		this.forfeitButton = new JButton("Forfeit");
		this.forfeitButton.setActionCommand("forfeit");
		this.restartButton = new JButton("Restart");
		this.restartButton.setActionCommand("restart");
		this.yesButton = new JButton("Yes");
		this.yesButton.setActionCommand("yes");
		this.noButton = new JButton("No");
		this.noButton.setActionCommand("no");
		this.undoButton = new JButton("Undo");
		this.undoButton.setActionCommand("undo");
		this.scoreLabel = new JLabel("Score: 0");

		this.nameField = new JTextField(player.getName());
		
		JPanel buttonPanel = new JPanel(new GridLayout(2, 3));
		buttonPanel.add(forfeitButton);
		buttonPanel.add(restartButton);
		buttonPanel.add(undoButton);
		buttonPanel.add(yesButton);
		buttonPanel.add(noButton);

		JPanel fieldPanel = new JPanel(new GridLayout(2, 1));
		fieldPanel.add(new JLabel("Name:"));
		fieldPanel.add(nameField);
		fieldPanel.add(scoreLabel);
		
		this.panel = new JPanel(new GridLayout(2, 1));
		this.panel.setBackground(BACKGROUND_COLOR);
		this.panel.add(fieldPanel);
		this.panel.add(buttonPanel);

		this.forfeitButton.addActionListener(this);
		this.restartButton.addActionListener(this);
		this.yesButton.addActionListener(this);
		this.noButton.addActionListener(this);
		this.undoButton.addActionListener(this);
	}

	/*
	 * Handle the cases where a GUI member is selected.
	 */
	public void actionPerformed(ActionEvent e) {
		if ("forfeit".equals(e.getActionCommand())) {
			controller.forfeit(player);
		} else if ("restart".equals(e.getActionCommand())) {
			controller.beginRestart(player);
		} else if ("no".equals(e.getActionCommand())) {
			controller.endRestart(false);
		} else if ("yes".equals(e.getActionCommand())) {
			controller.endRestart(true);
		} else if ("undo".equals(e.getActionCommand())) {
			controller.undoMove();
		}
	}

	/*
	 * Draw the GUI. Determine which buttons should be enabled and disabled.
	 */ 
	public void draw() {
		controller.setPlayerName(player, nameField.getText());
		
		switch (controller.getCurrGameState()) {
		case WAITING_FOR_START_POS_SELECTION:
		case WAITING_FOR_DESTINATION_POS_SELECTION:
			restartButton.setEnabled(true);
			forfeitButton.setEnabled(true);
			yesButton.setEnabled(false);
			noButton.setEnabled(false);

			if (controller.getCurrPlayersColor() != player.getColor()) {
				undoButton.setEnabled(true);
			} else {
				undoButton.setEnabled(false);
			}
			break;
		case GAME_END:
			restartButton.setEnabled(true);
			forfeitButton.setEnabled(false);
			yesButton.setEnabled(false);
			noButton.setEnabled(false);
			undoButton.setEnabled(false);
			break;
		case WAITING_FOR_RESTART_RESPONSE_BLACK:
			restartButton.setEnabled(false);
			forfeitButton.setEnabled(false);
			if (player.getColor() == ChessColor.WHITE) {
				yesButton.setEnabled(false);
				noButton.setEnabled(false);
			} else {
				yesButton.setEnabled(true);
				noButton.setEnabled(true);
			}
			undoButton.setEnabled(false);
			break;
		case WAITING_FOR_RESTART_RESPONSE_WHITE:
			restartButton.setEnabled(false);
			forfeitButton.setEnabled(false);
			if (player.getColor() == ChessColor.BLACK) {
				yesButton.setEnabled(false);
				noButton.setEnabled(false);
			} else {
				yesButton.setEnabled(true);
				noButton.setEnabled(true);
			}
			undoButton.setEnabled(false);
			break;
		}

		scoreLabel.setText("Score: " + player.getScore());
	}

	public JPanel getPanel() {
		return this.panel;
	}
}
