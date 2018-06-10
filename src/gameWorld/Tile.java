package gameWorld;

/**
 *
 * @author yury_
 */
import gameEntity.GameObject;
import factory.catalog.Estado;

public class Tile extends GameObject {

    private GameObject contenido;
    private Estado est;

    public Tile(int vida, int fase) {
        super(vida, fase);
        est = Estado.VACIO;
    }

    public GameObject getContenido() {
        return contenido;
    }

    public void setContenido(GameObject contenido) {
        if (this.est == Estado.VACIO) {
            this.contenido = contenido;
            est = Estado.ACTIVO;
        } else {
            System.out.println("Ya esta en uso");
        }
    }

    public Estado getEst() {
        return est;
    }

    @Override
    public void actionPerformed() {
    }

}
