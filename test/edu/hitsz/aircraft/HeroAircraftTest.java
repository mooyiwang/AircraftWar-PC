package edu.hitsz.aircraft;

import edu.hitsz.bullet.BaseBullet;
import edu.hitsz.bullet.EnemyBullet;
import edu.hitsz.strategy.StraightShootDown;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assumptions.assumeFalse;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

class HeroAircraftTest {

    private HeroAircraft heroAircraft_1;
    private HeroAircraft heroAircraft_2;
    private BaseBullet enemyBullet;
    private AbstractAircraft enemyAircraft;

    @BeforeEach
    void setUp() {
        heroAircraft_1 = HeroAircraft.getHeroAircraft();
        System.out.println( "---------------------------------------------");
    }

    @AfterAll
    static void tearDown() {
        System.out.println( "---------------------------------------------");
    }


    @DisplayName("crash()")
    @Test
    void crash() {

        System.out.println( "TEST1001_HA_Crash");
        System.out.println("TEST1001_1: heroAircraft crash enemyBullet");
        enemyBullet = new EnemyBullet(heroAircraft_1.getLocationX()-1,heroAircraft_1.getLocationY()+2,0,heroAircraft_1.getSpeedY(),10);
        assumeTrue(heroAircraft_1.crash(enemyBullet),()->"fail!");
        System.out.println("pass!");
        System.out.println("TEST1001_2: heroAircraft do not crash enemyAircraft");
        enemyAircraft = new EliteEnemy(0,0,0,heroAircraft_1.getSpeedY(),10, new StraightShootDown());
        assumeFalse(heroAircraft_1.crash(enemyAircraft),()->"fail!");
        System.out.println("pass!");
    }

    @DisplayName("getHeroAircraft()")
    @Test
    void getHeroAircraft() {

        System.out.println( "TEST1002_HA_GHA");
        heroAircraft_2 = HeroAircraft.getHeroAircraft();
        assumeTrue(heroAircraft_1.equals(heroAircraft_2),()->"Aborting Test:fail!");
        System.out.println("pass!");
    }

}