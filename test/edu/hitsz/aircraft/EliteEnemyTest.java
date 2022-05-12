package edu.hitsz.aircraft;

import edu.hitsz.application.Main;
import edu.hitsz.bullet.BaseBullet;
import edu.hitsz.bullet.EnemyBullet;
import edu.hitsz.factory.EliteFactory;
import edu.hitsz.factory.EnemyFactory;
import org.junit.jupiter.api.*;

import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumeTrue;
import static org.junit.jupiter.api.Assumptions.assumingThat;

class EliteEnemyTest {
    private EnemyFactory eliteFactory;
    private EliteEnemy eliteEnemy;
    private LinkedList<BaseBullet> bullets;
    private BaseBullet bullet;

    @BeforeEach
    void setUp() {
        eliteFactory = new EliteFactory();
        eliteEnemy = (EliteEnemy) eliteFactory.enemyCreator();
        System.out.println( "---------------------------------------------");
    }

    @AfterEach
    void tearDown() {
        eliteFactory = null;
        eliteFactory = null;
        bullet = null;
        bullets = null;
    }

    @AfterAll
    static void down() {
        System.out.println( "---------------------------------------------");
    }

    @DisplayName("forward()")
    @Test
    void forward() {
        System.out.println( "TEST2001_EE_Forward");
        System.out.println("TEST2001_1: change location within one time-cycle");
        int cur_locationY = eliteEnemy.getLocationY() + eliteEnemy.getSpeedY();
        int cur_locationX = eliteEnemy.getLocationX() + eliteEnemy.getSpeedX();
        eliteEnemy.forward();
        assumeTrue(cur_locationY == eliteEnemy.getLocationY(),
                ()->"Aborting Test:locationY fail!");
        assumeTrue(cur_locationX == eliteEnemy.getLocationX(),
                ()->"Aborting Test:locationX fail!");
        System.out.println("pass!");
        System.out.println("TEST2001_2: boundary check within one time-cycle");
        eliteEnemy.setLocation(0, Main.WINDOW_HEIGHT);
        eliteEnemy.forward();
        assumeTrue(eliteEnemy.notValid(),()->"Aborting Test:fail!");
        System.out.println("pass!");
    }

    /**
     * 精英机每调用一次shoot，返回的子弹数只有1个
     *
     */
    @DisplayName("shoot()")
    @Test
    void shoot() {
        System.out.println( "TEST2002_EE_Shoot");
        bullet = new EnemyBullet(eliteEnemy.getLocationX(),eliteEnemy.getLocationY()+2,0,eliteEnemy.getSpeedY()+5,15);
        bullets = (LinkedList<BaseBullet>) eliteEnemy.shoot();
        assumeTrue(bullets.size() != 0, ()->"Aborting Test: can not generate bullets!");
        assumeTrue(bullet.getLocationX() == bullets.getFirst().getLocationX(), ()->"Aborting Test: bullet locationX error!");
        assumeTrue(bullet.getLocationY() == bullets.getFirst().getLocationY(), ()->"Aborting Test: bullet locationY error!");
        assumeTrue(bullets.getFirst().getSpeedY() == bullet.getSpeedY(), ()->"Aborting Test: bullet speedY error!");
        assumeTrue(bullets.getFirst().getSpeedX() == bullet.getSpeedX(), ()->"Aborting Test: bullet speedX error!");
        assumeTrue(bullets.getFirst().getPower() == bullet.getPower(), ()->"Aborting Test: bullet power error!");
        System.out.println("pass!");
    }
}