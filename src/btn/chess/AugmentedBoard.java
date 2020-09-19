package btn.chess;

import btn.utils.Vector2i;

public class AugmentedBoard extends StandardBoard {

	public AugmentedBoard() {
		super();

		super.addPiece(new Centaur(ChessColor.WHITE),  new Vector2i(4, 2));
		super.addPiece(new Vanguard(ChessColor.WHITE), new Vector2i(5, 2));
		super.addPiece(new Centaur(ChessColor.BLACK),  new Vector2i(4, 5));
		super.addPiece(new Vanguard(ChessColor.BLACK), new Vector2i(5, 5));
	}
}
