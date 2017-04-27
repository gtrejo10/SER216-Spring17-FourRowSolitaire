/*     */ package FourRowSolitaire;
/*     */ 
/*     */ import java.awt.Desktop;
/*     */ import java.awt.HeadlessException;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import java.io.BufferedReader;
/*     */ import java.io.DataInputStream;
/*     */ import java.io.File;
/*     */ import java.io.FileInputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStreamReader;
/*     */ import java.io.PrintStream;
/*     */ import java.net.URI;
/*     */ import java.net.URISyntaxException;
/*     */ import java.net.URL;
/*     */ import java.util.LinkedList;
/*     */ import javax.swing.JMenu;
/*     */ import javax.swing.JMenuBar;
/*     */ import javax.swing.JMenuItem;
/*     */ import javax.swing.JOptionPane;
/*     */ import javax.swing.JTextArea;
/*     */ import javax.swing.KeyStroke;
/*     */ import javax.swing.UIManager;
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
/*     */ public class FourRowSolitaire
/*     */   extends SolitaireBoard
/*     */   implements ActionListener
/*     */ {
/*     */   public static final String version = ".76";
/*  43 */   private final JMenuBar menuBar = new JMenuBar();
/*     */   
/*  45 */   private final JMenu game = new JMenu("Game");
/*  46 */   private final JMenu helpMenu = new JMenu("Help");
/*     */   
/*  48 */   private final JMenuItem newGame = new JMenuItem("New Game");
/*  49 */   private final JMenuItem undo = new JMenuItem("Undo Last Move");
/*  50 */   private final JMenuItem hint = new JMenuItem("Hint");
/*  51 */   private final JMenuItem statistics = new JMenuItem("Statistics");
/*  52 */   private final JMenuItem options = new JMenuItem("Options");
/*  53 */   private final JMenuItem appearance = new JMenuItem("Change Appearance");
/*  54 */   private final JMenuItem topTimes = new JMenuItem("Best Times");
/*  55 */   private final JMenuItem exit = new JMenuItem("Exit");
/*     */   
/*  57 */   private final JMenuItem help = new JMenuItem("View Help");
/*  58 */   private final JMenuItem about = new JMenuItem("About Game");
/*  59 */   private final JMenuItem checkUpdate = new JMenuItem("Check for Updates");
/*     */   
/*     */   public FourRowSolitaire()
/*     */   {
/*  63 */     setup();
/*  64 */     loadData();
/*     */     
/*  66 */     int i = super.getCheckForUpdates();
/*  67 */     int j = (int)(Double.parseDouble(".76") * 100.0D);
/*  68 */     if (i != j)
/*     */     {
/*  70 */       checkForUpdate();
/*     */     }
/*     */   }
/*     */   
/*     */   private void setup()
/*     */   {
/*  76 */     this.game.add(this.newGame);
/*  77 */     this.game.addSeparator();
/*  78 */     this.game.add(this.undo);
/*  79 */     this.game.add(this.hint);
/*  80 */     this.game.addSeparator();
/*  81 */     this.game.add(this.statistics);
/*  82 */     this.game.add(this.options);
/*  83 */     this.game.add(this.appearance);
/*  84 */     this.game.add(this.topTimes);
/*  85 */     this.game.addSeparator();
/*  86 */     this.game.add(this.exit);
/*     */     
/*  88 */     this.newGame.addActionListener(this);
/*  89 */     this.undo.addActionListener(this);
/*  90 */     this.hint.addActionListener(this);
/*  91 */     this.statistics.addActionListener(this);
/*  92 */     this.options.addActionListener(this);
/*  93 */     this.appearance.addActionListener(this);
/*  94 */     this.topTimes.addActionListener(this);
/*  95 */     this.exit.addActionListener(this);
/*     */     
/*  97 */     this.helpMenu.add(this.help);
/*  98 */     this.helpMenu.add(this.about);
/*  99 */     this.helpMenu.add(this.checkUpdate);
/*     */     
/* 101 */     this.help.addActionListener(this);
/* 102 */     this.about.addActionListener(this);
/* 103 */     this.checkUpdate.addActionListener(this);
/*     */     
/* 105 */     this.menuBar.add(this.game);
/* 106 */     this.menuBar.add(this.helpMenu);
/*     */     
/* 108 */     setJMenuBar(this.menuBar);
/*     */     
/* 110 */     this.newGame.setMnemonic('N');
/* 111 */     this.newGame.setAccelerator(KeyStroke.getKeyStroke("F2"));
/* 112 */     this.undo.setMnemonic('u');
/* 113 */     this.undo.setAccelerator(KeyStroke.getKeyStroke(90, 2));
/* 114 */     this.hint.setMnemonic('h');
/* 115 */     this.hint.setAccelerator(KeyStroke.getKeyStroke('h'));
/* 116 */     this.statistics.setMnemonic('s');
/* 117 */     this.statistics.setAccelerator(KeyStroke.getKeyStroke("F4"));
/* 118 */     this.options.setMnemonic('o');
/* 119 */     this.options.setAccelerator(KeyStroke.getKeyStroke("F5"));
/* 120 */     this.appearance.setMnemonic('a');
/* 121 */     this.appearance.setAccelerator(KeyStroke.getKeyStroke("F7"));
/* 122 */     this.topTimes.setMnemonic('b');
/* 123 */     this.topTimes.setAccelerator(KeyStroke.getKeyStroke("F8"));
/* 124 */     this.exit.setMnemonic('x');
/*     */     
/* 126 */     this.help.setMnemonic('v');
/* 127 */     this.help.setAccelerator(KeyStroke.getKeyStroke("F1"));
/* 128 */     this.about.setMnemonic('a');
/*     */   }
/*     */   
/*     */   private void checkForUpdate()
/*     */   {
/*     */     try
/*     */     {
/* 135 */       URL localURL = new URL("http://www.mastadisasta.com/FourRowSolitaire/version.txt");
/* 136 */       BufferedReader localBufferedReader = new BufferedReader(new InputStreamReader(localURL.openStream()));
/*     */       
/* 138 */       String str = localBufferedReader.readLine();
/* 139 */       localBufferedReader.close();
/*     */       
/*     */ 
/* 142 */       if ((!str.contains("DOCTYPE")) && (!".76".equals(str)))
/*     */       {
/* 144 */         String[] arrayOfString = { "Get It", "Not Now", "Don't Remind Me Again" };
/* 145 */         int i = JOptionPane.showOptionDialog(this, "There is a newer version available.", "Newer Version Available", -1, 2, null, arrayOfString, arrayOfString[0]);
/*     */         
/*     */ 
/*     */ 
/* 149 */         if (i == 0)
/*     */         {
/* 151 */           this.checkUpdate.doClick();
/*     */         }
/* 153 */         else if (i == 2)
/*     */         {
/* 155 */           int j = (int)(Double.parseDouble(".76") * 100.0D);
/* 156 */           super.setCheckForUpdates(j);
/*     */         }
/*     */       }
/*     */     }
/*     */     catch (IOException localIOException) {}catch (HeadlessException localHeadlessException) {}catch (NumberFormatException localNumberFormatException) {}
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private void loadData()
/*     */   {
/* 171 */     String str = System.getProperty("user.home") + System.getProperty("file.separator");
/* 172 */     int i = 0;int j = -2;
/*     */     
/* 174 */     int m = 1;int n = 0;int i1 = 3;int i2 = 2;
/* 175 */     int i3 = 0;int i4 = 0;int i5 = 0;int i6 = 1;int i7 = 1;
/* 176 */     int i8 = 2;int i9 = 2;
/* 177 */     int i10 = 1;
/* 178 */     int i11 = 1;
/*     */     Object localObject;
/*     */     try
/*     */     {
/* 182 */       File localFile = new File(str + "frs-statistics.dat");
/* 183 */       localFile.createNewFile();
/* 184 */       localObject = new DataInputStream(new FileInputStream(localFile));
/*     */       
/* 186 */       if (((DataInputStream)localObject).available() > 0)
/*     */       {
/* 188 */         j = ((DataInputStream)localObject).readInt();
/* 189 */         i++;
/*     */       }
/*     */       
/* 192 */       if (j != -2)
/*     */       {
/*     */         int k;
/*     */         
/* 196 */         if (j == -1)
/*     */         {
/*     */ 
/* 199 */           while ((((DataInputStream)localObject).available() > 0) && (i < 44))
/*     */           {
/* 201 */             k = ((DataInputStream)localObject).readInt();
/* 202 */             switch (i) {
/*     */             case 31: 
/* 204 */               i6 = k; break;
/* 205 */             case 32:  m = k; break;
/* 206 */             case 33:  i1 = k; break;
/* 207 */             case 34:  i2 = k; break;
/* 208 */             case 35:  n = k; break;
/* 209 */             case 36:  i4 = k; break;
/* 210 */             case 37:  i5 = k; break;
/* 211 */             case 38:  i7 = k; break;
/* 212 */             case 39:  i8 = k; break;
/* 213 */             case 40:  i9 = k; break;
/* 214 */             case 41:  i10 = k; break;
/* 215 */             case 42:  i3 = k; break;
/* 216 */             case 43:  i11 = k; break;
/*     */             }
/*     */             
/*     */             
/*     */ 
/* 221 */             i++;
/*     */           }
/*     */         }
/*     */         
/*     */ 
/*     */ 
/* 227 */         while ((((DataInputStream)localObject).available() > 0) && (i < 14))
/*     */         {
/* 229 */           k = ((DataInputStream)localObject).readInt();
/* 230 */           switch (i) {
/*     */           case 5: 
/* 232 */             m = k; break;
/* 233 */           case 6:  n = k; break;
/* 234 */           case 7:  i1 = k; break;
/* 235 */           case 8:  i2 = k; break;
/* 236 */           case 9:  i3 = k; break;
/* 237 */           case 10:  i4 = k; break;
/* 238 */           case 11:  i5 = k; break;
/* 239 */           case 12:  i6 = k; break;
/* 240 */           case 13:  i7 = k; break;
/*     */           }
/*     */           
/*     */           
/*     */ 
/* 245 */           i++;
/*     */         }
/*     */       }
/*     */       
/* 249 */       ((DataInputStream)localObject).close();
/*     */     }
/*     */     catch (IOException localIOException1)
/*     */     {
/* 253 */       System.err.println(localIOException1);
/*     */     }
/*     */     
/* 256 */     super.setDeckNumber(i1);
/* 257 */     super.setBackgroundNumber(i2);
/* 258 */     super.setTimerStatus(n);
/* 259 */     super.setNewDrawCount(m);
/* 260 */     super.setWinAnimationStatus(i4);
/* 261 */     super.setWinSoundsStatus(i5);
/* 262 */     super.setDrawCount(i6);
/* 263 */     super.setDifficulty(i8);
/* 264 */     super.setNewDifficulty(i9);
/* 265 */     super.setCheckForUpdates(i11);
/*     */     
/* 267 */     if (i3 == 1)
/*     */     {
/* 269 */       super.setDeckThroughs(i7);
/* 270 */       LinkedList localLinkedList = new LinkedList();
/*     */       
/*     */       try
/*     */       {
/* 274 */         localObject = new File(str + "frs-savedgame.dat");
/* 275 */         ((File)localObject).createNewFile();
/* 276 */         DataInputStream localDataInputStream = new DataInputStream(new FileInputStream((File)localObject));
/*     */         
/* 278 */         while (localDataInputStream.available() > 0)
/*     */         {
/* 280 */           localLinkedList.add(Integer.valueOf(localDataInputStream.readInt()));
/*     */         }
/*     */         
/* 283 */         if (localLinkedList.size() == 67)
/*     */         {
/* 285 */           super.setTimer(((Integer)localLinkedList.pollLast()).intValue());
/* 286 */           super.createBoard(localLinkedList, i10);
/*     */         }
/*     */         else
/*     */         {
/* 290 */           System.err.println("Problem Loading Saved Game (More or Less Than 52 Cards Stored)... Starting New Game");
/* 291 */           super.createBoard(null, 1);
/*     */         }
/*     */       }
/*     */       catch (IOException localIOException2)
/*     */       {
/* 296 */         System.err.println("Problem Loading Saved Game (Unknown Error)... Starting New Game");
/* 297 */         super.createBoard(null, 1);
/*     */       }
/*     */     }
/*     */     else
/*     */     {
/* 302 */       super.createBoard(null, 1);
/*     */     }
/*     */     
/* 305 */     if (j != -1)
/*     */     {
/* 307 */       super.saveOptions();
/*     */     }
/*     */   }
/*     */   
/*     */   public static void main(String[] paramArrayOfString)
/*     */   {
/* 313 */     FourRowSolitaire localFourRowSolitaire = new FourRowSolitaire();
/*     */   }
/*     */   
/*     */   public void actionPerformed(ActionEvent paramActionEvent)
/*     */   {
/* 318 */     if (paramActionEvent.getSource() == this.newGame)
/*     */     {
/* 320 */       super.newGame(0);
/*     */     }
/* 322 */     else if (paramActionEvent.getSource() == this.undo)
/*     */     {
/* 324 */       super.undoMove();
/*     */     }
/* 326 */     else if (paramActionEvent.getSource() == this.hint)
/*     */     {
/* 328 */       super.getHint(); } else { Object localObject;
/*     */       int i;
/* 330 */       int k; int m; int n; int j; if (paramActionEvent.getSource() == this.statistics)
/*     */       {
/* 332 */         localObject = System.getProperty("user.home") + System.getProperty("file.separator") + "frs-statistics.dat";
/*     */         
/*     */ 
/* 335 */         i = 0;
/*     */         
/* 337 */         k = 0;m = 0;n = 0;int i1 = 0;
/* 338 */         int i2 = 0;
/* 339 */         int i3 = 0;int i4 = 0;int i5 = 0;int i6 = 0;
/* 340 */         int i7 = 0;
/* 341 */         int i8 = 0;int i9 = 0;int i10 = 0;int i11 = 0;
/* 342 */         int i12 = 0;
/* 343 */         int i13 = 0;int i14 = 0;int i15 = 0;int i16 = 0;
/* 344 */         int i17 = 0;
/* 345 */         int i18 = 0;int i19 = 0;int i20 = 0;int i21 = 0;
/* 346 */         int i22 = 0;
/* 347 */         int i23 = 0;int i24 = 0;int i25 = 0;int i26 = 0;
/* 348 */         int i27 = 0;
/*     */         
/*     */         try
/*     */         {
/* 352 */           File localFile = new File((String)localObject);
/* 353 */           localFile.createNewFile();
/* 354 */           DataInputStream localDataInputStream = new DataInputStream(new FileInputStream(localFile));
/*     */           
/* 356 */           while ((localDataInputStream.available() > 0) && (i < 31))
/*     */           {
/* 358 */             j = localDataInputStream.readInt();
/* 359 */             switch (i) {
/*     */             case 1: 
/* 361 */               k = j; break;
/* 362 */             case 2:  m = j; break;
/* 363 */             case 3:  n = j; break;
/* 364 */             case 4:  i1 = j; break;
/* 365 */             case 5:  i2 = j; break;
/*     */             case 6: 
/* 367 */               i3 = j; break;
/* 368 */             case 7:  i4 = j; break;
/* 369 */             case 8:  i5 = j; break;
/* 370 */             case 9:  i6 = j; break;
/* 371 */             case 10:  i7 = j; break;
/*     */             case 11: 
/* 373 */               i8 = j; break;
/* 374 */             case 12:  i9 = j; break;
/* 375 */             case 13:  i10 = j; break;
/* 376 */             case 14:  i11 = j; break;
/* 377 */             case 15:  i12 = j; break;
/*     */             case 16: 
/* 379 */               i13 = j; break;
/* 380 */             case 17:  i14 = j; break;
/* 381 */             case 18:  i15 = j; break;
/* 382 */             case 19:  i16 = j; break;
/* 383 */             case 20:  i17 = j; break;
/*     */             case 21: 
/* 385 */               i18 = j; break;
/* 386 */             case 22:  i19 = j; break;
/* 387 */             case 23:  i20 = j; break;
/* 388 */             case 24:  i21 = j; break;
/* 389 */             case 25:  i22 = j; break;
/*     */             case 26: 
/* 391 */               i23 = j; break;
/* 392 */             case 27:  i24 = j; break;
/* 393 */             case 28:  i25 = j; break;
/* 394 */             case 29:  i26 = j; break;
/* 395 */             case 30:  i27 = j; break;
/*     */             }
/*     */             
/*     */             
/*     */ 
/* 400 */             i++;
/*     */           }
/*     */           
/* 403 */           localDataInputStream.close();
/*     */         }
/*     */         catch (IOException localIOException2)
/*     */         {
/* 407 */           System.err.println(localIOException2);
/*     */         }
/*     */         
/*     */ 
/*     */ 
/*     */ 
/*     */         int i28;
/*     */         
/*     */ 
/*     */ 
/* 417 */         if (k == 0)
/*     */         {
/* 419 */           i28 = 0;
/*     */         }
/*     */         else
/*     */         {
/* 423 */           i28 = 100 * m / k;
/*     */         }
/*     */         int i29;
/* 426 */         if (i3 == 0)
/*     */         {
/* 428 */           i29 = 0;
/*     */         }
/*     */         else
/*     */         {
/* 432 */           i29 = 100 * i4 / i3;
/*     */         }
/*     */         int i30;
/* 435 */         if (i8 == 0)
/*     */         {
/* 437 */           i30 = 0;
/*     */         }
/*     */         else
/*     */         {
/* 441 */           i30 = 100 * i9 / i8;
/*     */         }
/*     */         int i31;
/* 444 */         if (i13 == 0)
/*     */         {
/* 446 */           i31 = 0;
/*     */         }
/*     */         else
/*     */         {
/* 450 */           i31 = 100 * i14 / i13;
/*     */         }
/*     */         int i32;
/* 453 */         if (i18 == 0)
/*     */         {
/* 455 */           i32 = 0;
/*     */         }
/*     */         else
/*     */         {
/* 459 */           i32 = 100 * i19 / i18;
/*     */         }
/*     */         int i33;
/* 462 */         if (i23 == 0)
/*     */         {
/* 464 */           i33 = 0;
/*     */         }
/*     */         else
/*     */         {
/* 468 */           i33 = 100 * i24 / i23;
/*     */         }
/*     */         
/* 471 */         JTextArea localJTextArea1 = new JTextArea();
/* 472 */         localJTextArea1.append("One-Card Draw (Easy)\nGames Played: " + k + "\nGames Won: " + m + "\nWin Percentage: " + i28 + "%" + "\n" + "\nBest Streak: " + n + "\nWorst Streak: " + i1 + "\nCurrent Streak: " + i2);
/*     */         
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 482 */         JTextArea localJTextArea2 = new JTextArea();
/* 483 */         localJTextArea2.append("One-Card Draw (Medium)\nGames Played: " + i3 + "\nGames Won: " + i4 + "\nWin Percentage: " + i29 + "%" + "\n" + "\nBest Streak: " + i5 + "\nWorst Streak: " + i6 + "\nCurrent Streak: " + i7);
/*     */         
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 493 */         JTextArea localJTextArea3 = new JTextArea();
/* 494 */         localJTextArea3.append("One-Card Draw (Hard)\nGames Played: " + i8 + "\nGames Won: " + i9 + "\nWin Percentage: " + i30 + "%" + "\n" + "\nBest Streak: " + i10 + "\nWorst Streak: " + i11 + "\nCurrent Streak: " + i12);
/*     */         
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 504 */         JTextArea localJTextArea4 = new JTextArea();
/* 505 */         localJTextArea4.append("Three-Card Draw (Easy)\nGames Played: " + i13 + "\nGames Won: " + i14 + "\nWin Percentage: " + i31 + "%" + "\n" + "\nBest Streak: " + i15 + "\nWorst Streak: " + i16 + "\nCurrent Streak: " + i17);
/*     */         
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 515 */         JTextArea localJTextArea5 = new JTextArea();
/* 516 */         localJTextArea5.append("Three-Card Draw (Medium)\nGames Played: " + i18 + "\nGames Won: " + i19 + "\nWin Percentage: " + i32 + "%" + "\n" + "\nBest Streak: " + i20 + "\nWorst Streak: " + i21 + "\nCurrent Streak: " + i22);
/*     */         
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 526 */         JTextArea localJTextArea6 = new JTextArea();
/* 527 */         localJTextArea6.append("Three-Card Draw (Hard)\nGames Played: " + i23 + "\nGames Won: " + i24 + "\nWin Percentage: " + i33 + "%" + "\n" + "\nBest Streak: " + i25 + "\nWorst Streak: " + i26 + "\nCurrent Streak: " + i27);
/*     */         
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 537 */         localJTextArea1.setOpaque(false);
/* 538 */         localJTextArea1.setBorder(null);
/* 539 */         localJTextArea1.setFont(UIManager.getFont("Label.font"));
/*     */         
/* 541 */         localJTextArea2.setOpaque(false);
/* 542 */         localJTextArea2.setBorder(null);
/* 543 */         localJTextArea2.setFont(UIManager.getFont("Label.font"));
/*     */         
/* 545 */         localJTextArea3.setOpaque(false);
/* 546 */         localJTextArea3.setBorder(null);
/* 547 */         localJTextArea3.setFont(UIManager.getFont("Label.font"));
/*     */         
/* 549 */         localJTextArea4.setOpaque(false);
/* 550 */         localJTextArea4.setBorder(null);
/* 551 */         localJTextArea4.setFont(UIManager.getFont("Label.font"));
/*     */         
/* 553 */         localJTextArea5.setOpaque(false);
/* 554 */         localJTextArea5.setBorder(null);
/* 555 */         localJTextArea5.setFont(UIManager.getFont("Label.font"));
/*     */         
/* 557 */         localJTextArea6.setOpaque(false);
/* 558 */         localJTextArea6.setBorder(null);
/* 559 */         localJTextArea6.setFont(UIManager.getFont("Label.font"));
/*     */         
/* 561 */         ShowStatistics localShowStatistics = new ShowStatistics(this, super.getDrawCount(), localJTextArea1, localJTextArea2, localJTextArea3, localJTextArea4, localJTextArea5, localJTextArea6);
/* 562 */         int i34 = localShowStatistics.getClosingAction();
/* 563 */         localShowStatistics.dispose();
/*     */         
/* 565 */         if (i34 == 1)
/*     */         {
/*     */ 
/* 568 */           super.resetStats();
/*     */ 
/*     */         }
/*     */         
/*     */ 
/*     */ 
/*     */       }
/* 575 */       else if (paramActionEvent.getSource() == this.options)
/*     */       {
/* 577 */         localObject = new ChangeOptions(this, super.getNewDrawCount(), super.getTimerNextGameStatus(), super.getWinAnimationStatus(), super.getWinSoundsStatus(), super.getNewDifficulty());
/* 578 */         i = ((ChangeOptions)localObject).getDrawCount();
/* 579 */         j = ((ChangeOptions)localObject).getTimer();
/* 580 */         k = ((ChangeOptions)localObject).getAnimation();
/* 581 */         m = ((ChangeOptions)localObject).getSounds();
/* 582 */         n = ((ChangeOptions)localObject).getDifficulty();
/*     */         
/* 584 */         if (i != -1)
/*     */         {
/* 586 */           super.setNewDrawCount(i);
/* 587 */           super.setTimerStatus(j);
/* 588 */           super.setWinAnimationStatus(k);
/* 589 */           super.setWinSoundsStatus(m);
/* 590 */           super.setNewDifficulty(n);
/*     */         }
/*     */         
/* 593 */         super.saveOptions();
/*     */       }
/* 595 */       else if (paramActionEvent.getSource() == this.appearance)
/*     */       {
/* 597 */         localObject = new ChangeAppearance(this, super.getDeckNumber(), super.getBackgroundNumber());
/* 598 */         i = ((ChangeAppearance)localObject).getDeckNumber();
/* 599 */         j = ((ChangeAppearance)localObject).getBackgroundNumber();
/* 600 */         ((ChangeAppearance)localObject).dispose();
/*     */         
/* 602 */         if (i != -1)
/*     */         {
/* 604 */           super.setAppearance(i, j);
/*     */         }
/*     */         
/* 607 */         super.saveOptions();
/*     */       }
/* 609 */       else if (paramActionEvent.getSource() == this.topTimes)
/*     */       {
/* 611 */         localObject = new TopTimes(this);
/* 612 */         ((TopTimes)localObject).setVisible(true);
/*     */       }
/* 614 */       else if (paramActionEvent.getSource() == this.exit)
/*     */       {
/* 616 */         this.wl.windowClosing(null);
/*     */ 
/*     */       }
/* 619 */       else if (paramActionEvent.getSource() == this.help)
/*     */       {
/* 621 */         JOptionPane.showMessageDialog(this, "This game is a mixture of FreeCell and normal (Klondike) Solitaire.\n\nSimilarities to FreeCell:\n   -The Cards in the columns are always visible\n   -There are four individual cells at the top\n\nSimilarities to Solitaire:\n   -There is a deck and discard pile to draw cards from\n   -Only Kings may be placed at the top of an empty column\n   -Cards may be removed from the Ace piles and placed back onto the playing field\n   -Any number of cards can be moved in one move (as long as they are stacked as in Solitaire\n\nFeatures unique to this game:\n   -The four individual cells start with cards in them\n   -You may only go through the deck twice on draw one and three times on draw three (difficulty may change)\n   -The obvious: there are only four columns, not 7 or 8 as in Solitaire and FreeCell, respectively", "Help!", 1);
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
/*     */       }
/* 636 */       else if (paramActionEvent.getSource() == this.about)
/*     */       {
/* 638 */         JOptionPane.showMessageDialog(this, "Four Row Solitaire was created and programmed by Matt Stephen.\n\nYou can modify this code in accordance with GPL v3.0.\n\nTo check if there is a newer version, go to fourrow.sourceforge.net.", "About Game", 1);
/*     */ 
/*     */ 
/*     */ 
/*     */       }
/* 643 */       else if (paramActionEvent.getSource() == this.checkUpdate)
/*     */       {
/*     */         try
/*     */         {
/* 647 */           Desktop.getDesktop().browse(new URI("https://sourceforge.net/projects/fourrow"));
/*     */         }
/*     */         catch (URISyntaxException localURISyntaxException) {}catch (IOException localIOException1) {}
/*     */       }
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Gema\Desktop\FourRowSolitaire-v.76.jar!\FourRowSolitaire\FourRowSolitaire.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */