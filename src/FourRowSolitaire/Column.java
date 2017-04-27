package FourRowSolitaire;

public class Column extends CardStack
{
    public Column() {}

    public Card push(Card card)
    {
        if ((isEmpty()) && (card.getNumber() == 13))
        {
            super.push(card);
            return card;
        }
        if ((card.getColor() != peek().getColor()) && (card.getNumber() == peek().getNumber() - 1))
        {
            super.push(card);
            return card;
        }

        return null;
    }


    public boolean isValidMove(Card card)
    {
        if ((isEmpty()) && (card.getNumber() == 13))
        {
            return true;
        }
        if ((!isEmpty()) && (card.getColor() != peek().getColor()) && (card.getNumber() == peek().getNumber() - 1))
        {
            return true;
        }

        return false;
    }


    public boolean isValidMove(CardStack cardStack)
    {
        return isValidMove(cardStack.peek());
    }


}
