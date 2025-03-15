import java.util.Random;

public abstract class Animal extends Organism{
    protected int range;
    protected int newX;
    protected int newY;
    Animal(int x, int y, World mainWorld){
        super(x,y,mainWorld);
        this.range =1;
        newX = x;
        newY = y;
    }

    Animal(){
        this.range =1;
    }

    protected void reproduce(){
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
            boolean breedingSuccess = true;
            Organism o = mainWorld.getOrganismsArray()[x+moveX][y+moveY];
            if(o != null){
                if(o instanceof Animal)
                    breedingSuccess = false;
                else {
                    if(o.getPower() > power){
                        breedingSuccess = false;
                    }
                    else {
                        o.attackedBy(this);
                    }
                }
            }
            if(breedingSuccess){
                Organism reproduced = toAdd(x+moveX,y+moveY);
                mainWorld.addOrganism(reproduced);
                mainWorld.addLog(this.getCharacteristics()+" repreoduced -> "+reproduced.getCharacteristics());
            }
            else{
                mainWorld.addLog(getCharacteristics() + " couldnt reproduce ");
            }
        }
        else{
            mainWorld.addLog(getCharacteristics() + " couldnt reproduce ");
        }
    }

    private boolean escape(){
        //todo
        if(this.x-1 >= 0 && mainWorld.getOrganismsArray()[x-1][y] == null)
        {
            newX = x-1;
            changePosition();
            return true;
        }
        else if(this.x+1 < mainWorld.getParameters().sizeX && mainWorld.getOrganismsArray()[x+1][y] == null)
        {
            newX = x+1;
            changePosition();
            return true;
        }
        else if(this.y-1 >= 0  && mainWorld.getOrganismsArray()[x][y-1] == null)
        {
            newY = y-1;
            changePosition();
            return true;
        }
        else if(this.y+1 < mainWorld.getParameters().sizeY  && mainWorld.getOrganismsArray()[x][y+1] == null)
        {
            newY = y+1;
            changePosition();
            return true;
        }
        else if(mainWorld.getParameters().mode == 1)
        {
            if(y%2 == 0 && this.y-1 >= 0 && this.x-1 >= 0 && mainWorld.getOrganismsArray()[x-1][y-1] == null)//left top
            {
                newY = y-1;
                newX = x-1;
                changePosition();
                return true;
            }
            else if(this.y-1 >= 0 && this.x+1 < mainWorld.getParameters().sizeX && mainWorld.getOrganismsArray()[x+1][y-1] == null) //rigth top{
            {
                newY = y-1;
                newX = x+1;
                changePosition();
                return true;
            }
            else if(y%2 == 0 && this.y+1 < mainWorld.getParameters().sizeY && this.x-1 >= 0 && mainWorld.getOrganismsArray()[x-1][y+1] == null)//left bottom
            {
                newY = y+1;
                newX = x-1;
                changePosition();
                return true;
            }
            else if(this.y+1 < mainWorld.getParameters().sizeY && this.x+1 < mainWorld.getParameters().sizeX && mainWorld.getOrganismsArray()[x+1][y+1] == null) //rigth bottom{
            {
                newY = y+1;
                newX = x+1;
                changePosition();
                return true;
            }
        }
        return false;
    }

    @Override
    public void collision(Organism collidingWith){
        boolean ifEspaced = false;
        if(collidingWith instanceof Animal){
            Animal colliding = (Animal) collidingWith;
            mainWorld.addLog(this.getCharacteristics() + " encounters " + colliding.getCharacteristics());
            if(collidingWith.getId() == this.id) {
                newX = this.x;
                newY = this.y;
                reproduce();
            }
            else {
                if(colliding.ifEscapes()){
                    ifEspaced = colliding.escape();
                }
                if(ifEspaced == false){
                    if(colliding.getPower() > this.power){
                        if(invincibility() == false){
                            attackedBy(colliding);
                        }
                        else
                        {
                            mainWorld.addLog(getCharacteristics() + " avoids " + colliding.getCharacteristics());
                            //changePosition();
                            if(!escape()){
                                attackedBy(colliding);
                                //changePosition();
                                //action();//stuffff
                            }
                            //action();
                        }
                    }
                    else{
                        if(colliding.invincibility() == false){
                            colliding.attackedBy(this);
                            changePosition();
                        }
                        else{
                            mainWorld.addLog(colliding.getCharacteristics() + " avoids " +getCharacteristics());
                        }
                    }
                }
                else{
                    mainWorld.addLog(colliding.getCharacteristics() + " escaped from " + getCharacteristics());
                    changePosition();
                }
            }
        }
        else if(collidingWith instanceof Plant){
            Plant colliding = (Plant) collidingWith;
            if(colliding.getPower() > this.power){
                if(invincibility() == false){
                    mainWorld.addLog(this.getCharacteristics() + " eats and gets poisoned by " + colliding.getCharacteristics());
                    //mainWorld.getOrganismsArray()[this.x][this.y] = null;
                    attackedBy(colliding);
                    colliding.attackedBy(this);
                }
                else {
                    mainWorld.addLog(this.getCharacteristics() + " avoids " + colliding.getCharacteristics());
                    if(!escape()){
                        attackedBy(colliding);
                        //changePosition();
                        //action();//stuffff
                    }
                    //action();
                }
            }
            else{
                mainWorld.addLog(this.getCharacteristics() + " eats " + colliding.getCharacteristics());
                colliding.attackedBy(this);
                changePosition();
            }
        }
    }

    public void changePosition(){
        mainWorld.getOrganismsArray()[this.x][this.y] = null;
        this.x = newX;
        this.y = newY;
        mainWorld.getOrganismsArray()[this.x][this.y] = this;
    }
    @Override
    public void action(){
        Random rand = new Random();
        int direction;
        int distance;
        distance = rand.nextInt(range)+1;
        if(mainWorld.getParameters().mode == 0)
            direction = rand.nextInt(4);
        else
            direction = rand.nextInt(6);
        int moveX = 0;
        int moveY = 0;
        boolean success = false;
        if(direction == 0)//left
        {
            if(this.x-distance >= 0)
            {
                moveX = -distance;
                success = true;
            }
        }
        else if(direction == 1)//right
        {
            if(this.x+distance < mainWorld.getParameters().sizeX)
            {
                moveX = distance;
                success = true;
            }
        }
        else if(direction == 2)//top
        {
            if(this.y-distance >= 0)
            {
                moveY = -distance;
                success = true;
            }
        }
        else if(direction == 3)//bottom
        {
            if(this.y+distance < mainWorld.getParameters().sizeY)
            {
                moveY = distance;
                success = true;
            }
        }
        else if(direction == 4)//left top or right top
        {
            if(y%2 == 0 && this.y-distance >= 0 && this.x-distance >= 0)//left top
            {
                moveY = -distance;
                moveX = -distance;
                success = true;
            }
            else if(this.y-distance >= 0 && this.x+distance < mainWorld.getParameters().sizeX) //rigth top{
            {
                moveY = -distance;
                moveX = distance;
                success = true;
            }
        }
        else if(direction == 5)//left bottom or right bottom
        {
            if(y%2 == 0 && this.y+distance < mainWorld.getParameters().sizeY && this.x-distance >= 0)//left bottom
            {
                moveY = distance;
                moveX = -distance;
                success = true;
            }
            else if(this.y+distance < mainWorld.getParameters().sizeY && this.x+distance < mainWorld.getParameters().sizeX) //rigth bottom{
            {
                moveY = distance;
                moveX = distance;
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

    void attackedBy(Organism organism){
        kill();
    }

    public boolean ifEscapes()
    {
        return false;
    }
}
