package com.example;

import sun.awt.image.OffScreenImage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * Created by L T on 2016/6/8.
 */
public class GameWindowSingle extends JFrame implements ActionListener{
    private Player player = new Player();
    private JButton btnStart = new JButton("开始");
    private JLabel scoreLabel = new JLabel("得分");
    private JLabel desc=new JLabel("控制方向: ↑,↓,←,→ (按住可加速)  "+" 返回主菜单: Esc");
    private JTextField score = new JTextField(10);
    private Snake snake;
    private boolean p1Run=false;


    public GameWindowSingle() {
        initUI();
        snake=new Snake(getWidth()-2*Snake.CELL_SIZE,getHeight()-4*Snake.CELL_SIZE,
                new Cell(Snake.CELL_SIZE*3,Snake.CELL_SIZE*3),Snake.DIR_RIGTH);


        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                switch (e.getKeyCode()){
                    case KeyEvent.VK_UP:
                        snake.changeDir(Snake.DIR_UP);
                        if(snake.dir==Snake.DIR_UP){
                            snake.DELAY=30;
                        }
                        break;
                    case KeyEvent.VK_DOWN:
                        snake.changeDir(Snake.DIR_DOWN);
                        if(snake.dir==Snake.DIR_DOWN){
                            snake.DELAY=30;
                        }
                        break;
                    case KeyEvent.VK_LEFT:
                        snake.changeDir(Snake.DIR_LEFT);
                        if(snake.dir==Snake.DIR_LEFT){
                            snake.DELAY=30;
                        }
                        break;
                    case KeyEvent.VK_RIGHT:
                        snake.changeDir(Snake.DIR_RIGTH);
                        if(snake.dir==Snake.DIR_RIGTH){
                            snake.DELAY=30;
                        }
                        break;
                    case KeyEvent.VK_ESCAPE:
                        p1Run=false;
                        dispose();
                        new SelectMode();
                    default:
                        break;
                }
            }

            @Override
            public void keyReleased(KeyEvent e){
                switch (e.getKeyCode()){
                    case KeyEvent.VK_UP:
                        if(snake.dir==Snake.DIR_UP){
                            snake.DELAY=100;
                        }
                        break;
                    case KeyEvent.VK_DOWN:
                        if(snake.dir==Snake.DIR_DOWN){
                            snake.DELAY=100;
                        }
                        break;
                    case KeyEvent.VK_LEFT:
                        if(snake.dir==Snake.DIR_LEFT){
                            snake.DELAY=100;
                        }
                        break;
                    case KeyEvent.VK_RIGHT:
                        if(snake.dir==Snake.DIR_RIGTH){
                            snake.DELAY=100;
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
        setExtendedState(MAXIMIZED_BOTH);
        //背景色
        Container con = getContentPane();
        con.setBackground(Color.DARK_GRAY);
        //
        JPanel top=new JPanel(new FlowLayout(FlowLayout.LEFT));
        top.setBackground(Color.DARK_GRAY);
        scoreLabel.setForeground(Color.white);
        score.setEditable(false);
        score.setText("0");
        score.setBackground(Color.darkGray);
        score.setForeground(Color.green);
        top.add(scoreLabel);
        top.add(score);
        btnStart.setBackground(Color.darkGray);
        btnStart.setForeground(Color.white);
        btnStart.addActionListener(this);
        top.add(btnStart);
        desc.setForeground(Color.WHITE);
        top.add(desc);
        con.add(top,"North");
        con.add(player,"Center");
        //pack();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        setVisible(true);
        requestFocus();
    }

    public void go(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                while(p1Run){
                    requestFocus();
                    refresh();
                    repaint();
                    try {
                        Thread.sleep(snake.DELAY);
                    } catch (InterruptedException e1) {
                        e1.printStackTrace();
                    }
                }
            }
        }).start();
    }
    public void refresh(){
        snake.move();
        score.setText(""+snake.score);
        if(snake.isGameOver()){
            p1Run=false;
            //btnStart.setEnabled(false);
            JOptionPane.showMessageDialog(null,"游戏结束");
            btnStart.setText("重新开始");
            snake.init(new Cell(snake.CELL_SIZE*3,snake.CELL_SIZE*3),Snake.DIR_RIGTH);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==btnStart){

            if(!p1Run){
                p1Run=true;
                go();
                btnStart.setText("暂停");
            }else{
                p1Run=false;
                btnStart.setText("开始");
            }
        }
    }

    class Player extends JPanel{
        @Override
        protected void paintComponent(Graphics g){
            g.setColor(Color.GRAY);
            g.fillRect(0,0,getWidth(),getHeight());

            chooseColor(g,snake.score);
            for (Cell cell : snake.cells) {
                    g.fillRect(cell.getX(), cell.getY(), snake.CELL_SIZE, snake.CELL_SIZE);
            }
            g.setColor(Color.RED);
            g.fillRect(snake.food.pos.getX(),snake.food.pos.getY(),snake.CELL_SIZE,snake.CELL_SIZE);
        }

        public void chooseColor(Graphics g,int score){
            if(score<=30){
                g.setColor(new Color(138,46,226));
            }else if(score<=50){
                g.setColor(Color.cyan);
            }else if(score<=70){
                g.setColor(Color.blue);
            }else if(score<=90){
                g.setColor(Color.green);
            }else if(score<=110){
                g.setColor(Color.yellow);
            }else if(score<=130){
                g.setColor(Color.ORANGE);
            }else if(score<=150){
                g.setColor(Color.RED);
            }else {
                int r_=(int)(Math.random()*256);
                int g_=(int)(Math.random()*256);
                int b_=(int)(Math.random()*256);
                g.setColor(new Color(r_,g_,b_));
            }
        }

    }
}
