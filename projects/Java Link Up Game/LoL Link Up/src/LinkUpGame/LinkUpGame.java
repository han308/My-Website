package LinkUpGame;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SwingUtilities;

/**
 *
 * @author Jack
 */
public class LinkUpGame extends JFrame{

    /**
     * @param args the command line arguments
     */
    public Image tile, temp, menubackground, button, buttonclicked, transparent, logo, levelbutton, levelClicked, grayshade, backArrow;
    public boolean mouseOnScreen, mouseDragged = true, menuClicked, playHover = false, ruleHover = false, backHover = false;
    public boolean levelHover = false, levelHover2 = false, levelHover3 = false, levelHover4 = false, levelHover5 = false, levelHover6 = false, levelHover7 = false, levelHover8 = false, levelHover9 = false;
    public ArrayList<String> FileName = new ArrayList<String>();
    private int  x, y;
    int [] chooseNumTiles = {36, 64, 100, 144};
    
    Image background;
    Image dbImage;
    Graphics dbg;
    //
    public static int count =0;   
    private MainGame panel;   

    //
    
    public static boolean gameStarted = false, playClicked = false, ruleClicked = false;

    
    public LinkUpGame ()
    {
        //Load Images
         menubackground = new ImageIcon ("resources/Menu background.jpg").getImage();
         background = new ImageIcon ("resources/background.jpg").getImage();
         button = new ImageIcon ("resources/button.png").getImage();
         buttonclicked = new ImageIcon ("resources/button clicked.png").getImage();
         logo = new ImageIcon ("resources/leagueoflegends-logo.png").getImage();
         levelbutton = new ImageIcon ("resources/level button.png").getImage();
         levelClicked = new ImageIcon ("resources/level button.jpg").getImage();
         grayshade = new ImageIcon ("resources/Shade.jpg").getImage();
         backArrow = new ImageIcon ("resources/backArrow.gif").getImage();

         menuClicked = false;
        
        initView();

    }
    public void initView ()
    {
     
          this.addWindowListener(new WindowAdapter(){  
              @Override
            public void windowClosing(WindowEvent e){  
                System.exit(0);  
            }  
        });
        
        this.setSize(800, 480);
        this.setTitle("LoL Link Up");  
        setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
        
        this.setResizable(false);  
        this.setLayout(new BorderLayout());
        this.addMouseListener(new MouseHandler());
        this.addMouseMotionListener(new MouseHandler());
        this.setVisible(true);  
    }

    public void start(int ROW, int COLUMN)
    {
        this.removeMouseListener(new MouseHandler());
        this.removeMouseMotionListener(new MouseHandler());
        gameStarted = true;
        panel = new MainGame(ROW, COLUMN);
        panel.setBounds(0, -25, 800, 480);
        this.getContentPane().add(panel, BorderLayout.CENTER);
        panel.setVisible(true);

    }
    @Override
    public void paint (Graphics g)
    {
        dbImage = createImage(getWidth(), getHeight());
        dbg = dbImage.getGraphics();
        draw(dbg);
        g.drawImage(dbImage, 0, 0, this);
    }
    
    public void draw (Graphics g)
    {
        Graphics2D ga = (Graphics2D) g;

        ga.setPaint(Color.white);
        ga.setFont(new Font("TimesRoman", Font.BOLD, 20));
        if (!gameStarted) {

            if (!playClicked && !ruleClicked) {

                g.drawImage(menubackground, 0, 0, this);

                if (playHover) {
                    g.drawImage(buttonclicked, 420, 200, this);
                } else {
                    g.drawImage(button, 420, 200, this);
                }

                if (ruleHover) {
                    g.drawImage(buttonclicked, 420, 250, this);
                } else {
                    g.drawImage(button, 420, 250, this);
                }
                g.drawImage(logo, 450, 50, this);
                ga.drawString("Play", 558, 228);
                ga.drawString("Rules", 555, 279);
            } else if (ruleClicked) {
                g.drawImage(grayshade, 0, 0, this);
                if (!backHover) {
                    g.drawImage(levelbutton, 100, 400, this);
                } else {
                    g.drawImage(levelClicked, 100, 400, this);
                }
                ga.setPaint(Color.white);
                ga.setFont(new Font("TimesRoman", Font.BOLD, 18));
                g.drawImage(backArrow, 118, 405, this);
                ga.drawString("The objective of the game is to pair all the tiles. When", 200, 100);
                ga.drawString("pairing the same tiles, a maximum of three lines can be", 200, 140);
                ga.drawString("used to connect and eliminate the tiles (or 2 bends).", 200, 180);

            } else if (playClicked) {

                g.drawImage(grayshade, 0, 0, this);
                if (!levelHover) {

                    g.drawImage(levelbutton, 230, 100, this);

                } else {

                    g.drawImage(levelClicked, 230, 100, this);
                }

                if (!levelHover2) {
                    g.drawImage(levelbutton, 360, 100, this);

                } else {
                    g.drawImage(levelClicked, 360, 100, this);

                }
                if (!levelHover3) {
                    g.drawImage(levelbutton, 490, 100, this);

                } else {
                    g.drawImage(levelClicked, 490, 100, this);

                }
                if (!levelHover4) {
                    g.drawImage(levelbutton, 230, 194, this);

                } else {
                    g.drawImage(levelClicked, 230, 194, this);

                }
                if (!levelHover5) {
                    g.drawImage(levelbutton, 360, 194, this);

                } else {
                    g.drawImage(levelClicked, 360, 194, this);

                }
                if (!levelHover6) {
                    g.drawImage(levelbutton, 490, 194, this);

                } else {
                    g.drawImage(levelClicked, 490, 194, this);

                }
                if (!levelHover7) {
                    g.drawImage(levelbutton, 230, 288, this);

                } else {
                    g.drawImage(levelClicked, 230, 288, this);

                }
                if (!levelHover8) {
                    g.drawImage(levelbutton, 360, 288, this);

                } else {
                    g.drawImage(levelClicked, 360, 288, this);

                }
                if (!levelHover9) {
                    g.drawImage(levelbutton, 490, 288, this);
                } else {
                    g.drawImage(levelClicked, 490, 288, this);
                }
                if (!backHover) {
                    g.drawImage(levelbutton, 100, 400, this);
                } else {
                    g.drawImage(levelClicked, 100, 400, this);
                }
                g.drawImage(backArrow, 118, 405, this);

                ga.drawString("1", 265, 128);
                ga.drawString("2", 395, 128);
                ga.drawString("3", 525, 128);
                ga.drawString("4", 265, 222);
                ga.drawString("5", 395, 222);
                ga.drawString("6", 525, 222);
                ga.drawString("7", 265, 316);
                ga.drawString("8", 395, 316);
                ga.drawString("9", 525, 316);
            }

        } 
        else {
            panel.paintComponent(g);
        }
        repaint();
    }
       
    public class MouseHandler extends MouseAdapter 
    {

    @Override
    public void mouseMoved(MouseEvent e) { 

             x = e.getX();
             y = e.getY();
             if (!playClicked) {
                 if (x >= 420 && x <= 720 && y >= 200 && y <= 244) {
                     playHover = true;
                 } else {
                     playHover = false;
                 }
                 if (x >= 420 && x <= 720 && y >= 250 && y <= 294) {
                     ruleHover = true;
                 } else {

                     ruleHover = false;
                 }
             }
             if (x >= 230 && x <= 310 && y >= 100 && y <= 144) {
                 levelHover = true;
             } else {
                 levelHover = false;
             }

             if (x >= 230 && x <= 310 && y >= 194 && y <= 238) {
                 levelHover4 = true;
             } else {
                 levelHover4 = false;
             }
             if (x >= 230 && x <= 310 && y >= 288 && y <= 332) {
                 levelHover7 = true;
             } else {
                 levelHover7 = false;
             }
             if (x >= 360 && x <= 440 && y >= 100 && y <= 144) {
                 levelHover2 = true;
             } else {
                 levelHover2 = false;
             }
             if (x >= 360 && x <= 440 && y >= 194 && y <= 238) {
                 levelHover5 = true;
             } else {
                 levelHover5 = false;
             }
             if (x >= 360 && x <= 440 && y >= 288 && y <= 332) {
                 levelHover8 = true;
             } else {
                 levelHover8 = false;
             }
             if (x >= 490 && x <= 570 && y >= 100 && y <= 144) {
                 levelHover3= true;
             } else {
                 levelHover3 = false;
             }
             if (x >= 490 && x <= 570 && y >= 194 && y <= 238) {
                 levelHover6 = true;
             } else {
                 levelHover6 = false;
             }
             if (x >= 490 && x <= 570 && y >= 288 && y <= 332) {
                 levelHover9 = true;
             } else {
                 levelHover9 = false;
             }
             if (x >= 100 && x <= 180 && y >= 400 && y <= 444)
             {
                 backHover = true;
             }
             else
             {
                 backHover = false;
             }
        }   

    @Override
    public void mouseDragged(MouseEvent e) {
                        int xCoor = e.getX();
                int yCoor = e.getY();
                x = xCoor;
                y = yCoor;
                if (x >= 420 && x <= 720 && y >= 200 && y <= 266) {
                    mouseDragged = true;                   
                }
    }
        @Override
        public void mousePressed(MouseEvent e) 
        {

            int xCoor = e.getX();
            int yCoor = e.getY();
            x = xCoor;
            y = yCoor;
            if (!gameStarted && !playClicked) {
                if (x >= 420 && x <= 720 && y >= 200 && y <= 244) {
                    playClicked = true;

                }
                if (x >= 420 && x <= 720 && y >= 250 && y <= 294) {
                    ruleClicked = true;
                }
                
            } else if (playClicked) {
                if (x >= 230 && x <= 310 && y >= 100 && y <= 144) {
                    levelHover = true;
                    start(4, 4);
                } else {
                    levelHover = false;
                }
                if (x >= 230 && x <= 310 && y >= 194 && y <= 238) {
                    levelHover4 = true;
                    start(6, 5);
                } else {
                    levelHover4 = false;
                }
                if (x >= 230 && x <= 310 && y >= 288 && y <= 332) {
                    levelHover7 = true;
                    start(7, 6);
                } else {
                    levelHover7 = false;
                }
                if (x >= 360 && x <= 440 && y >= 100 && y <= 144) {
                    levelHover2 = true;
                    start(5, 4);
                    
                } else {
                    levelHover2 = false;
                }
                if (x >= 360 && x <= 440 && y >= 194 && y <= 238) {
                    levelHover5 = true;
                    start(6, 5);
                } else {
                    levelHover5 = false;
                }
                if (x >= 360 && x <= 440 && y >= 288 && y <= 332) {
                    levelHover8 = true;
                    
                    start(10, 10);
                } else {
                    levelHover8 = false;
                }
                if (x >= 490 && x <= 570 && y >= 100 && y <= 144) {
                    levelHover3 = true;
                    
                    start(6, 4);
                } else {
                    levelHover3 = false;
                }
                if (x >= 490 && x <= 570 && y >= 194 && y <= 238) {
                    levelHover6 = true;
                    start(6, 6);
                } else {
                    levelHover6 = false;
                }
                if (x >= 490 && x <= 570 && y >= 288 && y <= 332) {
                    levelHover9 = true;
                    start(12, 12);
                } else {
                    levelHover9 = false;
                }
            }
            if (x >= 100 && x <= 180 && y >= 400 && y <= 444) {
                playClicked = false;
                ruleClicked = false;
            } 


        }

        @Override
        public void mouseEntered(MouseEvent e) {
            mouseOnScreen = true;
        }
        @Override
        public void mouseExited(MouseEvent e) {
            mouseOnScreen = false;
        }


        @Override
        public void mouseReleased(MouseEvent e) {
            mouseOnScreen = true;
        }


    }
    public static void main(String[] args) {
        // TODO code application logic here

            SwingUtilities.invokeLater(new Runnable() {
        public void run() {
            new LinkUpGame().setVisible(true);
        }
    });

        
            
 
    
    }
}
