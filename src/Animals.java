// Animals.java
import java.awt.Image;
import java.awt.Toolkit;

public class Animals {
    int xpos, ypos;
    int width = 100, height = 100;  // Set a standard width and height for the images
    Image image;

    // Constructor for creating an animal with specific position and image
    public Animals(int x, int y, String img) {
        xpos = x;
        ypos = y;
        image = Toolkit.getDefaultToolkit().getImage(img); // Load the image
    }

    // Method to move the animal (to the right)
    public void move() {
        xpos += 2;  // Move right
        if (xpos > 1000) xpos = 0;  // Reset the position if it goes off the screen
    }

    // Get the image of the animal
    public Image getImage() {
        return image;
    }
}
