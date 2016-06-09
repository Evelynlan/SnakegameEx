package com.example;

import org.omg.CORBA.BooleanHolder;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * Created by L T on 2016/6/9.
 */
public class GameWindowDouble extends JFrame implements ActionListener {
    Snake snake1;
    Snake snake2;
    Player player = new Player();
    private boolean p1Run = false;

    JLabel scoreLabel1 = new JLabel("1P");
    JLabel scoreLabel2 = new JLabel("2P");
    JTextField p1Score = new JTextField(10);
    JTextField p2Score = new JTextField(10);
    JButton btnStart = new JButton("开始");

    public GameWindowDouble() {
        initUI();
        snake1 = new Snake(getWidth() - 2 * Snake.CELL_SIZE, getHeight() - 4 * Snake.CELL_SIZE,
                new Cell(Snake.CELL_SIZE * 3, Snake.CELL_SIZE * 3), Snake.DIR_RIGTH);
        snake2 = new Snake(getWidth() - 2 * Snake.CELL_SIZE, getHeight() - 4 * Snake.CELL_SIZE,
                new Cell(Snake.CELL_SIZE * 65, Snake.CELL_SIZE * 33), Snake.DIR_LEFT);
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                switch (e.getKeyCode()){
                    case KeyEvent.VK_UP:
                        snake2.changeDir(Snake.DIR_UP);
                        if(snake2.dir==Snake.DIR_UP){
                            snake2.DELAY=30;
                        }
                        break;
                    case KeyEvent.VK_DOWN:
                        snake2.changeDir(Snake.DIR_DOWN);
                        if(snake2.dir==Snake.DIR_DOWN){
                            snake2.DELAY=30;
                        }
                        break;
                    case KeyEvent.VK_LEFT:
                        snake2.changeDir(Snake.DIR_LEFT);
                        if(snake2.dir==Snake.DIR_LEFT){
                            snake2.DELAY=30;
                        }
                        break;
                    case KeyEvent.VK_RIGHT:
                        snake2.changeDir(Snake.DIR_RIGTH);
                        if(snake2.dir==Snake.DIR_RIGTH){
                            snake2.DELAY=30;
                        }
                        break;
                    case KeyEvent.VK_W:
                        snake1.changeDir(Snake.DIR_UP);
                        if(snake1.dir==Snake.DIR_UP){
                            snake1.DELAY=30;
                        }
                        break;
                    case KeyEvent.VK_S:
                        snake1.changeDir(Snake.DIR_DOWN);
                        if(snake1.dir==Snake.DIR_DOWN){
                            snake1.DELAY=30;
                        }
                        break;
                    case KeyEvent.VK_A:
                        snake1.changeDir(Snake.DIR_LEFT);
                        if(snake1.dir==Snake.DIR_LEFT){
                            snake1.DELAY=30;
                        }
                        break;
                    case KeyEvent.VK_D:
                        snake1.changeDir(Snake.DIR_RIGTH);
                        if(snake1.dir==Snake.DIR_RIGTH){
                            snake1.DELAY=30;
                        }
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
                switch (e.getKeyCode()){
                    case KeyEvent.VK_UP:
                        if(snake2.dir==Snake.DIR_UP){
                            snake2.DELAY=100;
                        }
                        break;
                    case KeyEvent.VK_DOWN:
                        if(snake2.dir==Snake.DIR_DOWN){
                            snake2.DELAY=100;
                        }
                        break;
                    case KeyEvent.VK_LEFT:
                        if(snake2.dir==Snake.DIR_LEFT){
                            snake2.DELAY=100;
                        }
                        break;
                    case KeyEvent.VK_RIGHT:
                        if(snake2.dir==Snake.DIR_RIGTH){
                            snake2.DELAY=100;
                        }
                        break;

                    case KeyEvent.VK_W:
                        if(snake1.dir==Snake.DIR_UP){
                            snake1.DELAY=100;
                        }
                        break;
                    case KeyEvent.VK_S:
                        if(snake1.dir==Snake.DIR_DOWN){
                            snake1.DELAY=100;
                        }
                        break;
                    case KeyEvent.VK_A:
                        if(snake1.dir==Snake.DIR_LEFT){
                            snake1.DELAY=100;
                        }
                        break;
                    case KeyEvent.VK_D:
                        if(snake1.dir==Snake.DIR_RIGTH){
                            snake1.DELAY=100;
                        }
                        break;
                    default:
                        break;
                }
            }
        });
    }

    private void initUI() {
        setTitle("SnakeGameEx v0.2");
        Container con = getContentPane();
        con.setBackground(Color.DARK_GRAY);
        setExtendedState(MAXIMIZED_BOTH);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JPanel top = new JPanel();
        top.setBackground(Color.DARK_GRAY);
        top.add(scoreLabel1);
        top.add(p1Score);
        top.add(btnStart);
        top.add(scoreLabel2);
        top.add(p2Score);

        p1Score.setEnabled(false);
        p2Score.setEnabled(false);
        p1Score.setText("0");
        p2Score.setText("0");

        scoreLabel1.setForeground(Color.white);
        scoreLabel2.setForeground(Color.white);

        btnStart.setBackground(Color.DARK_GRAY);
        btnStart.setForeground(Color.white);
        btnStart.addActionListener(this);
        add(top, "North");
        add(player, "Center");
        player.setBackground(Color.darkGray);

        setVisible(true);
    }

    public void goSnake1() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (p1Run) {
                    requestFocus();
                    refreshSnake1(p1Score);
//                    if(GG()){
//                        p1Run = false;
//                        btnStart.setText("重新开始");
//                        reStart();
//                    }
                    repaint();
                    try {
                        Thread.sleep(snake1.DELAY);
                    } catch (InterruptedException e1) {
                        e1.printStackTrace();
                    }
                }
            }
        }).start();
    }

    public void goSnake2() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (p1Run) {
                    requestFocus();
                    refreshSnake2(p2Score);
                    if(GG()){
                        p1Run = false;
                        btnStart.setText("重新开始");
                        reStart();
                    }
                    repaint();
                    try {
                        Thread.sleep(snake2.DELAY);
                    } catch (InterruptedException e1) {
                        e1.printStackTrace();
                    }
                }
            }
        }).start();
    }

    public void refreshSnake1(JTextField score) {
        snake1.move();
        score.setText("" + snake1.score);
    }

    public void refreshSnake2(JTextField score) {
        snake2.move();
        score.setText("" + snake2.score);
    }

    public void reStart() {
        snake1.init(new Cell(snake1.CELL_SIZE * 3, snake1.CELL_SIZE * 3), Snake.DIR_RIGTH);
        snake2.init(new Cell(Snake.CELL_SIZE * 65, Snake.CELL_SIZE * 33), Snake.DIR_LEFT);
    }

    public boolean GG() {
        if (snake1.isGameOver()) {
            JOptionPane.showMessageDialog(null, "游戏结束,P2胜");
            return true;
        }
        if (snake2.isGameOver()) {
            JOptionPane.showMessageDialog(null, "游戏结束,P1胜");
            return true;
        }
        if (p1BiteP2()) {
            JOptionPane.showMessageDialog(null,"游戏结束,P2胜");
        }
        if (p2BiteP1()) {
            JOptionPane.showMessageDialog(null,"游戏结束,P1胜");
        }

        return false;
    }

    private boolean p2BiteP1() {
        Cell head = snake2.cells.get(snake2.cells.size() - 1);
        for (int i = 0; i < snake1.cells.size() - 1; i++) {
            if (head.getX() == snake1.cells.get(i).getX()
                    && head.getY() == snake1.cells.get(i).getY()) {
                return true;
            }
        }
        if (head.getX() == snake1.cells.get(snake1.cells.size() - 1).getX()
                && head.getY() == snake1.cells.get(snake1.cells.size() - 1).getY()
                && snake2.score<=snake1.score){
            return true;
        }
        return false;
    }

    private boolean p1BiteP2() {
        Cell head = snake1.cells.get(snake1.cells.size() - 1);
        for (int i = 0; i < snake2.cells.size() - 1; i++) {
            if (head.getX() == snake2.cells.get(i).getX()
                    && head.getY() == snake2.cells.get(i).getY()) {
                return true;
            }
        }
        if (head.getX() == snake2.cells.get(snake2.cells.size() - 1).getX()
                && head.getY() == snake2.cells.get(snake2.cells.size() - 1).getY()
                && snake1.score<=snake2.score){
            return true;
        }
            return false;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnStart) {
            if (!p1Run) {
                p1Run = true;
                goSnake1();
                goSnake2();
                btnStart.setText("暂停");
            } else {
                p1Run = false;
                btnStart.setText("开始");
            }
        }
    }

    class Player extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            g.setColor(Color.GRAY);
            g.fillRect(0, 0, getWidth(), getHeight());

            drawSnake(g, snake1);
            drawSnake(g, snake2);
            drawFood(g, snake1);

        }

        public void drawSnake(Graphics g, Snake snake) {
            chooseColor(g, snake.score);
            for (Cell cell : snake.cells) {
                g.fillRect(cell.getX(), cell.getY(), snake.CELL_SIZE, snake.CELL_SIZE);
            }

        }

        public void drawFood(Graphics g, Snake snake) {
            g.setColor(Color.RED);
            g.fillRect(snake.food.pos.getX(), snake.food.pos.getY(), snake.CELL_SIZE, snake.CELL_SIZE);
        }

        public void chooseColor(Graphics g, int score) {
            if (score <= 30) {
                g.setColor(new Color(138, 46, 226));
            } else if (score <= 50) {
                g.setColor(Color.cyan);
            } else if (score <= 70) {
                g.setColor(Color.blue);
            } else if (score <= 90) {
                g.setColor(Color.green);
            } else if (score <= 110) {
                g.setColor(Color.yellow);
            } else if (score <= 130) {
                g.setColor(Color.ORANGE);
            } else if (score <= 150) {
                g.setColor(Color.RED);
            } else {
                int r_ = (int) (Math.random() * 256);
                int g_ = (int) (Math.random() * 256);
                int b_ = (int) (Math.random() * 256);
                g.setColor(new Color(r_, g_, b_));
            }
        }

    }
}

