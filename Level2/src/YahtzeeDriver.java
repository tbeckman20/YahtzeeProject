import java.util.Scanner;

public class YahtzeeDriver
{
	/*
	1. Creates a new instance of the YahtzeeGame class
	2. Calls the playGame method on the Yahtzee object
	3. Asks user if they would like to play again
	4. When the user is done playing, prints the number of games played, min, max, and average score
	*/
	public static void main(String [] args) {
		Scanner s = new Scanner(System.in);
		int numGames = 0;
		int score;
		int scoresSum = 0;
		int highScore = -1;
		int lowScore = 1000;
		System.out.print("Would you like to play?\nY for Yes, N for No: ");
		String decision = s.nextLine();
		char yOrN = decision.charAt(0);
		while (yOrN == 'y')
		{
			numGames++;
			YahtzeeGame myGame = new YahtzeeGame();
			System.out.println("Welcome to Trent's APCSA Yahtzee Game!");
			score = myGame.playGame();
			scoresSum = scoresSum + score;
			if (score < lowScore) {
				lowScore = score;
			}
			if (score > highScore) {
				highScore = score;
			}
			System.out.println("Would you like to play again?\nY for Yes, N for No:");
			decision = s.nextLine();
			yOrN = decision.charAt(0);
		}
		System.out.println("Number of games played:"+ numGames);
		System.out.println("Your highscore: "+ highScore);
		System.out.println("Your lowscore:"+ lowScore);
		System.out.println("Your Average Score:"+ (scoresSum *1.0)/(numGames*1.0));
	}
}
