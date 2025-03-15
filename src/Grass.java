import javax.swing.*;

public class Grass extends Plant{


    Grass(int x, int y, World mainWorld){
        super(x,y,mainWorld);
        img = new ImageIcon("Images/grass.png");
        this.id = 1;
        this.power = 0;
        this.reproductionChance = 10;
    }

    Grass(){
        super();
        img = new ImageIcon("Images/grass.png");
        this.id = 1;
        this.power = 0;
        this.reproductionChance = 10;
    }

    @Override
    public String getThisName(){
        return "Grass";
    }


    @Override
    Organism toAdd(int x, int y) {
        Grass newGrass = new Grass(x,y,mainWorld);
        return newGrass;
    }
}
