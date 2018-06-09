package core;

/**
 *
 * @author yury_
 */
//singleton pero mas pro
import gameEntity.Match;
public class Game {

    private final Menu mainmenu = Menu.getInstance();
    public static final Maker FABRICA = Maker.getInstace();
    private Match partida;

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
        partida = new Match(3);
        partida.Start();
    }

}
