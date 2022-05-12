package edu.hitsz.factory;

import edu.hitsz.aircraft.AbstractAircraft;
import edu.hitsz.aircraft.MobEnemy;
import edu.hitsz.application.ImageManager;
import edu.hitsz.application.Main;
import edu.hitsz.strategy.ShootNothing;

public class MobFactory implements EnemyFactory {

    @Override
    public AbstractAircraft enemyCreator(){
        return new MobEnemy((int) ( Math.random() * (Main.WINDOW_WIDTH - ImageManager.MOB_ENEMY_IMAGE.getWidth()))*1,
                            0,
                            0,
                            10,
                            30,
                            new ShootNothing());
    }
}

