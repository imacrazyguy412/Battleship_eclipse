package gamestuff;
import java.util.ArrayList;

import exceptions.BoatOutOfBoundsException;
import exceptions.BoatOverlappingException;

public class Ocean {
    private ArrayList<Boat> boats;

    public Ocean(){
        boats = new ArrayList<Boat>();
    }


    public void placeBoat(String boatName, String direction, Position pos) throws BoatOutOfBoundsException, BoatOverlappingException {
        Boat testBoat = new Boat(boatName, pos, direction);

        //check for overlapping boats
        for(Boat b : boats){
            //vertical check
            if(direction.equals("Vertical")){
                for(int i = 0; i<testBoat.size()+1; i++){
                if(b.onBoat(new Position(pos.rowIndex()+1 + i, pos.colIndex()+ 1))){
                //    System.out.println(new Position(pos.rowIndex()+1 + i, pos.colIndex() + 1));
                    throw(new BoatOverlappingException());
                }
                /*if(b.onBoat(new Position(pos.rowIndex()+1, pos.colIndex() + i + 1))){
                    System.out.println(new Position(pos.colIndex()+1, pos.rowIndex() + i + 1));
                        throw(new BoatOverlappingException());
                    }
               
            */
            }
            }    
            if(direction.equals("Horizontal")){
                for(int i = 0; i<testBoat.size(); i++){
                    if(b.onBoat(new Position(pos.rowIndex()+1, pos.colIndex() + i + 1))){
                   //     System.out.println(new Position(pos.colIndex()+1, pos.rowIndex() + i + 1));
                        throw(new BoatOverlappingException());
                    }
                    /*if(b.onBoat(new Position(pos.colIndex()+1, pos.rowIndex() + i + 1))){
                        System.out.println(new Position(pos.colIndex()+1, pos.rowIndex() + i + 1));
                        throw(new BoatOverlappingException());
                }
                */
                }
            }
        }

        //check for boats out of bounds
        if(direction.equals("Vertical") && pos.rowIndex() + testBoat.size() >= 10){
            throw(new BoatOutOfBoundsException());
        } else if(direction.equals("Horizontal") && pos.colIndex() + testBoat.size() >= 10){
            throw(new BoatOutOfBoundsException());
        }


        boats.add(testBoat);
        //throw(new Exception());
    }

    //loop through boats and use onBoat with pos on each boat, return boat method if true 
    public void shootAt(Position pos){
        for(Boat b : boats){
            b.hit(pos);

        }
        
    }

    public boolean hit(Position pos){
        for(Boat b : boats){
            if(b.isHit(pos)){
                return true;
            }
        }
        return false;
    }
    
    public char boatInitial(Position pos){
        for(Boat b : boats){
            if(b.onBoat(pos)){
                return b.abbreviation().toCharArray()[0];
            }
        }
        return '-';
    }

    public Boat getBoat(Position pos){
        for(Boat b : boats){
            if(b.onBoat(pos)){
                return b;
            }
        }
        return null;
    }

    public String boatName(Position pos){
        for(Boat b : boats){
            if(b.onBoat(pos)){
                return b.name();
            }
        }
        return "-";
    }

    public boolean sunk(Position pos){
        for(Boat b : boats){
            if(b.onBoat(pos)){
                return b.sunk();
            }
        }
        return false;
    }

    public boolean allSunk(){
        for(Boat b : boats){
            if(!b.sunk()){
                return false;
            }
        }
        return true;
    }

    public void placeAllBoats(){
        boolean didItPlace = false;
        String orientation;
        orientation = (Math.random()>.5) ? "Vertical" : "Horizontal";
        String[] boats = {"Aircraft Carrier", "Battleship", "Cruiser", "Submarine", "Destroyer"};
        for(int i = 0; i <5; i++){
            didItPlace = false;
            while(!didItPlace){
                orientation = (Math.random()>.5) ? "Vertical" : "Horizontal";
            try{
                placeBoat(boats[i], orientation, new Position((int)(Math.random()*10 +1), (int)(Math.random()*10 +1)));
                didItPlace = true;
            } catch(BoatOutOfBoundsException b) {
      
            } catch(BoatOverlappingException o) {
      
            }
            
            }

        }
        

    }

    public String toString(){
        String out = "";
        /*
        for(Boat b : boats){
            out += "name: " + b.name() + "\nabbreviation: " + b.abbreviation() + "\ndirection:  " + b.direction() + "\nposition: " + b.position();
            out += "\n\n";
        }
        */

        out += "  1 2 3 4 5 6 7 8 9 10\n";
			for(char r = 'a'; r<='j'; r++){
				out += Character.toUpperCase(r) + " ";
				for(int c = 1; c<=10; c++){
					out += this.boatInitial(PositionChecker.checkPosition(r + "-" + c )) + " "; 
				}
				out += "\n";
			}
        return out;
    }
}
