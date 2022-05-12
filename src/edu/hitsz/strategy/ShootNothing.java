package edu.hitsz.strategy;

import edu.hitsz.aircraft.AbstractAircraft;
import edu.hitsz.bullet.BaseBullet;

import java.util.LinkedList;
import java.util.List;

public class ShootNothing extends ShootStrategy{


    public ShootNothing() {
        super();
    }

    @Override
    public List<BaseBullet> shoot(AbstractAircraft aircraft){
        return new LinkedList<>();
    }
}

