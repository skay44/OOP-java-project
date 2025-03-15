import javax.swing.*;
import java.awt.*;

public class Logs {

    private TextArea textArea;
    private int logsAmount;

    Logs(int sizeX, int sizeY, int widthX, int widthY){
        textArea = new TextArea();
        textArea.setBounds(sizeX,sizeY,widthX,widthY);
        textArea.setEditable(false);
        logsAmount = 0;
    }

    public void addlog(String newLog, boolean listed){
        if(listed) {
            textArea.setText(textArea.getText()+logsAmount+"."+newLog+"\n");
            logsAmount++;
        }
        else {
            textArea.setText(textArea.getText()+newLog+"\n");
        }
    }

    public void resetLog(){
        textArea.setText("");
        logsAmount=0;
    }
    public TextArea getTextArea(){
        return textArea;
    }
}
