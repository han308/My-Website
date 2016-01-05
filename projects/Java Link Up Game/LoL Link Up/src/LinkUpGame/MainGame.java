/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package LinkUpGame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 *
 * @author Jack
 */
public class MainGame extends JPanel implements Runnable{
    private final int row;
    private final int col;
    public Image tile, temp, menubackground, playbutton, transparent, delete, Victory;
    public boolean mouseOnScreen, menuClicked, Delete = true, mouseDragged = false, quitHover = false, restartHover = false, Deleted = false;
    private Tile [][] tilearray;
    private ArrayList<Tile>line = new ArrayList<Tile>();
    private ArrayList<Tile>Finish = new ArrayList<Tile>();
    private ArrayList<CheckingGenerator> checkTwo = new ArrayList<CheckingGenerator>();
    private ArrayList<String> FileName = new ArrayList<String>();
    private Random imagechooser;
    private int imagechoice, clickNumTime = 0, x, y, done = 0;
    private Tile Delete1, Delete2;
    int [] chooseNumTiles = {36, 64, 100, 144};
    
    Image background, clicked;
    private Image button,buttonClicked;
    
    
    public MainGame (int row,int col) {    
        
         menubackground = new ImageIcon ("resources/OlafMenuBackground.jpg").getImage();
         background = new ImageIcon ("resources/background.jpg").getImage();
         playbutton = new ImageIcon ("resources/play-button.gif").getImage();
         tile = new ImageIcon ("resources/tile.jpg").getImage ();
         clicked = new ImageIcon ("resources/Clicked.gif").getImage();
         transparent = new ImageIcon ("resources/transparentTile.gif").getImage ();
         button = new ImageIcon ("resources/level button.png").getImage();
         buttonClicked = new ImageIcon ("resources/level button.jpg").getImage();
         Victory = new ImageIcon ("resources/Victory.jpg").getImage();
//         ultimate = new ImageIcon ("resources/Requiem.jpg").getImage();
         menuClicked = true;
         
        tilearray = new Tile [row][col];
        this.addMouseListener (new Mouse ());
        this.addMouseMotionListener(new Mouse());
        this.row=row;  
        this.col=col;  
        init();  
    }  

    private void init() {   
        loadChampions();
        createButtons();  
        repaint();
  
//        this.setLayout(new GridLayout(row,col));  
//        for(int i=0;i<row;i++){  
//            for(int j=0;j<col;j++){  
//                this.add(allButtons[i][j]);  
// 
//            }  
//        }  
    }
        @Override    
    public void run() {
        while (true) {
            repaint();
            try {
                Thread.sleep(34);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            
        }
    }

    @Override
    public void paintComponent (Graphics g)
    {
//        done = checkFinished();
        BufferedImage buff = new BufferedImage( 400, 240, BufferedImage.TYPE_INT_RGB);
        Graphics2D ga = (Graphics2D) g;
        ga.setPaint(Color.white);
        ga.setFont(new Font("TimesRoman", Font.BOLD, 20));
            g.drawImage(background, 0, 0, this);
            for (int i = 0; i < row; i++) {
                for (int j = 0; j < col ; j++) {
                    
                    g.drawImage(tilearray [i][j].getTileImage(), tilearray[i][j].getTileX(), tilearray[i][j].getTileY(), this);
                    g.drawImage(tilearray [i][j].getTilePairImage(), tilearray[i][j].getTileX() + 2, tilearray[i][j].getTileY() + 2, this);
                    
                    if (tilearray[i][j].showClicked()) {
                        g.drawImage(clicked, tilearray[i][j].getTileX(), tilearray[i][j].getTileY(), this);
                    }
                }
        }
        

        if (!checkFini()) {

            if (!restartHover) {
                g.drawImage(button, 700, 191, this);

            } else {
                g.drawImage(buttonClicked, 700, 191, this);
            }
                    ga.drawString ("Restart", 706, 220);

        }


        if (!quitHover) {
            g.drawImage(button, 700, 245, this);
        } 
        else {
            g.drawImage(buttonClicked, 700, 245, this);
        }
        ga.drawString ("exit", 723, 273);
        if (Deleted) {
            
                drawLine(g);

        }
        if (checkFini()) {

            g.drawImage(Victory, 251, 156, this);
            ga.setColor(Color.white);
            ga.drawString("Please click exit.", 325, 400);
            
        }

    }

    public void drawLine (Graphics g)
    {
        g.drawImage(delete, Delete1.getTileX() + 2, Delete1.getTileY() + 2, this);
        g.drawImage(delete, Delete2.getTileX() + 2, Delete2.getTileY() + 2, this);
        g.setColor(Color.red);
        for (int i = 1; i < line.size(); i++)
        {
                g.drawLine (line.get(i - 1).getCenterX(), line.get(i - 1).getCenterY(), line.get(i).getCenterX(), line.get(i).getCenterY());
        }

    }
    public void terminate ()
    {
        LinkUpGame.gameStarted = false;
        this.setVisible(false);
    }

    public class Mouse extends MouseAdapter
    {
        @Override
        public void mouseMoved(MouseEvent e) {

            x = e.getX();
            y = e.getY();

            if (x >= 700 && x <= 780 && y >= 191 && y <= 235) {

                restartHover = true;
            } else {

                restartHover = false;
            }


            if (x >= 700 && x <= 780 && y >= 245 && y <= 289) {

                quitHover = true;

            } else {
                quitHover = false;
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
            Deleted = false;
            delete = transparent;
            line.clear();
            int x, y;
            int xCoor = e.getX();
            int yCoor = e.getY();
            x = xCoor;
            y = yCoor;
            if (!checkFini())
            {
                if (x >= 700 && x <= 780 && y >= 191 && y <= 235) {
                    restartHover = true;
                    createButtons();

                } else {
                    restartHover = false;
                }
            }
            if (x >= 700 && x <= 780 && y >= 245 && y <= 289) {

                quitHover = true;
                terminate();

            } else {
                quitHover = false;
            }
            for (int i = 0; i < tilearray.length; i++)
            {
                for (int j = 0; j < tilearray[0].length; j++) {
                    if (!(x >= 195 && x <= 605 && y >= 35 && y <= 445)) {
                        for (int k = 0; k < tilearray.length; k++) {
                            for (int l = 0; l < tilearray[0].length; l++) {
                                tilearray[k][l].resetClick();
                                clickNumTime = 0;
                            }

                            repaint();
                        }
                    } else {
                        if (tilearray[i][j].checkClicked(x, y) && tilearray[i][j].checkClickable()) {


                            if (clickNumTime == 2) {

                                clickNumTime = 0;
                                for (int k = 0; k < tilearray.length; k++) {
                                    for (int l = 0; l < tilearray[0].length; l++) {
                                        tilearray[k][l].resetClick();
                                    }
                                }
                            }

                            tilearray[i][j].setClicked();
                            repaint();
                            clickNumTime++;

                            if (clickNumTime == 2) {
                                checkPair(tilearray[i][j]);

                            }
                        }

                    }
                }
            }
        }
           @Override
        public void mouseReleased(MouseEvent e) {
            mouseOnScreen = true;
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            mouseOnScreen = false;
        }
    }
//    public boolean ULTIMATEPair (Tile a, Tile b)
//    {
//
//        if ((a.getID() == b.getID()) && b != a) {
//
//            boolean none = horizontalMatch(a, b) || verticalMatch(a, b);
//            boolean one = oneCorner(a, b);
//            boolean two = twoCorner(a, b);
//            if (none || one || two) {
//                tilearray[b.getYIndex()][b.getXIndex()].setTileImage(transparent);
//                tilearray[b.getYIndex()][b.getXIndex()].setTilePairImage(transparent);
//                tilearray[b.getYIndex()][b.getXIndex()].setClickable();
//                tilearray[b.getYIndex()][b.getXIndex()].setID(-1);
//                tilearray[a.getYIndex()][a.getXIndex()].setTileImage(transparent);
//                tilearray[a.getYIndex()][a.getXIndex()].setTilePairImage(transparent);
//                tilearray[a.getYIndex()][a.getXIndex()].setClickable();
//                tilearray[a.getYIndex()][a.getXIndex()].setID(-1);
//                Delete1 = tilearray[b.getYIndex()][b.getXIndex()];
//                Delete2 = tilearray[a.getYIndex()][a.getXIndex()];
//                delete = new ImageIcon("resources/Homeguard_item.png").getImage();
//                repaint();
//                return true;
//
//            }
//            gameFinished = checkFinished();
//            repaint();
//
//        }
//           return false;
//        
//    }
//    
    public void checkPair (Tile a)
    {
        for (int i = 0; i < tilearray.length; i++)
        {
            for (int j = 0; j < tilearray[0].length ; j++) 
            {             
                if (a.showClicked() && tilearray[i][j].showClicked() && (a.getID() == tilearray[i][j].getID()) && tilearray[i][j] != a) 
                {
                    line.add(a);
                    line.add(tilearray[i][j]);
                    if (horizontalMatch (a, tilearray[i][j])||verticalMatch (a, tilearray[i][j])|| oneCorner(a, tilearray[i][j])|| twoCorner(a, tilearray[i][j]))
                    {

                        tilearray[i][j].setTileImage(transparent);
                        tilearray[i][j].setTilePairImage(transparent);
                        tilearray[i][j].setClickable();
                        tilearray[i][j].setID(-1);

                        tilearray[a.getYIndex()][a.getXIndex()].setTileImage(transparent);
                        tilearray[a.getYIndex()][a.getXIndex()].setTilePairImage(transparent);
                        tilearray[a.getYIndex()][a.getXIndex()].setClickable();
                        tilearray[a.getYIndex()][a.getXIndex()].setID(-1);
                        Delete1 = tilearray[i][j];
                        Delete2 = tilearray[a.getYIndex()][a.getXIndex()];
                        delete = new ImageIcon ("resources/Homeguard_item.png").getImage();
                        repaint();
                        Finish.remove(Finish.size() - 1);
                        Finish.remove(Finish.size() - 1);
                        Deleted = true;
                            
                    }
                    for (int s = 0; s < tilearray.length; s++) {
                        for (int t = 0; t < tilearray[0].length; t++) {
                            tilearray[s][t].resetClick();
                        }
                    }
                    repaint(); 
                    Timer timer = new Timer ();
                    timer.schedule(new TimerTask () {

                        @Override
                        public void run() {
                            if (Deleted) {
                                try {
                                    Thread.sleep(500);
                                } catch (InterruptedException ex) {
                                    Logger.getLogger(MainGame.class.getName()).log(Level.SEVERE, null, ex);
                                }
                                line.clear();
                                delete = transparent;
                            }
                        }
                    }, 500);
                    
                }
            }
        }
//        done = checkFinished();
    }
    public boolean checkFini() {
        if (Finish.isEmpty())
        {
            return true;
        }
        return false;
    }
//    public int checkFinished() {
//        int h;
//        for (int i = 0; i < tilearray.length; i++) {
//            for (int j = 0; j < tilearray[0].length; j++) {
//                System.out.println (tilearray[i][j].getID ());
//                h = tilearray[i][j].getID ();
//                if (tilearray[i][j].getID () != -1);
//                {
//                    return 0;
//                }
//            }
//        }
//        return 1;
//    }

    boolean twoCorner (Tile a, Tile b)
    {
        ArrayList<Tile> c = new ArrayList<Tile>();
        for (int i = 0; i < row; i++)
        {
            c.add (tilearray[i][a.getXIndex()]);

        }

        for (int i = 0; i < row; i++)
        {
            if (oneCorner(c.get(i), b) && verticalMatch (c.get(i), a) && c.get(i).getID() == -1)
            {
                line.add(1, c.get(i));
                return true;
            }
            else 
            {
        line.clear();
        line.add(a);
        line.add(b);
            }
        }       
        c.clear();

        for (int i = 0; i < col; i++)
        {
            c.add (tilearray[a.getYIndex()][i]);

        }

        for (int i = 0; i < col; i++)
        {
            if (oneCorner(c.get(i), b)&& horizontalMatch (c.get(i), a) && c.get(i).getID() == -1)
            {
                line.add(1, c.get(i));
                return true;
            }
            else 
            {
        line.clear();
        line.add(a);
        line.add(b);
            }
        }
        return false;
    }
    boolean oneCorner(Tile a, Tile b) {

        Tile c, d;
        boolean isMatch;

        c = tilearray [a.getYIndex()] [b.getXIndex()];


        d = tilearray  [b.getYIndex()][a.getXIndex()];

        if (c.getID() == -1) {

            isMatch = (horizontalMatch(a, c) && verticalMatch(b, c)) || (horizontalMatch(b, c) && verticalMatch(a, c));

            if (isMatch) {
                line.add(1, c);
                return isMatch;
            }

        }
        if (d.getID() == -1) { 

            isMatch = (verticalMatch(a, d) && horizontalMatch(b, d)) || (verticalMatch(b, d) && horizontalMatch(a, d));

            if (isMatch)
            {
                line.add(1, d);
                return isMatch;
            }
        }

        return false;

    }
      boolean horizontalMatch(Tile a, Tile b) {
          
        if (a.getYIndex() == b.getYIndex())
        {
            if (a.getXIndex() < b.getXIndex())
            {
                for (int i = a.getXIndex() + 1; i < b.getXIndex(); i++)
                {
                    if (tilearray[a.getYIndex()][i].getID() != -1)
                    {
                        return false;
                    }
                }
            }
            else if (b.getXIndex() < a.getXIndex())
            {
                for (int i = b.getXIndex() + 1; i < a.getXIndex(); i++)
                {
                    if (tilearray [b.getYIndex()][i].getID() != -1)
                    {
                        return false;
                    }
                }
            }
            return true;            
        }
        else
        {
            return false;
        }
        
    }

    boolean verticalMatch(Tile a, Tile b) {
        if (a.getXIndex() == b.getXIndex())
        {
            if (a.getYIndex () < b.getYIndex())
            {
                for (int i = a.getYIndex() + 1; i < b.getYIndex(); i++)
                {
                    if (tilearray [i][a.getXIndex()].getID() != -1)
                    {
                        return false;
                    }
                }
            }
            else if (a.getYIndex() > b.getYIndex())
            {
                for (int i = b.getYIndex() + 1; i < a.getYIndex(); i++)
                {
                    if (tilearray [i][b.getXIndex()].getID () != -1)
                    {
                        return false;
                    }
                }
            }
            return true;
        }
        else
        {
            return false;
        }
    }
    

    private void createButtons() {  

        ArrayList<String> list = new ArrayList<String>();
        int i;
        int j = 0;
        if (row == 4 && col == 4) {
            try {
                String loadName = "level 1.txt";
                FileReader reader = new FileReader(loadName);
                Scanner in = new Scanner(reader);
                while (in.hasNextLine()) {
                    list.add(in.nextLine());
                }
                in.close();
            } catch (FileNotFoundException e) {
            }
            int h = 0;
            for (int y = 158; y <= 281; y = y + 41) {
                i = 0;
                for (int x = 318; x <= 441; x = x + 41) {
                    if (x >= 359 && x <= 400 && y >= 199 && y <= 240) {
//                        do {
//                            imagechooser = new Random();
//                            imagechoice = imagechooser.nextInt(((tilearray.length - 2) * (tilearray[0].length - 2)) / 2);
//                        } while (checkTwo.get(imagechoice).getCheck() >= 2);
//                        checkTwo.get(imagechoice).GenerateCheck();
                        
                        
                        tilearray[j][i] = new Tile(x, y, j, i, Integer.parseInt(list.get(h)), true, tile);
                        

                        temp = new ImageIcon("resources/" + FileName.get(Integer.parseInt(list.get(h))) + ".png").getImage();
                        tilearray[j][i].setTilePairImage(temp);
                        h++;
                        Finish.add(tilearray[j][i]);
                    } else {
                        tilearray[j][i] = new Tile(x, y, j, i, -1, false, transparent);
                        tilearray[j][i].setTilePairImage(transparent);
                    }
                    i++;
                }
                j++;
            }
        }

        if (row == 5 && col == 4) {
            try {
                String loadName = "level 2.txt";
                FileReader reader = new FileReader(loadName);
                Scanner in = new Scanner(reader);
                while (in.hasNextLine()) {
                    list.add(in.nextLine());
                }
                in.close();
            } catch (FileNotFoundException e) {
            }
            int h = 0;
            for (int y = 138; y <= 302; y = y + 41) {
                i = 0;
                for (int x = 318; x <= 441; x = x + 41) {
                    if (x >= 359 && x < 441 && y >= 179 && y < 302) {
//                        do {
//                            imagechooser = new Random();
//                            imagechoice = imagechooser.nextInt(((tilearray.length - 2) * (tilearray[0].length - 2)) / 2);
//                        } while (checkTwo.get(imagechoice).getCheck() >= 2);
//                        checkTwo.get(imagechoice).GenerateCheck();

                        tilearray[j][i] = new Tile(x, y, j, i, Integer.parseInt(list.get(h)), true, tile);
                        

                        temp = new ImageIcon("resources/" + FileName.get(Integer.parseInt(list.get(h))) + ".png").getImage();
                        tilearray[j][i].setTilePairImage(temp);
                        h++;
                        Finish.add(tilearray[j][i]);
                    } else {
                        tilearray[j][i] = new Tile(x, y, j, i, -1, false, transparent);
                        tilearray[j][i].setTilePairImage(transparent);
                    }
                    i++;
                }
                j++;
            }
        }
        if (row == 6 && col == 4) {
       try {
                String loadName = "level 3.txt";
                FileReader reader = new FileReader(loadName);
                Scanner in = new Scanner(reader);
                while (in.hasNextLine()) {
                    list.add(in.nextLine());
                }
                in.close();
            } catch (FileNotFoundException e) {
            }
       int h = 0;
            for (int y = 117; y <= 322; y = y + 41) {
                i = 0;
                for (int x = 318; x <= 441; x = x + 41) {
                    if (x >= 359 && x < 441 && y >= 158 && y < 322) {
//                        do {
//                            imagechooser = new Random();
//                            imagechoice = imagechooser.nextInt(((tilearray.length - 2) * (tilearray[0].length - 2)) / 2);
//                        } while (checkTwo.get(imagechoice).getCheck() >= 2);
//                        checkTwo.get(imagechoice).GenerateCheck();

                        tilearray[j][i] = new Tile(x, y, j, i, Integer.parseInt(list.get(h)), true, tile);
                        

                        temp = new ImageIcon("resources/" + FileName.get(Integer.parseInt(list.get(h))) + ".png").getImage();
                        tilearray[j][i].setTilePairImage(temp);
                        h++;
                        Finish.add(tilearray[j][i]);
                    } else {
                        tilearray[j][i] = new Tile(x, y, j, i, -1, false, transparent);
                        tilearray[j][i].setTilePairImage(transparent);
                    }
                    i++;
                }
                j++;
            }
        }
        if (row == 6 && col == 5) {
            
            
            Random generator = new Random ();
            int z = generator.nextInt (2);
            if (z == 0)
            {
                try {
                String loadName = "level 4.txt";
                FileReader reader = new FileReader(loadName);
                Scanner in = new Scanner(reader);
                while (in.hasNextLine()) {
                    list.add(in.nextLine());
                }
                in.close();
            } catch (FileNotFoundException e) {
            }
               int h = 0;
            for (int y = 117; y <= 322; y = y + 41) {
                i = 0;
                for (int x = 298; x <= 462; x = x + 41) {
                    if (x >= 339 && x < 462 && y >= 158 && y < 322) {
//                        do {
//                            imagechooser = new Random();
//                            imagechoice = imagechooser.nextInt(((tilearray.length - 2) * (tilearray[0].length - 2)) / 2);
//                        } while (checkTwo.get(imagechoice).getCheck() >= 2);
//                        checkTwo.get(imagechoice).GenerateCheck();

                        tilearray[j][i] = new Tile(x, y, j, i, Integer.parseInt(list.get(h)), true, tile);
                        

                        temp = new ImageIcon("resources/" + FileName.get(Integer.parseInt(list.get(h))) + ".png").getImage();
                        tilearray[j][i].setTilePairImage(temp);
                        h++;
                        Finish.add(tilearray[j][i]);
                    } else {
                        tilearray[j][i] = new Tile(x, y, j, i, -1, false, transparent);
                        tilearray[j][i].setTilePairImage(transparent);
                    }
                    i++;
                }
                j++;
            }
            }
            else
            {
               try {
                String loadName = "level 5.txt";
                FileReader reader = new FileReader(loadName);
                Scanner in = new Scanner(reader);
                while (in.hasNextLine()) {
                    list.add(in.nextLine());
                }
                in.close();
            } catch (FileNotFoundException e) {
            }
               int h = 0;
            for (int y = 117; y <= 322; y = y + 41) {
                i = 0;
                for (int x = 298; x <= 462; x = x + 41) {
                    if (x >= 339 && x < 462 && y >= 158 && y < 322) {
//                        do {
//                            imagechooser = new Random();
//                            imagechoice = imagechooser.nextInt(((tilearray.length - 2) * (tilearray[0].length - 2)) / 2);
//                        } while (checkTwo.get(imagechoice).getCheck() >= 2);
//                        checkTwo.get(imagechoice).GenerateCheck();

                        tilearray[j][i] = new Tile(x, y, j, i, Integer.parseInt(list.get(h)), true, tile);
                        

                        temp = new ImageIcon("resources/" + FileName.get(Integer.parseInt(list.get(h))) + ".png").getImage();
                        tilearray[j][i].setTilePairImage(temp);
                        h++;
                        Finish.add(tilearray[j][i]);
                    } else {
                        tilearray[j][i] = new Tile(x, y, j, i, -1, false, transparent);
                        tilearray[j][i].setTilePairImage(transparent);
                    }
                    i++;
                }
                j++;
            }
        }
        }
        if (row == 6 && col == 6) {
            try {
                String loadName = "level 6.txt";
                FileReader reader = new FileReader(loadName);
                Scanner in = new Scanner(reader);
                while (in.hasNextLine()) {
                    list.add(in.nextLine());
                }
                in.close();
            } catch (FileNotFoundException e) {
            }
            int h = 0;
            for (int y = 117; y <= 322; y = y + 41) {
                i = 0;
                for (int x = 277; x <= 482; x = x + 41) {
                    if (x >= 318 && x < 482 && y >= 158 && y < 322) {
//                        do {
//                            imagechooser = new Random();
//                            imagechoice = imagechooser.nextInt(((tilearray.length - 2) * (tilearray[0].length - 2)) / 2);
//                        } while (checkTwo.get(imagechoice).getCheck() >= 2);
//                        checkTwo.get(imagechoice).GenerateCheck();

                        tilearray[j][i] = new Tile(x, y, j, i, Integer.parseInt(list.get(h)), true, tile);

                        temp = new ImageIcon("resources/" + FileName.get(Integer.parseInt(list.get(h))) + ".png").getImage();
                        tilearray[j][i].setTilePairImage(temp);
                        h++;
                        Finish.add(tilearray[j][i]);
                    } else {
                        tilearray[j][i] = new Tile(x, y, j, i, -1, false, transparent);
                        tilearray[j][i].setTilePairImage(transparent);
                    }
                    i++;
                }
                j++;
            }
        }
//        if (row == 7 && col == 4)
//        {
//            try {
//                String loadName = "level 4.txt";
//                FileReader reader = new FileReader(loadName);
//                Scanner in = new Scanner(reader);
//                while (in.hasNextLine()) {
//                    list.add(in.nextLine());
//                }
//                in.close();
//            } catch (FileNotFoundException e) {
//            }
//       int h = 0;
//            for (int y = 97; y <= 343; y = y + 41) {
//                i = 0;
//                for (int x = 318; x <= 441; x = x + 41) {
//                    if (x >= 359 && x < 441 && y >= 158 && y < 322) {
////                        do {
////                            imagechooser = new Random();
////                            imagechoice = imagechooser.nextInt(((tilearray.length - 2) * (tilearray[0].length - 2)) / 2);
////                        } while (checkTwo.get(imagechoice).getCheck() >= 2);
////                        checkTwo.get(imagechoice).GenerateCheck();
//
//                        tilearray[j][i] = new Tile(x, y, j, i, Integer.parseInt(list.get(h)), true, tile);
//                        
//
//                        temp = new ImageIcon("resources/" + FileName.get(Integer.parseInt(list.get(h))) + ".png").getImage();
//                        tilearray[j][i].setTilePairImage(temp);
//                        h++;
//                        Finish.add(tilearray[j][i]);
//                    } else {
//                        tilearray[j][i] = new Tile(x, y, j, i, -1, false, transparent);
//                        tilearray[j][i].setTilePairImage(transparent);
//                    }
//                    i++;
//                }
//                j++;
//            }
//        
//        }
        if (row == 7 && col == 6) {
                      try {
                String loadName = "level 7.txt";
                FileReader reader = new FileReader(loadName);
                Scanner in = new Scanner(reader);
                while (in.hasNextLine()) {
                    list.add(in.nextLine());
                }
                in.close();
            } catch (FileNotFoundException e) {
            }
                      int h = 0;
            for (int y = 97; y <= 343; y = y + 41) {
                i = 0;
                for (int x = 277; x <= 482; x = x + 41) {
                    if (x >= 318 && x < 482 && y >= 138 && y < 343) {
//                        do {
//                            imagechooser = new Random();
//                            imagechoice = imagechooser.nextInt(((tilearray.length - 2) * (tilearray[0].length - 2)) / 2);
//                        } while (checkTwo.get(imagechoice).getCheck() >= 2);
//                        checkTwo.get(imagechoice).GenerateCheck();

                        tilearray[j][i] = new Tile(x, y, j, i, Integer.parseInt(list.get(h)), true, tile);

                        temp = new ImageIcon("resources/" + FileName.get(Integer.parseInt(list.get(h))) + ".png").getImage();
                        tilearray[j][i].setTilePairImage(temp);
                        h++;
                        Finish.add(tilearray[j][i]);
                    } else {
                        tilearray[j][i] = new Tile(x, y, j, i, -1, false, transparent);
                        tilearray[j][i].setTilePairImage(transparent);
                    }
                    i++;
                }
                j++;
            }
        }
        if (row == 10 && col == 10) {
                                  try {
                String loadName = "level 8.txt";
                FileReader reader = new FileReader(loadName);
                Scanner in = new Scanner(reader);
                while (in.hasNextLine()) {
                    list.add(in.nextLine());
                }
                in.close();
            } catch (FileNotFoundException e) {
            }
                      int h = 0;
            for (int y = 35; y < 445; y = y + 41) {
                i = 0;
                for (int x = 195; x < 605; x = x + 41) {
                    if (x >= 236 && x < 564 && y >= 76 && y < 404) {
//                        do {
//                            imagechooser = new Random();
//                            imagechoice = imagechooser.nextInt(((tilearray.length - 2) * (tilearray[0].length - 2)) / 2);
//                        } while (checkTwo.get(imagechoice).getCheck() >= 2);
//                        checkTwo.get(imagechoice).GenerateCheck();

                     tilearray[j][i] = new Tile(x, y, j, i, Integer.parseInt(list.get(h)), true, tile);

                        temp = new ImageIcon("resources/" + FileName.get(Integer.parseInt(list.get(h))) + ".png").getImage();
                        tilearray[j][i].setTilePairImage(temp);
                        h++;
                        Finish.add(tilearray[j][i]);
                    } else {
                        tilearray[j][i] = new Tile(x, y, j, i, -1, false, transparent);
                        tilearray[j][i].setTilePairImage(transparent);
                    }
                    i++;

                }
                j++;
            }
        }
        if (row == 12 && col == 12) {
            try {
                String loadName = "level 9.txt";
                FileReader reader = new FileReader(loadName);
                Scanner in = new Scanner(reader);
                while (in.hasNextLine()) {
                    list.add(in.nextLine());
                }
                in.close();
            } catch (FileNotFoundException e) {
            }
            int h = 0;

            for (int y = -6; y <= 445; y = y + 41) {
                i = 0;
                for (int x = 154; x <= 605; x = x + 41) {

                    if (x >= 195 && x < 605 && y >= 35 && y < 445) {
//
//                        do {
//                            imagechooser = new Random();
//                            imagechoice = imagechooser.nextInt(((tilearray.length - 2) * (tilearray[0].length - 2)) / 2);
//                        } while (checkTwo.get(imagechoice).getCheck() >= 2);
//                        checkTwo.get(imagechoice).GenerateCheck();
                        tilearray[j][i] = new Tile(x, y, j, i, Integer.parseInt(list.get(h)), true, tile);

                        temp = new ImageIcon("resources/" + FileName.get(Integer.parseInt(list.get(h))) + ".png").getImage();
                        tilearray[j][i].setTilePairImage(temp);
                        h++;
                        Finish.add(tilearray[j][i]);

                    } else {
                        tilearray[j][i] = new Tile(x, y, j, i, -1, false, transparent);
                        tilearray[j][i].setTilePairImage(transparent);

                    }
                    i++;
                }
                j++;

            }
        }
    
    }
     public void loadChampions ()
    {

          FileName.add("36px-AkaliSquare");
          checkTwo.add (new CheckingGenerator ());
          FileName.add ("36px-AmumuSquare");
          checkTwo.add (new CheckingGenerator ());
          FileName.add("36px-BrandSquare");
          checkTwo.add (new CheckingGenerator ());
          FileName.add("36px-CaitlynSquare");
          checkTwo.add (new CheckingGenerator ());
          FileName.add ("36px-DariusSquare");
          checkTwo.add (new CheckingGenerator ());
          FileName.add("36px-DianaSquare");
          checkTwo.add (new CheckingGenerator ());
          FileName.add("36px-DravenSquare");
          checkTwo.add (new CheckingGenerator ());
          FileName.add("36px-EliseSquare");
          checkTwo.add (new CheckingGenerator ());
          FileName.add("36px-EzrealSquare");
          checkTwo.add (new CheckingGenerator ());
          FileName.add("36px-FizzSquare");
          checkTwo.add (new CheckingGenerator ());
          FileName.add("36px-GravesSquare");
          checkTwo.add (new CheckingGenerator ());
          FileName.add("36px-HecarimSquare");
          checkTwo.add (new CheckingGenerator ());
          FileName.add("36px-IreliaSquare");
          checkTwo.add (new CheckingGenerator ());
          FileName.add("36px-JannaSquare");
          checkTwo.add (new CheckingGenerator ());
          FileName.add("36px-JarvanIVSquare");
          checkTwo.add (new CheckingGenerator ());
          FileName.add("36px-JaxSquare");
          checkTwo.add (new CheckingGenerator ());
          FileName.add("36px-KatarinaSquare");
          checkTwo.add (new CheckingGenerator ());
          FileName.add("36px-KhaZixSquare");
          checkTwo.add (new CheckingGenerator ());
          FileName.add("36px-LeeSinSquare");
          checkTwo.add (new CheckingGenerator ());
          FileName.add("36px-LeonaSquare");
          checkTwo.add (new CheckingGenerator ());
          FileName.add("36px-LuxSquare");
          checkTwo.add (new CheckingGenerator ());
          FileName.add("36px-MalphiteSquare");
          checkTwo.add (new CheckingGenerator ());
          FileName.add("36px-MaokaiSquare");
          checkTwo.add (new CheckingGenerator ());
          FileName.add("36px-MissFortuneSquare");
          checkTwo.add (new CheckingGenerator ());
          FileName.add("36px-NautilusSquare");
          checkTwo.add (new CheckingGenerator ());
          FileName.add("36px-NidaleeSquare");
          checkTwo.add (new CheckingGenerator ());
          FileName.add("36px-OlafSquare");
          checkTwo.add (new CheckingGenerator ());
          FileName.add("36px-OriannaSquare");
          checkTwo.add (new CheckingGenerator ());
          FileName.add("36px-RenektonSquare");
          checkTwo.add (new CheckingGenerator ());
          FileName.add("36px-RengarSquare");
          checkTwo.add (new CheckingGenerator ());
          FileName.add("36px-RivenSquare");       
          checkTwo.add (new CheckingGenerator ());
          FileName.add("36px-RumbleSquare");
          checkTwo.add (new CheckingGenerator ());
          FileName.add("36px-ShacoSquare");
          checkTwo.add (new CheckingGenerator ());
          FileName.add("36px-ShenSquare");
          checkTwo.add (new CheckingGenerator ());
          FileName.add("36px-SivirSquare");
          checkTwo.add (new CheckingGenerator ());
          FileName.add("36px-SonaSquare");
          checkTwo.add (new CheckingGenerator ());
          FileName.add("36px-SyndraSquare");
          checkTwo.add (new CheckingGenerator ());
          FileName.add("36px-TalonSquare");
          checkTwo.add (new CheckingGenerator ());
          FileName.add("36px-TaricSquare");
          checkTwo.add (new CheckingGenerator ());
          FileName.add("36px-TeemoSquare");
          checkTwo.add (new CheckingGenerator ());
          FileName.add("36px-TwistedFateSquare");
          checkTwo.add (new CheckingGenerator ());
          FileName.add("36px-UdyrSquare");
          checkTwo.add (new CheckingGenerator ());
          FileName.add("36px-VayneSquare");
          checkTwo.add (new CheckingGenerator ());
          FileName.add("36px-ViSquare");
          checkTwo.add (new CheckingGenerator ());
          FileName.add("36px-WarwickSquare");
          checkTwo.add (new CheckingGenerator ());
          FileName.add("36px-WukongSquare");
          checkTwo.add (new CheckingGenerator ());
          FileName.add("36px-XerathSquare");
          checkTwo.add (new CheckingGenerator ());
          FileName.add("36px-XinZhaoSquare");
          checkTwo.add (new CheckingGenerator ());
          FileName.add("36px-ZedSquare");
          checkTwo.add (new CheckingGenerator ());
          FileName.add("36px-ZyraSquare");
          checkTwo.add (new CheckingGenerator ());     
          Collections.shuffle(FileName);
    }

    
}