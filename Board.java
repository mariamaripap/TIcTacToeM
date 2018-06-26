package tictactoe;

import java.util.*;

public class Board
{
    private static Random generator = new Random();
    
    public Board() 
    {    }
    
	 /* ****************************
	 *   Clear board functions 
	 *************************** */
    final public void clearBoard(int[][] numMatches,int sizeBoard) 
    {
		for (int row=0; row<sizeBoard; row++) 
		{
			for (int column=0; column<sizeBoard; column++) 
			{
				numMatches[row][column]=0;
			}
		}
    }

    final public void clearBoard(char[][] squares) 
    {
    	for (char[] row : squares) 
    	{
    			Arrays.fill(row,' ');
    	}
    }
	
    final public void clearBoard(char[] inputchar) 
    {
		for (int row=0; row<4; row++) 
		{
			inputchar[row]=' ';
		}
	}
    
	 /* **********************************
	 *      Computer moves randomly
	 *   when there is no better move 
	 *********************************** */
    
    public int[] computerMove(int sizeBoard,char[][] squares)
    {
    	int randomIntR=0;
    	int randomIntC=0;
        int[] cell = { 0, 0 };
        boolean validTurn = false;
    	do
    	{
            for (int idx = 0; idx < sizeBoard; ++idx)
            {
              randomIntR = generator.nextInt(sizeBoard);
        	  randomIntC = generator.nextInt(sizeBoard);
            }
            
    	    if(squares[randomIntR][randomIntC]== ' ')
    	    {
    	          cell[0]=randomIntR;
    	    	  cell[1]=randomIntC;
    	    	  validTurn = true;
    	    }
    		
    	}while(!validTurn);

        return cell;
        
    }
    
	 /* **************************************************************
	 *               Computer moves with prediction
	 *     it uses array numMatches, checks weight in each cell
	 *     and chooses the cell after the position with max weight 
	 **************************************************************** */
    
    public int[] predictComputerMove(int[][] numMatches,int sizeBoard,char[][] squares) 
    {
        int[] cell = { 0, 0 };
        int i=0;
        boolean validTurn = false;
        do 
        {
        	for (int row = 0; row < numMatches.length; row++)
        	{
        		for(int column = 0; column < numMatches.length; column++)
        		{
        			if(numMatches[row][column]==sizeBoard-i-2)//check if there is max value in match
        			{

        				if(column>1 && numMatches[row][column-1]==sizeBoard-i-3)// check previous position in the same row 
        				{

        					if(column<sizeBoard-1 && squares[row][column+1]== ' ')//check if next position is blank
        					{
        						cell[0]=row;
        						cell[1]=column+1;
        						validTurn = true;
        						return cell;
        					}
        				}
        				else if(row>1 && numMatches[row-1][column]==numMatches[row][column]-1 )// check previous position in the same column
        				{

        					if(row<sizeBoard-1 && squares[row+1][column]== ' ')//check if next position in the same column is blank
        					{
        						cell[0]=row+1;
        						cell[1]=column;
        						validTurn = true;
        						return cell;
        					}
        				}
        				else if(row>1 && column>1 && numMatches[row-1][column-1]==numMatches[row][column]-1 )// check previous position in the same diagonal
        				{

        					if(row<sizeBoard-1 && column<sizeBoard-1 && squares[row+1][column+1]== ' ')//check if next position in the same diagonal is blank
        					{
        						cell[0]=row+1;
        						cell[1]=column+1;
        						validTurn = true;
        						return cell;
        					}
        				}
        				else if(row>1 && column<sizeBoard-1 && numMatches[row-1][column+1]==numMatches[row][column]-1 )// check previous position in the same anti diagonal
        				{

        					if(row<sizeBoard-1 && column>0 && squares[row+1][column-1]== ' ')//check if next position in the same anti diagonal is blank
        					{
        						cell[0]=row+1;
        						cell[1]=column-1;
        						validTurn = true;
        						return cell;
        					}
        				}
        				else
        				{
        					cell=computerMove(sizeBoard,squares);
        					if(squares[cell[0]][cell[1]]== ' ')
        					{
        						validTurn = true;
        					}
    						return cell;
        				}
        					
        			}

        		}
        	}i++;
        }while(!validTurn && i<=sizeBoard);
        return cell;
    }
    
    
	 /* **************************************************************
	 *               Function that draws a board  
	 **************************************************************** */

    
    public void drawBoard(int sizeBoard,char[][] squares) 
    {
    	System.out.println();
        for (int row = 0; row < sizeBoard; row++)
        {
        	for(int column = 0; column < sizeBoard; column++)
        	{
        		System.out.print(squares[row][column]);
        		System.out.print("|");
        	}
        	System.out.println();
        }
    	System.out.println();

    }   
    
    public void drawBoard(int sizeBoard,int[][] numMatches) 
    {
    	System.out.println();
        for (int row = 0; row < sizeBoard; row++)
        {
        	for(int column = 0; column < sizeBoard; column++)
        	{
        		System.out.print(numMatches[row][column]);
        		System.out.print(" | ");
        	}
        	System.out.println();
        }
    	System.out.println();

    } 

    
}
	
	
	
