/*     */ package FourRowSolitaire;
/*     */ 
/*     */ import java.awt.BorderLayout;
/*     */ import java.awt.Dialog.ModalityType;
/*     */ import java.awt.FlowLayout;
/*     */ import java.awt.GridLayout;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import javax.swing.ButtonGroup;
/*     */ import javax.swing.ImageIcon;
/*     */ import javax.swing.JButton;
/*     */ import javax.swing.JDialog;
/*     */ import javax.swing.JFrame;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.JPanel;
/*     */ import javax.swing.JRadioButton;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ChangeAppearance
/*     */   extends JDialog
/*     */   implements ActionListener
/*     */ {
/*     */   public static final int NUM_DECKS = 3;
/*     */   public static final int NUM_BACKGROUNDS = 3;
/*     */   public static final int FRS_DECK = 3;
/*     */   public static final int FRS_BACKGROUND = 2;
/*  41 */   private final JRadioButton[] decks = new JRadioButton[3];
/*     */   
/*  43 */   private final JRadioButton[] backgrounds = new JRadioButton[3];
/*     */   
/*  45 */   private final JButton ok = new JButton("Choose This Setup");
/*     */   
/*  47 */   public int deckNumber = 3;
/*  48 */   public int backgroundNumber = 2;
/*     */   
/*  50 */   private boolean exited = true;
/*     */   
/*     */ 
/*  53 */   private final JLabel cardBackLabel = new JLabel();
/*  54 */   private final JLabel backgroundLabel = new JLabel();
/*     */   
/*     */   public ChangeAppearance(JFrame paramJFrame, int paramInt1, int paramInt2)
/*     */   {
/*  58 */     setTitle("Change Appearance");
/*  59 */     setSize(400, 300);
/*  60 */     setDefaultCloseOperation(1);
/*  61 */     setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
/*  62 */     setLocationRelativeTo(paramJFrame);
/*     */     
/*  64 */     this.deckNumber = paramInt1;
/*  65 */     this.backgroundNumber = paramInt2;
/*  66 */     setup();
/*     */     
/*  68 */     setVisible(true);
/*     */   }
/*     */   
/*     */ 
/*     */   private void setup()
/*     */   {
/*  74 */     JPanel localJPanel1 = new JPanel();
/*  75 */     localJPanel1.setLayout(new FlowLayout());
/*     */     
/*  77 */     ButtonGroup localButtonGroup1 = new ButtonGroup();
/*     */     
/*  79 */     for (int i = 0; i < 3; i++)
/*     */     {
/*  81 */       this.decks[i] = new JRadioButton("Deck " + (i + 1));
/*  82 */       localButtonGroup1.add(this.decks[i]);
/*  83 */       localJPanel1.add(this.decks[i]);
/*  84 */       this.decks[i].addActionListener(this);
/*     */     }
/*     */     
/*  87 */     if (this.deckNumber <= this.decks.length)
/*     */     {
/*  89 */       this.decks[(this.deckNumber - 1)].setSelected(true);
/*     */     }
/*     */     else
/*     */     {
/*  93 */       this.decks[2].setSelected(true);
/*     */     }
/*     */     
/*     */ 
/*  97 */     JPanel localJPanel2 = new JPanel();
/*  98 */     localJPanel2.setLayout(new FlowLayout());
/*     */     
/* 100 */     ButtonGroup localButtonGroup2 = new ButtonGroup();
/*     */     
/* 102 */     for (int j = 0; j < 3; j++)
/*     */     {
/* 104 */       this.backgrounds[j] = new JRadioButton("Background " + (j + 1));
/* 105 */       localButtonGroup2.add(this.backgrounds[j]);
/* 106 */       localJPanel2.add(this.backgrounds[j]);
/* 107 */       this.backgrounds[j].addActionListener(this);
/*     */     }
/*     */     
/* 110 */     if (this.backgroundNumber <= 3)
/*     */     {
/* 112 */       this.backgrounds[(this.backgroundNumber - 1)].setSelected(true);
/*     */     }
/*     */     else
/*     */     {
/* 116 */       this.backgrounds[0].setSelected(true);
/*     */     }
/*     */     
/* 119 */     JPanel localJPanel3 = new JPanel();
/* 120 */     localJPanel3.setLayout(new FlowLayout(1));
/* 121 */     localJPanel3.add(this.ok);
/*     */     
/* 123 */     this.cardBackLabel.setIcon(new ImageIcon(getClass().getResource("images/cardbacks/cardback" + this.deckNumber + ".png")));
/* 124 */     JPanel localJPanel4 = new JPanel();
/* 125 */     localJPanel4.add(this.cardBackLabel);
/*     */     
/* 127 */     this.backgroundLabel.setIcon(new ImageIcon(getClass().getResource("images/backgrounds/background" + this.backgroundNumber + "small.jpg")));
/* 128 */     JPanel localJPanel5 = new JPanel();
/* 129 */     localJPanel5.add(this.backgroundLabel);
/*     */     
/* 131 */     JPanel localJPanel6 = new JPanel();
/* 132 */     localJPanel6.setLayout(new GridLayout(2, 2, 0, 0));
/* 133 */     localJPanel6.add(localJPanel1);
/* 134 */     localJPanel6.add(localJPanel2);
/* 135 */     localJPanel6.add(localJPanel4);
/* 136 */     localJPanel6.add(localJPanel5);
/*     */     
/*     */ 
/* 139 */     JPanel localJPanel7 = new JPanel();
/* 140 */     localJPanel7.setLayout(new BorderLayout());
/*     */     
/* 142 */     JLabel localJLabel = new JLabel("Note: Deck changes will take effect on new game");
/* 143 */     localJLabel.setHorizontalAlignment(0);
/*     */     
/* 145 */     localJPanel7.add(localJLabel, "North");
/* 146 */     localJPanel7.add(localJPanel6, "Center");
/* 147 */     localJPanel7.add(localJPanel3, "South");
/*     */     
/* 149 */     add(localJPanel7);
/*     */     
/* 151 */     this.ok.addActionListener(this);
/*     */   }
/*     */   
/*     */   public int getDeckNumber()
/*     */   {
/* 156 */     if (!this.exited)
/*     */     {
/* 158 */       return this.deckNumber;
/*     */     }
/*     */     
/* 161 */     return -1;
/*     */   }
/*     */   
/*     */   public int getBackgroundNumber()
/*     */   {
/* 166 */     if (!this.exited)
/*     */     {
/* 168 */       return this.backgroundNumber;
/*     */     }
/*     */     
/* 171 */     return -1;
/*     */   }
/*     */   
/*     */   public void actionPerformed(ActionEvent paramActionEvent)
/*     */   {
/* 176 */     if (paramActionEvent.getSource() == this.decks[0])
/*     */     {
/* 178 */       this.deckNumber = 1;
/* 179 */       this.cardBackLabel.setIcon(new ImageIcon(getClass().getResource("images/cardbacks/cardback" + this.deckNumber + ".png")));
/*     */     }
/* 181 */     else if (paramActionEvent.getSource() == this.decks[1])
/*     */     {
/* 183 */       this.deckNumber = 2;
/* 184 */       this.cardBackLabel.setIcon(new ImageIcon(getClass().getResource("images/cardbacks/cardback" + this.deckNumber + ".png")));
/*     */     }
/* 186 */     else if (paramActionEvent.getSource() == this.decks[2])
/*     */     {
/* 188 */       this.deckNumber = 3;
/* 189 */       this.cardBackLabel.setIcon(new ImageIcon(getClass().getResource("images/cardbacks/cardback" + this.deckNumber + ".png")));
/*     */ 
/*     */     }
/* 192 */     else if (paramActionEvent.getSource() == this.backgrounds[0])
/*     */     {
/* 194 */       this.backgroundNumber = 1;
/* 195 */       this.backgroundLabel.setIcon(new ImageIcon(getClass().getResource("images/backgrounds/background" + this.backgroundNumber + "small.jpg")));
/*     */     }
/* 197 */     else if (paramActionEvent.getSource() == this.backgrounds[1])
/*     */     {
/* 199 */       this.backgroundNumber = 2;
/* 200 */       this.backgroundLabel.setIcon(new ImageIcon(getClass().getResource("images/backgrounds/background" + this.backgroundNumber + "small.jpg")));
/*     */     }
/* 202 */     else if (paramActionEvent.getSource() == this.backgrounds[2])
/*     */     {
/* 204 */       this.backgroundNumber = 3;
/* 205 */       this.backgroundLabel.setIcon(new ImageIcon(getClass().getResource("images/backgrounds/background" + this.backgroundNumber + "small.jpg")));
/*     */ 
/*     */     }
/* 208 */     else if (paramActionEvent.getSource() == this.ok)
/*     */     {
/* 210 */       this.exited = false;
/* 211 */       setVisible(false);
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Gema\Desktop\FourRowSolitaire-v.76.jar!\FourRowSolitaire\ChangeAppearance.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */