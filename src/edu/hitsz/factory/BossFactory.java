package edu.hitsz.factory;

import edu.hitsz.aircraft.AbstractAircraft;
import edu.hitsz.aircraft.BossEnemy;
import edu.hitsz.application.ImageManager;
import edu.hitsz.application.Main;
import edu.hitsz.strategy.DiffuseShootDown3;


public class BossFactory implements EnemyFactory {

    @Override
    public AbstractAircraft enemyCreator(){
        return new BossEnemy(
                (int)(Math.random() * (Main.WINDOW_WIDTH - ImageManager.BOSS_ENEMY_IMAGE.getWidth()))*1,
                5,
                2,
                0,
                300,
                new DiffuseShootDown3());
    }

}
