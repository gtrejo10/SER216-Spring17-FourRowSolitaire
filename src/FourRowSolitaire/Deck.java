package FourRowSolitaire;

import java.util.LinkedList;
import java.util.Random;

/**
 * Class: Deck
 *
 * Description: The Deck class holds all the Cards to form a 52 card deck.
 *
 */
public class Deck
{
    private int deckNumber;
    private LinkedList<Card> deck = new LinkedList<Card>();

    public Deck(int deckNumber)
    {
        this.deckNumber = deckNumber;
        shuffle();
    }

    public LinkedList<Card> getDeck()
    {
        return deck;
    }

    public LinkedList<Card> getDeck(LinkedList<Integer> numbers)
    {
        deck = new LinkedList<Card>();

        for(int i = 0; i < numbers.size(); i++)
        {
            if(numbers.get(i) > 0)
            {
                createCard(numbers.get(i));
            }
        }

        return deck;
    }

    public void shuffle()
    {
        LinkedList<Integer> numberList = new LinkedList<Integer>();

        for(int i = 1; i <= 52; i++)
        {
            numberList.add(i);
        }

        Random gen = new Random();

        for(int i = 0; i < 52; i++)
        {
            int num = gen.nextInt(numberList.size());

            int cardNumber = numberList.get(num);
            numberList.remove(num);

            createCard(cardNumber);
        }
    }

    private void createCard(int cardNumber)
    {
        if(cardNumber >= 1 && cardNumber <= 13)
        {
            deck.add(new Card(Card.SPADES_SUIT, cardNumber, deckNumber, cardNumber));
        }
        else if(cardNumber >= 14 && cardNumber <= 26)
        {
            cardNumber -= 13; // To make the cardNumber 1-13 instead of 14-26
            deck.add(new Card(Card.CLUBS_SUIT, cardNumber, deckNumber, cardNumber + 13));
        }
        else if(cardNumber >= 27 && cardNumber <= 39)
        {
            cardNumber -= 26; // To make the cardNumber 1-13 instead of 27-39
            deck.add(new Card(Card.DIAMONDS_SUIT, cardNumber, deckNumber, cardNumber + 26));
        }
        else if(cardNumber >= 40 && cardNumber <= 52)
        {
            cardNumber -= 39; // To make the cardNumber 1-13 instead of 40-52
            deck.add(new Card(Card.HEARTS_SUIT, cardNumber, deckNumber, cardNumber + 39));
        }
        else
        {
            //Let user know the card is invalid
            deck.add(new Card(Card.INVALID_SUIT, Card.INVALID_NUMBER, deckNumber, Card.INVALID_NUMBER));
        }
    }
}