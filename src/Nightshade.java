import javax.swing.*;

public class Nightshade extends Plant {

    Nightshade(int x, int y, World mainWorld){
        super(x,y,mainWorld);
        img = new ImageIcon("Images/nightshade.png");
        this.id = 4;
        this.power = 99;
        this.reproductionChance = 5;
    }

    Nightshade(){
        super();
        img = new ImageIcon("Images/nightshade.png");
        this.id = 4;
        this.power = 99;
        this.reproductionChance = 5;
    }

    @Override
    public String getThisName(){
        return "Nightshade";
    }


    @Override
    Organism toAdd(int x, int y) {
        Nightshade newNightshade = new Nightshade(x,y,mainWorld);
        return newNightshade;
    }

    @Override
    public void attackedBy(Organism organism){
        //mainWorld.addLog(this.getCharacteristics()+" poisones "+organism.getCharacteristics());
        kill();
        if(organism.isDead() == false)
            organism.attackedBy(this);
    }
}
