package factory.gameObjs.struchas;

import factory.catalog.Razas;
import factory.catalog.Strucha;
import factory.constrains.Strux;
import factory.gameObjs.troops.Tropa;
import gameEntity.InnerController;
import gameEntity.Player;
import java.util.ArrayList;

/**
 *
 * @author yury_
 */
public class Cuartel extends Strux {

    private final Tropa[] troopin;
    private final Tropa[] troopHecha;

    private ArrayList<Tropa> getTroopHecha() {
        ArrayList<Tropa> auxy = new ArrayList<>();
        for (int i = 0; i < troopHecha.length; i++) {
            if (troopHecha[i] != null) {
                auxy.add(troopHecha[i]);
                troopHecha[i] = null;
            }
        }
        return auxy;
    }

    public Cuartel(Razas ras) {
        super(1500, 2, ras);
        type = Strucha.CUARTEL;
        nameIT(ras);
        troopin = new Tropa[10];
        troopHecha = new Tropa[10];
    }

    private void nameIT(Razas ras2) {
        setName("Cuartel de: " + ras2.name());
        setMAX(10);
    }

    public void setMoreTroop(Tropa trupa) {
        int libre = -1;
        for (int i = 0; i < troopin.length; i++) {
            if (troopin[i] == null) {
                libre = i;
                break;
            }
        }
        if (libre < 0) {
            System.err.println("No hay espacio en su cuartel");
        } else {
            troopin[libre] = trupa;
            System.out.println(" Soldado añadido a la cola" + trupa.name);
        }

    }

    private int activaTroop(Tropa[] soldados) {
        int activo = 0;
        for (Tropa troopin1 : soldados) {
            if (troopin1 != null) {
                activo += 1;
            }
        }
        return activo;
    }

    private int[] contTupa(Tropa[] soldados) {
        int bas = 0, pro = 0, ulti = 0;
        int[] cantidad = new int[3];
        for (Tropa trap : soldados) {
            if (trap != null) {
                switch (trap.trooptype) {
                    case 1:
                        bas += 1;
                        break;
                    case 2:
                        pro += 1;
                        break;
                    case 3:
                        ulti += 1;
                        break;
                }
            }
        }
        cantidad[0] = bas;
        cantidad[1] = pro;
        cantidad[2] = ulti;
        return cantidad;
    }

    private int nextAvailable() {
        int libre = -1;
        for (int i = 0; i < troopHecha.length; i++) {
            if (troopHecha[i] == null) {
                libre = i;
                break;
            }
        }
        return libre;
    }

    private void traintroop() {
        int killer = 0;
        for (Tropa troopin1 : troopin) {
            if (troopin1 != null) {
                if (troopin1.isBuild()) {
                    if (nextAvailable() >= 0) {
                        System.out.println(troopin1.name + "Fue centrenado");
                        troopHecha[nextAvailable()] = troopin1;
                        troopin[killer] = null;
                    }
                } else {
                    System.out.println(troopin1.name + " Se creara en fase:" + troopin1.getFasecicle());
                }
            }
            killer += 1;
        }
    }

    @Override
    public void printCtn() {
        if (isBuild()) {
            printVidaBar();
            if (activaTroop(troopin) > 0) {
                String fullbar = "████████████████████";
                int aux = (int) (getRespercen(activaTroop(troopin)) * 20) / 100;
                fullbar = fullbar.substring(0, aux);
                System.out.print("\033[34m\tCapacidad tropas: " + praza);
                System.out.println(fullbar + " " + getRespercen(activaTroop(troopin)) + "%");
                System.out.println("tropas listas:");
                int[] msiTrupas = contTupa(troopHecha);
                System.out.println(praza + " Basico:" + msiTrupas[0]);
                System.out.println(praza + " Pro:" + msiTrupas[1]);
                System.out.println(praza + " Ultimate:" + msiTrupas[2]);
                System.out.println("\033[30m ");
            } else {
                System.out.println("\033[30m\tNo hay tropas");
            }
        } else {
            System.out.println(name + " estara listo en fase: " + getFasecicle());
        }
    }

    @Override
    public void action() {
        if (isBuild()) {
            traintroop();
        }
    }

    @Override
    public void exec(Player lolord) {
        if (isBuild()) {
            InnerController.createTroop(this);
        } else {
            System.out.println(name + " estara listo en fase: " + getFasecicle());
        }
    }

    @Override
    public int getMAX() {
        ArrayList<Tropa> fuerza = getTroopHecha();
        int vergazo = 0;
        vergazo = fuerza.stream().map((trops) -> trops.getAttck()).reduce(vergazo, Integer::sum);
        return vergazo;
    }

}
