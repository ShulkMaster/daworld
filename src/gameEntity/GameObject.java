package gameEntity;

/**
 *
 * @author yury_
 */
import java.awt.Dimension;
import core.Game;

public abstract class GameObject {

    private int vidacurrent;
    private final int fasecicle;
    private final int VIDAInicial;
    private Dimension location;
    public String name;

    public GameObject(int vida, int fase) {
        this.vidacurrent = vida;
        VIDAInicial = vida;
        this.fasecicle = fase + Game.getInstance().partida.getFASE();
    }

    public void printVidaBar() {
        System.out.print(name + " ");
        System.out.print(getVidaCurrent() + " UDV ");
        if (vidacurrent > 0) {
            String fullbar = "████████████████████";
            int aux = (int) ((getPersent(vidacurrent, VIDAInicial) * 20) / 100);
            fullbar = fullbar.substring(0, aux);
            if (aux > 10) {
                System.out.print("\033[32m" + fullbar + " " + getPersent(vidacurrent, VIDAInicial) + "%");
            } else if (aux > 6) {
                System.out.print("\033[33m" + fullbar + " " + getPersent(vidacurrent, VIDAInicial) + "%");
            } else {
                System.out.print("\033[31m" + fullbar + " " + getPersent(vidacurrent, VIDAInicial) + "%");
            }
            System.out.println("\033[30m ");
        } else {
            System.out.println("\033[30m ");
        }
    }

    public boolean isBuild() {
        boolean hecho = false;
        if (fasecicle <= Game.getInstance().partida.getFASE()) {
            hecho = true;
        }
        return hecho;
    }

    public int getFasecicle() {
        return fasecicle;
    }

    public Dimension getLocation() {
        return location;
    }

    public int getVidaCurrent() {
        return vidacurrent;
    }

    public int getPersent(int cInd, int iInd) {
        return (int) ((cInd * 100) / iInd);
    }

    public void setDamage(int damage) {
        vidacurrent -= damage;
    }

    public void setLocation(Dimension dim) {
        location = dim;
    }

    public abstract void printCtn();

    public abstract void action();
}
