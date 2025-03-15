import javax.swing.*;

public class Wolf extends Animal {
    Wolf(int x, int y, World mainWorld){
        super(x,y,mainWorld);
        img = new ImageIcon("Images/wolf.png");
        this.id = 1;
        this.initiative = 5;
        this.power = 9;
    }

    Wolf(){
        super();
        img = new ImageIcon("Images/wolf.png");
        this.id = 1;
        this.initiative = 5;
        this.power = 9;
    }

    @Override
    boolean invincibility() {
        return false;
    }

    @Override
    public String getThisName(){
        return "Wolf";
    }


    @Override
    Organism toAdd(int x, int y) {
        Wolf newWolf = new Wolf(x,y,mainWorld);
        return newWolf;
    }
}
