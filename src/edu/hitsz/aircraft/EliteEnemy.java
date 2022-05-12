package edu.hitsz.aircraft;

import edu.hitsz.application.AbstractGame;

import edu.hitsz.application.Main;
import edu.hitsz.strategy.ShootStrategy;

/**
 * 精英敌机
 * 向下发射敌机子弹EnemyBullet
 *
 * @author hitsz.WangMuyi
 */
public class EliteEnemy extends AbstractAircraft implements BombpropActivateUpdate {


    public EliteEnemy(int locationX, int locationY, int speedX, int speedY, int hp, ShootStrategy strategy) {
        super(locationX, locationY, speedX, speedY, hp, strategy);
    }

    @Override
    public void forward(){
        super.forward();
        // 判定 y 轴向下飞行出界
        if (locationY >= Main.WINDOW_HEIGHT ) {
            vanish();
        }
    }

    @Override
    public void update() {
        this.vanish();
        AbstractGame.score += 30;
    }

//    @Override
//    /**
//     * 通过射击产生子弹
//     * @return 射击出的子弹List
//     */
//    public List<BaseBullet> shoot() {
//        return shootStrategy.shoot(this);
//    }








}
