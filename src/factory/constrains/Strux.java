package factory.constrains;

/**
 *
 * @author yury_
 */
import factory.catalog.Razas;
import factory.catalog.Strucha;
import gameEntity.GameObject;
import gameEntity.Player;

public abstract class Strux extends GameObject {

    public final Razas praza;
    public Strucha type;
    private int MAX;

    public Strux(int vida, int fase, Razas ras) {
        super(vida, fase);
        praza = ras;
    }

    public void setMAX(int MAX) {
        this.MAX = MAX;
    }

    public int getMAX() {
        return MAX;
    }

    public void setName(String namex) {
        this.name = namex;
    }

    public int getRespercen(int cant) {
        return (cant * 100) / MAX;
    }
    
    public abstract void exec(Player lolord);
}
