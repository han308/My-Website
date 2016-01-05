
package LinkUpGame;

import java.awt.Image;


public class Tile{

    
    private Image tileImage, pairImage;
    private int tileX, tileY, centerX, centerY, imageXLeft, imageXRight, imageYTop, imageYBottom, pictureId, i, j;
    private boolean clicked = false, occupied, clickable;
    
    public Tile(int x, int y, int row, int col, int pictureId, boolean click, Image tileImage){
    	tileX = x;
    	tileY = y;
        i = row;
        j= col;
    	centerX = x + 20;
    	centerY = y + 20;
        imageXLeft = x;
        imageXRight = x + 40;
        imageYTop = y;
        imageYBottom = y + 40;
        this.tileImage = tileImage;
        clickable = click;
        this.pictureId = pictureId;  
        
        if (clickable)
        {
            occupied = true;
        }
        
    }
    public Tile ()
    {
        
    }

    //see if a tile is clicked
    public boolean checkClicked (int x, int y)
    {
        if ((x > imageXLeft && x < imageXRight) && ((y > imageYTop && y < imageYBottom)))
        {
            return true;
        }
        else
        {
            return false;
        }
        
    }
    public void setClickable ()
    {
        if (clickable)
        {
            clickable = false;
            occupied = false;
        }
        else
        {
            clickable = true;
        }
    }
    public boolean checkClickable ()
    {
        return clickable;
    }
    //set the opposite stance of click
    public void setClicked ()
    {
        if (clicked)
        {
            clicked = false;
        }
        else
        {
            clicked = true;
        }

    }
    //reset to false if not
    public void resetClick ()
    {
        clicked = false;
    }

    
    public boolean showClicked ()
    {
        return clicked;
    }

    //set the pair up image
    public void setTilePairImage (Image pairImage)
    {
    	this.pairImage = pairImage;
    }
    //retrieve pair up image
    public Image getTilePairImage ()
    {
    	return pairImage;
    }
    
    
    public int getTileX() {
        
        return tileX;
    }

    public void setTileX(int tileX) {
        this.tileX = tileX;
    }

    public int getTileY() {
    	
        return tileY;
    }

    public void setTileY(int tileY) {
        this.tileY = tileY;
    }

    public void setTileImage(Image image) {
        tileImage = image;
    }
    public Image getTileImage() {
        return tileImage;
    }
    
    public int getCenterY(){
    	return centerY;
    }
    public int getXIndex()
    {
        return j;
    }
        public int getYIndex()
    {
        return i;
    }
    public int getCenterX(){
    	return centerX;
    }
    public void setID (int id)
    {
        pictureId = id;
    }
    public int getID ()
    {
        return pictureId;
    }
//    public void setOccupied ()
//    {
//        if (occupied)
//        {
//            occupied = false;
//        }
//        else
//        {
//            occupied = true;
//        }
//    }
//    public boolean Occupied ()
//    {
//        return occupied;
//    }

}