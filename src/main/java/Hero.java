import java.util.ArrayList;
import java.util.List;

public class Hero {
    public String heroName;
    public int heroAge;
    public String heroSpecialPower;
    public String heroWeakness;
    public int heroID;
    public String squadGroup;
    public static List<Hero> heroList = new ArrayList<>();

    public static boolean duplicate = false;

    public Hero(String name, int age, String specialpower, String weakness) {
        heroName = name;
        heroAge = age;
        heroSpecialPower = specialpower;
        heroWeakness = weakness;

        heroList.add(this);
        heroID = heroList.size();
        squadGroup = "";

        findDuplicateHero(this);

        if (duplicate) {
            System.out.println("models.Hero already exists");
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

    private static void findDuplicateHero(Hero newInsertion) {
        for (Hero hero : heroList) {
            if (newInsertion.heroName.equalsIgnoreCase(hero.heroName) &&
                    newInsertion.heroSpecialPower.equalsIgnoreCase(hero.heroSpecialPower) &&
                    newInsertion.heroWeakness.equalsIgnoreCase(hero.heroWeakness)) {
                duplicate = true;
                break;
            } else if (newInsertion.heroName.equalsIgnoreCase(hero.heroName)) {
                duplicate = true;
                break;
            }
        }
    }

}
