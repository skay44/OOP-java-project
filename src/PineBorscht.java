import javax.swing.*;

public class PineBorscht extends Plant{
    PineBorscht(int x, int y, World mainWorld){
        super(x,y,mainWorld);
        img = new ImageIcon("Images/pineBorscht.png");
        this.id = 5;
        this.power = 10;
        this.reproductionChance = 5;
    }

    PineBorscht(){
        super();
        img = new ImageIcon("Images/pineBorscht.png");
        this.id = 5;
        this.power = 10;
        this.reproductionChance = 5;
    }

    @Override
    public void action(){
        killEveryoneAround();
        super.action();
    }

    private void burn(Organism toBurn){
        if(toBurn != null) {
            if(toBurn instanceof Animal)
            {
                mainWorld.addLog(toBurn.getCharacteristics() + " got burned by " + getCharacteristics());
                toBurn.kill();
            }

        }
    }

    private void killEveryoneAround(){
        if(this.x > 0)//left
        {
            burn(mainWorld.getOrganismsArray()[x-1][y]);
        }
        if(this.x < mainWorld.getParameters().sizeX-1)//right
        {
            burn(mainWorld.getOrganismsArray()[x+1][y]);
        }
        if(this.y > 0)//top
        {
            burn(mainWorld.getOrganismsArray()[x][y-1]);
        }
        if(this.y < mainWorld.getParameters().sizeY-1)//bottom
        {
            burn(mainWorld.getOrganismsArray()[x][y+1]);
        }

        //left top or right top
        if(mainWorld.getParameters().mode == 1){
            if(y%2 == 0 && this.y > 0 && this.x > 0){//left top
                //moveY = -1;
                // moveX = -1;
                burn(mainWorld.getOrganismsArray()[x-1][y-1]);
            }
            else if(this.y > 0 && this.x < mainWorld.getParameters().sizeX-1){//rigth top
                burn(mainWorld.getOrganismsArray()[x+1][y-1]);
            }
            //left bottom or right bottom
            if(y%2 == 0 && this.y < mainWorld.getParameters().sizeY-1 && this.x > 0)//left bottom
            {
                burn(mainWorld.getOrganismsArray()[x-1][y+1]);
            }
            else if(this.y < mainWorld.getParameters().sizeY-1 && this.x < mainWorld.getParameters().sizeX-1) //rigth bottom
            {
                burn(mainWorld.getOrganismsArray()[x+1][y+1]);
            }
        }
    }

    @Override
    public String getThisName(){
        return "PineBorscht";
    }


    @Override
    Organism toAdd(int x, int y) {
        PineBorscht newPineBorscht = new PineBorscht(x,y,mainWorld);
        return newPineBorscht;
    }

    @Override
    public void attackedBy(Organism organism){
        //mainWorld.addLog(this.getCharacteristics()+" poisones "+organism.getCharacteristics());
        kill();
        if(organism.isDead() == false)
            organism.attackedBy(this);
    }
}
