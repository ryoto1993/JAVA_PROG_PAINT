import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.event.MouseInputListener;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

class PaintModule extends JPanel implements MouseInputListener {
       public enum Mode {
        PEN, LINE, ERASER, RECTANGLE
    }
    private Point startPoint, endPoint, draggedPoint, releasedPoint;
    BufferedImage canvas;
    public static final Point ZEROPOINT = new Point(0,0);
    private Shape BasicPen,BasicEllipse,BasicRectangle;
    public Mode mode;
    public Point current = new Point();

    public Point size = new Point(500, 380);
    public JLabel coordinate = new JLabel("(0, 0)");
    public JLabel sizeLabel = new JLabel();

    Color currentColor = Color.BLACK;


    public PaintModule() {
        this.startPoint = new Point(0,0);
        this.endPoint = new Point(0,0);
        this.draggedPoint = new Point(0,0);
        this.releasedPoint = new Point(0,0);
        this.BasicPen =new Line2D.Double ();
        this.BasicEllipse= new Ellipse2D.Double();
        this.BasicRectangle= new Rectangle2D.Double();

        sizeLabel.setText(size.x +"×"+ size.y);
        this.setPreferredSize(new Dimension(size.x, size.y));

        super.setVisible(true);

        this.setBackground(new Color(255, 255, 255));
        this.setForeground(new Color(255, 255, 255));
        this.setBorder(new LineBorder(Color.BLACK, 1, true));
        this.setName("canvas");
        this.setBackground(Color.white);
        this.canvas = null;

        repaint();

        addMouseMotionListener(this);
        addMouseListener(this);

        mode = Mode.PEN;

        // this.setPreferredSize(new Dimension(size.x, size.y));

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

    public void setMode(Mode m) {
        this.mode = m;
    }

    public void clearCanvas (){
        this.canvas.flush();
        this.canvas=null;
    }

    public void setCanvas (BufferedImage b){
        this.canvas=b;
    }

    public BufferedImage getCanvas(){
        return this.canvas;
    }


    public void paint(Graphics g) {
        Graphics2D tmpG = (Graphics2D)g;
        if(canvas==null)
            canvas = (BufferedImage)createImage(size.x, size.y);
        Graphics2D globalG = canvas.createGraphics();
        tmpG.drawImage(canvas, 0, 0, null);

        switch (mode) {
            case PEN:
                writeFreeHand(globalG, this.BasicPen,
                        currentColor, new BasicStroke(3.0f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND),
                        this.startPoint, this.endPoint);
                break;
            case LINE:
                writeLine(tmpG,this.BasicPen,
                        currentColor,
                        new BasicStroke(3.0f,BasicStroke.CAP_ROUND  ,BasicStroke.JOIN_ROUND)
                        ,this.startPoint,this.endPoint);
                break;
            case RECTANGLE:
                writeRectangle(tmpG,this.BasicRectangle,
                        currentColor,
                        new BasicStroke(3.0f,BasicStroke.CAP_ROUND  ,BasicStroke.JOIN_ROUND)
                        ,this.startPoint,this.endPoint);
                break;
        }

        globalG.dispose();
        tmpG.dispose();
    }

    private void writeFreeHand(Graphics2D graphics,Shape shape,Color c,Stroke s,Point start, Point end) {
        graphics.setColor(c);
        graphics.setStroke(s);
        ((Line2D) shape).setLine(start,end);
        if(!start.equals(ZEROPOINT) && !end.equals(ZEROPOINT))
            graphics.draw(shape);
        start.setLocation(end);
    }
    private void writeLine(Graphics2D graphics,Shape shape,Color c,Stroke s,Point start, Point end) {
        graphics.setColor(c);
        graphics.setStroke(s);
        ((Line2D) shape).setLine(start,end);
        if(!start.equals(ZEROPOINT) && !end.equals(ZEROPOINT)) {
            graphics.draw(shape);
        }
    }
    private void writeRectangle(Graphics2D graphics,Shape shape,Color c,Stroke s,Point start, Point end) {
        graphics.setColor(c);
        graphics.setStroke(s);
        ((Rectangle2D) shape).setRect(start.getX(),start.getY(),end.getX()-start.getX(), end.getY()-start.getY()) ;
        if(!start.equals(ZEROPOINT) && !end.equals(ZEROPOINT)) {
            graphics.draw(shape);
        }
    }

    public void setColor(Color color) {
        currentColor = color;
    }


    public void mouseDragged(MouseEvent e) {
        endPoint.setLocation(e.getPoint());
        current.setLocation(e.getPoint());
        coordinate.setText("(" + e.getX() + ", " + e.getY() + ")");
        repaint();
    }

    public void mouseMoved(MouseEvent e) {
        startPoint.setLocation(e.getPoint());
        current.setLocation(e.getPoint());
        coordinate.setText("(" + e.getX() + ", " + e.getY() + ")");
    }

    public void mouseClicked(MouseEvent e) {

    }

    public void mousePressed(MouseEvent e) {

    }

    public void mouseReleased(MouseEvent e) {
        switch (mode) {
            case PEN:
                break;
            case LINE:
                writeLine(this.canvas.createGraphics(),this.BasicPen,
                        currentColor,
                        new BasicStroke(3.0f,BasicStroke.CAP_ROUND  ,BasicStroke.JOIN_ROUND)
                        ,this.startPoint,this.endPoint);
                break;
            case RECTANGLE:
                writeRectangle(this.canvas.createGraphics(),this.BasicRectangle,
                        currentColor,
                        new BasicStroke(3.0f,BasicStroke.CAP_ROUND  ,BasicStroke.JOIN_ROUND)
                        ,this.startPoint,this.endPoint);
                break;
        }
    }

    public void mouseEntered(MouseEvent e) {

    }

    public void mouseExited(MouseEvent e) {
        this.endPoint.setLocation(0,0);
        this.startPoint.setLocation(0,0);
    }
}