import javax.swing.*;

public class Guarana extends Plant {
    Guarana(int x, int y, World mainWorld){
        super(x,y,mainWorld);
        img = new ImageIcon("Images/guarana.png");
        this.id = 3;
        this.power = 0;
        this.reproductionChance = 5;
    }

    Guarana(){
        super();
        img = new ImageIcon("Images/guarana.png");
        this.id = 3;
        this.power = 0;
        this.reproductionChance = 5;
    }

    @Override
    public String getThisName(){
        return "Guarana";
    }


    @Override
    Organism toAdd(int x, int y) {
        Guarana newGuarana = new Guarana(x,y,mainWorld);
        return newGuarana;
    }

    @Override
    public void attackedBy(Organism organism) {
        mainWorld.addLog(organism.getCharacteristics() + " increased its power by eating " + getCharacteristics());
        organism.setPower(organism.getPower()+3);
        kill();
    }
}
