package logic;

import org.lwjgl.glfw.*;
import org.lwjgl.opengl.*;

import entity.Player;
import entity.Enemy;
import entity.Entity;
import entity.Money;

import static org.lwjgl.glfw.Callbacks.*;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryUtil.*;

import java.util.ArrayList;

public class Game {
	
	public static boolean DEBUG = 1 == 1;
	
	public static int WINDOW_X = 800;
	public static int WINDOW_Y = 800;
	public static String WINDOW_TITLE = "Game";
	
	public static int MOUSE_X = 0;
	public static int MOUSE_Y = 0;
	//public static int MOUSE_DX = 0;
	//public static int MOUSE_DY = 0;

	public static ArrayList<Entity> activeEntities = new ArrayList<>();
	public static ArrayList<Entity> deleteEntities = new ArrayList<>();
	public static ArrayList<Entity> spawnEntities = new ArrayList<>();
	
	public static int SIZE_X = 800;
	public static int SIZE_Y = 800;
	public static int ENTITY_MAX_AGE = 10000;
	public static int FRAME_COUNT = 0;
	
	public static Player character = null;
	public static int money = 0;
	public static int xp = 0;
	public static float SHOT_SPEED = 5;
	
	public static boolean PAUSE = false;
	
	public static int x = 1;
	
	public static int PAUSE_KEY = GLFW_KEY_P;
	public static int UP_KEY = GLFW_KEY_W;
	public static int LEFT_KEY = GLFW_KEY_A;
	public static int DOWN_KEY = GLFW_KEY_S;
	public static int RIGHT_KEY = GLFW_KEY_D;
	
	public static boolean UP_HELD = false;
	public static boolean LEFT_HELD = false;
	public static boolean DOWN_HELD = false;
	public static boolean RIGHT_HELD = false;
	public static boolean DIAG_MOVE = false;

	// The window handle
	private long window;

	public void run() {

		try {
			
			init();
			loop();

			// Free the window callbacks and destroy the window
			glfwFreeCallbacks(window);
			glfwDestroyWindow(window);
		} finally {
			// Terminate GLFW and free the error callback
			glfwTerminate();
			glfwSetErrorCallback(null).free();
		}
	}

	private void init() {
		// Setup an error callback. The default implementation
		// will print the error message in System.err.
		GLFWErrorCallback.createPrint(System.err).set();

		// Initialize GLFW. Most GLFW functions will not work before doing this.
		if (!glfwInit()) { throw new IllegalStateException("Unable to initialize GLFW"); }

		// Configure our window
		glfwDefaultWindowHints(); // optional, the current window hints are already the default
		glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE); // the window will stay hidden after creation
		glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE); // the window will be resizable

		// Create the window
		window = glfwCreateWindow(WINDOW_X, WINDOW_Y, "Hello World!", NULL, NULL);
		
		if (window == NULL) { throw new RuntimeException("Failed to create the GLFW window"); }
		
        // Initialize all mouse values as 0
        glfwSetCursorPosCallback(window, (window, xpos, ypos) -> {
        	
            // Add delta of x and y mouse coordinates
            //MOUSE_DX += (int)xpos - MOUSE_X;
            //MOUSE_DY += (int)xpos - MOUSE_Y;
            // Set new positions of x and y
            MOUSE_X = (int) xpos;
            MOUSE_Y = (int) ypos;
     
        });
        
        glfwSetMouseButtonCallback(window, (window, button, action, mods) -> {
			if (button == GLFW_MOUSE_BUTTON_LEFT && action == GLFW_PRESS && character != null && !PAUSE) {
				character.shootTowards(MOUSE_X, MOUSE_Y, SHOT_SPEED);
			}
        });

		// Setup a key callback. It will be called every time a key is pressed, repeated or released.
		glfwSetKeyCallback(window, (window, key, scancode, action, mods) -> {
			
			// ESC: Close window
			if (key == GLFW_KEY_ESCAPE && action == GLFW_RELEASE)
				glfwSetWindowShouldClose(window, true); // We will detect this in our rendering loop
			
			// N: Spawn enemy
			if (key == GLFW_KEY_N && action == GLFW_RELEASE) {
				new Enemy(MOUSE_X, MOUSE_Y);
			}
			
			// B: Spawn money
			if (key == GLFW_KEY_B && action == GLFW_RELEASE) {
				new Money(MOUSE_X, MOUSE_Y, 10);
			}
			
			// V: Spawn player
			if (key == GLFW_KEY_V && action == GLFW_RELEASE) {
				character = new Player(MOUSE_X, MOUSE_Y);
			}
			
			// P: Pause / Unpause
			if (key == PAUSE_KEY && action == GLFW_RELEASE) {
				PAUSE = !PAUSE;
			}
			
			// WASD: Player movement
			if (key == UP_KEY && action == GLFW_PRESS && character != null) {
				UP_HELD = true;
				diag();
				
				character.setDy(-character.getSpeed());
				if (LEFT_HELD) { character.setDx(-character.getSpeed()); }
				if (RIGHT_HELD) { character.setDx(character.getSpeed()); }
			}
			
			if (key == LEFT_KEY && action == GLFW_PRESS && character != null) {
				LEFT_HELD = true;
				diag();
				
				character.setDx(-character.getSpeed());
				if (UP_HELD) { character.setDy(-character.getSpeed()); }
				if (DOWN_HELD) { character.setDy(character.getSpeed()); }
			}
			
			if (key == DOWN_KEY && action == GLFW_PRESS && character != null) {
				DOWN_HELD = true;
				diag();
				
				character.setDy(character.getSpeed());
				if (LEFT_HELD) { character.setDx(-character.getSpeed()); }
				if (RIGHT_HELD) { character.setDx(character.getSpeed()); }
			}
			
			if (key == RIGHT_KEY && action == GLFW_PRESS && character != null) {
				RIGHT_HELD = true;
				diag();
				
				character.setDx(character.getSpeed());
				if (UP_HELD) { character.setDy(-character.getSpeed()); }
				if (DOWN_HELD) { character.setDy(character.getSpeed()); }
				
			}
			
			// Stopping player movement
			if (key == UP_KEY && action == GLFW_RELEASE && character != null) {
				UP_HELD = false;
				diag();
				
				character.setDy(0);
				if (DOWN_HELD) { character.setDy(character.getSpeed()); }			
				if (LEFT_HELD) { character.setDx(-character.getSpeed()); }
				if (RIGHT_HELD) { character.setDx(character.getSpeed()); }
				
			}
			
			if (key == LEFT_KEY && action == GLFW_RELEASE && character != null) {
				LEFT_HELD = false;
				diag();
				
				character.setDx(0);
				if (RIGHT_HELD) { character.setDx(character.getSpeed()); }
				if (UP_HELD) { character.setDy(-character.getSpeed()); }
				if (DOWN_HELD) { character.setDy(character.getSpeed()); }
				
			}
			
			if (key == DOWN_KEY && action == GLFW_RELEASE && character != null) {
				DOWN_HELD = false;
				diag();
				
				character.setDy(0);
				if (UP_HELD) { character.setDy(-character.getSpeed()); }
				if (LEFT_HELD) { character.setDx(-character.getSpeed()); }
				if (RIGHT_HELD) { character.setDx(character.getSpeed()); }
				
			}
			
			if (key == RIGHT_KEY && action == GLFW_RELEASE && character != null) {
				RIGHT_HELD = false;
				diag();
				
				character.setDx(0);
				if (LEFT_HELD) { character.setDx(-character.getSpeed()); }
				if (UP_HELD) { character.setDy(-character.getSpeed()); }
				if (DOWN_HELD) { character.setDy(character.getSpeed()); }
				
			}
		});

		// Get the resolution of the primary monitor
		GLFWVidMode vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor());
		// Center our window
		glfwSetWindowPos(
			window,
			(vidmode.width() - WINDOW_X) / 2,
			(vidmode.height() - WINDOW_Y) / 2
		);

		// Make the OpenGL context current
		glfwMakeContextCurrent(window);
		// Enable v-sync
		glfwSwapInterval(1);
		
		// Make the window visible
		glfwShowWindow(window);
	}

	private void loop() {
		// This line is critical for LWJGL's interoperation with GLFW's
		// OpenGL context, or any context that is managed externally.
		// LWJGL detects the context that is current in the current thread,
		// creates the GLCapabilities instance and makes the OpenGL
		// bindings available for use.
		GL.createCapabilities();

		/*glOrtho(100.0, 100.0, 100.0, 100.0, 1.0, -1.0);
		glViewport(0, 0, WINDOW_X, WINDOW_Y);
		glDisable(GL_TEXTURE_2D);*/
		
		// Set the clear color
		glClearColor(0.0f, 0.0f, 0.0f, 0.0f);

		// Run the rendering loop until the user has attempted to close
		// the window or has pressed the ESCAPE key.
		while (!glfwWindowShouldClose(window)) {
			
			glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT); // clear the framebuffer
			
			Logic.nextFrame();
			
			glfwSwapBuffers(window); // swap the color buffers
			
			// Poll for window events. The key callback above will only be
			// invoked during this call.
			glfwPollEvents();
			
		}
	}
	
	// Detect if the player is moving diagonally
	private static void diag() {
		if ((UP_HELD || DOWN_HELD) && (LEFT_HELD || RIGHT_HELD)) { DIAG_MOVE = true; }
		else { DIAG_MOVE = false; }
	}

	public static void main(String[] args) {
		new Game().run();
	}

}