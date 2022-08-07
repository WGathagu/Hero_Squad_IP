import org.junit.Test;

import static org.junit.Assert.*;

@SuppressWarnings("ALL")
public class HeroTest {

    @Test
    private Hero setupNewHero(){
        return new Hero("Superman", 35, "Superstrength", "Kryptonite");
    }

    @Test
    public void heroInstantiatesCorrectly() {
        Hero newHero = setupNewHero();
        assertTrue(newHero instanceof Hero);
    }

    @Test
    public void getHeroName_true() {
        Hero newHero = setupNewHero();
        assertTrue(newHero.getName() instanceof String);
    }

    @Test
    public void getHeroAge_int() {
        Hero newHero = setupNewHero();
        assertEquals(35, newHero.getAge());
    }

    @Test
    public void getHeroSuperPower_true() {
        Hero newHero = setupNewHero();
        assertTrue(newHero.getPower() instanceof String);
    }

    @Test
    public void getHeroWeakness_true() {
        Hero newHero = setupNewHero();
        assertTrue(newHero.getWeakness() instanceof String);
    }

    @Test
    public void getHeroList_storeTwoHeroes_true() {
        Hero newHero = setupNewHero();
        assertTrue(Hero.getHeroList().contains(newHero));
    }

    @Test
    public void findHero_searchForHeroById_String() {
        Hero newHero = setupNewHero();
        assertEquals("Superman", Hero.findHero(newHero.getHeroID()).getName());
    }
}