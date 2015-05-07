package com.company;

import java.awt.*;

/**
 * Created by ua001022 on 12.03.2015.
 */
public class Paddle extends Component{

    /** Dimensions of the paddle */
    private  final int PADDLE_WIDTH = 60;
    private  final int PADDLE_HEIGHT = 10;
    /** Offset of the paddle up from the bottom */
    private  final int PADDLE_Y_OFFSET = 30;

    private int xPaddlePosition = (GamePanel.APPLICATION_WIDTH-PADDLE_WIDTH)/2;
    private int yPaddlePosition = GamePanel.APPLICATION_HEIGHT-PADDLE_Y_OFFSET-PADDLE_HEIGHT;



    public  final Color PADDLE_COLOR = Color.white;

    public Paddle() {
    }

    public Paddle(int xPaddlePosition) {
        this.xPaddlePosition = xPaddlePosition;

        if(xPaddlePosition<0) this.xPaddlePosition = 0;
        if(xPaddlePosition>getWidth()-PADDLE_WIDTH) this.xPaddlePosition = getWidth()-PADDLE_WIDTH;
    }

    public int getPADDLE_WIDTH() {
        return PADDLE_WIDTH;
    }

    public int getPADDLE_HEIGHT() {
        return PADDLE_HEIGHT;
    }

    public int getxPaddlePosition() {
        return xPaddlePosition;
    }

    public void setxPaddlePosition(int xPaddlePosition) {
        this.xPaddlePosition = xPaddlePosition;
    }

    public int getyPaddlePosition() {
        return yPaddlePosition;
    }

    public Color getPADDLE_COLOR() {
        return PADDLE_COLOR;
    }

    public boolean checkHitPaddle(Ball b){

        if(b.getxPos()<=getxPaddlePosition()+PADDLE_WIDTH&&b.getxPos()>=getxPaddlePosition()){
            if(b.getyPos()+(b.getBallRadius()*2)>=getyPaddlePosition()){
                return true;
            }
        }

        return false;
    }





}
