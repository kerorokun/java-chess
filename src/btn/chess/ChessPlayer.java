package btn.chess;

public class ChessPlayer {

	private int score;
	private ChessColor color;
	private String name;

	public ChessPlayer(ChessColor color, String name) {
		this.color = color;
		this.score = 0;
		this.name = name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
	
	public void incrementScore() {
		score++;
	}

	public int getScore() {
		return score;
	}

	public ChessColor getColor() {
		return color;
	}
}
