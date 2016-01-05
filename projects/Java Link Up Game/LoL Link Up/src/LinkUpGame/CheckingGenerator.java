/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package LinkUpGame;


public class CheckingGenerator {
    
    private int trackNum = 0;

    public CheckingGenerator() {
    }

    public int getCheck() {
        return trackNum;
    }

    public void GenerateCheck() {
        trackNum++;
    }

}
