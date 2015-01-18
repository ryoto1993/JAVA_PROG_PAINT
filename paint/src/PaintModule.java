import javax.swing.*;
import javax.swing.event.MouseInputListener;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

class PaintModule extends JPanel implements MouseInputListener {
       public enum Mode {
        PEN, BRUSH, ERASER
    }
    private Point start = new Point(-10, -10);
    private Point end = new Point(-10, -10);
    public Mode mode = Mode.PEN;
    public Point current = new Point();
    public Point size = new Point(500, 380);
    public JLabel coordinate = new JLabel("(0, 0)");
    public JLabel sizeLabel = new JLabel();
    private Line2D.Double line = new Line2D.Double();
    private Shape BasicPen,BasicEllipse,BasicRectangle;
    Color currentColor = Color.BLACK;
    BufferedImage image, canvas = null;

    public PaintModule() {
        super.setVisible(true);

        this.BasicPen =new Line2D.Double ();
        this.BasicEllipse= new Ellipse2D.Double();
        this.BasicRectangle= new Rectangle2D.Double();
        this.setBackground(new Color(255, 255, 255));
        this.setForeground(new Color(255, 255, 255));
        this.setBackground(Color.white);
        this.setPreferredSize(new Dimension(size.x, size.y));
        sizeLabel.setText(size.x +"×"+ size.y);
        addMouseMotionListener(this);
    }

    public void changeSize(Point newSize) {
            super.setVisible(false);
            this.setPreferredSize(new Dimension(size.x, size.y));
            size.setLocation(newSize);
            this.setSize(new Dimension(size.x, size.y));
            sizeLabel.setText(size.x + "×" + size.y);
            canvas = (BufferedImage) createImage(size.x, size.y);
            super.setVisible(true);

    }

    public void setImage (BufferedImage img) {
        image = img;
        canvas = null;

        start.x = -10;
        start.y = -10;
        end.x = -10;
        end.y = -10;

        repaint();
    }

    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D)g;

        if(canvas==null)
            canvas = (BufferedImage)createImage(size.x, size.y);

        Graphics2D buf = canvas.createGraphics();
        buf.drawImage(image, 0, 0, null);
        buf.drawImage(canvas, 0, 0, null);
        buf.setStroke(new BasicStroke(3.0f));
        buf.setPaint(currentColor);
        line.setLine(start, end);
        buf.draw(line);
        start.setLocation(end);
        g2.drawImage(canvas, 0, 0, null);
        buf.dispose();
    }

    public void setColor(Color color) {
        currentColor = color;
    }

    public BufferedImage getCanvas(){
        return canvas;
    }

    public void mouseDragged(MouseEvent e) {
        end.setLocation(e.getPoint());
        current.setLocation(e.getPoint());
        coordinate.setText("(" + e.getX() + ", " + e.getY() + ")");
        repaint();
    }

    public void mouseMoved(MouseEvent e) {
        start.setLocation(e.getPoint());
        current.setLocation(e.getPoint());
        coordinate.setText("(" + e.getX() + ", " + e.getY() + ")");
    }

    public void mouseClicked(MouseEvent e) {

    }

    public void mousePressed(MouseEvent e) {

    }

    public void mouseReleased(MouseEvent e) {

    }

    public void mouseEntered(MouseEvent e) {

    }

    public void mouseExited(MouseEvent e) {

    }
}