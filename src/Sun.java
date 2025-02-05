import java.awt.*;

public class Sun {
    int xpos, ypos;
    int width = 150, height = 140;  // Standard width and height for the images
    int dx = 5, dy = 5;  // Default movement in x and y directions
    public Image image;
    Rectangle rec;
    boolean isAlive = true;  // Default alive status
    boolean isCrashing = false;

    // Constructor for creating an animal with specific position and image
    public Sun(int x, int y, String img) {
        xpos = x;
        ypos = y;
        image = Toolkit.getDefaultToolkit().getImage(img); // Load the image
        rec = new Rectangle(xpos, ypos, width, height);
    }

    // Method to move the animal based on dx and dy, with wrapping logic
    public void wrap() {
        xpos += dx;
        ypos += dy;

        // Wrap horizontally
        if (xpos > 1000) xpos = 0;
        if (xpos < 0) xpos = 1000;

        // Wrap vertically
        if (ypos > 700) ypos = 0;
        if (ypos < 0) ypos = 700;

        rec.setBounds(xpos, ypos, width, height);
    }

    // Method to move the animal based on dx and dy, with bouncing logic
    public void bounce() {
        xpos += dx;
        ypos += dy;

        // Bounce off walls
        if (xpos >= 1000 || xpos <= 0) dx = -dx;
        if (ypos >= 700 || ypos <= 0) dy = -dy;

        rec.setBounds(xpos, ypos, width, height);
    }

    // Method to move the animal (to the right)
    public void move() {
        xpos += 5;  // Move right
        if (xpos > 1000) xpos = 0;  // Reset the position if it goes off the screen
    }
    // Get the image of the animal
//    public Image getImage() {
//        return image;
//    }
}
