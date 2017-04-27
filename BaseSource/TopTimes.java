/*     */ package FourRowSolitaire;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import java.awt.Component;
/*     */ import java.awt.Container;
/*     */ import java.awt.Dialog.ModalityType;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import java.awt.event.KeyEvent;
/*     */ import java.awt.event.KeyListener;
/*     */ import java.io.BufferedReader;
/*     */ import java.io.FileReader;
/*     */ import java.io.FileWriter;
/*     */ import java.io.IOException;
/*     */ import java.io.PrintWriter;
/*     */ import javax.swing.BorderFactory;
/*     */ import javax.swing.GroupLayout;
/*     */ import javax.swing.GroupLayout.Alignment;
/*     */ import javax.swing.GroupLayout.ParallelGroup;
/*     */ import javax.swing.GroupLayout.SequentialGroup;
/*     */ import javax.swing.JButton;
/*     */ import javax.swing.JDialog;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.JSeparator;
/*     */ import javax.swing.JTextField;
/*     */ import javax.swing.LayoutStyle.ComponentPlacement;
/*     */ import javax.swing.text.Caret;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class TopTimes
/*     */   extends JDialog
/*     */   implements ActionListener, KeyListener
/*     */ {
/*  46 */   private final int NUM_OF_RECORDS = 10;
/*  47 */   private final JTextField[] names = new JTextField[10];
/*  48 */   private final JLabel[] times = new JLabel[10];
/*  49 */   private final JLabel[] jLabel = new JLabel[10];
/*  50 */   private final JSeparator[] jSeparator = new JSeparator[10];
/*     */   private int pos;
/*     */   private JButton jButton;
/*     */   private final Component parent;
/*     */   
/*     */   public TopTimes(Component paramComponent)
/*     */   {
/*  57 */     this.parent = paramComponent;
/*  58 */     initComponents();
/*     */   }
/*     */   
/*     */   private void initComponents()
/*     */   {
/*  63 */     for (int i = 0; i < this.names.length; i++)
/*     */     {
/*  65 */       this.jSeparator[i] = new JSeparator();
/*  66 */       this.jLabel[i] = new JLabel(i + 1 + ".");
/*  67 */       this.names[i] = new JTextField();
/*  68 */       this.names[i].setBorder(null);
/*  69 */       this.names[i].setColumns(10);
/*  70 */       this.names[i].setEditable(false);
/*  71 */       this.times[i] = new JLabel();
/*  72 */       this.times[i].setText(" ");
/*     */     }
/*     */     
/*  75 */     loadData();
/*     */     
/*  77 */     this.jButton = new JButton("Clear Times");
/*  78 */     this.jButton.addActionListener(this);
/*     */     
/*  80 */     setDefaultCloseOperation(2);
/*  81 */     setTitle("Best Times");
/*  82 */     toFront();
/*  83 */     setAlwaysOnTop(true);
/*  84 */     requestFocus();
/*     */     
/*  86 */     GroupLayout localGroupLayout = new GroupLayout(getContentPane());
/*  87 */     getContentPane().setLayout(localGroupLayout);
/*  88 */     localGroupLayout.setHorizontalGroup(localGroupLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(this.jSeparator[0]).addComponent(this.jSeparator[1]).addComponent(this.jSeparator[2], GroupLayout.Alignment.TRAILING).addGroup(localGroupLayout.createSequentialGroup().addGroup(localGroupLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(this.jSeparator[3]).addComponent(this.jSeparator[6], GroupLayout.Alignment.LEADING, -1, 483, 32767).addComponent(this.jSeparator[5], GroupLayout.Alignment.LEADING).addComponent(this.jSeparator[4], GroupLayout.Alignment.LEADING))).addComponent(this.jSeparator[7], GroupLayout.Alignment.TRAILING).addComponent(this.jSeparator[8], GroupLayout.Alignment.TRAILING).addGroup(localGroupLayout.createSequentialGroup().addContainerGap().addGroup(localGroupLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(localGroupLayout.createSequentialGroup().addComponent(this.jLabel[0]).addGap(18, 18, 18).addComponent(this.names[0], -2, -1, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, -1, 32767).addComponent(this.times[0]).addGap(38, 38, 38)).addGroup(localGroupLayout.createSequentialGroup().addComponent(this.jLabel[1]).addGap(18, 18, 18).addComponent(this.names[1], -2, -1, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, -1, 32767).addComponent(this.times[1]).addGap(37, 37, 37)).addGroup(localGroupLayout.createSequentialGroup().addComponent(this.jLabel[2]).addGap(18, 18, 18).addComponent(this.names[2], -2, -1, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, -1, 32767).addComponent(this.times[2]).addGap(38, 38, 38)).addGroup(localGroupLayout.createSequentialGroup().addComponent(this.jLabel[3]).addGap(18, 18, 18).addComponent(this.names[3], -2, -1, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, -1, 32767).addComponent(this.times[3]).addGap(38, 38, 38)).addGroup(localGroupLayout.createSequentialGroup().addComponent(this.jLabel[4]).addGap(18, 18, 18).addComponent(this.names[4], -2, -1, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, -1, 32767).addComponent(this.times[4]).addGap(38, 38, 38)).addGroup(localGroupLayout.createSequentialGroup().addComponent(this.jLabel[5]).addGap(18, 18, 18).addComponent(this.names[5], -2, -1, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, -1, 32767).addComponent(this.times[5]).addGap(38, 38, 38)).addGroup(localGroupLayout.createSequentialGroup().addComponent(this.jLabel[6]).addGap(18, 18, 18).addComponent(this.names[6], -2, -1, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, -1, 32767).addComponent(this.times[6]).addGap(38, 38, 38)).addGroup(localGroupLayout.createSequentialGroup().addComponent(this.jLabel[7]).addGap(18, 18, 18).addComponent(this.names[7], -2, -1, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, -1, 32767).addComponent(this.times[7]).addGap(38, 38, 38)).addGroup(localGroupLayout.createSequentialGroup().addComponent(this.jLabel[8]).addGap(18, 18, 18).addComponent(this.names[8], -2, -1, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, -1, 32767).addComponent(this.times[8]).addGap(38, 38, 38)).addGroup(localGroupLayout.createSequentialGroup().addComponent(this.jLabel[9]).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.names[9], -2, -1, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, -1, 32767).addComponent(this.times[9]).addGap(38, 38, 38)))).addComponent(this.jSeparator[9]).addGroup(localGroupLayout.createSequentialGroup().addGap(185, 185, 185).addComponent(this.jButton).addGap(0, 0, 32767)));
/*  89 */     localGroupLayout.setVerticalGroup(localGroupLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(localGroupLayout.createSequentialGroup().addContainerGap().addGroup(localGroupLayout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.jLabel[0]).addComponent(this.times[0]).addComponent(this.names[0], -2, -1, -2)).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.jSeparator[0], -2, 11, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addGroup(localGroupLayout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.jLabel[1]).addComponent(this.times[1]).addComponent(this.names[1], -2, -1, -2)).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.jSeparator[1], -2, 10, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addGroup(localGroupLayout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.jLabel[2]).addComponent(this.times[2]).addComponent(this.names[2], -2, -1, -2)).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.jSeparator[2], -2, 10, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addGroup(localGroupLayout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.jLabel[3]).addComponent(this.times[3]).addComponent(this.names[3], -2, -1, -2)).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.jSeparator[3], -2, 10, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addGroup(localGroupLayout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.jLabel[4]).addComponent(this.times[4]).addComponent(this.names[4], -2, -1, -2)).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.jSeparator[4], -2, 10, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addGroup(localGroupLayout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.jLabel[5]).addComponent(this.names[5], -2, -1, -2).addComponent(this.times[5])).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.jSeparator[5], -2, 10, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addGroup(localGroupLayout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.jLabel[6]).addComponent(this.names[6], -2, -1, -2).addComponent(this.times[6])).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.jSeparator[6], -2, 10, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addGroup(localGroupLayout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.jLabel[7]).addComponent(this.names[7], -2, -1, -2).addComponent(this.times[7])).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.jSeparator[7], -2, 10, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addGroup(localGroupLayout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.jLabel[8]).addComponent(this.names[8], -2, -1, -2).addComponent(this.times[8])).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.jSeparator[8], -2, 10, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addGroup(localGroupLayout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.jLabel[9]).addComponent(this.names[9], -2, -1, -2).addComponent(this.times[9])).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.jSeparator[9], -2, 10, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.jButton).addContainerGap(13, 32767)));
/*     */     
/*  91 */     pack();
/*  92 */     setLocationRelativeTo(this.parent);
/*  93 */     setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
/*     */   }
/*     */   
/*     */   public void loadData()
/*     */   {
/*  98 */     String str1 = System.getProperty("user.home") + System.getProperty("file.separator") + "frs-topTimes.dat";
/*     */     
/* 100 */     int i = 0;
/*     */     
/*     */ 
/*     */     try
/*     */     {
/* 105 */       FileReader localFileReader = new FileReader(str1);
/* 106 */       BufferedReader localBufferedReader = new BufferedReader(localFileReader);
/* 107 */       while (i < 10)
/*     */       {
/* 109 */         String str2 = localBufferedReader.readLine();
/* 110 */         this.names[i].setText(str2);
/* 111 */         str2 = localBufferedReader.readLine();
/* 112 */         this.times[i].setText(str2 == null ? " " : str2);
/* 113 */         i++;
/*     */       }
/*     */       
/* 116 */       localBufferedReader.close();
/*     */     }
/*     */     catch (IOException localIOException) {}
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void actionPerformed(ActionEvent paramActionEvent)
/*     */   {
/* 125 */     if (paramActionEvent.getSource() == this.jButton)
/*     */     {
/* 127 */       clearData();
/* 128 */       repaint();
/*     */     }
/*     */   }
/*     */   
/*     */   private void clearData()
/*     */   {
/* 134 */     String str = System.getProperty("user.home") + System.getProperty("file.separator") + "frs-topTimes.dat";
/*     */     
/*     */ 
/*     */     try
/*     */     {
/* 139 */       FileWriter localFileWriter = new FileWriter(str);
/* 140 */       PrintWriter localPrintWriter = new PrintWriter(localFileWriter);
/* 141 */       for (int i = 0; i < 10; i++)
/*     */       {
/* 143 */         localPrintWriter.println(" ");
/* 144 */         localPrintWriter.println(" ");
/* 145 */         this.names[i].setText(" ");
/* 146 */         this.times[i].setText(" ");
/*     */       }
/* 148 */       localPrintWriter.close();
/*     */     }
/*     */     catch (IOException localIOException) {}
/*     */   }
/*     */   
/*     */   private void saveData()
/*     */   {
/* 155 */     String str = System.getProperty("user.home") + System.getProperty("file.separator") + "frs-topTimes.dat";
/*     */     
/*     */ 
/*     */     try
/*     */     {
/* 160 */       FileWriter localFileWriter = new FileWriter(str);
/* 161 */       PrintWriter localPrintWriter = new PrintWriter(localFileWriter);
/* 162 */       for (int i = 0; i < 10; i++)
/*     */       {
/* 164 */         localPrintWriter.println(this.names[i].getText());
/* 165 */         localPrintWriter.println(this.times[i].getText());
/*     */       }
/* 167 */       localPrintWriter.close();
/*     */     }
/*     */     catch (IOException localIOException) {}
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setProperties(int paramInt)
/*     */   {
/* 180 */     for (int i = 9; (i >= 1) && (i > this.pos); i--)
/*     */     {
/* 182 */       this.names[i].setText(this.names[(i - 1)].getText());
/* 183 */       this.times[i].setText(this.times[(i - 1)].getText());
/*     */     }
/* 185 */     this.names[this.pos].setEditable(true);
/* 186 */     this.names[this.pos].getCaret().moveDot(this.names[this.pos].getText().length());
/* 187 */     this.names[this.pos].setBorder(BorderFactory.createLineBorder(Color.black));
/* 188 */     this.times[this.pos].setText(paramInt + "");
/* 189 */     this.names[this.pos].getCaret().setVisible(true);
/* 190 */     this.names[this.pos].requestFocus();
/* 191 */     this.names[this.pos].select(0, this.names[this.pos].getText().length());
/* 192 */     this.names[this.pos].addKeyListener(this);
/* 193 */     this.jButton.setEnabled(false);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void keyTyped(KeyEvent paramKeyEvent) {}
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void keyPressed(KeyEvent paramKeyEvent)
/*     */   {
/* 206 */     if (paramKeyEvent.getKeyCode() == 10)
/*     */     {
/* 208 */       this.names[this.pos] = ((JTextField)paramKeyEvent.getSource());
/* 209 */       this.names[this.pos].setEditable(false);
/* 210 */       this.names[this.pos].setBorder(null);
/* 211 */       this.names[this.pos].getCaret().setVisible(false);
/* 212 */       this.names[this.pos].removeKeyListener(this);
/* 213 */       saveData();
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void keyReleased(KeyEvent paramKeyEvent) {}
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public int IsTopTime(int paramInt)
/*     */   {
/* 227 */     for (int i = 0; i < 10; i++)
/*     */     {
/* 229 */       if (!this.times[i].getText().isEmpty())
/*     */       {
/* 231 */         if (paramInt < (this.times[i].getText().equals(" ") ? Integer.MAX_VALUE : Integer.valueOf(this.times[i].getText()).intValue()))
/*     */         {
/* 233 */           this.pos = i;
/* 234 */           return i;
/*     */         }
/*     */       }
/*     */       else
/*     */       {
/* 239 */         this.pos = i;
/* 240 */         return i;
/*     */       }
/*     */     }
/* 243 */     this.pos = -1;
/* 244 */     return -1;
/*     */   }
/*     */ }


/* Location:              C:\Users\Gema\Desktop\FourRowSolitaire-v.76.jar!\FourRowSolitaire\TopTimes.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */