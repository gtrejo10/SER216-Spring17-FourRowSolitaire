/*     */ package FourRowSolitaire;
/*     */ 
/*     */ import java.awt.Component;
/*     */ import java.awt.Container;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.LayoutManager;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ public class SolitaireLayout
/*     */   implements LayoutManager
/*     */ {
/*     */   public static final String COLUMN_ONE = "Column One";
/*     */   public static final String COLUMN_TWO = "Column Two";
/*     */   public static final String COLUMN_THREE = "Column Three";
/*     */   public static final String COLUMN_FOUR = "Column Four";
/*     */   public static final String SPADES_ACE_PILE = "Spaces Ace Pile";
/*     */   public static final String CLUBS_ACE_PILE = "Clubs Ace Pile";
/*     */   public static final String DIAMONDS_ACE_PILE = "Diamonds Ace Pile";
/*     */   public static final String HEARTS_ACE_PILE = "Hears Ace Pile";
/*     */   public static final String DISCARD_PILE = "Discard Pile";
/*     */   public static final String DECK = "Deck";
/*     */   public static final String CELL_ONE = "Cell One";
/*     */   public static final String CELL_TWO = "Cell Two";
/*     */   public static final String CELL_THREE = "Cell Three";
/*     */   public static final String CELL_FOUR = "Cell Four";
/*     */   private Component colOne;
/*     */   private Component colTwo;
/*     */   private Component colThree;
/*     */   private Component colFour;
/*     */   private Component aceSpades;
/*     */   private Component aceClubs;
/*     */   private Component aceDiamonds;
/*     */   private Component aceHearts;
/*     */   private Component discardPile;
/*     */   private Component deck;
/*     */   private Component cellOne;
/*     */   private Component cellTwo;
/*     */   private Component cellThree;
/*     */   private Component cellFour;
/*     */   
/*     */   public void addLayoutComponent(String paramString, Component paramComponent)
/*     */   {
/*  73 */     if (paramString.equals("Column One"))
/*     */     {
/*  75 */       this.colOne = paramComponent;
/*     */     }
/*  77 */     else if (paramString.equals("Column Two"))
/*     */     {
/*  79 */       this.colTwo = paramComponent;
/*     */     }
/*  81 */     else if (paramString.equals("Column Three"))
/*     */     {
/*  83 */       this.colThree = paramComponent;
/*     */     }
/*  85 */     else if (paramString.equals("Column Four"))
/*     */     {
/*  87 */       this.colFour = paramComponent;
/*     */ 
/*     */     }
/*  90 */     else if (paramString.equals("Spaces Ace Pile"))
/*     */     {
/*  92 */       this.aceSpades = paramComponent;
/*     */     }
/*  94 */     else if (paramString.equals("Clubs Ace Pile"))
/*     */     {
/*  96 */       this.aceClubs = paramComponent;
/*     */     }
/*  98 */     else if (paramString.equals("Diamonds Ace Pile"))
/*     */     {
/* 100 */       this.aceDiamonds = paramComponent;
/*     */     }
/* 102 */     else if (paramString.equals("Hears Ace Pile"))
/*     */     {
/* 104 */       this.aceHearts = paramComponent;
/*     */ 
/*     */     }
/* 107 */     else if (paramString.equals("Discard Pile"))
/*     */     {
/* 109 */       this.discardPile = paramComponent;
/*     */     }
/* 111 */     else if (paramString.equals("Deck"))
/*     */     {
/* 113 */       this.deck = paramComponent;
/*     */ 
/*     */     }
/* 116 */     else if (paramString.equals("Cell One"))
/*     */     {
/* 118 */       this.cellOne = paramComponent;
/*     */     }
/* 120 */     else if (paramString.equals("Cell Two"))
/*     */     {
/* 122 */       this.cellTwo = paramComponent;
/*     */     }
/* 124 */     else if (paramString.equals("Cell Three"))
/*     */     {
/* 126 */       this.cellThree = paramComponent;
/*     */     }
/* 128 */     else if (paramString.equals("Cell Four"))
/*     */     {
/* 130 */       this.cellFour = paramComponent;
/*     */     }
/*     */   }
/*     */   
/*     */   public void removeLayoutComponent(Component paramComponent)
/*     */   {
/* 136 */     if (this.colOne == paramComponent)
/*     */     {
/* 138 */       this.colOne = null;
/*     */     }
/* 140 */     else if (this.colTwo == paramComponent)
/*     */     {
/* 142 */       this.colTwo = null;
/*     */     }
/* 144 */     else if (this.colThree == paramComponent)
/*     */     {
/* 146 */       this.colThree = null;
/*     */     }
/* 148 */     else if (this.colFour == paramComponent)
/*     */     {
/* 150 */       this.colFour = null;
/*     */ 
/*     */     }
/* 153 */     else if (this.aceSpades == paramComponent)
/*     */     {
/* 155 */       this.aceSpades = null;
/*     */     }
/* 157 */     else if (this.aceClubs == paramComponent)
/*     */     {
/* 159 */       this.aceClubs = null;
/*     */     }
/* 161 */     else if (this.aceDiamonds == paramComponent)
/*     */     {
/* 163 */       this.aceDiamonds = null;
/*     */     }
/* 165 */     else if (this.aceHearts == paramComponent)
/*     */     {
/* 167 */       this.aceHearts = null;
/*     */ 
/*     */     }
/* 170 */     else if (this.discardPile == paramComponent)
/*     */     {
/* 172 */       this.discardPile = null;
/*     */     }
/* 174 */     else if (this.deck == paramComponent)
/*     */     {
/* 176 */       this.deck = null;
/*     */ 
/*     */     }
/* 179 */     else if (this.cellOne == paramComponent)
/*     */     {
/* 181 */       this.cellOne = null;
/*     */     }
/* 183 */     else if (this.cellTwo == paramComponent)
/*     */     {
/* 185 */       this.cellTwo = null;
/*     */     }
/* 187 */     else if (this.cellThree == paramComponent)
/*     */     {
/* 189 */       this.cellThree = null;
/*     */     }
/* 191 */     else if (this.cellFour == paramComponent)
/*     */     {
/* 193 */       this.cellFour = null;
/*     */     }
/*     */   }
/*     */   
/*     */   public Dimension preferredLayoutSize(Container paramContainer)
/*     */   {
/* 199 */     throw new UnsupportedOperationException("Not supported yet.");
/*     */   }
/*     */   
/*     */   public Dimension minimumLayoutSize(Container paramContainer)
/*     */   {
/* 204 */     throw new UnsupportedOperationException("Not supported yet.");
/*     */   }
/*     */   
/*     */ 
/*     */   public void layoutContainer(Container paramContainer)
/*     */   {
/*     */     Component localComponent;
/* 211 */     if ((localComponent = this.colOne) != null)
/*     */     {
/* 213 */       localComponent.setBounds(81, 115, 72, 800);
/*     */     }
/* 215 */     if ((localComponent = this.colTwo) != null)
/*     */     {
/* 217 */       localComponent.setBounds(164, 115, 72, 800);
/*     */     }
/* 219 */     if ((localComponent = this.colThree) != null)
/*     */     {
/* 221 */       localComponent.setBounds(247, 115, 72, 800);
/*     */     }
/* 223 */     if ((localComponent = this.colFour) != null)
/*     */     {
/* 225 */       localComponent.setBounds(330, 115, 72, 800);
/*     */     }
/*     */     
/* 228 */     if ((localComponent = this.aceSpades) != null)
/*     */     {
/* 230 */       localComponent.setBounds(568, 3, 72, 96);
/*     */     }
/* 232 */     if ((localComponent = this.aceClubs) != null)
/*     */     {
/* 234 */       localComponent.setBounds(650, 3, 72, 96);
/*     */     }
/* 236 */     if ((localComponent = this.aceDiamonds) != null)
/*     */     {
/* 238 */       localComponent.setBounds(568, 110, 72, 96);
/*     */     }
/* 240 */     if ((localComponent = this.aceHearts) != null)
/*     */     {
/* 242 */       localComponent.setBounds(650, 110, 72, 96);
/*     */     }
/*     */     
/* 245 */     if ((localComponent = this.discardPile) != null)
/*     */     {
/* 247 */       localComponent.setBounds(650, 318, 102, 96);
/*     */     }
/* 249 */     if ((localComponent = this.deck) != null)
/*     */     {
/* 251 */       localComponent.setBounds(568, 318, 72, 96);
/*     */     }
/*     */     
/* 254 */     if ((localComponent = this.cellOne) != null)
/*     */     {
/* 256 */       localComponent.setBounds(81, 3, 72, 96);
/*     */     }
/* 258 */     if ((localComponent = this.cellTwo) != null)
/*     */     {
/* 260 */       localComponent.setBounds(164, 3, 72, 96);
/*     */     }
/* 262 */     if ((localComponent = this.cellThree) != null)
/*     */     {
/* 264 */       localComponent.setBounds(247, 3, 72, 96);
/*     */     }
/* 266 */     if ((localComponent = this.cellFour) != null)
/*     */     {
/* 268 */       localComponent.setBounds(330, 3, 72, 96);
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Gema\Desktop\FourRowSolitaire-v.76.jar!\FourRowSolitaire\SolitaireLayout.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */