package factory.gameObjs.struchas;

/**
 *
 * @author yury_
 */
import factory.catalog.Razas;
import factory.catalog.Strucha;
import factory.catalog.Recursos;
import factory.constrains.Strux;
import gameEntity.Player;

public class Extractor extends Strux {

    private int recCant;
    private final Recursos rexx;
    private final int maxRate;

    public Extractor(int vida, int maxr, Razas R, Recursos rex) {
        super(vida, 2, R);
        maxRate = maxr;
        rexx = rex;
        recCant = 0;
        type = Strucha.RECURSO;
    }

    public void nameit(Player mylord, Recursos rec) {
        setName(mylord.name + " " + "Extractor de: " + rec + " ");
        setMAX(2000);
    }

    private void slope() {
        if (recCant < getMAX()) {
            System.out.println("\033[34m" + name + " +" + maxRate + "UND");
            recCant += maxRate;
        } else {
            recCant = getMAX();
        }
    }

    @Override
    public void printCtn() {
        if (isBuild()) {
            printVidaBar();
            if (recCant >= 0) {
                String fullbar = "████████████████████";
                int aux = (int) (getRespercen(recCant) * 20) / 100;
                fullbar = fullbar.substring(0, aux);
                System.out.print("\033[34m\t" + rexx + ":" + recCant + " UND ");
                System.out.print(fullbar + " " + getRespercen(recCant) + "%");
                System.out.println("\033[30m ");
            } else {
                System.out.println("\033[30mNo posee este recurso");
            }
        } else {
            System.out.println(name + " estara listo en fase: " + getFasecicle());
        }
    }

    @Override
    public void action() {
        if (isBuild()) {
            slope();
        } else {
            System.out.println(name + " estara listo en fase: " + getFasecicle());
        }
    }

    public int getJuice() {
        int aux;
        if (isBuild()) {
            aux = recCant;
            recCant = 0;
        } else {
            aux = 0;
            System.out.println(name + " estara listo en fase: " + getFasecicle());
        }
        return aux;
    }

    @Override
    public void exec(Player lolord) {
        lolord.battlefield.CM.getResort(getJuice(), rexx);
    }

}
