/*     */ package FourRowSolitaire;
/*     */ 
/*     */ import java.awt.BorderLayout;
/*     */ import java.awt.Dialog.ModalityType;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.FlowLayout;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import javax.swing.ButtonGroup;
/*     */ import javax.swing.JButton;
/*     */ import javax.swing.JCheckBox;
/*     */ import javax.swing.JDialog;
/*     */ import javax.swing.JFrame;
/*     */ import javax.swing.JOptionPane;
/*     */ import javax.swing.JPanel;
/*     */ import javax.swing.JRadioButton;
/*     */ import javax.swing.border.TitledBorder;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ChangeOptions
/*     */   extends JDialog
/*     */   implements ActionListener
/*     */ {
/*  36 */   private int drawCount = 1;
/*     */   
/*  38 */   private final JRadioButton drawOne = new JRadioButton("Draw One");
/*  39 */   private final JRadioButton drawThree = new JRadioButton("Draw Three");
/*     */   
/*  41 */   private final JCheckBox timerCheck = new JCheckBox("Timer");
/*  42 */   private int timer = 0;
/*     */   
/*  44 */   private final JCheckBox winAnimationCheck = new JCheckBox("Win Animation");
/*  45 */   private int animation = 0;
/*  46 */   private final JCheckBox winSoundsCheck = new JCheckBox("Win Sounds");
/*  47 */   private int sounds = 0;
/*     */   
/*  49 */   private int difficulty = 2;
/*     */   
/*  51 */   private final JRadioButton easy = new JRadioButton("Easy");
/*  52 */   private final JRadioButton medium = new JRadioButton("Medium", true);
/*  53 */   private final JRadioButton hard = new JRadioButton("Hard");
/*     */   
/*  55 */   private final JButton ok = new JButton("Accept Options");
/*     */   
/*  57 */   private boolean exited = true;
/*     */   
/*     */   public ChangeOptions(JFrame paramJFrame, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5)
/*     */   {
/*  61 */     setTitle("Options");
/*  62 */     setSize(340, 190);
/*  63 */     setDefaultCloseOperation(1);
/*  64 */     setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
/*  65 */     setLocationRelativeTo(paramJFrame);
/*     */     
/*  67 */     this.drawCount = paramInt1;
/*  68 */     this.timer = paramInt2;
/*  69 */     this.animation = paramInt3;
/*  70 */     this.sounds = paramInt4;
/*  71 */     this.difficulty = paramInt5;
/*  72 */     setup();
/*     */     
/*  74 */     setVisible(true);
/*     */   }
/*     */   
/*     */   private void setup()
/*     */   {
/*  79 */     ButtonGroup localButtonGroup1 = new ButtonGroup();
/*  80 */     localButtonGroup1.add(this.drawOne);
/*  81 */     localButtonGroup1.add(this.drawThree);
/*     */     
/*  83 */     JPanel localJPanel1 = new JPanel(new FlowLayout(0));
/*  84 */     localJPanel1.setBorder(new TitledBorder("Card Draw"));
/*  85 */     localJPanel1.add(this.drawOne);
/*  86 */     localJPanel1.add(this.drawThree);
/*     */     
/*  88 */     localJPanel1.setMaximumSize(new Dimension(110, 80));
/*  89 */     localJPanel1.setMinimumSize(localJPanel1.getMaximumSize());
/*  90 */     localJPanel1.setPreferredSize(localJPanel1.getMaximumSize());
/*     */     
/*  92 */     if (this.drawCount == 3)
/*     */     {
/*  94 */       this.drawThree.setSelected(true);
/*  95 */       this.easy.setToolTipText("4 times through deck");
/*  96 */       this.medium.setToolTipText("3 times through deck");
/*  97 */       this.hard.setToolTipText("2 times through deck");
/*     */     }
/*     */     else
/*     */     {
/* 101 */       this.drawOne.setSelected(true);
/* 102 */       this.easy.setToolTipText("3 times through deck");
/* 103 */       this.medium.setToolTipText("2 times through deck");
/* 104 */       this.hard.setToolTipText("1 time through deck");
/*     */     }
/*     */     
/* 107 */     JPanel localJPanel2 = new JPanel(new FlowLayout(0));
/* 108 */     localJPanel2.setBorder(new TitledBorder("Extra Settings"));
/* 109 */     localJPanel2.add(this.timerCheck);
/* 110 */     localJPanel2.add(this.winAnimationCheck);
/* 111 */     localJPanel2.add(this.winSoundsCheck);
/*     */     
/* 113 */     localJPanel2.setMaximumSize(new Dimension(120, 80));
/* 114 */     localJPanel2.setMinimumSize(localJPanel2.getMaximumSize());
/* 115 */     localJPanel2.setPreferredSize(localJPanel2.getMaximumSize());
/*     */     
/* 117 */     if (this.timer == 1)
/*     */     {
/* 119 */       this.timerCheck.setSelected(true);
/*     */     }
/*     */     else
/*     */     {
/* 123 */       this.timerCheck.setSelected(false);
/*     */     }
/*     */     
/* 126 */     if (this.animation == 1)
/*     */     {
/* 128 */       this.winAnimationCheck.setSelected(true);
/*     */     }
/*     */     else
/*     */     {
/* 132 */       this.winAnimationCheck.setSelected(false);
/*     */     }
/*     */     
/* 135 */     if (this.sounds == 1)
/*     */     {
/* 137 */       this.winSoundsCheck.setSelected(true);
/*     */     }
/*     */     else
/*     */     {
/* 141 */       this.winSoundsCheck.setSelected(false);
/*     */     }
/*     */     
/* 144 */     JPanel localJPanel3 = new JPanel(new FlowLayout(1));
/* 145 */     localJPanel3.add(this.ok);
/*     */     
/* 147 */     ButtonGroup localButtonGroup2 = new ButtonGroup();
/* 148 */     localButtonGroup2.add(this.easy);
/* 149 */     localButtonGroup2.add(this.medium);
/* 150 */     localButtonGroup2.add(this.hard);
/*     */     
/* 152 */     JPanel localJPanel4 = new JPanel(new FlowLayout(0));
/* 153 */     localJPanel4.setBorder(new TitledBorder("Difficulty"));
/* 154 */     localJPanel4.add(this.easy);
/* 155 */     localJPanel4.add(this.medium);
/* 156 */     localJPanel4.add(this.hard);
/*     */     
/* 158 */     localJPanel4.setMaximumSize(new Dimension(110, 80));
/* 159 */     localJPanel4.setMinimumSize(localJPanel1.getMaximumSize());
/* 160 */     localJPanel4.setPreferredSize(localJPanel1.getMaximumSize());
/*     */     
/* 162 */     if (this.difficulty == 1)
/*     */     {
/* 164 */       this.easy.setSelected(true);
/*     */     }
/* 166 */     else if (this.difficulty == 3)
/*     */     {
/* 168 */       this.hard.setSelected(true);
/*     */     }
/*     */     else
/*     */     {
/* 172 */       this.medium.setSelected(true);
/*     */     }
/*     */     
/* 175 */     JPanel localJPanel5 = new JPanel(new BorderLayout());
/* 176 */     localJPanel5.add(localJPanel1, "West");
/* 177 */     localJPanel5.add(localJPanel2, "East");
/* 178 */     localJPanel5.add(localJPanel4, "Center");
/* 179 */     localJPanel5.add(localJPanel3, "South");
/*     */     
/* 181 */     add(localJPanel5);
/*     */     
/* 183 */     this.drawOne.addActionListener(this);
/* 184 */     this.drawThree.addActionListener(this);
/* 185 */     this.timerCheck.addActionListener(this);
/* 186 */     this.winAnimationCheck.addActionListener(this);
/* 187 */     this.winSoundsCheck.addActionListener(this);
/* 188 */     this.easy.addActionListener(this);
/* 189 */     this.medium.addActionListener(this);
/* 190 */     this.hard.addActionListener(this);
/* 191 */     this.ok.addActionListener(this);
/*     */   }
/*     */   
/*     */   public int getDrawCount()
/*     */   {
/* 196 */     if (!this.exited)
/*     */     {
/* 198 */       return this.drawCount;
/*     */     }
/*     */     
/* 201 */     return -1;
/*     */   }
/*     */   
/*     */   public int getTimer()
/*     */   {
/* 206 */     if (!this.exited)
/*     */     {
/* 208 */       return this.timer;
/*     */     }
/*     */     
/* 211 */     return -1;
/*     */   }
/*     */   
/*     */   public int getAnimation()
/*     */   {
/* 216 */     if (!this.exited)
/*     */     {
/* 218 */       return this.animation;
/*     */     }
/*     */     
/* 221 */     return -1;
/*     */   }
/*     */   
/*     */   public int getSounds()
/*     */   {
/* 226 */     if (!this.exited)
/*     */     {
/* 228 */       return this.sounds;
/*     */     }
/*     */     
/* 231 */     return -1;
/*     */   }
/*     */   
/*     */   public int getDifficulty()
/*     */   {
/* 236 */     return this.difficulty;
/*     */   }
/*     */   
/*     */   public void actionPerformed(ActionEvent paramActionEvent)
/*     */   {
/* 241 */     if (paramActionEvent.getSource() == this.drawOne)
/*     */     {
/* 243 */       this.drawCount = 1;
/* 244 */       this.easy.setToolTipText("3 times through deck");
/* 245 */       this.medium.setToolTipText("2 times through deck");
/* 246 */       this.hard.setToolTipText("1 time through deck");
/*     */     }
/* 248 */     else if (paramActionEvent.getSource() == this.drawThree)
/*     */     {
/* 250 */       this.drawCount = 3;
/* 251 */       this.easy.setToolTipText("4 times through deck");
/* 252 */       this.medium.setToolTipText("3 times through deck");
/* 253 */       this.hard.setToolTipText("2 times through deck");
/*     */ 
/*     */     }
/* 256 */     else if (paramActionEvent.getSource() == this.ok)
/*     */     {
/* 258 */       JOptionPane.showMessageDialog(null, "Note: Some options will take affect on the next game.", "Note", -1);
/*     */       
/* 260 */       this.exited = false;
/* 261 */       setVisible(false);
/*     */ 
/*     */     }
/* 264 */     else if (paramActionEvent.getSource() == this.timerCheck)
/*     */     {
/* 266 */       if (this.timerCheck.isSelected())
/*     */       {
/* 268 */         this.timer = 1;
/*     */       }
/*     */       else
/*     */       {
/* 272 */         this.timer = 0;
/*     */       }
/*     */     }
/* 275 */     else if (paramActionEvent.getSource() == this.winAnimationCheck)
/*     */     {
/* 277 */       if (this.winAnimationCheck.isSelected())
/*     */       {
/* 279 */         this.animation = 1;
/*     */       }
/*     */       else
/*     */       {
/* 283 */         this.animation = 0;
/*     */       }
/*     */     }
/* 286 */     else if (paramActionEvent.getSource() == this.winSoundsCheck)
/*     */     {
/* 288 */       if (this.winSoundsCheck.isSelected())
/*     */       {
/* 290 */         this.sounds = 1;
/*     */       }
/*     */       else
/*     */       {
/* 294 */         this.sounds = 0;
/*     */       }
/*     */       
/*     */     }
/* 298 */     else if (paramActionEvent.getSource() == this.easy)
/*     */     {
/* 300 */       this.difficulty = 1;
/*     */     }
/* 302 */     else if (paramActionEvent.getSource() == this.medium)
/*     */     {
/* 304 */       this.difficulty = 2;
/*     */     }
/* 306 */     else if (paramActionEvent.getSource() == this.hard)
/*     */     {
/* 308 */       this.difficulty = 3;
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Gema\Desktop\FourRowSolitaire-v.76.jar!\FourRowSolitaire\ChangeOptions.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */