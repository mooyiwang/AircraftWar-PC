package edu.hitsz.prop;

import edu.hitsz.aircraft.EliteEnemy;
import edu.hitsz.aircraft.HeroAircraft;
import edu.hitsz.aircraft.MobEnemy;
import edu.hitsz.basic.AbstractFlyingObject;
import edu.hitsz.bullet.EnemyBullet;

import java.util.ArrayList;
import java.util.List;

public class BombProp extends AbstractProp{

    private List<AbstractFlyingObject> enemyAndEnemyBullet = new ArrayList<>();

    public BombProp(int locationX, int locationY, int speedX, int speedY) {
        super(locationX, locationY, speedX, speedY);
    }

    public void addBombed(AbstractFlyingObject bombed){
        this.enemyAndEnemyBullet.add(bombed);
    }

    public void removeBombed(AbstractFlyingObject bombed){
        this.enemyAndEnemyBullet.remove(bombed);
    }

    public void notifyAllSub(){
        for(AbstractFlyingObject bombed : enemyAndEnemyBullet){
            if(bombed instanceof EnemyBullet){
                ((EnemyBullet) bombed).update();
            }
            else if(bombed instanceof EliteEnemy){
                ((EliteEnemy) bombed).update();
            }
            else{
                ((MobEnemy) bombed).update();
            }
        }
    }

    @Override
    public void function(HeroAircraft heroAircraft){
        notifyAllSub();
        System.out.println("BombSupply Active!");
    }
}
