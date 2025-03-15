import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Tile implements ActionListener {
    private MyPanel panel;
    private JLabel label;
    private World mainWorld;
    private ImageIcon image;

    private int x, y;

    private Parameters parameters;
    private JButton button;

    private Shape sh;


    Tile(int X,int Y,int widthX,int widthY, Parameters parameters, World mainWorld){
        this.mainWorld = mainWorld;
        this.parameters = parameters;
        this.x = X;
        this.y = Y;
        this.label = new JLabel();
        this.image = new ImageIcon("Images/default.png");

        this.label.setIcon(image);
        if(parameters.mode == 0)
            this.label.setBounds(0,0, parameters.widthX, parameters.widthY);
        else if(parameters.mode == 1)
            this.label.setBounds(0,0, (int)(parameters.widthX*1.2), (int)(parameters.widthY*1.2));
        this.label.setVerticalAlignment(JLabel.CENTER);
        this.label.setHorizontalAlignment(JLabel.CENTER);

        this.button = new JButton();
        this.button.setBounds(widthX/4,widthY/4, (int)(parameters.widthX*0.75), (int)(parameters.widthY*0.75));
        this.button.setOpaque(false);
        this.button.setContentAreaFilled(false);
        this.button.setBorderPainted(false);
        this.button.addActionListener(this);

        this.panel = new MyPanel(parameters);
        this.panel.setBackground(parameters.defaultColor);
        //this.panel.setOpaque(false);
        setPanelBounds();

        this.panel.setLayout(null);

        this.panel.add(this.label);
        this.panel.add(this.button);

    }

    public void setPanelBounds(){
        if(parameters.mode == 0)
            this.panel.setBounds((parameters.widthX)*(x+1) , (parameters.widthY)*(y+1), parameters.widthX, parameters.widthY);
        else if(parameters.mode == 1)
        {
            if(y%2==1)
                this.panel.setBounds((int)((parameters.widthX)*(x+1.25)) , (int)((parameters.widthY*0.9)*(y+1)), (int)( parameters.widthX*1.2), (int)( parameters.widthY*1.2));
            else
                this.panel.setBounds((int)((parameters.widthX)*(x+0.75)) , (int)((parameters.widthY*0.9)*(y+1)), (int)( parameters.widthX*1.2), (int)(parameters.widthY*1.2));
        }
    }


    public void actionPerformed(ActionEvent e){
        String resultName;
        if(e.getSource() == this.button){
            if (mainWorld.getOrganismsArray()[x][y] != null)
            {
                resultName = mainWorld.getOrganismsArray()[x][y].getThisName();
                //this.mainWorld.getOrganismsArray()[x][y].pritData();
            }
            else
            {
                resultName = "Empty";
                //System.out.println("Empty");
            }

            this.mainWorld.getFrame().setTileTitle(resultName + " ["+x+","+y+"]");
            this.mainWorld.setActiveTile(x,y);
        }
    }

    public JPanel getPanel(){
        return panel;
    }

    public ImageIcon getImage(){
        return image;
    }

    public void setImage(ImageIcon image)
    {
        this.image = image;
        this.label.setIcon(image);
    }


}
