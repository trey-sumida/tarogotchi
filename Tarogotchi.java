import java.awt.Color;

public class Tarogotchi {
	// creating width variable
	static int width = 1200;
	// creating height variable
	static int height = 800;
	// creating font size variable
	static int fontsize = 200;
	// creating water variable
	static float water = 0.5f;
	// creating fertilizer variable
	static float fertilizer = 0.5f;
	// creating health variable
	static float health = 1.0f;
	// creating growth variable
	static float growth = 0.1f;
	// creating stem variable
	static EZRectangle stem;
	// creating taro variable
	static EZImage taro;
	// creating poop variable
	static EZImage poop;
	// creating wheelbarrow variable
	static EZImage wheelbarrow;
	// creating raindrop variable
	static EZImage raindrop;
	// creating wateringcan variable
	static EZImage wateringcan;
	// creating splash variable
	static EZSound splash;
	// creating fart variable
	static EZSound fart;
	// creating win variable
	static EZSound win;
	// creating lose variable
	static EZSound lose;
	// creating clickx variable
	static int clickx;
	// creating clicky variable
	static int clicky;

	// creating function called setuup
	public static void setup() {
		// setting up color for stem
		Color s = new Color(0, health / 2, 0);
		// setting up window
		EZ.initialize(width, height);
		// adding background image
		EZ.addImage("mud.jpeg", 600, 400);
		// creating the stem
		stem = EZ.addRectangle(600, 500, 50, 400, s, true);
		// adding image of taro and assigning it to variable taro
		taro = EZ.addImage("taro.gif", 600, 200);
		// adding image of poop and assigning it to variable poop
		poop = EZ.addImage("poop.png", 950, 450);
		// adding image of wheelbarrow and assigning it to variable wheelbarrow
		wheelbarrow = EZ.addImage("wheelbarrow.png", 950, 700);
		// adding image of water and assigning it to variable raindrop
		raindrop = EZ.addImage("raindrop.png", 250, 450);
		// adding image of watering can and assigning it to variable watering can
		wateringcan = EZ.addImage("wateringcan.png", 250, 700);
		// adding sound of water and assigning it to variable splash
		splash = EZ.addSound("watersplash.wav");
		// adding sound of farting and assigning it to variable fart
		fart = EZ.addSound("fart.wav");
		// adding sound for winning and assigning it to variable win
		win = EZ.addSound("cheer.wav");
		// adding sound for losing and assigning it to variable lose
		lose = EZ.addSound("boo.wav");
		// creating variable for mouse interaction
		clickx = 0;
		clicky = 0;
	}

	// creating function called game
	public static void game() {
		// creating start time
		long StartTime = System.currentTimeMillis();
		// running the game
		while (true) {
			// creating color for stem to change based on health
			Color c = new Color(0, health / 2, 0);
			// changing the color of the stem based on the new color
			stem.setColor(c);
			// creating variable for current time
			long CurrentTime = System.currentTimeMillis();
			// checking if a second has passed (changed to 100 to speed up game, set to 1000
			// for every second)
			if (CurrentTime - StartTime >= 100) {
				// decreasing water based on growth * 0.05
				water = water - growth * 0.05f;
				// decreasing water by 0.001
				water = water - 0.001f;
				// decreasing by fertilizer * 0.01
				water = water - fertilizer * 0.01f;
				// checking if water drops below 0
				if (water <= 0) {
					// set water to 0
					water = 0f;
					// if so, health decreases by 0.02
					health -= 0.02f;
				}
				// checking if water is greater than 1
				if (water > 1) {
					// if so, health decreases by 0.03
					health -= 0.03f;
				}
				// decreasing fertilizer by growth * 0.01
				fertilizer = fertilizer - growth * 0.01f;
				// decreasing fertilizer by 0.001
				fertilizer = fertilizer - 0.001f;
				// checking if fertilizer drops below 0
				if (fertilizer <= 0) {
					// setting fertilizer to 0
					fertilizer = 0f;
					// health drops by 0.01
					health -= 0.01f;
				}
				// checking if fertilizer is above 1
				if (fertilizer > 1) {
					// if so, health decreases by 0.02
					health -= 0.02f;
				}
				// checking if health is above 1
				if (health > 1) {
					// setting health to 1
					health = 1;
				}
				// checking if growth is greater than or equal to 1
				if (growth >= 1.0f) {
					// play winning sound
					win.play();
					// adding text to say "YOU WIN!"
					EZ.addText(width / 2, height / 2, "YOU WIN!", Color.BLACK, fontsize);
					// ending simulation
					break;
				}
				// checking if health is less than or equal to 0
				if (health <= 0.0f) {
					// play losing sound
					lose.play();
					// adding text to say "YOU LOSE"
					EZ.addText(width / 2, height / 2, "YOU LOSE", Color.RED, fontsize);
					// ending simulation
					break;
				}
				// adding 0.02 health
				health += 0.02f;
				// increasing growth by health * 0.001
				growth = growth + health * 0.001f;
				// scaling raindrop image based on water
				raindrop.scaleTo(1 + water);
				// scaling poop image based on fertilizer
				poop.scaleTo(1 + fertilizer);
				// scaling taro based on growth
				taro.scaleTo(1 + growth);
				// scaling stem based on growth
				stem.scaleTo(1 + growth);
				// setting start time to current time
				StartTime = CurrentTime;

			}
			// setting clickx to store value of position of x mouse
			clickx = EZInteraction.getXMouse();
			// setting clicky to store value of position of y mouse
			clicky = EZInteraction.getYMouse();
			// checking if left mouse button was pressed and released
			if (EZInteraction.wasMouseLeftButtonReleased()) {
				// checking if watering can image was clicked
				if (wateringcan.isPointInElement(clickx, clicky)) {
					// adding 0.3 to water
					water += 0.3f;
					// scaling image of raindrop to reflect water
					raindrop.scaleTo(1 + water);
					// playing water sound
					splash.play();

				}
				// checking if wheelbarrow image was clicked
				if (wheelbarrow.isPointInElement(clickx, clicky)) {
					// adding 0.3 to fertilizer
					fertilizer += 0.3f;
					// scaling poop image to reflect fertilizer
					poop.scaleTo(1 + fertilizer);
					// playing farting sound
					fart.play();

				}

			}
			// refreshing the screen
			EZ.refreshScreen();
			// printing out health variable
			System.out.println(health);
			// printing out water variable
			System.out.println(water);
			// printing out fertilizer variable
			System.out.println(fertilizer);
			// printing out growth variable
			System.out.println(growth);
		}

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// calling function setup
		setup();
		// calling function game
		game();

	}

}