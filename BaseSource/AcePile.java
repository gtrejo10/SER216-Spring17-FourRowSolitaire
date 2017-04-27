/*    */ package FourRowSolitaire;
/*    */ 
/*    */ import java.awt.Graphics;
/*    */ import java.awt.Point;
/*    */ import java.awt.image.BufferedImage;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class AcePile
/*    */   extends CardStack
/*    */ {
/*    */   private final String suit;
/*    */   
/*    */   public AcePile(String paramString)
/*    */   {
/* 39 */     this.suit = paramString;
/*    */   }
/*    */   
/*    */   public String getSuit()
/*    */   {
/* 44 */     return this.suit;
/*    */   }
/*    */   
/*    */ 
/*    */   public Card push(Card paramCard)
/*    */   {
/* 50 */     if ((isEmpty()) && (paramCard.getSuit().equals(this.suit)) && (paramCard.getNumber() == 1))
/*    */     {
/* 52 */       super.push(paramCard);
/* 53 */       return paramCard;
/*    */     }
/* 55 */     if ((paramCard.getSuit().equals(this.suit)) && (paramCard.getNumber() == peek().getNumber() + 1))
/*    */     {
/* 57 */       super.push(paramCard);
/* 58 */       return paramCard;
/*    */     }
/*    */     
/* 61 */     return null;
/*    */   }
/*    */   
/*    */ 
/*    */   public Card getCardAtLocation(Point paramPoint)
/*    */   {
/* 67 */     return peek();
/*    */   }
/*    */   
/*    */ 
/*    */   public boolean isValidMove(Card paramCard)
/*    */   {
/* 73 */     if ((isEmpty()) && (paramCard.getSuit().equals(this.suit)))
/*    */     {
/* 75 */       return true;
/*    */     }
/* 77 */     if ((!isEmpty()) && (paramCard.getSuit().equals(this.suit)) && (paramCard.getNumber() == peek().getNumber() + 1))
/*    */     {
/* 79 */       return true;
/*    */     }
/*    */     
/* 82 */     return false;
/*    */   }
/*    */   
/*    */ 
/*    */   public boolean isValidMove(CardStack paramCardStack)
/*    */   {
/* 88 */     return false;
/*    */   }
/*    */   
/*    */ 
/*    */   public void paint(Graphics paramGraphics)
/*    */   {
/* 94 */     super.paint(paramGraphics);
/*    */     
/* 96 */     for (int i = 0; i < length(); i++)
/*    */     {
/* 98 */       BufferedImage localBufferedImage = getCardAtLocation(i).getImage();
/* 99 */       paramGraphics.drawImage(localBufferedImage, 0, 0, null);
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\Gema\Desktop\FourRowSolitaire-v.76.jar!\FourRowSolitaire\AcePile.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */