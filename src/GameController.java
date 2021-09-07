import java.util.*;
import javax.swing.JOptionPane;

public class GameController {

    private static boolean hasPair = false, hasTriplet = false, hasFlush = false, hasStraight=false;
    private static int handRank1=-1, handRank2=-1, min, max;
    private static String winner="", type = "", value="", playAgain="";

    public static void main(String args[]) {
        CardDeck deck = new CardDeck();
        CheckHand checkHand = new CheckHand();


        do {
            //create 2 card hands
            String[] hand1 = deck.getCardHand();
            String[] hand2 = deck.getCardHand();

            //check card hands don't contain the same cards
            if(deck.isSameCard(hand1,hand2) == true){
                //if they do change card hand 2
                hand2 = deck.getCardHand();
            }

            //check hand rankings
            handRank1 = handRank(hand1);
            handRank2 = handRank(hand2);

            //compare rankings to find winning hand and winning cards
            if(handRank1==0 && handRank2==0) {
                winner = "No Winner";
            }else if(handRank1 > handRank2) {
                winner = "Hand 1";
                type = handType(hand1);
            }else if(handRank1 < handRank2) {
                winner = "Hand 2";
                type = handType(hand2);
            }else if(handRank1==1 && handRank2==1) {
                //if both contain pairs, compare each pair number value
                min = deck.returnIndex(checkHand.pairTriValue(hand1));
                max = deck.returnIndex(checkHand.pairTriValue(hand2));
                if(max < min) {
                    winner = "Hand 1";
                    type = handType(hand1);
                }else if(min < max){
                    winner = "Hand 2";
                    type = handType(hand2);
                }else {
                    winner = "Draw";
                    type = handType(hand2);
                }
            }

            //print out both card hands, the winner and the winning value
            if(!winner.equalsIgnoreCase("No Winner")) {
                value = type;
                if(type.equalsIgnoreCase("Flush")) {
                    if(winner.equals("Hand 1")) {
                        value = type + " (" + checkHand.flushSuit(hand1) + ")";
                    }else {
                        value = type + " (" + checkHand.flushSuit(hand2) + ")";
                    }
                }
                if(type.equalsIgnoreCase("Pair")) {
                    if(winner.equals("Hand 1")) {
                        value = type + " of " + checkHand.pairTriValue(hand1) + "'s";
                    }else if(winner.equals("Hand 2")){
                        value = type + " of " + checkHand.pairTriValue(hand2) +  "'s";
                    }else {
                        value = type + " of " + checkHand.pairTriValue(hand1) +  "'s";
                    }
                }

                JOptionPane.showMessageDialog(null, "HAND 1:   " + Arrays.toString(hand1).replace("[", "").replace("]","") + "\n\n"
                        + "HAND 2:   " + Arrays.toString(hand2).replace("[", "").replace("]","") + "\n\n\n"
                        + "WINNER:   " + winner + " - " + value);
            }else {
                JOptionPane.showMessageDialog(null, "HAND 1:   " + Arrays.toString(hand1).replace("[", "").replace("]","") + "\n\n"
                        + "HAND 2:   " + Arrays.toString(hand2).replace("[", "").replace("]","") + "\n\n\n"
                        + "WINNER:   " + winner);
            }

            //Ask user if they want to play again
            playAgain = JOptionPane.showInputDialog(null, "Do You Want to Play Again? (Y/N)");

        }while(playAgain.equalsIgnoreCase("Y"));


    }

    //card hand ranking -> returns highest ranking in hand
    private static int handRank(String[] hand) {
        CheckHand checkHand = new CheckHand();

        //True or False values
        hasPair = checkHand.isPair(hand);
        hasTriplet = checkHand.isTriplet(hand);
        hasFlush = checkHand.isFlush(hand);
        hasStraight = checkHand.isStraight(hand);

        if(hasFlush) {
            return 4;
        }else if(hasStraight){
            return 3;
        } else if(hasTriplet){
            return 2;
        }else if(hasPair) {
            return 1;
        } else {
            return 0;
        }
    }

    //card hand type depending on ranking -> returns card hand type
    private static String handType(String[] hand) {
        int n = handRank(hand);

        if(n == 4) {
            type= "Straight";
        }else if(n == 3)  {
            type= "Flush";
        }else if(n == 2) {
            type="Three of a Kind";
        }else if(n == 1) {
            type="Pair";
        } else {
            type= "No Matches";
        }

        return type;
    }
}