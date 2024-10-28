
import java.util.Scanner;

import model.SoccerController;

public class Main {
    // Attributes
    Scanner sc;
    SoccerController controller;

    /**
     * Name: Main
     * <br/>
     * Description: The constructor of the Main.
     * <br/>
     * Pre: None
     * <br/>
     * Post: The Main has been created.
     */
    public Main() {
        sc = new Scanner(System.in);
    }

    public static void main(String[] args) {
        Main main = new Main();
        System.out.println("Bienvenido al sistema de gestión de equipos de fútbol");
        System.out.println("Por favor, introduzca el nombre del torneo:");
        String name = main.sc.nextLine();
        main.controller = new SoccerController(name);
        main.menu();
    }

    /**
     * Name: menu
     * <br/>
     * Description: The menu of the program is displayed, and the user is prompted
     * to select an option.
     * <br/>
     * Pre: None
     * <br/>
     * Post: The user has selected an option.
     */
    public void menu() {
        int option = 0;
        do {
            System.out.println("-----------------------");
            System.out.println("1. Agregar equipo");
            System.out.println("2. Registrar jugador");
            System.out.println("3. Registrar árbitro");
            System.out.println("4. Inicializar el torneo (pre-cargar datos)");
            System.out.println("5. Generar fixture");
            System.out.println("6. Asignar arbitros a un partido");
            System.out.println("7. Registrar resultado de un partido");
            System.out.println("8. Salir");
            System.out.println("-----------------------");
            option = sc.nextInt();
            sc.nextLine();
            switch (option) {
                case 1:
                    registerTeam();
                    break;
                case 2:
                    registerPlayer();
                    break;
                case 3:
                    registerReferee();
                    break;
                case 4:
                    initializeTournament();
                    break;
                case 5:
                    generateGroupStageFixture();
                    break;
                case 6:
                    assignRefereesToMatch();
                    break;
                case 7:
                    registerMatchResult();
                    break;
                case 8:
                    System.out.println("Hasta pronto");
                    break;
                default:
                    System.out.println("Opción incorrecta");
            }
        } while (option != 8);
    }

    /**
     * Name: registerTeam
     * <br/>
     * Description:
     * Registers a new team by prompting the user for team details such as name,
     * country, and coach name.
     * <br/>
     * Pre: None
     * <br/>
     * Post: A new team has been registered.
     */
    public void registerTeam() {
        System.out.println("-----------------------");
        System.out.println("REGISTRAR EQUIPO");
        System.out.println("Por favor, introduzca el nombre del equipo:");
        String name = sc.nextLine();
        System.out.println("Introduzca el país del equipo:");
        String country = sc.nextLine();
        System.out.println("Por favor, introduzca el nombre del entrenador:");
        String coachName = sc.nextLine();
        System.out.println(controller.registerTeam(name, country, coachName));
    }

    /**
     * Name: registerPlayer
     * <br/>
     * Description:
     * Registers a new player by prompting the user for player details such as ID,
     * name, country, dorsal number, and position.
     * <br/>
     * Pre: A team must be registered before a player can be registered
     * <br/>
     * Post: A new player has been registered.
     */
    public void registerPlayer() {
        System.out.println("-----------------------");
        System.out.println("REGISTRAR JUGADOR");
        System.out.println("Por favor, introduzca el id del jugador:");
        String id = sc.nextLine();
        System.out.println("Por favor, introduzca el nombre del jugador:");
        String name = sc.nextLine();
        System.out.println("Introduzca el país del jugador:");
        String country = sc.nextLine();
        System.out.println("Por favor, introduzca el dorsal del jugador:");
        int dorsal = sc.nextInt();
        sc.nextLine();
        int position = 0;
        do {
            System.out.println("1. Delantero");
            System.out.println("2. Medio");
            System.out.println("3. Defensa");
            System.out.println("4. Portero");
            System.out.println("Por favor, introduzca la posición del jugador:");
            position = sc.nextInt();
            sc.nextLine();
            if (position < 1 || position > 4) {
                System.out.println("Opción incorrecta");
            }
        } while (position < 1 || position > 4);
        System.out.println("Por favor, introduzca el nombre del equipo al que pertenece el jugador:");
        String teamName = sc.nextLine();
        System.out.println(controller.addPerson(id, name, country, dorsal, position, teamName));
    }

    /**
     * Name: registerReferee
     * <br/>
     * Description:
     * Registers a new referee by prompting the user for referee details such as ID,
     * name, country, and type.
     * <br/>
     * Pre: None
     * <br/>
     * Post: A new referee has been registered.
     */
    public void registerReferee() {
        System.out.println("-----------------------");
        System.out.println("REGISTRAR ARBITRO");
        System.out.println("Por favor, introduzca el id del arbitro:");
        String id = sc.nextLine();
        System.out.println("Por favor, introduzca el nombre del arbitro:");
        String name = sc.nextLine();
        System.out.println("Introduzca el país del arbitro:");
        String country = sc.nextLine();
        int refereeType = 0;
        do {
            System.out.println("1. Central");
            System.out.println("2. Asistente");
            System.out.println("Por favor, introduzca el tipo de arbitro:");
            refereeType = sc.nextInt();
            sc.nextLine();
            if (refereeType < 1 || refereeType > 2) {
                System.out.println("Opción incorrecta");
            }
        } while (refereeType < 1 || refereeType > 2);
        System.out.println(controller.addPerson(id, name, country, refereeType));
    }

    /**
     * Name: initializeTournament
     * <br/>
     * Description:
     * Initializes the tournament by pre-loading data such as teams, players, and
     * referees.
     * <br/>
     * Pre: At least one team, player, and referee must be registered.
     * <br/>
     * Post: The tournament has been initialized.
     */
    public void initializeTournament() {
        System.out.println("-----------------------");
        System.out.println("Precargando información del torneo");
        System.out.println(controller.initializeTournament());
    }

    /**
     * Name: generateGroupStageFixture
     * <br/>
     * Description:
     * Generates the group stage fixture by creating matches between teams.
     * <br/>
     * Pre: The tournament must be initialized.
     * <br/>
     * Post: The group stage fixture has been generated.
     */
    public void generateGroupStageFixture() {
        System.out.println("-----------------------");
        System.out.println("GENERAR FIXTURE DE GRUPO");
        String[] group1Dates = new String[5];
        String[] group1Hours = new String[5];
        String[] group2Dates = new String[5];
        String[] group2Hours = new String[5];
        for (int i = 0; i < 5; i++) {
            System.out.println("Por favor, introduzca la fecha del partido " + (i + 1) + " del grupo 1:");
            group1Dates[i] = sc.nextLine();
            System.out.println("Introduzca la hora del partido " + (i + 1) + " del grupo 1:");
            group1Hours[i] = sc.nextLine();
            System.out.println("Introduzca la fecha del partido " + (i + 1) + " del grupo 2:");
            group2Dates[i] = sc.nextLine();
            System.out.println("Introduzca la hora del partido " + (i + 1) + " del grupo 2:");
            group2Hours[i] = sc.nextLine();
        }
        System.out.println("Introduzca la última fecha de fase de grupos:");
        String lastDate = sc.nextLine();
        System.out.println("Introduzca la hora de la ultima fecha de fase de grupos:");
        String lastHour = sc.nextLine();
        System.out.println(controller.generateGroupStageFixture(group1Dates, group1Hours, group2Dates, group2Hours,
                lastDate, lastHour));
    }

    /**
     * Name: assignReferee
     * <br/>
     * Description:
     * Assigns a referee to a match by prompting the user for match details such as
     * local and visitor teams, referee ID, and local or visitor goals.
     */
    public void assignRefereesToMatch() {
        System.out.println("-----------------------");
        System.out.println("ASIGNAR ARBITRO A PARTIDO");
        System.out.println("Por favor, introduzca el nombre del equipo (local):");
        String localName = sc.nextLine();
        System.out.println("Por favor, introduzca el nombre del equipo (visitante):");
        String visitorName = sc.nextLine();
        String[] refereeIds = new String[3];
        for (int i = 0; i < refereeIds.length; i++) {
            System.out.println("Por favor, introduzca el id del arbitro " + (i + 1) + ":");
            refereeIds[i] = sc.nextLine();
        }
        System.out.println(controller.assignRefereesToMatch(refereeIds, localName, visitorName));
    }

    /**
     * Name: registerMatchResult
     * <br/>
     * Description:
     * Registers the result of a match by prompting the user for match details such
     * as local and visitor teams, local and visitor goals.
     */
    public void registerMatchResult() {
        System.out.println("-----------------------");
        System.out.println("REGISTRAR RESULTADO DE PARTIDO");
        System.out.println("Por favor, introduzca el nombre del equipo (local):");
        String localName = sc.nextLine();
        System.out.println("Por favor, introduzca el nombre del equipo (visitante):");
        String visitorName = sc.nextLine();
        System.out.println("Ingresa la cantidad de goles del partido");
        int goals = sc.nextInt();
        sc.nextLine();
        String[] scorersIds = new String[goals];
        int[] scorersMinutes = new int[goals];
        String[] assistantIds = new String[goals];
        boolean[] ownGoals = new boolean[goals];
        for (int i = 0; i < goals; i++) {
            System.out.println("Ingrese el id del goleador " + (i + 1) + ":");
            scorersIds[i] = sc.nextLine();
            System.out.println("Ingrese el id del asistente " + (i + 1) + ":");
            assistantIds[i] = sc.nextLine();
            System.out.println("Ingrese el minuto del gol " + (i + 1) + ":");
            scorersMinutes[i] = sc.nextInt();
            sc.nextLine();
            String ownGoal = "";
            boolean isValid = false;
            do {
                System.out.println("Gol en contra (S/N): ");
                ownGoal = sc.nextLine();
                if (ownGoal.equalsIgnoreCase("S") || ownGoal.equals("N")) {
                    isValid = true;
                } else {
                    System.out.println("¡Opción no válida!");
                }
            } while (!isValid);
            ownGoals[i] = ownGoal.equalsIgnoreCase("S");
        }
        int[] yellowCards = new int[2];
        System.out.println("Ingrese la cantidad de tarjetas amarillas al equipo 1:");
        yellowCards[0] = sc.nextInt();
        sc.nextLine();
        System.out.println("Ingrese la cantidad de tarjetas amarillas al equipo 2:");
        yellowCards[1] = sc.nextInt();
        sc.nextLine();
        int[] redCards = new int[2];
        System.out.println("Ingrese la cantidad de tarjetas rojas al equipo 1:");
        redCards[0] = sc.nextInt();
        sc.nextLine();
        System.out.println("Ingrese la cantidad de tarjetas rojas al equipo 2:");
        redCards[1] = sc.nextInt();
        sc.nextLine();
        System.out.println(controller.registerResult(localName, visitorName, scorersIds, scorersMinutes, assistantIds,
                ownGoals, yellowCards, redCards));
    }

}