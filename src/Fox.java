import javax.swing.*;
import java.util.Random;

public class Fox extends Animal{
    Fox(int x, int y, World mainWorld){
        super(x,y,mainWorld);
        img = new ImageIcon("Images/fox.png");
        this.id = 3;
        this.initiative = 7;
        this.power = 3;
    }

    Fox(){
        super();
        img = new ImageIcon("Images/fox.png");
        this.id = 3;
        this.initiative = 7;
        this.power = 3;
    }

    @Override
    boolean invincibility() {
        return false;
    }

    @Override
    public String getThisName(){
        return "Fox";
    }

    private boolean checkSafe(int nx, int ny){
        if(mainWorld.getOrganismsArray()[nx][ny] != null){
            if(mainWorld.getOrganismsArray()[nx][ny].getPower() > this.power){
                mainWorld.addLog(getCharacteristics() + " avoids danger from " + mainWorld.getOrganismsArray()[nx][ny].getCharacteristics());
                return false;
            }
        }
        return true;
    }
    @Override
    public void action(){
        Random rand = new Random();
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
            if(this.x > 0 && checkSafe(x-1,y))
            {
                moveX = -1;
                success = true;
            }
        }
        else if(direction == 1)//right
        {
            if(this.x < mainWorld.getParameters().sizeX-1  && checkSafe(x+1,y))
            {
                moveX = 1;
                success = true;
            }
        }
        else if(direction == 2)//top
        {
            if(this.y > 0  && checkSafe(x,y-1))
            {
                moveY = -1;
                success = true;
            }
        }
        else if(direction == 3)//bottom
        {
            if(this.y < mainWorld.getParameters().sizeY-1  && checkSafe(x,y+1))
            {
                moveY = 1;
                success = true;
            }
        }
        else if(direction == 4)//left top or right top
        {
            if(y%2 == 0 && this.y > 0 && this.x > 0  && checkSafe(x-1,y-1))//left top
            {
                moveY = -1;
                moveX = -1;
                success = true;
            }
            else if(this.y > 0 && this.x < mainWorld.getParameters().sizeX-1  && checkSafe(x+1,y-1)) //rigth top{
            {
                moveY = -1;
                moveX = 1;
                success = true;
            }
        }
        else if(direction == 5)//left bottom or right bottom
        {
            if(y%2 == 0 && this.y < mainWorld.getParameters().sizeY-1 && this.x > 0  && checkSafe(x-1,y+1))//left bottom
            {
                moveY = 1;
                moveX = -1;
                success = true;
            }
            else if(this.y < mainWorld.getParameters().sizeY-1 && this.x < mainWorld.getParameters().sizeX-1 && checkSafe(x+1,y+1)) //rigth bottom{
            {
                moveY = 1;
                moveX = 1;
                success = true;
            }
        }
        if(success == true){
            if(mainWorld.getOrganismsArray()[x+moveX][y+moveY] != null){
                this.newX = this.x + moveX;
                this.newY = this.y + moveY;
                collision(mainWorld.getOrganismsArray()[x+moveX][y+moveY]);
                //mainWorld.addLog(this.getCharacteristics()+" tried to reproduce but collided with "+mainWorld.getOrganismsArray()[x+moveX][y+moveY].getCharacteristics());
            }
            else {
                this.newX = this.x + moveX;
                this.newY = this.y + moveY;
                changePosition();
            }
        }
    }

    @Override
    Organism toAdd(int x, int y) {
        Fox newFox = new Fox(x,y,mainWorld);
        return newFox;
    }


}
