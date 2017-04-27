/*     */ package FourRowSolitaire;
/*     */ 
/*     */ import java.awt.Graphics;
/*     */ import java.awt.image.BufferedImage;
/*     */ import java.io.IOException;
/*     */ import java.io.PrintStream;
/*     */ import java.net.URL;
/*     */ import javax.imageio.ImageIO;
/*     */ import javax.swing.JComponent;
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
/*     */ public final class Card
/*     */   extends JComponent
/*     */ {
/*     */   public static final String SPADES_SUIT = "Spades";
/*     */   public static final String CLUBS_SUIT = "Clubs";
/*     */   public static final String HEARTS_SUIT = "Hearts";
/*     */   public static final String DIAMONDS_SUIT = "Diamonds";
/*     */   public static final String INVALID_SUIT = "Invalid Suit";
/*     */   public static final int ACE = 1;
/*     */   public static final int TWO = 2;
/*     */   public static final int THREE = 3;
/*     */   public static final int FOUR = 4;
/*     */   public static final int FIVE = 5;
/*     */   public static final int SIX = 6;
/*     */   public static final int SEVEN = 7;
/*     */   public static final int EIGHT = 8;
/*     */   public static final int NINE = 9;
/*     */   public static final int TEN = 10;
/*     */   public static final int JACK = 11;
/*     */   public static final int QUEEN = 12;
/*     */   public static final int KING = 13;
/*     */   public static final int INVALID_NUMBER = -1;
/*     */   private final String cardSuit;
/*     */   private final int cardNumber;
/*     */   private int fullCardNumber;
/*     */   private int cardColor;
/*     */   private int deckNumber;
/*     */   private BufferedImage image;
/*     */   private String cardBack;
/*     */   private String cardImageString;
/*     */   private String cardHighlighted;
/*  72 */   private boolean faceUp = false;
/*  73 */   private boolean highlighted = false;
/*     */   
/*  75 */   private String location = "";
/*     */   
/*     */   public Card(String paramString, int paramInt1, int paramInt2, int paramInt3)
/*     */   {
/*  79 */     if ((isValidSuit(paramString)) && (paramInt1 >= 1) && (paramInt1 <= 13))
/*     */     {
/*  81 */       this.cardSuit = paramString;
/*  82 */       this.cardNumber = paramInt1;
/*  83 */       this.fullCardNumber = paramInt3;
/*  84 */       this.deckNumber = paramInt2;
/*     */       
/*  86 */       if ((paramInt2 >= 1) && (paramInt2 <= 3))
/*     */       {
/*  88 */         this.cardBack = ("images/cardbacks/cardback" + paramInt2 + ".png");
/*     */       }
/*     */       else
/*     */       {
/*  92 */         this.cardBack = "images/cardbacks/cardback3.png";
/*     */       }
/*     */       
/*  95 */       initializeCardImageString();
/*     */     }
/*     */     else
/*     */     {
/*  99 */       this.cardSuit = "Invalid Suit";
/* 100 */       this.cardNumber = -1;
/*     */       
/* 102 */       this.cardImageString = "images/invalidcard.png";
/*     */     }
/*     */     
/* 105 */     setFaceUp();
/*     */   }
/*     */   
/*     */   public void highlight()
/*     */   {
/* 110 */     this.highlighted = true;
/*     */     
/*     */     try
/*     */     {
/* 114 */       URL localURL = getClass().getResource(this.cardHighlighted);
/*     */       
/* 116 */       if (localURL != null)
/*     */       {
/* 118 */         this.image = ImageIO.read(localURL);
/*     */       }
/*     */     }
/*     */     catch (IOException localIOException)
/*     */     {
/* 123 */       System.err.println("Error in creating highlighted card face image.");
/*     */     }
/*     */     
/* 126 */     repaint();
/*     */   }
/*     */   
/*     */   public void unhighlight()
/*     */   {
/* 131 */     this.highlighted = false;
/* 132 */     setFaceUp();
/*     */   }
/*     */   
/*     */   public boolean isHighlighted()
/*     */   {
/* 137 */     return this.highlighted;
/*     */   }
/*     */   
/*     */   public void setFaceUp()
/*     */   {
/* 142 */     this.faceUp = true;
/*     */     
/*     */     try
/*     */     {
/* 146 */       URL localURL = getClass().getResource(this.cardImageString);
/*     */       
/* 148 */       if (localURL != null)
/*     */       {
/* 150 */         this.image = ImageIO.read(localURL);
/*     */       }
/*     */     }
/*     */     catch (IOException localIOException)
/*     */     {
/* 155 */       System.err.println("Error in creating card face image.");
/*     */     }
/*     */   }
/*     */   
/*     */   public void setFaceDown()
/*     */   {
/* 161 */     this.faceUp = false;
/*     */     
/*     */     try
/*     */     {
/* 165 */       URL localURL = getClass().getResource(this.cardBack);
/*     */       
/* 167 */       if (localURL != null)
/*     */       {
/* 169 */         this.image = ImageIO.read(localURL);
/*     */       }
/*     */     }
/*     */     catch (IOException localIOException)
/*     */     {
/* 174 */       System.err.println("Error in creating card back image.");
/*     */     }
/*     */   }
/*     */   
/*     */   public boolean isFaceUp()
/*     */   {
/* 180 */     return this.faceUp;
/*     */   }
/*     */   
/*     */   private boolean isValidSuit(String paramString)
/*     */   {
/* 185 */     return (paramString.equals("Spades")) || (paramString.equals("Diamonds")) || (paramString.equals("Hearts")) || (paramString.equals("Clubs"));
/*     */   }
/*     */   
/*     */ 
/*     */   private void initializeCardImageString()
/*     */   {
/* 191 */     this.cardImageString = "images/cardfaces/";
/* 192 */     this.cardHighlighted = "images/highlightedfaces/";
/*     */     
/* 194 */     if (this.cardSuit.equals("Spades"))
/*     */     {
/* 196 */       this.cardImageString += "s";
/* 197 */       this.cardHighlighted += "s";
/* 198 */       this.cardColor = 0;
/*     */     }
/* 200 */     else if (this.cardSuit.equals("Clubs"))
/*     */     {
/* 202 */       this.cardImageString += "c";
/* 203 */       this.cardHighlighted += "c";
/* 204 */       this.cardColor = 0;
/*     */     }
/* 206 */     else if (this.cardSuit.equals("Diamonds"))
/*     */     {
/* 208 */       this.cardImageString += "d";
/* 209 */       this.cardHighlighted += "d";
/* 210 */       this.cardColor = 1;
/*     */     }
/*     */     else
/*     */     {
/* 214 */       this.cardImageString += "h";
/* 215 */       this.cardHighlighted += "h";
/* 216 */       this.cardColor = 1;
/*     */     }
/*     */     
/* 219 */     if (this.cardNumber == 1)
/*     */     {
/* 221 */       this.cardImageString += "Ace";
/* 222 */       this.cardHighlighted += "Ace";
/*     */     }
/* 224 */     else if (this.cardNumber == 2)
/*     */     {
/* 226 */       this.cardImageString += "Two";
/* 227 */       this.cardHighlighted += "Two";
/*     */     }
/* 229 */     else if (this.cardNumber == 3)
/*     */     {
/* 231 */       this.cardImageString += "Three";
/* 232 */       this.cardHighlighted += "Three";
/*     */     }
/* 234 */     else if (this.cardNumber == 4)
/*     */     {
/* 236 */       this.cardImageString += "Four";
/* 237 */       this.cardHighlighted += "Four";
/*     */     }
/* 239 */     else if (this.cardNumber == 5)
/*     */     {
/* 241 */       this.cardImageString += "Five";
/* 242 */       this.cardHighlighted += "Five";
/*     */     }
/* 244 */     else if (this.cardNumber == 6)
/*     */     {
/* 246 */       this.cardImageString += "Six";
/* 247 */       this.cardHighlighted += "Six";
/*     */     }
/* 249 */     else if (this.cardNumber == 7)
/*     */     {
/* 251 */       this.cardImageString += "Seven";
/* 252 */       this.cardHighlighted += "Seven";
/*     */     }
/* 254 */     else if (this.cardNumber == 8)
/*     */     {
/* 256 */       this.cardImageString += "Eight";
/* 257 */       this.cardHighlighted += "Eight";
/*     */     }
/* 259 */     else if (this.cardNumber == 9)
/*     */     {
/* 261 */       this.cardImageString += "Nine";
/* 262 */       this.cardHighlighted += "Nine";
/*     */     }
/* 264 */     else if (this.cardNumber == 10)
/*     */     {
/* 266 */       this.cardImageString += "Ten";
/* 267 */       this.cardHighlighted += "Ten";
/*     */     }
/* 269 */     else if (this.cardNumber == 11)
/*     */     {
/* 271 */       this.cardImageString += "Jack";
/* 272 */       this.cardHighlighted += "Jack";
/*     */     }
/* 274 */     else if (this.cardNumber == 12)
/*     */     {
/* 276 */       this.cardImageString += "Queen";
/* 277 */       this.cardHighlighted += "Queen";
/*     */     }
/*     */     else
/*     */     {
/* 281 */       this.cardImageString += "King";
/* 282 */       this.cardHighlighted += "King";
/*     */     }
/*     */     
/* 285 */     this.cardImageString += ".png";
/* 286 */     this.cardHighlighted += "H.png";
/*     */   }
/*     */   
/*     */   public BufferedImage getImage()
/*     */   {
/* 291 */     return this.image;
/*     */   }
/*     */   
/*     */   public int getNumber()
/*     */   {
/* 296 */     return this.cardNumber;
/*     */   }
/*     */   
/*     */   public String getSuit()
/*     */   {
/* 301 */     return this.cardSuit;
/*     */   }
/*     */   
/*     */   public int getColor()
/*     */   {
/* 306 */     return this.cardColor;
/*     */   }
/*     */   
/*     */   public int getFullNumber()
/*     */   {
/* 311 */     return this.fullCardNumber;
/*     */   }
/*     */   
/*     */   public String getSource()
/*     */   {
/* 316 */     return this.location;
/*     */   }
/*     */   
/*     */   public void setSource(String paramString)
/*     */   {
/* 321 */     this.location = paramString;
/*     */   }
/*     */   
/*     */ 
/*     */   protected void paintComponent(Graphics paramGraphics)
/*     */   {
/* 327 */     super.paintComponent(paramGraphics);
/* 328 */     paramGraphics.drawImage(this.image, 0, 0, null);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public Card clone()
/*     */   {
/* 335 */     Card localCard = new Card(this.cardSuit, this.cardNumber, this.deckNumber, this.fullCardNumber);
/* 336 */     return localCard;
/*     */   }
/*     */ }


/* Location:              C:\Users\Gema\Desktop\FourRowSolitaire-v.76.jar!\FourRowSolitaire\Card.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */