The project consists of 4 files. 

TicTacToeM.java
Board.java
input.txt
README.txt

The main is in TicTacToeM.java

Input file is configured by the user. When choosing character # means that player wont play.
The program reads only the last character of each line of the input text. Only in the first
line it reads the last two ones characters, so as to catch the case of two digit size of board 10.

When running the program it first asks to give the file path with inputs.
 
Insert file path : 
C:\\Users\\eclipse-workspace\\TicTacToe\\tictactoe\\input.txt

Each user plays by its turn. There is no random selection of who is going to play first.

Computer plays randomly when prediction gives no good result.
Since it searches rows/columns/diagonals/anti diagonals only one way/direction, computer wont catch the 
TicTacToe case where for example B is about to win in the following:

                 |A|C|
                A|B|A|
                C| |B|
                
but will catch this case :

                B|A|C|
                A|B|A|
                 | |C|
                 
//////////////////////////////////////////////////////////////////////////////////////////////
                 
Move must be given in format like this:

              Enter your turn (row , <column): 1,1

if not , it prints the following:

              Enter your turn (row , <column): 1.1
              Please enter both a row and a column!
              Enter your turn (row , <column): 1,1

                    A| | | |
                     | | | |
                     | | | |
                     | | | |


If a move is already entered by another player the program asks again for input:

                    A| | | |
                     |B| | |
                     | |C| |
                     | | | |

                Enter your turn (row , <column): 1,1
                Give a valid move....
                Enter your turn (row , <column): 


If board size given in input exceeds 10 , it prints the following:

                The size of the board is : 40
                Number of the players are: 3
                Wrong size of board(1-10), fix file and....
                Insert file path : 