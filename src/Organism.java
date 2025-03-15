import javax.swing.*;
import java.io.Serializable;

public abstract class Organism implements Serializable {

    protected ImageIcon img = new ImageIcon("Images/grass.png");
    protected int id;

    protected boolean dead;
    protected int power;
    protected int initiative;
    protected int x, y;
    transient protected World mainWorld;

    Organism(int x, int y, World mainWorld){
        this.x = x;
        this.y = y;
        this.mainWorld = mainWorld;
        dead = false;
    }

    Organism(){
        dead = false;
    }

    protected void kill(){
        mainWorld.getOrganismsArray()[this.x][this.y] = null;
        mainWorld.addLog(this.getThisName()+" died");
        dead = true;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getInitiative() {
        return initiative;
    }

    public int getPower() {
        return power;
    }

    public int getId(){
        return id;
    }

    public boolean isDead(){
        return dead;
    }

    public void setX(int x){
        this.x = x;
    }

    public void setY(int y){
        this.y = y;
    }

    public void setXY(int x, int y){
        setX(x);
        setY(y);
    }

    public void setPower(int power){
        this.power = power;
    }

    abstract boolean invincibility();

    abstract void attackedBy(Organism organism);

    abstract String getThisName();

    abstract void action();

    abstract void collision(Organism collidingWith);


    public String getCharacteristics(){
        return getThisName()+" "+"["+x+","+y+"]";
    }

    public ImageIcon draw(){
        return img;
    }

    public void setMainWorld(World mainWorld){
        this.mainWorld = mainWorld;
    }

    abstract Organism toAdd(int x, int y);


    public void pritData(){
        System.out.println("==============\nx: "+x+"\ny: "+y+"\ndead: "+dead+"\npower:"+power+"\niniciative:"+initiative+"\nid:"+id);
    }

}
