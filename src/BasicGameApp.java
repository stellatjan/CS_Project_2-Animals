// Basic Game Application
// Version 2
// Basic Object, Image, Movement
// Animals move to the right.
// Threaded

// K. Chun 8/2018

// Import Section
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.awt.*;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class BasicGameApp implements Runnable {

	// Variable Definition Section
	final int WIDTH = 1000;
	final int HEIGHT = 700;

	public JFrame frame;
	public Canvas canvas;
	public JPanel panel;
	public Image background;


	public BufferStrategy bufferStrategy;

	private Animals eagle, snake, zebra, elephant, lion, hippo;


	public static void main(String[] args) {
		BasicGameApp ex = new BasicGameApp();
		new Thread(ex).start();
	}

	public BasicGameApp() {
		setUpGraphics();
		background = Toolkit.getDefaultToolkit().getImage("background.png");
		eagle = new Animals(10, 100, "eagle.png");
		lion = new Animals(100, 500, "lion.png");
		snake = new Animals(400, 500, "snake.png");
		zebra = new Animals(550, 500, "zebra.png");
		elephant = new Animals(250, 500, "elephant.png");
		hippo = new Animals(-50, 500, "hippo.png");

	}

	public void run() {
		while (true) {
			moveThings();
			render();
			pause(20);
		}
	}

	public void moveThings() {
		// Only move animals, not the background
		lion.move();
		zebra.move();
		snake.move();
		eagle.move();
		elephant.move();
		hippo.move();
		hippo.bounce();
		snake.wrap();

	}
	public void collisions(){
		System.out.println(hippo.isCrashing);
		if(hippo.rec.intersects(snake.rec) && hippo.isCrashing==false && hippo.isAlive && snake.isAlive){
			System.out.println("explosion!");
			hippo.dx = -hippo.dx;
			hippo.dy = -hippo.dy;
			snake.dx = -snake.dx;
			snake.dy = -snake.dy;

			// make hippo get bigger
			hippo.width = hippo.width + 50;
			hippo.height = hippo.height + 50;

			// make snake faster
			snake.dx = snake.dx + 20;
			snake.dy = snake.dy + 20;

			hippo.isCrashing=true;
		}

		if(!hippo.rec.intersects(snake.rec)){
			//System.out.println("noCrash");
			hippo.isCrashing=false;
			hippo.isAlive=false;


		}
	}


	public void pause(int time) {
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
		}
	}

	private void setUpGraphics() {
		frame = new JFrame("Animal Game");
		panel = (JPanel) frame.getContentPane();
		panel.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		panel.setLayout(null);
		canvas = new Canvas();
		canvas.setBounds(0, 0, WIDTH, HEIGHT);
		canvas.setIgnoreRepaint(true);
		panel.add(canvas);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setResizable(false);
		frame.setVisible(true);
		canvas.createBufferStrategy(2);
		bufferStrategy = canvas.getBufferStrategy();
		canvas.requestFocus();
		System.out.println("DONE graphic setup");
	}

	private void render() {
		Graphics2D g = (Graphics2D) bufferStrategy.getDrawGraphics();
		g.clearRect(0, 0, WIDTH, HEIGHT);

		// Draw the background image first (it stays still)
		g.drawImage(background, 0, -5, 1500, 900, null);

		// Draw the animals on top of the background
		g.drawImage(hippo.getImage(), hippo.xpos, hippo.ypos, hippo.width, hippo.height, null);
		g.drawImage(lion.getImage(), lion.xpos, lion.ypos, lion.width, lion.height, null);
		g.drawImage(zebra.getImage(), zebra.xpos, zebra.ypos, zebra.width, zebra.height, null);
		g.drawImage(snake.getImage(), snake.xpos, snake.ypos, snake.width, snake.height, null);
		g.drawImage(eagle.getImage(), eagle.xpos, eagle.ypos, eagle.width, eagle.height, null);
		g.drawImage(elephant.getImage(), elephant.xpos, elephant.ypos, elephant.width, elephant.height, null);


		g.dispose();
		bufferStrategy.show();

	}
}
