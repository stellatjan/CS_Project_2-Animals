import java.awt.*;

public class Animals {
    int xpos, ypos;
    int width = 150, height = 140;  // Standard width and height for the images
    int dx = 5, dy = 5;  // Default movement in x and y directions
   public Image image;
    Rectangle rec;
    boolean isAlive = true;  // Default alive status
    boolean isCrashing = false;

    // Constructor for creating an animal with specific position and image
    public Animals(int x, int y, String img) {
        xpos = x;
        ypos = y;
        image = Toolkit.getDefaultToolkit().getImage(img); // Load the image
        rec = new Rectangle(xpos, ypos, width, height);
    }


    public void goofy() {
        // Update the position based on dx and dy
        xpos += dx*0.5;
        ypos += dy*-2;


//        dx -= 5;  // Slightly increase dx
//        dy *= 2;  // Slightly increase dy


        if (dx > 5 || dx < -5) {
            dx = 1;  // Reset dx to a slower value
        }
        if (dy > 5 || dy < -5) {
            dy = 1;  // Reset dy to a slower value
        }



        if (xpos < 0) {
            xpos = 0;  // Don't let xpos go below 0
            dx = -dx;  // Reverse direction to make it jitter the other way
        }
        if (xpos > 800) {
            xpos = 800;  // Don't let xpos go beyond 800
            dx = -dx;  // Reverse direction to make it jitter the other way
        }
        if (ypos < 0) {
            ypos = 0;  // Don't let ypos go below 0
            dy = -dy;  // Reverse direction to make it jitter the other way
        }
        if (ypos > 600) {
            ypos = 600;  // Don't let ypos go beyond 600
            dy = -dy;  // Reverse direction to make it jitter the other way
        }

        rec.setBounds(xpos, ypos, width, height);
    }




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

    // Method to move the animal based on dx and dy
    public void bounce() {
        xpos += dx;
        ypos += dy;

        // Bounce off walls
        if (xpos < 0) {
            xpos = 0;  // Don't let xpos go below 0
            dx = -dx;  // Reverse direction to make it jitter the other way
        }
        if (xpos > 800) {
            xpos = 800;  // Don't let xpos go beyond 800
            dx = -dx;  // Reverse direction to make it jitter the other way
        }
        if (ypos < 0) {
            ypos = 0;  // Don't let ypos go below 0
            dy = -dy;  // Reverse direction to make it jitter the other way
        }
        if (ypos > 600) {
            ypos = 600;  // Don't let ypos go beyond 600
            dy = -dy;  // Reverse direction to make it jitter the other way
        }

        rec.setBounds(xpos, ypos, width, height);
    }


    // Method to move the animal (to the right)
    public void move() {
        xpos += 5;  // Move right
        if (xpos > 1000) xpos = 0;  // Reset the position if it goes off the screen
    }


}
