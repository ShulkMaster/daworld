package gameWorld;

/**
 *
 * @author yury_
 */
import gameEntity.GameObject;
import factory.constrains.Strux;
import factory.catalog.Estado;

public class Tile extends GameObject {

    private Strux contenido;
    private Estado est;

    public Tile(int vida, int fase) {
        super(vida, fase);
        est = Estado.VACIO;
    }

    public Strux getContenido() {
        return contenido;
    }

    public void setContenido(Strux contenido) {
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
    public void printCtn() {
        if (est.equals(Estado.ACTIVO)) {
            contenido.printCtn();
        }else{
            System.out.println(est);
        }

    }

    @Override
    public void action() {
        if (est.equals(Estado.ACTIVO)) {
            if (contenido.getVidaCurrent() <= 0) {
                System.err.println(contenido.name + " Fue destruido");
                est = Estado.VACIO;
            }
        }
    }

}
