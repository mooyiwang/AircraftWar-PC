package edu.hitsz.application;

import edu.hitsz.aircraft.BossEnemy;
import edu.hitsz.aircraft.EliteEnemy;
import edu.hitsz.aircraft.HeroAircraft;
import edu.hitsz.aircraft.MobEnemy;
import edu.hitsz.bullet.EnemyBullet;
import edu.hitsz.bullet.HeroBullet;
import edu.hitsz.factory.EliteFactory;
import edu.hitsz.prop.BloodProp;
import edu.hitsz.prop.BombProp;
import edu.hitsz.prop.FireProp;
import edu.hitsz.strategy.ShootStrategy;

import javax.imageio.ImageIO;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class GameEasy extends AbstractGame{




    public GameEasy() throws FileNotFoundException, UnsupportedEncodingException {
        super();
        super.eliteNum = 1;
    }

    @Override
    public void setBackgroundImage() {
        try {
            ImageManager.BACKGROUND_IMAGE = ImageIO.read(new FileInputStream("src/images/bg2.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(-1);
        }
    }

    @Override
    public boolean hasBoss() {
        return false;
    }

    @Override
    public boolean isBossHpUp() {
        return false;
    }

    @Override
    public int setScoreThreshold() {
        return Integer.MAX_VALUE;
    }

    @Override
    public void setEliteNum(){
    }

    @Override
    public boolean isCreateElite(int eliteAppearCtrl) {
        if(eliteAppearCtrl % 10 == 0){
            return true;
        }
        else {
            return false;
        }
    }


    @Override
    public int setEnemyHp() {
        return 10;
    }

    @Override
    public void hardnessPrint(int eliteAppearCtrl) {
        if(eliteAppearCtrl %8 == 0){
            System.out.println("【难度提示】 EASY: 难度不随时间改变。");
        }
    }

    @Override
    public ShootStrategy setBossStrategy() {
        return null;
    }

    @Override
    public int setEnemyBulletPower() {
        return 10;
    }
}
