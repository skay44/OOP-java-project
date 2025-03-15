import javax.swing.*;

public class Dandelion extends Plant {
    Dandelion(int x, int y, World mainWorld){
        super(x,y,mainWorld);
        img = new ImageIcon("Images/dandelion.png");
        this.id = 2;
        this.power = 0;
        this.reproductionChance = 10;
    }

    Dandelion(){
        super();
        img = new ImageIcon("Images/dandelion.png");
        this.id = 2;
        this.power = 0;
        this.reproductionChance = 10;
    }

    @Override
    public String getThisName(){
        return "Dandelion";
    }

    @Override
    public void action(){
        for(int i = 0; i < 3; i++)
            super.action();
    }

    @Override
    Organism toAdd(int x, int y) {
        Dandelion newDandelion = new Dandelion(x,y,mainWorld);
        return newDandelion;
    }
}
