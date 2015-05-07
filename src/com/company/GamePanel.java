package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Arrays;

public class GamePanel extends JPanel implements MouseMotionListener, ActionListener, MouseListener {

    /** Width and height of application window in pixels */
    public static final int APPLICATION_WIDTH = 400;
    public static final int APPLICATION_HEIGHT = 600;

    public static final int NBRICKS_PER_ROW = 10;
    public static final int NBRICK_ROWS = 10;
    public static final int BRICK_SEP = 4;
    private static final int BRICK_WIDTH =
            (APPLICATION_WIDTH - (NBRICKS_PER_ROW - 1) * BRICK_SEP) / NBRICKS_PER_ROW;
    private static final int BRICK_HEIGHT = 8;
    private static final int BRICK_Y_OFFSET = 70;

    ArrayList<Brick> bricks;
    Paddle myPaddle;
    Ball myBall;
    Color brickColor;
    int quantityOfAttempt=3;
    JButton startButton;
    private Timer gameTimeDelay;


    int gameScore = 0;

    public int getGameScore() {
        return gameScore;
    }

    public void setGameScore(int gameScore) {
        this.gameScore = gameScore;
    }

    public Color getBrickColor() {
        return brickColor;
    }

    public void setBrickColor(Color brickColor) {
        this.brickColor = brickColor;
    }

    public GamePanel() {
        setPreferredSize(new Dimension(APPLICATION_WIDTH, APPLICATION_HEIGHT));
        addMouseMotionListener(this);
        gameTimeDelay = new Timer(30,this);

        bricks = new ArrayList<Brick>();

        rectPosition(bricks);

        myPaddle = new Paddle();
        myBall = new Ball((APPLICATION_WIDTH-(Ball.getBallRadius()*2))/2,(APPLICATION_HEIGHT-(Ball.getBallRadius()*2))/2,7,7);

        // Кнопка запуска игры
        startButton = new JButton("Start");
        add(startButton);
        startButton.addActionListener(this);

        repaint();


    }

    public void rectPosition( ArrayList<Brick> b){

        // Определение местоположения кирпича и добавление его в ArrayList
        int yStart = BRICK_Y_OFFSET;

        for (int i = 0; i < NBRICK_ROWS; i++) {
            int xStart = (APPLICATION_WIDTH - (NBRICKS_PER_ROW * (BRICK_WIDTH + BRICK_SEP) - BRICK_SEP)) / 2;


            for (int j = 0; j < NBRICKS_PER_ROW; j++) {

                // раскраска кирпичей
                if (i == 0 || i == 1) {
                    setBrickColor(Color.red);
                } else if (i == 2 || i == 3) {
                    setBrickColor(Color.orange);
                } else if (i == 4 || i == 5) {
                    setBrickColor(Color.yellow);
                } else if (i == 6 || i == 7) {
                    setBrickColor(Color.green);
                } else if (i == 8 || i == 9) {
                    setBrickColor(Color.cyan);
                }
                b.add(new Brick(xStart, yStart, getBrickColor()));
                xStart += BRICK_WIDTH + BRICK_SEP;
            }
            yStart += BRICK_HEIGHT + BRICK_SEP;

        }
    }

    public void gameReset(){
        if(gameOverCheck()){
            gameTimeDelay.stop();
            return;
        }
       myBall.setxPos((APPLICATION_WIDTH-(myBall.getBallRadius()*2))/2);
       myBall.setyPos((APPLICATION_HEIGHT - (myBall.getBallRadius() * 2)) / 2);



    }

    public boolean gameOverCheck(){
        if(quantityOfAttempt==0){
        return true;
        }
        return false;

    }

    private boolean empty(){
        return (bricks.size()==0)?true:false;
    }

    public void checkCollision(){

        //попадание мяча на ракетку
        if(myPaddle.checkHitPaddle(myBall)){
            myBall.setdY(myBall.getdY()*-1);
            return;
        }
        //пападание мяча на стены
        if(myBall.getxPos()<=0||myBall.getxPos()>=APPLICATION_WIDTH-(myBall.getBallRadius()*2)){
            myBall.setdX(myBall.getdX()*-1);
        }
        if(myBall.getyPos()<=0){
            myBall.setdY(myBall.getdY()*-1);
        }
        //попадание мяча ниже ракетки
        if(myBall.getyPos()+(myBall.getBallRadius()*2)>myPaddle.getyPaddlePosition()+myPaddle.getPADDLE_HEIGHT()){

            quantityOfAttempt--;
            gameReset();
        }
        // попадание мяча в кирпич
        for (int i = 0; i <bricks.size() ; i++) {
            if(bricks.get(i).hitBrickByBall(myBall)){
                setGameScore(getGameScore()+100);
                bricks.remove(i);
            }
        }
    }



    // Движение ракетки за курсором мыши
    @Override
    public void mouseMoved(MouseEvent e) {
        if(e.getX()>=0&&e.getX()<=APPLICATION_WIDTH-myPaddle.getPADDLE_WIDTH()){
            myPaddle.setxPaddlePosition(e.getX());
            repaint();
        }
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        //Game panel color
        g.fillRect(0,0,APPLICATION_WIDTH,APPLICATION_HEIGHT);
        g.drawRect(0,0,APPLICATION_WIDTH,APPLICATION_HEIGHT);

        // Прорисовка каждого кирича
        for(Brick brk :bricks){
            g.setColor(brk.getBrickColour());
            g.fillRect(brk.getxPos(),brk.getyPos(),brk.getBRICK_WIDTH(),brk.getBRICK_HEIGHT());
        }

        //Прорисовка ракетки.
        g.setColor(myPaddle.getPADDLE_COLOR());
        g.fillRect(myPaddle.getxPaddlePosition(),myPaddle.getyPaddlePosition(),myPaddle.getPADDLE_WIDTH(),myPaddle.getPADDLE_HEIGHT());

        //Прорисовка мяча
        g.setColor(myBall.getBallColor());
        g.fillOval(myBall.getxPos(),myBall.getyPos(),myBall.getBallRadius()*2,myBall.getBallRadius()*2);


        //Score and Lives
        g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
        g.drawString("Score: "+getGameScore(),10,25);
        g.drawString("Lives: "+quantityOfAttempt,10,50);

        //Фин результат игры
        if(gameOverCheck()){
            g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
            g.drawString("Game Over! Score: "+getGameScore(),(getWidth()/2)-130,getHeight()/2);
        }

        if(empty()){
            gameTimeDelay.stop();
            g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
            g.drawString("You won ! Score: "+getGameScore(),(getWidth()/2)-130,getHeight()/2);
        }

    }


    @Override
    public void mouseDragged(MouseEvent e) {

    }


    @Override
    public void actionPerformed(ActionEvent e) {
            gameTimeDelay.start();
            remove(startButton);
            checkCollision();
            myBall.ballMove();
            repaint();

    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
