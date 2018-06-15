package core;

/**
 *
 * @author yury_
 */
import factory.ObjMaker;
import factory.gameObjs.troops.Tropa;

public class Maker{

    private static Maker creador;

    private Maker() {
    }

    public static Maker getInstace() {
        if (creador == null) {
            creador = new Maker();
        } else {
            System.err.println("Ya eciste instancia de la Abstrac Factory");
        }
        return creador;
    }
/*
    public ObjMaker makeObjectFak(GameObjectType type) {
        ObjMaker CmakeObjectFak;
        switch (type) {
            case STRUCHA:
                System.out.println("New " + type);
                CmakeObjectFak = new Strucfak();
                break;
            case SPELLS:
                System.out.println("New " + type);
                CmakeObjectFak = new Spells();
                break;
            case TRANSPORT:
                System.out.println("New " + type);
                CmakeObjectFak = new TransportFak();
                break;
            case TROOP:
                System.out.println("New " + type);
                CmakeObjectFak = new TroopFak();
                break;
            default:
                System.err.println("WFT error type desconocido:" + type);
                CmakeObjectFak = null;
        }
        return CmakeObjectFak;
    }    
*/
    public void createTroop(int[] order){
        
    
    }
}
