import java.util.Scanner;

public class TicTacToe
{
	private static char[][] board = new char[3][3];

	public static void main( String[] args )
	{
		Scanner keyboard = new Scanner(System.in);

		initBoard();
		displayBoard();

		char player;	//this is a variable which is used throughout most of the methods. Sufficient to determine the player turn only once which took out a lot of inefficient checks
		int turn = 1;

		while(turn < 10)	//maximum 9 total moves
		{
			if (turn % 2 == 1)	//player X turn
			{
				player = 'X';
			}
			else	//player O turn
			{
				player = 'O';
			}

			System.out.print(player + ", choose your location (row, column): ");
			String choice = keyboard.nextLine();
			String[] choiceSplit = choice.split("\\s+");

			int row = Integer.valueOf(choiceSplit[0]);
			int col = Integer.valueOf(choiceSplit[1]);

			while (row < 0 || row > 2 || col < 0 || col > 2 || board[row][col] != ' ')	//check for occupied squrares
			{
				System.out.print( "invalid coordinates, choose another location (row, column): ");
				choice = keyboard.nextLine();
				choiceSplit = choice.split("\\s+");

				row = Integer.valueOf(choiceSplit[0]);
				col = Integer.valueOf(choiceSplit[1]);
			}

			playBoard(player, row, col);
			Winner(player, row, col);

			turn++;

			displayBoard();
		}

			System.out.println("The game is a tie.");	//if no winner declaed then it's a tie
	}

	public static void initBoard()	//fills up the board with blanks
	{
		for ( int r = 0; r < 3; r++ )
			for ( int c = 0; c < 3; c++ )
				board[r][c] = ' ';
	}

	public static void displayBoard()
	{
		System.out.println("  0  " + board[0][0] + "|" + board[0][1] + "|" + board[0][2]);
		System.out.println("    --+-+--");
		System.out.println("  1  " + board[1][0] + "|" + board[1][1] + "|" + board[1][2]);
		System.out.println("    --+-+--");
		System.out.println("  2  " + board[2][0] + "|" + board[2][1] + "|" + board[2][2]);
		System.out.println("     0 1 2 ");
	}

	public static char[][] playBoard(char p, int r, int c)	//marks a square with X or O depending on player
	{
		board[r][c] = p;
		return board;
	}

	public static void Winner(char p, int r, int c)	//the arguments are the player and the coords picked
	{
		for (int i = 0; i < 3; i++)	//checks row
		{
			if (board[r][i] != p)
			{
				break;	//ends program as soon as a winner is declared
			}

			if (i == 2)
			{
				displayBoard();
				System.out.println("player " + p + " wins by row");
				System.exit(0);
			}
		}

		for (int i = 0; i < 3; i++)	//checks column
		{
			if (board[i][c] != p)
			{
				break;
			}

			if (i == 2)
			{
				displayBoard();
				System.out.println("player " + p + " wins by column");
				System.exit(0);
			}
		}

		if (r == c)	//checks main diagonal
		{
			for (int i = 0; i < 3; i++)
			{
				if (board[i][i] != p)
				{
					break;
				}

				if (i == 2)
				{
					displayBoard();
					System.out.println("player " + p + " wins by main diagonal");
					System.exit(0);
				}
			}
		}

		if ( r + c == 2)	//checks anti diagonal
		{
			for (int i = 0; i < 3; i++)
			{
				if (board[i][2-i] != p)
				{
					break;
				}

				if (i == 2)
				{
					displayBoard();
					System.out.println("player " + p + " wins by anti diagonal");
					System.exit(0);
				}
			}
		}
	}
}