//*****************************************************************************
//Calvin Goah
//cgg2126
//Player class   
//Constructs the player/human user hand, and keeps track of his/her funds
//*****************************************************************************
import java.util.*;

public class Player
{
	double bankAccount;
	ArrayList<Card> playerHand;

	// Constructs player's available funds as well as player's hand
	public Player(double buyIn)
	{
		bankAccount = buyIn;
		playerHand = new ArrayList<Card>();

	} // End of constructor

	// If sum below 21 returns true, if not then player is busted
	public boolean giveCard(Card card)
	{
		playerHand.add(card);

		return (sumHand() <= 21);

	} // End of method

	// accounts for player bid
	double currentBid;
	double tempAmt;
	public void plBid(double moola)
	{
		currentBid = moola;
		tempAmt = bankAccount - currentBid;

	} // End of method

	// If player loses bet
	public void plLoses()
	{
		bankAccount -= currentBid;
	}

	public double getFunds()
	{
		return bankAccount;

	} // End of method

	public double getCurrentBid()
	{
		return currentBid;

	} // End of method

	// winnings if not blackjack
	public void playerWinReg()
	{
		bankAccount = bankAccount + currentBid;

	} // End of method

	// funds after tie
	public void push()
	{
		bankAccount = tempAmt + currentBid;

	} // End of method

	// funds after blackjack
	public void blackJack()
	{
		bankAccount = bankAccount + 2.5*currentBid;

	} // End of method

	public void showHand()
	{
		for (Card elem: playerHand)
		{
			System.out.println(elem.toStringVal());

		}
		

	} // End of method

	// Gets sum of user hand;
	public int sumHand()
	{
		
		int sum = 0;
		int countAce = 0;
		int val = 0;

		/*
		*Iterates over hand and get sum of cards
		*/
		for (Card elem: playerHand)
		{
			val = elem.getVal();
			if (val == 1)
			{
				countAce ++;
				sum += 11;

			} // End of if statement

			else if(val > 10)
			{
				sum += 10;

			} // End of else if

			else
			{
				sum += val;

			} // End  of else
			
		} // End of for loop

		/** 
		*if sum is over 21 and we have aces
		* 10 is subtracted from the sum and the number
		*of aces is decremented by 1
		*/

		while ( sum > 21 && countAce > 0)
		{
			sum -= 10;
			countAce--;

		} // End of while loop

		return sum;

	} // End of method

	public int sizeHand()
	{
		return playerHand.size();

	} // End of method

	public ArrayList<Card> handNow()
	{
		return playerHand;

	} // End of method

	public void discardHand()
	{
		playerHand.clear();

	} // End of method

} // End of class