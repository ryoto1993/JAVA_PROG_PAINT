import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

public class Paint {
    public static void main(String[] args) {

        MainModule MainModule = new MainModule();

    }


}

class MainModule extends JFrame {
    JPanel mainbox, toolbox, optionbox, statusbox;
    JMenuBar menu = new JMenuBar();
    JMenu m_file;
    JMenuItem menuItemExit;

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

        m_file = new JMenu("File");
        m_file.setMnemonic(KeyEvent.VK_F);

        menuItemExit = new JMenuItem("Exit");
        menuItemExit.setMnemonic(KeyEvent.VK_X);


        // component setting
        this.setLayout(new BorderLayout());

        // add handler


        // add component to container
        this.add(menu, BorderLayout.NORTH);
        this.add(mainbox, BorderLayout.CENTER);
        this.add(toolbox, BorderLayout.WEST);
        this.add(optionbox, BorderLayout.EAST);
        this.add(statusbox, BorderLayout.SOUTH);

        menu.add(m_file);

        m_file.add(menuItemExit);

    }


}