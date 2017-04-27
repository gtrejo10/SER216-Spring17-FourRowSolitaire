/*    */ package FourRowSolitaire;
/*    */ 
/*    */ import java.awt.Graphics;
/*    */ import java.awt.Image;
/*    */ import java.net.URL;
/*    */ import javax.swing.ImageIcon;
/*    */ import javax.swing.JPanel;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class SolitairePanel
/*    */   extends JPanel
/*    */ {
/* 35 */   private int backgroundNumber = 2;
/*    */   private Image background;
/*    */   
/*    */   public SolitairePanel()
/*    */   {
/* 40 */     URL localURL = getClass().getResource("images/backgrounds/background" + this.backgroundNumber + ".jpg");
/*    */     
/* 42 */     if (localURL != null)
/*    */     {
/* 44 */       this.background = new ImageIcon(localURL).getImage();
/*    */     }
/*    */   }
/*    */   
/*    */   public void changeBackground(int paramInt)
/*    */   {
/* 50 */     this.backgroundNumber = paramInt;
/*    */     
/* 52 */     URL localURL = getClass().getResource("images/backgrounds/background" + paramInt + ".jpg");
/*    */     
/* 54 */     if (localURL != null)
/*    */     {
/* 56 */       this.background = new ImageIcon(localURL).getImage();
/*    */     }
/*    */     
/* 59 */     repaint();
/*    */   }
/*    */   
/*    */ 
/*    */   protected void paintComponent(Graphics paramGraphics)
/*    */   {
/* 65 */     super.paintComponent(paramGraphics);
/* 66 */     paramGraphics.drawImage(this.background, 0, 0, null);
/*    */   }
/*    */ }


/* Location:              C:\Users\Gema\Desktop\FourRowSolitaire-v.76.jar!\FourRowSolitaire\SolitairePanel.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */