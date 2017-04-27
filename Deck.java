/*     */ package FourRowSolitaire;
/*     */ 
/*     */ import java.util.LinkedList;
/*     */ import java.util.Random;
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
/*     */ public class Deck
/*     */ {
/*     */   private final int deckNumber;
/*  35 */   private LinkedList<Card> deck = new LinkedList();
/*     */   
/*     */   public Deck(int paramInt)
/*     */   {
/*  39 */     this.deckNumber = paramInt;
/*  40 */     shuffle();
/*     */   }
/*     */   
/*     */   public LinkedList<Card> getDeck()
/*     */   {
/*  45 */     return this.deck;
/*     */   }
/*     */   
/*     */   public LinkedList<Card> getDeck(LinkedList<Integer> paramLinkedList)
/*     */   {
/*  50 */     this.deck = new LinkedList();
/*     */     
/*  52 */     for (int i = 0; i < paramLinkedList.size(); i++)
/*     */     {
/*  54 */       if (((Integer)paramLinkedList.get(i)).intValue() > 0)
/*     */       {
/*  56 */         createCard(((Integer)paramLinkedList.get(i)).intValue());
/*     */       }
/*     */     }
/*     */     
/*  60 */     return this.deck;
/*     */   }
/*     */   
/*     */   private void shuffle()
/*     */   {
/*  65 */     LinkedList localLinkedList = new LinkedList();
/*     */     
/*  67 */     for (int i = 1; i <= 52; i++)
/*     */     {
/*  69 */       localLinkedList.add(Integer.valueOf(i));
/*     */     }
/*     */     
/*  72 */     Random localRandom = new Random();
/*     */     
/*  74 */     for (int j = 0; j < 52; j++)
/*     */     {
/*  76 */       int k = localRandom.nextInt(localLinkedList.size());
/*     */       
/*  78 */       int m = ((Integer)localLinkedList.get(k)).intValue();
/*  79 */       localLinkedList.remove(k);
/*     */       
/*  81 */       createCard(m);
/*     */     }
/*     */   }
/*     */   
/*     */   private void createCard(int paramInt)
/*     */   {
/*  87 */     if ((paramInt >= 1) && (paramInt <= 13))
/*     */     {
/*  89 */       this.deck.add(new Card("Spades", paramInt, this.deckNumber, paramInt));
/*     */     }
/*  91 */     else if ((paramInt >= 14) && (paramInt <= 26))
/*     */     {
/*  93 */       paramInt -= 13;
/*  94 */       this.deck.add(new Card("Clubs", paramInt, this.deckNumber, paramInt + 13));
/*     */     }
/*  96 */     else if ((paramInt >= 27) && (paramInt <= 39))
/*     */     {
/*  98 */       paramInt -= 26;
/*  99 */       this.deck.add(new Card("Diamonds", paramInt, this.deckNumber, paramInt + 26));
/*     */     }
/* 101 */     else if ((paramInt >= 40) && (paramInt <= 52))
/*     */     {
/* 103 */       paramInt -= 39;
/* 104 */       this.deck.add(new Card("Hearts", paramInt, this.deckNumber, paramInt + 39));
/*     */ 
/*     */     }
/*     */     else
/*     */     {
/* 109 */       this.deck.add(new Card("Invalid Suit", -1, this.deckNumber, -1));
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Gema\Desktop\FourRowSolitaire-v.76.jar!\FourRowSolitaire\Deck.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */