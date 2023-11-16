package gamestuff;
import java.util.Scanner;
public class Position {
	Scanner scan = new Scanner(System.in);
	
	private char row;
	private int col;
	
	private int rowIndex;
	private int colIndex;
	
	public Position() {
		PositionChecker.checkPosition(scan.nextLine());
	}
	
	public Position(char row, int col) {
		this.row = Character.toUpperCase(row);
		this.col = col;
		row = Character.toLowerCase(row);

		rowIndex = Character.compare(row, 'a');
		colIndex = col-1;
	}
	
	public Position(int row, int col) {
		this.row = Character.toUpperCase((char)(row + 64));
		this.col = col;
		rowIndex = row-1;
		colIndex = col-1;
	}
	
	public int rowIndex() {
		return rowIndex;
	}
	
	public int colIndex() {
		return colIndex;
	}
	
	public char row() {
		return row;
	}
	
	public int column() {
		return col;
	}
	
	public String toString() {
		if(row == '@') {
			return "Invalid position" + "\nIndexes: " + rowIndex + ", " + colIndex; 
		}
		return row + "-" + col;
		//return row + "-" + col + "\nIndexes: " + rowIndex + ", " + colIndex;
	}
}
