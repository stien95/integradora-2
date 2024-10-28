package model;

public class SoccerController {
    // Attributes
    private String name;
    // Associations
    private Team[] teams;
    private Person[] people;
    private Match[] matches;
    // Constants
    public static final int MAX_TEAMS = 8;
    public static final int MAX_PEOPLE = 200;

    public SoccerController(String name) {
        this.name = name;
        teams = new Team[MAX_TEAMS];
        people = new Person[MAX_PEOPLE];
        matches = new Match[12];
    }

    public String registerTeam(String name, String country, String coachName) {
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

    public String addPerson(String id, String name, String country, int dorsal, int position, String teamName) {
        Person existsPerson = searchPerson(id);

        String message = "";

        if (existsPerson == null) {
            Team team = searchTeam(teamName);
            if (team != null) {
                Player player = new Player(id, name, country, dorsal, position);
                boolean added = false;
                for (int i = 0; i < people.length && !added; i++) {
                    if (people[i] == null) {
                        people[i] = player;
                        added = true;
                    }
                }
                if (added) {
                    message = team.addPlayer(player);
                    ;
                } else {
                    message = "No se pueden agregar más jugadores";
                }
            } else {
                message = "El equipo no existe";
            }
        } else {
            message = "La persona ya existe";
        }

        return message;
    }

    public String addPerson(String id, String name, String country, int refereeType) {
        Person existsPerson = searchPerson(id);
        String message = "";
        if (existsPerson == null) {
            int assistantCount = 0;
            int centralCount = 0;

            for (int i = 0; i < people.length; i++) {
                if (people[i] instanceof Referee) {
                    Referee referee = (Referee) people[i];
                    if (referee.equalsType(2)) { // ASSISTANT
                        assistantCount++;
                    } else if (referee.equalsType(1)) { // CENTRAL
                        centralCount++;
                    }
                }
            }

            // Verificar que no se supere el límite
            if ((refereeType == 2 && assistantCount >= 8) || (refereeType == 1 && centralCount >= 4)) {
                return "No se pueden agregar más árbitros del tipo especificado";
            }

            // Agregar nuevo árbitro si los límites no han sido alcanzados
            Referee referee = new Referee(id, name, country, refereeType);
            boolean added = false;
            for (int i = 0; i < people.length && !added; i++) {
                if (people[i] == null) {
                    people[i] = referee;
                    added = true;
                }
            }

            message = added ? "Persona agregada" : "No se pueden agregar más personas";
        } else {
            message = "La persona ya existe";
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

    public Team searchTeam(String teamName) {
        Team team = null;
        boolean found = false;
        for (int i = 0; i < teams.length && !found; i++) {
            if (teams[i].getName().equals(teamName)) {
                team = teams[i];
                found = true;
            }
        }
        return team;
    }

    public String initializeTournament() {
        String[] names = { "Juan", "Pedro", "Carlos", "Andrés", "Luis", "Javier", "Miguel", "José", "Mario",
                "Jorge", "Fernando", "Ricardo", "Alberto", "Roberto", "Guillermo", "Gustavo", "Hugo", "Eduardo", "Raúl",
                "Sergio", "Alejandro" };
        String[] lastNames = { "Garcia", "Gonzalez", "Lopez", "Perez", "Martinez", "Sanchez", "Rodriguez", "Fernandez",
                "Gomez", "Vargas", "Jimenez", "Gutierrez", "Hernandez", "Ramirez", "Herrera", "Torres", "Castro" };
        for (int i = 0; i < teams.length; i++) {
            if (teams[i] == null) {
                String coachName = names[(int) Math.random() * names.length + 1] + " "
                        + lastNames[(int) Math.random() * lastNames.length + 1];
                teams[i] = new Team("Equipo " + (i + 1), "País " + (i + 1), coachName);
            }
            for (int j = 0; j < teams[i].getPlayers().length; j++) {
                if (teams[i].getPlayers()[j] == null) {
                    String name = names[(int) Math.random() * names.length] + " "
                            + lastNames[(int) Math.random() * lastNames.length + 1];
                    int position = (int) Math.floor(Math.random() * 4 + 1);
                    int dorsal = (int) Math.floor(Math.random() * 20 + 1);
                    Player player = new Player("J" + (j + 1), name, "País " + (i + 1), dorsal, position);
                    teams[i].addPlayer(player);
                    addPerson("J" + (j + 1), name, "País " + (i + 1), dorsal, position, teams[i].getName());
                }
            }
        }
        // 4 Centrales y 8 asistentes
        for (int i = 0; i < 12; i++) {
            String name = names[(int) Math.random() * names.length] + " "
                    + lastNames[(int) Math.random() * lastNames.length + 1];
            addPerson("A" + (i + 1), name, "País " + (i + 1), i % 3 == 0 ? 2 : 1);
        }

        return "Torneo inicializado";
    }

    public String generateGroupStageFixture(String[] group1Dates, String[] group1Hours, String[] group2Dates,
            String[] group2Hours,
            String lastDate, String lastHour) {

        return null;
    }

    public String assignRefereesToMatch(String[] refereeIds, String localName, String visitorName) {
        String message = "";
        if (refereeIds.length != 3) {
            message = "Error: Debe haber exactamente 3 árbitros";
        } else {
            Referee[] referees = new Referee[3];
            boolean stop = false;
            for (int i = 0; i < refereeIds.length && !stop; i++) {
                Referee referee = (Referee) searchPerson(refereeIds[i]);
                if (referee == null) {
                    stop = true;
                    message = "Error: El árbitro con id " + refereeIds[i] + " no existe";
                } else {
                    referees[i] = referee;
                }
            }

            if (!stop) {
                Team local = searchTeam(localName);
                Team visitor = searchTeam(visitorName);
                if (local == null) {
                    message = "Error: El equipo local no existe";
                } else if (visitor == null) {
                    message = "Error: El equipo visitante no existe";
                } else {
                    Match match = searchMatch(local, visitor);
                    if (match == null) {
                        message = "Error: El partido no existe";
                    } else {
                        match.assignReferee(referees);
                    }
                }
            }
        }

        return message;
    }

    public String registerResult(String localName, String visitorName, String[] scorersIds, int[] scorersMinutes,
            String[] assistantIds, boolean[] ownGoals, int[] yellowCards, int[] redCards) {
        Team local = searchTeam(localName);
        Team visitor = searchTeam(visitorName);
        String message = "";
        if (local == null) {
            message = "Error: El equipo local no existe";
        } else if (visitor == null) {
            message = "Error: El equipo visitante no existe";
        } else {
            Match match = searchMatch(local, visitor);
            if (match == null) {
                message = "Error: El partido no existe";
            } else {
                Player[] scorers = new Player[scorersIds.length];
                Player[] assistants = new Player[assistantIds.length];
                for (int i = 0; i < scorersIds.length; i++) {
                    Player scorer = (Player) searchPerson(scorersIds[i]);
                    Player assistant = (Player) searchPerson(assistantIds[i]);
                    if (scorer == null || assistant == null) {
                        message = "Error: Uno de los jugadores no existe";
                    } else {
                        scorers[i] = scorer;
                        assistants[i] = assistant;
                    }
                }
                match.registerResult(scorers, assistants, scorersMinutes, ownGoals, yellowCards, redCards);
            }
        }
        return message;
    }

    public Match searchMatch(Team local, Team visitor) {
        Match match = null;
        boolean found = false;
        for (int i = 0; i < matches.length && !found; i++) {
            if (matches[i].getLocal().equals(local) && matches[i].getVisitor().equals(visitor)) {
                match = matches[i];
                found = true;
            }
        }
        return match;
    }

}
