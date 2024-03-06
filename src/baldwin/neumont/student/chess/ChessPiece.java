package baldwin.neumont.student.chess;

import java.util.ArrayList;

import java.util.List;
import java.util.Map;


import javax.swing.ImageIcon;

public enum ChessPiece implements IPieceCommands
{
	EMPTY("none", "empty", "") {
		
		@Override
		public List<String> getMoves(String _position, Map<String, ChessSquare> board) {
			return new ArrayList<String>();
		}
	},
	BLACK_KNIGHT("black", "knight", "black_knight.png"){
		@Override
		public List<String> getMoves(String _position, Map<String, ChessSquare> board) {
			List<String> moves = new ArrayList<String>();
			
			String northWest = "";
			northWest += (char)(_position.charAt(0) + 2);
			northWest += Character.getNumericValue(_position.charAt(1) - 1);
			
			if(isInBounds(northWest))
			{
				if(checkAttack(getColor(), board.get(northWest).getPiece()))
				{
					moves.add(northWest);
				}
			}
			
			String westNorth = "";
			westNorth += (char)(_position.charAt(0) + 1);
			westNorth += Character.getNumericValue(_position.charAt(1) - 2);
			
			if(isInBounds(westNorth))
			{
				if(checkAttack(getColor(), board.get(westNorth).getPiece()))
				{
					moves.add(westNorth);
				}
			}
			
			String westSouth = "";
			westSouth += (char)(_position.charAt(0) - 1);
			westSouth += Character.getNumericValue(_position.charAt(1) - 2);
			
			if(isInBounds(westSouth))
			{
				if(checkAttack(getColor(), board.get(westSouth).getPiece()))
				{
					moves.add(westSouth);
				}
			}
			
			String southWest = "";
			southWest += (char)(_position.charAt(0) - 2);
			southWest += Character.getNumericValue(_position.charAt(1) - 1);
			
			if(isInBounds(southWest))
			{
				if(checkAttack(getColor(), board.get(southWest).getPiece()))
				{
					moves.add(southWest);
				}
			}
			
			String southEast = "";
			southEast += (char)(_position.charAt(0) - 2);
			southEast += Character.getNumericValue(_position.charAt(1) + 1);
			
			if(isInBounds(southEast))
			{
				if(checkAttack(getColor(), board.get(southEast).getPiece()))
				{
					moves.add(southEast);
				}
			}
			
			String eastSouth = "";
			eastSouth += (char)(_position.charAt(0) - 1);
			eastSouth += Character.getNumericValue(_position.charAt(1) + 2);
			
			if(isInBounds(eastSouth))
			{
				if(checkAttack(getColor(), board.get(eastSouth).getPiece()))
				{
					moves.add(eastSouth);
				}
			}
			
			String eastNorth = "";
			eastNorth += (char)(_position.charAt(0) + 1);
			eastNorth += Character.getNumericValue(_position.charAt(1) + 2);
			
			if(isInBounds(eastNorth))
			{
				if(checkAttack(getColor(), board.get(eastNorth).getPiece()))
				{
					moves.add(eastNorth);
				}
			}
			
			String northEast = "";
			northEast += (char)(_position.charAt(0) + 2);
			northEast += Character.getNumericValue(_position.charAt(1) + 1);
			
			if(isInBounds(northEast))
			{
				if(checkAttack(getColor(), board.get(northEast).getPiece()))
				{
					moves.add(northEast);
				}
			}
			
			return moves;
		}
	},
	BLACK_ROOK("black", "rook", "black_rook.png") {
		@Override
		public List<String> getMoves(String _position, Map<String, ChessSquare> board) 
		{
			List<String> moves = new ArrayList<String>();
			
			int nextSquare = 1;
			
			while(true)
			{
				String north = "";
				north += (_position.charAt(0));
				north += Character.getNumericValue(_position.charAt(1) + nextSquare);
				
				if(isInBounds(north))
				{
					if(board.get(north).isEmpty())
					{
						moves.add(north);
					}
					else if(checkAttack(getColor(), board.get(north).getPiece()))
					{
						moves.add(north);
						break;
					}
					else
					{
						break;
					}
				}
				else 
				{
					break;
				}
				
				nextSquare++;
			}
			
			nextSquare = 1;
			
			while(true)
			{
				String south = "";
				south += (_position.charAt(0));
				south += Character.getNumericValue(_position.charAt(1) - nextSquare);
				
				if(isInBounds(south))
				{
					if(board.get(south).isEmpty())
					{
						moves.add(south);
					}
					else if(checkAttack(getColor(), board.get(south).getPiece()))
					{
						moves.add(south);
						break;
					}
					else
					{
						break;
					}
				}
				else 
				{
					break;
				}
				
				nextSquare++;
			}
			
			nextSquare = 1;
			
			while(true)
			{
				String east = "";
				east += (char)(_position.charAt(0) + nextSquare);
				east += _position.charAt(1);
				
				if(isInBounds(east))
				{
					if(board.get(east).isEmpty())
					{
						moves.add(east);
					}
					else if(checkAttack(getColor(), board.get(east).getPiece()))
					{
						moves.add(east);
						break;
					}
					else
					{
						break;
					}
				}
				else 
				{
					break;
				}
				
				nextSquare++;
			}
			
			nextSquare = 1;
			
			while(true)
			{
				String west = "";
				west += (char)(_position.charAt(0) - nextSquare);
				west += _position.charAt(1);
				
				if(isInBounds(west))
				{
					if(board.get(west).isEmpty())
					{
						moves.add(west);
					}
					else if(checkAttack(getColor(), board.get(west).getPiece()))
					{
						moves.add(west);
						break;
					}
					else
					{
						break;
					}
				}
				else 
				{
					break;
				}
				
				nextSquare++;
			}
			
			
			return moves;
		}
	},
	BLACK_BISHOP("black", "bishop", "black_bishop.png") {
		@Override
		public List<String> getMoves(String _position, Map<String, ChessSquare> board) 
		{
			List<String> moves = new ArrayList<String>();
			
			int nextSquare = 1;
			
			while(true)
			{
				String northEast = "";
				northEast += (char)(_position.charAt(0) + nextSquare);
				northEast += Character.getNumericValue(_position.charAt(1) + nextSquare);
				
				if(isInBounds(northEast))
				{
					if(board.get(northEast).isEmpty())
					{
						moves.add(northEast);
					}
					else if(checkAttack(getColor(), board.get(northEast).getPiece()))
					{
						moves.add(northEast);
						break;
					}
					else
					{
						break;
					}
				}
				else 
				{
					break;
				}
				
				nextSquare++;
			}
			
			nextSquare = 1;
			
			while(true)
			{
				String southEast = "";
				southEast += (char)(_position.charAt(0) + nextSquare);
				southEast += Character.getNumericValue(_position.charAt(1) - nextSquare);
				
				if(isInBounds(southEast))
				{
					if(board.get(southEast).isEmpty())
					{
						moves.add(southEast);
					}
					else if(checkAttack(getColor(), board.get(southEast).getPiece()))
					{
						moves.add(southEast);
						break;
					}
					else
					{
						break;
					}
				}
				else 
				{
					break;
				}
				
				nextSquare++;
			}
			
			nextSquare = 1;
			
			while(true)
			{
				String southWest = "";
				southWest += (char)(_position.charAt(0) - nextSquare);
				southWest += Character.getNumericValue(_position.charAt(1) - nextSquare);
				
				if(isInBounds(southWest))
				{
					if(board.get(southWest).isEmpty())
					{
						moves.add(southWest);
					}
					else if(checkAttack(getColor(), board.get(southWest).getPiece()))
					{
						moves.add(southWest);
						break;
					}
					else
					{
						break;
					}
				}
				else 
				{
					break;
				}
				
				nextSquare++;
			}
			
			nextSquare = 1;
			
			while(true)
			{
				String northWest = "";
				northWest += (char)(_position.charAt(0) - nextSquare);
				northWest += Character.getNumericValue(_position.charAt(1) + nextSquare);
				
				if(isInBounds(northWest))
				{
					if(board.get(northWest).isEmpty())
					{
						moves.add(northWest);
					}
					else if(checkAttack(getColor(), board.get(northWest).getPiece()))
					{
						moves.add(northWest);
						break;
					}
					else
					{
						break;
					}
				}
				else 
				{
					break;
				}
				
				nextSquare++;
			}
			
			
			return moves;
		}
	},
	BLACK_QUEEN("black", "queen", "black_queen.png") {
		@Override
		public List<String> getMoves(String _position, Map<String, ChessSquare> board)
		{
			List<String> moves = new ArrayList<String>();
			
			int nextSquare = 1;
			
			while(true)
			{
				String northEast = "";
				northEast += (char)(_position.charAt(0) + nextSquare);
				northEast += Character.getNumericValue(_position.charAt(1) + nextSquare);
				
				if(isInBounds(northEast))
				{
					if(board.get(northEast).isEmpty())
					{
						moves.add(northEast);
					}
					else if(checkAttack(getColor(), board.get(northEast).getPiece()))
					{
						moves.add(northEast);
						break;
					}
					else
					{
						break;
					}
				}
				else 
				{
					break;
				}
				
				nextSquare++;
			}
			
			nextSquare = 1;
			
			while(true)
			{
				String southEast = "";
				southEast += (char)(_position.charAt(0) + nextSquare);
				southEast += Character.getNumericValue(_position.charAt(1) - nextSquare);
				
				if(isInBounds(southEast))
				{
					if(board.get(southEast).isEmpty())
					{
						moves.add(southEast);
					}
					else if(checkAttack(getColor(), board.get(southEast).getPiece()))
					{
						moves.add(southEast);
						break;
					}
					else
					{
						break;
					}
				}
				else 
				{
					break;
				}
				
				nextSquare++;
			}
			
			nextSquare = 1;
			
			while(true)
			{
				String southWest = "";
				southWest += (char)(_position.charAt(0) - nextSquare);
				southWest += Character.getNumericValue(_position.charAt(1) - nextSquare);
				
				if(isInBounds(southWest))
				{
					if(board.get(southWest).isEmpty())
					{
						moves.add(southWest);
					}
					else if(checkAttack(getColor(), board.get(southWest).getPiece()))
					{
						moves.add(southWest);
						break;
					}
					else
					{
						break;
					}
				}
				else 
				{
					break;
				}
				
				nextSquare++;
			}
			
			nextSquare = 1;
			
			while(true)
			{
				String northWest = "";
				northWest += (char)(_position.charAt(0) - nextSquare);
				northWest += Character.getNumericValue(_position.charAt(1) + nextSquare);
				
				if(isInBounds(northWest))
				{
					if(board.get(northWest).isEmpty())
					{
						moves.add(northWest);
					}
					else if(checkAttack(getColor(), board.get(northWest).getPiece()))
					{
						moves.add(northWest);
						break;
					}
					else
					{
						break;
					}
				}
				else 
				{
					break;
				}
				
				nextSquare++;
			}
			
			nextSquare = 1;
			
			while(true)
			{
				String north = "";
				north += (_position.charAt(0));
				north += Character.getNumericValue(_position.charAt(1) + nextSquare);
				
				if(isInBounds(north))
				{
					if(board.get(north).isEmpty())
					{
						moves.add(north);
					}
					else if(checkAttack(getColor(), board.get(north).getPiece()))
					{
						moves.add(north);
						break;
					}
					else
					{
						break;
					}
				}
				else 
				{
					break;
				}
				
				nextSquare++;
			}
			
			nextSquare = 1;
			
			while(true)
			{
				String south = "";
				south += (_position.charAt(0));
				south += Character.getNumericValue(_position.charAt(1) - nextSquare);
				
				if(isInBounds(south))
				{
					if(board.get(south).isEmpty())
					{
						moves.add(south);
					}
					else if(checkAttack(getColor(), board.get(south).getPiece()))
					{
						moves.add(south);
						break;
					}
					else
					{
						break;
					}
				}
				else 
				{
					break;
				}
				
				nextSquare++;
			}
			
			nextSquare = 1;
			
			while(true)
			{
				String east = "";
				east += (char)(_position.charAt(0) + nextSquare);
				east += _position.charAt(1);
				
				if(isInBounds(east))
				{
					if(board.get(east).isEmpty())
					{
						moves.add(east);
					}
					else if(checkAttack(getColor(), board.get(east).getPiece()))
					{
						moves.add(east);
						break;
					}
					else
					{
						break;
					}
				}
				else 
				{
					break;
				}
				
				nextSquare++;
			}
			
			nextSquare = 1;
			
			while(true)
			{
				String west = "";
				west += (char)(_position.charAt(0) - nextSquare);
				west += _position.charAt(1);
				
				if(isInBounds(west))
				{
					if(board.get(west).isEmpty())
					{
						moves.add(west);
					}
					else if(checkAttack(getColor(), board.get(west).getPiece()))
					{
						moves.add(west);
						break;
					}
					else
					{
						break;
					}
				}
				else 
				{
					break;
				}
				
				nextSquare++;
			}
			
			return moves;
		}
	},
	BLACK_KING("black", "king", "black_king.png") {
		@Override
		public List<String> getMoves(String _position, Map<String, ChessSquare> board) 
		{
			List<String> moves = new ArrayList<String>();
			
			int nextSquare = 1;
			
		
				String northEast = "";
				northEast += (char)(_position.charAt(0) + nextSquare);
				northEast += Character.getNumericValue(_position.charAt(1) + nextSquare);
				
				if(isInBounds(northEast))
				{
					if(board.get(northEast).isEmpty())
					{
						moves.add(northEast);
					}
					else if(checkAttack(getColor(), board.get(northEast).getPiece()))
					{
						moves.add(northEast);
					}
				}
				
				String southEast = "";
				southEast += (char)(_position.charAt(0) + nextSquare);
				southEast += Character.getNumericValue(_position.charAt(1) - nextSquare);
				
				if(isInBounds(southEast))
				{
					if(board.get(southEast).isEmpty())
					{
						moves.add(southEast);
					}
					else if(checkAttack(getColor(), board.get(southEast).getPiece()))
					{
						moves.add(southEast);
					}
				}
			
				String southWest = "";
				southWest += (char)(_position.charAt(0) - nextSquare);
				southWest += Character.getNumericValue(_position.charAt(1) - nextSquare);
				
				if(isInBounds(southWest))
				{
					if(board.get(southWest).isEmpty())
					{
						moves.add(southWest);
					}
					else if(checkAttack(getColor(), board.get(southWest).getPiece()))
					{
						moves.add(southWest);

					}
				}
				
				String northWest = "";
				northWest += (char)(_position.charAt(0) - nextSquare);
				northWest += Character.getNumericValue(_position.charAt(1) + nextSquare);
				
				if(isInBounds(northWest))
				{
					if(board.get(northWest).isEmpty())
					{
						moves.add(northWest);
					}
					else if(checkAttack(getColor(), board.get(northWest).getPiece()))
					{
						moves.add(northWest);
					}
				}
		
				String north = "";
				north += (_position.charAt(0));
				north += Character.getNumericValue(_position.charAt(1) + nextSquare);
				
				if(isInBounds(north))
				{
					if(board.get(north).isEmpty())
					{
						moves.add(north);
					}
					else if(checkAttack(getColor(), board.get(north).getPiece()))
					{
						moves.add(north);
					}
			}
			
		
				String south = "";
				south += (_position.charAt(0));
				south += Character.getNumericValue(_position.charAt(1) - nextSquare);
				
				if(isInBounds(south))
				{
					if(board.get(south).isEmpty())
					{
						moves.add(south);
					}
					else if(checkAttack(getColor(), board.get(south).getPiece()))
					{
						moves.add(south);

					}
				}
			
				String east = "";
				east += (char)(_position.charAt(0) + nextSquare);
				east += _position.charAt(1);
				
				if(isInBounds(east))
				{
					if(board.get(east).isEmpty())
					{
						moves.add(east);
					}
					else if(checkAttack(getColor(), board.get(east).getPiece()))
					{
						moves.add(east);

					}
				}
			
				String west = "";
				west += (char)(_position.charAt(0) - nextSquare);
				west += _position.charAt(1);
				
				if(isInBounds(west))
				{
					if(board.get(west).isEmpty())
					{
						moves.add(west);
					}
					else if(checkAttack(getColor(), board.get(west).getPiece()))
					{
						moves.add(west);
					}
				}
			
			return moves;
		}
	},
	BLACK_PAWN("black", "pawn", "black_pawn.png"){
		
		public List<String> getMoves(String _position, Map<String, ChessSquare> board)
		{
			List<String> moves = new ArrayList<String>();
			
			int singleJump = (Character.getNumericValue(_position.charAt(1) - 1));
			String singleMove = (_position.charAt(0) + String.valueOf(singleJump));
			if(board.get(singleMove).isEmpty() && isInBounds(singleMove))
			{
				moves.add(singleMove);
			}
			
			if(Character.getNumericValue(_position.charAt(1)) == 7)
			{
				int doubleJump = (Character.getNumericValue(_position.charAt(1)) - 2);
				String doubleMove = (_position.charAt(0) + "" + String.valueOf(doubleJump));
				
				if(board.get(singleMove).isEmpty() && board.get(doubleMove).isEmpty() && isInBounds(doubleMove))
				{
					moves.add(doubleMove);
				}
				
				
			}
			
			String diagonalPositiveAttack = "";
			diagonalPositiveAttack += (char)((_position.charAt(0) + 1));
			diagonalPositiveAttack += Character.getNumericValue(_position.charAt(1)) - 1;
			
			if(isInBounds(diagonalPositiveAttack) && !board.get(diagonalPositiveAttack).isEmpty())
			{
				if(checkAttack(getColor(), board.get(diagonalPositiveAttack).getPiece()))
				{
					moves.add(diagonalPositiveAttack);
				}
			}
			
			String diagonalNegativeAttack = "";
			diagonalNegativeAttack += (char)((_position.charAt(0) - 1));
			diagonalNegativeAttack += Character.getNumericValue(_position.charAt(1)) - 1;
			
			if(isInBounds(diagonalNegativeAttack) && !board.get(diagonalNegativeAttack).isEmpty())
			{
				if(checkAttack(getColor(), board.get(diagonalNegativeAttack).getPiece()))
				{
					moves.add(diagonalNegativeAttack);
				}
			}
			
			
			return moves;
		}
	},
	WHITE_KNIGHT("white", "knight", "white_knight.png") {
		@Override
		public List<String> getMoves(String _position, Map<String, ChessSquare> board) 
		{
			List<String> moves = new ArrayList<String>();
			
			String northWest = "";
			northWest += (char)(_position.charAt(0) + 2);
			northWest += Character.getNumericValue(_position.charAt(1) - 1);
			
			if(isInBounds(northWest))
			{
				if(checkAttack(getColor(), board.get(northWest).getPiece()))
				{
					moves.add(northWest);
				}
			}
			
			String westNorth = "";
			westNorth += (char)(_position.charAt(0) + 1);
			westNorth += Character.getNumericValue(_position.charAt(1) - 2);
			
			if(isInBounds(westNorth))
			{
				if(checkAttack(getColor(), board.get(westNorth).getPiece()))
				{
					moves.add(westNorth);
				}
			}
			
			String westSouth = "";
			westSouth += (char)(_position.charAt(0) - 1);
			westSouth += Character.getNumericValue(_position.charAt(1) - 2);
			
			if(isInBounds(westSouth))
			{
				if(checkAttack(getColor(), board.get(westSouth).getPiece()))
				{
					moves.add(westSouth);
				}
			}
			
			String southWest = "";
			southWest += (char)(_position.charAt(0) - 2);
			southWest += Character.getNumericValue(_position.charAt(1) - 1);
			
			if(isInBounds(southWest))
			{
				if(checkAttack(getColor(), board.get(southWest).getPiece()))
				{
					moves.add(southWest);
				}
			}
			
			String southEast = "";
			southEast += (char)(_position.charAt(0) - 2);
			southEast += Character.getNumericValue(_position.charAt(1) + 1);
			
			if(isInBounds(southEast))
			{
				if(checkAttack(getColor(), board.get(southEast).getPiece()))
				{
					moves.add(southEast);
				}
			}
			
			String eastSouth = "";
			eastSouth += (char)(_position.charAt(0) - 1);
			eastSouth += Character.getNumericValue(_position.charAt(1) + 2);
			
			if(isInBounds(eastSouth))
			{
				if(checkAttack(getColor(), board.get(eastSouth).getPiece()))
				{
					moves.add(eastSouth);
				}
			}
			
			String eastNorth = "";
			eastNorth += (char)(_position.charAt(0) + 1);
			eastNorth += Character.getNumericValue(_position.charAt(1) + 2);
			
			if(isInBounds(eastNorth))
			{
				if(checkAttack(getColor(), board.get(eastNorth).getPiece()))
				{
					moves.add(eastNorth);
				}
			}
			
			String northEast = "";
			northEast += (char)(_position.charAt(0) + 2);
			northEast += Character.getNumericValue(_position.charAt(1) + 1);
			
			if(isInBounds(northEast))
			{
				if(checkAttack(getColor(), board.get(northEast).getPiece()))
				{
					moves.add(northEast);
				}
			}
			
			return moves;
		}
	},
	WHITE_ROOK("white", "rook", "white_rook.png") {
		@Override
		public List<String> getMoves(String _position, Map<String, ChessSquare> board) 
		{
			List<String> moves = new ArrayList<String>();
			
			int nextSquare = 1;
			
			while(true)
			{
				String north = "";
				north += (_position.charAt(0));
				north += Character.getNumericValue(_position.charAt(1) + nextSquare);
				
				if(isInBounds(north))
				{
					if(board.get(north).isEmpty())
					{
						moves.add(north);
					}
					else if(checkAttack(getColor(), board.get(north).getPiece()))
					{
						moves.add(north);
						break;
					}
					else
					{
						break;
					}
				}
				else 
				{
					break;
				}
				
				nextSquare++;
			}
			
			nextSquare = 1;
			
			while(true)
			{
				String south = "";
				south += (_position.charAt(0));
				south += Character.getNumericValue(_position.charAt(1) - nextSquare);
				
				if(isInBounds(south))
				{
					if(board.get(south).isEmpty())
					{
						moves.add(south);
					}
					else if(checkAttack(getColor(), board.get(south).getPiece()))
					{
						moves.add(south);
						break;
					}
					else
					{
						break;
					}
				}
				else 
				{
					break;
				}
				
				nextSquare++;
			}
			
			nextSquare = 1;
			
			while(true)
			{
				String east = "";
				east += (char)(_position.charAt(0) + nextSquare);
				east += _position.charAt(1);
				
				if(isInBounds(east))
				{
					if(board.get(east).isEmpty())
					{
						moves.add(east);
					}
					else if(checkAttack(getColor(), board.get(east).getPiece()))
					{
						moves.add(east);
						break;
					}
					else
					{
						break;
					}
				}
				else 
				{
					break;
				}
				
				nextSquare++;
			}
			
			nextSquare = 1;
			
			while(true)
			{
				String west = "";
				west += (char)(_position.charAt(0) - nextSquare);
				west += _position.charAt(1);
				
				if(isInBounds(west))
				{
					if(board.get(west).isEmpty())
					{
						moves.add(west);
					}
					else if(checkAttack(getColor(), board.get(west).getPiece()))
					{
						moves.add(west);
						break;
					}
					else
					{
						break;
					}
				}
				else 
				{
					break;
				}
				
				nextSquare++;
			}
			
			
			return moves;
		}
	},
	WHITE_BISHOP("white","bishop","white_bishop.png") {
		@Override
		public List<String> getMoves(String _position,	Map<String, ChessSquare> board) 
		{
		List<String> moves = new ArrayList<String>();
			
			int nextSquare = 1;
			
			while(true)
			{
				String northEast = "";
				northEast += (char)(_position.charAt(0) + nextSquare);
				northEast += Character.getNumericValue(_position.charAt(1) + nextSquare);
				
				if(isInBounds(northEast))
				{
					if(board.get(northEast).isEmpty())
					{
						moves.add(northEast);
					}
					else if(checkAttack(getColor(), board.get(northEast).getPiece()))
					{
						moves.add(northEast);
						break;
					}
					else
					{
						break;
					}
				}
				else 
				{
					break;
				}
				
				nextSquare++;
			}
			
			nextSquare = 1;
			
			while(true)
			{
				String southEast = "";
				southEast += (char)(_position.charAt(0) + nextSquare);
				southEast += Character.getNumericValue(_position.charAt(1) - nextSquare);
				
				if(isInBounds(southEast))
				{
					if(board.get(southEast).isEmpty())
					{
						moves.add(southEast);
					}
					else if(checkAttack(getColor(), board.get(southEast).getPiece()))
					{
						moves.add(southEast);
						break;
					}
					else
					{
						break;
					}
				}
				else 
				{
					break;
				}
				
				nextSquare++;
			}
			
			nextSquare = 1;
			
			while(true)
			{
				String southWest = "";
				southWest += (char)(_position.charAt(0) - nextSquare);
				southWest += Character.getNumericValue(_position.charAt(1) - nextSquare);
				
				if(isInBounds(southWest))
				{
					if(board.get(southWest).isEmpty())
					{
						moves.add(southWest);
					}
					else if(checkAttack(getColor(), board.get(southWest).getPiece()))
					{
						moves.add(southWest);
						break;
					}
					else
					{
						break;
					}
				}
				else 
				{
					break;
				}
				
				nextSquare++;
			}
			
			nextSquare = 1;
			
			while(true)
			{
				String northWest = "";
				northWest += (char)(_position.charAt(0) - nextSquare);
				northWest += Character.getNumericValue(_position.charAt(1) + nextSquare);
				
				if(isInBounds(northWest))
				{
					if(board.get(northWest).isEmpty())
					{
						moves.add(northWest);
					}
					else if(checkAttack(getColor(), board.get(northWest).getPiece()))
					{
						moves.add(northWest);
						break;
					}
					else
					{
						break;
					}
				}
				else 
				{
					break;
				}
				
				nextSquare++;
			}
			
			
			return moves;
		}
	},
	WHITE_QUEEN("white", "queen", "white_queen.png") {
		@Override
		public List<String> getMoves(String _position, Map<String, ChessSquare> board) 
		{
		List<String> moves = new ArrayList<String>();
			
			int nextSquare = 1;
			
			while(true)
			{
				String northEast = "";
				northEast += (char)(_position.charAt(0) + nextSquare);
				northEast += Character.getNumericValue(_position.charAt(1) + nextSquare);
				
				if(isInBounds(northEast))
				{
					if(board.get(northEast).isEmpty())
					{
						moves.add(northEast);
					}
					else if(checkAttack(getColor(), board.get(northEast).getPiece()))
					{
						moves.add(northEast);
						break;
					}
					else
					{
						break;
					}
				}
				else 
				{
					break;
				}
				
				nextSquare++;
			}
			
			nextSquare = 1;
			
			while(true)
			{
				String southEast = "";
				southEast += (char)(_position.charAt(0) + nextSquare);
				southEast += Character.getNumericValue(_position.charAt(1) - nextSquare);
				
				if(isInBounds(southEast))
				{
					if(board.get(southEast).isEmpty())
					{
						moves.add(southEast);
					}
					else if(checkAttack(getColor(), board.get(southEast).getPiece()))
					{
						moves.add(southEast);
						break;
					}
					else
					{
						break;
					}
				}
				else 
				{
					break;
				}
				
				nextSquare++;
			}
			
			nextSquare = 1;
			
			while(true)
			{
				String southWest = "";
				southWest += (char)(_position.charAt(0) - nextSquare);
				southWest += Character.getNumericValue(_position.charAt(1) - nextSquare);
				
				if(isInBounds(southWest))
				{
					if(board.get(southWest).isEmpty())
					{
						moves.add(southWest);
					}
					else if(checkAttack(getColor(), board.get(southWest).getPiece()))
					{
						moves.add(southWest);
						break;
					}
					else
					{
						break;
					}
				}
				else 
				{
					break;
				}
				
				nextSquare++;
			}
			
			nextSquare = 1;
			
			while(true)
			{
				String northWest = "";
				northWest += (char)(_position.charAt(0) - nextSquare);
				northWest += Character.getNumericValue(_position.charAt(1) + nextSquare);
				
				if(isInBounds(northWest))
				{
					if(board.get(northWest).isEmpty())
					{
						moves.add(northWest);
					}
					else if(checkAttack(getColor(), board.get(northWest).getPiece()))
					{
						moves.add(northWest);
						break;
					}
					else
					{
						break;
					}
				}
				else 
				{
					break;
				}
				
				nextSquare++;
			}
			
			nextSquare = 1;
			
			while(true)
			{
				String north = "";
				north += (_position.charAt(0));
				north += Character.getNumericValue(_position.charAt(1) + nextSquare);
				
				if(isInBounds(north))
				{
					if(board.get(north).isEmpty())
					{
						moves.add(north);
					}
					else if(checkAttack(getColor(), board.get(north).getPiece()))
					{
						moves.add(north);
						break;
					}
					else
					{
						break;
					}
				}
				else 
				{
					break;
				}
				
				nextSquare++;
			}
			
			nextSquare = 1;
			
			while(true)
			{
				String south = "";
				south += (_position.charAt(0));
				south += Character.getNumericValue(_position.charAt(1) - nextSquare);
				
				if(isInBounds(south))
				{
					if(board.get(south).isEmpty())
					{
						moves.add(south);
					}
					else if(checkAttack(getColor(), board.get(south).getPiece()))
					{
						moves.add(south);
						break;
					}
					else
					{
						break;
					}
				}
				else 
				{
					break;
				}
				
				nextSquare++;
			}
			
			nextSquare = 1;
			
			while(true)
			{
				String east = "";
				east += (char)(_position.charAt(0) + nextSquare);
				east += _position.charAt(1);
				
				if(isInBounds(east))
				{
					if(board.get(east).isEmpty())
					{
						moves.add(east);
					}
					else if(checkAttack(getColor(), board.get(east).getPiece()))
					{
						moves.add(east);
						break;
					}
					else
					{
						break;
					}
				}
				else 
				{
					break;
				}
				
				nextSquare++;
			}
			
			nextSquare = 1;
			
			while(true)
			{
				String west = "";
				west += (char)(_position.charAt(0) - nextSquare);
				west += _position.charAt(1);
				
				if(isInBounds(west))
				{
					if(board.get(west).isEmpty())
					{
						moves.add(west);
					}
					else if(checkAttack(getColor(), board.get(west).getPiece()))
					{
						moves.add(west);
						break;
					}
					else
					{
						break;
					}
				}
				else 
				{
					break;
				}
				
				nextSquare++;
			}
			
			return moves;
		}
	},
	WHITE_KING("white", "king", "white_king.png") {
		@Override
		public List<String> getMoves(String _position, Map<String, ChessSquare> board)
		{
			List<String> moves = new ArrayList<String>();
			
			int nextSquare = 1;
			
		
				String northEast = "";
				northEast += (char)(_position.charAt(0) + nextSquare);
				northEast += Character.getNumericValue(_position.charAt(1) + nextSquare);
				
				if(isInBounds(northEast))
				{
					if(board.get(northEast).isEmpty())
					{
						moves.add(northEast);
					}
					else if(checkAttack(getColor(), board.get(northEast).getPiece()))
					{
						moves.add(northEast);
					}
				}
				
				String southEast = "";
				southEast += (char)(_position.charAt(0) + nextSquare);
				southEast += Character.getNumericValue(_position.charAt(1) - nextSquare);
				
				if(isInBounds(southEast))
				{
					if(board.get(southEast).isEmpty())
					{
						moves.add(southEast);
					}
					else if(checkAttack(getColor(), board.get(southEast).getPiece()))
					{
						moves.add(southEast);
					}
				}
			
				String southWest = "";
				southWest += (char)(_position.charAt(0) - nextSquare);
				southWest += Character.getNumericValue(_position.charAt(1) - nextSquare);
				
				if(isInBounds(southWest))
				{
					if(board.get(southWest).isEmpty())
					{
						moves.add(southWest);
					}
					else if(checkAttack(getColor(), board.get(southWest).getPiece()))
					{
						moves.add(southWest);

					}
				}
				
				String northWest = "";
				northWest += (char)(_position.charAt(0) - nextSquare);
				northWest += Character.getNumericValue(_position.charAt(1) + nextSquare);
				
				if(isInBounds(northWest))
				{
					if(board.get(northWest).isEmpty())
					{
						moves.add(northWest);
					}
					else if(checkAttack(getColor(), board.get(northWest).getPiece()))
					{
						moves.add(northWest);
					}
				}
		
				String north = "";
				north += (_position.charAt(0));
				north += Character.getNumericValue(_position.charAt(1) + nextSquare);
				
				if(isInBounds(north))
				{
					if(board.get(north).isEmpty())
					{
						moves.add(north);
					}
					else if(checkAttack(getColor(), board.get(north).getPiece()))
					{
						moves.add(north);
					}
			}
			
		
				String south = "";
				south += (_position.charAt(0));
				south += Character.getNumericValue(_position.charAt(1) - nextSquare);
				
				if(isInBounds(south))
				{
					if(board.get(south).isEmpty())
					{
						moves.add(south);
					}
					else if(checkAttack(getColor(), board.get(south).getPiece()))
					{
						moves.add(south);

					}
				}
			
				String east = "";
				east += (char)(_position.charAt(0) + nextSquare);
				east += _position.charAt(1);
				
				if(isInBounds(east))
				{
					if(board.get(east).isEmpty())
					{
						moves.add(east);
					}
					else if(checkAttack(getColor(), board.get(east).getPiece()))
					{
						moves.add(east);

					}
				}
			
				String west = "";
				west += (char)(_position.charAt(0) - nextSquare);
				west += _position.charAt(1);
				
				if(isInBounds(west))
				{
					if(board.get(west).isEmpty())
					{
						moves.add(west);
					}
					else if(checkAttack(getColor(), board.get(west).getPiece()))
					{
						moves.add(west);
					}
				}
			
			return moves;
		}
	},
	WHITE_PAWN("white", "pawn", "white_pawn.png") {
		public List<String> getMoves(String _position, Map<String, ChessSquare> board)
		{
			List<String> moves = new ArrayList<String>();
			
			int singleJump = (Character.getNumericValue(_position.charAt(1) + 1));
			String singleMove = (_position.charAt(0) + String.valueOf(singleJump));
			if(board.get(singleMove).isEmpty())
			{
				moves.add(singleMove);
			}
			
			if(Character.getNumericValue(_position.charAt(1)) == 2)
			{
				int doubleJump = (Character.getNumericValue(_position.charAt(1)) + 2);
				String doubleMove = (_position.charAt(0) + "" + String.valueOf(doubleJump));
				
				if(board.get(singleMove).isEmpty() && board.get(doubleMove).isEmpty() && isInBounds(doubleMove))
				{
					moves.add(doubleMove);
				}
			}
			
			String diagonalPositiveAttack = "";
			diagonalPositiveAttack += (char)((_position.charAt(0) + 1));
			diagonalPositiveAttack += Character.getNumericValue(_position.charAt(1)) + 1;
			
			if(isInBounds(diagonalPositiveAttack) && !board.get(diagonalPositiveAttack).isEmpty())
			{
				if(checkAttack(getColor(), board.get(diagonalPositiveAttack).getPiece()))
				{
					moves.add(diagonalPositiveAttack);
				}
			}
			
			String diagonalNegativeAttack = "";
			diagonalNegativeAttack += (char)((_position.charAt(0) - 1));
			diagonalNegativeAttack += Character.getNumericValue(_position.charAt(1)) + 1;
			
			if(isInBounds(diagonalNegativeAttack) && !board.get(diagonalNegativeAttack).isEmpty())
			{
				if(checkAttack(getColor(), board.get(diagonalNegativeAttack).getPiece()))
				{
					moves.add(diagonalNegativeAttack);
				}
			}
			
			return moves;
		}
	};

	private String color;
	private String piece;
	private ImageIcon image;
	
	
	ChessPiece(String _color, String _piece, String _image)
	{
		color = _color;
		piece = _piece;
		image = new ImageIcon(_image);
	}

	/**
	 * Checks the piece at in the attack square to verify that the move can be made
	 * and the piece is the opposite color.
	 */
	public boolean checkAttack(String attackerColor, ChessPiece recieving)
	{
		boolean isValid = false;
		
		if(!attackerColor.equalsIgnoreCase(recieving.getColor()))
		{
			isValid = true;
		}
		
		return isValid;
	}
	
	/**
	 * Checks to make sure that the given move is within the bounds of the board,
	 * if not returns false.
	 * 
	 * @param location
	 * @return
	 */
	public boolean isInBounds(String location)
	{
		boolean isValid = false;
		
		char column = Character.toLowerCase(location.charAt(0));
		int row = Character.getNumericValue(location.charAt(1));
		
		if((column <= 'h' && column >= 'a') && (row >= 1 && row <= 8) )
		{
			isValid = true;
		}
		
		return isValid;
	}
	
	/* 
	 * (non-Javadoc)
	 * @see baldwin.neumont.student.chess.IPieceCommands#getMoves(java.lang.String, java.util.Map)
	 */
	public abstract List<String> getMoves(String _position, Map<String, ChessSquare> board);
	
	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getPiece() {
		return piece;
	}

	public void setPiece(String piece) {
		this.piece = piece;
	}

	public ImageIcon getImage() {
		return image;
	}

	public void setImage(ImageIcon image) {
		this.image = image;
	}
}
