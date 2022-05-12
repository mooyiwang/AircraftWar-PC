package edu.hitsz.factory;

import edu.hitsz.aircraft.AbstractAircraft;
import edu.hitsz.prop.AbstractProp;

//public interface PropFactory {
//    AbstractProp propCreator(AbstractAircraft enemyAircraft);
//}

public interface PropFactory {
    /**
     *
     * @param enemyAircraft 失效敌机的参数用于创建道具
     * @return 创建的道具
     */
    AbstractProp randomPropCreator(AbstractAircraft enemyAircraft);
}

