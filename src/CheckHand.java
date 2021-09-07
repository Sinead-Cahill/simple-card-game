import java.util.Arrays;

public class CheckHand {

    private boolean hasPair, hasTriplet, hasFlush, hasStraight;
    private int matches, index;
    private String value, temp1, temp2, suit1, suit2;
    private int[] newOrder;

    public CheckHand() {
        //initialising variables
        value = "";
        temp1 = "";
        temp2 = "";
        suit1 = "";
        suit2 = "";
        index = -1;
    }

    private void checkCards(String[] cardHand, String type) {

        //Go through Hand and Count Matches

        //if checking for pair or three of a kind
        if(type.equals("Pair/Tri")) {
            for(int i=0; i<cardHand.length; i++) {
                temp1 = cardHand[i];
                for(int j=0; j<cardHand.length; j++) {
                    temp2 = cardHand[j];

                    //if comparing the same card in the list together, dont compare them
                    if(j == i) {
                        continue;
                    }

                    //if the value at the start of the card (everything before the first space) matches another, add one to the variable matches
                    if(temp1.startsWith(temp2.substring(0, temp2.indexOf(" ")))) {
                        matches++;
                        //save the card value that matches
                        value = temp1.substring(0, temp1.indexOf(" "));
                    }
                }
            }
        }

        //if checking for a flush
        if(type.equals("Flush")) {
            for(int i=0; i<cardHand.length; i++) {
                temp1 = cardHand[i];
                //get the suit of card to compare with the rest
                suit1 = temp1.substring(temp1.lastIndexOf(" ")+1);

                for(int j=0; j<cardHand.length; j++) {
                    temp2 = cardHand[j];
                    suit2 = temp2.substring(temp2.lastIndexOf(" ")+1);

                    //if comparing the same card in the list together, dont compare them
                    if(j == i) {
                        continue;
                    }

                    //if the two suits being compared match, add one to variable matches
                    if(suit1.equals(suit2)){
                        matches++;
                    }
                }
            }
        }

        //if checking for a straight
        if(type.equals("Straight")) {
            //sort the card hand in numerical order
            sortHand(cardHand);

            for(int i=0; i<newOrder.length-1; i++) {
                //if the card on the left -1 equals the card to the right, add one to the variable matches
                if(newOrder[i+1]-1 ==newOrder[i]) {
                    matches++;;
                }
            }
        }
    }

    //check if the card hand has a pair
    public boolean isPair (String[] cardHand) {
        matches = 0;
        hasPair = false;
        //Go through Hand and Count Matches
        checkCards(cardHand, "Pair/Tri");

        if(matches != 0) {
            //if the number of matches %2 = 0 then it has a pair
            if(matches%2==0) {
                hasPair=true;
            }
        }

        return hasPair;
    }

    //check if the card hand has three of a kind
    public boolean isTriplet (String[] cardHand) {
        matches = 0;
        hasTriplet = false;
        //Go through Hand and Count Matches
        checkCards(cardHand, "Pair/Tri");

        if(matches != 0) {
            //if the number of matches %3 = 0 then it has a pair
            if(matches%3==0) {
                hasPair=true;
            }
        }

        return hasTriplet;
    }

    //if the hand has a pair or three of a kind, return the matching card value
    public String pairTriValue(String[] cardHand) {
        checkCards(cardHand, "Pair/Tri");
        return value;
    }

    //check if the card hand is a flush (five of the same suit)
    public boolean isFlush (String[] cardHand) {
        matches = 0;
        hasFlush = false;

        //go through hand and count matches
        checkCards(cardHand, "Flush");

        if(matches != 0) {
            //if the number of matches %5 = 0 then it is a flush
            if(matches%5==0) {
                hasFlush=true;
            }
        }

        return hasFlush;
    }

    //if the hand is a flush, return the suit
    public String flushSuit(String[] cardHand) {
        checkCards(cardHand, "Flush");
        return suit1;
    }

    //check if the card hand is a straight
    public boolean isStraight(String[] cardHand) {
        matches=0;
        hasStraight=false;

        //go through hand and count matches
        checkCards(cardHand, "Straight");

        //if the matches = 4, then it is a straight
        if(matches==4) {
            hasStraight=true;
        }
        return hasStraight;
    }

    //Sort the card hand by value (1-13) -> return Int array
    private void sortHand(String[] cardHand) {
        newOrder = new int[cardHand.length];

        for(int i=0; i<cardHand.length; i++) {
            temp1 = cardHand[i];
            //Take the value of the card (string before the first space)
            value = temp1.substring(0, temp1.indexOf(" "));

            for(int j=0; j<newOrder.length; j++) {

                //if the value is ace, king, queen or jack, set its index
                if(value.equals("King")) {
                    index = 13;
                }else if(value.equals("Queen")) {
                    index = 12;
                }else if(value.equals("Jack")) {
                    index = 11;
                }else if(value.equals("Ace")) {
                    index = 1;
                }else {
                    //convert the string value to integer
                    index = Integer.parseInt(value);
                }

                //add value index to new array
                newOrder[j] = index-1;
            }
        }
        //Sort by index number
        Arrays.sort(newOrder);
    }

}
