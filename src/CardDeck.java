import java.util.*;

public class CardDeck {
    private String[] cardSuit, cardValue, deck, cardHand;
    private int numberOfCards;
    private Random randnum;
    private boolean isSameCard;

    public CardDeck() {
        //Initialising Variables
        cardSuit = new String[]{"Hearts", "Diamonds", "Clubs", "Spades"};
        cardValue = new String[]{"Ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King"};
        numberOfCards = cardSuit.length * cardValue.length; //52
        //create a deck of size 52
        deck = new String[numberOfCards];

        randnum = new Random();

        //Create Full Deck using Suit and Value Arrays
        for(int i=0; i < cardSuit.length; i++) {
            for(int j=0; j< cardValue.length; j++) {
                deck[cardValue.length*i + j]=cardValue[j] + " Of " + cardSuit[i];
            }
        }
        //Shuffle Deck
        for(int i=0; i<deck.length; i++){
            //pick a random index number between 0-51
            int randIndex = randnum.nextInt(numberOfCards);
            String temp = deck[randIndex];
            //Swapping over the values of two indexes at a time
            deck[randIndex] = deck[i];
            deck[i] = temp;
        }
        //System.out.println("Starting Deck: " + Arrays.toString(deck));
    }

    public String[] getCardHand() {
        cardHand = new String[5];
        String[] temp;
        //Cards will always be taken from the array from the top (last in array)
        //Add top card from deck and placing it into the card hand, remove it from deck and repeat 4 more times (length of hand array)
        for(int i=0; i<cardHand.length; i++) {
            cardHand[i] = deck[deck.length-1];
            //Remove card from deck array
            removeFromDeck();
        }

        //System.out.println("Updated Deck: " + Arrays.toString(deck));
        //System.out.println(Arrays.toString(cardHand));

        return cardHand;
    }

    private void removeFromDeck(){
        //Temp array created that is size 1 less than deck array (will be removing 1 card)
        String[] temp = new String[deck.length-1];

        //Removing last card -> add all cards from deck array to temp array except for the last one
        for (int i = 0; i < temp.length; i++) {
            temp[i] = deck[i];
        }

        //Updating the deck array with available cards left
        deck = temp;

    }

    public String[] changeCard(String[] cardHand, int index){
        //remove card at index from hand -> index given is user numbers 1 to 5 so need to -1
        cardHand[index-1] = deck[deck.length-1];
        removeFromDeck();

        System.out.println("New Hand: " + Arrays.toString(cardHand));

        return cardHand;
    }


//Last 2 methods are used in Week 7 exercise so cannot be removed

    //return the index of where the card is depending on its order in the cardValue list (Ace - King)
    public int returnIndex (String value) {
        for(int i=0; i<cardValue.length; i++) {
            //if the value is found in the list, return its index
            if(cardValue[i].compareTo(value)==0) {
                return i;
            }
        }

        return -1;
    }

    //Not required anymore as getCardHand has been updated so that all cards are taken from the same deck but this method is used in week 7 exercise project so cannot be removed
    //Check both card hands do not contain the same cards
    public boolean isSameCard (String[] hand1, String[] hand2){
        isSameCard = false;

        for(int i=0; i<hand1.length; i++){
            for(int j=0; j<hand2.length; j++){
                //if the card at hand 1 equals the card at hand 2, isSameCard = true
                if(hand1[i].equals(hand2[j])){
                    isSameCard = true;
                }
            }
        }

        return isSameCard;
    }
}
