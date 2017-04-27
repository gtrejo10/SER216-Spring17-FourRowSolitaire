/*     */ package FourRowSolitaire;
/*     */ 
/*     */ import java.awt.Graphics;
/*     */ import java.awt.Point;
/*     */ import java.awt.image.BufferedImage;
/*     */ import java.util.LinkedList;
/*     */ import javax.swing.JOptionPane;
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
/*     */ public class DealDeck
/*     */   extends CardStack
/*     */ {
/*     */   private final DiscardPile discardPile;
/*  38 */   private int numTimesThroughDeck = 1;
/*     */   
/*  40 */   private int drawCount = 1;
/*  41 */   private int difficulty = 2;
/*     */   
/*     */   private static final int EASY_THROUGH_LIMIT = 3;
/*     */   
/*     */   private static final int MEDIUM_THROUGH_LIMIT = 2;
/*     */   
/*     */   private static final int HARD_THROUGH_LIMIT = 1;
/*     */   
/*     */   private int deckThroughLimit;
/*     */   
/*  51 */   private boolean redealable = true;
/*     */   
/*     */   public DealDeck(DiscardPile paramDiscardPile, int paramInt)
/*     */   {
/*  55 */     this.discardPile = paramDiscardPile;
/*  56 */     this.drawCount = paramInt;
/*     */     
/*  58 */     if (paramInt == 3)
/*     */     {
/*  60 */       this.deckThroughLimit = 3;
/*     */     }
/*     */     else
/*     */     {
/*  64 */       this.deckThroughLimit = 2;
/*     */     }
/*     */     
/*  67 */     paramDiscardPile.setDrawCount(paramInt);
/*     */   }
/*     */   
/*     */   public void reset()
/*     */   {
/*  72 */     this.numTimesThroughDeck = 1;
/*     */   }
/*     */   
/*     */   private void undone()
/*     */   {
/*  77 */     this.numTimesThroughDeck -= 1;
/*     */   }
/*     */   
/*     */   public int getDeckThroughs()
/*     */   {
/*  82 */     return this.numTimesThroughDeck;
/*     */   }
/*     */   
/*     */   public void setDeckThroughs(int paramInt)
/*     */   {
/*  87 */     this.numTimesThroughDeck = paramInt;
/*     */   }
/*     */   
/*     */   public void setDeck(LinkedList<Card> paramLinkedList)
/*     */   {
/*  92 */     for (int i = 0; i < paramLinkedList.size(); i++)
/*     */     {
/*  94 */       ((Card)paramLinkedList.get(i)).setFaceDown();
/*  95 */       addCard((Card)paramLinkedList.get(i));
/*     */     }
/*     */   }
/*     */   
/*     */   public void setDrawCount(int paramInt)
/*     */   {
/* 101 */     this.drawCount = paramInt;
/* 102 */     this.discardPile.setDrawCount(paramInt);
/*     */     
/* 104 */     if (this.drawCount == 3)
/*     */     {
/* 106 */       this.deckThroughLimit = 3;
/*     */     }
/*     */     else
/*     */     {
/* 110 */       this.deckThroughLimit = 2;
/*     */     }
/*     */   }
/*     */   
/*     */   public void setDifficulty(int paramInt)
/*     */   {
/* 116 */     this.difficulty = paramInt;
/*     */     
/* 118 */     if (paramInt == 1)
/*     */     {
/* 120 */       this.deckThroughLimit = 3;
/*     */     }
/* 122 */     else if (paramInt == 3)
/*     */     {
/* 124 */       this.deckThroughLimit = 1;
/*     */     }
/*     */     else
/*     */     {
/* 128 */       this.deckThroughLimit = 2;
/*     */     }
/*     */     
/* 131 */     if (this.drawCount == 3)
/*     */     {
/* 133 */       this.deckThroughLimit += 1;
/*     */     }
/*     */   }
/*     */   
/*     */   public boolean hasDealsLeft()
/*     */   {
/* 139 */     return this.redealable;
/*     */   }
/*     */   
/*     */   public synchronized Card pop() {
/*     */     int i;
/*     */     Object localObject1;
/* 145 */     if (!isEmpty())
/*     */     {
/*     */ 
/* 148 */       if (this.drawCount == 1)
/*     */       {
/* 150 */         Card localCard = super.pop();
/*     */         
/* 152 */         localCard.setFaceUp();
/* 153 */         this.discardPile.push(localCard);
/*     */         
/* 155 */         repaint();
/* 156 */         return localCard;
/*     */       }
/*     */       
/*     */ 
/* 160 */       i = this.drawCount;
/* 161 */       localObject1 = new CardStack();
/*     */       
/* 163 */       while ((this.drawCount > 1) && (i > 0) && (!isEmpty()))
/*     */       {
/* 165 */         localObject2 = super.pop();
/*     */         
/* 167 */         ((Card)localObject2).setFaceUp();
/* 168 */         ((CardStack)localObject1).push((Card)localObject2);
/*     */         
/* 170 */         i--;
/*     */       }
/*     */       
/* 173 */       Object localObject2 = new CardStack();
/*     */       
/* 175 */       for (int j = ((CardStack)localObject1).length(); j > 0; j--)
/*     */       {
/* 177 */         ((CardStack)localObject2).push(((CardStack)localObject1).pop());
/*     */       }
/*     */       
/* 180 */       this.discardPile.push((CardStack)localObject2);
/*     */       
/* 182 */       repaint();
/* 183 */       return this.discardPile.peek();
/*     */     }
/*     */     
/* 186 */     if ((!this.discardPile.isEmpty()) && (this.numTimesThroughDeck < this.deckThroughLimit))
/*     */     {
/* 188 */       for (i = this.discardPile.length(); i > 0; i--)
/*     */       {
/* 190 */         localObject1 = this.discardPile.pop();
/* 191 */         ((Card)localObject1).setFaceDown();
/* 192 */         ((Card)localObject1).setSource("Deck");
/* 193 */         push((Card)localObject1);
/*     */       }
/*     */       
/* 196 */       this.discardPile.repaint();
/* 197 */       this.numTimesThroughDeck += 1;
/*     */     }
/* 199 */     else if (this.numTimesThroughDeck >= this.deckThroughLimit)
/*     */     {
/* 201 */       this.redealable = false;
/* 202 */       JOptionPane.showMessageDialog(null, "You have reached your deck through limit.");
/*     */     }
/*     */     
/* 205 */     repaint();
/* 206 */     return null;
/*     */   }
/*     */   
/*     */   public synchronized void undoPop()
/*     */   {
/* 211 */     while (!isEmpty())
/*     */     {
/* 213 */       Card localCard = super.pop();
/* 214 */       localCard.setFaceUp();
/* 215 */       this.discardPile.push(localCard);
/*     */     }
/*     */     
/* 218 */     undone();
/*     */     
/* 220 */     if (!this.redealable)
/*     */     {
/* 222 */       this.redealable = true;
/*     */     }
/*     */     
/* 225 */     this.discardPile.repaint();
/* 226 */     repaint();
/*     */   }
/*     */   
/*     */ 
/*     */   public Card getCardAtLocation(Point paramPoint)
/*     */   {
/* 232 */     return peek();
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean isValidMove(Card paramCard)
/*     */   {
/* 238 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean isValidMove(CardStack paramCardStack)
/*     */   {
/* 244 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */   public void paint(Graphics paramGraphics)
/*     */   {
/* 250 */     super.paint(paramGraphics);
/*     */     
/* 252 */     if (!isEmpty())
/*     */     {
/* 254 */       for (int i = 0; i < length(); i++)
/*     */       {
/* 256 */         BufferedImage localBufferedImage = getCardAtLocation(i).getImage();
/* 257 */         paramGraphics.drawImage(localBufferedImage, 0, 0, null);
/*     */       }
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Gema\Desktop\FourRowSolitaire-v.76.jar!\FourRowSolitaire\DealDeck.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */