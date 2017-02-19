package map;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import logic.Game;
import tile.Floor;
import tile.Tile;
import tile.Wall;

public class Map {

	private static int xTemp, yTemp;
	
	private int xTileCount, yTileCount;
	private ArrayList<Tile> tiles;
	
	public Map(String file) {
		this.tiles = fileToTiles(file);
		this.xTileCount = xTemp;
		this.yTileCount = yTemp;
		
	}	
	
	public void draw() {
		
		for (Tile tile : tiles) {
			tile.draw();
		}
	}
	
	public static ArrayList<Tile> fileToTiles(String file) {
		
		ArrayList<Tile> ret = new ArrayList<>();
		xTemp = 0;
		yTemp = 0;
		
		try {
			
			FileReader fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);
		
			String str;
			int line = 0;
			while ((str = br.readLine()) != null) {
				
				int index = 0;
				for (char c : str.toCharArray()) {
				    	switch (c) {
				    	
				    		case 'W':
				    			Wall wall = new Wall(index * Game.TILE_SIZE, line * Game.TILE_SIZE);
				    			Game.collisionEntities.add(wall);
				    			ret.add(wall);
				    			break;
				    			
				    		case 'O':
				    			ret.add(new Floor(index * Game.TILE_SIZE, line * Game.TILE_SIZE));
				    			break;
				    			
				    		default:
				    			break;
				    	}
				    	
				    	index++;
				    			
				}
				
				yTemp++;
				xTemp = str.length();
				line++;
			}
			
			br.close();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return ret;
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

	public ArrayList<Tile> getTiles() {
		return tiles;
	}

	public void setTiles(ArrayList<Tile> tiles) {
		this.tiles = tiles;
	}
	
	
	
}
