package btn.views;

import btn.chess.*;
import btn.controllers.ChessController;
import btn.utils.Vector2i;
import btn.utils.BufferedImageLoader;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class BoardView {

	private ChessController controller;

	private JPanel panel;
	private PiecesView piecesView;

    private BufferedImage bg;
    private Graphics2D graphics;
    private int boardWidth;
    private int boardHeight;

	private BoardViewMouseListener mouseListener;
    
    private BufferedImage whiteCellImg;
    private BufferedImage blackCellImg;
	private BufferedImage selectedCellImg;
	private BufferedImage invalidCellImg;

    public BoardView(BufferedImageLoader loader, ChessController controller,
					 int width, int height) {
		this.controller = controller;
        this.boardWidth = width;
        this.boardHeight = height;

        createPanel(width, height);
		this.piecesView = new PiecesView(loader);
		loadImages(loader);
        createGraphics();
    }

	/*
	 * Create the JPanel attributes in which the graphics lie
	 * @param width: width of the Board
	 * @param height: height of the Board
	 */
    private void createPanel(int width, int height) {
		this.panel = new JPanel();
		this.panel.setPreferredSize(new Dimension(width, height));
		this.panel.setMinimumSize(new Dimension(width, height));
        this.panel.setFocusable(true);
        this.panel.requestFocus();

		mouseListener = new BoardViewMouseListener(controller);
		this.panel.addMouseListener(mouseListener);
    }

	/*
	 * Load the images relevant to the Board
	 * @param loader: BufferedImageLoader to use when loading images
	 */
	private void loadImages(BufferedImageLoader loader) {
		whiteCellImg = loader.loadImage("/assets/white_cell.png");
		blackCellImg = loader.loadImage("/assets/black_cell.png");
		selectedCellImg = loader.loadImage("/assets/selected_cell.png");
		invalidCellImg = loader.loadImage("/assets/invalid_cell.png");
	}

	/*
	 * Create an image to use as a buffer to draw images to.
	 */
    private void createGraphics() {
        bg = new BufferedImage(boardWidth, boardHeight, BufferedImage.TYPE_INT_RGB);
        graphics = (Graphics2D) bg.getGraphics();
    }

	/*
	 * Draw what is on the buffer image to the screen
	 */
    private void drawToScreen() {
        Graphics g2 = this.panel.getGraphics();
		g2.drawImage(bg, 0, 0, boardWidth, boardHeight, null);
		g2.dispose();
    }
    
	/*
	 * Draw the board with all the pieces in their proper places.
	 */
    public void draw(Board board) {
        boolean white = true;
		
        int cellWidth = this.boardWidth / board.getWidth();
        int cellHeight = this.boardHeight / board.getHeight();
		mouseListener.setCellWidth(cellWidth);
		mouseListener.setCellHeight(cellHeight);
		        
        for (int row = 0; row < board.getHeight(); row++) {
            for (int col = 0; col < board.getWidth(); col++) {
				drawCellImg(col, row, cellWidth, cellHeight, white);
                white = !white;
				piecesView.drawPiece(graphics, board.getPieceAt(new Vector2i(col, row)), 
									 cellWidth, cellHeight);
            }
            white = !white;
        }
        drawToScreen();
    }

	/*
	 * Draw the specific cell
	 * @param col: column of the cell to draw
	 * @param row: row of the cell to draw
	 * @param cellWidth: width of the cell
	 * @param cellHeight: height of the cell
	 * @param white: boolean specifying whether the current cell is white or not.
	 */
	private void drawCellImg(int col, int row, int cellWidth, int cellHeight, boolean white) {
		Vector2i selectedCell = controller.getSelectedSpace();
		Vector2i invalidCell = controller.getInvalidSpace();
		BufferedImage cellImg;
		if (invalidCell.getX() == col && invalidCell.getY() == row) {
			cellImg = invalidCellImg;
		} else if (selectedCell.getX() == col && selectedCell.getY() == row) {
			cellImg = selectedCellImg;
		} else {
			cellImg = white ? whiteCellImg : blackCellImg;
		}

		graphics.drawImage(cellImg, 
						   col * cellWidth, row * cellHeight,
						   cellWidth, cellWidth,
						   null);
	}

	public JPanel getPanel() {
		return this.panel;
	}
}
