package com.company;

import java.awt.*;

/**
 * Created by ua001022 on 12.03.2015.
 */
public class Ball {

    /** Radius of the ball in pixels */
    private static final int BALL_RADIUS = 10;

    public static final Color BALL_COLOR = Color.white;

    private int xPos, yPos;
    private int dX, dY;

    public Ball(int xPos, int yPos, int dX, int dY) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.dX = dX;
        this.dY = dY;
    }

    public int getxPos() {
        return xPos;
    }

    public void setxPos(int xPos) {
        this.xPos = xPos;
    }

    public int getyPos() {
        return yPos;
    }

    public void setyPos(int yPos) {
        this.yPos = yPos;
    }

    public int getdX() {
        return dX;
    }

    public void setdX(int dX) {
        this.dX = dX;
    }

    public int getdY() {
        return dY;
    }

    public void setdY(int dY) {
        this.dY = dY;
    }

    public static Color getBallColor() {
        return BALL_COLOR;
    }

    public void ballMove(){
        xPos+=dX;
        yPos+=dY;

          }

    public static int getBallRadius() {
        return BALL_RADIUS;
    }


}
