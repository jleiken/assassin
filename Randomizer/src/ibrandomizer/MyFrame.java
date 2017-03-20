package ibrandomizer;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class MyFrame extends JFrame {
    public boolean isOpen;
    public MyFrame()
    {
        super();
        
        this.setAlwaysOnTop(true);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setVisible(true);
        this.setTitle("Color Reeder");
        this.setVisible(true);
        isOpen = true;
    }
    public void main()
    {
        try
        {
            Robot robby = new Robot();
            this.getContentPane().removeAll();
            BufferedImage strt = robby.createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
            BufferedImage bim = new BufferedImage(strt.getWidth(), strt.getHeight(), strt.getType());
            GaussianFilter gauss = new GaussianFilter(5);
            for(int i = 1; i<=5;i++)
                gauss.filter(strt, bim);

            ArrayList<Integer> oneDPix = new ArrayList<Integer>();

            for(int x=0; x < bim.getWidth(); x++)
            {
                for(int y=0; y < bim.getHeight(); y++)
                {
                    oneDPix.add(bim.getRGB(x, y));
                }
            }
            int mode=0, pocc=0,occ=0;

            Collections.sort(oneDPix);
            for(int i = 1; i < oneDPix.size(); i++)
            {
                //get the most common color
                int curr = Math.abs(oneDPix.get(i));
                if(curr == Math.abs(oneDPix.get(i-1))&&curr!=0)
                    occ++;
                else
                {
                    if(occ>pocc)
                    {
                        mode=i;
                        pocc=occ;
                    }
                    occ=0;
                }
            }
            BufferedImage tempIm = new BufferedImage(160, 90,BufferedImage.TYPE_INT_ARGB);
            Graphics2D gr = tempIm.createGraphics();
            gr.setColor(new Color(oneDPix.get(mode)));
            System.out.println(oneDPix.get(mode));
            //System.out.println(oneDPix.get(mode));
            gr.fillRect(0, 0, 160, 90);


            this.getContentPane().setLayout(new FlowLayout());
            JLabel lab = new JLabel(new ImageIcon(tempIm));
            this.getContentPane().add(lab);
            //this.getContentPane().add(new JLabel(new ImageIcon(bim)));
            this.pack();
            robby.delay(100);
        }
        catch(Exception e){System.out.println(e);}
    }
    public void dispose()
    {
        super.dispose();
        isOpen = false;
    }
    public boolean isOpen()
    {
        return isOpen;
    }
}