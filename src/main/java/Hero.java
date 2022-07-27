import java.util.ArrayList;
import java.util.List;

public class Hero {
    private String heroName;
    private int heroAge;
    private String heroSpecialPower;
    private String heroWeakness;
    private int heroID;
    private String squadGroup;
    private static List<Hero> heroList = new ArrayList<>();

    public Hero(String name, int age, String specialpower, String weakness) {
        heroName = name;
        heroAge = age;
        heroSpecialPower = specialpower;
        heroWeakness = weakness;
    }
    public void setPower(String power) {
        heroSpecialPower = power;
    }

    public void setWeakness(String weakness) {
        heroWeakness = weakness;
    }

    public String getName() {
        return heroName;
    }

    public int getAge() {
        return heroAge;
    }

    public String getPower() {
        return heroSpecialPower;
    }

    public String getWeakness() {
        return heroWeakness;
    }

    public int getHeroID() {
        return heroID;
    }

    public static List<Hero> getHeroList() {
        return heroList;
    }

    public void setSquadGroup(String squadAlliance) {
        squadGroup = squadAlliance;
    }

    public String getSquadGroup() {
        return squadGroup;
    }

    public static Hero findHero(int searchID) {
        //return heroList.get(searchID - 1);
        return heroList.get(searchID);
    }
}
