package gameEntity;

import factory.catalog.Strucha;

/**
 *
 * @author yury_
 */
public class Match {

    public final Player[] jugadores;
    private boolean gameOver = false;
    private int turno;
    private int subfase = 0;
    private int FASE = 0;

    public Match(int numplayer) {
        jugadores = new Player[numplayer];
        turno = (int) (Math.random() * numplayer);
    }

    private void selfInit(int indicador) {
        for (int i = 0; i < indicador; i++) {
            jugadores[i] = new Player();
        }
    }

    public void Start() {
        selfInit(jugadores.length);
        while (!gameOver) {
            jugada();
            togglePlayer();
            checkWin();
        }
    }

    private void infoFase() {
        System.out.println("\033[34m_________________________________________");
        System.out.println("\033[34m               FASE " + FASE);
        System.out.println("\033[34m_________________________________________");
        System.out.println("\033[30m ");
    }

    private void faseCalculation() {
        if (subfase >= jugadores.length) {
            incrementFase();
            subfase = 0;
        }
    }

    private void incrementFase() {
        for (Player cplayer : jugadores) {
            System.out.print("\033[34mResumen: ");
            System.out.println(cplayer.name);
            cplayer.battlefield.updateContent();
            cplayer.battlefield.clearContent();
        }
        FASE += 1;
    }

    private void jugada() {
        if (!jugadores[turno].defeated) {
            while (jugadores[turno].moveAvailable) {
                printOptions(jugadores[turno]);
            }
            jugadores[turno].moveAvailable = true;
            subfase += 1;
            faseCalculation();
        } else {
            System.out.println("Este jugador ya perdio: " + jugadores[turno].name);
            faseCalculation();
        }

    }

    private void printOptions(Player cPlayer) {
        infoFase();
        System.out.println("\033[34m_________________________________________");
        System.out.println("\033[34m               Turno " + jugadores[turno].name);
        System.out.println("\033[34m_________________________________________");
        System.out.println("\033[30m ");
        System.out.println("1) Atacar");
        System.out.println("2) Entrenar");
        System.out.println("3) Contruir");
        System.out.println("4) Recolectar");
        System.out.println("5) Defender");
        System.out.println("6) Mejorar estructura");
        System.out.println("7) Ver su estado");
        System.out.println("8) Saltar turno");
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
                InnerController.traintroops(jugadores[turno]);
                break;
            case 3:
                System.out.println("Que desea crear:");
                InnerController.addStruck(jugadores[turno]);
                break;
            case 4:
                jugadores[turno].battlefield.execute(jugadores[turno], Strucha.RECURSO);
                break;
            case 5: //def
                break;
            case 6:
                //mejorar
                break;
            case 7:
                jugadores[turno].battlefield.printContent();
                break;
            case 8:
                System.out.println("Esta seguro?");
                System.out.println("No = any number  Si = 1");
                if (jugadores[0].control.nextInt() != 1) {
                    printOptions(jugadores[turno]);
                } else {
                    jugadores[turno].moveAvailable = false;
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
            for (Player cPlay : jugadores) {
                if (!cPlay.defeated) {
                    System.out.println("\033[34m_________________________________________");
                    System.out.println("\033[34m              GANADOR " + cPlay.name);
                    System.out.println("\033[34m_________________________________________");
                }
            }
            gameOver = true;
        }
    }

    private void printPlayer() {
        for (int i = 0; i < jugadores.length; i++) {
            if (!jugadores[i].name.equals(jugadores[turno].name) && !jugadores[i].defeated) {
                System.out.println(i + ")" + jugadores[i].name);
            }
        }
    }

    private void togglePlayer() {
        int nPlayer = jugadores.length;
        turno = (turno + 1) % nPlayer;
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

    public Player getPlayer() {
        return jugadores[turno];
    }

    public int getFASE() {
        return FASE;
    }
}
