package gamestuff;

public class BattleshipGrid {
    Ocean ocean;
    boolean[][] boats;
    char[][] boatsI;

    public BattleshipGrid(Ocean ocean){
        this.ocean = ocean;
        boats = new boolean[10][10];
        boatsI = new char[10][10];
        
        for(int r = 0; r<10; r++){
            for(int c = 0; c<10; c++){
                boatsI[r][c] = '-';
            }
        }
    }

    public BattleshipGrid(){
        ocean = new Ocean();
        boats = new boolean[10][10];
        boatsI = new char[10][10];
        
        for(int r = 0; r<10; r++){
            for(int c = 0; c<10; c++){
                boatsI[r][c] = '-';
            }
        }
    }

    public void shotAt(Position pos,boolean hit,char initial){
            boats[pos.rowIndex()][pos.colIndex()] = hit;
            if(hit){
                boatsI[pos.rowIndex()][pos.colIndex()] = ocean.boatInitial(pos);
                
            } else {
                boatsI[pos.rowIndex()][pos.colIndex()] = '#';
            }
    }

    public boolean hit(Position pos){
        if(ocean.hit(pos)){
            return true;
        }
            return false;
    }

    public boolean miss(Position pos){
        if(boatsI[pos.rowIndex()][pos.colIndex()] == 'n'){
            return true;
        } else {
            return false;
        }
    }

    public boolean empty(Position pos){
        if(boatsI[pos.rowIndex()][pos.colIndex()] == '-'){
            return true;
        } else {
            return false;
        }
    }

    public char boatInitial(Position pos){
        return ocean.boatInitial(pos);
    }

    public String toString(){
        String out = "";

        out += "  1 2 3 4 5 6 7 8 9 10\n";
        for(int r = 0; r< 10; r++){
            out += (char)('A' + r) + " ";
            for(int c = 0; c<10; c++){
                out += boatsI[r][c] + " "; 
            }
            out += "\n";
        }
        return out;
    }


}
