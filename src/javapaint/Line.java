package javapaint;

import java.awt.*;



public class Line extends Shape 
{

    public Line(int x1, int y1, Paint paint, boolean filled, boolean dashed, int width, float dashWidth) 
    {
        super(x1, y1, paint, filled, dashed, width, dashWidth);
    }

    public void draw(Graphics g) 
    {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setPaint(this.getPaint());
        Stroke myStroke;
        if (this.isDashed()) 
        {
            myStroke = new BasicStroke(this.getWidth(), BasicStroke.CAP_ROUND, BasicStroke.JOIN_BEVEL, 10,
                    this.getDashWidth(), 0);
        }
        else 
        {
            myStroke = new BasicStroke(this.getWidth(), BasicStroke.CAP_ROUND, BasicStroke.JOIN_BEVEL);
        }
        g2d.setStroke(myStroke);
        g2d.drawLine(this.getX1(), this.getY1(), this.getX2(), this.getY2());
    }

}