package factory.gameObjs.struchas;

/**
 *
 * @author yury_
 */
import factory.catalog.Razas;
import factory.catalog.Recursos;
import factory.catalog.Strucha;
import factory.constrains.Strux;
import gameEntity.Player;

public class CentroMando extends Strux {

    public int[] recux;
    public Recursos[] recurs;

    public CentroMando(int vida, Razas ras) {
        super(vida, 0, ras);
        recurs = new Recursos[3];
        recux = new int[3];
        recux[0] = 5000;
        recux[1] = 2500;
        recux[2] = 1250;
        nameresour(ras);
        type = Strucha.CENTROMANDO;
    }

    public void nameit() {
        setName("Centro Mando: " + praza + " ");
        setMAX(8000);
    }

    private void nameresour(Razas rax) {
        switch (rax) {
            case HUMANO:
                recurs = cutArray(0, 2);
                break;
            case ELFO:
                recurs = cutArray(3, 5);
                break;
            case DEMONIO:
                recurs = cutArray(6, 8);
                break;
            case ANGEL:
                recurs = cutArray(9, 11);
                break;
        }
    }

    private Recursos[] cutArray(int ini, int fin) {
        Recursos[] aux = new Recursos[3];
        int cont = 0;
        for (int i = ini; i < fin + 1; i++) {
            aux[cont] = Recursos.values()[i];
            cont++;
        }
        return aux;
    }

    public void getResort(int cant, Recursos rex) {
        for (int i = 0; i < recux.length; i++) {
            if (rex.equals(recurs[i])) {
                recux[i] += cant;
                if (recux[i] > getMAX()) {
                    recux[i] = getMAX();
                }
            }
        }

    }

    @Override
    public void action() {
    }

    @Override
    public void printCtn() {
        printVidaBar();
        for (int i = 0; i < 3; i++) {
            if (recux[i] >= 0) {
                String fullbar = "████████████████████";
                int aux = (getRespercen(recux[i] * 20) / 100);
                fullbar = fullbar.substring(0, aux);
                System.out.print("\033[34m\t" + recurs[i] + ":" + recux[i] + " UND ");
                System.out.print(fullbar + " " + getRespercen(recux[i]) + "%");
                System.out.println("\033[30m ");
            } else {
                System.out.println("\033[30mNo posee este recurso");
            }
        }
    }

    @Override
    public void exec(Player lolord) {
    }
}
