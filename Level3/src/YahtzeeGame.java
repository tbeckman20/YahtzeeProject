import java.util.Scanner;
public class YahtzeeGame
{
	private YahtzeeDie die1;
	private YahtzeeDie die2;
	private YahtzeeDie die3;
	private YahtzeeDie die4;
	private YahtzeeDie die5;
	private YahtzeeScorecard scoreBoard;
	private static final int NUM_SIDES = 6;
	/* instance data should include the five yahtzee dice, a scoreboard, and a CONSTANT (static final) variable NUM_SIDES
	which should be set to six (the number of sides on a yahtzee die) */


	/* initializes the dice and scoreboard */
	public YahtzeeGame()
	{
		die1 = new YahtzeeDie(NUM_SIDES);
		die2 = new YahtzeeDie(NUM_SIDES);
		die3 = new YahtzeeDie(NUM_SIDES);
		die4 = new YahtzeeDie(NUM_SIDES);
		die5 = new YahtzeeDie(NUM_SIDES);
		scoreBoard = new YahtzeeScorecard();
		/* your code here */
	}

	/* check to see if the game is over, and while the game is not over call the method takeTurn()
	once the game ends (hint: there are 13 turns in a game of Yahtzee), the method should print a
	final scorecard and return the grand total */
	public int playGame()
	{
		for(int i = 0;i < 13;i++){
			takeTurn();
		}
		System.out.println(scoreBoard.getGrandTotal());
		return 0;
	}

	/* 	1. call rollDice()
		2. call printDice()
		3. Ask the user if they are satisfied with their roll, or if they would like to roll again.
		   If they are not satisfied continue, otherwise call markScore()
		4. call chooseFrozen()
		5. call rollDice()
		6. call printDice()
		7. Ask the user if they are satisfied with their roll, or if they would like to roll again.
		   If they are not satisfied continue, otherwise call markScore()
		8. call chooseFrozen()
		9. call rollDice()
		10. call printDice()
		11. call markScore()
	*/
	private void takeTurn()
	{
		Scanner s = new Scanner(System.in);
		rollDice();
		printDice();
		String satisfiedChecker;
		System.out.println("Are you satisfied with your roll?\nEnter Y, or N to roll again");
		satisfiedChecker = s.nextLine();
		int numRolls = 0;
		while(satisfiedChecker.equals("N") && numRolls < 2){
			chooseFrozen();
			rollDice();
			printDice();
			System.out.println("Are you satisfied with your roll?\nEnter Y, or N to roll again");
			satisfiedChecker = s.nextLine();
			numRolls++;
		}
		markScore();
	}

	/* Rolls all unfrozen dice.  Also resets all dice to the unfrozen state after the roll */
	private void rollDice()
	{
		if(!die1.isFrozen()){
			die1.rollDie();
		}else if(!die2.isFrozen()) {
			die2.rollDie();
		}else if(!die3.isFrozen()) {
			die3.rollDie();
		}else if(!die4.isFrozen()) {
			die4.rollDie();
		}else if(!die5.isFrozen()){
			die5.rollDie();
		}
		die1.unfreezeDie();
		die2.unfreezeDie();
		die3.unfreezeDie();
		die4.unfreezeDie();
		die5.unfreezeDie();
	}

	/* Asks the user which dice should be frozen 1-5 (should be read in in one line) */
	private void chooseFrozen()
	{
		Scanner s = new Scanner(System.in);
		String freezeChoice;
		System.out.print("Which Dice would you like to freeze: ");
		freezeChoice = s.nextLine();
		for(int i = 0; i < freezeChoice.length(); i++){
			int x = 0;
			int y = 1;
			char die = (freezeChoice.substring(x,y)).charAt(x);
			switch (die){
				case '1': die1.freezeDie();
				break;
				case '2': die2.freezeDie();
				break;
				case '3': die3.freezeDie();
				break;
				case '4': die4.freezeDie();
				break;
				case '5': die5.freezeDie();
				break;
			}
		x++;
		x++;
		y++;
		y++;
		}
	}

	/* Should print the value of all five dice separated by a tab (\t) to the console */
	private void printDice()
	{
		System.out.println(die1+"\t"+die2+"\t"+die3+"\t"+die4+"\t"+die5);
	}

	/* 1. Print a scoreboard
	   2. Ask the user where they would like to mark their score.
	   3. Call appropriate function
	   4. If false is returned the user entered an invalid number, so ask the user to try again	*/
	private void markScore()
	{
		Scanner s = new Scanner(System.in);
		int markScore;
		boolean markValid = false;
		while(!markValid) {
			System.out.println(scoreBoard);
			System.out.println("would you like to mark your score? \n 1 = Ones \n 2 = Twos \n 3 = Threes \n 4 = Fours \n " +
					"5 = Fives \n 6 = Sixes \n 7 = 3 of a Kind \n 8 = 4 of a Kind \n 9 = Full House \n 10 = Small Straight " +
					"\n 11 = Large Straight \n 12 = Yahtzee \n 13 = Chance");
			markScore = s.nextInt();
			boolean isValid = markScore <= 13 && markScore > 0;
			if(isValid)
				switch (markScore) {
					case 1:
						markValid = scoreBoard.markOnes(die1.getValue(), die2.getValue(), die3.getValue(), die4.getValue(), die5.getValue());
						break;
					case 2:
						markValid = scoreBoard.markTwos(die1.getValue(), die2.getValue(), die3.getValue(), die4.getValue(), die5.getValue());
						break;
					case 3:
						markValid = scoreBoard.markThrees(die1.getValue(), die2.getValue(), die3.getValue(), die4.getValue(), die5.getValue());
						break;
					case 4:
						markValid = scoreBoard.markFours(die1.getValue(), die2.getValue(), die3.getValue(), die4.getValue(), die5.getValue());
						break;
					case 5:
						markValid = scoreBoard.markFives(die1.getValue(), die2.getValue(), die3.getValue(), die4.getValue(), die5.getValue());
						break;
					case 6:
						markValid = scoreBoard.markSixes(die1.getValue(), die2.getValue(), die3.getValue(), die4.getValue(), die5.getValue());
						break;
					case 7:
						markValid = scoreBoard.markThreeOfAKind(die1.getValue(), die2.getValue(), die3.getValue(), die4.getValue(), die5.getValue());
						break;
					case 8:
						markValid = scoreBoard.markFourOfAKind(die1.getValue(), die2.getValue(), die3.getValue(), die4.getValue(), die5.getValue());
						break;
					case 9:
						markValid = scoreBoard.markFullHouse(die1.getValue(), die2.getValue(), die3.getValue(), die4.getValue(), die5.getValue());
						break;
					case 10:
						markValid = scoreBoard.markSmallStraight(die1.getValue(), die2.getValue(), die3.getValue(), die4.getValue(), die5.getValue());
						break;
					case 11:
						markValid = scoreBoard.markLargeStraight(die1.getValue(), die2.getValue(), die3.getValue(), die4.getValue(), die5.getValue());
						break;
					case 12:
						markValid = scoreBoard.markYahtzee(die1.getValue(), die2.getValue(), die3.getValue(), die4.getValue(), die5.getValue());
						break;
					case 13:
						markValid = scoreBoard.markChance(die1.getValue(), die2.getValue(), die3.getValue(), die4.getValue(), die5.getValue());
						break;
				}else{
				System.out.println("Invalid Input, Enter a Valid option");
			}
		}
	}
}