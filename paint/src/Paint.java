import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class Paint {
    public static void main(String[] args) {

        MainModule MainModule = new MainModule();

    }
}

class MainModule extends JFrame implements ActionListener {
    JMenuBar menu;
    JPanel mainbox, toolbox, optionbox, statusbar;
    int point_x = 0, point_y = 0, canvas_x = 400, canvas_y = 300;

    public MainModule() {
        // set look and feel
        try {
            UIManager
                    .setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (ClassNotFoundException ignored) {
        } catch (InstantiationException e) {
        } catch (IllegalAccessException e) {
        } catch (UnsupportedLookAndFeelException e) {
        }
        SwingUtilities.updateComponentTreeUI(this);

        // manage component
        manageComponent();

        // set frame
        this.setSize(650, 500);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setTitle("Simple Paint");
        this.setVisible(true);
    }

    public void manageComponent() {
        // create and set component
        menu = new JMenuBar();
        mainbox = new JPanel();
        toolbox = new JPanel();
        optionbox = new JPanel();
        statusbar = new JPanel();

        // component setting
        createToolBox(toolbox);
        createMenu(menu);
        createStatusBar(statusbar);
        this.setLayout(new BorderLayout());

        // add component to container
        this.add(menu, BorderLayout.NORTH);
        this.add(mainbox, BorderLayout.CENTER);
        this.add(toolbox, BorderLayout.WEST);
        this.add(optionbox, BorderLayout.EAST);
        this.add(statusbar, BorderLayout.SOUTH);

    }

    public void createMenu(JMenuBar menu) {
        // component
        JMenu m_file, m_edit, m_canvas, m_help;
        JMenuItem mi_exit, mi_setCanvasSize;

        // set component
        m_file = new JMenu("File");
        m_file.setMnemonic(KeyEvent.VK_F);

        mi_exit = new JMenuItem("Exit");
        mi_exit.setMnemonic(KeyEvent.VK_X);
        mi_exit.addActionListener(this);
        mi_exit.setActionCommand("mi_exit");

        m_edit = new JMenu("Edit");
        m_edit.setMnemonic(KeyEvent.VK_E);

        m_canvas = new JMenu("Canvas");
        m_canvas.setMnemonic(KeyEvent.VK_C);

        mi_setCanvasSize = new JMenuItem("Set Canvas Size");
        mi_setCanvasSize.setMnemonic(KeyEvent.VK_S);
        mi_exit.addActionListener(this);
        mi_exit.setActionCommand("mi_set_size");

        m_help = new JMenu("Help");
        m_help.setMnemonic(KeyEvent.VK_H);

        // add into container
        menu.add(m_file);
        m_file.add(mi_exit);

        menu.add(m_edit);

        menu.add(m_canvas);
        m_canvas.add(mi_setCanvasSize);

        menu.add(m_help);
    }

    public void createToolBox(JPanel box) {
        // create component
        JToggleButton toggleButtonPen = new JToggleButton("Pen", true);
        JToggleButton toggleButtonPaintBrush = new JToggleButton("Brush");
        JToggleButton toggleButtonEraser = new JToggleButton("Eraser");
        JToggleButton toggleButtonSelect = new JToggleButton("Select");
        JToggleButton toggleButtonSquare = new JToggleButton("Square");

        // set component
        box.setLayout(new BoxLayout(box, BoxLayout.Y_AXIS));
        box.setPreferredSize(new Dimension(60, this.getHeight()));
        box.setBackground(Color.LIGHT_GRAY);

        // set button group
        ButtonGroup buttonGroupToolButton = new ButtonGroup();
        buttonGroupToolButton.add(toggleButtonPen);
        buttonGroupToolButton.add(toggleButtonPaintBrush);
        buttonGroupToolButton.add(toggleButtonEraser);
        buttonGroupToolButton.add(toggleButtonSelect);
        buttonGroupToolButton.add(toggleButtonSquare);

        // add into container
        box.add(toggleButtonPen);
        box.add(toggleButtonPaintBrush);
        box.add(toggleButtonEraser);
        box.add(toggleButtonSelect);
        box.add(toggleButtonSquare);

    }

    public void createStatusBar(JPanel bar) {
        // create component
        JLabel sizeinfo = new JLabel(
                canvas_x +"×"+ canvas_y +"    ("+ point_x +","+ point_y +")");

        // set component
        bar.setPreferredSize(new Dimension(getWidth(), 25));

        sizeinfo.setHorizontalAlignment(JLabel.LEFT);

        // add into container
        bar.add(sizeinfo);

    }

    public void actionPerformed(ActionEvent e) {
        // menu item action
        if(e.getActionCommand()=="mi_exit")
            System.exit(1);

        if(e.getActionCommand()=="mi_set_size") {

        }

        // tool button action
    }
}