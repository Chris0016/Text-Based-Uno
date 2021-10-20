import java.util.Arrays;
import java.util.ArrayList;
import java.util.Scanner;
public class UnoDeck{
	public static Scanner input = new Scanner(System.in);

	public static ArrayList<ArrayList<String>> cardSuits = new ArrayList<ArrayList<String>>(5);

	public static ArrayList<String> playerDeck = new ArrayList<String>();// use the Arrays.asList to transfer cards from cardSuits
	public  static  ArrayList<String> cpu1Deck = new ArrayList<String>();
	public static ArrayList<String> cpu2Deck = new ArrayList<String>();
	public static ArrayList<String> playedDeck =  new ArrayList<String>();
	//public static String lastCard =  playerID.get(playerID.size())

	public static String tC; 
	//Remove static property after completed 
	
	//cardSuits is also going to be the available deck

	public static byte count;
	public static byte rowValue;
	public static byte val;

	public static String playedCard;




	public UnoDeck(){
		cpu1Deck.add("Alex");//Give cpu a name value at the end of string 
		cpu2Deck.add("James");//REMINDER: IF ITERATING ALWAYS START AT 1.
        playerDeck.add("BPlus_2");
      playerDeck.add("YPlus_2");
      playerDeck.add("GPlus_2");
      playerDeck.add("RPlus_2");
        
		setCardSuit(); 
	
		//setDeck(playerDeck);
	    setDeck(cpu1Deck);
		setDeck(cpu2Deck);
		
		setPlayedDeck();
		setTC();
			
	}


	public static void plus_(ArrayList<String> playerID, int adding ){
         for (int i = 0; i < adding ; i++)
            playerID.add(randomCard());
	}

	public static String lastCard(ArrayList<String> playerID){
		return playerID.get(playerID.size()-1);
	}

	

	public static void cpuPlay(ArrayList<String> playerID){
		/*
		Rules: Multiple cards with same number can be placed and turn is over. Or 
		Only one card of the same color can be placed and turn is over.
		Aim: Iterate through deck. If any card from deck matches the number on the top card then it will be put down and turn is over.
		If no cards match the number then iterate through deck for at least one card matching color then break.
		If no cards with same color are found(considering that cards with the same number were looked for) then pick up code is run(while loop).

		This was accomplished by using a variable "count" to check if any cards were placed after the checks. If count was zeroo then the
		next check would be made.

		*/
		System.out.println(playerID.get(0) + " deck: "+ playerID);
		if (playerID.size() == 1 ){
				MyProgram.conTinue = false;

			if ((playerID.get(0)).equals("Alex")){
				
					System.out.println("Alex Wins!!");

				}
				else{
					
					System.out.println("James Wins!!");
				}
			
			
		}


		else {
		count = 0;
		//String playedCard;
		for(byte i = 1 ; i < playerID.size(); i++){
			playedCard = " ";
			if( ((playerID.get(i).substring(1)).equals(lastCard(playedDeck).substring(1))) ){
				playedCard =  playerID.get(i);
				// (playerID.get(i) represents the card that was just played.

				playedDeck.add(playerID.get(i));
			
				count++;
				setTC();
				System.out.println(playerID.get(0)+ " played: " + tC);
			    
			    if ((playerID.get(i).substring(1)).equals("Reverse")){
				    if(MyProgram.order == true)
				        {MyProgram.order = false;}
				else{
					MyProgram.order = true;
					}
					System.out.println("\n ORDER SWITCHED");
		        	}
	            playerID.remove(i);
			}//connects with overall if stament.
			
		
		}
		

			if (count == 0){
				for (byte i = 1; i < playerID.size(); i++){
					if( (playerID.get(i).substring(0,1)).equals(lastCard(playedDeck).substring(0,1))){
						playedCard =  playerID.get(i);
						playedDeck.add(playerID.get(i));

						playerID.remove(i);
						setTC();
						
						if ((playerID.get(i).substring(1)).equals("Reverse")){
				             if(MyProgram.order == true){
				                 MyProgram.order = false;}
			                	else{
				                	MyProgram.order = true;
					            } 
					           System.out.println("\n ORDER SWITCHED");
		                   	}
		                
		                  /* 
		                  For usual cards the number or the color has to match the card that is being played. 
		                  Only one that matches the color can be played or multiple that match the color can be played. 
		                  
		                  For special cards (plus2/4, reverse):
		                  For special cards, if the top card is not a special and it matches the color of the special 
		                  played then the a card with the same color that is special can be played and then multiple of the same type can be played after no matter the color. 
		                  */

						
						count++;
						System.out.println(playerID.get(0)+ " played " + tC);
						break;
					
				//Only one same color card can be played. Once the card is played nextTurn
						}
					}

				}
				
				
		
				if (count == 0 ){
					playerID.add(randomCard());
					System.out.println( playerID.get(0) + " Picked up.");

				 }
				 	System.out.println(playerID.get(0)+ " has "+ (playerID.size()-1) +" cards."+"\n");
				 }
			}

	public static void setTC(){tC = playedDeck.get(playedDeck.size()-1);}
	public static void printTC(){System.out.println("Top card: "+ tC+ "\n");}


	public static String randomCard(){
		String rCard;
		//rowValue = (byte)(Math.random()* (cardSuits.size()-1));
		while(true){
			rowValue = (byte)(Math.random()* (cardSuits.size()-1));
			if (cardSuits.get(rowValue).size() >  0){
				break;
			}
		}
			val = (byte)(Math.random()*(cardSuits.get(rowValue).size()));

			rCard = cardSuits.get(rowValue).get(val);
			cardSuits.get(rowValue).remove(val);

		
		
		return rCard;
	}

	
	public void setPlayedDeck(){
	
		while(true){
			rowValue = (byte)(Math.random()* (cardSuits.size()-2));
			if (cardSuits.get(rowValue).size() >  0){
				break;
			}
		}
			val = (byte)(Math.random()*(cardSuits.get(rowValue).size()-3));
		
		playedDeck.add(cardSuits.get(rowValue).get(val));
		cardSuits.get(rowValue).remove(val);
		
		/*
		Since the top card is equal to the last card of the played deck, this method initializes the playedDeck 
		which will define the variable tC in the static block.
		*/
	}
	
	public void setDeck(ArrayList<String> playerID){
		
		for (int i = 0 ; i < 7; i++){
			playerID.add( randomCard() );
		}
	}


	public void resetGame(){
			//Transfers cards from played deck to cardSuits
			for(int i =0 ; i < playedDeck.size(); i++){
				if((( playedDeck.get(i)).substring(0,1)).equals("B")){
					cardSuits.get(0).add(playedDeck.get(i));
				}else if((( playedDeck.get(i)).substring(0,1)).equals("G")){
					cardSuits.get(1).add(playedDeck.get(i));
				}
				else if(( playedDeck.get(i).substring(0,1)).equals("Y")){
					cardSuits.get(2).add(playedDeck.get(i));
				}else if(( playedDeck.get(i).substring(0,1)).equals("R")){
					cardSuits.get(3).add(playedDeck.get(i));
				}else {
					cardSuits.get(4).add(playedDeck.get(i));
					}
				}
				playedDeck.clear();
			}
			
	
	public void restartGame(){
		playedDeck.clear();
		cardSuits.clear();
		playerDeck.clear();
		cpu1Deck.clear();
		cpu2Deck.clear();
		//call the unoDeck class again
	}
	public static void setCardSuit(){
		for (int i = 0 ; i < 5; i++){
			cardSuits.add(new ArrayList());
		}
        /*
        This is being commented out so that the other
        cpu programs dont have 
        */
		cardSuits.get(0).add("B1");
		cardSuits.get(0).add("B2");
		cardSuits.get(0).add("B3");
		cardSuits.get(0).add("B4");
		cardSuits.get(0).add("B5");
		cardSuits.get(0).add("B6");
		cardSuits.get(0).add("B7");
		cardSuits.get(0).add("B8");
		cardSuits.get(0).add("B9");
		cardSuits.get(0).add("BReverse");
		cardSuits.get(0).add("BSkip");
	//	cardSuits.get(0).add("BPlus_2");

		cardSuits.get(1).add("G1");
		cardSuits.get(1).add("G2");
		cardSuits.get(1).add("G3");
		cardSuits.get(1).add("G4");
		cardSuits.get(1).add("G5");
		cardSuits.get(1).add("G6");
		cardSuits.get(1).add("G7");
		cardSuits.get(1).add("G8");
		cardSuits.get(1).add("G9");
		cardSuits.get(1).add("GReverse");
		cardSuits.get(1).add("GSkip");
	//	cardSuits.get(1).add("GPlus_2");

		cardSuits.get(2).add("Y1");
		cardSuits.get(2).add("Y2");
		cardSuits.get(2).add("Y3");
		cardSuits.get(2).add("Y4");
		cardSuits.get(2).add("Y5");
		cardSuits.get(2).add("Y6");
		cardSuits.get(2).add("Y7");
		cardSuits.get(2).add("Y8");
		cardSuits.get(2).add("Y9");
		cardSuits.get(2).add("YReverse");
		cardSuits.get(2).add("YSkip");
	//	cardSuits.get(2).add("YPlus_2");

		cardSuits.get(3).add("R1");
		cardSuits.get(3).add("R2");
		cardSuits.get(3).add("R3");
		cardSuits.get(3).add("R4");
		cardSuits.get(3).add("R5");
		cardSuits.get(3).add("R6");
		cardSuits.get(3).add("R7");
		cardSuits.get(3).add("R8");
		cardSuits.get(3).add("R9");
		cardSuits.get(3).add("RReverse"); 
		cardSuits.get(3).add("RSkip");
	//	cardSuits.get(3).add("RPlus_2");

		cardSuits.get(4).add("Plus_4");
		cardSuits.get(4).add("Plus_4");
		cardSuits.get(4).add("ColorSwitch");
		cardSuits.get(4).add("ColorSwitch");
		
		
		


	}
	
}
