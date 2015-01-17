/*import java.awt.*;
import java.awt.event.*;
*/
import javax.swing.*;

public class Paint {


    public static void main(String[] args) {

        MainFrame MainFrame = new MainFrame();

    }


}

class MainFrame extends JFrame {
    public MainFrame() {
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

        // set frame
        this.setSize(650, 500);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setTitle("Simple Paint");
        this.setVisible(true);
    }
}