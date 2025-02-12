import java.awt.*;

public class grass {
    int xpos, ypos;
    int width = 150, height = 140;  // Standard width and height for the images
    int dx = 5, dy = 5;  // Default movement in x and y directions
    public Image image;
    Rectangle rec;


    // Constructor
    public grass(int x, int y, String img) {
        xpos = x;
        ypos = y;
        image = Toolkit.getDefaultToolkit().getImage(img); // Load the image
        rec = new Rectangle(170, 670, 300, 70);
    }

}