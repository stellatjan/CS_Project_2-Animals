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
	private Discrepancies albinozebra, closeenoughtojustzebrastripes, eaglenowings, elephantnotrunknotail, hipponotooth, justeaglewings, justelephanttrunkandtail, justhippotooth, justlionmane, justsnaketounge, lionnomane, snakenotoungeandfrown;
	private Sun burningsun;
	private tombstone tombstone;

	public static void main(String[] args) {
		BasicGameApp ex = new BasicGameApp();
		new Thread(ex).start();
	}

	public BasicGameApp() {
		setUpGraphics();

		background = Toolkit.getDefaultToolkit().getImage("background.png");
		burningsun = new Sun(200, 22, "burningsun.png");
		tombstone = new tombstone(50, 500, "tombstone.png");
		eagle = new Animals(10, 100, "eagle.png");
		lion = new Animals(100, 500, "lion.png");
		snake = new Animals(400, 500, "snake.png");
		zebra = new Animals(550, 500, "zebra.png");
		elephant = new Animals(250, 500, "elephant.png");
		hippo = new Animals(-50, 500, "hippo.png");

		albinozebra = new Discrepancies(100, 100, "albinozebra.png");
		closeenoughtojustzebrastripes = new Discrepancies(500, 500, "closeenoughtojustzebrastripes.png"); //no work??
		eaglenowings = new Discrepancies(100, 200, "eaglenowings.png");
		elephantnotrunknotail = new Discrepancies(100, 300, "elephantnotrunknotail.png");
		hipponotooth = new Discrepancies(100, 400, "hipponotooth.png");
		justeaglewings = new Discrepancies(100, 400, "justeaglewings.png");
		justelephanttrunkandtail = new Discrepancies(100, 400, "justelephanttrunkandtail.png");
		justhippotooth = new Discrepancies(100, 400, "justhippotooth.png");
		justlionmane = new Discrepancies(100, 400, "justlionmane.png");
		justsnaketounge = new Discrepancies(100, 400, "justsnaketounge.png");
		lionnomane = new Discrepancies(100, 400, "lionnomane.png");
		snakenotoungeandfrown = new Discrepancies(100, 400, "snakenotoungeandfrown.png");

		albinozebra.isAlive = false;
		closeenoughtojustzebrastripes.isAlive = false;
		eaglenowings.isAlive = false;
		elephantnotrunknotail.isAlive = false;
		hipponotooth.isAlive = false;
		justeaglewings.isAlive = false;
		justelephanttrunkandtail.isAlive = false;
		justhippotooth.isAlive = false;
		justlionmane.isAlive = false;
		justsnaketounge.isAlive = false;
		lionnomane.isAlive = false;
		snakenotoungeandfrown.isAlive = false;


	}


	public void run() {
		while (true) {
			moveThings();
			render();
			pause(20);
			collisions();
		}
	}

	public void moveThings() {
		// Only move animals, not the background
		lion.bounce();
		zebra.goofy();
		eagle.wrap();
		elephant.bounce();
		hippo.goofy();
		snake.wrap();


	}

	public void collisions() {
		// Check if any of the animals intersect with the burning sun
		if (hippo.rec.intersects(burningsun.rec) && hippo.isAlive) {
			System.out.println("Hippo collided with the sun!");
			hippo.isAlive = false;  // Set hippo as "dead"


		}

		if (lion.rec.intersects(burningsun.rec) && lion.isAlive) {
			System.out.println("Lion collided with the sun!");
			lion.isAlive = false;  // Set lion as "dead"
		}

		if (zebra.rec.intersects(burningsun.rec) && zebra.isAlive) {
			System.out.println("Zebra collided with the sun!");
			zebra.isAlive = false;  // Set zebra as "dead"
		}

		if (snake.rec.intersects(burningsun.rec) && snake.isAlive) {
			System.out.println("Snake collided with the sun!");
			snake.isAlive = false;  // Set snake as "dead"
		}

		if (eagle.rec.intersects(burningsun.rec) && eagle.isAlive) {
			System.out.println("Eagle collided with the sun!");
			eagle.isAlive = false;  // Set eagle as "dead"
		}

		if (elephant.rec.intersects(burningsun.rec) && elephant.isAlive) {
			System.out.println("Elephant collided with the sun!");
			elephant.isAlive = false;  // Set elephant as "dead"
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

		g.drawImage(background, 0, -5, 1500, 900, null);
		g.drawImage(burningsun.image, burningsun.xpos, burningsun.ypos, burningsun.width, burningsun.height, null);
//		g.drawRect(burningsun.rec.x, burningsun.rec.y, burningsun.rec.width, burningsun.rec.height);
//		g.drawRect(hippo.rec.x, hippo.rec.y, hippo.rec.width, hippo.rec.height);

		g.drawImage(tombstone.image, tombstone.xpos, tombstone.ypos, tombstone.width, tombstone.height, null);
// Check if all regular animals are dead

		if (!hippo.isAlive && !lion.isAlive && !zebra.isAlive && !snake.isAlive && !eagle.isAlive && !elephant.isAlive) {
			g.drawImage(albinozebra.image, albinozebra.xpos, albinozebra.ypos, albinozebra.width, albinozebra.height, null);
			g.drawImage(closeenoughtojustzebrastripes.image, closeenoughtojustzebrastripes.xpos, closeenoughtojustzebrastripes.ypos, closeenoughtojustzebrastripes.width, closeenoughtojustzebrastripes.height, null);
			g.drawImage(eaglenowings.image, eaglenowings.xpos, eaglenowings.ypos, eaglenowings.width, eaglenowings.height, null);
			g.drawImage(elephantnotrunknotail.image, elephantnotrunknotail.xpos, elephantnotrunknotail.ypos, elephantnotrunknotail.width, elephantnotrunknotail.height, null);
			g.drawImage(hipponotooth.image, hipponotooth.xpos, hipponotooth.ypos, hipponotooth.width, hipponotooth.height, null);
			g.drawImage(justeaglewings.image, justeaglewings.xpos, justeaglewings.ypos, justeaglewings.width, justeaglewings.height, null);
			g.drawImage(justelephanttrunkandtail.image, justelephanttrunkandtail.xpos, justelephanttrunkandtail.ypos, justelephanttrunkandtail.width, justelephanttrunkandtail.height, null);
			g.drawImage(justhippotooth.image, justhippotooth.xpos, justhippotooth.ypos, justhippotooth.width, justhippotooth.height, null);
			g.drawImage(justlionmane.image, justlionmane.xpos, justlionmane.ypos, justlionmane.width, justlionmane.height, null);
			g.drawImage(justsnaketounge.image, justsnaketounge.xpos, justsnaketounge.ypos, justsnaketounge.width, justsnaketounge.height, null);
			g.drawImage(lionnomane.image, lionnomane.xpos, lionnomane.ypos, lionnomane.width, lionnomane.height, null);
			g.drawImage(snakenotoungeandfrown.image, snakenotoungeandfrown.xpos, snakenotoungeandfrown.ypos, snakenotoungeandfrown.width, snakenotoungeandfrown.height, null);
		}

		// Draw the animals only if they are alive
		if (hippo.isAlive) g.drawImage(hippo.image, hippo.xpos, hippo.ypos, hippo.width, hippo.height, null);
		if (lion.isAlive) g.drawImage(lion.image, lion.xpos, lion.ypos, lion.width, lion.height, null);
		if (zebra.isAlive) g.drawImage(zebra.image, zebra.xpos, zebra.ypos, zebra.width, zebra.height, null);
		if (snake.isAlive) g.drawImage(snake.image, snake.xpos, snake.ypos, snake.width, snake.height, null);
		if (eagle.isAlive) g.drawImage(eagle.image, eagle.xpos, eagle.ypos, eagle.width, eagle.height, null);
		if (elephant.isAlive) g.drawImage(elephant.image, elephant.xpos, elephant.ypos, elephant.width, elephant.height, null);



		g.dispose();
		bufferStrategy.show();
	}
}
