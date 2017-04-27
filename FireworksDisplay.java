/*     */ package FourRowSolitaire;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import java.awt.Graphics;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import java.util.Random;
/*     */ import javax.swing.JPanel;
/*     */ import javax.swing.Timer;
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
/*     */ public class FireworksDisplay
/*     */   extends JPanel
/*     */   implements ActionListener
/*     */ {
/*     */   public final int NUM_FIREWORKS;
/*     */   public final int FIREWORKS_SIZE;
/*     */   public static final int SET_DELAY = 10;
/*     */   public static final int FIREWORKS_TIME = 30;
/*     */   private final int[] x;
/*     */   private final int[] y;
/*     */   private final Color[] colors;
/*     */   private final int[][] xx;
/*     */   private final int[][] yy;
/*  48 */   private int num = 0;
/*  49 */   private int numSets = 0;
/*  50 */   private int startValue = 0;
/*     */   
/*  52 */   private final Timer timer = new Timer(100, this);
/*  53 */   private final Random random = new Random();
/*     */   
/*     */   public FireworksDisplay(int paramInt1, int paramInt2)
/*     */   {
/*  57 */     this.NUM_FIREWORKS = paramInt1;
/*  58 */     this.FIREWORKS_SIZE = paramInt2;
/*     */     
/*  60 */     this.x = new int[this.NUM_FIREWORKS];
/*  61 */     this.y = new int[this.NUM_FIREWORKS];
/*  62 */     this.colors = new Color[this.NUM_FIREWORKS];
/*     */     
/*  64 */     this.xx = new int[this.NUM_FIREWORKS][this.FIREWORKS_SIZE];
/*  65 */     this.yy = new int[this.NUM_FIREWORKS][this.FIREWORKS_SIZE];
/*     */     
/*  67 */     setBackground(Color.BLACK);
/*     */   }
/*     */   
/*     */   public void restartDisplay()
/*     */   {
/*  72 */     this.timer.stop();
/*     */     
/*  74 */     this.num = 0;
/*     */     int j;
/*  76 */     int k; double d; for (int i = 0; i < this.x.length; i++)
/*     */     {
/*  78 */       this.x[i] = ((int)(Math.random() * 300.0D) + 300);
/*     */       
/*  80 */       for (j = 0; j < this.FIREWORKS_SIZE; j++)
/*     */       {
/*  82 */         k = this.random.nextInt(151);
/*  83 */         d = Math.random();
/*     */         
/*  85 */         if (d <= 0.5D)
/*     */         {
/*  87 */           this.xx[i][j] = (-k);
/*     */         }
/*     */         else
/*     */         {
/*  91 */           this.xx[i][j] = k;
/*     */         }
/*     */       }
/*     */     }
/*     */     
/*  96 */     for (i = 0; i < this.y.length; i++)
/*     */     {
/*  98 */       this.y[i] = ((int)(Math.random() * 200.0D) + 300);
/*     */       
/* 100 */       for (j = 0; j < this.FIREWORKS_SIZE; j++)
/*     */       {
/* 102 */         k = this.random.nextInt(151);
/* 103 */         d = Math.random();
/*     */         
/* 105 */         if (d <= 0.5D)
/*     */         {
/* 107 */           this.yy[i][j] = (-k);
/*     */         }
/*     */         else
/*     */         {
/* 111 */           this.yy[i][j] = k;
/*     */         }
/*     */       }
/*     */     }
/*     */     
/* 116 */     for (i = 0; i < this.colors.length; i++)
/*     */     {
/* 118 */       this.colors[i] = randomColor();
/*     */     }
/*     */     
/* 121 */     this.timer.start();
/*     */   }
/*     */   
/*     */   public Color randomColor()
/*     */   {
/* 126 */     double d = Math.random();
/*     */     
/* 128 */     if (d <= 0.1D)
/*     */     {
/* 130 */       return Color.RED;
/*     */     }
/* 132 */     if (d <= 0.2D)
/*     */     {
/* 134 */       return Color.BLUE;
/*     */     }
/* 136 */     if (d <= 0.3D)
/*     */     {
/* 138 */       return Color.YELLOW;
/*     */     }
/* 140 */     if (d <= 0.4D)
/*     */     {
/* 142 */       return Color.GREEN;
/*     */     }
/* 144 */     if (d <= 0.5D)
/*     */     {
/* 146 */       return Color.ORANGE;
/*     */     }
/* 148 */     if (d <= 0.6D)
/*     */     {
/* 150 */       return Color.CYAN;
/*     */     }
/* 152 */     if (d <= 0.7D)
/*     */     {
/* 154 */       return Color.MAGENTA;
/*     */     }
/* 156 */     if (d <= 0.8D)
/*     */     {
/* 158 */       return Color.PINK;
/*     */     }
/* 160 */     if (d <= 0.9D)
/*     */     {
/* 162 */       return Color.WHITE;
/*     */     }
/*     */     
/*     */ 
/* 166 */     return new Color(153, 50, 205);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void paint(Graphics paramGraphics)
/*     */   {
/* 173 */     super.paint(paramGraphics);
/*     */     
/* 175 */     paramGraphics.setColor(Color.BLACK);
/* 176 */     paramGraphics.fillRect(0, 0, getWidth(), getHeight());
/*     */     
/* 178 */     paramGraphics.setColor(Color.RED);
/* 179 */     paramGraphics.drawString("You Win! -- Click to Close.", 340, 550);
/*     */     int i;
/*     */     int k;
/* 182 */     int j; if (this.numSets < 5)
/*     */     {
/* 184 */       for (i = this.startValue; i < this.startValue + 2; i++)
/*     */       {
/* 186 */         if (this.num < 20)
/*     */         {
/*     */ 
/* 189 */           k = getHeight() - this.num * this.y[i] / 20;
/*     */           
/*     */ 
/* 192 */           if (i % 2 == 0)
/*     */           {
/* 194 */             j = this.num * this.x[i] / 20;
/*     */ 
/*     */           }
/*     */           else
/*     */           {
/* 199 */             j = getWidth() - this.num * this.x[i] / 20;
/*     */           }
/*     */           
/* 202 */           paramGraphics.setColor(this.colors[i]);
/* 203 */           paramGraphics.drawRect(j, k, 5, 5);
/*     */         }
/*     */         else
/*     */         {
/* 207 */           this.num = ((int)(this.num - Math.ceil(20.0D)));
/*     */           
/* 209 */           for (j = 0; j < this.FIREWORKS_SIZE; j++)
/*     */           {
/* 211 */             paramGraphics.setColor(this.colors[i]);
/*     */             
/* 213 */             if (i % 2 == 0)
/*     */             {
/* 215 */               paramGraphics.drawLine(this.x[i], getHeight() - this.y[i], this.x[i] + this.num * this.xx[i][j] / (this.NUM_FIREWORKS / 3), getHeight() - (this.y[i] + this.num * this.yy[i][j] / (this.NUM_FIREWORKS / 3)));
/*     */ 
/*     */             }
/*     */             else
/*     */             {
/* 220 */               paramGraphics.drawLine(getWidth() - this.x[i], getHeight() - this.y[i], getWidth() - (this.x[i] + this.num * this.xx[i][j] / (this.NUM_FIREWORKS / 3)), getHeight() - (this.y[i] + this.num * this.yy[i][j] / (this.NUM_FIREWORKS / 3)));
/*     */             }
/*     */           }
/*     */           
/*     */ 
/* 225 */           this.num = ((int)(this.num + Math.ceil(20.0D)));
/*     */         }
/*     */         
/*     */       }
/*     */       
/* 230 */     } else if (this.numSets < 10)
/*     */     {
/* 232 */       for (i = this.startValue; i < this.startValue + 3; i++)
/*     */       {
/* 234 */         if (this.num < 20)
/*     */         {
/*     */ 
/* 237 */           k = getHeight() - this.num * this.y[i] / 20;
/*     */           
/*     */ 
/* 240 */           if (i % 2 == 0)
/*     */           {
/* 242 */             j = this.num * this.x[i] / 20;
/*     */ 
/*     */           }
/*     */           else
/*     */           {
/* 247 */             j = getWidth() - this.num * this.x[i] / 20;
/*     */           }
/*     */           
/* 250 */           paramGraphics.setColor(this.colors[i]);
/* 251 */           paramGraphics.drawRect(j, k, 5, 5);
/*     */         }
/*     */         else
/*     */         {
/* 255 */           this.num = ((int)(this.num - Math.ceil(20.0D)));
/*     */           
/* 257 */           for (j = 0; j < this.FIREWORKS_SIZE; j++)
/*     */           {
/* 259 */             paramGraphics.setColor(this.colors[i]);
/*     */             
/* 261 */             if (i % 2 == 0)
/*     */             {
/* 263 */               paramGraphics.drawLine(this.x[i], getHeight() - this.y[i], this.x[i] + this.num * this.xx[i][j] / (this.NUM_FIREWORKS / 3), getHeight() - (this.y[i] + this.num * this.yy[i][j] / (this.NUM_FIREWORKS / 3)));
/*     */ 
/*     */             }
/*     */             else
/*     */             {
/* 268 */               paramGraphics.drawLine(getWidth() - this.x[i], getHeight() - this.y[i], getWidth() - (this.x[i] + this.num * this.xx[i][j] / (this.NUM_FIREWORKS / 3)), getHeight() - (this.y[i] + this.num * this.yy[i][j] / (this.NUM_FIREWORKS / 3)));
/*     */             }
/*     */           }
/*     */           
/*     */ 
/* 273 */           this.num = ((int)(this.num + Math.ceil(20.0D)));
/*     */         }
/*     */         
/*     */       }
/*     */       
/* 278 */     } else if (this.numSets < 15)
/*     */     {
/* 280 */       for (i = this.startValue; i < this.startValue + 4; i++)
/*     */       {
/* 282 */         if (this.num < 20)
/*     */         {
/*     */ 
/* 285 */           k = getHeight() - this.num * this.y[i] / 20;
/*     */           
/*     */ 
/* 288 */           if (i % 2 == 0)
/*     */           {
/* 290 */             j = this.num * this.x[i] / 20;
/*     */ 
/*     */           }
/*     */           else
/*     */           {
/* 295 */             j = getWidth() - this.num * this.x[i] / 20;
/*     */           }
/*     */           
/* 298 */           paramGraphics.setColor(this.colors[i]);
/* 299 */           paramGraphics.drawRect(j, k, 5, 5);
/*     */         }
/*     */         else
/*     */         {
/* 303 */           this.num = ((int)(this.num - Math.ceil(20.0D)));
/*     */           
/* 305 */           for (j = 0; j < this.FIREWORKS_SIZE; j++)
/*     */           {
/* 307 */             paramGraphics.setColor(this.colors[i]);
/*     */             
/* 309 */             if (i % 2 == 0)
/*     */             {
/* 311 */               paramGraphics.drawLine(this.x[i], getHeight() - this.y[i], this.x[i] + this.num * this.xx[i][j] / (this.NUM_FIREWORKS / 3), getHeight() - (this.y[i] + this.num * this.yy[i][j] / (this.NUM_FIREWORKS / 3)));
/*     */ 
/*     */             }
/*     */             else
/*     */             {
/* 316 */               paramGraphics.drawLine(getWidth() - this.x[i], getHeight() - this.y[i], getWidth() - (this.x[i] + this.num * this.xx[i][j] / (this.NUM_FIREWORKS / 3)), getHeight() - (this.y[i] + this.num * this.yy[i][j] / (this.NUM_FIREWORKS / 3)));
/*     */             }
/*     */           }
/*     */           
/*     */ 
/* 321 */           this.num = ((int)(this.num + Math.ceil(20.0D)));
/*     */         }
/*     */         
/*     */       }
/*     */       
/* 326 */     } else if (this.numSets < 20)
/*     */     {
/* 328 */       for (i = this.startValue; i < this.startValue + 5; i++)
/*     */       {
/* 330 */         if (this.num < 20)
/*     */         {
/*     */ 
/* 333 */           k = getHeight() - this.num * this.y[i] / 20;
/*     */           
/*     */ 
/* 336 */           if (i % 2 == 0)
/*     */           {
/* 338 */             j = this.num * this.x[i] / 20;
/*     */ 
/*     */           }
/*     */           else
/*     */           {
/* 343 */             j = getWidth() - this.num * this.x[i] / 20;
/*     */           }
/*     */           
/* 346 */           paramGraphics.setColor(this.colors[i]);
/* 347 */           paramGraphics.drawRect(j, k, 5, 5);
/*     */         }
/*     */         else
/*     */         {
/* 351 */           this.num = ((int)(this.num - Math.ceil(20.0D)));
/*     */           
/* 353 */           for (j = 0; j < this.FIREWORKS_SIZE; j++)
/*     */           {
/* 355 */             paramGraphics.setColor(this.colors[i]);
/*     */             
/* 357 */             if (i % 2 == 0)
/*     */             {
/* 359 */               paramGraphics.drawLine(this.x[i], getHeight() - this.y[i], this.x[i] + this.num * this.xx[i][j] / (this.NUM_FIREWORKS / 3), getHeight() - (this.y[i] + this.num * this.yy[i][j] / (this.NUM_FIREWORKS / 3)));
/*     */ 
/*     */             }
/*     */             else
/*     */             {
/* 364 */               paramGraphics.drawLine(getWidth() - this.x[i], getHeight() - this.y[i], getWidth() - (this.x[i] + this.num * this.xx[i][j] / (this.NUM_FIREWORKS / 3)), getHeight() - (this.y[i] + this.num * this.yy[i][j] / (this.NUM_FIREWORKS / 3)));
/*     */             }
/*     */           }
/*     */           
/*     */ 
/* 369 */           this.num = ((int)(this.num + Math.ceil(20.0D)));
/*     */         }
/*     */         
/*     */       }
/*     */       
/* 374 */     } else if (this.numSets < 25)
/*     */     {
/* 376 */       for (i = this.startValue; i < this.startValue + 10; i++)
/*     */       {
/* 378 */         if (this.num < 20)
/*     */         {
/*     */ 
/* 381 */           k = getHeight() - this.num * this.y[i] / 20;
/*     */           
/*     */ 
/* 384 */           if (i % 2 == 0)
/*     */           {
/* 386 */             j = this.num * this.x[i] / 20;
/*     */ 
/*     */           }
/*     */           else
/*     */           {
/* 391 */             j = getWidth() - this.num * this.x[i] / 20;
/*     */           }
/*     */           
/* 394 */           paramGraphics.setColor(this.colors[i]);
/* 395 */           paramGraphics.drawRect(j, k, 5, 5);
/*     */         }
/*     */         else
/*     */         {
/* 399 */           this.num = ((int)(this.num - Math.ceil(20.0D)));
/*     */           
/* 401 */           for (j = 0; j < this.FIREWORKS_SIZE; j++)
/*     */           {
/* 403 */             paramGraphics.setColor(this.colors[i]);
/*     */             
/* 405 */             if (i % 2 == 0)
/*     */             {
/* 407 */               paramGraphics.drawLine(this.x[i], getHeight() - this.y[i], this.x[i] + this.num * this.xx[i][j] / (this.NUM_FIREWORKS / 3), getHeight() - (this.y[i] + this.num * this.yy[i][j] / (this.NUM_FIREWORKS / 3)));
/*     */ 
/*     */             }
/*     */             else
/*     */             {
/* 412 */               paramGraphics.drawLine(getWidth() - this.x[i], getHeight() - this.y[i], getWidth() - (this.x[i] + this.num * this.xx[i][j] / (this.NUM_FIREWORKS / 3)), getHeight() - (this.y[i] + this.num * this.yy[i][j] / (this.NUM_FIREWORKS / 3)));
/*     */             }
/*     */           }
/*     */           
/*     */ 
/* 417 */           this.num = ((int)(this.num + Math.ceil(20.0D)));
/*     */         }
/*     */         
/*     */       }
/*     */       
/* 422 */     } else if (this.numSets < 26)
/*     */     {
/* 424 */       for (i = this.startValue; i < this.x.length; i++)
/*     */       {
/* 426 */         if (this.num < 20)
/*     */         {
/*     */ 
/* 429 */           k = getHeight() - this.num * this.y[i] / 20;
/*     */           
/*     */ 
/* 432 */           if (i % 2 == 0)
/*     */           {
/* 434 */             j = this.num * this.x[i] / 20;
/*     */ 
/*     */           }
/*     */           else
/*     */           {
/* 439 */             j = getWidth() - this.num * this.x[i] / 20;
/*     */           }
/*     */           
/* 442 */           paramGraphics.setColor(this.colors[i]);
/* 443 */           paramGraphics.drawRect(j, k, 5, 5);
/*     */         }
/*     */         else
/*     */         {
/* 447 */           this.num = ((int)(this.num - Math.ceil(20.0D)));
/*     */           
/* 449 */           for (j = 0; j < this.FIREWORKS_SIZE; j++)
/*     */           {
/* 451 */             paramGraphics.setColor(this.colors[i]);
/*     */             
/* 453 */             if (i % 2 == 0)
/*     */             {
/* 455 */               paramGraphics.drawLine(this.x[i], getHeight() - this.y[i], this.x[i] + this.num * this.xx[i][j] / (this.NUM_FIREWORKS / 3), getHeight() - (this.y[i] + this.num * this.yy[i][j] / (this.NUM_FIREWORKS / 3)));
/*     */ 
/*     */             }
/*     */             else
/*     */             {
/* 460 */               paramGraphics.drawLine(getWidth() - this.x[i], getHeight() - this.y[i], getWidth() - (this.x[i] + this.num * this.xx[i][j] / (this.NUM_FIREWORKS / 3)), getHeight() - (this.y[i] + this.num * this.yy[i][j] / (this.NUM_FIREWORKS / 3)));
/*     */             }
/*     */           }
/*     */           
/*     */ 
/* 465 */           this.num = ((int)(this.num + Math.ceil(20.0D)));
/*     */         }
/*     */       }
/*     */     }
/*     */   }
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
/*     */ 
/*     */ 
/*     */ 
/*     */   public void actionPerformed(ActionEvent paramActionEvent)
/*     */   {
/* 731 */     if (paramActionEvent.getSource() == this.timer)
/*     */     {
/* 733 */       if (this.num >= 30)
/*     */       {
/* 735 */         this.num = 0;
/* 736 */         this.numSets += 1;
/*     */         
/* 738 */         this.startValue = this.random.nextInt(this.x.length / 2);
/*     */       }
/*     */       
/* 741 */       this.num += 1;
/*     */       
/* 743 */       if (this.numSets >= 26)
/*     */       {
/* 745 */         this.timer.stop();
/*     */       }
/*     */       else
/*     */       {
/* 749 */         repaint();
/*     */       }
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Gema\Desktop\FourRowSolitaire-v.76.jar!\FourRowSolitaire\FireworksDisplay.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */