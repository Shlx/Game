package map;

import static util.Utils.*;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import logic.Game;

public class Map {

	private static int xTemp, yTemp;
	
	private int xTileCount, yTileCount;
	private char[] tiles;
	
	public Map(String file) {
		this.tiles = fileToChars(file);
		this.xTileCount = xTemp;
		this.yTileCount = yTemp;
		
	}	
	
	public void draw() {
		
		for (int y = 0; y < this.yTileCount; y++) {
			for (int x = 0; x < this.xTileCount; x++) {
				
				// Draw a grey rectangle if there's a wall
				if (this.tiles[y * xTileCount + x] == 'W') { glColor("grey"); }
				
				// Draw a white rectangle otherwise
				else { glColor("white"); }
				
				// Add or subtract values later on for scrolling
				drawSquare(Game.TILE_SIZE * x, Game.TILE_SIZE * y, Game.TILE_SIZE, Game.TILE_SIZE);
			}
		}
	}
	
	public static char[] fileToChars(String file) {
		
		String ret = new String();
		xTemp = 0;
		yTemp = 0;
		
		try {
			
			FileReader fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);
		
			String str;
			while ((str = br.readLine()) != null) {
			        ret += str;
			        yTemp++;
			        xTemp = str.length();
			}
			
			br.close();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return ret.toCharArray();
	}
	
	public int getSizeX() {
		return this.getxTileCount() * Game.TILE_SIZE;
	}
	
	public int getSizeY() {
		return this.getyTileCount() * Game.TILE_SIZE;
	}

	////////// GETTERS / SETTERS //////////

	public int getxTileCount() {
		return xTileCount;
	}

	public void setxTileCount(int xTileCount) {
		this.xTileCount = xTileCount;
	}

	public int getyTileCount() {
		return yTileCount;
	}

	public void setyTileCount(int yTileCount) {
		this.yTileCount = yTileCount;
	}

	public char[] getTiles() {
		return tiles;
	}

	public void setTiles(char[] tiles) {
		this.tiles = tiles;
	}
	
	
	
}
