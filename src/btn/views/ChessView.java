package btn.views;

import javax.swing.*;
import btn.chess.Board;
import btn.chess.ChessModel;

import java.awt.*;
import java.awt.image.BufferedImage;
import btn.utils.BufferedImageLoader;
import btn.controllers.ChessController;

public class ChessView {
    
    private JFrame frame;
	private JSplitPane splitPane;
	private BufferedImageLoader loader;

	private ChessController controller;
	private ChessModel model;
	
    private BoardView boardView;
	private UserOptionsView userOptionsView;
	private GameStateView gameStateView;

    public ChessView(ChessController controller,
					 ChessModel model,
					 int windowWidth, int windowHeight,
					 int boardWidth, int boardHeight) {
		this.loader = new BufferedImageLoader();
		this.controller = controller;
		this.model = model;
		
		createSubviews(boardWidth, boardHeight);
		createGUI(windowWidth, windowHeight, boardWidth, boardHeight);
    }

	/*
	 * Create views inside in the window
	 * @param boardWidth: width of the board
	 * @param boardHeight: height of the board
	 */
	private void createSubviews(int boardWidth, int boardHeight) {
		this.boardView = new BoardView(loader, controller,
									   boardWidth, boardHeight);
		this.userOptionsView = new UserOptionsView(controller);
		this.gameStateView = new GameStateView();
	}

	/*
	 * Create the GUI
	 * @param windowWidth: width of the window to make
	 * @param windowHeight: height of the window to make
	 * @param boardWidth: width of the board
	 * @param boardHeight: height of the board
	 */
	private void createGUI(int windowWidth, int windowHeight,
						   int boardWidth, int boardHeight) {

		JSplitPane leftPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT,
											 gameStateView.getPanel(),
											 userOptionsView.getPanel());
		leftPane.setOneTouchExpandable(false);
		leftPane.setDividerLocation(boardHeight / 2);
											 
		splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
								   boardView.getPanel(),
								   leftPane);

		splitPane.setOneTouchExpandable(false);
		splitPane.setDividerLocation(boardWidth);
		splitPane.setPreferredSize(new Dimension(windowWidth, windowHeight));
		
        frame = new JFrame("Chess");
        frame.setSize(windowWidth, windowHeight);
        frame.setContentPane(splitPane);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.pack();
        frame.setVisible(true);
	}

	/*
	 * Draw the Chess GUI
	 */
    public void draw() {
        boardView.draw(model.getBoard());
		userOptionsView.draw();
    }
    
    public JFrame getWindow() {
        return frame;
    }

	public GameStateView getGameStateView() {
		return gameStateView;
	}
}
