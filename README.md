# Spades the Game
I decided to create a console-based spades game that you play solo in Java. In order to play this game, there are a couple things you need to do. 

First of all, please download the Java 11 JDK if you do not already have it. Instructions for doing so can be found here: https://www.oracle.com/technetwork/java/javase/downloads/jdk11-downloads-5066655.html 

Once this has been done, you need to download the zip file and unzip the downloaded zip file. Then, open up your terminal and change the working directory to the Game folder. On a MacBook, simply run
```
  > cd downloads
  > cd spadesGame-master
  > cd Game
```

Compile the Java files by running
```
  > javac Spades.java Deck.java Player.java Solver.java
```

Finally, in order to begin the game, run 
```
  > java Spades
```

and this will begin the game! Please note: when asked for a name, put in only your first name and when asked for a number, put in only numbers. Thank you and enjoy the game :) 

If you don't know the rules of spades, please don't skip this part: spades is a trick taking game where each player will receive a hand of cards. Before any cards are played, each player looks at their hand and decides on how many 'tricks' they think they can win. However many hands the player believes they can win, they will bid that number of 'tricks'. For example, if I believe I can win 5 hands, I will bid 5.

After each player bids, a randomly-selected player will play a card of their choice. Players in clockwise fashion then choose to play cards of their own; they must play a card of the same suit if they can, otherwise they may play any card. Please note that players cannot lead with spades until a spade has already been played. After each player has played a card, the trick is won by whoever played the highest card of the led suit -- or whoever played the highest spade. Whoever won the trick will get to go first for the next round.

This continues until every player has played all of their cards. Each player then tallies up how many tricks they have won. If the number of tricks they have won is equivalent to the number of tricks they have bid, then they win 10 times their bid in points (i.e. if I bid 5 tricks and I win 5 tricks, I receive 50 points). If the number of tricks they have win is less than the number of tricks they have bid, then they lose 10 times their bid in points (i.e. if I bid 4 tricks and I win 2 tricks, I lose 40 points). Finally, if the number of tricks they won is greater than the number of tricks they have bid, then they win 10 times their bid plus the number of tricks they won minus the number of tricks they have bid (i.e. if I bid 4 tricks and I win 6 tricks, I will win 40 + 6 - 4 = 42 points... if I bid 3 tricks and I win 8 tricks, I will win 30 + 8 - 3 = 35 points). However, you will also receive sandbags for each trick you overbid by (i.e. so if I bid 4 tricks and I win 6 tricks, I will receive 42 points and 6 - 4 = 2 sandbags). Once you have accumulated 10 sandbags, you lose 100 points and you lose all your sandbags.

So when I was designing this game, I knew there were a couple things that were necessary.

First, I needed a class to represent a player. It needs to keep track of how many points they have accumulated, their current hand of cards, how many points they have bid for and how many sandbags they have. This is all done in Player.java.

Second, I needed a deck of cards. I decided to represent the cards with strings, represented as "X of Y" where X was a numerical value (so 2, 3, J, Q, K, A, etc) and Y represented the suits (so Spades, Diamonds, etc). This is represented by Deck.java. Please note that T is equivalent to 10 in this game!

Finally, I wanted the AI for the game to play intelligently. Hence, I created a class in Solver.java which evaluated a given hand of cards and told the computer which card to play. Most of the time, the computer just plays their best possible card but occasionally, the computer will play an arbitrary card (especially in the cases where the number of tricks they have won is equal to the number of tricks they have bid for).
