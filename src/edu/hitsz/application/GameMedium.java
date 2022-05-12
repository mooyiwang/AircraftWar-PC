package edu.hitsz.application;

import edu.hitsz.aircraft.EliteEnemy;
import edu.hitsz.factory.EliteFactory;
import edu.hitsz.strategy.DiffuseShootDown3;
import edu.hitsz.strategy.DiffuseShootDown5;
import edu.hitsz.strategy.ShootStrategy;

import javax.imageio.ImageIO;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.LinkedList;
import java.util.List;

public class GameMedium extends AbstractGame{
    public GameMedium() throws FileNotFoundException, UnsupportedEncodingException {
        super();
        super.eliteNum = 16;
    }

    @Override
    public void setBackgroundImage() {
        try {
            ImageManager.BACKGROUND_IMAGE = ImageIO.read(new FileInputStream("src/images/bg3.jpg"));
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
        return false;
    }

    @Override
    public int setScoreThreshold() {
        return 800;
    }

    @Override
    public void setEliteNum(){
//        if(eliteAppearCtrl >= 24 && eliteAppearCtrl%24==0){
            if(eliteNum<=1){
                eliteNum = 1;
            }
            else{
                eliteNum --;
            }
//        }
    }
    @Override
    public boolean isCreateElite(int eliteAppearCtrl) {
        if(eliteAppearCtrl >= eliteNum && eliteAppearCtrl % eliteNum == 0){
            setEliteNum();
            return true;
        }
        else {
            return false;
        }
    }

    @Override
    public int setEnemyHp() {
        return 30;
    }

    @Override
    public void hardnessPrint(int eliteAppearCtrl) {
        if(eliteAppearCtrl >= eliteNum && eliteAppearCtrl % (eliteNum+1) == 0){
            float elitePossibility = (float)(16-eliteNum)/8;
            float bossInterval = (float)(scoreThreshold-lastScoreThreshold)/800;
            System.out.println("【难度提示】 HARD: 精英机出现系数为："+elitePossibility+",boss机出现系数为："+bossInterval);
        }
    }

    @Override
    public ShootStrategy setBossStrategy() {
        return new DiffuseShootDown3();
    }

    @Override
    public int setEnemyBulletPower() {
        return 15;
    }
}

