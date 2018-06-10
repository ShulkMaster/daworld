package factory.types.struchas;

/**
 *
 * @author yury_
 */
import gameEntity.GameObject;

public class Extractor extends GameObject {

    private GameObject recur;
    private int recCant;
    private final int maxRate;

    public Extractor(int vida, int maxr) {
        super(vida, 2);
        maxRate = maxr;
        recCant = 0;
    }

    public int getResor() {
        int aux = recCant;
        recCant = 0;
        return aux;
    }

    @Override
    public void actionPerformed() {
        recCant += maxRate;
    }

}
