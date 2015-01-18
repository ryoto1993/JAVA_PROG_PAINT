import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;

class PaintModule extends JPanel implements MouseMotionListener {
    private Point start = new Point(-10, -10);
    private Point end = new Point(-10, -10);
    public Point current = new Point();
    public Point size = new Point(400, 300);
    public JLabel coordinate = new JLabel("(0, 0)");
    public JLabel sizeLabel = new JLabel();
    private Line2D.Double line = new Line2D.Double();
    BufferedImage image, canvas = null;

    public PaintModule() {
        super.setVisible(true);
        this.setPreferredSize(new Dimension(size.x, size.y));
        sizeLabel.setText(size.x +"×"+ size.y);
        addMouseMotionListener(this);
    }

    public void changeSize(Point newSize) {
        BufferedImage tmpCanvas = canvas;
        size = newSize;
        sizeLabel.setText(size.x +"×"+ size.y);
        canvas = (BufferedImage)createImage(size.x, size.y);
        Graphics2D buf = tmpCanvas.createGraphics();
        buf.drawImage(canvas, 0, 0, null);
        buf.dispose();
    }

    public void setImage (BufferedImage img) {
        image = img;
    }

    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D)g;

        if(canvas==null)
            canvas = (BufferedImage)createImage(size.x, size.y);

        Graphics2D buf = canvas.createGraphics();
        buf.setBackground(Color.WHITE);
        buf.drawImage(image, 0, 0, null);
        buf.drawImage(canvas, 0, 0, null);
        buf.setStroke(new BasicStroke(3.0f));
        buf.setPaint(Color.RED);
        line.setLine(start, end);
        buf.draw(line);
        start.setLocation(end);
        g2.drawImage(canvas, 0, 0, null);
        buf.dispose();
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
}