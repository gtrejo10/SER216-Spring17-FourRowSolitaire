/*     */ package FourRowSolitaire;
/*     */ 
/*     */ import java.awt.Graphics;
/*     */ import java.awt.Point;
/*     */ import java.awt.Rectangle;
/*     */ import java.awt.image.BufferedImage;
/*     */ import java.util.ArrayList;
/*     */ import javax.swing.JLayeredPane;
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
/*     */ public class CardStack
/*     */   extends JLayeredPane
/*     */ {
/*  35 */   private final ArrayList<Card> cards = new ArrayList();
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void addCard(Card paramCard)
/*     */   {
/*  42 */     this.cards.add(paramCard);
/*  43 */     paramCard.setBounds(0, 0, 72, 96);
/*  44 */     add(paramCard, 0);
/*     */   }
/*     */   
/*     */   public void addStack(CardStack paramCardStack)
/*     */   {
/*  49 */     for (int i = paramCardStack.length(); i > 0; i--)
/*     */     {
/*  51 */       Card localCard = paramCardStack.pop();
/*  52 */       addCard(localCard);
/*     */     }
/*     */   }
/*     */   
/*     */   public Card push(Card paramCard)
/*     */   {
/*  58 */     this.cards.add(paramCard);
/*  59 */     paramCard.setBounds(0, 0, 72, 96);
/*  60 */     add(paramCard, 0);
/*  61 */     return paramCard;
/*     */   }
/*     */   
/*     */   public CardStack push(CardStack paramCardStack)
/*     */   {
/*  66 */     while (!paramCardStack.isEmpty())
/*     */     {
/*  68 */       Card localCard = paramCardStack.pop();
/*  69 */       push(localCard);
/*     */     }
/*     */     
/*  72 */     return paramCardStack;
/*     */   }
/*     */   
/*     */   public synchronized Card pop()
/*     */   {
/*  77 */     Card localCard = peek();
/*     */     
/*  79 */     remove(localCard);
/*  80 */     this.cards.remove(this.cards.size() - 1);
/*     */     
/*  82 */     return localCard;
/*     */   }
/*     */   
/*     */ 
/*     */   public CardStack pop(CardStack paramCardStack)
/*     */   {
/*  88 */     CardStack localCardStack = new CardStack();
/*     */     
/*  90 */     while (!paramCardStack.isEmpty())
/*     */     {
/*  92 */       Card localCard = paramCardStack.pop();
/*  93 */       localCardStack.push(localCard);
/*  94 */       remove(localCard);
/*     */     }
/*     */     
/*  97 */     return localCardStack;
/*     */   }
/*     */   
/*     */   public synchronized Card peek()
/*     */   {
/* 102 */     if (this.cards.isEmpty())
/*     */     {
/* 104 */       return null;
/*     */     }
/*     */     
/* 107 */     return (Card)this.cards.get(this.cards.size() - 1);
/*     */   }
/*     */   
/*     */   public boolean isEmpty()
/*     */   {
/* 112 */     return this.cards.isEmpty();
/*     */   }
/*     */   
/*     */   public int length()
/*     */   {
/* 117 */     return this.cards.size();
/*     */   }
/*     */   
/*     */   public synchronized int search(Card paramCard)
/*     */   {
/* 122 */     int i = this.cards.lastIndexOf(paramCard);
/*     */     
/* 124 */     if (i >= 0)
/*     */     {
/* 126 */       return this.cards.size() - i;
/*     */     }
/*     */     
/* 129 */     return -1;
/*     */   }
/*     */   
/*     */   public Card getCardAtLocation(int paramInt)
/*     */   {
/* 134 */     if (paramInt < this.cards.size())
/*     */     {
/* 136 */       return (Card)this.cards.get(paramInt);
/*     */     }
/*     */     
/* 139 */     return null;
/*     */   }
/*     */   
/*     */   public Card getCardAtLocation(Point paramPoint)
/*     */   {
/* 144 */     if (this.cards.isEmpty())
/*     */     {
/* 146 */       return null;
/*     */     }
/*     */     
/* 149 */     if (isValidClick(paramPoint))
/*     */     {
/* 151 */       int i = (int)paramPoint.getY();
/*     */       
/*     */       int j;
/*     */       
/* 155 */       if (i > 25 * (this.cards.size() - 1))
/*     */       {
/* 157 */         j = this.cards.size() - 1;
/*     */       }
/*     */       else
/*     */       {
/* 161 */         j = i / 25;
/*     */       }
/*     */       
/* 164 */       if (isValidCard(j))
/*     */       {
/* 166 */         return (Card)this.cards.get(j);
/*     */       }
/*     */     }
/*     */     
/* 170 */     return null;
/*     */   }
/*     */   
/*     */   public Object[] getCardAtLocationPreview(Point paramPoint)
/*     */   {
/* 175 */     if (this.cards.isEmpty())
/*     */     {
/* 177 */       return null;
/*     */     }
/*     */     
/* 180 */     int i = (int)paramPoint.getY();
/*     */     
/*     */     int j;
/*     */     
/* 184 */     if (i > 25 * (this.cards.size() - 1))
/*     */     {
/* 186 */       j = this.cards.size() - 1;
/*     */     }
/*     */     else
/*     */     {
/* 190 */       j = i / 25;
/*     */     }
/*     */     
/* 193 */     if (j < this.cards.size())
/*     */     {
/* 195 */       return new Object[] { this.cards.get(j), Integer.valueOf(j) };
/*     */     }
/*     */     
/*     */ 
/* 199 */     return null;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   private boolean isValidCard(int paramInt)
/*     */   {
/* 206 */     if (paramInt >= this.cards.size())
/*     */     {
/* 208 */       return false;
/*     */     }
/*     */     
/* 211 */     for (int i = paramInt; i < this.cards.size() - 1; i++)
/*     */     {
/*     */ 
/* 214 */       if ((((Card)this.cards.get(i)).getColor() == ((Card)this.cards.get(i + 1)).getColor()) || (((Card)this.cards.get(i)).getNumber() != ((Card)this.cards.get(i + 1)).getNumber() + 1))
/*     */       {
/*     */ 
/* 217 */         return false;
/*     */       }
/*     */     }
/*     */     
/* 221 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */   private boolean isValidClick(Point paramPoint)
/*     */   {
/* 227 */     int i = (int)paramPoint.getY();
/*     */     
/* 229 */     if (!isEmpty())
/*     */     {
/* 231 */       if (i > 25 * (this.cards.size() - 1) + ((Card)this.cards.get(this.cards.size() - 1)).getBounds().getHeight())
/*     */       {
/* 233 */         return false;
/*     */       }
/*     */     }
/*     */     
/* 237 */     return true;
/*     */   }
/*     */   
/*     */   public CardStack getStack(Card paramCard)
/*     */   {
/* 242 */     CardStack localCardStack = new CardStack();
/* 243 */     int i = search(paramCard);
/*     */     
/* 245 */     for (int j = 0; j < i; j++)
/*     */     {
/* 247 */       localCardStack.push(getCardAtLocation(this.cards.size() - j - 1).clone());
/* 248 */       getCardAtLocation(this.cards.size() - j - 1).highlight();
/*     */     }
/*     */     
/* 251 */     return localCardStack;
/*     */   }
/*     */   
/*     */   public CardStack getStack(int paramInt)
/*     */   {
/* 256 */     CardStack localCardStack = new CardStack();
/* 257 */     int i = length() - paramInt;
/*     */     
/* 259 */     for (int j = length(); j > i; j--)
/*     */     {
/* 261 */       localCardStack.push(getCardAtLocation(this.cards.size() - j - 1).clone());
/* 262 */       getCardAtLocation(this.cards.size() - j - 1).highlight();
/*     */     }
/*     */     
/* 265 */     return localCardStack;
/*     */   }
/*     */   
/*     */   public CardStack undoStack(int paramInt)
/*     */   {
/* 270 */     CardStack localCardStack = new CardStack();
/*     */     
/* 272 */     for (int i = 0; i < paramInt; i++)
/*     */     {
/* 274 */       localCardStack.push(pop());
/*     */     }
/*     */     
/* 277 */     return localCardStack;
/*     */   }
/*     */   
/*     */   public boolean isValidMove(Card paramCard)
/*     */   {
/* 282 */     return false;
/*     */   }
/*     */   
/*     */   public boolean isValidMove(CardStack paramCardStack)
/*     */   {
/* 287 */     return false;
/*     */   }
/*     */   
/*     */   public Card getBottom()
/*     */   {
/* 292 */     return (Card)this.cards.get(0);
/*     */   }
/*     */   
/*     */   public CardStack getAvailableCards() {
/*     */     CardStack localCardStack;
/* 297 */     if ((!isEmpty()) && ((this instanceof Column)))
/*     */     {
/* 299 */       localCardStack = new CardStack();
/* 300 */       int i = 1;
/* 301 */       int j = length() - 1;
/*     */       
/* 303 */       localCardStack.addCard((Card)this.cards.get(j));
/*     */       
/*     */       do
/*     */       {
/* 307 */         j--;
/*     */         
/* 309 */         if (j >= 0)
/*     */         {
/* 311 */           Card localCard = (Card)this.cards.get(j);
/*     */           
/* 313 */           if ((localCard.getColor() != localCardStack.peek().getColor()) && (localCard.getNumber() == localCardStack.peek().getNumber() + 1))
/*     */           {
/* 315 */             localCardStack.addCard(localCard);
/*     */           }
/*     */           else
/*     */           {
/* 319 */             i = 0;
/*     */           }
/*     */         }
/*     */         else
/*     */         {
/* 324 */           i = 0;
/*     */         }
/* 326 */       } while (i != 0);
/*     */       
/* 328 */       return localCardStack;
/*     */     }
/* 330 */     if (!isEmpty())
/*     */     {
/* 332 */       localCardStack = new CardStack();
/* 333 */       localCardStack.addCard(peek());
/*     */       
/* 335 */       return localCardStack;
/*     */     }
/*     */     
/* 338 */     return null;
/*     */   }
/*     */   
/*     */ 
/*     */   public void paint(Graphics paramGraphics)
/*     */   {
/* 344 */     super.paint(paramGraphics);
/*     */     
/* 346 */     if (!isEmpty())
/*     */     {
/* 348 */       for (int i = 0; i < this.cards.size(); i++)
/*     */       {
/* 350 */         BufferedImage localBufferedImage = ((Card)this.cards.get(i)).getImage();
/* 351 */         paramGraphics.drawImage(localBufferedImage, 0, i * 25, null);
/*     */       }
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Gema\Desktop\FourRowSolitaire-v.76.jar!\FourRowSolitaire\CardStack.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */