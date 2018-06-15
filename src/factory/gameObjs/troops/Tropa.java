/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package factory.gameObjs.troops;

/**
 *
 * @author yury_
 */
import gameEntity.GameObject;
public class Tropa extends GameObject{
    private final int attck;

    public int getAttck() {
        return attck;
    }
    public final int trooptype;

    public Tropa(int[] statcs) {
        super(statcs[1], statcs[2]);
        attck = statcs[0];
        trooptype = statcs[5];
    }

    @Override
    public void printCtn() {
        printVidaBar();
    }

    @Override
    public void action() {
    }
    
}
