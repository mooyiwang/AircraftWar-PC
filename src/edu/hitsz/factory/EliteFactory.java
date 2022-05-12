package edu.hitsz.factory;

import edu.hitsz.aircraft.AbstractAircraft;
import edu.hitsz.aircraft.EliteEnemy;
import edu.hitsz.application.ImageManager;
import edu.hitsz.application.Main;
import edu.hitsz.strategy.StraightShootDown;

public class EliteFactory implements EnemyFactory {


    @Override
    public AbstractAircraft enemyCreator(){
        return new EliteEnemy(
                            (int)(Math.random() * (Main.WINDOW_WIDTH - ImageManager.ELITE_ENEMY_IMAGE.getWidth()))*1,
                            0,
                            (int)(Math.random()*( 5 - (-5) + 1) + (-5) )*1,
                            7,
                            30,
                            new StraightShootDown());
    }
}
