/*    */ package FourRowSolitaire;
/*    */ 
/*    */ import java.awt.Point;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ public class SingleCell
/*    */   extends CardStack
/*    */ {
/*    */   public Card push(Card paramCard)
/*    */   {
/* 38 */     if (isEmpty())
/*    */     {
/* 40 */       super.push(paramCard);
/* 41 */       return paramCard;
/*    */     }
/*    */     
/* 44 */     return null;
/*    */   }
/*    */   
/*    */ 
/*    */   public Card getCardAtLocation(Point paramPoint)
/*    */   {
/* 50 */     return peek();
/*    */   }
/*    */   
/*    */ 
/*    */   public boolean isValidMove(Card paramCard)
/*    */   {
/* 56 */     return isEmpty();
/*    */   }
/*    */   
/*    */ 
/*    */   public boolean isValidMove(CardStack paramCardStack)
/*    */   {
/* 62 */     return false;
/*    */   }
/*    */ }


/* Location:              C:\Users\Gema\Desktop\FourRowSolitaire-v.76.jar!\FourRowSolitaire\SingleCell.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */