package edu.hitsz.prop;

import edu.hitsz.aircraft.HeroAircraft;

public class BloodProp extends AbstractProp{

    private static int increase = 50;

    public BloodProp(int locationX, int locationY, int speedX, int speedY) {
        super(locationX, locationY, speedX, speedY);
    }

    @Override
    public void function(HeroAircraft heroAircraft){
        heroAircraft.increaseHp(increase);
    };
}
