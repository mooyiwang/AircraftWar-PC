package edu.hitsz.application;

import edu.hitsz.aircraft.EliteEnemy;
import edu.hitsz.factory.EliteFactory;
import edu.hitsz.strategy.DiffuseShootDown5;
import edu.hitsz.strategy.ShootStrategy;

import javax.imageio.ImageIO;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.LinkedList;
import java.util.List;

public class GameHard extends AbstractGame{


    public GameHard() throws FileNotFoundException, UnsupportedEncodingException {
        super();
        super.eliteNum = 12;
    }

    @Override
    public void setBackgroundImage() {
        try {
            ImageManager.BACKGROUND_IMAGE = ImageIO.read(new FileInputStream("src/images/bg5.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(-1);
        }
    }

    @Override
    public boolean hasBoss() {
        return true;
    }

    @Override
    public boolean isBossHpUp() {
        return true;
    }

    @Override
    public int setScoreThreshold() {
        return 500;
    }

    @Override
    public void setEliteNum(){
//        if(eliteAppearCtrl >= 16 && eliteAppearCtrl%16==0){
            if(eliteNum<=1){
                eliteNum = 1;
            }
            else {
                eliteNum -= 1;
            }
//        }
    }
    @Override
    public boolean isCreateElite(int eliteAppearCtrl) {
        if(eliteAppearCtrl >= eliteNum && eliteAppearCtrl % (eliteNum) == 0){
            setEliteNum();
            return true;
        }
        else {
            return false;
        }
    }
    @Override
    public int setEnemyHp() {
        return 60;
    }

    @Override
    public void hardnessPrint(int eliteAppearCtrl) {
        if(eliteAppearCtrl >= 0 && eliteAppearCtrl % (eliteNum+1) == 0){
            float elitePossibility = (float)(12-eliteNum)/8;
            float bossInterval = (float)(scoreThreshold-lastScoreThreshold)/500;
            System.out.println("【难度提示】 HARD: 精英机出现系数为："+elitePossibility+",boss机出现系数为："+bossInterval);
        }
    }

    @Override
    public ShootStrategy setBossStrategy() {
        return new DiffuseShootDown5();
    }

    @Override
    public int setEnemyBulletPower() {
        return 30;
    }
}
