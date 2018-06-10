package gameEntity;

import factory.catalog.Razas;
import factory.catalog.Recursos;
import factory.catalog.Strucha;
import factory.types.struchas.Extractor;
import java.awt.Dimension;

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
        victim.battlefield.receiveAttack(x, y, 300);
    }

    public static void addStruck(Player mylord) {

        for (int i = 1; i < Strucha.values().length; i++) {
            System.out.println(i + ")" + Strucha.values()[i]);
        }
        switch (mylord.control.nextInt()) {
            case 1:
                showRsourc(mylord.getRaza());
                Extractor cont = swExtrac(mylord.control.nextInt());
                mylord.battlefield.addGameObject(askCoordi(mylord), cont);
                break;
        }

    }

    private static void showRsourc(Razas R) {
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
        System.out.println("0)Mina de: " + recurs[0]);
        System.out.println("1)Estractor de: " + recurs[1]);
        System.out.println("2)Purifucador de: " + recurs[2]);

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

    private static Extractor swExtrac(int index) {
        Extractor Cobj;
        switch (index) {
            case 0:
                Cobj = new Extractor(800, 120);
                break;
            case 1:
                Cobj = new Extractor(800, 60);
                break;
            case 2:
                Cobj = new Extractor(1020, 25);
                break;
            default:
                System.err.println("Le toco un extractor defectuoso");
                Cobj = new Extractor(50, 2);
                break;
        }
        return Cobj;
    }

}
