package com.TicTacToe;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/*********************************SET UP UI COMPONENTS AND CONTROLLER HERE*************************************/


public class TicTacToe_View extends JFrame
{
    private JButton[][] gameBoard;
    private Container pane;
    private JMenuBar menuBar;
    private JMenu menu;
    private JMenuItem restartGame;
    private JMenuItem endGame;
    private TicTacToe_Controller ticTacToe_controller;
    private JMenu m;
    private int dim;
    private JTextField authorName;

    public TicTacToe_View()
    {
        super();
        dim=3;//3*3 board DEFAULT
        pane=getContentPane();
        pane.setLayout(new GridLayout(dim,dim));
        setTitle("TicTacToe");//gives frame a title
        setSize(550,550);
        setResizable(false);//does let user resize
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);//make GUI visible
        gameBoard=new JButton[dim][dim];
        setUpBoard();
        initializeMenuBar();
        //Register Controller ....takes current view and a model object as parameter
        ticTacToe_controller=new TicTacToe_Controller(this,new TicTacToe_Model());//register view and model in controller

    }
    public void initializeMenuBar()
    {
        menuBar=new JMenuBar();
        authorName=new JTextField("Author: Mehrin Athar");
        m=new JMenu("Author: Mehrin Athar");
        m.addSeparator();
        menu=new JMenu("Game Options");
        restartGame=new JMenuItem("Restart");
        endGame=new JMenuItem("End Game");
        //now add listener for when player clicks Restart
        restartGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ticTacToe_controller.resetOnRestartClick_Controller();
            }
        });

        //now add listener for end game
        endGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                ticTacToe_controller.endGame_Controller();
            }
        });

        //add menu items to menu
        menu.add(restartGame);
        menu.add(endGame);

        //now add menu to menu bar
        menuBar.add(m);
        menuBar.add(menu);
        setJMenuBar(menuBar);//JFrame method
    }

    public void setUpBoard()
    {

        for(int i=0;i<dim;i++)
        {
            for(int j=0;j<dim;j++)
            {
                JButton btn=new JButton();
                btn.setFont(new Font(Font.SANS_SERIF,Font.BOLD,100));
                gameBoard[i][j]=btn;
                btn.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        JButton getBtn=(JButton)e.getSource();
                        int [] coordinates=getBoardCoordinate(e);
                        ticTacToe_controller.takeMove_Controller(coordinates[0],coordinates[1]);
                                            }
                });
                pane.add(btn);

            }
        }

    }
    //get x , y coordinate where move is expected to be taken
    public int [] getBoardCoordinate(ActionEvent e)
    {
        int [] coordinates=new int[2];
        for(int i=0;i<dim;i++)//i=row, j=column
        {
            for(int j=0;j<dim;j++)
            {
                if(e.getSource()==gameBoard[i][j])//cell where user at found
                {
                 coordinates[0]=i;//row
                 coordinates[1]=j;//column
                }
            }
        }
        return coordinates;
    }

    //GETTERS AND SETTERS
    public JButton[][] getGameBoard() {
        return gameBoard;
    }

    public void setGameBoard(JButton[][] gameBoard) {
        this.gameBoard = gameBoard;
    }

    public Container getPane() {
        return pane;
    }

    public void setPane(Container pane) {
        this.pane = pane;
    }



    public void setMenuBar(JMenuBar menuBar) {
        this.menuBar = menuBar;
    }

    public JMenu getMenu() {
        return menu;
    }

    public void setMenu(JMenu menu) {
        this.menu = menu;
    }

    public JMenuItem getRestartGame() {
        return restartGame;
    }

    public void setRestartGame(JMenuItem restartGame) {
        this.restartGame = restartGame;
    }

    public JMenuItem getEndGame() {
        return endGame;
    }

    public void setEndGame(JMenuItem endGame) {
        this.endGame = endGame;
    }

    public TicTacToe_Controller getTicTacToe_controller() {
        return ticTacToe_controller;
    }

    public void setTicTacToe_controller(TicTacToe_Controller ticTacToe_controller) {
        this.ticTacToe_controller = ticTacToe_controller;
    }

    public int getDim() {
        return dim;
    }

    public void setDim(int dim) {
        this.dim = dim;
    }
}
