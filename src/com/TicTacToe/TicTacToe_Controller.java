package com.TicTacToe;

/*********************************KNOWS ABOUT VIEW AND MODEL BOTH*******************************************/

public class TicTacToe_Controller
{
    private TicTacToe_View view;
    private  TicTacToe_Model model;

    //Registers View and Model object for controller
    public TicTacToe_Controller(TicTacToe_View ticTacToe_view, TicTacToe_Model ticTacToe_model) {
        view = ticTacToe_view;
        model = ticTacToe_model;
        model.setViewInModel(ticTacToe_view);
    }
    //call model method to handle player move
    public void takeMove_Controller(int x,int y)
    {
        model.takeMoveLogic(x,y);
    }

    //call model method to handle reset game action by the user
    public void resetOnRestartClick_Controller()
    {

        model.resetBoardOnRestartClick();
    }

    //call model method to handle end game action by the user
    public void endGame_Controller()
    {
        model.endGameOnOptionClick();
    }

}
