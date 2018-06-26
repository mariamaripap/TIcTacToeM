package tictactoe;

import java.util.*;
import java.io.*;

/* **************************************************************
*               Game Tic Tac Toe
*               
*     programmer: Maria Papadopoulou
*     date: 06/26/2018
*                
**************************************************************** */

public class TicTacToeM
{

	static int sizeBoard;
    static int numOfPlayers;
    static double numOfLoops;
    static char[][] squares; //the board Game
    static int[][] numMatches; //the array with weights
    static Board board;
    
    static boolean gameHasWinner; // Boolean to show if game is a win/lose
    static boolean aPlayer;
    static boolean bPlayer;
    static boolean computerPlayer;
    static char aPlayersChar;
    static char bPlayersChar;
    static char computerPlayersChar;
    
    static int move[];
    static char inputchar[];  //array for saving inputs from file (size board and characters)

    
    private final static Scanner sc = new Scanner(System.in);
    
    static char winnerIs;
    
    public static void main(String[] args)  throws IOException
    {
        numOfPlayers=0;
        board=new Board();
        boolean validInput=false;
        
    	while(!validInput)  //repeat if input is incorrect
    	{
    		
    		 /*
        	 * Input from file, Output board to console.
        	 */
    		
            System.out.println("Insert file path : ");
            
        	//inputchar=readCharFromFile("C:\\Users\\Πατρίκιος\\eclipse-workspace\\TicTacToe\\tictactoe\\input.txt");
            inputchar=readCharFromFile(sc.nextLine());
            
            //check if integer given for size of board is 2digit
            if(inputchar[1]!=' ')
            {
                String size= new StringBuilder().append(inputchar[1]).append(inputchar[0]).toString();
            	sizeBoard= Integer.parseInt(size);
            }
            else
            {
                String size= new StringBuilder().append(inputchar[0]).toString();
            	sizeBoard= Integer.parseInt(size);
            }

            squares = new char[sizeBoard][sizeBoard];
            numMatches=new int [sizeBoard][sizeBoard];
            
            //calculate how many players will play
            for(int i=2; i<5;i++)
            {
            	if (inputchar[i]!='#') //if character is # it means that this player wont play
            	{
            		numOfPlayers++;
            	}
            	
            }
            

        	//System.out.print("Insert size of the board : ");
            //sizeBoard = sc.nextInt();
        	System.out.println("The size of the board is : "+sizeBoard);
        	
            //System.out.print("Insert number of the players (1-3): ");
            //numOfPlayers = sc.nextInt();
            System.out.println("Number of the players are: "+numOfPlayers);
            
            //check if size of board is in the given limits
            if (sizeBoard<3 || sizeBoard>10)
            {
            	System.out.println ("Wrong size of board(1-10), fix file and....");

            }
            else
            {
                //check if number of players is in the given limits
                if (numOfPlayers>3 || numOfPlayers<1)
                {
                	System.out.println ("Wrong num of players(1-3), fix file and....");
                }
                else
                {
                	if(inputchar[2]=='#' && inputchar[3]=='#')
                	{
                		System.out.println ("Computer cannot play alone, fix file and....");
                	}
                	else //input was correct so you can continue
                	validInput=true;
                }

            }
    	}
    	
    	if(inputchar[2]!='#')
    	{
            aPlayersChar=inputchar[2];
        	System.out.println("Player 1 plays with character : "+aPlayersChar);
        	if(inputchar[3]!='#')
        	{
                bPlayersChar=inputchar[3];
            	System.out.println("Player 2 plays with character : "+bPlayersChar);
        	}
        	else
        	{
        		bPlayersChar='#';
        	}
        	if(inputchar[4]!='#')
        	{
                computerPlayersChar=inputchar[4];
            	System.out.println("Computer plays with character : "+computerPlayersChar);
        	}
    	}
    	else
    	{
    		if(inputchar[3]!='#')
        	{
                aPlayersChar='#';
                bPlayersChar=inputchar[3];
            	System.out.println("Player 1 plays with character : "+bPlayersChar);
        	}
        	if(inputchar[4]!='#')
        	{
                computerPlayersChar=inputchar[4];
            	System.out.println("Computer plays with character : "+computerPlayersChar);
        	}

    	}



        gameHasWinner = false; 



        board.clearBoard(inputchar);
    	board.clearBoard(squares);
    	board.clearBoard(numMatches,sizeBoard);
    	System.out.println("Welcome to tic tac toe game!!!");
        board.drawBoard(sizeBoard,squares);
        
        
   	     /* *************************************************************************
   	     * 
    	 *  There is a maximum of sizeBoard^2 plays the loop keeps track of the  
    	 *  number of plays. The game overs in the sizeBoard^2-th play if no winner
    	 *   has been found.
    	 *               
    	 ************************************************************************** */
        
        numOfLoops=Math.pow(sizeBoard, 2);
        int i = 0;
        while(i < numOfLoops)
        {
        	for(int j=0; j<numOfPlayers; j++) //each player plays after the other
        	{
        		i++;
                if (i==numOfLoops+1)
                {	
                    if (!gameHasWinner)
                    {
                     System.out.println("NO WINNER! DRAW!");
                     board.drawBoard(sizeBoard,numMatches);
                     System.exit(0);
                    }
                }
                switch(j)
                {
                	case 0:
                		System.out.println("It is first player's turn....");
                		move=promptMove();
        			break;
                	case 1:
                		if(aPlayersChar!='#')
                		{
                			if(bPlayersChar=='#')
                			{
                				System.out.println("It is computer's turn....");
                        		move=board.predictComputerMove(numMatches,sizeBoard,squares);
                			}
                			else
                			{
                        		System.out.println("It is second player's turn....");
                        		move=promptMove();	
                			}

                		}
                		else
                		{
                    		System.out.println("It is computer's turn....");
                    		move=board.predictComputerMove(numMatches,sizeBoard,squares);	
                		}
        			break;
                	case 2:
                		System.out.println("It is computer's turn....");
                		move=board.predictComputerMove(numMatches,sizeBoard,squares);
        			break;
                }


                updateBoard(move, j);
                board.drawBoard(sizeBoard,squares);
                winnerIs=verifyWinner();


                if (gameHasWinner) 
                {
                    System.out.println("WIN..!!!");
                    System.out.println("Winner is:"+winnerIs);
                    board.drawBoard(sizeBoard,numMatches);

                    System.exit(0);
                }
        	}

        }
        
        if (!gameHasWinner)
        {
         System.out.println("NO WINNER! DRAW!");
        }


        
    }//end of main
    
	 /* **************************************************************
	 * 
	 *               Function that updates the Game board  
	 *                    with each player's move
	 *                    
	 **************************************************************** */
    private static void updateBoard(int playmove[], int player) 
    {
	    int row = playmove[0] ; 
	    int column = playmove[1];
	    
	    if (player==0)
	    	if(aPlayersChar!='#')
	    		squares[row][column] = aPlayersChar;
	    	else
	    		squares[row][column] = bPlayersChar;
	    else if (player==1)
	    	if(aPlayersChar!='#' && bPlayersChar!='#' )
	    		squares[row][column] = bPlayersChar;
	    	else
	    		squares[row][column] = computerPlayersChar;
	    else if (player==2)
	    	squares[row][column] = computerPlayersChar;
        else
        	squares[row][column] = ' ';
    }
    
    
	 /* **************************************************************
	 * 
	 *               Function that takes player's move  
	 *                    given in the console
	 *                    
	 **************************************************************** */
    private static int[] promptMove() 
    {
        int[] cell = { 0, 0 };
        boolean validTurn = false;
        do 
        {
            System.out.print("Enter your turn (row , <column): ");
            String[] input = sc.nextLine().split(",");   //rows and columns are split by comma
            
            if (input.length < 2) 
            {
                if (input[0].equalsIgnoreCase("quit")) //you can exit with quit
                {
                    System.exit(0);
                } else 
                {
                    System.out.println("Please enter both a row and a column!");
                }
            }
            else 
            {
            	cell[0] = Integer.parseInt(input[0]) - 1;
                cell[1] = Integer.parseInt(input[1]) - 1;
  		      	if(squares[cell[0]][cell[1]]== ' ')
  		      	{
  		      		validTurn = true;
  		      	}
  		      	else 
  		      	{
  		      		System.out.println("Give a valid move...."); //if move is already entered by another player
  		      	}

             }

        }while (!validTurn);

        return cell;
    }
    
    
	 /* **************************************************************
	 * 
	 *               Function that verifies if there is a winner.
	 *               
	 *   It also keeps track of the weights inside array numMatches.
	 *   
	 *   If two same characters are in a row/column/diagonal/anti diagonal 
	 *   then weight is increased in the position where the last character 
	 *   is.
	 *   
	 *   For example:
	 *   
	 *   The following board:
	 *   
	 *                 A|A|A|
	 *                 B|C| |
	 *                  |B|C|
	 *    
	 *    is translated in the following weight board:
	 *    
	 *               0 | 1 | 2 |
	 *               0 | 0 | 0 | 
	 *               0 | 0 | 0 | 
	 *                    
	 *   P.S. rows/columns/diagonals are only searched from one direction 
	 *        row(left to to right), column (up to down), diagonal(up left
	 *        to down right), anti diagonal(up right to down left).
	 *        For that reason the diagonal C,C is not caught right!!!            
	 **************************************************************** */
    
    public static char verifyWinner() 
    {
        char winner = ' ';
        int testrow = 0;
        int testcolumn = 0 ;
        int r=0;
        int c=0;
        int match =0;
        boolean flag = false ;
        //gameHasWinner = false;
        int count=0;
        
        while(count<sizeBoard) //for a given row searches all columns
        {
        		count++;
        		match=0;
            	while(testcolumn<sizeBoard-1 && r<sizeBoard)
            	{
            		//if two same characters in a row and not ' ' then search if next column in the same 
            		//row has the same character as well
            		if(squares[r][testcolumn]== squares[r][testcolumn+1]&& squares[r][testcolumn]!=  ' ')
            		{     
            			testcolumn++;
            			match++;
            			numMatches[r][testcolumn]=match;
            			flag = true ;
            		}

            		else 
            		{
            			r++;
            			testcolumn = 0;
            			flag=false;
            			break;
            		}
            	}
                if(flag)
                {
                	gameHasWinner = true;
                    winner = squares[r][testcolumn];
                    return winner;
                }
                
                winner =  ' ';
            }//end of test columns in a row

            
            testrow = 0;
            c = 0;
            count=0;
            while(count<sizeBoard) //for a given column searches all rows
            {
        		count++;
        		match=0;
            	while(testrow<sizeBoard-1 && c<sizeBoard)
            	{
            		//if two same characters in a column and not ' ' then search if next row in the same 
            		//column has the same character as well
            		if(squares[testrow][c]== squares[testrow+1][c]&& squares[testrow][c]!=  ' ')
            		{   
            			testrow++;
            			match++;
            			numMatches[testrow][c]=match;
            			flag = true ;
            		}

            		else 
            		{
            			c++;
            			testrow=0;
            			flag = false ;
            			break;
            		}
            	}
                if(flag)
                {
                	gameHasWinner = true;
                    winner = squares[testrow][c];
                    return winner;
                }
                winner =  ' ';
           }//end of test rows in a column
            

            testcolumn = 0;
            testrow = 0;
            match =0;
            while(testcolumn<sizeBoard-1) //searches the diagonal 
            {
           		count++;
         
           		//if two same characters in a column,row and not ' ' then search if next row in the next 
           		//column has the same character as well
               	if(squares[testrow][testcolumn]== squares[testrow+1][testcolumn+1]&& squares[testrow][testcolumn]!=  ' ')
               	{        			
               		testrow++;
               		testcolumn++;
           			match++;
           			numMatches[testrow][testcolumn]=match;
               		flag = true ;
               	}
            	else 
            	{
           			flag=false;
           			break;
           		}
            }
            if(flag)
            {
              	gameHasWinner = true;
              	winner = squares[0][0];
                return winner;
             }
             winner =  ' ';
             //end of test diagonal
                
             testrow = 0;
             testcolumn = sizeBoard-1;
             match =0;

             while(testcolumn>0)//searches the anti diagonal 
             {
           		count++;
      
           		//if two same characters in a column,row and not ' ' then search if next row in the previous
               	//column has the same character as well
               	if(squares[testrow][testcolumn]== squares[testrow+1][testcolumn-1]&& squares[testrow][testcolumn]!=  ' ')
               	{   
               		testrow++;
               		testcolumn--;
           			match++;
           			numMatches[testrow][testcolumn]=match;
               		flag = true ;
                } 
            	else 
           		{
           			flag=false;
           			break;
           		}
           	  }
              if(flag)
              {
            	  gameHasWinner = true;
            	  winner = squares[testrow][testcolumn];
                  return winner;
               }                   
               winner =  ' ';
               //end of test anti diagonal


		return winner;

    }//end of verify
    
    
	 /* **************************************************************
	 * 
	 *    Function that reads from file and fills an array of chars.
	 *    Takes the last two characters from first line and the 
	 *    last character from the following lines.
	 *    Max lines in the text are 4.             
	 *                    
	 **************************************************************** */
 
	public static char[] readCharFromFile(String inputFileName) throws IOException
	{
		File inputFile = new File(inputFileName);
		BufferedReader bufferedReader = new BufferedReader(new FileReader(inputFile));
		String inputLine;
		char characters[]= new char[5];
		for(int line = 0; ((inputLine = bufferedReader.readLine()) != null); line++) 
		{
			if(line==0)
			{
					characters[line]=inputLine.charAt(inputLine.length()-1);
					characters[line+1]=inputLine.charAt(inputLine.length()-2);
			}
			else
			{
				characters[line+1]=inputLine.charAt(inputLine.length()-1);
			}
		}
        bufferedReader.close();
		return characters;

	}
}
