import org.junit.Test;

import static org.junit.Assert.*;

@SuppressWarnings("ALL")
public class SquadTest {

    @Test
    private Hero setupNewHero() {
        return new Hero("Superman", 35, "Superstrength", "Kryptonite");
    }

    @Test
    private Squad setupNewSquad(Hero hero) {
        return new Squad("Justice League", "Save Humanity", hero);
    }

    @Test
    public void squadInstanciatedCorrectly() {
        Squad squad = setupNewSquad(setupNewHero());
        assertTrue(squad instanceof Squad);
    }

    @Test
    public void getSquadName() {
        Squad squad = setupNewSquad(setupNewHero());
        assertTrue(squad.getName() instanceof String);
    }

    @Test
    public void getSquadCause() {
        Squad squad = setupNewSquad(setupNewHero());
        assertTrue(squad.getCause() instanceof String);
    }
}