package baldwin.neumont.student.chess;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author cbaldwin
 * Class read input from a file and prints out chess piece
 * positions, moves, attacks and castles.
 */
public class MoveReader 
{
	public final String FILE = "test.txt"; //File to be read in
	private final int PIECE_DETERMINER = 1;
	private final int PIECE_POS = 2;
	private final int INITIAL_POS = 1;
	private final int FINISH_POS = 2;
	private final int INITIAL_POS_2 = 3;
	private final int FINISH_POS_2 = 4;
	
	public MoveReader()
	{
	}
	
	/**
	 * Opens a buffered reader and reads line by line the values of 
	 * each move and then prints the piece, its team and an appropriate message
	 * for position, move, attacks or castles. 
	 */
	public String readFile()
	{
		try 
		{
			BufferedReader reader = new BufferedReader(new FileReader(FILE)); //reads file
			
			while(reader.ready()) //while there are lines to go through
			{
				return reader.readLine();
			}
			
			reader.close(); //closes the stream
		} 
		catch (FileNotFoundException e) 
		{
			System.out.println("File was not found...");;
		} catch (IOException e) {

			System.out.println("File was read wrong or is corrupt.");
		}
		return "";
	}
	
	/**
	 * Takes in the code given by the file and determines what kind of "move"
	 * was made and returns it as a string.
	 * 
	 * @param input
	 * @return
	 */
	public String determineInput(String input)
	{
		String move = "";
	
		if(!positionInfo(input)[0].equals("") && !positionInfo(input)[1].equals("")) // matches on positions
		{
			move = "position";
		}
		else if(!moveInfo(input)[0].equals("") && !moveInfo(input)[1].equals("")) // matches on moves
		{
			move = "move";
		}
		else if(!attackInfo(input).equals("")) //matches on attacks
		{
			move = "attack";
		}
		else if(!castleInfo(input).equals("")) //matches on a castle
		{
			move = "castle";
		}
		
		return move;
	}
	
	public String castleInfo(String code)
	{
		String regex = "(?i)([a-h][1-8])\\s([a-h][1-8])\\s([a-h][1-8])\\s([a-h][1-8])"; //changes regex for new match
		Pattern pat =  Pattern.compile(regex);
		Matcher match = pat.matcher(code);
		
		String info = "";
		
		if(match.matches())
		{
			info += "King at " + match.group(INITIAL_POS) + " has moved to " + match.group(FINISH_POS) + " to be castled by Rook at " + match.group(INITIAL_POS_2) + " which moved to " + match.group(FINISH_POS_2);
		}
		
		return info;
	}
	
	public String[] attackInfo(String code)
	{
		String regex = "(?i)([a-h][1-8])\\s([a-h][1-8])\\*";  //changes regex for new match
		Pattern pat =  Pattern.compile(regex);
		Matcher match = pat.matcher(code);
		
		String[] info = new String[2];
		info[0] = "";
		info[1] = "";
		
		if(match.matches())
		{
			info[0] = match.group(INITIAL_POS);
			info[1]	= match.group(FINISH_POS);
		}
		
		return info;
	}
	
	public String[] moveInfo(String code)
	{
		String regex = "(?i)([a-h][1-8])\\s([a-h][1-8])"; 
		Pattern pat =  Pattern.compile(regex);
		Matcher match = pat.matcher(code);
		
		String[] info = new String[2];
		info[0] = "";
		info[1] = "";
		
		if(match.matches())
		{
			info[0] = match.group(INITIAL_POS);
			info[1] = match.group(FINISH_POS);
		}
		
		return info;
	}
	
	public String[] positionInfo(String code)
	{
		String regex = "(?i)([qkpbrn][ld])([a-h][1-8])";
		Pattern pat = Pattern.compile(regex);
		Matcher match = pat.matcher(code);
		
		String[] info = new String[2];
		info[0] = "";
		info[1] = "";
		
		if(match.matches())
		{
			info[0] = getPiece(match.group(PIECE_DETERMINER));
			info[1] = match.group(PIECE_POS);
		}
		
		return info;
	}
	
	/**
	 * Takes in the code that determines a players piece and team and returns
	 * the piece and its team.
	 * 
	 * @param code
	 * @return
	 */
	private String getPiece(String code)
	{
		String piece = ""; 
		
		//Color determiner
		if(code.charAt(1) == 'l' || code.charAt(1) == 'L') //checks to see if piece is light
		{
			piece += "White ";
		}
		else if(code.charAt(1) == 'd' || code.charAt(1) == 'D')// checkes to see if piece is dark
		{
			piece += "Black ";
		}

		//Piece determiner
		if(code.charAt(0) == 'p' || code.charAt(0) == 'p' ) 
		{
			piece += "Pawn";
		}
		else if(code.charAt(0) == 'R' || code.charAt(0) == 'r')
		{
			piece += "Rook";
		}
		else if(code.charAt(0) == 'B' || code.charAt(0) == 'b')
		{ 
			piece += "Bishop";
		}
		else if(code.charAt(0) == 'N' || code.charAt(0) == 'n')
		{
			piece += "Knight";
		}
		else if(code.charAt(0) == 'Q' || code.charAt(0) == 'q')
		{
			piece += "Queen";
		}
		else if(code.charAt(0) == 'K' || code.charAt(0) == 'k')
		{
			piece += "King";
		}
		
		return piece; // returns the piece and team
	}
}
