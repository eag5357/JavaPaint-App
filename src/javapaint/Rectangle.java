package javapaint;

import java.awt.*;

public class Rectangle extends Shape {

    public Rectangle(int x1, int y1, Paint paint, boolean filled, boolean dashed, int width, float dashWidth) 
    {
        super(x1, y1, paint, filled, dashed, width, dashWidth);
    }

    public void draw(Graphics g) 
    {
        Graphics2D g2d = (Graphics2D) g;
        
        int height = Math.abs(this.getY1() - this.getY2());
        int width = Math.abs(this.getX1() - this.getX2());
        int smallX = this.getX1() < this.getX2() ? this.getX1() : this.getX2();
        int smallY = this.getY1() < this.getY2() ? this.getY1() : this.getY2();
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
        if (this.isFilled()) 
        {
            g2d.fillRect(smallX, smallY, width, height);
        }
        else 
        {
            g2d.drawRect(smallX, smallY, width, height);
        }
    }

}
