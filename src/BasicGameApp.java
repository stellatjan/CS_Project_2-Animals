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

	public BufferStrategy bufferStrategy;

	private Animals zebra, snake, eagle, elephant, lion;

	public static void main(String[] args) {
		BasicGameApp ex = new BasicGameApp();
		new Thread(ex).start();
	}

	public BasicGameApp() {
		setUpGraphics();

		zebra = new Animals(10, 100, "zebra.png");
		snake = new Animals(100, 100, "snake.png");
		eagle = new Animals(200, 100, "eagle.png");
		elephant = new Animals(300, 100, "elephant.png");
		lion = new Animals(400, 100, "lion.png");
	}

	public void run() {
		while (true) {
			moveThings();
			render();
			pause(20);
		}
	}

	public void moveThings() {
		zebra.move();
		snake.move();
		eagle.move();
		elephant.move();
		lion.move();
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

		g.drawImage(zebra.getImage(), zebra.xpos, zebra.ypos, zebra.width, zebra.height, null);
		g.drawImage(snake.getImage(), snake.xpos, snake.ypos, snake.width, snake.height, null);
		g.drawImage(eagle.getImage(), eagle.xpos, eagle.ypos, eagle.width, eagle.height, null);
		g.drawImage(elephant.getImage(), elephant.xpos, elephant.ypos, elephant.width, elephant.height, null);
		g.drawImage(lion.getImage(), lion.xpos, lion.ypos, lion.width, lion.height, null);

		g.dispose();
		bufferStrategy.show();
	}
}
