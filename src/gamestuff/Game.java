package gamestuff;
public class Game {
    
    private Player player1;
    private Player player2;
    private Player computerPlayer;
    private int turns;

    //game against bot board
    public Game(Player player){
        turns = 0;
        this.player1 = player;
        this.player1.startGame(true);
        
        System.out.println(playPvBoard());
    }

    //pvp game
    public Game(Player player1, Player player2, boolean bot){
        if(bot){
        this.player1 = player1;
        this.computerPlayer = new Player("bot");
        computerPlayer.initializeGrid();
        player1.startGame(true);
        playPvC();
        } else {
        this.player1 = player1;
        this.player2 = player2;
        playPvP();
        }
    }
    public Game(Player computerPlayer, boolean bot) {
    	this.computerPlayer = computerPlayer;
    	player1 = new Player("play");
    	testAI();
    }
    /*
    public int testAI() {
    	
    	
    	
    	while(!computerPlayer.getGrid().ocean.allSunk() && turns < 100) {
    		turns++;
    		Position pos = computerPlayer.randomShoot();
    		computerPlayer.getGrid().ocean.shootAt(pos);
    		computerPlayer.updateGrid(pos, computerPlayer.getGrid().hit(pos), computerPlayer.getGrid().ocean.boatInitial(pos));
    		computerPlayer.updatePlayer(pos, computerPlayer.getGrid().hit(pos), computerPlayer.getGrid().ocean.boatInitial(pos), computerPlayer.getGrid().ocean.boatName(pos), computerPlayer.getGrid().ocean.sunk(pos), computerPlayer.getGrid().ocean.allSunk(), false, turns);
            
    	}
    	return turns;
    }
    */
    
    
    public int testAI() {
    	turns = 0;
    	computerPlayer.initializeGrid();
    	player1.startGame(false);
    	while(!player1.getGrid().ocean.allSunk() && turns < 100 && !computerPlayer.getGrid().ocean.allSunk()){
            turns++;
            if(computerPlayer.getGrid().ocean.allSunk()){
            	return turns;
                
            }
            Position pos = computerPlayer.smartShoot();
            player1.getGrid().ocean.shootAt(pos);
            computerPlayer.updateGrid(pos, player1.getGrid().ocean.hit(pos), player1.getGrid().ocean.boatInitial(pos));
            String hit;
            if(player1.getGrid().ocean.hit(pos)){
                hit = "hit";
            } else {
                hit = "miss";
            }
            //System.out.println(computerPlayer.getGrid().ocean);
            //comment this out later
            computerPlayer.updatePlayer(pos, player1.getGrid().ocean.hit(pos), player1.getGrid().ocean.boatInitial(pos), player1.getGrid().ocean.boatName(pos), player1.getGrid().ocean.sunk(pos), player1.getGrid().ocean.allSunk(), false, turns);
            if(player1.getGrid().ocean.allSunk()){
                System.out.println("Game over, the bot won in " + turns + " turns!");
                return turns;
            }
        }
        return turns;
    }
    
    public int playPvBoard(){
        System.out.println(this.player1.getGrid().ocean);
        
        while(!player1.getGrid().ocean.allSunk() && turns < 100){
            Position pos = player1.shoot();
            //System.out.println(this.player1.getGrid().ocean);
            player1.updateGrid(pos, player1.getGrid().hit(pos), player1.getGrid().ocean.boatInitial(pos));
            player1.updatePlayer(pos, player1.getGrid().hit(pos), player1.getGrid().ocean.boatInitial(pos), player1.getGrid().ocean.boatName(pos), player1.getGrid().ocean.sunk(pos), player1.getGrid().ocean.allSunk(), false, turns);
            turns++;
        }

        return turns;
    }

    public int playPvC(){
        
        System.out.println(this.player1.getGrid().ocean);
        
        while(!player1.getGrid().ocean.allSunk() && turns < 100 && !computerPlayer.getGrid().ocean.allSunk()){
            turns++;
            System.out.println(player1.playerName() + "'s turn");
            Position pos = player1.shoot();
            computerPlayer.getGrid().ocean.shootAt(pos);
            player1.updateGrid(pos, computerPlayer.getGrid().ocean.hit(pos), computerPlayer.getGrid().ocean.boatInitial(pos));
            player1.updatePlayer(pos, computerPlayer.getGrid().ocean.hit(pos), computerPlayer.getGrid().ocean.boatInitial(pos), computerPlayer.getGrid().ocean.boatName(pos), computerPlayer.getGrid().ocean.sunk(pos), computerPlayer.getGrid().ocean.allSunk(), false, turns);
            if(computerPlayer.getGrid().ocean.allSunk()){
                break;
            }
            pos = computerPlayer.randomShoot();
            player1.getGrid().ocean.shootAt(pos);
            computerPlayer.updateGrid(pos, player1.getGrid().ocean.hit(pos), player1.getGrid().ocean.boatInitial(pos));
            String hit;
            if(player1.getGrid().ocean.hit(pos)){
                hit = "hit";
            } else {
                hit = "miss";
            }
            System.out.println("computer shot at " + pos.toString() + ", it was a " + hit);
            //System.out.println(computerPlayer.getGrid().ocean);
            //comment this out later
            //computerPlayer.updatePlayer(pos, player1.getGrid().ocean.hit(pos), player1.getGrid().ocean.boatInitial(pos), player1.getGrid().ocean.boatName(pos), player1.getGrid().ocean.sunk(pos), player1.getGrid().ocean.allSunk(), false, turns);
            if(player1.getGrid().ocean.allSunk()){
                System.out.println("Game over, the bot won in " + turns + " turns! (L)");
                break;
            }
        }
        return turns;
    }
    void playPvP(){}
}
