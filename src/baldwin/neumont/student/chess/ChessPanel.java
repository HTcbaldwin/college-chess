package baldwin.neumont.student.chess;

import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class ChessPanel extends JPanel
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3224811815939896379L;
	private final int WIDTH = 800;
	private final int HEIGHT = 600;
	private final int EDGE_SPACING = 20;
	private final String[] COLUMNS = {"A", "B", "C", "D", "E", "F", "G", "H"};
	private final String[] ROWS = {"8", "7", "6", "5", "4", "3", "2", "1"};
	private final int CHESS_SQUARE_SIZE = 61;
	private ChessBoardPanel board;
	
	public ChessPanel()
	{
		super();
		setLayout(null);
		setBounds(0,0,WIDTH,HEIGHT);
		setBackground(Color.black);
		board = new ChessBoardPanel();
		board.setLocation(EDGE_SPACING, EDGE_SPACING);
		add(board);
		placeLabels();
	}
	
	private void placeLabels()
	{
		for(int i = 0;i<COLUMNS.length;i++)
		{
			JLabel label = new JLabel(COLUMNS[i]);
			label.setBounds((CHESS_SQUARE_SIZE * (i+1)), 5, 10, 10);
			label.setForeground(Color.white);
			add(label);
		}
		
		for(int i = 7;i>-1;i--)
		{
			JLabel label = new JLabel(ROWS[i]);
			label.setBounds(5,(CHESS_SQUARE_SIZE * (i+1)), 10, 10);
			label.setForeground(Color.white);
			add(label);
		}
	}
	
	public void readMoves()
	{
		board.readMoves();
	}
}
