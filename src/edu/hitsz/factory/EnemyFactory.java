package edu.hitsz.factory;

import edu.hitsz.aircraft.AbstractAircraft;

public interface EnemyFactory {
    /**
     *
     * @return 创建好的敌机
     */
    AbstractAircraft enemyCreator();
}
