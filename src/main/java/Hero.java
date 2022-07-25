import java.util.ArrayList;
import java.util.List;

public class Hero {
    private String heroName;
    private int heroAge;
    private String heroSpecialPower;
    private String heroWeakness;
    private static List<Hero> heroEntry = new ArrayList<>();
    private int heroID;
    private String squadAlliance;

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
}
