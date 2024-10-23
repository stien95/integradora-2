
import java.util.Scanner;
import model.SoccerController;

public class Main {
    Scanner sc;
    SoccerController controller;

    public Main() {
        sc = new Scanner(System.in);
    }

    public static void main(String[] args) {
        System.out.println("Bienvenido al sistema de gestión de torneos de futbol de la fase de grupos");
        Main main = new Main();
        System.out.println("Ingrese el nombre del torneo");
        String name = main.sc.nextLine();
        main.controller = new SoccerController(name);
    }

    public void showMenu() {
        System.out.println("1. Agregar equipo");
        System.out.println("2. Agregar árbitro");
        System.out.println("3. Agregar jugador");
        System.out.println("4. Realizar sorteo");
        System.out.println("Ingrese una opción");
        int option = sc.nextInt();
        switch (option) {
            case 1:
                addTeam();
                break;
            case 2:
                addReferee();
                break;
            case 3:
                addPlayer();
                break;
            case 4:
                draw();
                break;
            default:
                System.out.println("Opción inválida");
                break;
        }
    }

    public void addTeam() {
        System.out.println("Ingrese el nombre del equipo");
        String name = sc.nextLine();
        System.out.println("Ingrese el país del equipo");
        String country = sc.nextLine();
        System.out.println("Ingrese el nombre del técnico");
        String coachName = sc.nextLine();
        System.out.println(controller.addTeam(name, country, coachName));
    }

    public void addPlayer() {
        System.out.println("Ingrese el id del jugador");
        String id = sc.nextLine();
        System.out.println("Ingrese el nombre del jugador");
        String name = sc.nextLine();
        System.out.println("Ingrese el nombre del equipo");
        String teamName = sc.nextLine();
        System.out.println("Ingrese el país del jugador");
        String country = sc.nextLine();
        System.out.println("Ingrese el dorsal del jugador");
        int dorsal = sc.nextInt();
        sc.nextLine();
        System.out.println("Ingrese la posición del jugador");
        System.out.println("1. Portero");
        System.out.println("2. Defensa");
        System.out.println("3. Mediocampista");
        System.out.println("4. Delantero");
        int positionOption = sc.nextInt();
        sc.nextLine();
        System.out.println(controller.addPlayer(id, name, teamName, country, dorsal, positionOption));
    }

    public void addReferee() {
        System.out.println("Ingrese el id del árbitro");
        String id = sc.nextLine();
        System.out.println("Ingrese el nombre del árbitro");
        String name = sc.nextLine();
        System.out.println("Ingrese el país del árbitro");
        String country = sc.nextLine();
        System.out.println("Ingrese el tipo de árbitro");
        System.out.println("1. Central");
        System.out.println("2. Asistente");
        int typeOption = sc.nextInt();
        sc.nextLine();
        System.out.println(controller.addReferee(id, name, country, typeOption));
    }

    public void draw() {
        String[] group1Dates = new String[5];
        String[] group1Hours = new String[5];
        String[] group2Dates = new String[5];
        String[] group2Hours = new String[5];
        String lastDate = "";
        String lastHour = "";
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 5; j++) {
                System.out.println("Ingrese la fecha del partido " + (j + 1) + " del grupo " + (i + 1));
                String date = sc.nextLine();
                System.out.println("Ingrese la hora del partido " + (j + 1) + " del grupo " + (i + 1));
                String hour = sc.nextLine();
                if (i == 0) {
                    group1Dates[i] = date;
                    group1Hours[i] = hour;
                } else {
                    group2Hours[i] = hour;
                    group2Dates[i] = date;
                }
            }

        }
        System.out.println("Ingrese la fecha de los dos últimos partidos de la fase de grupos");
        lastDate = sc.nextLine();
        System.out.println("Ingrese la hora de los dos últimos partidos de la fase de grupos");
        lastHour = sc.nextLine();
        System.out.println(controller.draw());
    }
}