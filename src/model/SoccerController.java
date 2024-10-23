package model;

public class SoccerController {
    // Attributes
    private String name;
    // Associations
    private Team[] teams;
    private Person[] people;
    private Match[] group1Matches;
    private Match[] group2Matches;
    // Constants
    public static final int MAX_TEAMS = 8;
    public static final int MAX_PEOPLE = 10;

    public SoccerController(String name) {
        this.name = name;
        teams = new Team[MAX_TEAMS];
        people = new Person[10];
        group1Matches = new Match[6];
        group2Matches = new Match[6];
    }

    public String addTeam(String name, String country, String coachName) {
        Team existsTeam = searchTeam(name);
        String message = "";
        if (existsTeam == null) {
            Team team = new Team(name, country, coachName);
            boolean added = false;
            for (int i = 0; i < teams.length && !added; i++) {
                if (teams[i] == null) {
                    teams[i] = team;
                    added = true;
                }
            }
            if (added) {
                message = "Equipo agregado";
            } else {
                message = "No se pueden agregar más equipos";
            }
        } else {
            message = "El equipo ya existe";
        }
        return message;
    }

    public String addPlayer(String id, String name, String teamName, String country, int dorsal, int positionOption) {
        String message = "";
        Team team = searchTeam(teamName);
        if (team != null) {
            Player player = new Player(id, name, country, dorsal, positionOption);
            message = team.addPlayer(player);
        } else {
            message = "El equipo no existe";
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
        Person person = null;
        boolean found = false;
        for (int i = 0; i < people.length && !found; i++) {
            if (people[i].getId().equals(id)) {
                person = people[i];
                found = true;
            }
        }
        return person;
    }

    public Team searchTeam(String name) {
        Team team = null;
        boolean found = false;
        for (int i = 0; i < teams.length && !found; i++) {
            if (teams[i].getName().equals(name)) {
                team = teams[i];
                found = true;
            }
        }
        return team;
    }

    public String draw(String[] group1Dates, String[] group1Hours, String[] group2Dates, String[] group2Hours,
            String lastDate, String lastHour) {
        String message = "";

        int countTeams = 0;
        for (int i = 0; i < teams.length; i++) {
            if (teams[i] != null) {
                countTeams++;
            }
        }
        if (countTeams != MAX_TEAMS) {
            message = "Aún no se han registrado los " + MAX_TEAMS + " equipos necesarios para realizar el sorteo";
        } else {
            int[] randomIndexes = new int[MAX_TEAMS];
            for (int i = 0; i < MAX_TEAMS; i++) {
                randomIndexes[i] = (int) (Math.random() * MAX_TEAMS);
                for (int j = 0; j < i; j++) {
                    if (randomIndexes[i] == randomIndexes[j]) {
                        i--;
                    }
                }
            }
            for (int i = 0; i < MAX_TEAMS; i++) {
                if (i < MAX_TEAMS / 2) {
                    group1Matches[i] = new Match(teams[randomIndexes[i]], teams[randomIndexes[MAX_TEAMS - i - 1]],
                            group1Dates[i], group1Hours[i]);
                } else {
                    group2Matches[i - MAX_TEAMS / 2] = new Match(teams[randomIndexes[i]],
                            teams[randomIndexes[MAX_TEAMS - i - 1]], group2Dates[i - MAX_TEAMS / 2],
                            group2Hours[i - MAX_TEAMS / 2]);
                }
            }
        }

        return message;
    }
}
