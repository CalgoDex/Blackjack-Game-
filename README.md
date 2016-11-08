# Blackjack-Game-
These program codes the game blackjack in java:

I approached this assignment from the micro level to the macro level
The card class was the easiest to implement and was straight forward.
The deck class simply created every unique instance of my card class and then
put them into an array. None of the methods in the deck class were especially
difficult to code, except maybe for the shuffle method, but for that all
I did was use the math class as we have in the past

The player class and dealer class were really similar and have a lot of the same
code. The biggest problem I initially had was with the discard method that I have
in both of them. Unfortunately I could not get them to work. I tried making them
return a new ArrayList<Card> but that just resulted in the cards not printing
anything. I even tried iterating over elements using the enhanced for loop but that
didn't work. As a result, after my code got past the first game, instead of
emptying out the player and dealer's hand, the code just keept them the same and
added another card, therefore the player constantly lost after the first round. 
Thankfully I was able to remedy this by countless hours of coding and rearranging
stuff.

My code has various check methods to ensure that the values inputed are valid
