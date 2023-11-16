package gamestuff;
import java.util.Scanner;

import exceptions.BoatOutOfBoundsException;
import exceptions.BoatOverlappingException;
public class Player {
    private String name;
    private BattleshipGrid2 grid;
    private Ocean ocean;
    //private Ocean enemyOcean;
    private Scanner scan = new Scanner(System.in);

    public Player(){
        name = null;
    }

    public Player(String name){
        this.name = name;
    }

    public void startGame(boolean place){
    	if(place) {
    		System.out.println("started game");
    	}
        
        ocean = new Ocean();
        grid = new BattleshipGrid2(ocean);
        if(place) {
        	System.out.println("would you like to place boats yourself? (y/n)");
            if(scan.nextLine().equals("y")) {
            	placeBoats();
            } else {
            	initializeGrid();
            }
        } else {
        	initializeGrid();
        }
        
        
        //put back for bot
        //initializeGrid();
        if(name == null){
            System.out.println("input name");
            name = scan.nextLine();
        }
    }
    public void placeBoats() {
    	ocean = new Ocean();
    	boatMe("Aircraft Carrier");
        System.out.println(ocean);
        boatMe("Battleship");
        System.out.println(ocean);
        boatMe("Cruiser");
        System.out.println(ocean);
        boatMe("Submarine");
        System.out.println(ocean);
        boatMe("Destroyer");
        System.out.println(ocean);
    	
    	
    	grid = new BattleshipGrid2(ocean);
    }

    public String playerName(){
        return name;
    }

    public Position shoot(){
        System.out.println("enter pos to shoot");
        Position pos = PositionChecker.checkPosition(scan.nextLine());
        while(pos.colIndex()==-1){
            System.out.println("invalid, try again");
            pos = PositionChecker.checkPosition(scan.nextLine());
        }
        return pos;
    }

    public Position randomShoot(){
        Position pos = new Position((int) (Math.random()*10 +1), (int) (Math.random()*10 +1));
        while(!(grid.boatsI[pos.rowIndex()][pos.colIndex()] == '-')){
            pos = new Position((int) (Math.random()*10 +1), (int) (Math.random()*10 +1));
        }
        return pos;
    }
    /*
     * This method shoots but smart
     */
    public Position smartShoot() {
        
    	int row =0;
    	int col =0;
    	for(int i = 1; i<11; i++) {
    		for(int j = 1; j<11; j++) {
    			Position pos = new Position(i, j);
    			//check for sunk later
    		    if(grid.hit(pos) && !grid.ocean.sunk(pos)) {
    		    	
    		    	Position posLeft = new Position(i,j-1); 
    		    	Position posUp = new Position(i-1,j); 
    		    	Position posRight = new Position(i,j+1); 
    		    	Position posDown = new Position(i+1,j); 
    		    	if(posLeft.colIndex()>-1 && grid.empty(posLeft) && ((posRight.colIndex() < 10 && grid.hit(posRight)) || posRight.colIndex() < 10)) {
    		    		
        		    		return posLeft;
    		    	}
    		    	
    		    	if(posUp.rowIndex() > -1 && grid.empty(posUp) && ((posDown.rowIndex()<10 && grid.hit(posDown)) || posDown.rowIndex()<10)) {
    		    		return posUp;
    		    	}
    		    	if(posRight.colIndex()<10 &&grid.empty(posRight) && ((posLeft.colIndex()>-1 && grid.hit(posLeft)) || posLeft.colIndex()>-1)) {
    		    		return posRight;
    		    	}
    		    	if(posDown.rowIndex()<10 && grid.empty(posDown) && ((posUp.rowIndex() > -1 && grid.hit(posUp)) || posUp.rowIndex() > -1)) {
    		    		return posDown;
    		    	}
    		    	
    		    	
    		    }
    		}
    		
    	}
    	while(col<10) {
    		row = col%2;
    		
    		while (row <10) {
    			Position pos = new Position(row +1, col +1);
    			if(grid.hit(pos) && !grid.ocean.sunk(pos)) {
    				Position posLeft = new Position(row+1, col);
    				Position posUp = new Position(row, col+1);
    				Position posRight = new Position(row+1, col+2);
    				Position posDown = new Position(row+2, col+1);
    				
    				if(posLeft.colIndex()>-1 && grid.empty(posLeft)) {
    		    		return posLeft;
		    	}
		    	
		    	if(posUp.rowIndex() > -1 && grid.empty(posUp)) {
		    		return posUp;
		    	}
		    	if(posRight.colIndex()<10 &&grid.empty(posRight)) {
		    		return posRight;
		    	}
		    	if(posDown.rowIndex()<10 && grid.empty(posDown)) {
		    		return posDown;
		    	}
    				
    				
    			}
    		
    			if(grid.empty(pos)) {
    				return pos;
    			}
    			
    			row+=2;
    		}
    		col++;
    	}
    	return randomShoot();
        
    }
    public void updateGrid(Position pos, boolean hit, char initial){
        grid.shotAt(pos, hit, initial);
    }

    public BattleshipGrid2 getGrid(){
        return grid;
    }

    public void initializeGrid(){
        ocean = new Ocean();
        grid = new BattleshipGrid2(ocean);
        ocean.placeAllBoats();
    }

    public void updatePlayer(Position pos, boolean hit, char initial, String boatName, boolean sunk, boolean gameOver, boolean tooManyTurns, int turns){
        System.out.println(grid);
        String isHit = (hit) ? ("hit") : ("miss");
        System.out.println("Turn #" + turns +": Your shot at " + pos +" was a " + isHit + ".");
        if(sunk){
            System.out.println("You sunk the " + boatName + "!");
        }
        if(gameOver){
            System.out.println("Game over, you took " + turns + " turns to win");
        }
    }
    
    private void boatMe(String ship){
        Position pos;
    	String orientation;
        System.out.println("place " + ship);
        //Ocean ocean, String type, String orientation, String pos
            do {
    	System.out.println("input a position in letter-number form");
		pos = PositionChecker.checkPosition(scan.nextLine());
		if(pos.colIndex()== -1) {
			System.out.println("invalid column");
		}
    	} while(pos.rowIndex() == -1);
		//input orientation of boat
    	do {
    		System.out.println("input an orientation: Vertical, Horizontal");
    		orientation = scan.nextLine();
    		if(!(orientation.equals("Vertical") || orientation.equals("Horizontal"))) {
    			System.out.println("invalid");
    		}
    	} while(!(orientation.equals("Vertical") || orientation.equals("Horizontal")));


		try{
				ocean.placeBoat(ship, orientation, pos);
				System.out.println("succesfully placed");
				} catch(BoatOutOfBoundsException e){
				System.out.println("Boat out of bounds, try again");
                boatMe(ship);
			} catch (BoatOverlappingException e){
				System.out.println("overlapping boats, try again");
                boatMe(ship);
			}

	}

}
