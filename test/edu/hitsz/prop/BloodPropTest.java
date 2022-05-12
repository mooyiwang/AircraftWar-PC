package edu.hitsz.prop;

import edu.hitsz.aircraft.AbstractAircraft;
import edu.hitsz.aircraft.HeroAircraft;
import org.junit.jupiter.api.*;

import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;

class BloodPropTest {
    private BloodProp bloodProp;
    private HeroAircraft heroAircraft;
    private LinkedList<AbstractAircraft> enemyAircrafts;

    @BeforeEach
    void setUp() {
        bloodProp = new BloodProp(10,10,0,0);
        heroAircraft = HeroAircraft.getHeroAircraft();
        enemyAircrafts = new LinkedList<>();
        System.out.println( "---------------------------------------------");
    }

    @AfterEach
    void tearDown() {
        bloodProp = null;
        heroAircraft = null;
        enemyAircrafts = null;
    }
    @AfterAll
    static void down() {
        System.out.println( "---------------------------------------------");
    }

    @DisplayName("Vanish()")
    @Test
    void vanish() {
        System.out.println( "TEST3001_BLP_Vanish");
        assertFalse(bloodProp.notValid(),()->"Aborting Test: bloodprop generating fail!");
        bloodProp.vanish();
        assertTrue(bloodProp.notValid(),()->"Aborting Test: Vanish fail");
        System.out.println("pass!");
    }

    @DisplayName("function()")
    @Test
    void function() {
        System.out.println( "TEST3002_BLP_Func");
        bloodProp.function(heroAircraft);
        assertEquals(150,heroAircraft.getHp(),()->"Aborting Test:fail");
        System.out.println("pass!");
    }
}