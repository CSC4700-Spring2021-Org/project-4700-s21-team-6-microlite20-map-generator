package main;

public class Dungeon {

	private final int SIDELENGTH = 500; //this is currently the default side length of the dungeon, can be changed at pretty much any time
	public final int SEED; //SEED is the seed to be used in random generation
	public final int X; //X is the horizontal side length of the map
	public final int Y; //Y is the vertical side length of the map 
	public boolean[][] d; //The dungeon is stored in a 2D array of booleans, where True is a wall and False is open air
	
	//this is the default constructor, it automatically gives the dungeon a default size of 500x500
	public Dungeon(int seed) {
		SEED = seed;
		X = SIDELENGTH;
		Y = SIDELENGTH;
		d = new boolean[X][Y];
	}
	
	public Dungeon(int seed, int x, int y) {
		SEED = seed;
		X = x;
		Y = y;
		d = new boolean[X][Y];
	}
	
	public void setLayout(boolean[][] in) {
		d = in;
	}
	
	
}
