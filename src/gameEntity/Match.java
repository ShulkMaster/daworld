package gameEntity;

/**
 *
 * @author yury_
 */
import factory.catalog.*;

public class Match {

    public final Player[] jugadores;
    private boolean gameOver = false;
    private int turno;
    private static int FASE;

    public Match(int numplayer) {
        jugadores = new Player[numplayer];
        selfInit(numplayer);
        turno = (int) (Math.random() * numplayer);
    }

    private void selfInit(int indicador) {
        for (int i = 0; i < indicador; i++) {
            jugadores[i] = new Player();
        }
    }

    public void Start() {
        System.out.println("Inicia player: " + jugadores[turno].name);
        while (!gameOver) {
            System.out.println("Turno de: " + jugadores[turno].name);
            jugada();
            togglePlayer();
            checkWin();
        }
    }

    private void jugada() {
        if (!jugadores[turno].defeated) {
            while (jugadores[turno].moveAvailable) {
                printOptions(jugadores[turno]);
                jugadores[turno].moveAvailable = false;
            }
            jugadores[turno].moveAvailable = true;
            FASE += 1;
        } else {
            System.out.println("Este jugador ya perdio: " + jugadores[turno].name);
        }

    }

    private void printOptions(Player cPlayer) {
        System.out.println("1) Atacar");
        System.out.println("2) Contruir");
        System.out.println("3) Recolectar");
        System.out.println("4) Defender");
        System.out.println("5) Mejorar estructura");
        System.out.println("6) Ver su estado");
        System.out.println("7) Saltar turno");
        ejecutar(cPlayer.control.nextInt());
    }

    private void ejecutar(int index) {
        switch (index) {
            case 1:
                System.out.println("A quien atacara:");
                printPlayer();
                int selecp = jugadores[0].control.nextInt();
                if (isValidPlayer(selecp)) {
                    System.out.println("Que atacara: ");
                    int contador = 0;
                    for (Player jugado : jugadores) {
                        if (!jugado.name.equals(jugadores[turno].name)) {
                            System.out.println((contador + ") ") + jugado.name);
                            contador += 1;
                        }
                    }
                    InnerController.Attack(jugadores[turno], jugadores[selecp]);
                }
                break;
            case 2:
                System.out.println("Que desea crear:");
                InnerController.addStruck(jugadores[turno]);
                break;
            case 3: //recolertar
                break;
            case 4: //defender
                break;
            case 5: //mejorar
                break;
            case 6:
                jugadores[turno].battlefield.printContent();
                break;
            case 7:
                System.out.println("Esta seguro?");
                System.out.println("No = any number  Si = 1");
                if (jugadores[0].control.nextInt() != 1) {
                    printOptions(jugadores[turno]);
                }
                break;
            default:
                System.err.println("Opcion invalida");
                printOptions(jugadores[turno]);
                break;
        }
    }

    private void checkWin() {
        int jugadoresEnPie = 0;
        for (Player cPlay : jugadores) {
            if (cPlay.battlefield.checkObj(0, 0) <= 0) {
                cPlay.defeated = true;
            } else if (!cPlay.defeated) {
                jugadoresEnPie += 1;
            }
        }
        if (jugadoresEnPie <= 1) {
            gameOver = true;
        }
    }

    private void printPlayer() {
        for (int i = 0; i < jugadores.length; i++) {
            if (!jugadores[i].name.equals(jugadores[turno].name)) {
                System.out.println(i + ")" + jugadores[i].name);
            }
        }
    }

    private void togglePlayer() {
        int nPlayer = jugadores.length;
        turno = (turno + 1) % nPlayer;
        System.out.println("El turno es de: " + jugadores[turno].name);
    }

    private boolean isValidPlayer(int index) {
        boolean valid = true;
        if ((index > jugadores.length) || (index < 0)) {
            valid = false;
            System.err.println("Jugador" + index + "no existe");
        } else if (jugadores[turno].name.equals(jugadores[index].name)) {
            System.err.println("No se puede atacar a si mismo");
            valid = false;
        }
        return valid;
    }

    public int getTurno() {
        return turno;
    }
}
