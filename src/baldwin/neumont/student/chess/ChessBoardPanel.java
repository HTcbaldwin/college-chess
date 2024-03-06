package baldwin.neumont.student.chess;

import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class ChessBoardPanel extends JPanel 
{	
	/**
	 * 
	 */
	
	private static final long serialVersionUID = 8495019079003902008L;
	private final int HEIGHT = 500;
	private final int WIDTH = 500;
	private final int CHESS_BOARD_SIZE = 8;
	private final char[] COLUMNS = {'a','b','c','d','e','f','g','h'}; //Indicates the
	private final String TAN = "#E6AF39"; //Color value for tan
	private final String BROWN = "#9C6A00"; // Color value for brown
	private final int NUMBER_OF_ROWS_COLUMNS = 8;
	private final String FILE = "test.txt";
	
	private Map<String, ChessSquare> squares; //contains all the chess squares and stores their position as the key
	private boolean isWhiteTurn = true;
	private String selectedPiece = "";
	private String kingInCheck = "";
	
	public ChessBoardPanel()
	{
		super();
		squares = new HashMap<String, ChessSquare>(); 
		setLayout(new GridLayout(CHESS_BOARD_SIZE,CHESS_BOARD_SIZE));
		setBounds(0,0,WIDTH,HEIGHT);
		setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		addButtons();
	}
	
	/**
	 * Adds the chess squares/buttons to the panel in alternating colors and adds them
	 * to the squares hash map with their key location
	 */
	public void addButtons()
	{	
		Boolean switchColor = true; //alternates the color
		
		for(int i = 8;i>0;i--)
		{
			for(int j = 0;j<NUMBER_OF_ROWS_COLUMNS;j++)
			{
				String text = COLUMNS[j] + "" + (i)  ; //give the button a row, column based designation
				ChessSquare x = new ChessSquare(text);
				x.setActionCommand(text);
				x.addActionListener(new ChessSquareListener());
				
				
				//Switches between the two defined colors on the board
				if(switchColor)
				{
					x.setOrigColor(Color.decode(TAN));
					switchColor = false;
				}
				else
				{
					x.setOrigColor(Color.decode(BROWN));
					switchColor = true;
				}
					
				add(x);
				squares.put(text, x); //adds button to hashmap
			}
			
			//alternates the color after a row is done to give a checker pattern
			if(switchColor)
			{
				switchColor = false;
			}
			else
			{
				switchColor = true;
			}
		}
		repaint();
	}
	
	/**
	 * Reads in moves from a file, determines their input type, and then moves
	 * the piece accordingly. 
	 */
	public void readMoves()
	{	
		MoveReader moveReader = new MoveReader();
		
		try 
		{
			BufferedReader reader = new BufferedReader(new FileReader(FILE)); //reads file
			
			while(reader.ready()) //while there are lines to go through
			{
				Thread.sleep(10);
				
				String input = reader.readLine();
				String moveType = moveReader.determineInput(input);
				input = input.toLowerCase();
				System.out.println(moveType);
				
					if(moveType.equalsIgnoreCase("position"))
					{
						placePiece(moveReader.positionInfo(input));
					}
					else if(moveType.equalsIgnoreCase("move"))
					{
						movePiece(moveReader.moveInfo(input), squares);
					}
					else if(moveType.equalsIgnoreCase("attack"))
					{
						attackPiece(moveReader.attackInfo(input), squares);
					}
				
			}
			reader.close(); //closes the stream
		} 
		catch (FileNotFoundException e) 
		{
			System.out.println("File was not found...");;
		} catch (IOException e) {

			System.out.println("File was read wrong or is corrupt.");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	
	/**
	 * Makes a piece attack the square given, but won't if the move is 
	 * invalid.
	 * @param info
	 */
	public boolean attackPiece(String[] info,  Map<String, ChessSquare> board)
	{	
		ChessPiece toMove = board.get(info[0]).getPiece();
		List<String> moves = toMove.getMoves(info[0], board); //gets the attacks from the piece and puts it in a list.
		boolean valid = false;
		
		if(checkMove(info[1], moves))
		{
			if(toMove.getColor().equalsIgnoreCase("white") && isWhiteTurn)
			{
				System.out.println("white move");
				board.get(info[0]).setPiece(ChessPiece.EMPTY);
				board.get(info[1]).setPiece(toMove);
				valid = true;
			}
			else if(toMove.getColor().equalsIgnoreCase("black") && !isWhiteTurn)
			{
				System.out.println("black move");
				board.get(info[0]).setPiece(ChessPiece.EMPTY);
				board.get(info[1]).setPiece(toMove);
				valid = true;
			}
		}
		else
		{
			System.out.println("Illegal Move");
		}
		
		return valid;
	}
	
	/**
	 * Makes the piece move to the square given, but will not if the move
	 * is invalid.
	 * @param info
	 */
	public boolean movePiece(String[] info, Map<String, ChessSquare> board)
	{
		ChessPiece toMove = board.get(info[0]).getPiece();
		List<String> moves = toMove.getMoves(info[0], board);
		boolean valid = false;
		if(toMove == ChessPiece.BLACK_KING || toMove == ChessPiece.WHITE_KING)
		{
			moves = getKingMoves(toMove, info[0]);
			if(checkMove(info[1], moves))
			{
				board.get(info[0]).setPiece(ChessPiece.EMPTY);
				board.get(info[1]).setPiece(toMove);
				valid = true;
			}
		}
		else if(checkMove(info[1], moves) && toMove.getColor().equalsIgnoreCase("white") && isWhiteTurn)
		{
			board.get(info[0]).setPiece(ChessPiece.EMPTY);
			board.get(info[1]).setPiece(toMove);
			valid = true;
		}
		else if(checkMove(info[1], moves) && toMove.getColor().equalsIgnoreCase("black") && !isWhiteTurn)
		{
			board.get(info[0]).setPiece(ChessPiece.EMPTY);
			board.get(info[1]).setPiece(toMove);
			valid = true;
		}
		else
		{
			System.out.println("Illegal Move");
		}	
		
		return valid;
	}
	
	/**
	 * Takes in a place piece move and add the correct piece to the board at the
	 * given location.
	 * @param info
	 */
	public void placePiece(String[] info)
	{
		if(info[0].equalsIgnoreCase("Black Pawn"))
		{
			squares.get(info[1].toLowerCase()).setPiece(ChessPiece.BLACK_PAWN);
		}
		else if(info[0].equalsIgnoreCase("Black Bishop"))
		{
			squares.get(info[1].toLowerCase()).setPiece(ChessPiece.BLACK_BISHOP);
		}
		else if(info[0].equalsIgnoreCase("Black Knight"))
		{
			squares.get(info[1].toLowerCase()).setPiece(ChessPiece.BLACK_KNIGHT);
		}
		else if(info[0].equalsIgnoreCase("Black Queen"))
		{
			squares.get(info[1].toLowerCase()).setPiece(ChessPiece.BLACK_QUEEN);
		}
		else if(info[0].equalsIgnoreCase("Black King"))
		{
			squares.get(info[1].toLowerCase()).setPiece(ChessPiece.BLACK_KING);
		}
		else if(info[0].equalsIgnoreCase("Black Rook"))
		{
			squares.get(info[1].toLowerCase()).setPiece(ChessPiece.BLACK_ROOK);
		}
		else if(info[0].equalsIgnoreCase("White Pawn"))
		{
			squares.get(info[1].toLowerCase()).setPiece(ChessPiece.WHITE_PAWN);
		}
		else if(info[0].equalsIgnoreCase("White Bishop"))
		{
			squares.get(info[1].toLowerCase()).setPiece(ChessPiece.WHITE_BISHOP);
		}
		else if(info[0].equalsIgnoreCase("White Knight"))
		{
			squares.get(info[1].toLowerCase()).setPiece(ChessPiece.WHITE_KNIGHT);
		}
		else if(info[0].equalsIgnoreCase("White Queen"))
		{
			squares.get(info[1].toLowerCase()).setPiece(ChessPiece.WHITE_QUEEN);
		}
		else if(info[0].equalsIgnoreCase("White King"))
		{
			squares.get(info[1].toLowerCase()).setPiece(ChessPiece.WHITE_KING);
		}
		else if(info[0].equalsIgnoreCase("White Rook"))
		{
			squares.get(info[1].toLowerCase()).setPiece(ChessPiece.WHITE_ROOK);
		}
	}
	
	/**
	 * Checks the kings moves against all the opposite team moves that could put the
	 * king in check and removes them. This doesn't allow the king to put himself in check.
	 * 
	 * @param toMove
	 * @param info
	 * @return
	 */
	private List<String> getKingMoves(ChessPiece toMove, String info)
	{
		Map<String, ChessSquare> clone = cloneBoard();
		List<String> moves = toMove.getMoves(info, clone);
		List<String> opposingMoves = new ArrayList<String>();
		List<String> toRemove = new ArrayList<String>();
		
		for(ChessSquare x: squares.values())
		{
				if(!isWhiteTurn && toMove.getColor().equals("black"))
				{
					if(x.getPiece().getColor().equals("white"))
					{
						List<String> unaddedMoves = x.getPiece().getMoves(x.getSquareLocation(), squares);
						for(String move: unaddedMoves)
						{
							opposingMoves.add(move);
						}
					}
				}
				else if(isWhiteTurn && toMove.getColor().equals("white"))
				{
					if(x.getPiece().getColor().equals("black"))
					{
						List<String> unaddedMoves = x.getPiece().getMoves(x.getSquareLocation(), squares);
						for(String move: unaddedMoves)
						{
							opposingMoves.add(move);
						}
					}
				}
		}
		
		for(String oppMove : opposingMoves)
		{	
			for(String move : moves)
			{
				if(oppMove.equalsIgnoreCase(move))
				{
					toRemove.add(move);
				}
			}
		}
		
		for(String remove: toRemove)
		{
			moves.remove(remove);
		}
		
		return moves;
	}
	
	/**
	 * Clones the board for move calculations and returns a simple clone.
	 * 
	 * @return
	 */
	private Map<String, ChessSquare> cloneBoard()
	{
		Map<String, ChessSquare> temp = new HashMap<String, ChessSquare>();
		
		for(String key: squares.keySet())
		{
			ChessSquare x = new ChessSquare(key);
			x.setPiece(squares.get(key).getPiece());
			x.setOrigColor(squares.get(key).getOrigColor());
			temp.put(key, x);
		}
		
		return temp;
	}
	
	/**
	 * Checks to determine if the given move is one of the moveset.
	 * 
	 * @param moveToMake
	 * @param moveSet
	 * @return
	 */
	private boolean checkMove(String moveToMake, List<String> moveSet)
	{
		boolean isValid = false;
		
		Iterator<String> iterator = moveSet.iterator();
		
		while(iterator.hasNext()) //loops through the attacks list to see if the move valid.
		{
			if(moveToMake.equalsIgnoreCase(iterator.next()))
			{
				isValid = true;
				break;
			}
		}
		
		return isValid;
	}
	
	/**
	 * Highlights a square for an available move.
	 * @param tileLocation
	 */
	private void highlightMoves(List<String> moveSet)
	{
		Iterator<String> iterator = moveSet.iterator();
		
		while (iterator.hasNext())
		{
			squares.get(iterator.next()).setBackground(Color.green);
		}
	}
	
	/**
	 * Highlights when the king is in check.
	 * 
	 * @param location
	 */
	private void checkHighlight(String location)
	{
		if(!location.equals(""))
		{
			squares.get(location).setBackground(Color.red);
		}
	}
	
	/**
	 * Resets the board squares to their original color.
	 */
	private void boardColorReset()
	{
			for(String key: squares.keySet())
			{
				squares.get(key).setBackground(squares.get(key).getOrigColor());
			}
	}
	
	private List<String> filterPieceMoves(List<String> moves, String startPos)
	{
		List<String> filteredMoves = new ArrayList<String>();
		
		for(String move: moves)
		{
			String[] moveInfo = new String[2];
			moveInfo[0] = startPos;
			moveInfo[1] = move;
			Map<String, ChessSquare> clone = cloneBoard();
			movePiece(moveInfo, clone);
			if(!checkForCheck(clone, true))
			{
				filteredMoves.add(move);
			}
		}
		
		return filteredMoves;
	}
	
	/**
	 * Checks to see if a king is in check and highlights that king. 
	 * 
	 * @param board
	 * @param isClone
	 * @return
	 */
	private boolean checkForCheck(Map<String, ChessSquare> board, boolean isClone)
	{
		System.out.println(isWhiteTurn);
		boolean check = false;
		
		kingInCheck = "";
		
			outerLoop:
			for(ChessSquare x: board.values())
			{
				if(isWhiteTurn && x.getPiece().getColor().equalsIgnoreCase("black"))
				{
					List<String> moves = x.getPiece().getMoves(x.getSquareLocation(), board);
					
					for(String move: moves)
					{
						if(board.get(move).getPiece() == ChessPiece.WHITE_KING)
						{
							System.out.println("white King in Check" + " " + move);
							if(!isClone)
							{
								kingInCheck = move;	
							}
							check = true;
							checkHighlight(move);
							break outerLoop;
						}
					}
				}
				else if(!isWhiteTurn && x.getPiece().getColor().equalsIgnoreCase("white"))
				{
					List<String> moves = x.getPiece().getMoves(x.getSquareLocation(), board);
					
					for(String move: moves)
					{
						if(board.get(move).getPiece() == ChessPiece.BLACK_KING)
						{
							System.out.println("black King in Check");
							if(!isClone)
							{
								kingInCheck = move;								
							}
							check = true;
							checkHighlight(move);
							break outerLoop;
						}
					}
				}
			}
		return check;
		}
	
	/**
	 * Check all of the current players moves to see if the king can be taken out of check.
	 * If the king can be taken out of check on the clone board, the move is added to a list.
	 * If that list has less than one value, checkmate baby.
	 * 
	 * @return
	 */
	private boolean checkForMate()
	{
		boolean checkmate = false;
		String color = (isWhiteTurn)?"white":"black";
		
		if(!isWhiteTurn && checkForCheck(squares, false))
		{
			List<String> moves = new ArrayList<String>(); //moves of all the players pieces
			
				for(ChessSquare x : squares.values()) //loops through all chess squares
				{
					if(x.getPiece().getColor().equalsIgnoreCase(color)) //makes sure the piece is black
					{
						List<String> uncheckedMoves = x.getPiece().getMoves(x.getSquareLocation(), squares); //get the pieces moves
						
						for(String y: filterPieceMoves(uncheckedMoves, x.getSquareLocation())) //gets the moves that can be made to take king out of check
						{
							moves.add(y); //add previous moves to list
						}
					}
				}
				
			 if(moves.size() < 1) // if list is less than one, checkmate
			 {
				 checkmate = true;
			 }
		}
		
		return checkmate;
	}
	
	/**
	 * Moves pieces based on mouse clicks and user input.
	 * 
	 * @param e
	 */
	private void mouseMovePiece(ActionEvent e)
	{
		if(!e.getSource().toString().equalsIgnoreCase(selectedPiece))
		{
			if(isWhiteTurn && selectedPiece.equals("") && squares.get(e.getSource().toString()).getPiece().getColor().equalsIgnoreCase("white")) //determines if piece is white and it is whites turn
			{
				selectedPiece = e.getSource().toString();
				if(squares.get(selectedPiece).getPiece() == ChessPiece.WHITE_KING)
				{
					highlightMoves(getKingMoves(squares.get(selectedPiece).getPiece(), selectedPiece));
				}
				else
				{
					List<String> moves = squares.get(e.getSource().toString()).getPiece().getMoves(e.getSource().toString(), squares);
					List<String> filteredMoves = filterPieceMoves(moves, e.getSource().toString());
					highlightMoves(filteredMoves);
				}
				checkHighlight(kingInCheck);
			}
			else if(selectedPiece.equals("") && squares.get(e.getSource().toString()).getPiece().getColor().equalsIgnoreCase("black") && !isWhiteTurn) //determines if piece is white and it is whites turn
			{
				selectedPiece = e.getSource().toString();
				if(squares.get(selectedPiece).getPiece() == ChessPiece.BLACK_KING)
				{
					highlightMoves(getKingMoves(squares.get(selectedPiece).getPiece(), selectedPiece));
				}
				else
				{
					List<String> moves = squares.get(e.getSource().toString()).getPiece().getMoves(e.getSource().toString(), squares);
					List<String> filteredMoves = filterPieceMoves(moves, e.getSource().toString());
					highlightMoves(filteredMoves);
				}
				checkHighlight(kingInCheck);
			}
			else if(!e.getSource().toString().equalsIgnoreCase(selectedPiece) && !selectedPiece.equals("")) //determines if the next selected square isn't the same square
			{
				if(squares.get(e.getSource().toString()).isEmpty())
				{
					String[] info = new String[2];
					info[0] = selectedPiece;
					info[1] = e.getSource().toString();
					Map<String, ChessSquare> clone = cloneBoard();
					movePiece(info, clone);
					if(!checkForCheck(clone, true))
					{
						if(movePiece(info, squares))
						{
							isWhiteTurn = !isWhiteTurn;
						}
					}
					selectedPiece = "";
				}
				else if(!squares.get(e.getSource().toString()).isEmpty())
				{
					String[] info = new String[2];
					info[0] = selectedPiece;
					info[1] = e.getSource().toString();
					Map<String, ChessSquare> clone = cloneBoard();
					attackPiece(info, clone);
					if(!checkForCheck(clone, true))
					{
						if(attackPiece(info, squares))
						{							
							isWhiteTurn = !isWhiteTurn;
						}
					}
					selectedPiece = "";
				}
				else
				{
					selectedPiece = "";
				}
			}	
			else
			{
				System.out.println("It is not your turn");
			}
		}
		else if(e.getSource().toString().equalsIgnoreCase(selectedPiece)) // if it is the same square as the piece, deselects the piece.
		{
			selectedPiece = "";
		}
	}	
	
	/**
	 * @author cbaldwin
	 * Defines a ActionListener for each chess square that will eventually respond to a click event.
	 */
	public class ChessSquareListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			boardColorReset();
			mouseMovePiece(e);
			checkForCheck(squares, false);
			if(checkForMate())
			{
				if(isWhiteTurn)
				{					
					JOptionPane.showMessageDialog(null, "!!!--White in CHECKMATE--!!!");
				}
				else if(!isWhiteTurn)
				{
					JOptionPane.showMessageDialog(null, "!!!--Black in CHECKMATE--!!!");
				}
				System.exit(0);
			}
		}
	}
}