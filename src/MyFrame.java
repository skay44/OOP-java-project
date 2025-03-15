import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Set;


public class MyFrame extends JFrame implements KeyListener, ActionListener {

    private World mainWorld;
    private JButton nextButton, saveButton, loadButton, newWorldButton;
    private Parameters parameters;

    //private JPanel[][] panels;
    //private JLabel[][] labeles;

    private Tile[][] tiles;

    private Logs logs;

    private JPanel tileTitle;
    private JLabel titleLabel;

    private ArrayList<SetterButton> setterButtons;

    private ImageIcon defaultImage;

    MyFrame(Parameters parameters, World mainWorld){
        this.setFocusable(true);
        this.requestFocus();
        this.parameters = parameters;
        this.mainWorld = mainWorld;
        this.addKeyListener(this);

        ImageIcon icon = new ImageIcon("Images/icon.png");
        this.setIconImage(icon.getImage());
        if((parameters.widthY)*(parameters.sizeY+2)+100 > 500)
            this.setSize((parameters.widthX)*(parameters.sizeX+2)+650,(parameters.widthY)*(parameters.sizeY+2)+100);
        else
            this.setSize((parameters.widthX)*(parameters.sizeX+2)+650,500);
        /*
        if((parameters.widthX)*(parameters.sizeX+2)+10 > 500)
            this.setSize((parameters.widthX)*(parameters.sizeX+2)+550,(parameters.widthY)*(parameters.sizeY+2)+100);
        else
            this.setSize(1050,(parameters.widthY)*(parameters.sizeY+2)+100);*/
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.getContentPane().setBackground(new Color(0x2D2929));

        this.defaultImage = new ImageIcon("Images/default.png");
        createSetterButtons();
        CreateNextButton();
        CreateSaveButton();
        CreateLoadButton();
        CreateNewWorldButton();
        createTiles();
        createTileTitle();

        this.logs = new Logs((parameters.widthX)*(parameters.sizeX+2)+210,parameters.widthY,400,(parameters.widthY)*(parameters.sizeY));
        this.add(this.logs.getTextArea());

        this.setVisible(true);
        this.repaint();
    }

    private void createSetterButtons(){
        ArrayList<SetterButton> setterButtons = new ArrayList<SetterButton>();

        SetterButton newButton = new SetterButton(new Grass(1,1,mainWorld), 40, mainWorld);
        setterButtons.add(newButton);
        this.add(newButton.getButton());

        newButton = new SetterButton(new Dandelion(1,1,mainWorld), 70, mainWorld);
        setterButtons.add(newButton);
        this.add(newButton.getButton());

        newButton = new SetterButton(new Guarana(1,1,mainWorld), 100, mainWorld);
        setterButtons.add(newButton);
        this.add(newButton.getButton());

        newButton = new SetterButton(new Nightshade(1,1,mainWorld), 130, mainWorld);
        setterButtons.add(newButton);
        this.add(newButton.getButton());

        newButton = new SetterButton(new PineBorscht(1,1,mainWorld), 160, mainWorld);
        setterButtons.add(newButton);
        this.add(newButton.getButton());

        newButton = new SetterButton(new Wolf(1,1,mainWorld), 200, mainWorld);
        setterButtons.add(newButton);
        this.add(newButton.getButton());

        newButton = new SetterButton(new Sheep(1,1,mainWorld), 230, mainWorld);
        setterButtons.add(newButton);
        this.add(newButton.getButton());

        newButton = new SetterButton(new Fox(1,1,mainWorld), 260, mainWorld);
        setterButtons.add(newButton);
        this.add(newButton.getButton());

        newButton = new SetterButton(new Turtle(1,1,mainWorld), 290, mainWorld);
        setterButtons.add(newButton);
        this.add(newButton.getButton());

        newButton = new SetterButton(new Antelope(1,1,mainWorld), 320, mainWorld);
        setterButtons.add(newButton);
        this.add(newButton.getButton());

    }
    private void createTileTitle(){
        this.tileTitle = new JPanel();
        this.titleLabel = new JLabel();
        tileTitle.setBounds((parameters.widthX)*(parameters.sizeX+2)+10,parameters.widthY,150,30);
        titleLabel.setText("Empty");
        tileTitle.add(titleLabel);
        this.add(tileTitle);
    }

    private void createTiles(){
        tiles = new Tile[parameters.sizeX][parameters.sizeY];
        for(int i = 0; i < parameters.sizeX; i++){
            for(int j = 0; j < parameters.sizeY; j++){
                tiles[i][j] = new Tile(i,j, parameters.widthX, parameters.widthY,parameters, mainWorld);
                this.add(tiles[i][j].getPanel());
            }
        }
    }


    public void update(Organism[][] organisms){
        for(int i = 0; i < parameters.sizeX; i++){
            for(int j = 0; j < parameters.sizeY; j++){
                if(organisms[i][j] == null)
                {
                    tiles[i][j].setImage(defaultImage);
                }
                else
                {
                    tiles[i][j].setImage(organisms[i][j].draw());
                }

            }
        }
        this.repaint();
    }

    public void updateMode(Organism[][] organisms){
        for(int i = 0; i < parameters.sizeX; i++){
            for(int j = 0; j < parameters.sizeY; j++){
                if(organisms[i][j] == null)
                {
                    tiles[i][j].setImage(defaultImage);
                }
                else
                {
                    tiles[i][j].setImage(organisms[i][j].draw());
                }
                tiles[i][j].setPanelBounds();
            }
        }
        this.repaint();
    }

    private void CreateNewWorldButton(){
        JButton newButton = new JButton();
        newButton.setText("Create New");
        int posX;
        int posY;
        if((parameters.widthX)*(parameters.sizeX+2)+10 > 500)
            posX = (parameters.sizeX*parameters.widthX)/2+155;
        else
            posX = 370;

        if((parameters.widthY)*(parameters.sizeY+2)+100 > 500)
            posY = (parameters.sizeY+2)*parameters.widthY;
        else
            posY = 400;

        /*
        if((parameters.widthY)*(parameters.sizeY+2)+10 > 500)
            posY = (parameters.sizeY+2)*parameters.widthY;
        else
            posY = (parameters.sizeY+2)*parameters.widthY;*/
        //newButton.setBounds((parameters.sizeX*parameters.widthX)/2+155,(parameters.sizeY+2)*parameters.widthY,100,50);

        newButton.setBounds(posX,posY,100,50);

        newButton.addActionListener(this);
        this.newWorldButton = newButton;
        this.add(newButton);
    }

    private void CreateLoadButton(){
        JButton newButton = new JButton();
        newButton.setText("Load");
        int posX;
        int posY;
        if((parameters.widthX)*(parameters.sizeX+2)+10 > 500)
            posX = (parameters.sizeX*parameters.widthX)/2+35;
        else
            posX = 250;

        if((parameters.widthY)*(parameters.sizeY+2)+100 > 500)
            posY = (parameters.sizeY+2)*parameters.widthY;
        else
            posY = 400;
        newButton.setBounds(posX,posY,100,50);

        newButton.addActionListener(this);
        this.loadButton = newButton;
        this.add(newButton);
    }

    private void CreateSaveButton(){
        JButton newButton = new JButton();
        newButton.setText("Save");
        int posX;
        int posY;
        if((parameters.widthX)*(parameters.sizeX+2)+10 > 500)
            posX = (parameters.sizeX*parameters.widthX)/2-85;
        else
            posX = 130;

        if((parameters.widthY)*(parameters.sizeY+2)+100 > 500)
            posY = (parameters.sizeY+2)*parameters.widthY;
        else
            posY = 400;
        newButton.setBounds(posX,posY,100,50);

        newButton.addActionListener(this);
        this.saveButton = newButton;
        this.add(newButton);
    }

    private void CreateNextButton(){
        JButton newButton = new JButton();
        newButton.setText("next round");
        int posX;
        int posY;
        if((parameters.widthX)*(parameters.sizeX+2)+10 > 500)
            posX = (parameters.sizeX*parameters.widthX)/2-205;
        else
            posX = 10;

        if((parameters.widthY)*(parameters.sizeY+2)+100 > 500)
            posY = (parameters.sizeY+2)*parameters.widthY;
        else
            posY = 400;
        newButton.setBounds(posX,posY,100,50);

        newButton.addActionListener(this);
        this.nextButton = newButton;
        this.add(newButton);
    }

    public Logs getLogs(){
        return logs;
    }

    public void setTileTitle(String newText){
        titleLabel.setText(newText);
    }

    /*
    private void CreateLables(){
        this.labeles = new JLabel[parameters.sizeX][parameters.sizeY];

        for(int i = 0; i < parameters.sizeX; i++){
            for(int j = 0; j < parameters.sizeY; j++){
                this.labeles[i][j] = new JLabel();
                labeles[i][j].setIcon(defaultImage);
                labeles[i][j].setBounds(0,0, parameters.widthX, parameters.widthY);
                labeles[i][j].setVerticalAlignment(JLabel.CENTER);
                labeles[i][j].setHorizontalAlignment(JLabel.CENTER);
            }
        }
    }

    private void CreatePanels(){
        panels = new JPanel[parameters.sizeX][parameters.sizeY];
        for(int i = 0; i < parameters.sizeX; i++){
            for(int j = 0; j < parameters.sizeY; j++){
                panels[i][j] = new JPanel();
                panels[i][j].setBackground(parameters.defaultColor);
                panels[i][j].setOpaque(true);
                panels[i][j].setBounds((parameters.widthX)*(i+1),(parameters.widthY)*(j+1), parameters.widthX-2, parameters.widthY-2);
                panels[i][j].setLayout(null);
                panels[i][j].add(this.labeles[i][j]);
                this.add(panels[i][j]);
            }
        }
    }*/

    /*
    @Override
    public void paint(Graphics g){
        Graphics2D g2D = (Graphics2D) g;
        g2D.drawLine(0,0,20,20);
    }*/

    public void actionPerformed(ActionEvent e){
        if(e.getSource() == nextButton){
            mainWorld.nextTurn();
        }
        if(e.getSource() == saveButton){
            System.out.println("save");
            mainWorld.save();
        }
        if(e.getSource() == loadButton){
            System.out.println("load");
            mainWorld.load();
        }
        if(e.getSource() == newWorldButton){
            mainWorld.createNew();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            //System.out.println("a");
            mainWorld.setpressed(mainWorld.KEY_UP);
        }
        else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            //System.out.println("b");
            mainWorld.setpressed(mainWorld.KEY_DOWN);
        }
        else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            //System.out.println("c");
            mainWorld.setpressed(mainWorld.KEY_LEFT);
        }
        else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            //System.out.println("d");
            mainWorld.setpressed(mainWorld.KEY_RIGHT);
        }
        else if(e.getKeyCode() == KeyEvent.VK_A){
            //System.out.println("e");
            mainWorld.setPressedAbility();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    /*
    InputMap im = getInputMap(WHEN_FOCUSED);
    ActionMap am = getActionMap();

        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), "onEnter");

        am.put("onEnter", new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Enter pressed
        }
    });*/
}
