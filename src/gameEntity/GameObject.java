package gameEntity;

/**
 *
 * @author yury_
 */
import java.awt.Dimension;

public abstract class GameObject {

    private int vidacurrent, fasecicle;
    private final int VIDAInicial;
    private Dimension location;
    public String name;

    public GameObject(int vida, int fase) {
        this.vidacurrent = vida;
        VIDAInicial = vida;
        this.fasecicle = fase;
    }

    public void printVidaBar() {
        if (vidacurrent > 0) {
            String fullbar = "████████████████████";
            int aux = (int) ((getVidaPersent() * 20) / 100);
            fullbar = fullbar.substring(0, aux);
            if (aux > 10) {
                System.out.print("\033[32m" + fullbar + " " + getVidaPersent() + "%");
            } else if (aux > 6) {
                System.out.print("\033[33m" + fullbar + " " + getVidaPersent() + "%");
            } else {
                System.out.print("\033[31m" + fullbar + " " + getVidaPersent() + "%");
            }
            System.out.println("\033[30m ");
        } else {
            System.out.println("\033[30m ");
        }
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

    public int getVidaPersent() {
        return (int) ((vidacurrent * 100) / VIDAInicial);
    }

    public void setDamage(int damage) {
        vidacurrent -= damage;
    }

    public void setLocation(Dimension dim) {
        location = dim;
    }

    public void setFasecicle(int fasecicle) {
        this.fasecicle = fasecicle;
    }

    public abstract void actionPerformed();
}
