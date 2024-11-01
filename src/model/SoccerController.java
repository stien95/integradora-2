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

    /**
     * Constructor of the controller
     * 
     * @param name - name of the tournament
     */
    public SoccerController(String name) {
        this.name = name;
        teams = new Team[MAX_TEAMS];
        people = new Person[MAX_PEOPLE];
        matches = new Match[12];
    }

    /**
     * Registers a new team by prompting the user for team details such as name,
     * country, and coach name.
     * <br/>
     * Pre: None
     * <br/>
     * Post: A new team has been registered.
     * 
     * @param name      the name of the team
     * @param country   the country of the team
     * @param coachName the coach name of the team
     * @return a message indicating whether the team was added or not
     */
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

    /**
     * Registers a new player by specifying its ID, name, country, dorsal number,
     * position, and team name.
     * <br/>
     * Pre: The team must exist.
     * <br/>
     * Post: The player has been registered and added to the team.
     * 
     * @param id       The ID of the player.
     * @param name     The name of the player.
     * @param country  The country of the player.
     * @param dorsal   The dorsal number of the player.
     * @param position The position of the player.
     * @param teamName The name of the team that the player belongs to.
     * @return A message indicating whether the player was registered successfully.
     */
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

    /**
     * Busca un equipo por su nombre.
     * <br/>
     * Pre: El torneo debe estar inicializado.
     * <br/>
     * Post: El equipo ha sido encontrado.
     * 
     * @param teamName El nombre del equipo a buscar.
     * @return El equipo si existe, null en caso contrario.
     */
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

    /**
     * Initializes the tournament by pre-loading data for teams, players, and
     * referees.
     * <br/>
     * Pre: The arrays for teams must be initialized. Players should be pre-defined
     * in each team. The maximum number of referees is 12.
     * <br/>
     * Post: Each team is initialized with a coach and players, and referees are
     * assigned.
     * Random names and countries are generated for coaches, players, and referees.
     * <br/>
     * 
     * @return A message indicating that the tournament has been initialized.
     */
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

    /**
     * Generates the group stage fixture by creating matches between teams.
     * <br/>
     * Pre: The tournament must be initialized.
     * <br/>
     * Post: The group stage fixture has been generated.
     * 
     * @param group1Dates The dates of the first group.
     * @param group1Hours The hours of the first group.
     * @param group2Dates The dates of the second group.
     * @param group2Hours The hours of the second group.
     * @param lastDate    The date of the last match.
     * @param lastHour    The hour of the last match.
     * @return A message indicating whether the group stage fixture was generated
     *         successfully.
     */
    public String generateGroupStageFixture(String[] group1Dates, String[] group1Hours, String[] group2Dates,
            String[] group2Hours, String lastDate, String lastHour) {
        String message = "";
        for (int i = 0; i < teams.length; i++) {
            teams[i] = searchTeam("Team" + (i + 1));
        }

        int[] indexes = { 0, 1, 2, 3, 4, 5, 6, 7 };
        for (int i = 0; i < indexes.length; i++) {
            int randomIndex = (int) (Math.random() * indexes.length);
            int temp = indexes[i];
            indexes[i] = indexes[randomIndex];
            indexes[randomIndex] = temp;
        }

        int[] group1Indexes = new int[4];
        int[] group2Indexes = new int[4];

        for (int i = 0; i < 4; i++) {
            group1Indexes[i] = indexes[i];
            group2Indexes[i] = indexes[i + 4];
        }

        int matchIndex = 0;
        for (int i = 0; i < group1Indexes.length; i++) {
            for (int j = i + 1; j < group1Indexes.length; j++) {
                String date = (matchIndex == 5) ? lastDate : group1Dates[matchIndex];
                String time = (matchIndex == 5) ? lastHour : group1Hours[matchIndex];
                matches[matchIndex] = new Match(teams[group1Indexes[i]], teams[group1Indexes[j]], date, time);
                matchIndex++;
            }
        }
        for (int i = 0; i < group2Indexes.length; i++) {
            for (int j = i + 1; j < group2Indexes.length; j++) {
                String date = (matchIndex == 11) ? lastDate : group2Dates[matchIndex - 6];
                String time = (matchIndex == 11) ? lastHour : group2Hours[matchIndex - 6];
                matches[matchIndex] = new Match(teams[group2Indexes[i]], teams[group2Indexes[j]], date, time);
                matchIndex++;
            }
        }

        message = "Fase de grupos generada exitosamente.";
        return message;
    }

    /**
     * Assigns referees to a match.
     * <br/>
     * Pre: The tournament must be initialized.
     * <br/>
     * Post: The referees have been assigned to the match.
     * 
     * @param refereeIds  The ids of the referees.
     * @param localName   The name of the local team.
     * @param visitorName The name of the visitor team.
     * @return A message indicating whether the referees were assigned successfully.
     */
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

    /**
     * Registers the result of a match by specifying the local and visitor teams,
     * goals, own goals, yellow cards, and red cards.
     * <br/>
     * Pre: The match must exist.
     * <br/>
     * Post: The result of the match has been registered.
     * 
     * @param localName      The name of the local team.
     * @param visitorName    The name of the visitor team.
     * @param scorersIds     The IDs of the goal scorers.
     * @param scorersMinutes The minutes of the goals.
     * @param assistantIds   The IDs of the goal assistants.
     * @param ownGoals       Whether the goals were own goals or not.
     * @param yellowCards    The number of yellow cards of each team.
     * @param redCards       The number of red cards of each team.
     * @return A message indicating whether the result was registered successfully.
     */
    public String registerResult(String localName, String visitorName, String[] scorersIds, String[] scorersMinutes,
            String[] assistantIds, boolean[] ownGoals, String[] playersIds, int[] cardTypes, String[] cardTimes,
            String[] refereesIds) {
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

                Player[] receivedCardPlayers = new Player[playersIds.length];
                Referee[] cardReferees = new Referee[refereesIds.length];

                for (int i = 0; i < playersIds.length; i++) {
                    Player player = (Player) searchPerson(playersIds[i]);
                    Referee referee = (Referee) searchPerson(refereesIds[i]);
                    if (player == null || referee == null) {
                        message = "Error: Uno de los jugadores o árbitros no existe";
                    } else {
                        receivedCardPlayers[i] = player;
                        cardReferees[i] = referee;
                    }
                }
                match.registerResult(scorers, assistants, scorersMinutes, ownGoals, receivedCardPlayers, cardTypes,
                        cardTimes, cardReferees);
            }
        }
        return message;
    }

    /**
     * Searches for a match in the tournament given the local and visitor teams.
     * <br/>
     * Pre: The tournament must be initialized.
     * <br/>
     * Post: The match has been found.
     * 
     * @param local   The local team of the match to search.
     * @param visitor The visitor team of the match to search.
     * @return The match if found, null otherwise.
     */
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
