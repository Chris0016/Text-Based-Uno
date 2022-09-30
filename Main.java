import java.util.ArrayList;

public class Main {
	static UnoDeck game;
	public static UserPlay player;
	public static boolean order = true;
	public static boolean conTinue = true;

	public static void main(String[] args) {

		ArrayList<ArrayList<String>> order1 = new ArrayList<>(1);

		order1.add(UnoDeck.cpu1Deck);
		order1.add(UnoDeck.cpu2Deck);
		order1.add(UnoDeck.playerDeck);

		player = new UserPlay();

		System.out.println(" Welcome to the Uno game");
		System.out.println(
				" Rules: \n1. Multiple cards that macth the number of the top card can be put down or One card matching the color of the top card can be put down.\n2. No pick and play\n3. If no cards on deck match the top card then: \n A Card will be drawn automatically\n Only one card is picked up(regardless if it matches or not) ");

		byte index = 0;
		while (conTinue == true) {
			// System.out.println("\n" + "Order value: " + order);

			if (order == true) {
				System.out.println("Top card:" + UnoDeck.lastCard(UnoDeck.playedDeck) + "\n");
				programFlow(order1.get(index));

			}

			else if (order == false) {

				System.out.println("Top card (reverse order): " + UnoDeck.lastCard(UnoDeck.playedDeck) + "\n");
				programFlow(order1.get(index));

			}
			// To check for the order value.

			if (order == true) {
				index++;

			} else {
				index--;

			}
			if (index >= 3) {
				index = 0;
			} else if (index < 0) {
				index = 2;
			}
		}

	}

	public static void programFlow(ArrayList<String> player1) {

		if (!((UnoDeck.tC.substring(1)).equals("Skip"))) {
			if ((UnoDeck.tC.substring(1)).equals("Plus_2") || (UnoDeck.tC.substring(0)).equals("Plus_4")) {
				game.plus_(player1, Integer.parseInt(UnoDeck.tC.substring(UnoDeck.tC.length() - 1)));
				UnoDeck.tC = "Plus_none";
				if ((player1.get(0)).equals("James") || (player1.get(0)).equals("Alex")) {

					System.out.println(player1.get(0) + " picked up " + UnoDeck.lastCard(UnoDeck.playedDeck)
							.substring((UnoDeck.lastCard(UnoDeck.playedDeck)).length() - 1) + " cards");
				}

				else {
					System.out.println("Picked up " + UnoDeck.lastCard(UnoDeck.playedDeck)
							.substring((UnoDeck.lastCard(UnoDeck.playedDeck)).length() - 1) + " cards.");
				}

			} else {
				if ((player1.get(0)).equals("James") || (player1.get(0)).equals("Alex")) {
					game.cpuPlay(player1);
					System.out.println();
				}

				else {
					player.play();
				}

			}
		}

		else {
			System.out.println(" PLAYER SKIPPPED ");
			UnoDeck.tC = "skip1";
		}

	}

}
