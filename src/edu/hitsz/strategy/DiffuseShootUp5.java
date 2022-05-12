package edu.hitsz.strategy;

import edu.hitsz.aircraft.AbstractAircraft;
import edu.hitsz.bullet.BaseBullet;
import edu.hitsz.bullet.HeroBullet;

import java.util.LinkedList;
import java.util.List;

public class DiffuseShootUp5 extends ShootStrategy{
//    int shootNum = 3;     //子弹一次发射数量
//    int power = 30;       //子弹伤害
//    int direction = -1;  //子弹射击方向 (向上发射：1，向下发射：-1) //模板这里方向反了

    public DiffuseShootUp5() {
        shootNum = 5;
        power = 30;
        direction = -1;
    }

    @Override
    public List<BaseBullet> shoot(AbstractAircraft aircraft){
        List<BaseBullet> res = new LinkedList<>();
        int x = aircraft.getLocationX();
        int y = aircraft.getLocationY() + direction*2;
        int speedX = -2;
        int speedY = aircraft.getSpeedY() + direction*5;
        BaseBullet bullet;
        for(int i=0; i<shootNum; i++){
            // 子弹发射位置相对飞机位置向前偏移
            // 多个子弹横向分散
            bullet = new HeroBullet(x + (i*2 - shootNum + 1)*10, y, speedX, speedY, power);
            res.add(bullet);
            speedX += 1;
        }
        return res;
    }
}
