package edu.hitsz.aircraft;

import edu.hitsz.application.Main;
import edu.hitsz.strategy.ShootStrategy;

public class BossEnemy extends AbstractAircraft {

    public BossEnemy(int locationX, int locationY, int speedX, int speedY, int hp, ShootStrategy strategy) {
        super(locationX, locationY, speedX, speedY, hp, strategy);
    }

    @Override
    public void forward() {
        super.forward();
        // 判定 y 轴向下飞行出界
        if (locationY >= Main.WINDOW_HEIGHT ) {
            vanish();
        }
    }

//    @Override
//    public List<BaseBullet> shoot() {
//        return shootStrategy.shoot(this);
//    }
}
