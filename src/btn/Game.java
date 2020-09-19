package btn;

import btn.chess.ChessModel;
import btn.controllers.ChessController;
import btn.views.ChessView;

public class Game {
    
	private ChessModel model;
    private ChessView view;
	private ChessController controller;

	private final int BOARD_WIDTH = 800;
	private final int BOARD_HEIGHT = 800;
	private final int WINDOW_WIDTH = 1200;
	private final int WINDOW_HEIGHT = 800;
    
	public Game() {
		model = new ChessModel();
		controller = new ChessController(model);
        view = new ChessView(controller, model,
							 WINDOW_WIDTH, WINDOW_HEIGHT,
							 BOARD_WIDTH, BOARD_HEIGHT);
		controller.setView(view);
		
		controller.mainLoop();
	}
	
	public static void main(String [] args) {
		new Game();
	}
}
