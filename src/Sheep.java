import javax.swing.*;

public class Sheep extends Animal{
    Sheep(int x, int y, World mainWorld){
        super(x,y,mainWorld);
        img = new ImageIcon("Images/sheep.png");
        this.id = 2;
        this.initiative = 4;
        this.power = 4;
    }

    Sheep(){
        super();
        img = new ImageIcon("Images/sheep.png");
        this.id = 2;
        this.initiative = 4;
        this.power = 4;
    }

    @Override
    boolean invincibility() {
        return false;
    }

    @Override
    public String getThisName(){
        return "Sheep";
    }


    @Override
    Organism toAdd(int x, int y) {
        Sheep newSheep = new Sheep(x,y,mainWorld);
        return newSheep;
    }


}
