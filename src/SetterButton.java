import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SetterButton implements ActionListener {

    JButton button;
    World mainWorld;

    Organism toSet;
    SetterButton(Organism toSet, int offset, World mainWorld){
        this.toSet = toSet;
        this.mainWorld = mainWorld;
        button = new JButton();
        button.addActionListener(this);
        button.setBounds((mainWorld.getParameters().widthX)*(mainWorld.getParameters().sizeX+2)+10,mainWorld.getParameters().widthY+offset,150,20);
        button.setText(toSet.getThisName());
    }

    public JButton getButton(){
        return button;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == button)
        {
            if(mainWorld.getActiveX() != -1 && mainWorld.getActiveY() != -1)
            {
                if(mainWorld.getOrganismsArray()[mainWorld.getActiveX()][mainWorld.getActiveY()] == null){
                    Organism newOrganism;
                    try{
                        newOrganism = toSet.getClass().newInstance();

                        newOrganism.setX(mainWorld.getActiveX());
                        newOrganism.setY(mainWorld.getActiveY());
                        newOrganism.setMainWorld(mainWorld);
                        if(newOrganism instanceof Animal){
                            Animal n = (Animal) newOrganism;
                            n.newX = n.getX();
                            n.newY = n.getY();
                        }

                        mainWorld.addOrganism(newOrganism);
                        mainWorld.drawWorld();
                    }
                    catch (Exception ex){
                        ex.printStackTrace();
                    }
                }
            }
        }
        mainWorld.getFrame().setFocusable(true);
        mainWorld.getFrame().requestFocus();
    }
}
