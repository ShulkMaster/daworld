package gameEntity;

/**
 *
 * @author yury_
 */
import factory.catalog.Razas;
import java.util.Scanner;
import gameWorld.Plano;

public class Player {

    public final Scanner control = new Scanner(System.in);
    public String name;
    private Razas PlayerRaza;
    public boolean defeated = false, moveAvailable;
    public Plano battlefield;
    private final String[] description = {": Son deviles pero contruyen y se entrenan rapdio",
    ": Tienen buenos equipamientos y pueden usar magia pero bajo daño por que no les gusta lastimar",
    ": Pueden usar magia demoniaca pero son caros para crear",
    ": Hacen mas daño a las otras razas de pero son lentos construyendo"};

    public Player() {
        createPorfile();
        System.out.println("Se creo Jugador: " + this.name);
        moveAvailable = true;
    }

    private void createPorfile() {
        System.out.print("Ingrese su nombre de jugador: ");
        name = control.nextLine();
        System.out.println("Seleccione su raza");
        razaPicker();
        battlefield = new Plano(3, 3);
        battlefield.addCommandCenter(this);
    }

    private void razaPicker() {
        for (int i = 0; i < Razas.values().length; i++) {
            System.out.println(i + ")" + Razas.values()[i] + description[i]);
        }
        switch (control.nextInt()) {
            case 0:
                PlayerRaza = Razas.HUMANO;
                break;
            case 1:
                PlayerRaza = Razas.ELFO;
                break;
            case 2:
                PlayerRaza = Razas.DEMONIO;
                break;
            case 3:
                PlayerRaza = Razas.ANGEL;
                break;
            default:
                System.err.println("Opcion invalida");
                razaPicker();
                break;
        }
    }

    public Razas getRaza() {
        return PlayerRaza;
    }

}
