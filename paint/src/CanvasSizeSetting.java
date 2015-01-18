/*
ID:   1G130071
NAME: 富岡亮登
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CanvasSizeSetting extends JDialog implements ActionListener {
    JPanel frame = new JPanel();
    JPanel message = new JPanel();
    JPanel sizer = new JPanel();
    JPanel buttons = new JPanel();
    JLabel lbl = new JLabel("Set new canvas size.");
    JLabel mul = new JLabel("×");
    JLabel px = new JLabel("px");
    JButton ok = new JButton("Apply");
    JButton cancel = new JButton("Cancel");
    JTextField fx = new JTextField();
    JTextField fy = new JTextField();
    public Point result = new Point();

    public CanvasSizeSetting(JFrame parent,Point size) {
        this.setModal(true);
        result = size;

        frame.setLayout(new BoxLayout(frame, BoxLayout.Y_AXIS));
        fx.setText(Integer.toString(size.x));
        fy.setText(Integer.toString(size.y));
        fx.setPreferredSize(new Dimension(40, 30));
        fy.setPreferredSize(new Dimension(40, 30));
        ok.addActionListener(this);
        ok.setActionCommand("ok");
        cancel.addActionListener(this);
        cancel.setActionCommand("cancel");

        message.add(lbl);
        sizer.add(fx);
        sizer.add(mul);
        sizer.add(fy);
        sizer.add(px);
        buttons.add(ok);
        buttons.add(cancel);

        frame.add(message);
        frame.add(sizer);
        frame.add(buttons);

        this.add(frame);

        this.setSize(200, 160);
        this.setLocationRelativeTo(parent);
        this.setTitle("Canvas Size");

    }

    public Point showDialog() {
        this.setVisible(true);
        return result;
    }

    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand()=="cancel")
            this.dispose();
        if(e.getActionCommand()=="ok") {
            try {
                result.x = Integer.parseInt(fx.getText());
                result.y = Integer.parseInt(fy.getText());
            } catch (Exception er) {}
            this.dispose();

        }
    }
}
