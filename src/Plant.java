import javax.swing.*;
import java.util.Random;

public abstract class Plant extends Organism{

    protected int reproductionChance;
    Plant(int x, int y, World mainWorld){
        super(x,y,mainWorld);
        this.initiative = 0;
    }

    Plant(){
        super();
        this.initiative = 0;
    }

    @Override
    public void collision(Organism collidingWith){
        if(collidingWith instanceof Plant){
            Plant collided = (Plant) collidingWith;
            if(collided.getId() != this.id){
                if(collided.getPower() <= this.power){
                    if(collided.invincibility() == false){
                        mainWorld.addLog(this.getCharacteristics() + " overgrows " + collided.getCharacteristics());
                        collidingWith.attackedBy(this);
                        Organism reproduced = toAdd(collidingWith.getX(),collidingWith.getY());
                        mainWorld.addOrganism(reproduced);
                    }
                }
            }
        }
        /*
        else if(collidingWith instanceof Animal){
            Animal collided = (Animal) collidingWith;
            if( collided.getPower() <= power){
                if(collided.invincibility() == false){
                    collided.attackedBy(this);
                    //mainWorld.getOrganismsArray()[collidingWith.x][collidingWith.y] = null;
                    mainWorld.addLog(collidingWith.getCharacteristics() + " eats and gests poisoned by " + this.getCharacteristics());
                }
                else{
                    collided.action();
                    mainWorld.addLog(collidingWith.getCharacteristics() + " avoids eating " + this.getCharacteristics());
                }
            }
            mainWorld.addLog(this.getCharacteristics()+" Collided with animal");
            //todo
        }*/
    }

    @Override
    public void action(){

        Random rand = new Random();
        int reproduction = rand.nextInt(100)+1;
        if(reproductionChance > reproduction){
            int direction;
            if(mainWorld.getParameters().mode == 0)
                direction = rand.nextInt(4);
            else
                direction = rand.nextInt(6);
            int moveX = 0;
            int moveY = 0;
            boolean success = false;
            if(direction == 0)//left
            {
                if(this.x > 0)
                {
                    moveX = -1;
                    success = true;
                }
            }
            else if(direction == 1)//right
            {
                if(this.x < mainWorld.getParameters().sizeX-1)
                {
                    moveX = 1;
                    success = true;
                }
            }
            else if(direction == 2)//top
            {
                if(this.y > 0)
                {
                    moveY = -1;
                    success = true;
                }
            }
            else if(direction == 3)//bottom
            {
                if(this.y < mainWorld.getParameters().sizeY-1)
                {
                    moveY = 1;
                    success = true;
                }
            }
            else if(direction == 4)//left top or right top
            {
                if(y%2 == 0 && this.y > 0 && this.x > 0)//left top
                {
                    moveY = -1;
                    moveX = -1;
                    success = true;
                }
                else if(this.y > 0 && this.x < mainWorld.getParameters().sizeX-1) //rigth top{
                {
                    moveY = -1;
                    moveX = 1;
                    success = true;
                }
            }
            else if(direction == 5)//left bottom or right bottom
            {
                if(y%2 == 0 && this.y < mainWorld.getParameters().sizeY-1 && this.x > 0)//left bottom
                {
                    moveY = 1;
                    moveX = -1;
                    success = true;
                }
                else if(this.y < mainWorld.getParameters().sizeY-1 && this.x < mainWorld.getParameters().sizeX-1) //rigth bottom{
                {
                    moveY = 1;
                    moveX = 1;
                    success = true;
                }
            }
            if(success == true){
                if(mainWorld.getOrganismsArray()[x+moveX][y+moveY] != null){
                    collision(mainWorld.getOrganismsArray()[x+moveX][y+moveY]);

                    //mainWorld.addLog(this.getCharacteristics()+" tried to reproduce but collided with "+mainWorld.getOrganismsArray()[x+moveX][y+moveY].getCharacteristics());
                }
                else {
                    Organism reproduced = toAdd(x+moveX,y+moveY);
                    mainWorld.addOrganism(reproduced);
                    mainWorld.addLog(this.getCharacteristics()+" repreoduced -> "+reproduced.getCharacteristics());
                }
            }
        }
    }

    @Override
    public void attackedBy(Organism organism){
        kill();
    }
    @Override
    public boolean invincibility(){
        return false;
    }
}
