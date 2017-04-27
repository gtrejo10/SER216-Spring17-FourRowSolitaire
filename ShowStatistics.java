/*     */ package FourRowSolitaire;
/*     */ 
/*     */ import java.awt.BorderLayout;
/*     */ import java.awt.Component;
/*     */ import java.awt.Dialog.ModalityType;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import javax.swing.JButton;
/*     */ import javax.swing.JComboBox;
/*     */ import javax.swing.JDialog;
/*     */ import javax.swing.JPanel;
/*     */ import javax.swing.JTextArea;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ShowStatistics
/*     */   extends JDialog
/*     */   implements ActionListener
/*     */ {
/*     */   private JPanel mainPanel;
/*     */   private JPanel[] statsPanels;
/*  42 */   BorderLayout mainLayout = new BorderLayout();
/*     */   
/*  44 */   private final JButton close = new JButton("Close");
/*  45 */   private final JButton reset = new JButton("Reset");
/*     */   
/*     */   private JComboBox<String> combobox;
/*     */   
/*     */   public static final int CLOSE_ACTION = 0;
/*     */   
/*     */   public static final int RESET_ACTION = 1;
/*  52 */   private int closingAction = 0;
/*     */   
/*     */ 
/*     */   public ShowStatistics(Component paramComponent, int paramInt, JTextArea paramJTextArea1, JTextArea paramJTextArea2, JTextArea paramJTextArea3, JTextArea paramJTextArea4, JTextArea paramJTextArea5, JTextArea paramJTextArea6)
/*     */   {
/*  57 */     setTitle("Statistics");
/*  58 */     setSize(550, 250);
/*  59 */     setLocationRelativeTo(paramComponent);
/*  60 */     setDefaultCloseOperation(2);
/*  61 */     setResizable(false);
/*     */     
/*  63 */     setup(paramInt, paramJTextArea1, paramJTextArea2, paramJTextArea3, paramJTextArea4, paramJTextArea5, paramJTextArea6);
/*     */     
/*  65 */     setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
/*  66 */     setVisible(true);
/*     */   }
/*     */   
/*     */ 
/*     */   private void setup(int paramInt, JTextArea paramJTextArea1, JTextArea paramJTextArea2, JTextArea paramJTextArea3, JTextArea paramJTextArea4, JTextArea paramJTextArea5, JTextArea paramJTextArea6)
/*     */   {
/*  72 */     String[] arrayOfString = { "One-Card Draw", "Three-Card Draw" };
/*  73 */     int i = 0;
/*  74 */     if (paramInt == 3)
/*     */     {
/*  76 */       i = 1;
/*     */     }
/*     */     
/*  79 */     this.close.addActionListener(this);
/*  80 */     this.reset.addActionListener(this);
/*     */     
/*  82 */     this.combobox = new JComboBox(arrayOfString);
/*  83 */     this.combobox.setSelectedIndex(i);
/*  84 */     this.combobox.addActionListener(this);
/*     */     
/*  86 */     this.combobox.setMinimumSize(new Dimension(150, 25));
/*  87 */     this.combobox.setMaximumSize(new Dimension(150, 25));
/*  88 */     this.combobox.setPreferredSize(new Dimension(150, 25));
/*     */     
/*  90 */     JPanel localJPanel1 = new JPanel();
/*  91 */     localJPanel1.add(this.combobox);
/*     */     
/*  93 */     this.mainPanel = new JPanel(this.mainLayout);
/*  94 */     this.mainPanel.add(localJPanel1, "North");
/*     */     
/*  96 */     this.statsPanels = new JPanel[2];
/*     */     
/*  98 */     paramJTextArea1.setMinimumSize(new Dimension(200, 150));
/*  99 */     paramJTextArea1.setMaximumSize(new Dimension(200, 150));
/* 100 */     paramJTextArea1.setPreferredSize(new Dimension(200, 150));
/*     */     
/* 102 */     paramJTextArea2.setMinimumSize(new Dimension(200, 150));
/* 103 */     paramJTextArea2.setMaximumSize(new Dimension(200, 150));
/* 104 */     paramJTextArea2.setPreferredSize(new Dimension(200, 150));
/*     */     
/* 106 */     paramJTextArea3.setMinimumSize(new Dimension(150, 150));
/* 107 */     paramJTextArea3.setMaximumSize(new Dimension(150, 150));
/* 108 */     paramJTextArea3.setPreferredSize(new Dimension(150, 150));
/*     */     
/* 110 */     paramJTextArea4.setMinimumSize(new Dimension(200, 150));
/* 111 */     paramJTextArea4.setMaximumSize(new Dimension(200, 150));
/* 112 */     paramJTextArea4.setPreferredSize(new Dimension(200, 150));
/*     */     
/* 114 */     paramJTextArea5.setMinimumSize(new Dimension(200, 150));
/* 115 */     paramJTextArea5.setMaximumSize(new Dimension(200, 150));
/* 116 */     paramJTextArea5.setPreferredSize(new Dimension(200, 150));
/*     */     
/* 118 */     paramJTextArea6.setMinimumSize(new Dimension(150, 150));
/* 119 */     paramJTextArea6.setMaximumSize(new Dimension(150, 150));
/* 120 */     paramJTextArea6.setPreferredSize(new Dimension(150, 150));
/*     */     
/* 122 */     JPanel localJPanel2 = new JPanel(new BorderLayout());
/* 123 */     localJPanel2.add(paramJTextArea1, "West");
/* 124 */     localJPanel2.add(paramJTextArea2, "Center");
/* 125 */     localJPanel2.add(paramJTextArea3, "East");
/*     */     
/* 127 */     JPanel localJPanel3 = new JPanel(new BorderLayout());
/* 128 */     localJPanel3.add(paramJTextArea4, "West");
/* 129 */     localJPanel3.add(paramJTextArea5, "Center");
/* 130 */     localJPanel3.add(paramJTextArea6, "East");
/*     */     
/* 132 */     this.statsPanels[0] = localJPanel2;
/* 133 */     this.statsPanels[1] = localJPanel3;
/*     */     
/* 135 */     this.mainPanel.add(this.statsPanels[i], "Center");
/*     */     
/* 137 */     JPanel localJPanel4 = new JPanel();
/* 138 */     localJPanel4.add(this.close);
/* 139 */     localJPanel4.add(this.reset);
/* 140 */     this.mainPanel.add(localJPanel4, "South");
/*     */     
/* 142 */     add(this.mainPanel);
/*     */   }
/*     */   
/*     */   public int getClosingAction()
/*     */   {
/* 147 */     return this.closingAction;
/*     */   }
/*     */   
/*     */   public void actionPerformed(ActionEvent paramActionEvent)
/*     */   {
/* 152 */     if (paramActionEvent.getSource() == this.close)
/*     */     {
/* 154 */       this.closingAction = 0;
/* 155 */       setVisible(false);
/*     */     }
/* 157 */     else if (paramActionEvent.getSource() == this.reset)
/*     */     {
/* 159 */       this.closingAction = 1;
/* 160 */       setVisible(false);
/*     */     }
/* 162 */     else if (paramActionEvent.getSource() == this.combobox)
/*     */     {
/* 164 */       int i = this.combobox.getSelectedIndex();
/* 165 */       this.mainPanel.remove(this.mainLayout.getLayoutComponent("Center"));
/* 166 */       this.mainPanel.add(this.statsPanels[i], "Center");
/* 167 */       this.mainPanel.repaint();
/* 168 */       this.mainPanel.revalidate();
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Gema\Desktop\FourRowSolitaire-v.76.jar!\FourRowSolitaire\ShowStatistics.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */