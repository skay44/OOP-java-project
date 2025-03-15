import javax.swing.*;
import java.util.Random;

public class Turtle extends Animal{
    Turtle(int x, int y, World mainWorld){
        super(x,y,mainWorld);
        img = new ImageIcon("Images/turtle.png");
        this.id = 4;
        this.initiative = 1;
        this.power = 2;
    }

    Turtle(){
        super();
        img = new ImageIcon("Images/turtle.png");
        this.id = 4;
        this.initiative = 1;
        this.power = 2;
    }

    @Override
    boolean invincibility() {
        return false;
    }

    @Override
    public String getThisName(){
        return "Turtle";
    }

    @Override
    public void action(){
        Random random = new Random();
        int rand = random.nextInt(4);
        if(rand == 0){
            super.action();
        }
    }

    @Override
    void attackedBy(Organism organism){
        if(organism instanceof Animal){
            Animal animal = (Animal) organism;
            if(animal.getPower() <= 5){
                if(newX == x && newY == y)// turtle is not an atacker
                {
                    mainWorld.addLog(getCharacteristics() + " defends from " + animal.getCharacteristics());
                    animal.newX = animal.getX();
                    animal.newY = animal.getY();
                    return;
                }
            }
        }
        kill();
    }

    @Override
    Organism toAdd(int x, int y) {
        Turtle newTurtle = new Turtle(x,y,mainWorld);
        return newTurtle;
    }
}
