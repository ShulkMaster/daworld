package factory.gameObjs.struchas;

import factory.catalog.Razas;
import factory.catalog.Strucha;
import factory.constrains.Strux;
import gameEntity.Player;

/**
 *
 * @author yury_
 */
public class Cuartel extends Strux {

    private int troopin;

    public Cuartel(Razas ras) {
        super(1500, 2, ras);
        type = Strucha.CUARTEL;
        nameIT(ras);
        troopin = 0;
    }

    private void nameIT(Razas ras2) {
        setName("Cuartel de: " + ras2.name());
        setMAX(10);
    }

    @Override
    public void printCtn() {
        printVidaBar();
        if (troopin > 0) {
            String fullbar = "████████████████████";
            int aux = (int) (getRespercen(troopin) * 20) / 100;
            fullbar = fullbar.substring(0, aux);
            System.out.print("\033[34m\tEntrenando " + troopin + " tropas");
            System.out.print(fullbar + " " + getRespercen(troopin) + "%");
            System.out.println("\033[30m ");
        } else {
            System.out.println("\033[30m\tNo hay tropas");
        }
    }

    @Override
    public void action() {
    }

    @Override
    public void exec(Player lolord) {
        System.out.println("Entrenar");
         troopin+=1;
    }

}
