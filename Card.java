//*****************************************************************************
//Calvin Goah
//cgg2126
//Card class   
//creates the cards necessary to play Balckjack
//*****************************************************************************

import java.util.ArrayList;

public class Card
{
	// Declaration and initialization of variables
	public final static int spades = 0;
	public final static int clubs = 1;
	public final static int hearts = 2;
	public final static int diamonds = 3; 

	// Declaration and initialization of face classes
	public final static int ACE = 1;
	public final static int JACK = 11;
	public final static int QUEEN = 12;
	public final static int KING = 13;

	// The composition of the any single card
	private final int suit;
	private final int value;

	// This constructor should construct a card based on values inputed
	public Card(int suit1, int numVal)
	{
		// initializes the suit and value of the card
		suit = suit1;
		value = numVal;

	} // End of constructor

	
	public int getSuit()
	{
		// will simply return the integer value of the suit
		return suit;

	} // End of method

	public int getVal()
	{
		// will simply return the integer value associated
		// with the a card
		return value;

	} // End of method

	// Will convert a number representation of a suit into its
	// actual suit representation
	public String toStringSuit(int clothes)
	{
		String suitUp = "";

		switch(clothes)
		{
			case 0:
				suitUp = " Spades";
				break;
			case 1:
				suitUp = " Clubs";
				break;
			case 2:
				suitUp = " Hearts";
				break;
			case 3:
				suitUp = " Diamonds";
				break;
			default:
				suitUp = " What!";
				break;
		}

		return suitUp;

	} // End of method

	// Converts the values of the cards to their string meaning
	public String toStringVal()
	{
		String strVal = "";

		switch(value)
		{
			case 1:
				strVal = "ACE ";
				break;
			case 2:
				strVal = "Two ";
				break;

			case 3:
				strVal = "Three ";
				break;

			case 4:
				strVal = "Four ";
				break;

			case 5:
				strVal = "Five ";
				break;

			case 6:
				strVal = "Six ";
				break;

			case 7:
				strVal = "Seven ";
				break;

			case 8:
				strVal = "Eight ";
				break;

			case 9:
				strVal = "Nine ";
				break;

			case 10:
				strVal = "Ten ";
				break;

			case 11:
				strVal = "JACK ";
				break;

			case 12:
				strVal = "QUEEN ";
				break;

			case 13:
				strVal = "KING ";
				break;
			default:
				strVal = "What! ";
				break;

		} // End of switch case

		// Returns the full name of the card
		return strVal + "of" + toStringSuit(suit);

	} // End of method

} // End of class