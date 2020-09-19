package btn.views;

import java.awt.image.BufferedImage;
import btn.utils.BufferedImageLoader;
import btn.chess.*;
import java.awt.Graphics2D;

public class PiecesView {

	private BufferedImage whitePawnImg;
    private BufferedImage whiteKnightImg;
    private BufferedImage whiteRookImg;
    private BufferedImage whiteBishopImg;
    private BufferedImage whiteKingImg;
    private BufferedImage whiteQueenImg;
    private BufferedImage whiteCentaurImg;
	private BufferedImage whiteVanguardImg;
	
    private BufferedImage blackPawnImg;
    private BufferedImage blackKnightImg;
    private BufferedImage blackRookImg;
    private BufferedImage blackBishopImg;
    private BufferedImage blackKingImg;
    private BufferedImage blackQueenImg;
	private BufferedImage blackCentaurImg;
	private BufferedImage blackVanguardImg;
	

	public PiecesView(BufferedImageLoader loader) {
		loadImages(loader);
	}

	/*
	 * Load all the images for the chess pieces.
	 * @param loader: BufferedImageLoader to use to load the images
	 */
	public void loadImages(BufferedImageLoader loader) {
		whitePawnImg = loader.loadImage("/assets/white_pawn.png");
		blackPawnImg = loader.loadImage("/assets/black_pawn.png");
		whiteKnightImg = loader.loadImage("/assets/white_knight.png");
		blackKnightImg = loader.loadImage("/assets/black_knight.png");
		whiteRookImg = loader.loadImage("/assets/white_rook.png");
		blackRookImg = loader.loadImage("/assets/black_rook.png");
		whiteBishopImg = loader.loadImage("/assets/white_bishop.png");
		blackBishopImg = loader.loadImage("/assets/black_bishop.png");
		whiteKingImg = loader.loadImage("/assets/white_king.png");
		blackKingImg = loader.loadImage("/assets/black_king.png");
		whiteQueenImg = loader.loadImage("/assets/white_queen.png");
		blackQueenImg = loader.loadImage("/assets/black_queen.png");
		whiteVanguardImg = loader.loadImage("/assets/white_vanguard.png");
		blackVanguardImg = loader.loadImage("/assets/black_vanguard.png");
		whiteCentaurImg = loader.loadImage("/assets/white_centaur.png");
		blackCentaurImg = loader.loadImage("/assets/black_centaur.png");
	}

	/*
	 * Draw a piece.
	 * @param graphics: Graphics2D object to write the image out to
	 * @param p: Piece to draw
	 * @param cellWidth: width of a cell in pixels
	 * @param cellHeight: height of a cell in pixels
	 */
	public void drawPiece(Graphics2D graphics, Piece p, int cellWidth, int cellHeight) {
		if (p == null) {
			return;
		}
		
        BufferedImage img = null;
        if (p instanceof King) {
            img = p.getColor() == ChessColor.WHITE ? whiteKingImg : blackKingImg;
        } else if (p instanceof Queen) {
            img = p.getColor() == ChessColor.WHITE ? whiteQueenImg : blackQueenImg;
        } else if (p instanceof Knight) {
            img = p.getColor() == ChessColor.WHITE ? whiteKnightImg : blackKnightImg;
        } else if (p instanceof Rook) {
            img = p.getColor() == ChessColor.WHITE ? whiteRookImg : blackRookImg;
        } else if (p instanceof Bishop) {
			img = p.getColor() == ChessColor.WHITE ? whiteBishopImg : blackBishopImg;
        } else if (p instanceof Pawn) {
            img = p.getColor() == ChessColor.WHITE ? whitePawnImg : blackPawnImg;
        } else if (p instanceof Centaur) {
			img = p.getColor() == ChessColor.WHITE ? whiteCentaurImg : blackCentaurImg;
		} else if (p instanceof Vanguard) {
			img = p.getColor() == ChessColor.WHITE ? whiteVanguardImg : blackVanguardImg;
		}
        if (img != null) {
			int x = p.getX() * cellWidth;
			int y = p.getY() * cellHeight;
            graphics.drawImage(img, x, y, cellWidth, cellHeight, null);
        }
    }
}
