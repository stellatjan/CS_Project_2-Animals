import java.awt.*;

public class Sun {
    int xpos, ypos;
    int width = 150, height = 140;  // Standard width and height for the images
    int dx = 10, dy = 5;  // Default movement in x and y directions
    public Image image;
    Rectangle rec;
    boolean isAlive = true;  // Default alive status
    boolean isCrashing = false;

    // Constructor
    public Sun(int x, int y, String img) {
        xpos = x;
        ypos = y;
        image = Toolkit.getDefaultToolkit().getImage(img); // Load the image
        rec = new Rectangle(xpos, ypos, width, height);
    }

}
