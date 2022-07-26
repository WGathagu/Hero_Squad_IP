public class Squad {
    String squadName;
    String squadCause;
    int squadID;

    public Squad(String name, String cause, Hero hero) {
        squadName = name;
        squadCause = cause;

    }

    public String getName() {
        return squadName;
    }

    public int getSquadId() {
        return squadID;
    }

    public String getCause() {
        return squadCause;
    }


}
