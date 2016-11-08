//*****************************************************************************
//Calvin Goah
//cgg2126
//Dealer class   
//Constructs the dealer/computer's hand as well as the rules the computer
//must abide by.
//*****************************************************************************
import java.lang.String;
import java.util.*;

public class Dealer
{
	ArrayList<Card> dealerHand;

	// Constructs dealer hand and adds drawn cards at start of game to
	public Dealer()
	{
		dealerHand = new ArrayList<Card>();

		// Will house number values of the cards
		
	} // End of constructor

	// Dealer adds drawn card to hand
	public boolean draw(Card card)
	{
		dealerHand.add(card);

		return (sumHand() >= 21);
	} // End of method

	public void discardHand()
	{
		dealerHand.clear();

	} // End of for loop

	public int sumHand()
	{
		int sum = 0;
		int countAce = 0;
		int val = 0;

		for (int i = 0; i < dealerHand.size(); i++)
		{
			Card carde = dealerHand.get(i);
			val = carde.getVal();
			if (val == 1)
			{
				countAce += 1;
				sum += 11;

			} // End of if statement

			else if(val > 10)
			{
				sum += 10;

			} // End of else if

			else
			{
				sum += val;

			} // End of else

		} // End of for loop
			
		while (sum > 21 && countAce > 0)
		{
			sum -= 10;
			countAce --;

		} // End of while loop

		if (sum == 2)
		{
			sum = 21;
		}

		return sum;

	} // End of method

	public void showOne()
	{
		// Shows first card in dealer hand
		Card firstCard = dealerHand.get(0);
		String nameCard = firstCard.toStringVal();

		System.out.println("Dealer's faceup card:\n" + nameCard);

	} // End of method

	public void revealHand()
	{
		System.out.println("these were the dealer's cards: ");

		for (Card elem: dealerHand)
		{
			System.out.println(elem.toStringVal());

		} // End of for loop

	} // End of method

	// Returns a string Hit of Stand to indicate continual playing or not
	public String hitOrStand()
	{
		String response = "";

		if (sumHand() < 17)
		{
			response = "H"; 

		}

		if (sumHand() >= 17)
		{
			response = "S";

		} // End 

		if (sumHand() == 2)
		{
			response = "S";
		}

		return response;

	} // End of method

	public ArrayList<Card> getHand()
	{
		return dealerHand;

	} // End of method



} // End of class