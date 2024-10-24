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
    public static final int MAX_PEOPLE = 12;

    public SoccerController(String name) {
        this.name = name;
        teams = new Team[MAX_TEAMS];
        people = new Person[MAX_PEOPLE];
        matches = new Match[12];
    }

    public String addTeam(String name, String country, String coachId, String coachName, String coachCountry) {
        Team existsTeam = searchTeam(name);
        String message = "";
        if (existsTeam == null) {
            Team team = new Team(name, country, new Person(coachId, coachName, coachCountry));
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

    public String preload() {
        String message = "";
        // Generar los equipos
        for (int i = 0; i < 12; i++) {
            this.teams[i] = new Team("Equipo " + i, "País " + i, new Person("E"+1, "Entrenador "+i, "País "+ i));
        }
        message = "Equipos pre-cargados";
        // generar los partidos
        String[] group1Dates = new String[5];
        String[] group1Hours = new String[5];
        String[] group2Dates = new String[5];
        String[] group2Hours = new String[5];
        for (int i = 0; i < 5; i++) {
            group1Dates[i] = "2024-11-0" + (i + 1);
            group1Hours[i] = "15:00";
            group2Dates[i] = "2024-11-0" + (i + 2);
            group2Hours[i] = "18:00";
        }
        draw(group1Dates, group1Hours, group2Dates, group2Hours, "2024-11-10", "15:00");
        message += "\nPartidos generados";
        // generar los árbitros 8 centrales y 4 asistentes
        for (int i = 0; i < 12; i++) {
            this.people[i] = new Referee("R"+i, "Árbitro "+i, "País "+ i, i % 3 == 0 ? 2 : 1);
        }
        message += "\nÁrbitros pre-cargados";
        return message;
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
            int indexMatches = 0;
            for (int i = 0; i < MAX_TEAMS; i++) {
                for (int j = i+1; j < MAX_TEAMS; j++) {
                    matches[indexMatches++] = new Match(teams[i],teams[j], lastDate, message);
                }
            }
            message = "Partidos generados";
        }
        return message;
    }

    public String assignReferee(String refereeId, String localName, String visitorName) {
        String message = "";
        Referee referee = (Referee) searchPerson(refereeId);
        Team localTeam = searchTeam(localName);
        Team visitorTeam = searchTeam(visitorName);
        if (referee != null) {
            if (localTeam != null) {
                if (visitorTeam != null) {
                    Match match = searchMatch(localTeam, visitorTeam);
                    if (match != null) {
                        message = match.asignReferee(referee);
                    } else {
                        message = "El partido no existe";
                    }
                } else {
                    message = "El equipo visitante no existe";
                }
            } else {
                message = "El equipo local no existe";
            }
        } else {
            message = "El árbitro no existe";
        }
        return message;
    }
    public String registerResult(String localName, String visitorName, int localGoals , int visitorGoals) {
        String message = "";
        Team localTeam = searchTeam(localName);
        Team visitorTeam = searchTeam(visitorName);
        if (localTeam != null && visitorTeam != null) {
            Match match = searchMatch(localTeam, visitorTeam);
            if (match != null) {
                match.setLocalGoals(localGoals);
                match.setVisitorGoals(visitorGoals);
                message = "Resultado registrado";
            } else {
                message = "El partido no existe";
            }
        } else {
            message = "Los equipos no existen";
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
