import javax.swing.*;

public class Human extends Animal {

    private int cooldown;
    private int duration;

    Human(int x, int y, World mainWorld){
        super(x,y,mainWorld);
        img = new ImageIcon("Images/human.png");
        this.id = 0;
        this.initiative = 4;
        this.power = 5;
        this.cooldown = mainWorld.getTurn();
        this.duration = mainWorld.getTurn();
    }

    Human(){
        super();
        img = new ImageIcon("Images/human.png");
        this.id = 0;
        this.initiative = 4;
        this.power = 4;
        this.cooldown = mainWorld.getTurn();
        this.duration = mainWorld.getTurn();
    }

    @Override
    protected void reproduce(){

    }

    @Override
    public void action() {
        if(mainWorld.getPressedAbility() == true){
            if(cooldown <= mainWorld.getTurn()){
                cooldown = mainWorld.getTurn()+10;
                duration = mainWorld.getTurn()+5;
            }
        }
        if(duration > mainWorld.getTurn())
        {
            img = new ImageIcon("Images/human2.png");
        }
        else {
            img = new ImageIcon("Images/human.png");
        }

        int move = mainWorld.getPressed();
        if(move == mainWorld.KEY_LEFT)//left
        {
            if(this.x-1 >= 0)
            {
                newX = this.x-1;
            }
        }
        else if(move == mainWorld.KEY_RIGHT)//right
        {
            if(this.x+1 < mainWorld.getParameters().sizeX)
            {
                newX = this.x+1;
            }
        }
        else if(move == mainWorld.KEY_UP)//top
        {
            if(this.y-1 >= 0)
            {
                newY = this.y-1;
            }
        }
        else if(move == mainWorld.KEY_DOWN)//bottom
        {
            if(this.y+1 < mainWorld.getParameters().sizeY)
            {
                newY = this.y+1;
            }
        }
        else if(move == mainWorld.KEY_NONE){
            return;
        }
        if(newX != x || newY != y){
            if(mainWorld.getOrganismsArray()[newX][newY] != null){
                collision(mainWorld.getOrganismsArray()[newX][newY]);
                //mainWorld.addLog(this.getCharacteristics()+" tried to reproduce but collided with "+mainWorld.getOrganismsArray()[x+moveX][y+moveY].getCharacteristics());
            }
            else {
                changePosition();
            }
        }

    }

    @Override
    boolean invincibility() {
        System.out.println("d:" + duration);
        if(duration > mainWorld.getTurn())
            return true;
        else
            return false;
    }

    @Override
    public String getThisName(){
        return "Human";
    }


    @Override
    Organism toAdd(int x, int y) {
        Human newHuman = new Human(x,y,mainWorld);
        return newHuman;
    }
}
