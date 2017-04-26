package javapaint;

import java.awt.*;

public abstract class Shape 
{

    private int x1;
    private int x2;
    private int y1;
    private int y2;
    private float[] dashLength;
    private boolean dashed;
    private boolean filled;
    private int width;
    private float dashWidth[] = {5};
    private Paint paint;

    public Shape(int x1, int y1, Paint paint, boolean filled, boolean dashed, int width, float dashWidth) 
    {
        this.x1 = x1;
        this.x2 = x1;
        this.y1 = y1;
        this.y2 = y1;
        this.paint = paint;
        this.filled = filled;
        this.dashed = dashed;
        this.width = width;
        this.dashWidth[0] = dashWidth;
    }

    public boolean isFilled() 
    {
        return filled;
    }

    public void setFilled(boolean filled) 
    {
        this.filled = filled;
    }

    public boolean isDashed() 
    {
        return dashed;
    }

    public void setDashed(boolean dashed) 
    {
        this.dashed = dashed;
    }

    public int getX1() 
    {
        return x1;
    }

    public void setX1(int x1) 
    {
        this.x1 = x1;
    }

    public int getX2() 
    {
        return x2;
    }

    public void setX2(int x2) 
    {
        this.x2 = x2;
    }

    public int getY1() 
    {
        return y1;
    }

    public void setY1(int y1) 
    {
        this.y1 = y1;
    }

    public int getY2() 
    {
        return y2;
    }

    public void setY2(int y2) 
    {
        this.y2 = y2;
    }
    public float[] getDashWidth() 
    {
        return dashWidth;
    }

    public int getWidth() 
    {
        return this.width;
    }

    public Paint getPaint() 
    {
        return this.paint;
    }

    public abstract void draw(Graphics g);
}