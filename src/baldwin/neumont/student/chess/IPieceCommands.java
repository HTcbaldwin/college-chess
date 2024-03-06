package baldwin.neumont.student.chess;

import java.util.List;
import java.util.Map;

public interface IPieceCommands
{
	/**
	 * Takes in the pieces position and calculates the moves for a given piece. Each move
	 * is checked against the board to make sure it is valid. Then the moves list is returned.
	 * 
	 * @param position
	 * @param board
	 * @return
	 */
	public List<String> getMoves(String position,  Map<String, ChessSquare> board);
}
