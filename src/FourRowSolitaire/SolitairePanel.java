
package FourRowSolitaire;

import java.awt.*;
import java.net.URL;
import javax.swing.*;

/**
 * Class: SolitairePanel
 *
 * Description: The Solitaire Panel is the main playing field view.
 */
public class SolitairePanel extends JPanel
{
    private int backgroundNumber = 2;
    private Image background;

    public SolitairePanel()
    {
        URL imageURL = this.getClass().getResource("/images/backgrounds/background" + backgroundNumber + ".jpg");

        if (imageURL != null)
        {
            background = new ImageIcon(imageURL).getImage();
        }
    }

    public void changeBackground(int back)
    {
        backgroundNumber = back;
        
        URL imageURL = this.getClass().getResource("/images/backgrounds/background" + back + ".jpg");

        if (imageURL != null)
        {
            background = new ImageIcon(imageURL).getImage();
        }

        repaint();
    }

    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        g.drawImage(background,0,0,null);
    }
}