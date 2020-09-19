package btn.views;

import java.awt.*;
import javax.swing.*;
import javax.swing.text.DefaultCaret;

public class GameStateView {

	private JPanel panel;
	private BoxLayout layout;
	private JScrollPane scrollPane;
	private JTextArea textArea;
	private JLabel stateLabel;
	
	public GameStateView() {
		this.textArea = new JTextArea();
		this.textArea.setEditable(false);
		DefaultCaret caret = (DefaultCaret)textArea.getCaret();
		caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
		
		this.stateLabel = new JLabel("Current game state: ");
		this.scrollPane = new JScrollPane(textArea);

		this.panel = new JPanel();
		this.panel.add(stateLabel);
		this.panel.add(scrollPane);
		
		this.layout = new BoxLayout(panel, BoxLayout.Y_AXIS);
		this.panel.setLayout(layout);
	}

	/*
	 * Append a string to the output textarea
	 */
	public void appendOutput(String output) {
		textArea.append(output + "\n");
	}

	public JPanel getPanel() {
		return this.panel;
	}
}
