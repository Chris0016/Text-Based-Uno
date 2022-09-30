import java.util.Arrays;
import java.util.ArrayList;

public class UserPlay extends UnoDeck {

	static String putDownCrds;
	static ArrayList<String> arrCards;
	static byte counter = 0;
	static int oLength;
	// ArrayList for user deck is: playerDeck.

	public static void play() {

		System.out.println(" Your turn \n");
		printCards(playerDeck);
		System.out.println();

		System.out.println(" Draw card(0) or Put Down Card(1): ");
		Byte userAction = input.nextByte();
		input.nextLine();// clears buffer.
		if (userAction == (byte) 0) {
			// Perform Draw card method
			drawCard();
		} else if (userAction == (byte) 1) {
			// Ask for cards to put in then perform putDown action.
			System.out.print("Enter cards to putDown: ");

			putDownCrds = input.nextLine();
			arrCards = new ArrayList<>(Arrays.asList(putDownCrds.split(" ")));
			oLength = arrCards.size();

			putDown(arrCards);
		}
		if (playedDeck.size() == 0) {
			System.out.print("YOU WIN!!!");
			Main.conTinue = false;
		}

	}

	public static void printCards(ArrayList<String> playerID) {
		System.out.println(" Your cards: ");
		System.out.print("0: " + playerID.get(0));
		for (int i = 1; i < playerID.size(); i++) {

			System.out.print(" , " + i + ":" + playerID.get(i));
		}

		System.out.println("\nTotal: " + playerID.size());
	}

	public static void putDown(ArrayList<String> cardNum)
	// In constructor ask for card to play then play card.
	// User can enter multiple numbers at once to indicate cards. Check needed for
	// valid cards.
	{

		/*
		 * Because the arrrayList shifts down after an element is removed and the size
		 * decreases.
		 * If we iterate through an arraylist with multiple elements ,then as we remove
		 * elements and compare it with the
		 * incrementing value of i then the code will not run and there will be elements
		 * that weren't iterated through
		 */

		byte counter = 0;

		for (int i = 0; i < oLength; i++) {
			// iterates to check for same number first. Since multiple cards with same
			// number can be placed.

			playedCard = " ";// resets the value of the played card to a empty string. Otherwise the value
								// assigned to played card previosuly will be evaluated.
			System.out.println("Check before if statement");
			if ((playerDeck.get(Integer.parseInt(cardNum.get(i)) - counter).substring(1))
					.equals(lastCard(playedDeck).substring(1))) {

				playedCard = playerDeck.get(Integer.parseInt(cardNum.get(i)) - counter);
				playedDeck.add(playedCard);

				System.out.println("Played: " + playedCard);

				// playerDeck.get( Integer.parseInt(cardNum.get(i))-counter )+"\n"

				if ((playedCard.substring(1)).equals("Reverse")) {
					System.out.println(" ORDER REVERSED");
					if (Main.order == true) {
						Main.order = false;
					} else {
						Main.order = true;
					}

				}

				setTC();
				playerDeck.remove(Integer.parseInt(cardNum.get(i)) - counter);
				cardNum.remove(i);
				counter = 1;

				// The remove method moves every element after the removed one index down which
				// means that the index value of a card is always changing after
				// removing an element. To accounter for this we substract 1 from the given
				// value after an element is removed.
				/*
				 * The variable counter is responsible for acting as a check as described in the
				 * note above(number of cards played). Instead of creating another variable
				 * to substract 1 from the input after one value is removed, I used the same
				 * variable counter.
				 * 
				 * A value of one is needed to be substracted so counter is always brung down to
				 * 1 after increasing past 1.
				 * Further, since the next check, will only run if at least 1 card is
				 * played(counter == 0 ) setting counter 1 will not interfere with the rest of
				 * the code.
				 */
			}
		}
		// If no cards with the same number were found(matching numbers are put down)
		// then the first card of the selected(cardNum) will be put down.
		if (counter == 0) {
			for (int i = 0; i < oLength; i++) {

				if ((playerDeck.get(Integer.valueOf(cardNum.get(i))).substring(0, 1))
						.equals(lastCard(playedDeck).substring(0, 1))) {
					playedDeck.add(playerDeck.get(Integer.valueOf(cardNum.get(i))));

					System.out.println("Played: " + playerDeck.get(Integer.valueOf(cardNum.get(i))) + "\n");

					playedCard = playerDeck.get(Integer.parseInt(cardNum.get(i)) - counter);
					if ((playedCard.substring(1)).equals("Reverse")) {
						System.out.println(" ORDER REVERSED");
						if (Main.order == true) {
							Main.order = false;
						} else {
							Main.order = true;
						}

					}

					playerDeck.remove(Integer.parseInt(cardNum.get(i)));

					setTC();

					cardNum.remove(i);

					counter++;
					break;
				}

			}

		}
		// Prints out the cards that weren't able to be played from the selected.
		if (cardNum.size() >= 1) {
			for (byte i = 0; i < cardNum.size(); i++) {
				System.out.print("Cannot play: " + playerDeck.get(Integer.valueOf(cardNum.get(i)) - counter) + "\n");
			}
		}
		// If no cards from the selected were valid to play then a card will be drawn
		// automatically.
		else if (counter == 0) {
			System.out.println("You were drawn a card");
			drawCard();
		}

	}

	public static void drawCard() {

		playerDeck.add(randomCard());
		System.out.println("Card drawn: " + lastCard(playerDeck));

		// if (lastCard(playerDeck).substring(0,1) == tC.substring(0,1))||
		// (lastCard(playerDeck).substring(1) == tC.substring(1)){} for pick and play.
		// Not part of the playboard.

	}

	public static void checkSameValue() {

		for (int i = 0; i < oLength; i++) {
			// iterates to check for same number first. Since multiple cards with same
			// number can be placed.

			playedCard = " ";

			if ((playerDeck.get(Integer.parseInt(arrCards.get(i)) - counter).substring(1))
					.equals(lastCard(playedDeck).substring(1))) {

				playedCard = playerDeck.get(Integer.parseInt(arrCards.get(i)) - counter);
				playedDeck.add(playedCard);

				System.out.println("Played: " + playedCard);

				if ((playedCard.substring(1)).equals("Reverse")) {
					System.out.println(" ORDER REVERSED");
					if (Main.order == true) {
						Main.order = false;
					} else {
						Main.order = true;
					}
				}

				setTC();
				playerDeck.remove(Integer.parseInt(arrCards.get(i)) - counter);
				arrCards.remove(i);
				counter = 1;

			}
		}
	}

	public static void checkSameClr() {
		for (int i = 0; i < oLength; i++) {

			if ((playerDeck.get(Integer.valueOf(arrCards.get(i))).substring(0, 1))
					.equals(lastCard(playedDeck).substring(0, 1))) {
				playedDeck.add(playerDeck.get(Integer.valueOf(arrCards.get(i))));

				System.out.println("Played: " + playerDeck.get(Integer.valueOf(arrCards.get(i))) + "\n");

				playedCard = playerDeck.get(Integer.parseInt(arrCards.get(i)) - counter);
				if ((playedCard.substring(1)).equals("Reverse")) {
					System.out.println(" ORDER REVERSED");
					if (Main.order == true) {
						Main.order = false;
					} else {
						Main.order = true;
					}

				}

				playerDeck.remove(Integer.parseInt(arrCards.get(i)));

				setTC();

				arrCards.remove(i);

				counter++;
				break;
			}

		}

	}

}