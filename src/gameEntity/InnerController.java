package gameEntity;

import factory.catalog.Razas;
import factory.catalog.Recursos;
import factory.catalog.Strucha;
import factory.constrains.Strux;
import factory.gameObjs.struchas.Cuartel;
import factory.gameObjs.struchas.Extractor;
import java.awt.Dimension;
import java.util.ArrayList;

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
            victim.battlefield.receiveAttack(x, y, 300);
        }
    }

    public static void traintroops(Player mylord) {
        ArrayList<Strux> auxc = mylord.battlefield.getStrucType(Strucha.CUARTEL);
        for (int i = 0; i < auxc.size(); i++) {
            System.out.print(i + ")");
            auxc.get(i).printCtn();
        }
        if(auxc.size()>0){
        System.out.println("Seleccione un cuartel para entrenar");
        mylord.control.nextInt();
        }else{
            System.err.println("Cree cuarteles primero");
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
                Cobj = new Extractor(800, 120, R1, aux[0]);
                break;
            case 1:
                Cobj = new Extractor(800, 60, R1, aux[1]);
                break;
            case 2:
                Cobj = new Extractor(1020, 25, R1, aux[2]);
                break;
            default:
                System.err.println("Le toco un extractor defectuoso");
                Cobj = new Extractor(50, 2, R1, aux[0]);
                break;
        }
        return Cobj;
    }

}
