//*****************************************************************************
//Calvin Goah
//cgg2126
//Game class   
//main through which Blackjack game is played
//*****************************************************************************

import java.util.Scanner;

public class Game
{
	public static void main(String[] args)
	{
		// Initializes the dealer and the deck
		Dealer dealer = new Dealer();
		Deck deck = new Deck();

		Scanner input = new Scanner(System.in);
		System.out.print("Welcome to your friendly Columbia casino! \n");
		System.out.print("Would you like to play Blackjack? (Enter Y or N)\n");
		String response = input.next();
		String upper = response.toUpperCase(); 

		/**
		*See's if player wants to play then initiates the game
		*/
		if (upper.equals("Y"))
		{
			System.out.print("At this table, buy-ins are $100 or more. Enter" +
				" buy-in value\n");
			/**
			* removes any dollar signs or the likes in the response 
			* and converts value to a double
			*/
			String buyIn = input.next();
			buyIn = buyIn.replaceAll("[^\\d.]", "");

			String tempStr;

			while (buyIn == null && buyIn.isEmpty())
			{
				System.out.print("Invalid figure entered.");
				System.out.print("Enter new amount.\n");
				tempStr = input.next();
				buyIn = tempStr.replaceAll("[^\\d.]", "");
			}

			double buyI = Double.parseDouble(buyIn);

			// Makes sure buy-in value in proper range
			while (buyI < 100)
			{
				System.out.print("Need more money to buy-in brah.");
				System.out.print("Enter new amount.\n");
				tempStr = input.next();
				buyIn = tempStr.replaceAll("[^\\d.]", "");

				while (buyIn == null && buyIn.isEmpty())
				{
					System.out.print("Invalid figure entered.");
					System.out.print("Enter new amount.\n");
					tempStr = input.next();
					buyIn = tempStr.replaceAll("[^\\d.]", "");
				}

				buyI = Double.parseDouble(buyIn);
			}

			//initizes the player
			Player player = new Player(buyI);
			
			/**
			* Player asked for a bet then the game will send bid to 
			* player class and the player start to make his or her move
			*/
			System.out.println("Enter a bet ($10-$1000)");
			String bet = input.next();
			bet = bet.replaceAll("[^\\d.]","");
			double betN = Double.parseDouble(bet);

			// Makes sure bet is in proper range
			while ((betN != 10 && betN < 10) || betN > 1000 ||
					betN > player.getFunds())
			{
				System.out.println("Invalid bet value");
				System.out.println("Enter a new bet ($10-$1000) " +
									"that is <= the amount of money" +
									" you currently have");
				tempStr = input.next();
				bet = tempStr.replaceAll("[^\\d.]","");

				while (bet == null && bet.isEmpty())
				{
					System.out.print("Invalid figure entered.");
					System.out.print("Enter new amount.\n");
					tempStr = input.next();
					bet = tempStr.replaceAll("[^\\d.]", "");
				}

				betN = Double.parseDouble(bet);
			}

			player.plBid(betN);

			// as long as current amount of money is more than bid
			boolean stableFinance = true;

			while (stableFinance)
			{
				player.discardHand();
				dealer.discardHand();
				// Now cards are dealt to each player
				player.giveCard(deck.dealCard());
				dealer.draw(deck.dealCard());
				player.giveCard(deck.dealCard());
				dealer.draw(deck.dealCard());
				System.out.println("Player's hand");
				player.showHand();
				dealer.showOne();

				// player has not busted
				boolean plBustedOStand = false;

				// dealer has not busted
				boolean dlBustedOStand = false;
				int num;

				// Boolean options to determine if still playing game
				while(!plBustedOStand || !dlBustedOStand)
				{

					// Player goes first
					if (!plBustedOStand)
					{
						// Sees if player wants another card
						System.out.println("Hit (H) or stand (S)?");
						String decision = input.next();
						String upD = decision.toUpperCase();

						if ( upD.equals("H"))
						{
							// assigns the negation of a boolean method
							// that returns a boolean if the sum of hand
							// is less than 21 to plBustedOStand
							plBustedOStand = !player.giveCard(deck.dealCard());
							System.out.println("Player's hand");
							player.showHand();
							dealer.showOne();

						} // End of if statement

						// Breaks out of loop if player stands
						if (upD.equals("S"))
						{
							plBustedOStand = true;

						} // End of else statement

						String carty;
						while (!upD.equals("H") && !upD.equals("S"))
						{
							System.out.print("Invalid player response");
							System.out.print("If Hit Enter H;" +
											 "If Stand Enter S?");
							carty = input.next();
							upD = carty.toUpperCase();
						}

					} // End of plBustedOStand if statement

					if (plBustedOStand == true)
					{
						if (player.sumHand() <= 21)
						{
							// Dealer goes next
							if (!dlBustedOStand)
							{
								String dealerRes = dealer.hitOrStand();

								// Deals card if dealer hits
								if (dealerRes.equals("H"))
								{
									dlBustedOStand = 
												!dealer.draw(deck.dealCard());

								} // End of if statement

								// Breaks out of loop if dealer stands
								if (dealerRes.equals("S"))
								{
									dlBustedOStand = true;
								}

							} // End of if statement

						} // End of if statement
						
					} // End of if statement

					if (plBustedOStand == true)
					{
						if (player.sumHand() > 21)
						{
							break;
						}
					}				

				} // End of while loop

				/**
				* Now we check for Blackjack in player or dealer hand.
				* Will also check for winning regardless of blackjack.
				*/

				if (player.sizeHand() == 2)
				{
					// Player has blackjack and dealer doesn't
					if (player.sumHand() == 21 && dealer.sumHand() != 21)
					{
						player.blackJack();
						System.out.println("Bull's Eye! You got Blackjack!");
						dealer.revealHand();
						System.out.println("Sum of dealer's cards: " +
									 	   dealer.sumHand());
						player.showHand();
						System.out.println("Sum of player cards: " +
											player.sumHand());
						System.out.println("Current budget " + 
											player.getFunds() + " dollars");

						// discard player and dealer hands in used pile
						// and also empties out the player hands
						deck.discard(player.handNow());
						deck.discard(dealer.getHand());
						player.discardHand();
						dealer.discardHand();


					} // End of if statement

					// Dealer has black jack and player doesn't
					else if ((player.sumHand() < 21 || player.sumHand() > 21)&&
						 	  dealer.sumHand() == 21)
					{
						player.plLoses();
						System.out.println("Dealer got Blackjack! You Lose");
						dealer.revealHand();
						System.out.println("Sum of dealer's cards: " +
									 	   dealer.sumHand());
						System.out.println("Player's hand");
						player.showHand();
						System.out.println("Sum of player cards: " +
											player.sumHand());
						System.out.println("Current budget " + 
											player.getFunds() + " dollars");
						deck.discard(player.handNow());
						deck.discard(dealer.getHand());
						player.discardHand();
						dealer.discardHand();
					}

					// Player and dealer both have black jack
					else if (player.sumHand() == dealer.sumHand() && 
						dealer.sumHand()==21)
					{
						player.push();
						System.out.println("You and dealer got blackjack!");
						dealer.revealHand();
						System.out.println("Sum of dealer's cards: " +
									 	   dealer.sumHand());
						System.out.println("Player's hand");
						player.showHand();
						System.out.println("Sum of player cards: " +
											player.sumHand());
						System.out.println("Current budget " + 
											player.getFunds() + " dollars");
						deck.discard(player.handNow());
						deck.discard(dealer.getHand());
						player.discardHand();
						dealer.discardHand();

					} // End of else

					else if (player.sumHand() < dealer.sumHand() &&
							 dealer.sumHand() <= 21)
					{
						player.plLoses();
						System.out.println("You Lose to dealer");
						dealer.revealHand();
						System.out.println("Sum of dealer's cards: " +
									 	   dealer.sumHand());
						System.out.println("Player's hand");
						player.showHand();
						System.out.println("Sum of player cards: " +
											player.sumHand());
						System.out.println("Current budget " + 
											player.getFunds() + " dollars");
						deck.discard(player.handNow());
						deck.discard(dealer.getHand());
						player.discardHand();
						dealer.discardHand();

					} // End of if statement

					else if (player.sumHand() > dealer.sumHand() &&
							player.sumHand() <= 21)
					{
						player.playerWinReg();
						System.out.println("You beat the dealer!");
						dealer.revealHand();
						System.out.println("Sum of dealer's cards: " +
									 	   dealer.sumHand());
						System.out.println("Player's hand");
						player.showHand();
						System.out.println("Sum of player cards: " +
											player.sumHand());
						System.out.println("Current budget " + 
											player.getFunds() + " dollars");
						deck.discard(player.handNow());
						deck.discard(dealer.getHand());
						player.discardHand();
						dealer.discardHand();
					}

					else if ((player.sumHand()==dealer.sumHand())&&
						player.sumHand()<21)
					{
						player.push();
						System.out.println("You tied the dealer!");
						dealer.revealHand();
						System.out.println("Sum of dealer's cards: " +
									 	   dealer.sumHand());
						System.out.println("Player's hand");
						player.showHand();
						System.out.println("Sum of player cards: " +
											player.sumHand());
						System.out.println("Current budget " + 
											player.getFunds() + " dollars");
						deck.discard(player.handNow());
						deck.discard(dealer.getHand());
						player.discardHand();
						dealer.discardHand();
					}

				} // End of if statement

				if (player.sizeHand() > 2)
				{
					if (player.sumHand() <= 21)
					{
						if (player.sumHand() > dealer.sumHand())
						{
							player.playerWinReg();
							System.out.println("You win!");
							dealer.revealHand();
							System.out.println("Sum of dealer's cards: " +
										 	   dealer.sumHand());
							System.out.println("Player's hand");
							player.showHand();
							System.out.println("Sum of player cards: " +
												player.sumHand());
							System.out.println("Current budget " + 
											player.getFunds() + " dollars");
							deck.discard(player.handNow());
							deck.discard(dealer.getHand());
							player.discardHand();
							dealer.discardHand();
							

						} // End of if statement

						else if (player.sumHand() < dealer.sumHand() &&
							dealer.sumHand() <= 21)
						{
							player.plLoses();
							System.out.println("Dealer wins!");
							dealer.revealHand();
							System.out.println("Sum of dealer's cards: " +
										 	   dealer.sumHand());
							System.out.println("Player's hand");
							player.showHand();
							System.out.println("Sum of player cards: " +
												player.sumHand());
							System.out.println("Current budget " + 
											player.getFunds() + " dollars");
							deck.discard(player.handNow());
							deck.discard(dealer.getHand());
							player.discardHand();
							dealer.discardHand();
							

						} // End of if statement

						else if (player.sumHand() == dealer.sumHand())
						{
							player.push();
							System.out.println("Tie!");
							dealer.revealHand();
							System.out.println("Sum of dealer's cards: " +
										 	   dealer.sumHand());
							System.out.println("Player's hand");
							player.showHand();
							System.out.println("Sum of player cards: " +
												player.sumHand());
							System.out.println("Current budget " + 
											player.getFunds() + " dollars");
							deck.discard(player.handNow());
							deck.discard(dealer.getHand());
							player.discardHand();
							dealer.discardHand();
							
						}

						else if (dealer.sumHand() > 21)
						{
							System.out.println("Dealer Busted!\nYou Win!");
							player.playerWinReg();
							dealer.revealHand();
							System.out.println("Sum of dealer's cards: " +
										 	   dealer.sumHand());
							System.out.println("Player's hand");
							player.showHand();
							System.out.println("Sum of player cards: " +
												player.sumHand());
							System.out.println("Current budget " + 
											player.getFunds() + " dollars");
							deck.discard(player.handNow());
							deck.discard(dealer.getHand());
							player.discardHand();
							dealer.discardHand();
							
						}

					} // End of if statement

					else
					{
						if(dealer.sumHand() <= 21 && player.sumHand() > 21)
						{
							System.out.println("You Busted!");
							player.plLoses();
							dealer.revealHand();
							System.out.println("Sum of dealer's cards: " +
										 	   dealer.sumHand());
							System.out.println("Player's hand");
							player.showHand();
							System.out.println("Sum of player cards: " +
												player.sumHand());
							System.out.println("Current budget " + 
											player.getFunds() + " dollars");
							deck.discard(player.handNow());
							deck.discard(dealer.getHand());
							player.discardHand();
							dealer.discardHand();
							

						}

						// If both dealer and player bust
						else if (player.sumHand() > 21 && dealer.sumHand() > 21)
						{
							System.out.println("The house wins in a bust" +
												" of both players!");
							player.plLoses();
							dealer.revealHand();
							System.out.println("Sum of dealer's cards: " +
										 	   dealer.sumHand());
							System.out.println("Player's hand");
							player.showHand();
							System.out.println("Sum of player cards: " +
												player.sumHand());
							System.out.println("Current budget " + 
											player.getFunds() + " dollars");
							deck.discard(player.handNow());
							deck.discard(dealer.getHand());
							player.discardHand();
							dealer.discardHand();
							
						}

					}

				} // End of if

				// This block of code initiates the looping of the
				// game as long as the player continues to play
				System.out.println("Keep Playing? (Y/N)");
				String stance = input.next();
				String stn = stance.toUpperCase();
				if (stn.equals("Y"))
				{
					player.discardHand();
					dealer.discardHand();
					System.out.println("Enter bid amount");
					String newBet = input.next();
					newBet = newBet.replaceAll("[^\\d.]","");

					// ensures that numbers are entered
					while (newBet == null && newBet.isEmpty())
					{
						System.out.println("Enter bid amount");
						newBet = input.next();
						newBet = newBet.replaceAll("[^\\d.]","");
					} // End of while

					double newB = 0.0;

					if (newBet != null && !newBet.isEmpty())
					{
						newB = Double.parseDouble(newBet);
					}

					// Ensures number is between values
					while (newB < 10 || newB >1000 || newB > player.getFunds())
					{
						System.out.println("Invalid input");
						System.out.println("Enter financially sound amount");
						newBet = input.next();
						newBet = newBet.replaceAll("[^\\d.]","");

						while (newBet == null && newBet.isEmpty())
						{
							System.out.println("Enter plausible bid amount");
							newBet = input.next();
							newBet = newBet.replaceAll("[^\\d.]","");
						} // End of while

						newB = Double.parseDouble(newBet);
					} // End of while

					if (player.getFunds() >= newB)
					{
						player.plBid(newB);
						stableFinance = true;
						

					} // End of if statement

					if (player.getFunds() < newB)
					{
						System.out.println("Not enough Funds!");
						stableFinance = false;

					} // End of else statement
					
				} // End of if statement

				// if player doen't want to play the game again
				// we report the total funds of the player and
				// exit the loop
				else
				{
					System.out.printf("Money you leave with " + 
									    player.getFunds() + "0 dollars");
					System.out.println("");
					System.out.println("Bye!");
					stableFinance = false;
				}

			} // End of stablefinance if statement

			if (player.getFunds() < 10)
			{
				System.out.println("Looks like you are out of money son");

			} // End of if statement
		
		} // End of outer most if statement

		// If player doesn't want to play the game from the very beginning,
		// we exit the program.
		else 
		{
			System.out.println("Alright, maybe next time.");
			System.exit(0);
		}

		input.close();

	} // End of main

} // End of class

	