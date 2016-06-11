package com.example;

import javax.swing.*;
import java.awt.*;

/**
 * Created by L T on 2016/6/9.
 */
public class About extends JDialog {
    JTextArea info = new JTextArea();
    JScrollPane js;

    public About() {
        initUI();
    }

    private void initUI() {
        Container con = getContentPane();
        con.setBackground(Color.DARK_GRAY);
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();

        setSize(400, 300);
        setLocation((d.width - getWidth()) / 2, (d.height - getHeight()) / 2);
        info.setBackground(Color.darkGray);
        info.setForeground(Color.white);
        info.setSelectedTextColor(Color.RED);
        info.setText("\nAuthor: Tcong Li\nLicense: GPL v2.0\n\n" +
                "A simple SnakeGame.\nWelcome to fork & pull request.\n" +
                "https://github.com/TcongLi/SnakegameEx\n\n" +
                "This program is free software; you can redistribute it and/or modify it \n" +
                "under the terms of the GNU General Public License as published \n" +
                "by the Free Software Foundation; either version 2 of the License,\n " +
                "or (at your option) any later version.\n" );
        info.setEditable(false);
        js = new JScrollPane(info);
        add(js);

        setTitle("关于");
        setModal(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);
    }

}
