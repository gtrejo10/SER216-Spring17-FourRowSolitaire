/*     */ package FourRowSolitaire;
/*     */ 
/*     */ import java.awt.Dialog.ModalityType;
/*     */ import java.awt.event.FocusAdapter;
/*     */ import java.awt.event.FocusEvent;
/*     */ import java.awt.event.MouseEvent;
/*     */ import java.io.File;
/*     */ import java.io.IOException;
/*     */ import java.net.URL;
/*     */ import java.util.Random;
/*     */ import javax.sound.midi.InvalidMidiDataException;
/*     */ import javax.sound.midi.MidiSystem;
/*     */ import javax.sound.midi.MidiUnavailableException;
/*     */ import javax.sound.midi.Sequence;
/*     */ import javax.sound.midi.Sequencer;
/*     */ import javax.swing.JDialog;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.event.MouseInputAdapter;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class WinScreen
/*     */   extends JDialog
/*     */ {
/*  42 */   SoundThread sound = null;
/*     */   
/*     */ 
/*     */   public WinScreen(int paramInt1, int paramInt2)
/*     */   {
/*  47 */     setUndecorated(true);
/*  48 */     setDefaultCloseOperation(2);
/*  49 */     setFocusable(true);
/*     */     
/*  51 */     if (paramInt2 == 1)
/*     */     {
/*  53 */       setSize(200, 200);
/*  54 */       this.sound = new SoundThread(null);
/*  55 */       this.sound.start();
/*     */     }
/*     */     
/*  58 */     if (paramInt1 == 1)
/*     */     {
/*  60 */       setSize(800, 600);
/*     */       
/*  62 */       FireworksDisplay localFireworksDisplay = new FireworksDisplay(100, 200);
/*  63 */       add(localFireworksDisplay);
/*  64 */       localFireworksDisplay.restartDisplay();
/*  65 */       setLocationRelativeTo(null);
/*  66 */       setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
/*     */       
/*  68 */       addFocusListener(new FocusAdapter()
/*     */       {
/*     */ 
/*     */         public void focusLost(FocusEvent paramAnonymousFocusEvent)
/*     */         {
/*  73 */           WinScreen.this.requestFocus();
/*     */         }
/*     */       });
/*     */     }
/*     */     else
/*     */     {
/*  79 */       setLocation(0, 0);
/*  80 */       add(new JLabel("Click Here to Stop Music"));
/*     */     }
/*     */     
/*  83 */     addMouseListener(new MouseInputAdapter()
/*     */     {
/*     */ 
/*     */       public void mouseClicked(MouseEvent paramAnonymousMouseEvent)
/*     */       {
/*     */ 
/*  89 */         if (WinScreen.this.sound != null)
/*     */         {
/*     */ 
/*  92 */           while (WinScreen.this.sound.sequencer == null)
/*     */           {
/*     */             try
/*     */             {
/*  96 */               Thread.sleep(25L);
/*     */             }
/*     */             catch (InterruptedException localInterruptedException) {}
/*     */           }
/*     */           
/* 101 */           if (WinScreen.this.sound.sequencer.isRunning())
/*     */           {
/* 103 */             WinScreen.this.sound.sequencer.stop();
/*     */           }
/*     */         }
/*     */         
/* 107 */         WinScreen.this.dispose();
/*     */       }
/*     */       
/* 110 */     });
/* 111 */     setVisible(true);
/*     */   }
/*     */   
/*     */   private class SoundThread extends Thread
/*     */   {
/*     */     public Sequencer sequencer;
/*     */     
/*     */     private SoundThread() {}
/*     */     
/*     */     public void run()
/*     */     {
/* 122 */       String str = "";
/* 123 */       Random localRandom = new Random();
/*     */       
/*     */ 
/*     */       try
/*     */       {
/* 128 */         File localFile = new File(getClass().getResource("sounds/win/").toURI());
/* 129 */         String[] arrayOfString = localFile.list();
/* 130 */         int j = 1;
/*     */         
/*     */         do
/*     */         {
/* 134 */           str = arrayOfString[localRandom.nextInt(arrayOfString.length)];
/*     */           
/* 136 */           if (str.toLowerCase().contains(".mid"))
/*     */           {
/* 138 */             j = 0;
/*     */           }
/* 140 */         } while (j != 0);
/*     */       }
/*     */       catch (Exception localException)
/*     */       {
/* 144 */         int i = localRandom.nextInt(4);
/*     */         
/* 146 */         if (i == 0)
/*     */         {
/* 148 */           str = "celebration.mid";
/*     */         }
/* 150 */         else if (i == 1)
/*     */         {
/* 152 */           str = "anotheronebitesthedust.mid";
/*     */         }
/* 154 */         else if (i == 2)
/*     */         {
/* 156 */           str = "wearethechampions.mid";
/*     */         }
/* 158 */         else if (i == 3)
/*     */         {
/* 160 */           str = "bluedabadee.mid";
/*     */         }
/*     */       }
/*     */       
/* 164 */       URL localURL = getClass().getResource("sounds/win/" + str);
/*     */       
/*     */       try
/*     */       {
/* 168 */         Sequence localSequence = MidiSystem.getSequence(localURL);
/* 169 */         this.sequencer = MidiSystem.getSequencer();
/* 170 */         this.sequencer.open();
/* 171 */         this.sequencer.setSequence(localSequence);
/* 172 */         this.sequencer.setLoopCount(-1);
/* 173 */         this.sequencer.start();
/*     */       }
/*     */       catch (InvalidMidiDataException localInvalidMidiDataException) {}catch (IOException localIOException) {}catch (MidiUnavailableException localMidiUnavailableException) {}
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Gema\Desktop\FourRowSolitaire-v.76.jar!\FourRowSolitaire\WinScreen.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */