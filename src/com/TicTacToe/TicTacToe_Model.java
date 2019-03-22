package com.TicTacToe;

import javax.swing.*;
import java.awt.*;
/***********THIS CLASS HANDLES ALL LOGIC OF PLAYER MOVES AND GAME STATE(WIN/DRAW..)****************
 ***********MODEL GETS CALLED BY THE CONTROLLER TO DO SO,IT MAKES DIRECT UPDATE TO THE VIEW*********/
public class TicTacToe_Model {
    private TicTacToe_View view;
    private int currentPlayer;
    private int dim;
    private String playerSymbol;
    boolean halt;

    public void setViewInModel(TicTacToe_View view)
    {
        this.view=view;
        currentPlayer=1;
        playerSymbol="O";//O =1=Player 1
        dim=3;
    }
    //check draw when no win and board full
    public boolean checkDrawCondition(int x,int y)
    {
        boolean draw=true;
        JButton [][]board=view.getGameBoard();
        for(int i=0;i<dim && draw;i++)
        {
            for(int j=0;j<dim && draw;j++)
            {
                if(board[i][j].getText().toString().equals(""))
                {
                    draw=false;
                }
            }
        }
        halt=true;
        return draw;

    }
    public boolean isFull()
    {
        boolean full=true;
        JButton [][]board=view.getGameBoard();
        for(int i=0;i<dim && full;i++)
        {
            for(int j=0;j<dim && full;j++)
            {
                if(board[i][j].getText().toString().equals(""))
                {
                    full=false;
                }
            }
        }

        return full;
    }
    public void resetBoardOnRestartClick()//when restart game
    {
        //reinitialize all fields as in constructor
        JOptionPane.showMessageDialog(view.getParent(),"PLAYER "+playerSymbol+" HAS REQUESTED GAME RESET");
        currentPlayer=1;
        playerSymbol="O";
        JButton[][] board=view.getGameBoard();
        for(int i=0;i<dim;i++)
        {
            for(int j=0;j<dim;j++)
            {
                board[i][j].setText("");
            }
        }
    }
    public void endGameOnOptionClick()
    {
        JOptionPane.showMessageDialog(view.getParent(),"PLAYER "+playerSymbol+" ENDED THE GAME");
        System.exit(0);
    }
    public void takeMoveLogic(int x,int y)
    {
        if (view.getGameBoard()[x][y].getText().toString().equals("") && !isWin() && !checkDrawCondition(x, y)) {
                if (playerSymbol.equals("X")) {
                    view.getGameBoard()[x][y].setText(playerSymbol);
                    view.getGameBoard()[x][y].setForeground(Color.RED);
                } else {
                    view.getGameBoard()[x][y].setText(playerSymbol);
                    view.getGameBoard()[x][y].setForeground(Color.BLACK);
                }

                if (isWin()) {
                    JOptionPane.showMessageDialog(view.getParent(), playerSymbol + " Has Won the Game!");
                } else {
                    //JOptionPane.showMessageDialog(view.getParent(),"Player "+playerSymbol +"'s turn");
                    togglePlayer();
                }

            } else if (checkDrawCondition(x, y)) {
            JOptionPane.showMessageDialog(view.getParent(), "Game Has Ended In a Draw");
        }
    }
    public void togglePlayer()//switch player
    {

        if(currentPlayer==1)
        {
            currentPlayer=2;
            playerSymbol="X";


        }
        else if(currentPlayer==2)
        {
            currentPlayer=1;
            playerSymbol="O";

        }
    }
    public boolean isWin()//check winner
    {
        JButton[][] gameBoard=view.getGameBoard();
        /*****************************WIN CASES FOR PLAYER*********************************************/

        /************CASE 1: ROW WIN (i same,j different)********************/

        for(int i=0;i<dim;i++)
        {
            boolean win=true;
            for(int j=0;j<dim && win;j++)
            {
                if(!gameBoard[i][j].getText().toString().equals(playerSymbol))
                {
                    win=false;      //if a mismatch found no win so exit inner loop
                }
            }
            if(win)
            {
                halt=true;
                return true;//a row with same player symbols was found hence WIN
            }
        }

        /************CASE 2: COLUMN WIN (i different,j same)********************/
        for(int j=0;j<dim;j++)
        {
            boolean win=true;
            for(int i=0;i<dim && win;i++)
            {
                if(!gameBoard[i][j].getText().toString().equals(playerSymbol))
                {
                    win=false;      //if a mismatch found no win so exit inner loop
                }
            }
            if(win)
            {
                halt=true;
                return true;//a row with same player symbols was found hence WIN
            }
        }

        /*******************CASE 3: Left Diagonal WIN (i =j)*********************/
        boolean win=true;
        for(int i=0,j=0;i<3 && j<3 && win;i++,j++)
        {
            if(!gameBoard[i][j].getText().toString().equals(playerSymbol))
            {
                win=false;//exit loop with no win
            }
        }
        if(win)
        {
            halt=true;
            return true;
        }
        else
        {
            win=false;
        }

        /*******CASE 4: Right Diagonal WIN (i=0 till dim-1 , j=dim-1 till 0)******/
        win=true;
        for(int i=0,j=dim-1;i<dim && j>=0 && win;i++,j--)
        {
            if(!gameBoard[i][j].getText().toString().equals(playerSymbol))
            {
                win=false;
            }
        }
        if(win)
        {
            halt=true;
            return true;
        }

        return  win;
    }

}
