/*
ID:   1G130071
NAME: 富岡亮登
 */

import javax.swing.*;

public class About extends JDialog {
    JLabel name, id, date;

    public About(JFrame parent) {
        name = new JLabel("Author : Ryoto Tomioka");
        id = new JLabel("ID : 1G130071");
        date = new JLabel("Created : Jan. 19, 2015");

        getContentPane().setLayout(
                new BoxLayout(getContentPane(), BoxLayout.PAGE_AXIS)
        );

        this.add(name);
        this.add(id);
        this.add(date);

        this.setSize(180, 100);
        this.setLocationRelativeTo(parent);
        this.setTitle("About this App");
        this.setModal(true);
        this.setVisible(true);
    }
}
