import javax.swing.*;

public class Antelope extends Animal {
    Antelope(int x, int y, World mainWorld){
        super(x,y,mainWorld);
        img = new ImageIcon("Images/antelope.png");
        this.id = 5;
        this.initiative = 4;
        this.power = 4;
        this.range = 2;
    }

    Antelope(){
        super();
        img = new ImageIcon("Images/antelope.png");
        this.id = 5;
        this.initiative = 4;
        this.power = 4;
        this.range = 2;
    }

    @Override
    boolean invincibility() {
        return false;
    }

    @Override
    public String getThisName(){
        return "Antelope";
    }


    @Override
    Organism toAdd(int x, int y) {
        Antelope newAntelope = new Antelope(x,y,mainWorld);
        return newAntelope;
    }

    @Override
    public boolean ifEscapes(){
        return true;
    }
}
