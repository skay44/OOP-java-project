import java.awt.*;
import java.io.Serializable;

public class Parameters implements Serializable {
    int sizeX = 20;
    int sizeY = 20;
    int widthX = 35;
    int widthY = 35;

    int mode = 1; //0 square 1 hex
    Color defaultColor = new Color(0x8C6C62);
}
