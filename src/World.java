import javax.swing.*;
import java.util.ArrayList;
import java.util.ListIterator;

public class World {

    public static int KEY_NONE = 0;
    public static int KEY_UP = 1;
    public static int KEY_DOWN = 2;
    public static int KEY_LEFT = 3;
    public static int KEY_RIGHT = 4;

    private int pressed;
    private boolean pressedAbility;
    //char keypressed;
    private int activeX, activeY;
    private int turn;
    private ArrayList<Organism> organisms;
    private Organism[][] organismsArray;
    private MyFrame frame;

    private Parameters parameters;
    World(Parameters parameters){
        this.activeX = -1;
        this.activeY = -1;
        this.turn = 0;
        this.parameters = parameters;
        organisms = new ArrayList<Organism>();
        this.frame = new MyFrame(parameters,this);
        this.organismsArray = new Organism[parameters.sizeX][parameters.sizeY];
        for(int i = 0; i < parameters.sizeX; i++){
            for(int j = 0; j < parameters.sizeY; j++){
                organismsArray[i][j] = null;
            }
        }
        Human human = new Human(parameters.sizeX/2, parameters.sizeY/2,this );
        addOrganism(human);
        //this.nextTurn();
    }

    public void drawWorld(){
        frame.update(organismsArray);
    }


    public void createNew(){
        boolean gotX = false;
        boolean gotY = false;
        int mode;
        int newX =0, newY=0;

        while(gotX == false) {
            gotX = true;
            try{
                newX = Integer.parseInt(JOptionPane.showInputDialog("Enter new size X (3-25)"));
            }
            catch (Exception e){
                e.getStackTrace();
                gotX = false;
            }
        }

        while(gotY == false){
            gotY = true;
            try{
                newY = Integer.parseInt(JOptionPane.showInputDialog("Enter new size Y (3-25)"));
            }
            catch (Exception e){
                e.getStackTrace();
                gotY = false;
            }
        }

        mode = JOptionPane.showConfirmDialog(new JOptionPane(),"Do you want to use square tiles?", "Tiles", JOptionPane.YES_NO_OPTION);
        //System.out.println(mode);

        if(newY >25) newY = 25;
        else if( newY < 3) newY = 3;

        if(newX > 25) newX = 25;
        else if( newX < 3) newX = 3;

        initnew(newX, newY, mode);
    }

    private void initnew(int newX, int newY, int mode){
        this.activeX = -1;
        this.activeY = -1;
        parameters.sizeX = newX;
        parameters.sizeY = newY;
        parameters.mode = mode;
        this.turn = 0;

        this.frame.dispose();

        organisms = new ArrayList<Organism>();
        this.frame = new MyFrame(parameters,this);
        this.organismsArray = new Organism[parameters.sizeX][parameters.sizeY];
        for(int i = 0; i < parameters.sizeX; i++){
            for(int j = 0; j < parameters.sizeY; j++){
                organismsArray[i][j] = null;
            }
        }
        Human human = new Human(parameters.sizeX/2, parameters.sizeY/2,this );
        addOrganism(human);
        //System.out.println("dsfsdf");
        /*
        this.nextTurn();*/
        frame.setFocusable(true);
        frame.requestFocus();
        drawWorld();
    }

    public void addLog(String log){
        frame.getLogs().addlog(log,true);
    }
    public void nextTurn(){
        turn++;
        frame.getLogs().resetLog();
        frame.getLogs().addlog("Turn: "+turn ,false);

        ListIterator<Organism> iter = organisms.listIterator();
        while(iter.hasNext()){
            if(iter.next().isDead())
            {
                //organismsArray[iter.next().getX()][iter.next().getY()] = null;
                iter.remove();
                //System.out.println("REMOVED");
            }

        }

        for(int i = 7; i >= 0; i--){
            for(int j = 0; j < organisms.size(); j++){
                if(organisms.get(j).getInitiative() == i)
                {
                    if(!organisms.get(j).isDead())
                        organisms.get(j).action();
                }

            }
        }

        //System.out.println("Next");

        frame.setFocusable(true);
        frame.requestFocus();
        drawWorld();
        pressed = KEY_NONE;
        pressedAbility = false;
    }

    public void save(){
        SaveManager saveManager = new SaveManager();
        Save save = new Save(turn,organisms,organismsArray,parameters);
        try{
            saveManager.save(save,"b.bin");
        }
        catch (Exception e){
            System.out.println("BBB");
        }
        frame.requestFocus();
    }

    public void load(){
        SaveManager saveManager = new SaveManager();
        Save save = null;
        try{
            save = (Save) saveManager.load("b.bin");
        }
        catch (Exception e){
            System.out.println("AAA");
        }

        turn = save.turn;
        organisms = save.organisms;
        organismsArray = save.organismsArray;
        parameters = save.p;

        for(Organism o: organisms){
            o.mainWorld = this;
        }

        this.frame.dispose();

        this.frame = new MyFrame(parameters,this);

        frame.requestFocus();
        drawWorld();
        frame.getLogs().resetLog();
        frame.getLogs().addlog("Turn: "+turn ,false);
    }

    private void changeMode(int x){
        parameters.mode = x;
        frame.updateMode(organismsArray);
    }

    public void addOrganism(Organism organism){
        this.organisms.add(organism);
        this.organismsArray[organism.getX()][organism.getY()] = organism;
    }

    public ArrayList<Organism> getOrganisms(){
        return organisms;
    }

    public Organism[][] getOrganismsArray(){
        return organismsArray;
    }

    public MyFrame getFrame(){
        return frame;
    }

    public void setActiveTile(int x,int y){
        activeX = x;
        activeY = y;
    }

    public void setpressed(int newPressed){
        this.pressed = newPressed;
    }

    public int getPressed(){
        return pressed;
    }

    public void setPressedAbility(){
        this.pressedAbility = true;
    }

    public boolean getPressedAbility(){
        return pressedAbility;
    }

    public int getActiveX() {
        return activeX;
    }

    public int getActiveY() {
        return activeY;
    }

    public int getTurn() {return turn;}

    public Parameters getParameters() {
        return parameters;
    }

}
