package btn.chess;

import btn.utils.Vector2i;
import java.lang.Math;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

public class Vanguard extends Piece {

	private List<Vector2i> validDisplacements;
	
    public Vanguard(ChessColor color) {
        super(color);

		validDisplacements = new ArrayList<Vector2i>(
			Arrays.asList(
				new Vector2i(-2, 2),
				new Vector2i(-2, -2),
				new Vector2i(-2, 0),
				new Vector2i(2, 2),
				new Vector2i(2, -2),
				new Vector2i(2, 0),
				new Vector2i(0, 2),
				new Vector2i(0, -2)
				)
			);
    }
    
    @Override
	public boolean isMoveValid(Board board, Vector2i newPos) {
		if (!super.isMoveValid(board, newPos)) {
			return false;
		}

		Vector2i displacement = newPos.subtract(this.position);
		boolean isValidDisp = false;
		for (Vector2i validDisplacement : validDisplacements) {
			if (validDisplacement.equals(displacement)) {
				isValidDisp = true;
				break;
			}
		}

		if (!isValidDisp) {
			return false;
		}

		return !isObstacleInStraightLine(board, newPos);
    }
}
