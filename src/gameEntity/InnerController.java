package gameEntity;

import factory.catalog.Razas;
import factory.catalog.Recursos;
import factory.catalog.Strucha;
import factory.constrains.Strux;
import factory.gameObjs.struchas.Cuartel;
import factory.gameObjs.struchas.Extractor;
import java.awt.Dimension;
import java.util.ArrayList;
import core.Game;
import factory.gameObjs.troops.Tropa;

/**
 *
 * @author yury_
 */
public class InnerController {

    public static Dimension askCoordi(Player Clord) {
        int x, y;
        System.out.println("Ingrese columna");
        x = Clord.control.nextInt();
        System.out.println("Ingrese fila");
        y = Clord.control.nextInt();
        return new Dimension(x, y);
    }

    public static void Attack(Player attacker, Player victim) {
        int x, y;
        victim.battlefield.printContent();
        System.out.println("Ingrese columna");
        x = attacker.control.nextInt();
        System.out.println("Ingrese fila");
        y = attacker.control.nextInt();
        if ((x == 0) && (y == 0) && !victim.battlefield.morObj()) {
            victim.battlefield.receiveAttack(x, y, 300);
        } else if ((x == 0) && (y == 0) && victim.battlefield.morObj()) {
            System.err.println("No se puede atacar al centro de mando si hay mas edificion en pie");
        } else {
            ArrayList<Strux> mono = attacker.battlefield.getStrucType(Strucha.CUARTEL);
            int zanverx = 0;
            zanverx = mono.stream().map((temp) -> temp.getMAX()).reduce(zanverx, Integer::sum);
            victim.battlefield.receiveAttack(x, y, zanverx);
            System.out.println("Son de daño:"+zanverx);
        }
    }

    public static void traintroops(Player mylord) {
        ArrayList<Strux> auxc = mylord.battlefield.getStrucType(Strucha.CUARTEL);
        for (int i = 0; i < auxc.size(); i++) {
            System.out.print(i + ")");
            auxc.get(i).printCtn();
        }
        if (auxc.size() > 0) {
            System.out.println("Seleccione un cuartel para entrenar");
            int amp = mylord.control.nextInt();
            printTroop(mylord);
            auxc.get(amp).exec(mylord);
        } else {
            System.err.println("Cree cuarteles primero");
        }

    }

    public static void createTroop(Cuartel Strex) {
        int[] soldStatc = statscalc(Strex.praza);
        Tropa trupix;
        switch (Game.getInstance().partida.getPlayer().control.nextInt()) {
            case 1:
                soldStatc[5] = 1;
                trupix = new Tropa(soldStatc);
                trupix.name = "Basic " + Strex.praza;
                Strex.setMoreTroop(trupix);
                break;
            case 2:
                soldStatc[5] = 2;
                soldStatc = statsUpdate(soldStatc, 1);
                trupix = new Tropa(soldStatc);
                trupix.name = "Pro " + Strex.praza;
                Strex.setMoreTroop(trupix);
                break;
            case 3:
                soldStatc[5] = 3;
                soldStatc = statsUpdate(soldStatc, 2);
                trupix = new Tropa(soldStatc);
                trupix.name = "Ultimate " + Strex.praza;
                Strex.setMoreTroop(trupix);
                break;
            default:
                System.out.println("Opcion invalida");
                createTroop(Strex);
                break;
        }
    }

    public static void addStruck(Player mylord) {
        for (int i = 1; i < Strucha.values().length; i++) {
            System.out.println(i + ")" + Strucha.values()[i]);
        }
        switch (mylord.control.nextInt()) {
            case 1:
                showRsourc(mylord.getRaza());
                int temp = mylord.control.nextInt();
                Extractor cont = swExtrac(temp, mylord.getRaza());
                cont.nameit(mylord, getRsourc(mylord.getRaza())[temp]);
                mylord.battlefield.addGameObject(askCoordi(mylord), cont);
                break;
            case 2:
                Cuartel cuarx = new Cuartel(mylord.getRaza());
                mylord.battlefield.addGameObject(askCoordi(mylord), cuarx);
                break;
        }

    }

    private static void showRsourc(Razas R) {
        Recursos[] recurs = getRsourc(R);
        System.out.println("0)Estractor de: " + recurs[0]);
        System.out.println("1)Estractor de: " + recurs[1]);
        System.out.println("2)Estractor de: " + recurs[2]);
    }

    private static Recursos[] getRsourc(Razas R) {
        Recursos[] recurs;
        switch (R) {
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
            default:
                System.err.println("Raza defectuosa");
                recurs = cutArray(6, 8);
                break;
        }
        return recurs;
    }

    private static Recursos[] cutArray(int ini, int fin) {
        Recursos[] aux = new Recursos[3];
        int cont = 0;
        for (int i = ini; i < fin + 1; i++) {
            aux[cont] = Recursos.values()[i];
            cont++;
        }
        return aux;
    }

    private static Extractor swExtrac(int index, Razas R1) {
        Extractor Cobj;
        Recursos[] aux = getRsourc(R1);
        switch (index) {
            case 0:
                Cobj = new Extractor(800, 800, R1, aux[0]);
                break;
            case 1:
                Cobj = new Extractor(800, 500, R1, aux[1]);
                break;
            case 2:
                Cobj = new Extractor(1020, 300, R1, aux[2]);
                break;
            default:
                System.err.println("Le toco un extractor defectuoso");
                Cobj = new Extractor(50, 2, R1, aux[0]);
                break;
        }
        return Cobj;
    }

    private static void printTroop(Player lord) {
        int[] ram = statscalc(lord.getRaza());
        System.out.println("Tropas disponibles: " + lord.getRaza());
        System.out.print("1) Basic " + lord.getRaza() + ": " + ram[0] + "UD, " + ram[1] + "UDV, " + ram[2] + " Fase Entrenamiento ");
        System.out.println(ram[3] + " " + lord.battlefield.CM.recurs[0].name() + ", " + ram[4] + " " + lord.battlefield.CM.recurs[1]);
        ram = statsUpdate(ram, 1);
        System.out.print("2) Pro " + lord.getRaza() + ": " + ram[0] + "UD, " + ram[1] + "UDV, " + ram[2] + " Fase Entrenamiento ");
        System.out.println(ram[3] + " " + lord.battlefield.CM.recurs[0].name() + ", " + ram[4] + " " + lord.battlefield.CM.recurs[2]);
        ram = statscalc(lord.getRaza());
        ram = statsUpdate(ram, 2);
        System.out.print("3) Ultimate " + lord.getRaza() + ": " + ram[0] + "UD, " + ram[1] + "UDV, " + ram[2] + " Fase Entrenamiento ");
        System.out.println(ram[3] + " " + lord.battlefield.CM.recurs[1].name() + ", " + ram[4] + " " + lord.battlefield.CM.recurs[2]);
    }

    private static int[] statsUpdate(int[] base, int xtend) {
        int[] aux = base;
        float[] shout = {1.0f, 1.0f, 1.0f, 1.0f, 1.0f};
        switch (xtend) {
            case 0:
                //0 attack
                //1 life
                //2 fase
                //3 recurso 1
                //4 recurso 2
                break;
            case 1:
                shout[0] = 2.5f;
                shout[1] = 2.0f;
                shout[2] = 1.0f;
                shout[3] = 2.6f;
                shout[4] = 1.7f;
                break;
            case 2:
                shout[0] = 3.6f;
                shout[1] = 3.3f;
                shout[2] = 2.0f;
                shout[3] = 3.0f;
                shout[4] = 2.0f;
                break;
        }
        for (int i = 0; i < 5; i++) {
            if (i != 2) {
                aux[i] *= shout[i];
            } else {
                aux[i] += shout[i];
            }
        }
        return aux;
    }

    private static int[] statscalc(Razas R) {
        int[] aux = new int[6];
        switch (R) {
            case HUMANO:
                aux[0] = 45;
                aux[1] = 100;
                aux[2] = 1;
                aux[3] = 180;
                aux[4] = 90;
                break;
            case ELFO:
                aux[0] = 35;
                aux[1] = 160;
                aux[2] = 2;
                aux[3] = 210;
                aux[4] = 110;
                break;
            case DEMONIO:
                aux[0] = 95;
                aux[1] = 155;
                aux[2] = 2;
                aux[3] = 310;
                aux[4] = 170;
                break;
            case ANGEL:
                aux[0] = 150;
                aux[1] = 110;
                aux[2] = 2;
                aux[3] = 170;
                aux[4] = 120;
                break;
        }
        return aux;
    }

}
