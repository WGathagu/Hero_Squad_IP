import java.util.ArrayList;
import java.util.List;

public class Squad {
    String squadName;
    String squadCause;
    int squadID;
    private static List<Squad> squadList = new ArrayList<>();

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

    public static List<Squad> getSquadsList() {
        return squadList;
    }

    public static Squad findSquad(int searchId) {
        return squadList.get(searchId);
        //return squadList.get(searchId - 1);
    }


}
