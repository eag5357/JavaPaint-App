package javapaint;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

public class PaintArea extends JPanel 
{

    private ArrayList<Shape> shapes;
    private boolean filled;
    private boolean dashed;
    private int lineWidth;
    private float dashWidth;
    private JLabel coordStatus;
    private int currentShapeType;
    private Paint currentPaint;
    private Paint paint;
    

    public PaintArea(JLabel coordStatus) 
    {
        this.coordStatus = coordStatus;
        addMouseMotionListener(new MouseHandler());
        addMouseListener(new MouseHandler());
        
        this.currentShapeType = 0;
        this.paint = Color.BLACK;
        shapes = new ArrayList<Shape>();
        
        this.filled = false;
        this.dashed = false;
        
        this.lineWidth = 5;
        this.dashWidth = 5;
    }

    public int getLineWidth() { return this.lineWidth; }

    public void setCurrentShapeType(int currentShapeType) 
    {
        this.currentShapeType = currentShapeType;
    }

    

    public void setDashed(boolean dashed) 
    {
        this.dashed = dashed;
    }
    public void setFilled(boolean filled) 
    {
        this.filled = filled;
    }

    public void setLineWidth(int lineWidth) 
    {
        this.lineWidth = lineWidth;
    }

    public void setDashWidth(float dashWidth) 
    {
        this.dashWidth = dashWidth;
    }

    public void setPaint(Color color1, Color color2, boolean gradient) 
    {
        if (gradient) 
        {
            this.paint = new GradientPaint(0, 0, color1, 50, 50, color2, true);
        }
        else 
        {
            this.paint = color1;
        }
    }


    public void undo() 
    {
        shapes.remove(shapes.size() - 1);
        repaint();
    }

    public void clear() 
    {
        shapes.clear();
        repaint();
    }

    @Override
    public void paintComponent(Graphics g) 
    {
        super.paintComponent(g);
        for(Shape s : shapes) {
            s.draw(g);
        }
    }

    private class MouseHandler extends MouseAdapter implements MouseMotionListener 
    {
        private int startX;
        private int startY;
        @Override
        public void mousePressed(MouseEvent event) 
        {
            this.startX = event.getX();
            this.startY = event.getY();
            Shape s;
            if (currentShapeType == 0)
                s = new Line(startX, startY, paint, filled, dashed, lineWidth, dashWidth);
            else if (currentShapeType == 1)
                s = new Oval(startX, startY, paint, filled, dashed, lineWidth, dashWidth);
            else
                s = new Rectangle(startX, startY, paint, filled, dashed, lineWidth, dashWidth);
            shapes.add(s);
        }

        

        @Override
        public void mouseReleased(MouseEvent event) 
        {
            repaint();
        }
        
        @Override
        public void mouseDragged(MouseEvent event) 
        {
            shapes.get(shapes.size()-1).setX2(event.getX());
            shapes.get(shapes.size()-1).setY2(event.getY());
            repaint();
        }

        @Override
        public void mouseMoved(MouseEvent event) 
        {
            coordStatus.setText("(" + event.getX() + ", " + event.getY() + ")");
        }
    }

}