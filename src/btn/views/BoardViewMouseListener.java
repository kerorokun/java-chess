package btn.views;

import javax.swing.*;
import btn.controllers.ChessController;

import btn.utils.Vector2i;
import btn.chess.ChessState;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class BoardViewMouseListener implements MouseListener {

	private ChessController controller;
	private int cellWidth;
	private int cellHeight;
	
	public BoardViewMouseListener(ChessController controller) {
		this.controller = controller;
	}

	@Override
	public void mousePressed(MouseEvent e) {

	}

	@Override
	public void mouseReleased(MouseEvent e) {

	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}

	public void setCellWidth(int width) {
		this.cellWidth = width;
	}

	public void setCellHeight(int height) {
		this.cellHeight = height;
	}

	/*
	 * Handle the mouse clicking on a cell on the board.
	 * @param e: MouseEvent specifying information about the event
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
		Vector2i mouseCellPos = new Vector2i(e.getX() / cellWidth,
											 e.getY() / cellHeight);
		
		switch(controller.getCurrGameState()) {
		case WAITING_FOR_START_POS_SELECTION:
			controller.attemptSpaceSelect(mouseCellPos);
			break;
		case WAITING_FOR_DESTINATION_POS_SELECTION:
			controller.attemptMove(mouseCellPos);
			break;
		default:
			break;
		}
	}
}
