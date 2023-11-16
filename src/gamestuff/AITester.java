package gamestuff;

public class AITester {
	
	public static double avgTurns(int games) {
		int sum = 0;
		for(int i = 0; i < games; i++) {
			Game gamer = new Game(new Player("bot"), true);
			
			sum += gamer.testAI();
			System.out.println("game");
		}
	System.out.println(sum);
	System.out.println(games);

		return (double)((double)(sum)/(double)(games));
	}
}
