import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

class MainModule extends JFrame implements ActionListener {
    JMenuBar menu;
    JPanel paintBox, toolbox, optionbox, statusbar;
    JScrollPane mainbox;
    ColorPicker clr = new ColorPicker();
    private BufferedImage img;
    public PaintModule paintModule;

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
        this.setSize(700, 500);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setTitle("Simple Paint");
        this.setVisible(true);
    }

    public void manageComponent() {
        // create and set component
        menu = new JMenuBar();
        mainbox = new JScrollPane();
        toolbox = new JPanel();
        paintBox = new JPanel();
        optionbox = new JPanel();
        statusbar = new JPanel();
        paintModule = new PaintModule();

        // component setting
        createToolBox(toolbox);
        createMenu(menu);
        createOptionBox(optionbox);
        createStatusBar(statusbar);
        this.setLayout(new BorderLayout());

        // add component to container
        paintBox.add(paintModule);
        mainbox.setViewportView(paintBox);


        this.add(menu, BorderLayout.NORTH);
        this.add(mainbox, BorderLayout.CENTER);
        this.add(toolbox, BorderLayout.WEST);
        this.add(optionbox, BorderLayout.EAST);
        this.add(statusbar, BorderLayout.SOUTH);

    }

    public void createMenu(JMenuBar menu) {
        // component
        JMenu m_file, m_edit, m_canvas, m_help;
        JMenuItem mi_new, mi_save, mi_open, mi_exit, mi_setCanvasSize;

        // set component
        m_file = new JMenu("File");
        m_file.setMnemonic(KeyEvent.VK_F);

        mi_new = new JMenuItem("New File");
        mi_new.setMnemonic(KeyEvent.VK_N);
        mi_new.addActionListener(this);
        mi_new.setActionCommand("mi_new");

        mi_save = new JMenuItem("Save...");
        mi_save.setMnemonic(KeyEvent.VK_S);
        mi_save.addActionListener(this);
        mi_save.setActionCommand("mi_save");

        mi_open = new JMenuItem("Open...");
        mi_open.setMnemonic(KeyEvent.VK_O);
        mi_open.addActionListener(this);
        mi_open.setActionCommand("mi_open");

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
        mi_setCanvasSize.addActionListener(this);
        mi_setCanvasSize.setActionCommand("mi_set_size");

        m_help = new JMenu("Help");
        m_help.setMnemonic(KeyEvent.VK_H);

        // add into container
        menu.add(m_file);
        m_file.add(mi_new);
        m_file.add(mi_open);
        m_file.add(mi_save);
        m_file.addSeparator();
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
        Dimension buttonDimension = new Dimension(80, 30);
        box.setLayout(new BoxLayout(box, BoxLayout.Y_AXIS));
        box.setPreferredSize(new Dimension(70, this.getHeight()));
        toggleButtonPen.setMaximumSize(buttonDimension);
        toggleButtonPaintBrush.setMaximumSize(buttonDimension);
        toggleButtonEraser.setMaximumSize(buttonDimension);
        toggleButtonSelect.setMaximumSize(buttonDimension);
        toggleButtonSquare.setMaximumSize(buttonDimension);

        // add listener
        toggleButtonPen.addActionListener(this);
        toggleButtonPen.setActionCommand("tool_pen");
        toggleButtonPaintBrush.addActionListener(this);
        toggleButtonPaintBrush.setActionCommand("tool_brush");
        toggleButtonEraser.addActionListener(this);
        toggleButtonEraser.setActionCommand("tool_eraser");

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

    public void createOptionBox(JPanel box) {
        // create component
        JPanel simpleColorPicker = new JPanel(new GridLayout(13,2));
        JToggleButton[] buttons = new JToggleButton[26];
        JButton buttonColorPicker = new JButton("MORE");
        ButtonGroup buttonGroupColors = new ButtonGroup();

        for(int i=0; i<26; i++) {
            buttons[i] = new JToggleButton();
            buttonGroupColors.add(buttons[i]);
            buttons[i].addActionListener(this);
            buttons[i].setActionCommand("color" + i);
            buttons[i].setBackground(new Color(
                    clr.colorList[i][0],
                    clr.colorList[i][1],
                    clr.colorList[i][2]));
        }
        buttons[0].setSelected(true);

        // set component
        simpleColorPicker.setPreferredSize(new Dimension(70, 300));
        //box.setLayout(new BoxLayout(box, BoxLayout.Y_AXIS));
        box.setPreferredSize(new Dimension(70, this.getHeight()));

        // add into container
        for(int i=0; i<26; i++) {
            simpleColorPicker.add(buttons[i]);
        }

        box.add(simpleColorPicker);
        box.add(buttonColorPicker);
    }

    public void createStatusBar(JPanel bar) {
        // create component

        // set component
        bar.setPreferredSize(new Dimension(getWidth(), 25));
        bar.setBackground(Color.LIGHT_GRAY);

        // add into container
        bar.add(paintModule.sizeLabel);
        bar.add(paintModule.coordinate);

    }

    public void actionPerformed(ActionEvent e) {
        JFileChooser fileChooser = new JFileChooser();

        // menu item action
        if(e.getActionCommand().equals("mi_exit"))
            System.exit(1);

        if(e.getActionCommand().equals("mi_new")) {
            img = null;
            paintModule.setImage(img);
        }

        if(e.getActionCommand().equals("mi_set_size")) {
            CanvasSizeSetting dlg = new CanvasSizeSetting(this, paintModule.size);
            paintModule.changeSize(dlg.showDialog());
        }

        if(e.getActionCommand().equals("mi_save")) {
            FileFilter pngFilter = new FileNameExtensionFilter(
                    "PNG(Portable Network Graphics) files", "png");
            fileChooser.setFileFilter(pngFilter);
            if(fileChooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                try {
                    if (selectedFile.toString().substring(selectedFile.toString().length() - 4).equals(".png")) {
                        ImageIO.write(paintModule.getCanvas(), "png", selectedFile);
                    } else {
                        File rename = new File(selectedFile + ".png");
                        selectedFile.renameTo(rename);
                        ImageIO.write(paintModule.getCanvas(), "png", rename);
                    }

                } catch(IOException exc) {
                    exc.printStackTrace();
                }
            }

        }

        // tool button action
        if(e.getActionCommand().equals("tool_pen"))
            paintModule.mode = PaintModule.Mode.PEN;

        if(e.getActionCommand().equals("tool_brush"))
            paintModule.mode = PaintModule.Mode.BRUSH;

        if(e.getActionCommand().equals("tool_eraser"))
            paintModule.mode = PaintModule.Mode.ERASER;

        // option button action
        for(int i=0; i<24; i++) {
            if(e.getActionCommand().equals("color"+i)) {
                paintModule.setColor(new Color(
                        clr.colorList[i][0],
                        clr.colorList[i][1],
                        clr.colorList[i][2]));
            }
        }
    }
}