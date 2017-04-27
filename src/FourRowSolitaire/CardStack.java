package FourRowSolitaire;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.swing.JLayeredPane;

public class CardStack
        extends JLayeredPane
{
    private final ArrayList<Card> cards = new ArrayList();


    public CardStack() {}

    public void addCard(Card paramCard)
    {
        cards.add(paramCard);
        paramCard.setBounds(0, 0, 72, 96);
        add(paramCard, 0);
    }

    public void addStack(CardStack paramCardStack)
    {
        for (int i = paramCardStack.length(); i > 0; i--)
        {
            Card localCard = paramCardStack.pop();
            addCard(localCard);
        }
    }

    public Card push(Card paramCard)
    {
        cards.add(paramCard);
        paramCard.setBounds(0, 0, 72, 96);
        add(paramCard, 0);
        return paramCard;
    }

    public CardStack push(CardStack paramCardStack)
    {
        while (!paramCardStack.isEmpty())
        {
            Card localCard = paramCardStack.pop();
            push(localCard);
        }

        return paramCardStack;
    }

    public synchronized Card pop()
    {
        Card localCard = peek();

        remove(localCard);
        cards.remove(cards.size() - 1);

        return localCard;
    }


    public CardStack pop(CardStack paramCardStack)
    {
        CardStack localCardStack = new CardStack();

        while (!paramCardStack.isEmpty())
        {
            Card localCard = paramCardStack.pop();
            localCardStack.push(localCard);
            remove(localCard);
        }

        return localCardStack;
    }

    public synchronized Card peek()
    {
        if (cards.isEmpty())
        {
            return null;
        }

        return (Card)cards.get(cards.size() - 1);
    }

    public boolean isEmpty()
    {
        return cards.isEmpty();
    }

    public int length()
    {
        return cards.size();
    }

    public synchronized int search(Card paramCard)
    {
        int i = cards.lastIndexOf(paramCard);

        if (i >= 0)
        {
            return cards.size() - i;
        }

        return -1;
    }

    public Card getCardAtLocation(int paramInt)
    {
        if (paramInt < cards.size())
        {
            return (Card)cards.get(paramInt);
        }

        return null;
    }

    public Card getCardAtLocation(Point paramPoint)
    {
        if (cards.isEmpty())
        {
            return null;
        }

        if (isValidClick(paramPoint))
        {
            int i = (int)paramPoint.getY();

            int j;

            if (i > 25 * (cards.size() - 1))
            {
                j = cards.size() - 1;
            }
            else
            {
                j = i / 25;
            }

            if (isValidCard(j))
            {
                return (Card)cards.get(j);
            }
        }

        return null;
    }

    public Object[] getCardAtLocationPreview(Point paramPoint)
    {
        if (cards.isEmpty())
        {
            return null;
        }

        int i = (int)paramPoint.getY();

        int j;

        if (i > 25 * (cards.size() - 1))
        {
            j = cards.size() - 1;
        }
        else
        {
            j = i / 25;
        }

        if (j < cards.size())
        {
            return new Object[] { cards.get(j), Integer.valueOf(j) };
        }


        return null;
    }



    private boolean isValidCard(int paramInt)
    {
        if (paramInt >= cards.size())
        {
            return false;
        }

        for (int i = paramInt; i < cards.size() - 1; i++)
        {

            if ((((Card)cards.get(i)).getColor() == ((Card)cards.get(i + 1)).getColor()) || (((Card)cards.get(i)).getNumber() != ((Card)cards.get(i + 1)).getNumber() + 1))
            {

                return false;
            }
        }

        return true;
    }


    private boolean isValidClick(Point paramPoint)
    {
        int i = (int)paramPoint.getY();

        if (!isEmpty())
        {
            if (i > 25 * (cards.size() - 1) + ((Card)cards.get(cards.size() - 1)).getBounds().getHeight())
            {
                return false;
            }
        }

        return true;
    }

    public CardStack getStack(Card paramCard)
    {
        CardStack localCardStack = new CardStack();
        int i = search(paramCard);

        for (int j = 0; j < i; j++)
        {
            localCardStack.push(getCardAtLocation(cards.size() - j - 1).clone());
            getCardAtLocation(cards.size() - j - 1).highlight();
        }

        return localCardStack;
    }

    public CardStack getStack(int paramInt)
    {
        CardStack localCardStack = new CardStack();
        int i = length() - paramInt;

        for (int j = length(); j > i; j--)
        {
            localCardStack.push(getCardAtLocation(cards.size() - j - 1).clone());
            getCardAtLocation(cards.size() - j - 1).highlight();
        }

        return localCardStack;
    }

    public CardStack undoStack(int paramInt)
    {
        CardStack localCardStack = new CardStack();

        for (int i = 0; i < paramInt; i++)
        {
            localCardStack.push(pop());
        }

        return localCardStack;
    }

    public boolean isValidMove(Card paramCard)
    {
        return false;
    }

    public boolean isValidMove(CardStack paramCardStack)
    {
        return false;
    }

    public Card getBottom()
    {
        return (Card)cards.get(0);
    }

    public CardStack getAvailableCards() {
        CardStack localCardStack;
        if ((!isEmpty()) && ((this instanceof Column)))
        {
            localCardStack = new CardStack();
            int i = 1;
            int j = length() - 1;

            localCardStack.addCard((Card)cards.get(j));

            do
            {
                j--;

                if (j >= 0)
                {
                    Card localCard = (Card)cards.get(j);

                    if ((localCard.getColor() != localCardStack.peek().getColor()) && (localCard.getNumber() == localCardStack.peek().getNumber() + 1))
                    {
                        localCardStack.addCard(localCard);
                    }
                    else
                    {
                        i = 0;
                    }
                }
                else
                {
                    i = 0;
                }
            } while (i != 0);

            return localCardStack;
        }
        if (!isEmpty())
        {
            localCardStack = new CardStack();
            localCardStack.addCard(peek());

            return localCardStack;
        }

        return null;
    }


    public void paint(Graphics paramGraphics)
    {
        super.paint(paramGraphics);

        if (!isEmpty())
        {
            for (int i = 0; i < cards.size(); i++)
            {
                BufferedImage localBufferedImage = ((Card)cards.get(i)).getImage();
                paramGraphics.drawImage(localBufferedImage, 0, i * 25, null);
            }
        }
    }
}
