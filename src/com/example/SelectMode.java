package com.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by L T on 2016/6/8.
 */
public class SelectMode extends JFrame implements ActionListener {
    private JButton btnSingle = new JButton("单人游戏");
    private JButton btnDouble = new JButton("双人游戏");
    private JButton btnDoubleRemote = new JButton("双人联机");
    private JButton btnSetting = new JButton("选项");
    private JButton btnAbout = new JButton("关于");
    private JButton btnExit = new JButton("退出");

    public SelectMode() {

        initUI();

        btnSingle.addActionListener(this);
        btnDouble.addActionListener(this);
        btnDoubleRemote.addActionListener(this);
        btnSetting.addActionListener(this);
        btnAbout.addActionListener(this);
        btnExit.addActionListener(this);

    }

    public void initUI() {
        setTitle("");
        setLayout(null);
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds(0, 0, d.width, d.height);
        add(btnSingle);
        add(btnDouble);
        add(btnDoubleRemote);
        add(btnSetting);
        add(btnAbout);
        add(btnExit);

        btnSingle.setBounds(getWidth() / 3, getHeight() / 8, getWidth() / 3, 50);
        btnDouble.setBounds(getWidth() / 3, getHeight() / 8 * 2, getWidth() / 3, 50);
        btnDoubleRemote.setBounds(getWidth() / 3, getHeight() / 8 * 3, getWidth() / 3, 50);
        btnSetting.setBounds(getWidth() / 3, getHeight() / 8 * 4, getWidth() / 3, 50);
        btnAbout.setBounds(getWidth() / 3, getHeight() / 8 * 5, getWidth() / 3, 50);
        btnExit.setBounds(getWidth() / 3, getHeight() / 8 * 6, getWidth() / 3, 50);

        btnSingle.setBackground(Color.white);
        btnDouble.setBackground(Color.white);
        btnDoubleRemote.setBackground(Color.white);
        btnSetting.setBackground(Color.white);
        btnAbout.setBackground(Color.white);
        btnExit.setBackground(Color.white);

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        getContentPane().setBackground(Color.DARK_GRAY);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnSingle) {
            dispose();
            new GameWindowSingle();
        } else if (e.getSource() == btnDouble) {
            dispose();
            new GameWindowDouble();
        } else if (e.getSource() == btnDoubleRemote) {
            //TODO
        } else if (e.getSource() == btnSetting) {
            //TODO
        } else if (e.getSource() == btnAbout) {
            new About();
        } else if (e.getSource() == btnExit) {
            System.exit(0);
        }
    }
}
