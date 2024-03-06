package baldwin.neumont.student.chess;

import javax.swing.JFrame;

public class ChessFrame extends JFrame
{

	private static final long serialVersionUID = 2845795500194629490L;
	private final int FRAME_HEIGHT = 600; //Defines JFrame Height
	private final int FRAME_WIDTH = 800;  //Defines JFrame Width
	private final int FRAME_START_POS = 40; // Starts the application window down and right 25 px from upper left corner.
	private ChessPanel panel;
	
	public ChessFrame()
	{
		super("Chess");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(FRAME_START_POS,FRAME_START_POS, FRAME_WIDTH, FRAME_HEIGHT);
		setVisible(true);
		panel = new ChessPanel();
		setContentPane(panel);
		setResizable(false);
	}
	
	public void readMoves()
	{
		panel.readMoves();
	}
}
