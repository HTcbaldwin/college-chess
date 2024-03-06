package baldwin.neumont.student.chess;

import java.awt.Color;

import javax.swing.JButton;

public class ChessSquare extends JButton
{
	

	/**
	 * 
	 */
	private static final long serialVersionUID = -4213184766748175525L;
	private String squareLocation;
	private Color origColor;
	private ChessPiece piece; //Defines the piece that is on this square.

	public ChessSquare(String _squareLocation)
	{
		super();
		piece = ChessPiece.EMPTY;
		setSquareLocation(_squareLocation);
	}
	
	public boolean isEmpty()
	{
		boolean isEmpty = false;
		
		if(piece.getPiece().equalsIgnoreCase("empty"))
		{
			isEmpty = true;
		}
		
		return isEmpty;
	}
	
	public ChessPiece getPiece() {
		return piece;
	}

	public void setPiece(ChessPiece piece) {
		this.piece = piece;
		this.setIcon(piece.getImage());
	}

	public void setSquareLocation(String squareLocation) {
		this.squareLocation = squareLocation;
	}

	public String getSquareLocation() {
		return squareLocation;
	}
	
	public String toString()
	{
		return squareLocation;
	}

	public Color getOrigColor() {
		return origColor;
	}

	public void setOrigColor(Color origColor) {
		this.origColor = origColor;
		setBackground(origColor);
	}
}
