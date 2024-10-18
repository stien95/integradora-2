package model;

public class SoccerController {
    // Attributes
    private String name;
    // Associations
    private Person[] people;
    public SoccerController(String name) {
        this.name = name;
    }

    public String addPlayer(String id, String name, String teamName, int dorsal, int positionOption) {
        Person existsPerson = searchPerson(id);
        String message = "";
        if (existsPerson == null) {
            Player player = new Player(name, teamName, id, dorsal, positionOption);
            boolean added = false;
            for (int i = 0; i < people.length && !added; i++) {
                if (people[i] == null) {
                    people[i] = player;
                    added = true;
                }
            }
            if (added) {
                message = "Jugador agregado";
            } else {
                message = "No se pueden agregar más jugadores";
            }
        } else {
            message = "El jugador ya existe";
        }
        return message;
    }
    public String addReferee(String id, String name, String country, int typeOption) {
        Person existsPerson = searchPerson(id);
        String message = "";
        if (existsPerson == null) {
            Referee referee = new Referee(id, name, country, typeOption);
            boolean added = false;
            for (int i = 0; i < people.length && !added; i++) {
                if (people[i] == null) {
                    people[i] = referee;
                    added = true;
                }
            }
            if (added) {
                message = "Árbitro agregado";
            } else {
                message = "No se pueden agregar más árbitros";
            }
        } else {
            message = "El árbitro ya existe";
        }
        return message;

    }
    public Person searchPerson(String id) {
        // Search the person by id
        Person person = null;
        for (int i = 0; i < people.length; i++) {
            if (people[i].getId().equals(id)) {
                person = people[i];
            }
        }
        return person;
    }

}
