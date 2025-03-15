import javax.swing.*;
import java.awt.*;

public class MyPanel extends JPanel {

    private Parameters parameters;
    MyPanel(Parameters parameters)
    {
        super();
        this.parameters = parameters;
    }

    @Override
    public void paint(Graphics g){

        Graphics2D g2D = (Graphics2D) g;

        if(parameters.mode == 0){
            g2D.setClip(new Rectangle(0,0,this.getWidth(),this.getHeight()));
            super.paint(g);

            g2D.setPaint(new Color(0x000000));
            g2D.setStroke(new BasicStroke(2));

            g2D.drawRect(0,0,this.getWidth()-1,this.getHeight()-1);

        }


        if(parameters.mode == 1)
        {
            int[] xPoly = new int[6];
            int[] yPoly = new int[6];
            for(int i = 0; i < 6; i++){
                xPoly[i] = (int)(this.getWidth()*(Math.sin(((double) (i+1.0)/3)*Math.PI)+1)/2);
                yPoly[i] = (int)(this.getWidth()*(Math.cos(((double) (i+1.0)/3)*Math.PI)+1)/2);
                //System.out.println("cos"+yPoly[i]+" "+((double)i/24)*Math.PI);
            }
            g2D.setClip(new Polygon(xPoly,yPoly,xPoly.length));
            super.paint(g);

            g2D.setPaint(new Color(0x000000));
            g2D.setStroke(new BasicStroke(4));

            g2D.drawPolygon(xPoly,yPoly,xPoly.length);
        }


    }

    public void setParameters(Parameters parameters){
        this.parameters = parameters;
    }
}
