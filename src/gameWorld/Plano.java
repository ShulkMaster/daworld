package gameWorld;

/**
 *
 * @author yury_
 */
import gameEntity.GameObject;
import factory.catalog.Estado;
import factory.types.Strux;
import gameEntity.Player;
import java.awt.Dimension;

public class Plano {

    private final Tile[][] celdas;

    public Plano(int filas, int columna) {
        celdas = new Tile[filas][columna];
        wipeTiles();
    }

    public void addCommandCenter(Player Mylord) {
        if (Mylord.battlefield.celdas[0][0].getEst().equals(Estado.VACIO)) {
            Mylord.battlefield.celdas[0][0].setContenido(new Strux(1500, 0));
            Mylord.battlefield.celdas[0][0].getContenido().name = "centro de Mando: " + Mylord.name;
        }
    }

    public void addGameObject(int x, int y, GameObject obj) {
        if ((x > 0) || (y > 0)) {
            celdas[x][y].setContenido(obj);
        } else {
            System.out.print("\033[35mPocision invalida");
            System.out.println("\033[30m ");
        }
    }

    public void addGameObject(Dimension dim, GameObject obj) {
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
        int conter = 0;
        for (int i = 0; i < iter1; i++) {
            for (int j = 0; j < iter2; j++) {
                celdas[i][j] = new Tile(0, 0);
                System.out.println("Se creo tile: " + conter);
                celdas[i][j].name = ("TILE " + i) + j;
                conter += 1;
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

    public void printContent() {
        int iter1 = celdas.length;
        int iter2 = celdas[0].length;
        for (int i = 0; i < iter1; i++) {
            for (int j = 0; j < iter2; j++) {
                if (celdas[i][j].getEst().equals(Estado.ACTIVO)) {
                    System.out.print("P(" + i + ", " + j + ") " + celdas[i][j].getContenido().name);
                    System.out.print(celdas[i][j].getContenido().getVidaCurrent() + " UDV ");
                    celdas[i][j].getContenido().printVidaBar();
                } else {
                    System.out.println("P(" + i + ", " + j + ") " + celdas[i][j].getEst());
                }
            }
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
}
