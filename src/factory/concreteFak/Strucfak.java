package factory.concreteFak;

/**
 *
 * @author yury_
 */
import factory.catalog.Strucha;
import factory.constrains.Structura;
import factory.types.struchas.CentroMando;
import factory.ObjMaker;
import factory.catalog.Razas;
import factory.types.struchas.Extractor;

public class Strucfak implements ObjMaker {

    Razas R;
    
    @Override
    public Structura getStruc(Strucha estc) {
        Structura strukx;
        switch (estc) {
            case CENTROMANDO:
               // strukx = new CentroMando(2100, R);
                break;
            case RECURSO:
                strukx = new Extractor();
                break;
            /*
            case RECURSO2:
                break;
            case RECURSO1:
                break;
             */
            default:
                System.err.println("Objeto corrupto");
               // strukx = new CentroMando(0,R);
                break;
        }
        return null;
    }

    public void setRaza(Razas r) {
        R = r;
    }

}
