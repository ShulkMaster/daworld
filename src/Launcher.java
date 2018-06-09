/*
        System.out.println("\033[30mEste texto es Negro");
        System.out.println("\033[31mEste texto es Rojo");
        System.out.println("\033[32m Este texto es Verde");
        System.out.println("\033[33m Este texto es Amarillo");
        System.out.println("\033[34mEste texto es Azul");
        System.out.println("\033[35mEste texto es Magenta");
        System.out.println("\033[36mEste texto es Cyan");
        System.out.println("\033[37mEste texto es Blanco");
        System.out.println((char)27 + "[34;43mEjemplo de texto azul y fondo amarillo");
 */
import core.Game;

/**
 *
 * @author yury_
 */
public class Launcher {

    private static Game yuryworld;

    public static void main(String[] args) {
        verifyModules();
        InitGame();
        launchGame();
    }

    private static void verifyModules() {
        System.out.println("All ready");
    }

    private static void InitGame() {
        System.out.println("game ready");
        yuryworld = Game.getInstance();
    }

    private static void launchGame() {
        System.out.println("game starts now");
        yuryworld.start();
    }

}
