import java.io.Serializable;
import java.util.ArrayList;

public class Save implements Serializable {

    public int turn;
    public ArrayList<Organism> organisms;
    public Organism[][] organismsArray;

    public Parameters p;
    //public MyFrame frame;

    Save(int turn,  ArrayList<Organism> organisms, Organism[][] organismsArray,Parameters p){
        this.turn = turn;
        this.organisms = organisms;
        this.organismsArray = organismsArray;
        this.p = p;
    }
}
