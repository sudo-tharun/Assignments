import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

//Player class
class BlackJackPlayer{
	static Random random = new Random();
	
	static private Scanner sc=new Scanner(System.in);
	
	static int bet=0,money=100;
	
	int hidden=0; //to keep track of 
	
	static int draw_player=0; //cards drawn by player during hit
	static int draw_dealer=0; //card drawn by dealer during hit
	
	//function to print the cards of the dealer
	private void printDealer(int [] cards) {
		
		//Considered random choice to show one of the cards
		int show=random.nextInt(2);
		System.out.println("\r\nOne of the dealer's choice is: "+cards[show]);
		if(show==1)
			hidden=cards[0];
		else
			hidden=cards[1];
	}
	
	//hit by the dealer
	private int dealerChoice() {
			System.out.println("\r\nDealer has chosen to hit!");
			draw_dealer=random.nextInt(12 - 2)+2; //card value is randomly generated
			System.out.println("Dealer draws "+draw_dealer);
			return draw_dealer;
	}
	
	//hit or stay by the player
	private int playerChoice() {
		int stay=0;
		System.out.println("\r\nWould you like to hit or stay? ");
		
		String ch=sc.next();
		if(ch.equals("stay") && stay==0) {
			System.out.println("\r\nYou have chosen to stay! \nDealer's turn!");
			System.out.println("His hidden card is "+hidden);/////
			stay++;
		}
		else if(ch.equals("stay")) {
			System.out.println("\r\nYou have chosen to stay! Dealer's turn!");
		}
		else if(ch.equals("hit")) {
			draw_player=random.nextInt(12 - 2)+2; //hard code
			System.out.println("You have drawn "+draw_player);
			return draw_player;
		}
		return 0;//check
	}
	
	/*
	 * If the player wins he gets an amount 1.5 times his bet money.
	 * If the player loses, he loses the bet money.
	 */
	//function to create/set bet
	private void betting() {
		System.out.println("\r\nYou have $"+money+" in your pocket!");
		System.out.println("How much bet would you like to place?");
		bet=sc.nextInt();
		if(bet>money || bet<1) {
			System.out.println("\r\nInvalid bet! Place your bet between $"+1+" to $"+money+".");
			System.out.println("Try again!");
			betting();
		}
	}
	
	//function to generate random values from 2-11
	private int[] randomGenerator(int min, int max) {
        int [] values=new int[2];
        values[0]=random.nextInt(max-min)+min;
        while(true) {
        	int k=random.nextInt(max-min)+min;
        	if(k!=values[0]) {
        		values[1]=k;
        		break;
        	}
        }
        return values;
	}
	
	//Main function to play the Blackjack Game
	public void playGame() {
		
		System.out.println("Welcome to the BlackJack!");
		betting();
		//to store initial values of the draw of player and dealer
		int[] player_card_values=randomGenerator(2, 12);
		int [] dealer_card_values=randomGenerator(2, 12);
		
		//Calculating sum of the drawn values
		int player_sum=Arrays.stream(player_card_values).sum();
		int dealer_sum=Arrays.stream(dealer_card_values).sum();
		
		System.out.println("\r\nYou have chosen: "+player_card_values[0] +" "+player_card_values[1]);
		System.out.println("Your total is: "+player_sum);
		
		
		if(player_sum==21) {
			money+=(bet*1.5);
			System.out.println("\r\nYou win!");
			System.out.println("You leave with $"+money+".");
			System.out.println("----------");
			return;
		}
		//printing dealer's card values
		printDealer(dealer_card_values);
		System.out.println("Dealer's sum is hidden!");
		
		//taking player's choice
		int choice_player=playerChoice();
		while(true) {
			
			while(choice_player!=0) {
				player_sum+=draw_player;
				System.out.println("Your sum is: "+player_sum);
				
				//if player's sum is >21 at any point, dealer wins.
				if(player_sum>21) {
					money-=bet;
					System.out.println("\r\nYou have busted! Dealer Wins!!");
					System.out.println("You leave with $"+money+".");
					System.out.println("----------");
					return;
				}
				if(player_sum==21) {
					money+=(bet*1.5);
					System.out.println("\r\nYou win!");
					System.out.println("You leave with $"+money+".");
					System.out.println("----------");
					return;
				}
				choice_player=playerChoice();
			}
			break;
		}
		
		System.out.println("\r\nDealer's sum is: "+dealer_sum);
		if(dealer_sum==21) {
			money-=bet;
			System.out.println("\r\nDealer win!!");
			System.out.println("You leave with $"+money+".");
			System.out.println("----------");
			return;
		}
		//taking dealer's choice
		
		//Dealer has any number of hits until his sum is less than 16
		while(dealer_sum<=16) {
				int choice_dealer=dealerChoice();
				dealer_sum+=choice_dealer;
				System.out.println("Dealer's sum is: "+dealer_sum);
				if(dealer_sum==21) {
					money-=bet;
					System.out.println("\r\nDealer win!!");
					System.out.println("You leave with $"+money+".");
					System.out.println("----------");
					return;
				}
				//If the dealer's sum is greater than 21, player wins.
				if(dealer_sum>21) {
					money+=(bet*1.5);
					System.out.println("\r\nDealer busted! You win!!");
					System.out.println("You leave with $"+money+".");
					System.out.println("----------");
					return;
				}

			}
			 System.out.println("Dealer stays!");
		
		
		if(player_sum>dealer_sum) {
			money+=(bet*1.5);
			System.out.println("You win!");
			System.out.println("You leave with $"+money+".");
			System.out.println("----------");
		}
		//If the dealer's sum is greater than or equal (tie) to player's sum
		//dealer wins.
		else {
			money-=bet;
			System.out.println("Dealer win!");
			System.out.println("You leave with $"+money+".");
			System.out.println("----------");
		}
	}
}

//Driver class
public class BlackJack{
	static BlackJackPlayer obj=new BlackJackPlayer();
	static Scanner t=new Scanner(System.in);

	//Choice to play again!
	private static int playChoice() {
		System.out.println("Would you like to play the game again?");
		System.out.println("Yes (Y/y) or No (N/n)?");
		String playChoice=t.nextLine().toLowerCase();
		if(playChoice.equals("y"))
			return 1;
		return 0;
	}
	public static void main(String [] args) {
		obj.playGame();
		int ch=playChoice();
		if(ch==1) 
			obj.playGame();
		else if(ch==0)
			System.out.println("You have chosen to quit! Thanks for playing!");
		else {
			System.out.println("Invalid Choice!");
			ch=playChoice();
		}
	}
}

