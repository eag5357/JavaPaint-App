//Eric Galante
//CMPSC 221
//Project 5


package javapaint;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class JavaPaint extends JFrame 
{

    private final JPanel paintPanel;
    private JComboBox<String> drawOptions;
    private JButton undoButton;
    private JButton clearButton;
    private PaintArea drawArea;
    private Color color1 = Color.BLACK;
    private Color color2 = Color.BLACK;
    private JCheckBox filledBox;
    private JCheckBox gradientCheckbox;
    private JButton firstColorButton;
    private JButton secondColorButton;
    private JColorChooser firstColorChooser;
    private JColorChooser secondColorChooser;
    private JTextField lineWidth;
    private JTextField dashLength;
    private JCheckBox dashedCheckbox;

    public JavaPaint() 
    {
        this.setSize(720, 480);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        
        paintPanel = new JPanel();
        JPanel buttonMenu1 = new JPanel();
        JPanel buttonMenu2 = new JPanel();
        
        filledBox = new JCheckBox("Filled");
        buttonMenu1.add(filledBox);
        filledBox.addActionListener(new FilledCheckboxListener());
        
        gradientCheckbox = new JCheckBox("Use Gradient");
        gradientCheckbox.addActionListener(new GradientCheckboxListener());
        buttonMenu2.add(gradientCheckbox);

        
        undoButton = new JButton("Undo");
        buttonMenu1.add(undoButton);
        undoButton.addActionListener(new UndoButtonHandler());
        
        clearButton = new JButton("Clear");
        buttonMenu1.add(clearButton);
        clearButton.addActionListener(new ClearButtonHandler());
        
        String[] shapes = {"Line", "Oval", "Rectangle"};
        drawOptions = new JComboBox<String>(shapes);
        buttonMenu1.add(drawOptions);
        drawOptions.addActionListener(new ShapeTypeChangeHandler());
        
        
        firstColorButton = new JButton("1st Color");
        secondColorButton = new JButton("2nd Color");
        firstColorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                color1 = JColorChooser.showDialog(JavaPaint.this, "Choose a color", color1);
                drawArea.setPaint(color1, color2, gradientCheckbox.isSelected());
            }
        });
        secondColorButton.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                color2 = JColorChooser.showDialog(JavaPaint.this, "Choose a color", color1);
                drawArea.setPaint(color1, color2, gradientCheckbox.isSelected());
            }
        });
        buttonMenu2.add(firstColorButton);
        buttonMenu2.add(secondColorButton);
        
        lineWidth = new JTextField();
        lineWidth.setColumns(2);
        lineWidth.addActionListener(new WidthUpdateListener());
        buttonMenu2.add(new JLabel("Line Width: "));
        buttonMenu2.add(lineWidth);
        

        
        JLabel coords = new JLabel();
        drawArea = new PaintArea(coords);
        drawArea.setBackground(Color.WHITE);
        
        JPanel buttonMenu = new JPanel();
        buttonMenu.setLayout(new BorderLayout());
        buttonMenu.add(buttonMenu1, BorderLayout.NORTH);
        buttonMenu.add(buttonMenu2, BorderLayout.SOUTH);
        
        dashLength = new JTextField();
        dashLength.setColumns(2);
        dashLength.addActionListener(new DashWidthUpdateListener());
        buttonMenu2.add(new JLabel("Dash Length: "));
        buttonMenu2.add(dashLength);
        
        dashedCheckbox = new JCheckBox("Dashed");
        dashedCheckbox.addActionListener(new DashedCheckboxListener());
        buttonMenu2.add(dashedCheckbox);

        paintPanel.setLayout(new BorderLayout());
        paintPanel.add(buttonMenu, BorderLayout.NORTH);
        paintPanel.add(drawArea, BorderLayout.CENTER);
        paintPanel.add(coords, BorderLayout.SOUTH);
        add(paintPanel);
    }

    private class ShapeTypeChangeHandler implements ActionListener 
    {
        @Override
        public void actionPerformed(ActionEvent event) 
        {
            drawArea.setCurrentShapeType(drawOptions.getSelectedIndex());
        }
    }

    private class UndoButtonHandler implements ActionListener 
    {
        @Override
        public void actionPerformed(ActionEvent event) 
        {
            drawArea.undo();
        }
    }

    private class ClearButtonHandler implements ActionListener 
    {
        @Override
        public void actionPerformed(ActionEvent event) 
        {
            drawArea.clear();
        }
    }

    private class FilledCheckboxListener implements ActionListener 
    {
        @Override
        public void actionPerformed(ActionEvent e) 
        {
            drawArea.setFilled(filledBox.isSelected());
        }
    }

    private class DashedCheckboxListener implements ActionListener 
    {
        @Override
        public void actionPerformed(ActionEvent e) 
        {
            drawArea.setDashed(dashedCheckbox.isSelected());
        }
    }

    private class WidthUpdateListener implements ActionListener 
    {
        @Override
        public void actionPerformed(ActionEvent e) 
        {
            drawArea.setLineWidth(Integer.parseInt(lineWidth.getText()));
        }
    }

    private class DashWidthUpdateListener implements ActionListener 
    {
        @Override
        public void actionPerformed(ActionEvent e)  
        {
            drawArea.setDashWidth(Float.parseFloat(dashLength.getText()));
        }
    }

    private class GradientCheckboxListener implements ActionListener
    {
       
        @Override
        public void actionPerformed(ActionEvent e) 
        {
            drawArea.setPaint(color1, color2, gradientCheckbox.isSelected());
        }
    }

    public static void main(String args[]) 
    {
        JavaPaint javaPaint = new JavaPaint();
        javaPaint.setVisible(true);
    }

}