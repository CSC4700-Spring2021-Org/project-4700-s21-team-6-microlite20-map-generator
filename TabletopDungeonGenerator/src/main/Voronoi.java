package main;

import java.util.ArrayList;
import java.util.Random;

public class Voronoi {
	
	static double distance(int x1, int x2, int y1, int y2) {
		return Math.abs(x1 - x2) + Math.abs(y1 - y2); //Manhattan Distance looks cool
	}
	
	//use this template file to make your dungeon layout randomizer
	//be sure to try to implement the seed so the randomizer consistently outputs the same thing using any given seed
	public static boolean[][] randomize(boolean[][] in, int seed, int cells) {
		//d is the temporary array that you'll use to make the layout, currently initialized as all False values.
		boolean[][] d = new boolean[in.length][in[0].length];
		
		//randomize the dungeon here
		int n = 0;
		Random rand = new Random(seed);
		int[] px = new int[cells];
		int[] py = new int[cells];
		for (int i = 0; i < cells; i++) {
			px[i] = rand.nextInt(in[0].length);
			py[i] = rand.nextInt(in.length);
 
		}
		for (int x = 0; x < in[0].length; x++) {
			for (int y = 0; y < in.length; y++) {
				n = 0;
				for (int i = 0; i < cells; i++) {
					if (distance(px[i], x, py[i], y) < distance(px[n], x, py[n], y)) {
						n = i; //find the closest point to x,y
					}
				}
				if(n%3==0||n%3==1) {
					d[x][y]= true;
				}
			}
		}
		
		return d;
	}
	
	public Dungeon[] findRooms(Dungeon d) {
		
		//find each individual room segment and put it into roomSegments
		ArrayList<ArrayList<int[]>> roomSegments = new ArrayList<ArrayList<int[]>>();
		int count = 0;
		for(int x = 0; x < d.X; x++) { //this is REALLY SLOW but if it works, I guess its fine
			for(int y = 0; y < d.Y; y++) {
				for(ArrayList<int[]> al : roomSegments) {
					if(al.contains(new int[]{x-1,y}) || al.contains(new int[]{x,y-1})) {
						al.add(new int[] {x,y});
					}
				}
			}
		}
		
		//join the room segments that connect
		//wow this is like O(n^4)
		for(ArrayList<int[]> a : roomSegments) { //this is also REALLY SLOW but its 1am and I dont want to think of a more elegant solution
			for(int[] i : a) {
				for(ArrayList<int[]> b : roomSegments) {
					if(!a.equals(b) && b.contains(i)) {
						if(a.size() <= b.size()) {
							for(int[] n : a) {
								if(!b.contains(n)) {b.add(n);}
							}
							roomSegments.remove(a);
						} else {
							for(int[] n : b) {
								if(!a.contains(n)) {a.add(n);}
							}
							roomSegments.remove(b);
						}
					}
				}
			}
		}
		
		for(ArrayList<int[]> l : roomSegments) {
			for(int j = 0; j < d.X; j++) {
				for(int k = 0; k < d.Y; k++) {
					
				}
			}
		}
		return new Dungeon[]{d};
	}
	
	public static void main(String args[]) {
		//this is the test method, it prints out the random dungeon with a seed of 1234 at the default size
		Dungeon dun = new Dungeon(123456);
		dun.setLayout(randomize(dun.d, dun.SEED, 20));
//		System.out.println(Arrays.toString(dun.d));
		DungeonViewer dv = new DungeonViewer(dun,1);
		dv.setVisible(true);
	}
	
}
