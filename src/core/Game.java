package core;

/**
 *
 * @author yury_
 */
//singleton pero mas pro
import gameEntity.Match;
import java.util.Scanner;
public class Game {

    private final Menu mainmenu = Menu.getInstance();
    private final Scanner asker = new Scanner(System.in);
    public static final Maker FABRICA = Maker.getInstace();
    public Match partida;

    private Game() {
    }

    public static Game getInstance() {
        return GameHolder.INSTANCE;
    }

    private static class GameHolder {
        private static final Game INSTANCE = new Game();
    }

    public void start() {
        mainmenu.showmenu();
        System.out.println("Cuantos jugaran?");
        partida = new Match(asker.nextInt());
        partida.Start();
    }

}
