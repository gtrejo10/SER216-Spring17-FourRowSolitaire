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
/*     */ public class DiscardPile
/*     */   extends CardStack
/*     */ {
/*  35 */   private int drawCount = 1;
/*  36 */   private int cardsLeftFromDraw = 0;
/*     */   
/*     */   public DiscardPile(int paramInt)
/*     */   {
/*  40 */     this.drawCount = paramInt;
/*     */   }
/*     */   
/*     */   public void setDrawCount(int paramInt)
/*     */   {
/*  45 */     this.drawCount = paramInt;
/*     */   }
/*     */   
/*     */   public int getNumViewableCards()
/*     */   {
/*  50 */     return this.cardsLeftFromDraw;
/*     */   }
/*     */   
/*     */   public void setView(int paramInt)
/*     */   {
/*  55 */     this.cardsLeftFromDraw = paramInt;
/*     */   }
/*     */   
/*     */ 
/*     */   public void addCard(Card paramCard)
/*     */   {
/*  61 */     this.cardsLeftFromDraw += 1;
/*  62 */     super.addCard(paramCard);
/*     */   }
/*     */   
/*     */ 
/*     */   public void addStack(CardStack paramCardStack)
/*     */   {
/*  68 */     for (int i = paramCardStack.length(); i > 0; i--)
/*     */     {
/*  70 */       Card localCard = paramCardStack.pop();
/*  71 */       addCard(localCard);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public Card push(Card paramCard)
/*     */   {
/*  78 */     if (this.drawCount == 1)
/*     */     {
/*  80 */       this.cardsLeftFromDraw = 0;
/*     */     }
/*     */     
/*  83 */     addCard(paramCard);
/*  84 */     paramCard.setSource("");
/*  85 */     return paramCard;
/*     */   }
/*     */   
/*     */ 
/*     */   public CardStack push(CardStack paramCardStack)
/*     */   {
/*  91 */     if ((this.drawCount != 1) || ((this.drawCount == 1) && (paramCardStack.length() == 1)))
/*     */     {
/*  93 */       this.cardsLeftFromDraw = 0;
/*     */       
/*  95 */       for (int i = 0; !paramCardStack.isEmpty(); i++)
/*     */       {
/*  97 */         push(paramCardStack.pop());
/*     */       }
/*     */     }
/*     */     
/* 101 */     return paramCardStack;
/*     */   }
/*     */   
/*     */ 
/*     */   public synchronized Card pop()
/*     */   {
/* 107 */     Card localCard = super.pop();
/*     */     
/*     */ 
/*     */ 
/* 111 */     if (this.cardsLeftFromDraw > 0)
/*     */     {
/* 113 */       this.cardsLeftFromDraw -= 1;
/*     */     }
/*     */     else
/*     */     {
/* 117 */       this.cardsLeftFromDraw = 0;
/*     */     }
/*     */     
/* 120 */     return localCard;
/*     */   }
/*     */   
/*     */   public synchronized Card undoPop()
/*     */   {
/* 125 */     Card localCard = super.pop();
/* 126 */     return localCard;
/*     */   }
/*     */   
/*     */ 
/*     */   public Card getCardAtLocation(Point paramPoint)
/*     */   {
/* 132 */     return peek();
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean isValidMove(Card paramCard)
/*     */   {
/* 138 */     return paramCard.getSource().equals("Deck");
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean isValidMove(CardStack paramCardStack)
/*     */   {
/* 144 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */   public void paint(Graphics paramGraphics)
/*     */   {
/* 150 */     super.paint(paramGraphics);
/*     */     int i;
/* 152 */     BufferedImage localBufferedImage; if ((!isEmpty()) && (this.drawCount == 1))
/*     */     {
/* 154 */       for (i = 0; i < length(); i++)
/*     */       {
/* 156 */         localBufferedImage = getCardAtLocation(i).getImage();
/* 157 */         paramGraphics.drawImage(localBufferedImage, 0, 0, null);
/*     */       }
/*     */       
/* 160 */     } else if ((!isEmpty()) && (this.drawCount == 3))
/*     */     {
/* 162 */       if (this.cardsLeftFromDraw > 0)
/*     */       {
/* 164 */         for (i = 0; i < length() - this.cardsLeftFromDraw + 1; i++)
/*     */         {
/* 166 */           localBufferedImage = getCardAtLocation(i).getImage();
/* 167 */           paramGraphics.drawImage(localBufferedImage, 0, 0, null);
/*     */         }
/*     */         
/* 170 */         for (i = length() - this.cardsLeftFromDraw + 1; i < length(); i++)
/*     */         {
/* 172 */           localBufferedImage = getCardAtLocation(i).getImage();
/*     */           
/* 174 */           if (((this.cardsLeftFromDraw == 3) && (i == length() - 2)) || ((this.cardsLeftFromDraw == 2) && (i == length() - 1)))
/*     */           {
/* 176 */             paramGraphics.drawImage(localBufferedImage, 15, 0, null);
/*     */           }
/* 178 */           else if ((this.cardsLeftFromDraw == 3) && (i == length() - 1))
/*     */           {
/* 180 */             paramGraphics.drawImage(localBufferedImage, 30, 0, null);
/*     */           }
/*     */         }
/*     */       }
/*     */       else
/*     */       {
/* 186 */         for (i = 0; i < length(); i++)
/*     */         {
/* 188 */           localBufferedImage = getCardAtLocation(i).getImage();
/* 189 */           paramGraphics.drawImage(localBufferedImage, 0, 0, null);
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Gema\Desktop\FourRowSolitaire-v.76.jar!\FourRowSolitaire\DiscardPile.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */