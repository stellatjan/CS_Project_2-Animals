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
	private Discrepancies albinozebra, closeenoughtojustzebrastripes, eaglenowings, elephantnotrunknotail, hipponotooth,justeaglewings,justelephanttrunkandtail, justhippotooth, justlionmane, justsnaketounge,lionnomane,snakenotoungeandfrown;
	private Sun burningsun;

	public static void main(String[] args) {
		BasicGameApp ex = new BasicGameApp();
		new Thread(ex).start();
	}

	public BasicGameApp() {
		setUpGraphics();
		background = Toolkit.getDefaultToolkit().getImage("background.png");
		burningsun= new Sun(200,22,"burningsun.png");
		eagle = new Animals(10, 100, "eagle.png");
		lion = new Animals(100, 500, "lion.png");
		snake = new Animals(400, 500, "snake.png");
		zebra = new Animals(550, 500, "zebra.png");
		elephant = new Animals(250, 500, "elephant.png");
		hippo = new Animals(-50, 500, "hippo.png");

		albinozebra = new Discrepancies(100,100,"albinozebra.png");
		closeenoughtojustzebrastripes = new Discrepancies (500,500, "closeenoughtojustzebrastripes.png"); //no work??
		eaglenowings = new Discrepancies(100,200, "eaglenowings.png");
		elephantnotrunknotail = new Discrepancies (100,300, "elephantnotrunknotail.png");
		hipponotooth = new Discrepancies (100,400, "hipponotooth.png");
		justeaglewings = new Discrepancies (100,400, "justeaglewings.png");
		justelephanttrunkandtail= new Discrepancies (100,400, "justelephanttrunkandtail.png");
		justhippotooth= new Discrepancies (100,400, "justhippotooth.png");
		justlionmane= new Discrepancies (100,400, "justlionmane.png");
		justsnaketounge= new Discrepancies (100,400, "justsnaketounge.png");
		lionnomane= new Discrepancies (100,400, "lionnomane.png");
		snakenotoungeandfrown= new Discrepancies (100,400, "snakenotoungeandfrown.png");


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


		g.drawImage(background, 0, -5, 1500, 900, null);
		g.drawImage(burningsun.image, burningsun.xpos, burningsun.ypos, burningsun.width, burningsun.height, null);

		// Draw the animals on top of the background
		g.drawImage(hippo.image, hippo.xpos, hippo.ypos, hippo.width, hippo.height, null);
		g.drawImage(lion.image, lion.xpos, lion.ypos, lion.width, lion.height, null);
		g.drawImage(zebra.image, zebra.xpos, zebra.ypos, zebra.width, zebra.height, null);
		g.drawImage(snake.image, snake.xpos, snake.ypos, snake.width, snake.height, null);
		g.drawImage(eagle.image, eagle.xpos, eagle.ypos, eagle.width, eagle.height, null);
		g.drawImage(elephant.image, elephant.xpos, elephant.ypos, elephant.width, elephant.height, null);
		g.drawImage(elephant.image, elephant.xpos, elephant.ypos, elephant.width, elephant.height, null);

		//make these if statments if the regular animals are not alive, and all of them will then apear and spin to the bottom and then stay there still at the bottom
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




		g.dispose();
		bufferStrategy.show();

	}
}
