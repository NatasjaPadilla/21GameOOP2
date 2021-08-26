/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package package1;

/**
 *
 * @author nat
 */

import java.util.Scanner;
import java.util.Stack;

public class mainclass {
    
    public static Scanner in = new Scanner(System.in);
    public static String s = "";
    public static String card = "";
    public static String dcard = "";
    public static int cardValue = 0;
    public static int dcardValue = 0;
    public static String[] temp;
    public static int cardsum = 0;
    public static int dcardsum = 0;
    public static int player = 0;
    public static int dealer = 0;
    public static int tie = 0;
    public static int rounds = 0;
    
    public static void main(String[] args) {
		// TODO Auto-generated method stub
                String ans = "";
                boolean play = true;
                do{
                    System.out.println("do you want to play?(y/n): ");
                    ans = in.nextLine();
                    if(ans.equalsIgnoreCase("y")){
                        cardsum = 0;
                        dcardsum = 0;
		String[] typeCards = new String[]
				{"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};
		String[] classCards = new String[]
				{"S", "D", "C", "H"};
		
                
                String[] deck = new String[52];
		int count = 0;
		
		for(int i = 0; i < classCards.length; i++)
		{
			for(int j = 0; j < typeCards.length; j++)
			{
				deck[count] = classCards[i] + "-" + typeCards[j];
				count++;
			}
		}
		
		shuffle(deck);
		conditions();
                rounds += 1;
                }
                    else if(ans.equalsIgnoreCase("n")){
                        play = false;
                    System.out.println("rounds played: " + rounds);
                    System.out.println("win\tlose\tdraw");
                    System.out.println(player +"\t"+ dealer +"\t"+ tie);
                }
                }
                while(play);
                
                
                }
    
    public static int switchCase(){
        switch(card) { 
            case "A": 
                System.out.println("pick a value (1 or 11): "); 
                s = in.nextLine(); cardValue = Integer.parseInt(s); 
                break; 
            case "J": 
                cardValue = 10; 
                break; 
            case "Q": 
                cardValue = 10; 
                break; 
            case "K": 
                cardValue = 10; 
                break; 
        }
        return cardValue;
    }
    
    public static int dswitchCase(){
        switch(dcard) { 
            case "A": 
                if(dcardsum + 11 < 21){
                    dcardValue = 11;
                }
                else{
                    dcardValue = 1;
                }
                break; 
            case "J": 
                dcardValue = 10; 
                break; 
            case "Q": 
                dcardValue = 10; 
                break; 
            case "K": 
                dcardValue = 10; 
                break; 
        }
        return dcardValue;
    }
    
    public static void giveCard(Stack<String> theStack){
        for(int i = 0; i < 2; i++){
        System.out.println("your card: ");
		
		card = theStack.pop();
		System.out.println(card);
		
		System.out.println();
		
		if(card.length() == 4)
		{
			card = "10";
		}
		else
		{
			temp = card.split("");
			card = temp[2];
		}
		try
		{
			cardValue = Integer.parseInt(card);
		}
		catch(Exception e)
		{
			switchCase();
		}
                
                cardsum += cardValue;
        }
        
                System.out.println("your total: " + cardsum);
    }
    
    public static void dealerCard(Stack<String> theStack){
        for(int i = 0; i < 2; i++){
        System.out.println("dealer's card: ");
		
		dcard = theStack.pop();
		System.out.println(dcard);
		
		System.out.println();
		
		if(dcard.length() == 4)
		{
			dcard = "10";
		}
		else
		{
			temp = dcard.split("");
			dcard = temp[2];
		}
		try
		{
			dcardValue = Integer.parseInt(dcard);
		}
		catch(Exception e)
		{
			dswitchCase();
		}
                
                dcardsum += dcardValue;
        }
                //System.out.println("dealer's total: " + dcardsum);
    }
    
    public static void extraCard(Stack<String> theStack){
        boolean playerExtra = true;
        boolean dealerExtra = true;
        
        do{
        System.out.println("would you like to draw another card?(y/n): ");
        String ans = in.nextLine();
        
        if(ans.equalsIgnoreCase("y")){
            System.out.println("your card: ");
            card = theStack.pop();
            System.out.println(card);
            System.out.println();
		
		if(card.length() == 4)
		{
			card = "10";
		}
		else
		{
			temp = card.split("");
			card = temp[2];
		}
		try
		{
			cardValue = Integer.parseInt(card);
		}
		catch(Exception e)
		{
			switchCase();
		}
                
                cardsum += cardValue;
                System.out.println("your total: " + cardsum);
        }
        else{
            playerExtra = false;
        }
        
        if(dcardsum < 18){
            System.out.println("dealer's card: ");
            dcard = theStack.pop();
            System.out.println(dcard);
            System.out.println();
		
		if(dcard.length() == 4)
		{
			dcard = "10";
		}
		else
		{
			temp = dcard.split("");
			dcard = temp[2];
		}
		try
		{
			dcardValue = Integer.parseInt(dcard);
		}
		catch(Exception e)
		{
			dswitchCase();
		}
                
                dcardsum += dcardValue;
                //System.out.println("dealer's total: " + dcardsum);
        }
        else{
            dealerExtra = false;
        }
        }
        while(playerExtra || dealerExtra);
        
        if(!playerExtra && !dealerExtra){
            System.out.println("player's total: " + cardsum);
            System.out.println("dealer's total: " + dcardsum);
        }
    }
    
    public static void shuffle(String[] deck){
        Stack<String> theStack = new Stack<String>();
        for(int i = 0; i < deck.length; i++)
		{
			int j = (int)(Math.random() * deck.length);
			if(deck[j].equals(" "))
			{
				i--;
			}
			else
			{
				theStack.push(deck[j]);
				//System.out.print(deck[j] + " ");
				deck[j] = " ";
			}
		}
        giveCard(theStack);
        dealerCard(theStack);
        extraCard(theStack);
    }
    
    public static void conditions(){
        if(cardsum == 21 && dcardsum != 21){
            player += 1;
            System.out.println("player wins");
        }
        else if(cardsum != 21 && dcardsum == 21){
            dealer += 1;
            System.out.println("dealer wins");
        }
        else if(cardsum < 21 && dcardsum > 21){
            player += 1;
            System.out.println("player wins");
        }
        else if(cardsum > 21 && dcardsum < 21){
            dealer += 1;
            System.out.println("dealer wins");
        }
        else if(cardsum < 21 && dcardsum < 21){
            if(cardsum > dcardsum){
                player += 1;
                System.out.println("player wins");
            }
            else if(dcardsum > cardsum){
                dealer += 1;
                System.out.println("dealer wins");
            }
            else if(cardsum == dcardsum){
                tie += 1;
                System.out.println("draw");
            }
        }
        else if(cardsum > 21 && dcardsum > 21){
            tie += 1;
            System.out.println("draw");
        }
    }
}
