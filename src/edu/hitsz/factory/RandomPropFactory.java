package edu.hitsz.factory;

import edu.hitsz.aircraft.AbstractAircraft;
import edu.hitsz.prop.AbstractProp;
import edu.hitsz.prop.BloodProp;
import edu.hitsz.prop.BombProp;
import edu.hitsz.prop.FireProp;

public class RandomPropFactory implements PropFactory {
    @Override
    public AbstractProp randomPropCreator(AbstractAircraft enemyAircraft) {
        switch ((int) (Math.random() * 4 + 1) * 1) {
            case 1:
                return new BloodProp(
                        enemyAircraft.getLocationX(),
                        enemyAircraft.getLocationY(),
                        0,
                        2);
            case 2:
                return new BombProp(
                        enemyAircraft.getLocationX(),
                        enemyAircraft.getLocationY(),
                        0,
                        2);
            case 3:
                return new FireProp(
                        enemyAircraft.getLocationX(),
                        enemyAircraft.getLocationY(),
                        0,
                        2);
            default:
                return null;
        }
    }
}

