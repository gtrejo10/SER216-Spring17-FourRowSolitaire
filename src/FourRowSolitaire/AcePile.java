
package FourRowSolitaire;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;

/**
 * Class: AcePile
 *
 * Description: The AcePile class manages one of the four foundation stacks.
 *
 */
public class AcePile extends CardStack
{
    private String suit;

    public AcePile(String suit)
    {
        this.suit = suit;
    }

    public String getSuit()
    {
        return suit;
    }

    public Card push(Card card)
    {
        if(isEmpty() && card.getSuit().equals(suit) && card.getNumber() == Card.ACE)
        {
            super.push(card);
            return card;
        }
        else if(card.getSuit().equals(suit) && card.getNumber() == peek().getNumber() + 1)
        {
            super.push(card);
            return card;
        }

        return null;
    }

    public Card getCardAtLocation(Point p)
    {
        return peek();
    }

    public boolean isValidMove(Card card)
    {
        if(isEmpty() && card.getSuit().equals(suit))
        {
            return true;
        }
        else if(!isEmpty() && card.getSuit().equals(suit) && card.getNumber() == (peek().getNumber() + 1))
        {
            return true;
        }

        return false;
    }

    public boolean isValidMove(CardStack stack)
    {
        return false;
    }

    public void paint(Graphics g)
    {
        super.paint(g);
        
        for(int i = 0; i < length(); i++)
        {
            Image image = getCardAtLocation(i).getImage();
            g.drawImage(image, 0, 0, null);
        }
    }
}