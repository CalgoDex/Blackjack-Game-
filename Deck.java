//*****************************************************************************
//Calvin Goah
//cgg2126
//Deck class   
//creates the Deck necessary to play Balckjack along with the methods
//needed as well, such as getDeck()
//*****************************************************************************

import java.util.ArrayList;
import java.lang.Math;
import java.util.Collections;

public class Deck
{
	ArrayList<Card> deck;
	ArrayList<Card> usedPile;

	// Constructs the deck for Blackjack
	public Deck()
	{
		//Initializes the deck and usedPile
		deck = new ArrayList<Card>();
		usedPile = new ArrayList<Card>();

		int counter = 0;
		for (int i = 0; i < 4; i++)
		{
			for(int j = 1; j < 14; j++)
			{
				deck.add(new Card(i,j));

			} // End of for loop

		} // End of for statement

		shuffle();

	} // End of constructor

	int count;
	// Method for shuffling the deck

	public void shuffle()
	{
		count = 0;
		// Ensures good shuffling
		while (count < 100)
		{
			// Generates two random numbers between 0 and 51
			int rand1 = (int)(Math.random()*52);
			int rand2 = (int)(Math.random()*52);

			// if the two randomly generated numbers are not equal
			if(rand1 != rand2)
			{
				// Swaps values at indices rand1 & rand2
				Collections.swap(deck, rand1, rand2);
				count = count + 1;

			} // End of if stateent

		} // End of for loop

	} // End of method

	// Adds the used pile back to the deck
	public void refillDeck()
	{
		for (int i = 0; i < usedPile.size(); i++)
		{
			// adds elements in usedPile back to the deck
			Card val = usedPile.get(i);
			deck.add(val);
			usedPile.remove(val);

		} // End of for loop

	} // End of method

	// This will remove a card from the deck and return it
	Card hitCard;
	public Card dealCard()
	{
		if (deck.size() >= 12)
		{
			// sets a variable to represent top of deck
			int topDeck = deck.size()-1;

			// assigns value of topcard to hitCard then removes
			// that card from the deck
			hitCard = deck.get(topDeck);
			deck.remove(topDeck);

		} // End of if statement

		else
		{
			// Will reshuffle the deck and call itself again
			refillDeck();
			shuffle();
			dealCard();

		} // End of else statement

		return hitCard;
 
	} // End of method

	// Will add used hand to used pile of cards
	public ArrayList<Card> discard(ArrayList<Card> hand)
	{
		ArrayList<Card> mockHand = new ArrayList<Card>(hand);
		for (int i = 0; i < mockHand.size(); i++)
		{
			usedPile.add(mockHand.get(i));
			deck.remove(mockHand.get(i));

		} // End of for loop

		return hand;

	} // End of method

	// Returns the current state of the deck
	public ArrayList<Card> getDeck()
	{
		return deck;

	} // End of method

	// Returns the current state of the used pile
	public ArrayList<Card> getUsedPile()
	{
		return usedPile;

	} // End of method

	} // End of class