package factory.types.struchas;

/**
 *
 * @author yury_
 */
import factory.catalog.Razas;
import factory.catalog.Recursos;
import gameEntity.GameObject;

public class CentroMando extends GameObject{

    public Razas praza;
    int recur1, recur2, recur3;
    public final int MAX = 10000;
    public Recursos[] recurs;

    public CentroMando(int vida, Razas ras) {
        super(vida,0);
        praza = ras;
        recurs = new Recursos[3];
        recur1 = 3000;
        recur2 = 1000;
        recur3 = 600;
    }

    public void nameresour(Razas rax) {
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

    @Override
    public void actionPerformed() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
