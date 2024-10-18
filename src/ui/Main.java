
import java.util.Scanner;
import model.SoccerController;

public class Main {
    Scanner sc;
    SoccerController controller;
    public Main() {
        sc = new Scanner(System.in);
    }
    public static void main(String[] args) {
        System.out.println("Bienvenido al sistema de gestión de torneos de futbol");
        Main main = new Main();
        System.out.println("Ingrese el nombre del torneo");
        String name = main.sc.nextLine();
        main.controller = new SoccerController(name);
    }
    public void showMenu() {
        System.out.println("1. Agregar equipo");
        System.out.println("2. Agregar árbitro");
        System.out.println("3. Realizar sorteo");
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
        controller.addTeam(name, country, coachName);
    }

}