import java.util.ArrayList;
import java.util.List;

public class Hero {
    private String heroName;
    private int heroAge;
    private String heroSpecialPower;
    private String heroWeakness;
    private int heroID;
    public String squadGroup;
    private static List<Hero> heroList = new ArrayList<>();

    private boolean duplicate = false;

    public Hero(String name, int age, String specialpower, String weakness) {
        heroName = name;
        heroAge = age;
        heroSpecialPower = specialpower;
        heroWeakness = weakness;

        findDuplicateHero(this);

        if (duplicate) {
            System.out.println("Hero already exists");
        } else {
            heroList.add(this);
            this.heroID = heroList.size();
            this.squadGroup = "";
        }
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

    public void findDuplicateHero(Hero newHeroRecord) {
        for (Hero hero : heroList) {
            if (newHeroRecord.heroName.equalsIgnoreCase(hero.heroName) &&
                    newHeroRecord.heroSpecialPower.equalsIgnoreCase(hero.heroSpecialPower) &&
                    newHeroRecord.heroWeakness.equalsIgnoreCase(hero.heroWeakness)) {
                duplicate = true;
                break;
            } else if (newHeroRecord.heroName.equalsIgnoreCase(hero.heroName)) {
                duplicate = true;
                break;
            }
        }
    }

}
