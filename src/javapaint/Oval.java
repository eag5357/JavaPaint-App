package javapaint;

import java.awt.*;

public class Oval extends Shape 
{

    public Oval(int x1, int y1, Paint paint, boolean filled, boolean dashed, int width, float dashWidth) 
    {
        super(x1, y1, paint, filled, dashed, width, dashWidth);
    }

    public void draw(Graphics g) 
    {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setPaint(this.getPaint());
        int width = Math.abs(this.getX1() - this.getX2());
        int height = Math.abs(this.getY1() - this.getY2());
        int smallX = this.getX1() < this.getX2() ? this.getX1() : this.getX2();
        int smallY = this.getY1() < this.getY2() ? this.getY1() : this.getY2();
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
        if (this.isFilled()) 
        {
            g2d.fillOval(smallX, smallY, width, height);
        }
        else 
        {
            g2d.drawOval(smallX, smallY, width, height);
        }
    }

}