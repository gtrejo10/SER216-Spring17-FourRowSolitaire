
package FourRowSolitaire;

//import java.awt.Dialog;
import java.awt.event.*;
import java.io.File;
import java.net.URL;
import java.util.Random;
import javax.sound.midi.*;
import javax.swing.*;
import javax.swing.event.MouseInputAdapter;

/**
 * Class: WinScreen
 * 
 * Description: The WinScreen class manages the win animation and sounds window.
 * 
 */
public class WinScreen extends JFrame
{
    SoundThread sound = null;

    public WinScreen(int animation, int sounds)
    {
        setUndecorated(true);
        //setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setFocusable(true);
        
        /*For JDialog instead of JFrame, but the mouse listener
            doesn't work with JDialog for some reason.
        setModalityType(Dialog.ModalityType.DOCUMENT_MODAL);*/

        if(sounds == 1)
        {
            setSize(200,200);
            sound = new SoundThread();
            sound.run();
        }

        if(animation == 1)
        {
            setSize(800,600);

            FireworksDisplay fw = new FireworksDisplay(100,200);
            add(fw);
            fw.restartDisplay();
            setLocationRelativeTo(null);
        }
        else
        {
            this.setLocation(0,0);
            add(new JLabel("Click Here to Stop Music"));
        }

        
        setVisible(true);

        addMouseListener(new MouseInputAdapter()
        {
            public void mouseClicked(MouseEvent e)
            {
                if(sound != null && sound.sequencer.isRunning())
                {
                    sound.sequencer.stop();
                }

                WinScreen.this.dispose();
            }
        });

        addFocusListener(new FocusAdapter()
        {
            public void focusLost(FocusEvent e)
            {
                WinScreen.this.requestFocus();
            }
        });
    }

    private class SoundThread extends Thread
    {
        public Sequencer sequencer;

        public void run()
        {
            String song = ""; //To hold choice
            Random gen = new Random();

            try
            {
                //Doesn't work as a .jar
                File songDir = new File(getClass().getResource("/sounds/win/").toURI());
                String[] songs = songDir.list();
                boolean retry = true;

                do
                {
                    song = songs[gen.nextInt(songs.length)];

                    if(song.toLowerCase().contains(".mid"))
                    {
                        retry = false;
                    }
                } while(retry);
            }
            catch(Exception ex)
            {
                int songInt = gen.nextInt(4);

                if(songInt == 0)
                {
                    song = "celebration.mid";
                }
                else if(songInt == 1)
                {
                    song = "anotheronebitesthedust.mid";
                }
                else if(songInt == 2)
                {
                    song = "wearethechampions.mid";
                }
                else if(songInt == 3)
                {
                    song = "bluedabadee.mid";
                }
            }

            URL filelocation = getClass().getResource("/sounds/win/" + song);

            try
            {
                Sequence sequence = MidiSystem.getSequence(filelocation);
                sequencer = MidiSystem.getSequencer();
                sequencer.open();
                sequencer.setSequence(sequence);
                sequencer.setLoopCount(0);
                sequencer.start();
            }
            catch(Exception ex)
            {
                System.err.println("Error opening win sound file.");
            }
        }
    }
}