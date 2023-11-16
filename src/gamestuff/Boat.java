package gamestuff;
import java.util.Arrays;


//TODO make boat not be created if part of it is out of bounds; method that calls constructor
//use your brain


public class Boat {
	private Position pos;
	private String type;
	private String orientation;
	private int size;
	private boolean[] pieceHealth;
	
	public Boat(String type, Position pos, String orientation) {
		this.pos = pos;
		this.type = type;
		/*
		if(orientation.equals("Vertical")){
			this.orientation = "Horizontal";
		} if(orientation.equals("Horizontal")){
			this.orientation = "Vertical";
		}
		 */
		this.orientation = orientation;
		
		
		//set sizes
		if(type.equals("Aircraft Carrier")) {
			size = 5;
		} else if(type.equals("Battleship")) {
			size = 4;
		} else if(type.equals("Cruiser") || type.equals("Submarine")) {
			size = 3;
		} else if(type.equals("Destroyer")) {
			size = 2;
		}
		
		pieceHealth = new boolean[size];
		Arrays.fill(pieceHealth, true);
	}
	
	public String name() {
		return type;
	}
	
	public String abbreviation() {
		return type.substring(0,1);
	}
	
	public Position position() {
		return pos;
	}
	
	public String direction() {
		return orientation;
	}

	public int size(){
		return size;
	}
	
	public boolean onBoat(Position guess) {
		if(orientation.equals("Horizontal")) {
			if(guess.rowIndex() == pos.rowIndex() && guess.colIndex() >= pos.colIndex() && guess.colIndex() < pos.colIndex() + size) {
				return true;
			}
		} else if(orientation.equals("Vertical")) {
			if(guess.colIndex() == pos.colIndex() && guess.rowIndex() >= pos.rowIndex() && guess.rowIndex() < pos.rowIndex() + size) {
				return true;
			}
		}
		return false;
	}
	
	public boolean isHit(Position guess) {
		//temp
		if(onBoat(guess)) {
			if(orientation.equals("Vertical")) {
				return !pieceHealth[guess.rowIndex()-pos.rowIndex()];
			}
			 if(orientation.equals("Horizontal")) {
				return !pieceHealth[guess.colIndex()-pos.colIndex()];
			}
		}
		//System.out.println("pos not on boat");
		return false;
	}
	
	public void hit(Position guess) {
		if(onBoat(guess)) {
			//System.out.println("onboat");
			if(orientation.equals("Horizontal")) {
				//System.out.println(guess);
				//System.out.println(pos);
				pieceHealth[guess.colIndex()-pos.colIndex()] = false;
			}
			if(orientation.equals("Vertical")) {
				pieceHealth[guess.rowIndex()-pos.rowIndex()] = false;
			}
		}
	}
	
	public boolean sunk() {
		for(boolean b : pieceHealth) {
			if(b) {
				return false;
			}
		}
		return true;
	}
	
	public String toString(){
		String out = "";
		for(boolean b : pieceHealth){
			out += b + " ";
		}
		return out;
	}
}
