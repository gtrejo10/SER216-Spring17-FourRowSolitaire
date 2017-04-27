/*      */ package FourRowSolitaire;
/*      */ 
/*      */ import java.awt.BorderLayout;
/*      */ import java.awt.FlowLayout;
/*      */ import java.awt.event.ActionEvent;
/*      */ import java.awt.event.ActionListener;
/*      */ import java.awt.event.MouseEvent;
/*      */ import java.awt.event.WindowAdapter;
/*      */ import java.awt.event.WindowEvent;
/*      */ import java.io.DataInputStream;
/*      */ import java.io.DataOutputStream;
/*      */ import java.io.File;
/*      */ import java.io.FileInputStream;
/*      */ import java.io.FileOutputStream;
/*      */ import java.io.IOException;
/*      */ import java.io.PrintStream;
/*      */ import java.util.LinkedList;
/*      */ import javax.swing.ImageIcon;
/*      */ import javax.swing.JFrame;
/*      */ import javax.swing.JLabel;
/*      */ import javax.swing.JOptionPane;
/*      */ import javax.swing.JPanel;
/*      */ import javax.swing.SwingUtilities;
/*      */ import javax.swing.Timer;
/*      */ import javax.swing.event.MouseInputAdapter;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class SolitaireBoard
/*      */   extends JFrame
/*      */ {
/*      */   public static final int GAME_WON = 1;
/*      */   public static final int GAME_LOST = 0;
/*      */   public static final int RESET_STATS = 2;
/*      */   public static final int DO_NOTHING = 3;
/*      */   public static final int GAME_SAVED = 4;
/*   44 */   private int drawCount = 1;
/*      */   
/*      */ 
/*      */ 
/*   48 */   private int newDrawCount = this.drawCount;
/*      */   
/*   50 */   private int backgroundNumber = 2;
/*   51 */   private int deckNumber = 3;
/*   52 */   private Deck deck = new Deck(this.deckNumber);
/*      */   
/*      */ 
/*   55 */   private final Column[] columns = new Column[4];
/*      */   
/*      */ 
/*   58 */   private final DiscardPile discardPile = new DiscardPile(this.drawCount);
/*   59 */   private final DealDeck dealDeck = new DealDeck(this.discardPile, this.drawCount);
/*      */   
/*      */ 
/*   62 */   private final AcePile[] acePiles = new AcePile[4];
/*      */   
/*      */ 
/*   65 */   private final SingleCell[] cells = new SingleCell[4];
/*      */   
/*      */   private SolitairePanel mainPanel;
/*      */   
/*   69 */   private final MyMouseListener ml = new MyMouseListener(null);
/*   70 */   public MyWindowListener wl = new MyWindowListener();
/*      */   
/*   72 */   private final Timer timer = new Timer(1000, new TimerListener(null));
/*   73 */   private final JPanel statusBar = new JPanel(new FlowLayout(1));
/*   74 */   private final JLabel timerLabel = new JLabel("Time: OFF");
/*   75 */   private int timerCount = 0;
/*   76 */   private int timerToRunNextGame = 0;
/*   77 */   private boolean timerToRun = false;
/*      */   
/*   79 */   private int winAnimationStatus = 0;
/*   80 */   private int winSoundsStatus = 0;
/*      */   
/*   82 */   private int difficulty = 2;
/*   83 */   private int newDifficulty = this.difficulty;
/*      */   
/*   85 */   private final LinkedList<CardStack> sourceList = new LinkedList();
/*   86 */   private final LinkedList<CardStack> destinationList = new LinkedList();
/*   87 */   private final LinkedList<Integer> numCards = new LinkedList();
/*   88 */   private final LinkedList<Integer> numCardsInDiscardView = new LinkedList();
/*      */   
/*   90 */   private int checkForUpdates = 1;
/*   91 */   private boolean gameOver = false;
/*      */   
/*      */   private WinScreen winScreen;
/*      */   
/*      */   public SolitaireBoard()
/*      */   {
/*   97 */     setTitle("Four Row Solitaire");
/*   98 */     setSize(800, 700);
/*   99 */     setLocationRelativeTo(null);
/*  100 */     setDefaultCloseOperation(0);
/*  101 */     setResizable(false);
/*  102 */     setIconImage(new ImageIcon(getClass().getResource("images/logo.png")).getImage());
/*      */     
/*  104 */     setVisible(true);
/*      */     
/*  106 */     addWindowListener(this.wl);
/*      */   }
/*      */   
/*      */   public void createBoard(LinkedList<Integer> paramLinkedList, int paramInt)
/*      */   {
/*  111 */     this.mainPanel = new SolitairePanel();
/*  112 */     this.mainPanel.setLayout(new SolitaireLayout());
/*      */     
/*  114 */     this.deck = new Deck(this.deckNumber);
/*  115 */     this.mainPanel.changeBackground(this.backgroundNumber);
/*      */     
/*  117 */     for (int i = 0; i < 4; i++)
/*      */     {
/*  119 */       this.columns[i] = new Column();
/*  120 */       this.columns[i].addMouseListener(this.ml);
/*      */     }
/*      */     
/*      */ 
/*  124 */     this.mainPanel.add(this.columns[0], "Column One");
/*  125 */     this.mainPanel.add(this.columns[1], "Column Two");
/*  126 */     this.mainPanel.add(this.columns[2], "Column Three");
/*  127 */     this.mainPanel.add(this.columns[3], "Column Four");
/*      */     
/*  129 */     for (i = 0; i < 4; i++)
/*      */     {
/*  131 */       this.cells[i] = new SingleCell();
/*  132 */       this.cells[i].addMouseListener(this.ml);
/*      */     }
/*      */     
/*      */ 
/*  136 */     this.mainPanel.add(this.cells[0], "Cell One");
/*  137 */     this.mainPanel.add(this.cells[1], "Cell Two");
/*  138 */     this.mainPanel.add(this.cells[2], "Cell Three");
/*  139 */     this.mainPanel.add(this.cells[3], "Cell Four");
/*      */     
/*  141 */     this.dealDeck.addMouseListener(this.ml);
/*      */     
/*  143 */     this.discardPile.addMouseListener(this.ml);
/*      */     
/*      */ 
/*  146 */     this.mainPanel.add(this.dealDeck, "Deck");
/*  147 */     this.mainPanel.add(this.discardPile, "Discard Pile");
/*      */     
/*  149 */     for (i = 0; i < 4; i++)
/*      */     {
/*  151 */       switch (i) {
/*      */       case 0: 
/*  153 */         this.acePiles[i] = new AcePile("Spades"); break;
/*  154 */       case 1:  this.acePiles[i] = new AcePile("Clubs"); break;
/*  155 */       case 2:  this.acePiles[i] = new AcePile("Diamonds"); break;
/*  156 */       case 3:  this.acePiles[i] = new AcePile("Hearts"); break;
/*      */       }
/*      */       
/*      */       
/*      */ 
/*  161 */       this.acePiles[i].addMouseListener(this.ml);
/*      */     }
/*      */     
/*      */ 
/*  165 */     this.mainPanel.add(this.acePiles[0], "Spaces Ace Pile");
/*  166 */     this.mainPanel.add(this.acePiles[1], "Clubs Ace Pile");
/*  167 */     this.mainPanel.add(this.acePiles[2], "Diamonds Ace Pile");
/*  168 */     this.mainPanel.add(this.acePiles[3], "Hears Ace Pile");
/*      */     
/*  170 */     this.statusBar.add(this.timerLabel);
/*      */     
/*  172 */     JPanel localJPanel = new JPanel(new BorderLayout());
/*  173 */     localJPanel.add(this.mainPanel, "Center");
/*  174 */     localJPanel.add(this.statusBar, "South");
/*  175 */     add(localJPanel);
/*      */     
/*  177 */     if (paramLinkedList != null)
/*      */     {
/*  179 */       dealOutCustomBoard(paramLinkedList, paramInt);
/*      */     }
/*      */     else
/*      */     {
/*  183 */       dealOutBoard();
/*      */     }
/*      */   }
/*      */   
/*      */   private void dealOutBoard()
/*      */   {
/*  189 */     LinkedList localLinkedList = this.deck.getDeck();
/*      */     
/*  191 */     for (int i = 0; i < 6; i++)
/*      */     {
/*  193 */       for (int j = 0; j < 4; j++)
/*      */       {
/*  195 */         Card localCard = (Card)localLinkedList.getLast();
/*  196 */         localLinkedList.removeLast();
/*      */         
/*  198 */         if (i < 5)
/*      */         {
/*  200 */           this.columns[j].addCard(localCard);
/*      */         }
/*      */         else
/*      */         {
/*  204 */           this.cells[j].addCard(localCard);
/*      */         }
/*      */       }
/*      */     }
/*      */     
/*  209 */     this.dealDeck.setDrawCount(this.newDrawCount);
/*  210 */     this.dealDeck.setDifficulty(this.newDifficulty);
/*      */     
/*  212 */     if (this.newDrawCount != this.drawCount)
/*      */     {
/*  214 */       this.drawCount = this.newDrawCount;
/*      */     }
/*      */     
/*  217 */     if (this.newDifficulty != this.difficulty)
/*      */     {
/*  219 */       this.difficulty = this.newDifficulty;
/*      */     }
/*      */     
/*  222 */     this.dealDeck.setDeck(localLinkedList);
/*      */     
/*  224 */     this.timerCount = 0;
/*      */     
/*  226 */     if (this.timerToRunNextGame == 1)
/*      */     {
/*  228 */       this.timer.stop();
/*  229 */       this.timerLabel.setText("Timer: 0");
/*  230 */       this.timerToRun = true;
/*      */     }
/*      */     else
/*      */     {
/*  234 */       this.timer.stop();
/*  235 */       this.timerLabel.setText("Time: OFF");
/*  236 */       this.timerToRun = false;
/*      */     }
/*      */     
/*  239 */     this.mainPanel.revalidate();
/*      */   }
/*      */   
/*      */   private void dealOutCustomBoard(LinkedList<Integer> paramLinkedList, int paramInt)
/*      */   {
/*  244 */     LinkedList localLinkedList = this.deck.getDeck(paramLinkedList);
/*      */     
/*  246 */     int i = 0;
/*  247 */     int j = -1;
/*      */     
/*  249 */     this.dealDeck.setDrawCount(this.drawCount);
/*  250 */     this.dealDeck.setDifficulty(this.difficulty);
/*      */     
/*      */ 
/*  253 */     for (int k = 0; k < paramLinkedList.size(); k++)
/*      */     {
/*  255 */       if (((Integer)paramLinkedList.get(k)).intValue() > 0)
/*      */       {
/*  257 */         j++;
/*      */       }
/*      */       else
/*      */       {
/*  261 */         i++;
/*  262 */         continue;
/*      */       }
/*      */       
/*  265 */       if (i == 0)
/*      */       {
/*  267 */         this.cells[0].addCard((Card)localLinkedList.get(j));
/*      */       }
/*  269 */       else if (i == 1)
/*      */       {
/*  271 */         this.cells[1].addCard((Card)localLinkedList.get(j));
/*      */       }
/*  273 */       else if (i == 2)
/*      */       {
/*  275 */         this.cells[2].addCard((Card)localLinkedList.get(j));
/*      */       }
/*  277 */       else if (i == 3)
/*      */       {
/*  279 */         this.cells[3].addCard((Card)localLinkedList.get(j));
/*      */ 
/*      */       }
/*  282 */       else if (i == 4)
/*      */       {
/*  284 */         this.columns[0].addCard((Card)localLinkedList.get(j));
/*      */       }
/*  286 */       else if (i == 5)
/*      */       {
/*  288 */         this.columns[1].addCard((Card)localLinkedList.get(j));
/*      */       }
/*  290 */       else if (i == 6)
/*      */       {
/*  292 */         this.columns[2].addCard((Card)localLinkedList.get(j));
/*      */       }
/*  294 */       else if (i == 7)
/*      */       {
/*  296 */         this.columns[3].addCard((Card)localLinkedList.get(j));
/*      */ 
/*      */       }
/*  299 */       else if (i == 8)
/*      */       {
/*  301 */         this.acePiles[0].addCard((Card)localLinkedList.get(j));
/*      */       }
/*  303 */       else if (i == 9)
/*      */       {
/*  305 */         this.acePiles[1].addCard((Card)localLinkedList.get(j));
/*      */       }
/*  307 */       else if (i == 10)
/*      */       {
/*  309 */         this.acePiles[2].addCard((Card)localLinkedList.get(j));
/*      */       }
/*  311 */       else if (i == 11)
/*      */       {
/*  313 */         this.acePiles[3].addCard((Card)localLinkedList.get(j));
/*      */ 
/*      */       }
/*  316 */       else if (i == 12)
/*      */       {
/*  318 */         Card localCard = (Card)localLinkedList.get(j);
/*  319 */         localCard.setFaceDown();
/*  320 */         this.dealDeck.addCard(localCard);
/*      */       }
/*  322 */       else if (i == 13)
/*      */       {
/*  324 */         this.discardPile.push((Card)localLinkedList.get(j));
/*      */       }
/*      */     }
/*      */     
/*  328 */     this.discardPile.setView(paramInt);
/*      */     
/*  330 */     if (this.timerToRunNextGame == 1)
/*      */     {
/*  332 */       this.timer.stop();
/*  333 */       this.timerLabel.setText("Time: " + (this.timerCount == -1 ? 0 : this.timerCount));
/*  334 */       this.timerToRun = true;
/*      */     }
/*      */     else
/*      */     {
/*  338 */       this.timer.stop();
/*  339 */       this.timerLabel.setText("Time: OFF");
/*  340 */       this.timerToRun = false;
/*      */     }
/*      */     
/*  343 */     this.mainPanel.revalidate();
/*      */   }
/*      */   
/*      */   private void clearBoard()
/*      */   {
/*  348 */     for (int i = 0; i < 4; i++)
/*      */     {
/*  350 */       while (!this.columns[i].isEmpty())
/*      */       {
/*  352 */         this.columns[i].pop();
/*      */       }
/*      */       
/*  355 */       this.columns[i].repaint();
/*      */     }
/*      */     
/*  358 */     for (i = 0; i < 4; i++)
/*      */     {
/*  360 */       while (!this.cells[i].isEmpty())
/*      */       {
/*  362 */         this.cells[i].pop();
/*      */       }
/*      */       
/*  365 */       this.cells[i].repaint();
/*      */     }
/*      */     
/*  368 */     for (i = 0; i < 4; i++)
/*      */     {
/*  370 */       while (!this.acePiles[i].isEmpty())
/*      */       {
/*  372 */         this.acePiles[i].pop();
/*      */       }
/*      */       
/*  375 */       this.acePiles[i].repaint();
/*      */     }
/*      */     
/*  378 */     while (!this.dealDeck.isEmpty())
/*      */     {
/*  380 */       this.dealDeck.pop();
/*      */     }
/*      */     
/*  383 */     this.dealDeck.repaint();
/*      */     
/*  385 */     while (!this.discardPile.isEmpty())
/*      */     {
/*  387 */       this.discardPile.pop();
/*      */     }
/*      */     
/*  390 */     this.discardPile.repaint();
/*      */   }
/*      */   
/*      */ 
/*      */   public void newGame(int paramInt)
/*      */   {
/*  396 */     if ((paramInt != 1) && (paramInt != 3))
/*      */     {
/*  398 */       int i = JOptionPane.showConfirmDialog(this, "Quitting the current game will result in a loss.\nDo you wish to continue?", "Continue?", -1);
/*      */       
/*      */ 
/*  401 */       if (i == 0)
/*      */       {
/*  403 */         recordGame(0);
/*      */ 
/*      */       }
/*      */       else
/*      */       {
/*  408 */         return;
/*      */       }
/*      */     }
/*      */     
/*  412 */     this.deck = new Deck(this.deckNumber);
/*      */     
/*  414 */     clearBoard();
/*  415 */     this.dealDeck.reset();
/*  416 */     dealOutBoard();
/*      */     
/*  418 */     this.sourceList.clear();
/*  419 */     this.destinationList.clear();
/*  420 */     this.numCards.clear();
/*  421 */     this.numCardsInDiscardView.clear();
/*      */   }
/*      */   
/*      */   public void resetStats()
/*      */   {
/*  426 */     recordGame(2);
/*      */   }
/*      */   
/*      */   public void saveOptions()
/*      */   {
/*  431 */     recordGame(3);
/*      */   }
/*      */   
/*      */   private void recordGame(int paramInt)
/*      */   {
/*  436 */     int i = 0;int j = 0;
/*  437 */     int k = 0;int m = 0;int n = 0;int i1 = 0;
/*  438 */     int i2 = 0;
/*  439 */     int i3 = 0;int i4 = 0;int i5 = 0;int i6 = 0;
/*  440 */     int i7 = 0;
/*  441 */     int i8 = 0;int i9 = 0;int i10 = 0;int i11 = 0;
/*  442 */     int i12 = 0;
/*  443 */     int i13 = 0;int i14 = 0;int i15 = 0;int i16 = 0;
/*  444 */     int i17 = 0;
/*  445 */     int i18 = 0;int i19 = 0;int i20 = 0;int i21 = 0;
/*  446 */     int i22 = 0;
/*  447 */     int i23 = 0;int i24 = 0;int i25 = 0;int i26 = 0;
/*  448 */     int i27 = 0;
/*      */     
/*  450 */     String str = System.getProperty("user.home") + System.getProperty("file.separator");
/*  451 */     File localFile1 = new File(str + "frs-statistics.dat");
/*      */     
/*      */     try
/*      */     {
/*  455 */       localFile1.createNewFile();
/*      */     }
/*      */     catch (IOException localIOException1)
/*      */     {
/*  459 */       System.err.println(localIOException1);
/*      */     }
/*      */     
/*      */     try
/*      */     {
/*  464 */       DataInputStream localDataInputStream = new DataInputStream(new FileInputStream(localFile1));
/*      */       
/*  466 */       if (localDataInputStream.available() > 0)
/*      */       {
/*  468 */         j = localDataInputStream.readInt();
/*  469 */         i++;
/*      */       }
/*      */       
/*  472 */       if (j != -1)
/*      */       {
/*  474 */         i3 = j;
/*      */         
/*  476 */         while ((localDataInputStream.available() > 0) && (i < 5))
/*      */         {
/*  478 */           j = localDataInputStream.readInt();
/*  479 */           switch (i)
/*      */           {
/*      */           case 1: 
/*  482 */             i4 = j; break;
/*  483 */           case 2:  i5 = j; break;
/*  484 */           case 3:  i6 = j; break;
/*  485 */           case 4:  i7 = j; break;
/*      */           }
/*      */           
/*      */           
/*      */ 
/*  490 */           i++;
/*      */         }
/*      */       }
/*      */       
/*      */ 
/*  495 */       while ((localDataInputStream.available() > 0) && (i < 31))
/*      */       {
/*  497 */         j = localDataInputStream.readInt();
/*  498 */         switch (i)
/*      */         {
/*      */         case 1: 
/*  501 */           k = j; break;
/*  502 */         case 2:  m = j; break;
/*  503 */         case 3:  n = j; break;
/*  504 */         case 4:  i1 = j; break;
/*  505 */         case 5:  i2 = j; break;
/*      */         case 6: 
/*  507 */           i3 = j; break;
/*  508 */         case 7:  i4 = j; break;
/*  509 */         case 8:  i5 = j; break;
/*  510 */         case 9:  i6 = j; break;
/*  511 */         case 10:  i7 = j; break;
/*      */         case 11: 
/*  513 */           i8 = j; break;
/*  514 */         case 12:  i9 = j; break;
/*  515 */         case 13:  i10 = j; break;
/*  516 */         case 14:  i11 = j; break;
/*  517 */         case 15:  i12 = j; break;
/*      */         case 16: 
/*  519 */           i13 = j; break;
/*  520 */         case 17:  i14 = j; break;
/*  521 */         case 18:  i15 = j; break;
/*  522 */         case 19:  i16 = j; break;
/*  523 */         case 20:  i17 = j; break;
/*      */         case 21: 
/*  525 */           i18 = j; break;
/*  526 */         case 22:  i19 = j; break;
/*  527 */         case 23:  i20 = j; break;
/*  528 */         case 24:  i21 = j; break;
/*  529 */         case 25:  i22 = j; break;
/*      */         case 26: 
/*  531 */           i23 = j; break;
/*  532 */         case 27:  i24 = j; break;
/*  533 */         case 28:  i25 = j; break;
/*  534 */         case 29:  i26 = j; break;
/*  535 */         case 30:  i27 = j; break;
/*      */         }
/*      */         
/*      */         
/*      */ 
/*  540 */         i++;
/*      */       }
/*      */       
/*      */ 
/*  544 */       localDataInputStream.close();
/*      */     }
/*      */     catch (IOException localIOException2)
/*      */     {
/*  548 */       System.err.println(localIOException2);
/*      */     }
/*      */     
/*      */     try
/*      */     {
/*  553 */       DataOutputStream localDataOutputStream1 = new DataOutputStream(new FileOutputStream(localFile1));
/*      */       
/*  555 */       if (paramInt == 1)
/*      */       {
/*  557 */         if (this.drawCount == 3)
/*      */         {
/*  559 */           if (this.difficulty == 1)
/*      */           {
/*  561 */             i13++;
/*  562 */             i14++;
/*      */             
/*  564 */             if (i17 >= 0)
/*      */             {
/*  566 */               i17++;
/*      */             }
/*      */             else
/*      */             {
/*  570 */               i17 = 1;
/*      */             }
/*      */             
/*  573 */             if (i15 < i17)
/*      */             {
/*  575 */               i15 = i17;
/*      */             }
/*      */           }
/*  578 */           else if (this.difficulty == 2)
/*      */           {
/*  580 */             i18++;
/*  581 */             i19++;
/*      */             
/*  583 */             if (i22 >= 0)
/*      */             {
/*  585 */               i22++;
/*      */             }
/*      */             else
/*      */             {
/*  589 */               i22 = 1;
/*      */             }
/*      */             
/*  592 */             if (i20 < i22)
/*      */             {
/*  594 */               i20 = i22;
/*      */             }
/*      */           }
/*      */           else
/*      */           {
/*  599 */             i23++;
/*  600 */             i24++;
/*      */             
/*  602 */             if (i27 >= 0)
/*      */             {
/*  604 */               i27++;
/*      */             }
/*      */             else
/*      */             {
/*  608 */               i27 = 1;
/*      */             }
/*      */             
/*  611 */             if (i25 < i27)
/*      */             {
/*  613 */               i25 = i27;
/*      */             }
/*      */             
/*      */           }
/*      */           
/*      */         }
/*  619 */         else if (this.difficulty == 1)
/*      */         {
/*  621 */           k++;
/*  622 */           m++;
/*      */           
/*  624 */           if (i2 >= 0)
/*      */           {
/*  626 */             i2++;
/*      */           }
/*      */           else
/*      */           {
/*  630 */             i2 = 1;
/*      */           }
/*      */           
/*  633 */           if (n < i2)
/*      */           {
/*  635 */             n = i2;
/*      */           }
/*      */         }
/*  638 */         else if (this.difficulty == 2)
/*      */         {
/*  640 */           i3++;
/*  641 */           i4++;
/*      */           
/*  643 */           if (i7 >= 0)
/*      */           {
/*  645 */             i7++;
/*      */           }
/*      */           else
/*      */           {
/*  649 */             i7 = 1;
/*      */           }
/*      */           
/*  652 */           if (i5 < i7)
/*      */           {
/*  654 */             i5 = i7;
/*      */           }
/*      */         }
/*      */         else
/*      */         {
/*  659 */           i8++;
/*  660 */           i9++;
/*      */           
/*  662 */           if (i12 >= 0)
/*      */           {
/*  664 */             i12++;
/*      */           }
/*      */           else
/*      */           {
/*  668 */             i12 = 1;
/*      */           }
/*      */           
/*  671 */           if (i10 < i12)
/*      */           {
/*  673 */             i10 = i12;
/*      */           }
/*      */           
/*      */         }
/*      */       }
/*  678 */       else if (paramInt == 2)
/*      */       {
/*  680 */         m = 0;
/*  681 */         k = 0;
/*  682 */         i2 = 0;
/*  683 */         n = 0;
/*  684 */         i1 = 0;
/*      */         
/*  686 */         i4 = 0;
/*  687 */         i3 = 0;
/*  688 */         i7 = 0;
/*  689 */         i5 = 0;
/*  690 */         i6 = 0;
/*      */         
/*  692 */         i9 = 0;
/*  693 */         i8 = 0;
/*  694 */         i12 = 0;
/*  695 */         i10 = 0;
/*  696 */         i11 = 0;
/*      */         
/*  698 */         i14 = 0;
/*  699 */         i13 = 0;
/*  700 */         i17 = 0;
/*  701 */         i15 = 0;
/*  702 */         i16 = 0;
/*      */         
/*  704 */         i19 = 0;
/*  705 */         i18 = 0;
/*  706 */         i22 = 0;
/*  707 */         i20 = 0;
/*  708 */         i21 = 0;
/*      */         
/*  710 */         i24 = 0;
/*  711 */         i23 = 0;
/*  712 */         i27 = 0;
/*  713 */         i25 = 0;
/*  714 */         i26 = 0;
/*      */       }
/*  716 */       else if ((paramInt != 3) && (paramInt != 4))
/*      */       {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  722 */         if (this.drawCount == 3)
/*      */         {
/*  724 */           if (this.difficulty == 1)
/*      */           {
/*  726 */             i13++;
/*      */             
/*  728 */             if (i17 <= 0)
/*      */             {
/*  730 */               i17--;
/*      */             }
/*      */             else
/*      */             {
/*  734 */               i17 = -1;
/*      */             }
/*      */             
/*  737 */             if (i16 > i17)
/*      */             {
/*  739 */               i16 = i17;
/*      */             }
/*      */           }
/*  742 */           else if (this.difficulty == 2)
/*      */           {
/*  744 */             i18++;
/*      */             
/*  746 */             if (i22 <= 0)
/*      */             {
/*  748 */               i22--;
/*      */             }
/*      */             else
/*      */             {
/*  752 */               i22 = -1;
/*      */             }
/*      */             
/*  755 */             if (i21 > i22)
/*      */             {
/*  757 */               i21 = i22;
/*      */             }
/*      */           }
/*      */           else
/*      */           {
/*  762 */             i23++;
/*      */             
/*  764 */             if (i27 <= 0)
/*      */             {
/*  766 */               i27--;
/*      */             }
/*      */             else
/*      */             {
/*  770 */               i27 = -1;
/*      */             }
/*      */             
/*  773 */             if (i26 > i27)
/*      */             {
/*  775 */               i26 = i27;
/*      */             }
/*      */             
/*      */           }
/*      */           
/*      */         }
/*  781 */         else if (this.difficulty == 1)
/*      */         {
/*  783 */           k++;
/*      */           
/*  785 */           if (i2 <= 0)
/*      */           {
/*  787 */             i2--;
/*      */           }
/*      */           else
/*      */           {
/*  791 */             i2 = -1;
/*      */           }
/*      */           
/*  794 */           if (i1 > i2)
/*      */           {
/*  796 */             i1 = i2;
/*      */           }
/*      */         }
/*  799 */         else if (this.difficulty == 2)
/*      */         {
/*  801 */           i3++;
/*      */           
/*  803 */           if (i7 <= 0)
/*      */           {
/*  805 */             i7--;
/*      */           }
/*      */           else
/*      */           {
/*  809 */             i7 = -1;
/*      */           }
/*      */           
/*  812 */           if (i6 > i7)
/*      */           {
/*  814 */             i6 = i7;
/*      */           }
/*      */         }
/*      */         else
/*      */         {
/*  819 */           i8++;
/*      */           
/*  821 */           if (i12 <= 0)
/*      */           {
/*  823 */             i12--;
/*      */           }
/*      */           else
/*      */           {
/*  827 */             i12 = -1;
/*      */           }
/*      */           
/*  830 */           if (i11 > i12)
/*      */           {
/*  832 */             i11 = i12;
/*      */           }
/*      */         }
/*      */       }
/*      */       
/*      */ 
/*  838 */       localDataOutputStream1.writeInt(-1);
/*      */       
/*  840 */       localDataOutputStream1.writeInt(k);
/*  841 */       localDataOutputStream1.writeInt(m);
/*  842 */       localDataOutputStream1.writeInt(n);
/*  843 */       localDataOutputStream1.writeInt(i1);
/*  844 */       localDataOutputStream1.writeInt(i2);
/*      */       
/*  846 */       localDataOutputStream1.writeInt(i3);
/*  847 */       localDataOutputStream1.writeInt(i4);
/*  848 */       localDataOutputStream1.writeInt(i5);
/*  849 */       localDataOutputStream1.writeInt(i6);
/*  850 */       localDataOutputStream1.writeInt(i7);
/*      */       
/*  852 */       localDataOutputStream1.writeInt(i8);
/*  853 */       localDataOutputStream1.writeInt(i9);
/*  854 */       localDataOutputStream1.writeInt(i10);
/*  855 */       localDataOutputStream1.writeInt(i11);
/*  856 */       localDataOutputStream1.writeInt(i12);
/*      */       
/*  858 */       localDataOutputStream1.writeInt(i13);
/*  859 */       localDataOutputStream1.writeInt(i14);
/*  860 */       localDataOutputStream1.writeInt(i15);
/*  861 */       localDataOutputStream1.writeInt(i16);
/*  862 */       localDataOutputStream1.writeInt(i17);
/*      */       
/*  864 */       localDataOutputStream1.writeInt(i18);
/*  865 */       localDataOutputStream1.writeInt(i19);
/*  866 */       localDataOutputStream1.writeInt(i20);
/*  867 */       localDataOutputStream1.writeInt(i21);
/*  868 */       localDataOutputStream1.writeInt(i22);
/*      */       
/*  870 */       localDataOutputStream1.writeInt(i23);
/*  871 */       localDataOutputStream1.writeInt(i24);
/*  872 */       localDataOutputStream1.writeInt(i25);
/*  873 */       localDataOutputStream1.writeInt(i26);
/*  874 */       localDataOutputStream1.writeInt(i27);
/*      */       
/*  876 */       localDataOutputStream1.writeInt(this.drawCount);
/*  877 */       localDataOutputStream1.writeInt(this.newDrawCount);
/*  878 */       localDataOutputStream1.writeInt(this.deckNumber);
/*  879 */       localDataOutputStream1.writeInt(this.backgroundNumber);
/*  880 */       localDataOutputStream1.writeInt(this.timerToRunNextGame);
/*      */       
/*  882 */       localDataOutputStream1.writeInt(this.winAnimationStatus);
/*  883 */       localDataOutputStream1.writeInt(this.winSoundsStatus);
/*  884 */       localDataOutputStream1.writeInt(this.dealDeck.getDeckThroughs());
/*  885 */       localDataOutputStream1.writeInt(this.difficulty);
/*  886 */       localDataOutputStream1.writeInt(this.newDifficulty);
/*  887 */       localDataOutputStream1.writeInt(this.discardPile.getNumViewableCards());
/*      */       
/*  889 */       File localFile2 = new File(str + "frs-savedgame.dat");
/*  890 */       DataOutputStream localDataOutputStream2 = new DataOutputStream(new FileOutputStream(localFile2));
/*      */       
/*  892 */       if (paramInt == 4)
/*      */       {
/*  894 */         localDataOutputStream1.writeInt(1);
/*      */         
/*  896 */         for (int i28 = 0; i28 < this.cells.length; i28++)
/*      */         {
/*  898 */           if (!this.cells[i28].isEmpty())
/*      */           {
/*  900 */             localDataOutputStream2.writeInt(this.cells[i28].peek().getFullNumber());
/*  901 */             localDataOutputStream2.writeInt(-1);
/*      */           }
/*      */           else
/*      */           {
/*  905 */             localDataOutputStream2.writeInt(-1);
/*      */           }
/*      */         }
/*      */         int i29;
/*  909 */         for (i28 = 0; i28 < this.columns.length; i28++)
/*      */         {
/*  911 */           if (!this.columns[i28].isEmpty())
/*      */           {
/*  913 */             for (i29 = 0; i29 < this.columns[i28].length(); i29++)
/*      */             {
/*  915 */               localDataOutputStream2.writeInt(this.columns[i28].getCardAtLocation(i29).getFullNumber());
/*      */             }
/*      */             
/*  918 */             localDataOutputStream2.writeInt(-1);
/*      */           }
/*      */           else
/*      */           {
/*  922 */             localDataOutputStream2.writeInt(-1);
/*      */           }
/*      */         }
/*      */         
/*  926 */         for (i28 = 0; i28 < this.acePiles.length; i28++)
/*      */         {
/*  928 */           if (!this.acePiles[i28].isEmpty())
/*      */           {
/*  930 */             for (i29 = 0; i29 < this.acePiles[i28].length(); i29++)
/*      */             {
/*  932 */               localDataOutputStream2.writeInt(this.acePiles[i28].getCardAtLocation(i29).getFullNumber());
/*      */             }
/*      */             
/*  935 */             localDataOutputStream2.writeInt(-1);
/*      */           }
/*      */           else
/*      */           {
/*  939 */             localDataOutputStream2.writeInt(-1);
/*      */           }
/*      */         }
/*      */         
/*  943 */         if (!this.dealDeck.isEmpty())
/*      */         {
/*  945 */           for (i28 = 0; i28 < this.dealDeck.length(); i28++)
/*      */           {
/*  947 */             localDataOutputStream2.writeInt(this.dealDeck.getCardAtLocation(i28).getFullNumber());
/*      */           }
/*      */           
/*  950 */           localDataOutputStream2.writeInt(-1);
/*      */         }
/*      */         else
/*      */         {
/*  954 */           localDataOutputStream2.writeInt(-1);
/*      */         }
/*      */         
/*  957 */         if (!this.discardPile.isEmpty())
/*      */         {
/*  959 */           for (i28 = 0; i28 < this.discardPile.length(); i28++)
/*      */           {
/*  961 */             localDataOutputStream2.writeInt(this.discardPile.getCardAtLocation(i28).getFullNumber());
/*      */           }
/*      */           
/*  964 */           localDataOutputStream2.writeInt(-1);
/*      */         }
/*      */         else
/*      */         {
/*  968 */           localDataOutputStream2.writeInt(-1);
/*      */         }
/*      */         
/*  971 */         if (this.timerToRun == true)
/*      */         {
/*  973 */           localDataOutputStream2.writeInt(this.timerCount);
/*      */         }
/*      */         else
/*      */         {
/*  977 */           localDataOutputStream2.writeInt(-1);
/*      */         }
/*      */       }
/*      */       else
/*      */       {
/*  982 */         localDataOutputStream1.writeInt(0);
/*  983 */         localFile2.delete();
/*      */       }
/*      */       
/*  986 */       localDataOutputStream1.writeInt(this.checkForUpdates);
/*      */       
/*  988 */       localDataOutputStream1.close();
/*  989 */       localDataOutputStream2.close();
/*      */     }
/*      */     catch (IOException localIOException3)
/*      */     {
/*  993 */       System.err.println(localIOException3);
/*      */     }
/*      */   }
/*      */   
/*      */   public void setAppearance(int paramInt1, int paramInt2)
/*      */   {
/*  999 */     this.deckNumber = paramInt1;
/* 1000 */     this.backgroundNumber = paramInt2;
/* 1001 */     this.mainPanel.changeBackground(this.backgroundNumber);
/*      */   }
/*      */   
/*      */   public int getDrawCount()
/*      */   {
/* 1006 */     return this.drawCount;
/*      */   }
/*      */   
/*      */   public void setDrawCount(int paramInt)
/*      */   {
/* 1011 */     this.drawCount = paramInt;
/*      */     
/* 1013 */     if ((this.drawCount != 3) && (this.drawCount != 1))
/*      */     {
/* 1015 */       this.drawCount = 1;
/*      */     }
/*      */   }
/*      */   
/*      */   public int getNewDrawCount()
/*      */   {
/* 1021 */     return this.newDrawCount;
/*      */   }
/*      */   
/*      */   public void setNewDrawCount(int paramInt)
/*      */   {
/* 1026 */     this.newDrawCount = paramInt;
/*      */     
/* 1028 */     if ((this.newDrawCount != 3) && (this.newDrawCount != 1))
/*      */     {
/* 1030 */       this.newDrawCount = 1;
/*      */     }
/*      */   }
/*      */   
/*      */   public int getDeckNumber()
/*      */   {
/* 1036 */     return this.deckNumber;
/*      */   }
/*      */   
/*      */   public void setDeckNumber(int paramInt)
/*      */   {
/* 1041 */     this.deckNumber = paramInt;
/*      */     
/* 1043 */     if ((this.deckNumber > 3) || (this.deckNumber <= 0))
/*      */     {
/* 1045 */       this.deckNumber = 3;
/*      */     }
/*      */   }
/*      */   
/*      */   public int getBackgroundNumber()
/*      */   {
/* 1051 */     return this.backgroundNumber;
/*      */   }
/*      */   
/*      */   public void setBackgroundNumber(int paramInt)
/*      */   {
/* 1056 */     this.backgroundNumber = paramInt;
/*      */     
/* 1058 */     if ((this.backgroundNumber > 3) || (this.backgroundNumber <= 0))
/*      */     {
/* 1060 */       this.backgroundNumber = 2;
/*      */     }
/*      */   }
/*      */   
/*      */   public int getTimerNextGameStatus()
/*      */   {
/* 1066 */     return this.timerToRunNextGame;
/*      */   }
/*      */   
/*      */   public int getTimerStatus()
/*      */   {
/* 1071 */     if (this.timer.isRunning())
/*      */     {
/* 1073 */       return 1;
/*      */     }
/*      */     
/*      */ 
/* 1077 */     return 0;
/*      */   }
/*      */   
/*      */ 
/*      */   public void setTimerStatus(int paramInt)
/*      */   {
/* 1083 */     if (paramInt == 1)
/*      */     {
/* 1085 */       this.timerToRunNextGame = 1;
/*      */     }
/* 1087 */     else if (paramInt == 0)
/*      */     {
/* 1089 */       this.timerToRunNextGame = 0;
/*      */     }
/*      */     
/* 1092 */     if ((paramInt != 0) && (paramInt != 1))
/*      */     {
/* 1094 */       this.timerToRunNextGame = 0;
/*      */     }
/*      */   }
/*      */   
/*      */   public void setTimer(int paramInt)
/*      */   {
/* 1100 */     this.timerCount = paramInt;
/*      */   }
/*      */   
/*      */   public int getWinAnimationStatus()
/*      */   {
/* 1105 */     return this.winAnimationStatus;
/*      */   }
/*      */   
/*      */   public void setWinAnimationStatus(int paramInt)
/*      */   {
/* 1110 */     this.winAnimationStatus = paramInt;
/*      */     
/* 1112 */     if ((this.winAnimationStatus != 0) && (this.winAnimationStatus != 1))
/*      */     {
/* 1114 */       this.winAnimationStatus = 0;
/*      */     }
/*      */   }
/*      */   
/*      */   public int getWinSoundsStatus()
/*      */   {
/* 1120 */     return this.winSoundsStatus;
/*      */   }
/*      */   
/*      */   public void setWinSoundsStatus(int paramInt)
/*      */   {
/* 1125 */     this.winSoundsStatus = paramInt;
/*      */     
/* 1127 */     if ((this.winSoundsStatus != 0) && (this.winSoundsStatus != 1))
/*      */     {
/* 1129 */       this.winSoundsStatus = 0;
/*      */     }
/*      */   }
/*      */   
/*      */   public int getDifficulty()
/*      */   {
/* 1135 */     return this.difficulty;
/*      */   }
/*      */   
/*      */   public void setDifficulty(int paramInt)
/*      */   {
/* 1140 */     if ((paramInt > 3) || (paramInt < 1))
/*      */     {
/* 1142 */       this.difficulty = 2;
/*      */     }
/*      */     else
/*      */     {
/* 1146 */       this.difficulty = paramInt;
/*      */     }
/*      */   }
/*      */   
/*      */   public int getNewDifficulty()
/*      */   {
/* 1152 */     return this.newDifficulty;
/*      */   }
/*      */   
/*      */   public void setNewDifficulty(int paramInt)
/*      */   {
/* 1157 */     if ((paramInt > 3) || (paramInt < 1))
/*      */     {
/* 1159 */       this.newDifficulty = 2;
/*      */     }
/*      */     else
/*      */     {
/* 1163 */       this.newDifficulty = paramInt;
/*      */     }
/*      */   }
/*      */   
/*      */   public void setDeckThroughs(int paramInt)
/*      */   {
/* 1169 */     this.dealDeck.setDeckThroughs(paramInt);
/*      */   }
/*      */   
/*      */   public void setCheckForUpdates(int paramInt)
/*      */   {
/* 1174 */     this.checkForUpdates = paramInt;
/*      */   }
/*      */   
/*      */   public int getCheckForUpdates()
/*      */   {
/* 1179 */     return this.checkForUpdates;
/*      */   }
/*      */   
/*      */   public synchronized void undoMove()
/*      */   {
/* 1184 */     if (!this.sourceList.isEmpty()) { CardStack localCardStack1;
/*      */       int m;
/*      */       int n;
/* 1187 */       if (this.sourceList.size() > this.destinationList.size())
/*      */       {
/* 1189 */         localCardStack1 = (CardStack)this.sourceList.getLast();
/* 1190 */         this.sourceList.removeLast();
/*      */         
/* 1192 */         int j = ((Integer)this.numCards.getLast()).intValue();
/* 1193 */         this.numCards.removeLast();
/*      */         
/* 1195 */         m = ((Integer)this.numCardsInDiscardView.getLast()).intValue();
/* 1196 */         this.numCardsInDiscardView.removeLast();
/*      */         
/* 1198 */         if (j == 1)
/*      */         {
/* 1200 */           this.discardPile.setView(m);
/* 1201 */           localCardStack1.peek().unhighlight();
/*      */           
/* 1203 */           this.ml.clickedCard = null;
/* 1204 */           this.ml.hasSelected = false;
/* 1205 */           this.ml.singleCardSelected = false;
/* 1206 */           this.ml.temp = null;
/*      */         }
/*      */         else
/*      */         {
/* 1210 */           for (n = 0; n < j; n++)
/*      */           {
/* 1212 */             localCardStack1.getCardAtLocation(localCardStack1.length() - n - 1).unhighlight();
/*      */           }
/*      */           
/* 1215 */           this.ml.clickedCard = null;
/* 1216 */           this.ml.hasSelected = false;
/* 1217 */           this.ml.temp = null;
/*      */         }
/*      */         
/* 1220 */         localCardStack1.repaint();
/*      */       }
/* 1222 */       else if (!(this.sourceList.getLast() instanceof DealDeck))
/*      */       {
/* 1224 */         localCardStack1 = (CardStack)this.sourceList.getLast();
/* 1225 */         CardStack localCardStack2 = (CardStack)this.destinationList.getLast();
/* 1226 */         m = ((Integer)this.numCards.getLast()).intValue();
/* 1227 */         n = ((Integer)this.numCardsInDiscardView.getLast()).intValue();
/*      */         
/* 1229 */         this.sourceList.removeLast();
/* 1230 */         this.destinationList.removeLast();
/* 1231 */         this.numCards.removeLast();
/* 1232 */         this.numCardsInDiscardView.removeLast();
/*      */         
/* 1234 */         if (m == 1)
/*      */         {
/* 1236 */           localCardStack1.addCard(localCardStack2.pop());
/*      */         }
/*      */         else
/*      */         {
/* 1240 */           CardStack localCardStack3 = localCardStack2.undoStack(m);
/* 1241 */           localCardStack1.addStack(localCardStack3);
/*      */         }
/*      */         
/* 1244 */         this.discardPile.setView(n);
/* 1245 */         localCardStack1.repaint();
/* 1246 */         localCardStack2.repaint();
/*      */       } else {
/*      */         int i;
/* 1249 */         if (((this.sourceList.getLast() instanceof DealDeck)) && (!((CardStack)this.destinationList.getLast()).isEmpty()))
/*      */         {
/* 1251 */           i = ((Integer)this.numCards.getLast()).intValue();
/* 1252 */           int k = ((Integer)this.numCardsInDiscardView.getLast()).intValue();
/*      */           
/* 1254 */           this.sourceList.removeLast();
/* 1255 */           this.destinationList.removeLast();
/* 1256 */           this.numCards.removeLast();
/* 1257 */           this.numCardsInDiscardView.removeLast();
/*      */           
/* 1259 */           for (m = 0; m < i; m++)
/*      */           {
/* 1261 */             Card localCard = this.discardPile.undoPop();
/* 1262 */             localCard.setFaceDown();
/* 1263 */             this.dealDeck.addCard(localCard);
/*      */           }
/*      */           
/* 1266 */           this.discardPile.setView(k);
/* 1267 */           this.dealDeck.repaint();
/* 1268 */           this.discardPile.repaint();
/*      */ 
/*      */         }
/* 1271 */         else if ((this.sourceList.getLast() instanceof DealDeck))
/*      */         {
/* 1273 */           this.dealDeck.undoPop();
/*      */           
/* 1275 */           i = ((Integer)this.numCardsInDiscardView.getLast()).intValue();
/* 1276 */           this.discardPile.setView(i);
/*      */           
/* 1278 */           this.discardPile.repaint();
/* 1279 */           this.discardPile.revalidate();
/* 1280 */           this.dealDeck.repaint();
/*      */           
/* 1282 */           this.sourceList.removeLast();
/* 1283 */           this.destinationList.removeLast();
/* 1284 */           this.numCards.removeLast();
/* 1285 */           this.numCardsInDiscardView.removeLast();
/*      */         }
/*      */       }
/*      */     }
/*      */   }
/*      */   
/*      */   public void getHint()
/*      */   {
/* 1293 */     Object localObject1 = new CardStack();
/* 1294 */     Object localObject2 = new CardStack();
/*      */     
/*      */ 
/* 1297 */     LinkedList localLinkedList = new LinkedList();
/* 1298 */     String str1 = "";
/*      */     String str3;
/* 1300 */     for (int i = 0; i < 9; i++)
/*      */     {
/* 1302 */       switch (i)
/*      */       {
/*      */ 
/*      */       case 0: 
/*      */       case 1: 
/*      */       case 2: 
/*      */       case 3: 
/* 1309 */         localObject1 = this.columns[i];
/* 1310 */         str1 = "Column " + (i + 1);
/* 1311 */         break;
/*      */       
/*      */       case 4: 
/*      */       case 5: 
/*      */       case 6: 
/*      */       case 7: 
/* 1317 */         localObject1 = this.cells[(i - 4)];
/* 1318 */         str1 = "Cell " + (i - 3);
/* 1319 */         break;
/*      */       
/*      */       case 8: 
/* 1322 */         localObject1 = this.discardPile;
/* 1323 */         str1 = "the Discard Pile";
/* 1324 */         break;
/*      */       }
/*      */       
/*      */       
/*      */ 
/* 1329 */       if ((localObject1 != null) && (!((CardStack)localObject1).isEmpty()))
/*      */       {
/* 1331 */         CardStack localCardStack = ((CardStack)localObject1).getAvailableCards();
/* 1332 */         str3 = "";
/*      */         
/* 1334 */         for (int k = 0; k < 8; k++)
/*      */         {
/* 1336 */           switch (k)
/*      */           {
/*      */ 
/*      */           case 0: 
/*      */           case 1: 
/*      */           case 2: 
/*      */           case 3: 
/* 1343 */             localObject2 = this.columns[k];
/* 1344 */             str3 = "Column " + (k + 1);
/* 1345 */             break;
/*      */           
/*      */           case 4: 
/*      */           case 5: 
/*      */           case 6: 
/*      */           case 7: 
/* 1351 */             localObject2 = this.acePiles[(k - 4)];
/* 1352 */             str3 = "its Ace Pile";
/* 1353 */             break; }
/*      */           
/*      */           int m;
/*      */           Object localObject3;
/*      */           String str4;
/* 1358 */           if ((localObject2 != null) && (!((CardStack)localObject2).isEmpty()) && (localObject2 != localObject1) && (!(localObject2 instanceof SingleCell)))
/*      */           {
/*      */ 
/* 1361 */             for (m = localCardStack.length() - 1; m >= 0; m--)
/*      */             {
/* 1363 */               localObject3 = localCardStack.getCardAtLocation(m);
/*      */               
/* 1365 */               if ((((localObject2 instanceof AcePile)) && (((Card)localObject3).getSuit().equals(((AcePile)localObject2).getSuit())) && (((Card)localObject3).getNumber() == ((CardStack)localObject2).peek().getNumber() + 1) && (m == 0)) || ((!(localObject2 instanceof AcePile)) && (((Card)localObject3).getColor() != ((CardStack)localObject2).peek().getColor()) && (((Card)localObject3).getNumber() == ((CardStack)localObject2).peek().getNumber() - 1)))
/*      */               {
/*      */ 
/*      */ 
/*      */ 
/* 1370 */                 str4 = "Move the ";
/*      */                 
/* 1372 */                 if (((Card)localObject3).getNumber() == 11)
/*      */                 {
/* 1374 */                   str4 = str4 + "Jack";
/*      */                 }
/* 1376 */                 else if (((Card)localObject3).getNumber() == 12)
/*      */                 {
/* 1378 */                   str4 = str4 + "Queen";
/*      */                 }
/* 1380 */                 else if (((Card)localObject3).getNumber() == 13)
/*      */                 {
/* 1382 */                   str4 = str4 + "King";
/*      */                 }
/* 1384 */                 else if (((Card)localObject3).getNumber() == 1)
/*      */                 {
/* 1386 */                   str4 = str4 + "Ace";
/*      */                 }
/*      */                 else
/*      */                 {
/* 1390 */                   str4 = str4 + ((Card)localObject3).getNumber();
/*      */                 }
/*      */                 
/* 1393 */                 str4 = str4 + " of " + ((Card)localObject3).getSuit() + " in " + str1 + " to the ";
/*      */                 
/* 1395 */                 if (((CardStack)localObject2).peek().getNumber() == 11)
/*      */                 {
/* 1397 */                   str4 = str4 + "Jack";
/*      */                 }
/* 1399 */                 else if (((CardStack)localObject2).peek().getNumber() == 12)
/*      */                 {
/* 1401 */                   str4 = str4 + "Queen";
/*      */                 }
/* 1403 */                 else if (((CardStack)localObject2).peek().getNumber() == 13)
/*      */                 {
/* 1405 */                   str4 = str4 + "King";
/*      */                 }
/* 1407 */                 else if (((CardStack)localObject2).peek().getNumber() == 1)
/*      */                 {
/* 1409 */                   str4 = str4 + "Ace";
/*      */                 }
/*      */                 else
/*      */                 {
/* 1413 */                   str4 = str4 + ((CardStack)localObject2).peek().getNumber();
/*      */                 }
/*      */                 
/* 1416 */                 str4 = str4 + " of " + ((CardStack)localObject2).peek().getSuit() + " in " + str3;
/*      */                 
/* 1418 */                 localLinkedList.add(str4);
/* 1419 */                 break;
/*      */               }
/*      */             }
/*      */           }
/* 1423 */           else if ((localObject2 != null) && (localObject2 != localObject1) && ((localObject2 instanceof Column)) && (((CardStack)localObject2).isEmpty()) && ((((CardStack)localObject1).getBottom().getNumber() != 13) || ((localObject1 instanceof SingleCell))))
/*      */           {
/*      */ 
/* 1426 */             for (m = 0; m < localCardStack.length(); m++)
/*      */             {
/* 1428 */               localObject3 = localCardStack.getCardAtLocation(m);
/*      */               
/* 1430 */               if (((Card)localObject3).getNumber() == 13)
/*      */               {
/* 1432 */                 str4 = "Move the King of " + ((Card)localObject3).getSuit() + " in " + str1 + " to the empty " + str3;
/*      */                 
/*      */ 
/* 1435 */                 localLinkedList.add(str4);
/* 1436 */                 break;
/*      */               }
/*      */             }
/*      */           }
/* 1440 */           else if ((localObject2 != null) && (localObject2 != localObject1) && ((localObject2 instanceof AcePile)) && (((CardStack)localObject2).isEmpty()))
/*      */           {
/*      */ 
/* 1443 */             Card localCard = localCardStack.peek();
/*      */             
/* 1445 */             if ((localCard.getNumber() == 1) && (localCard.getSuit().equals(((AcePile)localObject2).getSuit())))
/*      */             {
/* 1447 */               localObject3 = "Move the Ace of " + localCard.getSuit() + " in " + str1 + " to " + str3;
/*      */               
/*      */ 
/* 1450 */               localLinkedList.add(localObject3);
/* 1451 */               break;
/*      */             }
/*      */           }
/*      */         }
/*      */       }
/*      */     }
/*      */     
/* 1458 */     for (i = 0; i < 4; i++)
/*      */     {
/* 1460 */       if (this.cells[i].isEmpty())
/*      */       {
/* 1462 */         str3 = "Move any available card to Cell " + (i + 1);
/* 1463 */         localLinkedList.add(str3);
/*      */       }
/*      */     }
/*      */     
/* 1467 */     if (!localLinkedList.isEmpty())
/*      */     {
/* 1469 */       String str2 = "";
/*      */       
/* 1471 */       for (int j = 0; j < localLinkedList.size(); j++)
/*      */       {
/* 1473 */         str2 = str2 + (String)localLinkedList.get(j) + "\n";
/*      */       }
/*      */       
/* 1476 */       JOptionPane.showMessageDialog(this, str2, "Hints Galore", 1);
/*      */     }
/*      */     else
/*      */     {
/* 1480 */       JOptionPane.showMessageDialog(this, "There are no moves on the field.\nEither deal more cards or start a new game", "Hints", 1);
/*      */     }
/*      */   }
/*      */   
/*      */   private class MyMouseListener
/*      */     extends MouseInputAdapter
/*      */   {
/* 1487 */     private boolean hasSelected = false;
/* 1488 */     private boolean singleCardSelected = false;
/*      */     
/*      */     private Card clickedCard;
/*      */     
/*      */     private CardStack source;
/*      */     private CardStack destination;
/*      */     private CardStack temp;
/*      */     private Card tempCard;
/* 1496 */     private boolean rightClicked = false;
/*      */     
/*      */     private MyMouseListener() {}
/*      */     
/* 1500 */     private void checkWin() { for (int i = 0; i < 4; i++)
/*      */       {
/* 1502 */         if ((SolitaireBoard.this.acePiles[i].isEmpty()) || (SolitaireBoard.this.acePiles[i].peek().getNumber() != 13))
/*      */         {
/* 1504 */           return;
/*      */         }
/*      */       }
/*      */       
/* 1508 */       SolitaireBoard.this.gameOver = true;
/*      */       
/* 1510 */       if (SolitaireBoard.this.timerToRun)
/*      */       {
/* 1512 */         TopTimes localTopTimes = new TopTimes(SolitaireBoard.this);
/* 1513 */         int k = localTopTimes.IsTopTime(SolitaireBoard.this.timerCount);
/*      */         
/* 1515 */         if (k >= 0)
/*      */         {
/* 1517 */           localTopTimes.setProperties(SolitaireBoard.this.timerCount);
/* 1518 */           localTopTimes.setVisible(true);
/*      */         }
/*      */         else
/*      */         {
/* 1522 */           localTopTimes.dispose();
/*      */         }
/*      */       }
/*      */       
/* 1526 */       if ((SolitaireBoard.this.winAnimationStatus != 0) || (SolitaireBoard.this.winSoundsStatus != 0))
/*      */       {
/* 1528 */         if (SolitaireBoard.this.winScreen != null)
/*      */         {
/* 1530 */           SolitaireBoard.this.winScreen.dispose();
/*      */         }
/*      */         
/* 1533 */         SolitaireBoard.this.winScreen = new WinScreen(SolitaireBoard.this.winAnimationStatus, SolitaireBoard.this.winSoundsStatus);
/*      */       }
/*      */       
/* 1536 */       int j = JOptionPane.showConfirmDialog(SolitaireBoard.this, "Play Again?", "You Won!", 0);
/*      */       
/* 1538 */       if (j == 0)
/*      */       {
/* 1540 */         SolitaireBoard.this.recordGame(1);
/* 1541 */         SolitaireBoard.this.newGame(1);
/*      */       }
/*      */       else
/*      */       {
/* 1545 */         SolitaireBoard.this.recordGame(1);
/* 1546 */         System.exit(0);
/*      */       }
/*      */       
/* 1549 */       SolitaireBoard.this.gameOver = false;
/*      */     }
/*      */     
/*      */ 
/*      */     public void mousePressed(MouseEvent paramMouseEvent)
/*      */     {
/* 1555 */       if (SwingUtilities.isRightMouseButton(paramMouseEvent))
/*      */       {
/* 1557 */         if ((paramMouseEvent.getSource() == SolitaireBoard.this.discardPile) && ((SolitaireBoard.this.discardPile.getNumViewableCards() == 1) || ((SolitaireBoard.this.discardPile.getNumViewableCards() == 0) && (!SolitaireBoard.this.discardPile.isEmpty()))))
/*      */         {
/*      */ 
/* 1560 */           this.tempCard = SolitaireBoard.this.discardPile.pop();
/* 1561 */           SolitaireBoard.this.discardPile.repaint();
/* 1562 */           this.rightClicked = true;
/*      */         }
/* 1564 */         else if ((paramMouseEvent.getSource() instanceof Column))
/*      */         {
/* 1566 */           Column localColumn = (Column)paramMouseEvent.getSource();
/* 1567 */           this.tempCard = localColumn.previewCard(paramMouseEvent.getPoint());
/* 1568 */           this.rightClicked = true;
/*      */         }
/*      */       }
/*      */     }
/*      */     
/*      */ 
/*      */     public void mouseReleased(MouseEvent paramMouseEvent)
/*      */     {
/* 1576 */       if ((SwingUtilities.isRightMouseButton(paramMouseEvent)) && (this.tempCard != null))
/*      */       {
/* 1578 */         if (paramMouseEvent.getSource() == SolitaireBoard.this.discardPile)
/*      */         {
/* 1580 */           SolitaireBoard.this.discardPile.push(this.tempCard);
/* 1581 */           SolitaireBoard.this.discardPile.repaint();
/* 1582 */           this.rightClicked = false;
/* 1583 */           this.tempCard = null;
/*      */         }
/* 1585 */         else if ((paramMouseEvent.getSource() instanceof Column))
/*      */         {
/* 1587 */           Column localColumn = (Column)paramMouseEvent.getSource();
/* 1588 */           localColumn.stopPreview();
/* 1589 */           this.rightClicked = false;
/* 1590 */           this.tempCard = null;
/*      */         }
/*      */       }
/*      */     }
/*      */     
/*      */ 
/*      */     public void mouseClicked(MouseEvent paramMouseEvent)
/*      */     {
/* 1598 */       SolitaireBoard.this.discardPile.repaint();
/* 1599 */       SolitaireBoard.this.discardPile.revalidate();
/*      */       
/* 1601 */       if ((!SolitaireBoard.this.timer.isRunning()) && (SolitaireBoard.this.timerToRun))
/*      */       {
/* 1603 */         SolitaireBoard.this.timer.start();
/*      */       }
/*      */       
/* 1606 */       if ((!SwingUtilities.isLeftMouseButton(paramMouseEvent)) || (this.rightClicked)) {
/*      */         return;
/*      */       }
/*      */       
/*      */       int i;
/* 1611 */       if ((paramMouseEvent.getClickCount() == 2) && (this.hasSelected) && (this.singleCardSelected))
/*      */       {
/* 1613 */         if (this.source.peek().getNumber() == 1) {
/*      */           Card localCard1;
/* 1615 */           if (this.source.peek().getSuit().equals("Spades"))
/*      */           {
/* 1617 */             localCard1 = this.source.pop();
/* 1618 */             localCard1.unhighlight();
/* 1619 */             SolitaireBoard.this.acePiles[0].push(localCard1);
/* 1620 */             SolitaireBoard.this.destinationList.add(SolitaireBoard.this.acePiles[0]);
/*      */           }
/* 1622 */           else if (this.source.peek().getSuit().equals("Clubs"))
/*      */           {
/* 1624 */             localCard1 = this.source.pop();
/* 1625 */             localCard1.unhighlight();
/* 1626 */             SolitaireBoard.this.acePiles[1].push(localCard1);
/* 1627 */             SolitaireBoard.this.destinationList.add(SolitaireBoard.this.acePiles[1]);
/*      */           }
/* 1629 */           else if (this.source.peek().getSuit().equals("Diamonds"))
/*      */           {
/* 1631 */             localCard1 = this.source.pop();
/* 1632 */             localCard1.unhighlight();
/* 1633 */             SolitaireBoard.this.acePiles[2].push(localCard1);
/* 1634 */             SolitaireBoard.this.destinationList.add(SolitaireBoard.this.acePiles[2]);
/*      */           }
/*      */           else
/*      */           {
/* 1638 */             localCard1 = this.source.pop();
/* 1639 */             localCard1.unhighlight();
/* 1640 */             SolitaireBoard.this.acePiles[3].push(localCard1);
/* 1641 */             SolitaireBoard.this.destinationList.add(SolitaireBoard.this.acePiles[3]);
/*      */           }
/*      */           
/* 1644 */           this.hasSelected = false;
/* 1645 */           this.source.repaint();
/* 1646 */           SolitaireBoard.this.repaint(); return;
/*      */         }
/*      */         
/*      */         Card localCard2;
/* 1650 */         for (i = 0; i < 4; i++)
/*      */         {
/* 1652 */           if ((!SolitaireBoard.this.acePiles[i].isEmpty()) && (this.source.peek().getSuit().equals(SolitaireBoard.this.acePiles[i].peek().getSuit())) && (this.source.peek().getNumber() == SolitaireBoard.this.acePiles[i].peek().getNumber() + 1))
/*      */           {
/*      */ 
/* 1655 */             localCard2 = this.source.pop();
/* 1656 */             localCard2.unhighlight();
/* 1657 */             SolitaireBoard.this.acePiles[i].push(localCard2);
/*      */             
/* 1659 */             SolitaireBoard.this.destinationList.add(SolitaireBoard.this.acePiles[i]);
/* 1660 */             this.hasSelected = false;
/*      */             
/* 1662 */             this.source.repaint();
/* 1663 */             SolitaireBoard.this.repaint();
/*      */             
/* 1665 */             if (localCard2.getNumber() == 13)
/*      */             {
/* 1667 */               checkWin();
/*      */             }
/*      */             
/* 1670 */             return;
/*      */           }
/*      */         }
/*      */         
/* 1674 */         for (i = 0; i < 4; i++)
/*      */         {
/* 1676 */           if (SolitaireBoard.this.cells[i].isEmpty())
/*      */           {
/* 1678 */             localCard2 = this.source.pop();
/* 1679 */             localCard2.unhighlight();
/* 1680 */             SolitaireBoard.this.cells[i].push(localCard2);
/*      */             
/* 1682 */             SolitaireBoard.this.destinationList.add(SolitaireBoard.this.cells[i]);
/* 1683 */             this.hasSelected = false;
/*      */             
/* 1685 */             this.source.repaint();
/* 1686 */             SolitaireBoard.this.repaint();
/* 1687 */             return;
/*      */           }
/*      */         }
/*      */         
/* 1691 */         this.source.peek().unhighlight();
/*      */         
/* 1693 */         this.source.repaint();
/* 1694 */         SolitaireBoard.this.repaint();
/* 1695 */         return;
/*      */       }
/* 1697 */       if ((paramMouseEvent.getClickCount() == 2) && (this.hasSelected))
/*      */       {
/* 1699 */         this.hasSelected = false;
/*      */         
/* 1701 */         if (this.temp.length() > 0)
/*      */         {
/* 1703 */           for (i = 0; i < this.temp.length(); i++)
/*      */           {
/* 1705 */             this.source.getCardAtLocation(this.source.length() - i - 1).unhighlight();
/*      */           }
/*      */         }
/*      */         
/* 1709 */         SolitaireBoard.this.sourceList.removeLast();
/* 1710 */         SolitaireBoard.this.numCardsInDiscardView.removeLast();
/* 1711 */         SolitaireBoard.this.numCards.removeLast();
/*      */ 
/*      */       }
/* 1714 */       else if (((!this.hasSelected) && (paramMouseEvent.getClickCount() == 1)) || ((paramMouseEvent.getSource() instanceof DealDeck)))
/*      */       {
/* 1716 */         this.source = ((CardStack)paramMouseEvent.getSource());
/*      */         
/* 1718 */         if ((this.source instanceof DealDeck))
/*      */         {
/* 1720 */           if (this.hasSelected)
/*      */           {
/* 1722 */             this.hasSelected = false;
/*      */             
/* 1724 */             if (this.temp.length() > 0)
/*      */             {
/* 1726 */               for (i = 0; i < this.temp.length(); i++)
/*      */               {
/* 1728 */                 ((CardStack)SolitaireBoard.this.sourceList.getLast()).getCardAtLocation(((CardStack)SolitaireBoard.this.sourceList.getLast()).length() - i - 1).unhighlight();
/*      */               }
/*      */               
/*      */             }
/*      */             else {
/* 1733 */               ((CardStack)SolitaireBoard.this.sourceList.getLast()).peek().unhighlight();
/*      */             }
/*      */             
/* 1736 */             ((CardStack)SolitaireBoard.this.sourceList.getLast()).repaint();
/* 1737 */             SolitaireBoard.this.repaint();
/* 1738 */             SolitaireBoard.this.sourceList.removeLast();
/* 1739 */             SolitaireBoard.this.numCardsInDiscardView.removeLast();
/* 1740 */             SolitaireBoard.this.numCards.removeLast();
/*      */           }
/*      */           
/* 1743 */           SolitaireBoard.this.numCardsInDiscardView.add(Integer.valueOf(SolitaireBoard.this.discardPile.getNumViewableCards()));
/* 1744 */           this.clickedCard = this.source.pop();
/*      */           
/* 1746 */           if (this.clickedCard != null)
/*      */           {
/* 1748 */             SolitaireBoard.this.sourceList.add(SolitaireBoard.this.dealDeck);
/* 1749 */             SolitaireBoard.this.destinationList.add(SolitaireBoard.this.discardPile);
/* 1750 */             SolitaireBoard.this.numCards.add(Integer.valueOf(SolitaireBoard.this.discardPile.getNumViewableCards()));
/*      */           }
/* 1752 */           else if (SolitaireBoard.this.dealDeck.hasDealsLeft())
/*      */           {
/* 1754 */             SolitaireBoard.this.sourceList.add(SolitaireBoard.this.dealDeck);
/* 1755 */             SolitaireBoard.this.destinationList.add(SolitaireBoard.this.discardPile);
/* 1756 */             SolitaireBoard.this.numCards.add(Integer.valueOf(0));
/*      */           }
/*      */           else
/*      */           {
/* 1760 */             SolitaireBoard.this.numCardsInDiscardView.removeLast();
/*      */           }
/*      */           
/* 1763 */           return;
/*      */         }
/*      */         
/* 1766 */         SolitaireBoard.this.numCardsInDiscardView.add(Integer.valueOf(SolitaireBoard.this.discardPile.getNumViewableCards()));
/* 1767 */         this.clickedCard = this.source.getCardAtLocation(paramMouseEvent.getPoint());
/*      */         
/* 1769 */         if (this.clickedCard != null)
/*      */         {
/* 1771 */           this.hasSelected = true;
/* 1772 */           this.temp = this.source.getStack(this.clickedCard);
/*      */           
/* 1774 */           SolitaireBoard.this.sourceList.add(this.source);
/* 1775 */           SolitaireBoard.this.numCards.add(Integer.valueOf(this.temp.length()));
/*      */           
/* 1777 */           this.singleCardSelected = (this.temp.length() <= 1);
/*      */         }
/*      */         else
/*      */         {
/* 1781 */           SolitaireBoard.this.numCardsInDiscardView.removeLast();
/* 1782 */           this.hasSelected = false;
/*      */         }
/*      */         
/*      */ 
/*      */       }
/* 1787 */       else if ((paramMouseEvent.getClickCount() == 1) && (this.hasSelected))
/*      */       {
/* 1789 */         this.destination = ((CardStack)paramMouseEvent.getSource());
/*      */         Object localObject;
/* 1791 */         if (this.singleCardSelected)
/*      */         {
/* 1793 */           if (this.destination.isValidMove(this.clickedCard))
/*      */           {
/* 1795 */             localObject = this.source.pop();
/* 1796 */             ((Card)localObject).unhighlight();
/* 1797 */             this.destination.push((Card)localObject);
/*      */             
/*      */ 
/* 1800 */             SolitaireBoard.this.destinationList.add(this.destination);
/*      */             
/* 1802 */             if (((this.destination instanceof AcePile)) && (this.clickedCard.getNumber() == 13))
/*      */             {
/* 1804 */               SolitaireBoard.this.repaint();
/* 1805 */               checkWin();
/*      */             }
/*      */             
/*      */ 
/*      */           }
/*      */           else
/*      */           {
/* 1812 */             this.source.peek().unhighlight();
/*      */             
/*      */ 
/* 1815 */             SolitaireBoard.this.sourceList.removeLast();
/* 1816 */             SolitaireBoard.this.numCards.removeLast();
/* 1817 */             SolitaireBoard.this.numCardsInDiscardView.removeLast();
/*      */           }
/*      */           
/*      */ 
/*      */         }
/* 1822 */         else if (this.destination.isValidMove(this.temp))
/*      */         {
/* 1824 */           localObject = new CardStack();
/*      */           
/* 1826 */           for (int k = this.temp.length(); k > 0; k--)
/*      */           {
/* 1828 */             Card localCard3 = this.source.pop();
/* 1829 */             localCard3.unhighlight();
/*      */             
/* 1831 */             ((CardStack)localObject).push(localCard3);
/*      */           }
/*      */           
/* 1834 */           this.destination.push((CardStack)localObject);
/*      */           
/*      */ 
/* 1837 */           SolitaireBoard.this.destinationList.add(this.destination);
/*      */         }
/*      */         else
/*      */         {
/* 1841 */           for (int j = this.temp.length() - 1; j >= 0; j--)
/*      */           {
/* 1843 */             this.source.getCardAtLocation(this.source.length() - j - 1).unhighlight();
/*      */           }
/*      */           
/*      */ 
/* 1847 */           SolitaireBoard.this.sourceList.removeLast();
/* 1848 */           SolitaireBoard.this.numCards.removeLast();
/* 1849 */           SolitaireBoard.this.numCardsInDiscardView.removeLast();
/*      */         }
/*      */         
/*      */ 
/* 1853 */         this.singleCardSelected = false;
/* 1854 */         this.hasSelected = false;
/* 1855 */         this.temp = null;
/* 1856 */         this.clickedCard = null;
/*      */       }
/*      */       
/* 1859 */       SolitaireBoard.this.repaint();
/*      */     }
/*      */   }
/*      */   
/*      */   private class TimerListener implements ActionListener {
/*      */     private TimerListener() {}
/*      */     
/*      */     public void actionPerformed(ActionEvent paramActionEvent) {
/* 1867 */       if ((paramActionEvent.getSource() == SolitaireBoard.this.timer) && (!SolitaireBoard.this.gameOver))
/*      */       {
/* 1869 */         SolitaireBoard.access$908(SolitaireBoard.this);
/* 1870 */         SolitaireBoard.this.timerLabel.setText("Time: " + SolitaireBoard.this.timerCount);
/* 1871 */         SolitaireBoard.this.statusBar.repaint();
/*      */       }
/*      */     }
/*      */   }
/*      */   
/*      */   public class MyWindowListener extends WindowAdapter
/*      */   {
/*      */     public MyWindowListener() {}
/*      */     
/*      */     public void windowClosing(WindowEvent paramWindowEvent) {
/* 1881 */       int i = JOptionPane.showConfirmDialog(SolitaireBoard.this, "Closing without saving will result in a loss, would you like to save the current game?", "Save Game?", 0);
/*      */       
/*      */ 
/* 1884 */       if (i == 0)
/*      */       {
/* 1886 */         SolitaireBoard.this.recordGame(4);
/* 1887 */         System.exit(0);
/*      */       }
/* 1889 */       else if (i == 1)
/*      */       {
/* 1891 */         SolitaireBoard.this.recordGame(0);
/* 1892 */         System.exit(0);
/*      */       }
/*      */     }
/*      */   }
/*      */ }


/* Location:              C:\Users\Gema\Desktop\FourRowSolitaire-v.76.jar!\FourRowSolitaire\SolitaireBoard.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */