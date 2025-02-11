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
	public Image newbackground;
	private boolean winScreen = false;


	public BufferStrategy bufferStrategy;

	private Animals eagle, snake, zebra, elephant, lion, hippo;
	private Discrepancies albinozebra, closeenoughtojustzebrastripes, eaglenowings, elephantnotrunknotail, hipponotooth, justeaglewings, justelephanttrunkandtail, justhippotooth, justlionmane, justsnaketounge, lionnomane, snakenotoungeandfrown;
	private Sun burningsun;
	private tombstone tombstone;
	private grass grass;

	public static void main(String[] args) {
		BasicGameApp ex = new BasicGameApp();
		new Thread(ex).start();
	}

	public BasicGameApp() {
		setUpGraphics();

		background = Toolkit.getDefaultToolkit().getImage("background.png");
		newbackground = Toolkit.getDefaultToolkit().getImage("newbackground.png");
		burningsun = new Sun(200, 22, "burningsun.png");
		tombstone = new tombstone(50, 500, "tombstone.png");
		grass = new grass(50, 500, "grass.png");
		eagle = new Animals(10, 100, "eagle.png");
		lion = new Animals(100, 500, "lion.png");
		snake = new Animals(400, 500, "snake.png");
		zebra = new Animals(550, 500, "zebra.png");
		elephant = new Animals(250, 500, "elephant.png");
		hippo = new Animals(-50, 500, "hippo.png");

		albinozebra = new Discrepancies(200, 50, "albinozebra.png");
		closeenoughtojustzebrastripes = new Discrepancies(200, 50, "closeenoughtojustzebrastripes.png"); //no work??
		eaglenowings = new Discrepancies(200, 50, "eaglenowings.png");
		elephantnotrunknotail = new Discrepancies(200, 50, "elephantnotrunknotail.png");
		hipponotooth = new Discrepancies(200, 50, "hipponotooth.png");
		justeaglewings = new Discrepancies(200, 50, "justeaglewings.png");
		justelephanttrunkandtail = new Discrepancies(200, 50, "justelephanttrunkandtail.png");
		justhippotooth = new Discrepancies(200, 50, "justhippotooth.png");
		justlionmane = new Discrepancies(200, 50, "justlionmane.png");
		justsnaketounge = new Discrepancies(200, 50, "justsnaketounge.png");
		lionnomane = new Discrepancies(200, 50, "lionnomane.png");
		snakenotoungeandfrown = new Discrepancies(200, 50, "snakenotoungeandfrown.png");

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

		// Check if all animals intersect with the grass.
		if (!winScreen) {
			boolean allAnimalsIntersectGrass = hippo.rec.intersects(grass.rec) &&
					lion.rec.intersects(grass.rec) &&
					zebra.rec.intersects(grass.rec) &&
					snake.rec.intersects(grass.rec) &&
					eagle.rec.intersects(grass.rec) &&
					elephant.rec.intersects(grass.rec);
			if (allAnimalsIntersectGrass) {
				System.out.println("All animals are on the grass! Changing background...");
				winScreen = true;
			}
		}
	}


	public void pause(int time) {
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
		}
	} //rather then having the rectangles follow them around, in order to implement the 'win screen' i set them as fixed values besides the zebra so they can all intercent the grass rectangle slightly differntly so i can use a one set if statment if they are all intercecting grass change the screen

	public void update() {
		int grassY = 570;  // The Y position where the grass starts

		// Hippo
		if (!hippo.isAlive) {
			if (hipponotooth.ypos >= grassY) {
				hippo.isAlive = false;
			} else {
				hipponotooth.ypos += 5;
				justhippotooth.ypos -= 30;
				justhippotooth.xpos -= 25;
			}
			hipponotooth.rec = new Rectangle(175, 570, 20, 20);
		}

		// Zebra
		if (!zebra.isAlive) {
			if (albinozebra.ypos >= grassY) {
				zebra.isAlive = false;
			} else {
				albinozebra.ypos += 5;
				closeenoughtojustzebrastripes.ypos -= 30;
				closeenoughtojustzebrastripes.xpos -= 25;
			}
			albinozebra.rec = new Rectangle(350, 570, 20, 50);
		}

		// Snake
		if (!snake.isAlive) {
			if (snakenotoungeandfrown.ypos >= grassY) {
				snake.isAlive = false;
			} else {
				snakenotoungeandfrown.ypos += 5;
				justsnaketounge.ypos -= 30;
				justsnaketounge.xpos -= 25;
			}
			snakenotoungeandfrown.rec = new Rectangle(205, 570, 20, 50);
		}

		// Eagle
		if (!eagle.isAlive) {
			if (eaglenowings.ypos >= grassY) {
				eagle.isAlive = false;
			} else {
				eaglenowings.ypos += 5;
				justeaglewings.ypos -= 30;
				justeaglewings.xpos -= 25;
			}
			eaglenowings.rec = new Rectangle(300, 570, 20, 50);
		}

		// Elephant
		if (!elephant.isAlive) {
			if (elephantnotrunknotail.ypos >= grassY) {
				elephant.isAlive = false;
			} else {
				elephantnotrunknotail.ypos += 5;
				justelephanttrunkandtail.ypos -= 30;
				justelephanttrunkandtail.xpos -= 25;
			}
			elephantnotrunknotail.rec = new Rectangle(elephantnotrunknotail.xpos, elephantnotrunknotail.ypos, 3, 50);
		}

		// Lion
		if (!lion.isAlive) {
			if (lionnomane.ypos >= grassY) {
				lion.isAlive = false;
			} else {
				lionnomane.ypos += 5;
				justlionmane.ypos -= 30;
				justlionmane.xpos -= 25;
			}
			lionnomane.rec = new Rectangle(400, 570, 20, 50);
		}
	}

//		public void changeBackground() {
//			background = Toolkit.getDefaultToolkit().getImage("newbackground.png");
//		}


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

		//is it not workiung bc all the rectangles are effectivlly on top of eachother so they are not all intercecting????

		g.drawImage(background, 0, -5, 1500, 900, null);
		g.drawImage(burningsun.image, burningsun.xpos, burningsun.ypos, burningsun.width, burningsun.height, null);
		g.drawImage(grass.image, 0, 570, 1500, grass.height, null);
		g.drawRect(grass.rec.x, grass.rec.y, grass.rec.width, grass.rec.height);
		g.drawRect(elephantnotrunknotail.rec.x, elephantnotrunknotail.rec.y, elephantnotrunknotail.rec.width, elephantnotrunknotail.height);
		g.drawRect(snakenotoungeandfrown.rec.x, snakenotoungeandfrown.rec.y, snakenotoungeandfrown.rec.width, snakenotoungeandfrown.height);
		g.drawRect(lionnomane.rec.x, lionnomane.rec.y, lionnomane.rec.width, lionnomane.height);
		g.drawRect(hipponotooth.rec.x, hipponotooth.rec.y, hipponotooth.rec.width, hipponotooth.height);
		g.drawRect(eaglenowings.rec.x, eaglenowings.rec.y, eaglenowings.rec.width, eaglenowings.height);
		g.drawRect(albinozebra.rec.x, albinozebra.rec.y, albinozebra.rec.width, albinozebra.height);


		if (background != newbackground) { // Only draw elements if new background is NOT active
			g.drawImage(burningsun.image, burningsun.xpos, burningsun.ypos, burningsun.width, burningsun.height, null);
			g.drawImage(grass.image, 0, 570, 1500, grass.height, null);

			// Call the update() method once per frame, before drawing the animals
			update();


			if (!zebra.isAlive) {
				g.drawImage(albinozebra.image, albinozebra.xpos, albinozebra.ypos, albinozebra.width, albinozebra.height, null);
				g.drawImage(closeenoughtojustzebrastripes.image, closeenoughtojustzebrastripes.xpos, closeenoughtojustzebrastripes.ypos, closeenoughtojustzebrastripes.width, closeenoughtojustzebrastripes.height, null);
			}

			if (!eagle.isAlive) {
				g.drawImage(eaglenowings.image, eaglenowings.xpos, eaglenowings.ypos, eaglenowings.width, eaglenowings.height, null);
				g.drawImage(justeaglewings.image, justeaglewings.xpos, justeaglewings.ypos, justeaglewings.width, justeaglewings.height, null);

			}

			if (!elephant.isAlive) {
				g.drawImage(elephantnotrunknotail.image, elephantnotrunknotail.xpos, elephantnotrunknotail.ypos, elephantnotrunknotail.width, elephantnotrunknotail.height, null);
				g.drawImage(justelephanttrunkandtail.image, justelephanttrunkandtail.xpos, justelephanttrunkandtail.ypos, justelephanttrunkandtail.width, justelephanttrunkandtail.height, null);
			}

			if (!hippo.isAlive) {
				g.drawImage(hipponotooth.image, hipponotooth.xpos, hipponotooth.ypos, hipponotooth.width, hipponotooth.height, null);
				g.drawImage(justhippotooth.image, justhippotooth.xpos, justhippotooth.ypos, justhippotooth.width, justhippotooth.height, null);
			}

			if (!lion.isAlive) {
				g.drawImage(lionnomane.image, lionnomane.xpos, lionnomane.ypos, lionnomane.width, lionnomane.height, null);
				g.drawImage(justlionmane.image, justlionmane.xpos, justlionmane.ypos, justlionmane.width, justlionmane.height, null);
				justlionmane.ypos = 2;
			}

			if (!snake.isAlive) {
				g.drawImage(snakenotoungeandfrown.image, snakenotoungeandfrown.xpos, snakenotoungeandfrown.ypos, snakenotoungeandfrown.width, snakenotoungeandfrown.height, null);
				g.drawImage(justsnaketounge.image, justsnaketounge.xpos, justsnaketounge.ypos, justsnaketounge.width, justsnaketounge.height, null);
			}

			// Draw the animals only if they are alive
			if (hippo.isAlive) g.drawImage(hippo.image, hippo.xpos, hippo.ypos, hippo.width, hippo.height, null);
			if (lion.isAlive) g.drawImage(lion.image, lion.xpos, lion.ypos, lion.width, lion.height, null);
			if (zebra.isAlive) g.drawImage(zebra.image, zebra.xpos, zebra.ypos, zebra.width, zebra.height, null);
			if (snake.isAlive) g.drawImage(snake.image, snake.xpos, snake.ypos, snake.width, snake.height, null);
			if (eagle.isAlive) g.drawImage(eagle.image, eagle.xpos, eagle.ypos, eagle.width, eagle.height, null);
			if (elephant.isAlive)
				g.drawImage(elephant.image, elephant.xpos, elephant.ypos, elephant.width, elephant.height, null);


			g.dispose();
			bufferStrategy.show();

		}

	}

}