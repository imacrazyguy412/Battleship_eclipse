package gamestuff;

public class BattleshipGrid2 {
    Ocean ocean;
    char[][] boatsI;

    public BattleshipGrid2(Ocean ocean){
        this.ocean = ocean;
        boatsI = new char[10][10];
        
        for(int r = 0; r<10; r++){
            for(int c = 0; c<10; c++){
                boatsI[r][c] = '-';
            }
        }
    }

    public BattleshipGrid2(){
        ocean = new Ocean();
        boatsI = new char[10][10];
        
        for(int r = 0; r<10; r++){
            for(int c = 0; c<10; c++){
                boatsI[r][c] = '-';
            }
        }
    }

    public void shotAt(Position pos,boolean hit,char initial){
        if(hit){
            boatsI[pos.rowIndex()][pos.colIndex()] = initial;
            
        } else {
            boatsI[pos.rowIndex()][pos.colIndex()] = '#';
        }
    }

    public boolean hit(Position pos){
        if(boatsI[pos.rowIndex()][pos.colIndex()] != '#' && boatsI[pos.rowIndex()][pos.colIndex()] != '-'){
            return true;
        }return false;
    }

    public boolean miss(Position pos){
        if(boatsI[pos.rowIndex()][pos.colIndex()] == '#'){
            return true;
        } 
        return false;
    }

    public boolean empty(Position pos){
        if(boatsI[pos.rowIndex()][pos.colIndex()] == '-'){
            return true;
        }
        return false;
    }

    public char boatInitial(Position pos){
        return boatsI[pos.rowIndex()][pos.colIndex()];
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
