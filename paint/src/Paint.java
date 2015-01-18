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
    JMenuBar menu = new JMenuBar();
    JPanel mainbox, toolbox, optionbox, statusbox;

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
        mainbox = new JPanel();
        toolbox = new JPanel();
        optionbox = new JPanel();
        statusbox = new JPanel();

        createMenu(menu);

        // component setting
        this.setLayout(new BorderLayout());

        // add handler


        // add component to container
        this.add(menu, BorderLayout.NORTH);
        this.add(mainbox, BorderLayout.CENTER);
        this.add(toolbox, BorderLayout.WEST);
        this.add(optionbox, BorderLayout.EAST);
        this.add(statusbox, BorderLayout.SOUTH);

    }

    public void createMenu(JMenuBar menu) {
        // component
        JMenu m_file, m_edit, m_canvas, m_help;
        JMenuItem menuItemExit;

        // set component
        m_file = new JMenu("File");
        m_file.setMnemonic(KeyEvent.VK_F);

        menuItemExit = new JMenuItem("Exit");
        menuItemExit.setMnemonic(KeyEvent.VK_X);
        menuItemExit.addActionListener(this);
        menuItemExit.setActionCommand("mi_exit");

        m_edit = new JMenu("Edit");
        m_edit.setMnemonic(KeyEvent.VK_E);

        m_canvas = new JMenu("Canvas");
        m_canvas.setMnemonic(KeyEvent.VK_C);

        m_help = new JMenu("Help");
        m_help.setMnemonic(KeyEvent.VK_H);

        // add into container
        menu.add(m_file);
        m_file.add(menuItemExit);

        menu.add(m_edit);

        menu.add(m_canvas);

        menu.add(m_help);
    }

    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand()=="mi_exit") System.exit(1);
    }
}