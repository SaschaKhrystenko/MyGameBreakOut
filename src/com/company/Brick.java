package com.company;

import java.awt.*;

/**
 * Created by ua001022 on 23.03.2015.
 */
public class Brick extends Component{

    /**
     * Width of a brick
     */
    private final int BRICK_WIDTH =
            (GamePanel.APPLICATION_WIDTH - (GamePanel.NBRICKS_PER_ROW - 1) * GamePanel.BRICK_SEP) / GamePanel.NBRICKS_PER_ROW;
    /**
     * Height of a brick
     */
    private final int BRICK_HEIGHT = 8;
    /**
     * Offset of the top brick row from the top
     */
    private Color brickColour;

    private int     xPos, yPos;

    public Brick(int xPos, int yPos, Color brickColour) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.brickColour = brickColour;
    }

    public int getBRICK_WIDTH() {
        return BRICK_WIDTH;
    }

    public int getBRICK_HEIGHT() {
        return BRICK_HEIGHT;
    }

    public int getxPos() {
        return xPos;
    }

    public int getyPos() {
        return yPos;
    }

    public void setBrickColour(Color brickColour) {
        this.brickColour = brickColour;
    }

    public Color getBrickColour() {
        return brickColour;
    }


    public boolean hitBrickByBall(Ball ball){

        //Если мяч входит в зону кирпича по вертикали
        if(ball.getxPos()>=getxPos()&&ball.getxPos()<=getxPos()+getBRICK_WIDTH()){
            // Если мяч ударяеться об низ кирпича
            if(ball.getyPos()<=getyPos()+getBRICK_HEIGHT()&&ball.getyPos()>getyPos()+(getBRICK_HEIGHT()/2)  ){
                ball.setdY(ball.getdY()*-1);
                return true;
            }
            //проверка удара мяча об верх кирпича
            else if(ball.getyPos()+(ball.getBallRadius()*2)>=getyPos()&&ball.getyPos()+(ball.getBallRadius()*2)<getyPos()+(ball.getBallRadius())){
                ball.setdY(ball.getdY()*-1);
                return true;
            }
        }
        // Если мяч входит в зону кирпича по горизонтали
        if(ball.getyPos()>=getyPos()&&ball.getyPos()<=getyPos()+getBRICK_HEIGHT()){

            // Если мяч ударяеться об левый край
            if(ball.getxPos()+(ball.getBallRadius()*2)>=getxPos()&&ball.getxPos()+(ball.getBallRadius()*2)<getxPos()+(getBRICK_WIDTH()/2)){
                ball.setdX(ball.getdX()*-1);
                return true;
            }
            else if (ball.getxPos()<=getxPos()+getBRICK_WIDTH()&&ball.getxPos()>=getxPos()+(getBRICK_WIDTH()/2)){
                ball.setdX(ball.getdX()*-1);
                return true;
            }

        }

        return  false;
    }
}
