package gameWorld;

/**
 *
 * @author yury_
 */
import factory.catalog.Estado;
import factory.catalog.Strucha;
import factory.constrains.Strux;
import factory.gameObjs.struchas.CentroMando;
import gameEntity.Player;
import java.awt.Dimension;
import java.util.ArrayList;

public class Plano {

    private final Tile[][] celdas;
    public CentroMando CM;

    public Plano(int filas, int columna) {
        celdas = new Tile[filas][columna];
        wipeTiles();
    }

    public void addCommandCenter(Player Mylord) {
        if (Mylord.battlefield.celdas[0][0].getEst().equals(Estado.VACIO)) {
            CentroMando ctn = new CentroMando(2500, Mylord.getRaza());
            ctn.nameit();
            CM = ctn;
            Mylord.battlefield.celdas[0][0].setContenido(ctn);
            Mylord.battlefield.celdas[0][0].getContenido().name += " de " + Mylord.name;
        }
    }

    public void addGameObject(int x, int y, Strux obj) {
        if ((x > 0) || (y > 0)) {
            celdas[x][y].setContenido(obj);
        } else {
            System.out.print("\033[35mPocision invalida");
            System.out.println("\033[30m ");
        }
    }

    public void addGameObject(Dimension dim, Strux obj) {
        if (isValidPos(dim.width, dim.height)) {
            celdas[dim.width][dim.height].setContenido(obj);
        } else {
            System.out.print("\033[35mPocision invalida");
            System.out.println("\033[30m ");
        }
    }

    public int checkObj(int x, int y) {
        return celdas[x][y].getContenido().getVidaCurrent();
    }

    private void wipeTiles() {
        int iter1 = celdas.length;
        int iter2 = celdas[0].length;
        for (int i = 0; i < iter1; i++) {
            for (int j = 0; j < iter2; j++) {
                celdas[i][j] = new Tile(0, 0);
                celdas[i][j].name = ("TILE " + i) + j;
            }
        }
    }

    public void receiveAttack(int x, int y, int dmg) {
        if (isValidPos(x, y)) {
            if (celdas[x][y].getEst().equals(Estado.ACTIVO)) {
                celdas[x][y].getContenido().setDamage(dmg);
            } else {
                System.err.println("Ataco algo vacio");
            }
        } else {
            System.err.println("Pocision Invalida");
        }
    }

    private boolean isValidPos(int posx, int posy) {
        boolean valid = true;
        if ((posx >= celdas.length) || (posx < 0)) {
            valid = false;
        } else if ((posy >= celdas[0].length) || (posy < 0)) {
            valid = false;
        }
        return valid;
    }

    public boolean morObj() {
        boolean moreObj = false;
        int iter1 = celdas.length;
        int iter2 = celdas[0].length;
        int more = 0;
        for (int i = 0; i < iter1; i++) {
            for (int j = 0; j < iter2; j++) {
                if (celdas[i][j].getEst().equals(Estado.ACTIVO)) {
                    more += 1;
                }
            }
        }
        if (more > 1) {
            moreObj = true;
        }
        return moreObj;
    }

    public void printContent() {
        int iter1 = celdas.length;
        int iter2 = celdas[0].length;
        for (int i = 0; i < iter1; i++) {
            for (int j = 0; j < iter2; j++) {
                System.out.print("P(" + i + ", " + j + "): ");
                celdas[i][j].printCtn();
            }
        }
    }

    public void clearContent() {
        int iter1 = celdas.length;
        int iter2 = celdas[0].length;
        for (int i = 0; i < iter1; i++) {
            for (int j = 0; j < iter2; j++) {
                celdas[i][j].action();
            }
        }
    }

    public void updateContent() {
        int iter1 = celdas.length;
        int iter2 = celdas[0].length;
        for (int i = 0; i < iter1; i++) {
            for (int j = 0; j < iter2; j++) {
                if (celdas[i][j].getEst().equals(Estado.ACTIVO)) {
                    celdas[i][j].getContenido().action();
                }
            }
        }
    }

    public void execute(Player lolord, Strucha exetur) {
        int iter1 = celdas.length;
        int iter2 = celdas[0].length;
        for (int i = 0; i < iter1; i++) {
            for (int j = 0; j < iter2; j++) {
                if (celdas[i][j].getEst().equals(Estado.ACTIVO)) {
                    if (celdas[i][j].getContenido().type.equals(exetur)) {
                        celdas[i][j].getContenido().exec(lolord);
                    }
                }
            }
        }

    }

    public ArrayList<Strux> getStrucType(Strucha typexer) {
        int iter1 = celdas.length;
        int iter2 = celdas[0].length;
        ArrayList<Strux> aux = new ArrayList<>();
        for (int i = 0; i < iter1; i++) {
            for (int j = 0; j < iter2; j++) {
                if (celdas[i][j].getEst().equals(Estado.ACTIVO)) {
                    if (celdas[i][j].getContenido().type.equals(typexer)) {
                        aux.add(celdas[i][j].getContenido());
                    }
                }
            }
        }
        return aux;

    }

}
