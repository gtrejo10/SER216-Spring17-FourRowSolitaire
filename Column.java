/*     */ package FourRowSolitaire;
/*     */ 
/*     */ import java.awt.Graphics;
/*     */ import java.awt.Point;
/*     */ import java.awt.image.BufferedImage;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Column
/*     */   extends CardStack
/*     */ {
/*     */   private Card previewCard;
/*     */   private int previewIndex;
/*     */   
/*     */   public Card push(Card paramCard)
/*     */   {
/*  43 */     if ((isEmpty()) && (paramCard.getNumber() == 13))
/*     */     {
/*  45 */       super.push(paramCard);
/*  46 */       return paramCard;
/*     */     }
/*  48 */     if ((paramCard.getColor() != peek().getColor()) && (paramCard.getNumber() == peek().getNumber() - 1))
/*     */     {
/*  50 */       super.push(paramCard);
/*  51 */       return paramCard;
/*     */     }
/*     */     
/*  54 */     return null;
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean isValidMove(Card paramCard)
/*     */   {
/*  60 */     if ((isEmpty()) && (paramCard.getNumber() == 13))
/*     */     {
/*  62 */       return true;
/*     */     }
/*  64 */     if ((!isEmpty()) && (paramCard.getColor() != peek().getColor()) && (paramCard.getNumber() == peek().getNumber() - 1))
/*     */     {
/*  66 */       return true;
/*     */     }
/*     */     
/*  69 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean isValidMove(CardStack paramCardStack)
/*     */   {
/*  75 */     return isValidMove(paramCardStack.peek());
/*     */   }
/*     */   
/*     */   public Card previewCard(Point paramPoint)
/*     */   {
/*  80 */     Object[] arrayOfObject = super.getCardAtLocationPreview(paramPoint);
/*     */     
/*  82 */     if (arrayOfObject == null)
/*     */     {
/*  84 */       return null;
/*     */     }
/*     */     
/*  87 */     this.previewCard = ((Card)arrayOfObject[0]);
/*  88 */     this.previewIndex = ((Integer)arrayOfObject[1]).intValue();
/*     */     
/*  90 */     repaint();
/*  91 */     revalidate();
/*  92 */     return this.previewCard;
/*     */   }
/*     */   
/*     */   public boolean stopPreview()
/*     */   {
/*  97 */     this.previewCard = null;
/*  98 */     repaint();
/*  99 */     revalidate();
/* 100 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */   public void paint(Graphics paramGraphics)
/*     */   {
/* 106 */     super.paint(paramGraphics);
/*     */     
/* 108 */     if (this.previewCard != null)
/*     */     {
/* 110 */       BufferedImage localBufferedImage = this.previewCard.getImage();
/* 111 */       paramGraphics.drawImage(localBufferedImage, 0, this.previewIndex * 25, null);
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Gema\Desktop\FourRowSolitaire-v.76.jar!\FourRowSolitaire\Column.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */