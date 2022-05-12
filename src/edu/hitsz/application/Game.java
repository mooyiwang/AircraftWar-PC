//package edu.hitsz.application;
//
//import edu.hitsz.aircraft.*;
//import edu.hitsz.bullet.BaseBullet;
//import edu.hitsz.basic.AbstractFlyingObject;
//import edu.hitsz.factory.*;
//import edu.hitsz.prop.AbstractProp;
//import edu.hitsz.DAO_ranking.GameRecordDAO;
//import edu.hitsz.DAO_ranking.GameRecordDAOImple;
//import edu.hitsz.prop.BombProp;
//import edu.hitsz.strategy.ShootStrategy;
//
//import javax.imageio.ImageIO;
//import javax.swing.*;
//import java.awt.*;
//import java.awt.image.BufferedImage;
//import java.io.FileInputStream;
//import java.io.FileNotFoundException;
//import java.io.IOException;
//import java.io.UnsupportedEncodingException;
//import java.util.*;
//import java.util.List;
//import java.util.concurrent.*;
//
///**
// * 游戏主面板，游戏启动
// *
// * @author hitsz
// */
//public class Game extends AbstractGame {
//
//
//    private int backGroundTop = 0;
//
//
//    /**
//     * Scheduled 线程池，用于任务调度
//     */
//    private final ScheduledExecutorService executorService;
//
//    private MusicThreadLoop bgm;
//    private MusicThreadLoop bossBgm;
//
//
//
//
//    /**
//     * 时间间隔(ms)，控制刷新频率
//     */
//    private int timeInterval = 40;
//
//    private final HeroAircraft heroAircraft;
//    private final List<AbstractAircraft> enemyAircrafts;
//    private final List<BaseBullet> heroBullets;
//    private final List<BaseBullet> enemyBullets;
//    private final List<AbstractProp> props;
//    private  AbstractProp prop;
//
//    /**
//     * 定义 工厂类
//     */
//    private final EnemyFactory mobFactory;
//    private final EnemyFactory eliteFactory;
//    private final EnemyFactory bossFactory;
//    private final PropFactory randomPropFactory;
//
//
//    /**
//     * enemyMaxNumber 普通机和精英机最大数量
//     * bossMaxNumber  boos最大数量
//     * curBossNum 当前boss数量
//     */
//    private int enemyMaxNumber = 5;
//
//    private int curBossNum = 0;
//
//    private boolean gameOverFlag = false;
//    public static int score = 0;
//    private int scoreThreshold = 300;
//    private int time = 0;
//
//
//    /**
//     * 周期（ms)
//     * 指示子弹的发射、敌机的产生频率
//     */
//    private int cycleDuration = 600;
//    private int cycleTime = 0;
//
//
//    public Game() throws FileNotFoundException, UnsupportedEncodingException {
//        heroAircraft = HeroAircraft.getHeroAircraft();
//
//
//        enemyAircrafts = new LinkedList<>();
//        heroBullets = new LinkedList<>();
//        enemyBullets = new LinkedList<>();
//        props = new LinkedList<>();
//
//        /**
//         * 实例化 工厂类
//         */
//        mobFactory = new MobFactory();
//        eliteFactory = new EliteFactory();
//        bossFactory = new BossFactory();
//        randomPropFactory = new RandomPropFactory();
//
//
//
//        //Scheduled 线程池，用于定时任务调度
//        ThreadFactory gameThread = new ThreadFactory() {
//            @Override
//            public Thread newThread(Runnable r) {
//                Thread t = new Thread(r);
//                t.setName("game thread");
//                return t;
//            }
//        };
//        executorService = new ScheduledThreadPoolExecutor(1,gameThread);
//
//
//        //启动英雄机鼠标监听
//        new HeroController(this, heroAircraft);
//
//    }
//
//    /**
//     * 游戏启动入口，执行游戏逻辑
//     */
//    public void action1() {
//
//        if(Main.GAME_SOUND) {
//            bgm = new MusicThreadLoop("bgm.wav");
////            bossBgm = new MusicThreadLoop("bgm_boss");
//            bgm.start();
////            bulletBgm.start();
//        }
//        // 定时任务：绘制、对象产生、碰撞判定、击毁及结束判定
//        Runnable task = () -> {
//
//                time += timeInterval;
//
//            // 周期性执行（控制频率）
//            if (timeCountAndNewCycleJudge()) {
//                System.out.println(time);
//                // 新敌机产生
//                if (enemyAircrafts.size() < enemyMaxNumber) {
//                    enemyAircrafts.add(mobFactory.enemyCreator());
//                    enemyAircrafts.add(eliteFactory.enemyCreator());
//                }
//                if(score > scoreThreshold ){
//                    if(curBossNum == 0){
//                        enemyAircrafts.add(bossFactory.enemyCreator());
//                        curBossNum = 1;
//                        if(Main.GAME_SOUND){
//                            bgm.setStop();
//                            bossBgm = new MusicThreadLoop("bgm_boss.wav");
//                            bossBgm.start();
//                        }
//                    }
//                    scoreThreshold += 1000;
//
//                }
//
//                // 飞机射出子弹
//                shootAction();
//            }
//
//            // 子弹移动
//            bulletsMoveAction();
//
//            // 飞机移动
//            aircraftsMoveAction();
//
//            // 道具移动
//            propsMoveAction();
//
//            // 撞击检测
//            crashCheckAction();
//
//            // 后处理
//            postProcessAction();
//
//            //每个时刻重绘界面
//            repaint();
//
//            // 游戏结束检查
//            if (heroAircraft.getHp() <= 0) {
//                // 游戏结束
//                executorService.shutdown();
//                gameOverFlag = true;
//                Main.SCORE = score;
//                System.out.println("Game Over!");
//                if(Main.GAME_SOUND){
//                    if(bgm != null && !bgm.getStop()){
//                        bgm.setStop();
//                    }
////                    if(!bulletBgm.getStop()){
////                        bulletBgm.setStop();
////                    }
//                    if(bossBgm != null && !bossBgm.getStop()){
//                        bossBgm.setStop();
//                    }
//                    new MusicThread("game_over.wav").start();
//                    System.out.println(bgm.getStop());
//                }
//                synchronized (Main.lock){
//                    Main.lock.notify();
//                }
//
//            }
//
//
//        };
//
//        /**
//         * 以固定延迟时间进行执行
//         * 本次任务执行完成后，需要延迟设定的延迟时间，才会执行新的任务
//         */
//        executorService.scheduleWithFixedDelay(task, timeInterval, timeInterval, TimeUnit.MILLISECONDS);
//
//
//    }
//
//    //***********************
//    //      Action 各部分
//    //***********************
//
//    private boolean timeCountAndNewCycleJudge() {
//        cycleTime += timeInterval;
//        if (cycleTime >= cycleDuration && cycleTime - timeInterval < cycleTime) {
//            // 跨越到新的周期
//            cycleTime %= cycleDuration;
//            return true;
//        } else {
//            return false;
//        }
//    }
//
//    private void shootAction() {
//        // TODO 敌机射击
//        for(AbstractAircraft enemyAircraft : enemyAircrafts){
//            enemyBullets.addAll(enemyAircraft.shoot());
//        }
//
//        // 英雄射击
//        heroBullets.addAll(heroAircraft.shoot());
//        if(Main.GAME_SOUND){
//            new MusicThread("bullet.wav").start();
//        }
//
//    }
//
//    private void bulletsMoveAction() {
//        for (BaseBullet bullet : heroBullets) {
//            bullet.forward();
//        }
//        for (BaseBullet bullet : enemyBullets) {
//            bullet.forward();
//        }
//    }
//
//    private void aircraftsMoveAction() {
//        for (AbstractAircraft enemyAircraft : enemyAircrafts) {
//            enemyAircraft.forward();
//        }
//    }
//
//    private void propsMoveAction(){
//        for(AbstractProp prop:props){
//            prop.forward();
//        }
//    }
//
//
//    /**
//     * 碰撞检测：
//     * 1. 敌机攻击英雄
//     * 2. 英雄攻击/撞击敌机
//     * 3. 英雄获得补给
//     */
//    private void crashCheckAction() {
//        // TODO 敌机子弹攻击英雄
//        for(BaseBullet bullet : enemyBullets){
//            if(bullet.notValid()){
//                continue;
//            }
//            if(heroAircraft.notValid()){
//                continue;
//            }
//            if(heroAircraft.crash(bullet) || bullet.crash(heroAircraft)){
//                heroAircraft.decreaseHp(bullet.getPower());
//                bullet.vanish();
//            }
//
//        }
//
//
//        // 英雄子弹攻击敌机
//        for (BaseBullet bullet : heroBullets) {
//            if (bullet.notValid()) {
//                continue;
//            }
//            for (AbstractAircraft enemyAircraft : enemyAircrafts) {
//                if (enemyAircraft.notValid()) {
//                    // 已被其他子弹击毁的敌机，不再检测
//                    // 避免多个子弹重复击毁同一敌机的判定
//                    continue;
//                }
//                if (enemyAircraft.crash(bullet)) {
//                    // 敌机撞击到英雄机子弹
//                    // 敌机损失一定生命值
//                    if(Main.GAME_SOUND){
//                        new MusicThread("bullet_hit.wav").start();
//                    }
//                    enemyAircraft.decreaseHp(bullet.getPower());
//                    bullet.vanish();
//                    if (enemyAircraft.notValid()) {
//                        // TODO 获得分数，产生道具补给
//                        if (enemyAircraft instanceof EliteEnemy) {
//                            prop = randomPropFactory.randomPropCreator(enemyAircraft);
//                            if(prop != null){
//                                props.add(prop);
//                            }
//                            score += 20;
//                        }
//                        if (enemyAircraft instanceof BossEnemy) {
//                            prop = randomPropFactory.randomPropCreator(enemyAircraft);
//                            if(prop != null){
//                                props.add(prop);
//                            }
//                            curBossNum = 0;
//                            score += 50;
//                            if(Main.GAME_SOUND){
//                                bossBgm.setStop();
//                                bgm = new MusicThreadLoop("bgm.wav");
//                                bgm.start();
//                            }
//
//                        }
//                        score += 10;
//                    }
//
//                }
//                // 英雄机 与 敌机 相撞，均损毁
//                if (!enemyAircraft.notValid() && (enemyAircraft.crash(heroAircraft) || heroAircraft.crash(enemyAircraft))) {
//                    enemyAircraft.vanish();
//                    heroAircraft.decreaseHp(Integer.MAX_VALUE);
//
////                    synchronized (Main.lock){
////                        Main.lock.notify();
////                    }
//                }
//            }
//        }
//
//        // Todo: 我方获得道具，道具生效
//        for(AbstractProp prop : props){
//            if(prop.crash(heroAircraft) || heroAircraft.crash(prop)){
//                if(prop.notValid()){
//                    continue;
//                }
//                if(!prop.notValid()){
//                    if(prop instanceof BombProp){
//                        if(Main.GAME_SOUND){
//                            new MusicThread("bomb_explosion.wav").start();
//                        }
//                        for(AbstractAircraft enemy:enemyAircrafts){
//                            if(!(enemy instanceof BossEnemy) && !enemy.notValid()){
//                                ((BombProp) prop).addBombed(enemy);
//                            }
//                        }
//                        for(BaseBullet enemybullet: enemyBullets){
//                            if(!enemybullet.notValid()){
//                                ((BombProp) prop).addBombed(enemybullet);
//                            }
//                        }
//                    }
//                    else {
//                        if(Main.GAME_SOUND) {
//                            new MusicThread("get_supply.wav").start();
//                        }
//                    }
//                    prop.function(heroAircraft);
//                    score += 30;
//                }
//                prop.vanish();
//            }
//        }
//    }
//
//    /**
//     * 后处理：
//     * 1. 删除无效的子弹
//     * 2. 删除无效的敌机
//     * 3. 检查英雄机生存
//     * <p>4. 删除无效的道具
//     * 无效的原因可能是撞击或者飞出边界
//     */
//    private void postProcessAction() {
//        enemyBullets.removeIf(AbstractFlyingObject::notValid);
//        heroBullets.removeIf(AbstractFlyingObject::notValid);
//        enemyAircrafts.removeIf(AbstractFlyingObject::notValid);
//        props.removeIf(AbstractFlyingObject::notValid);
//    }
//
//
//    //***********************
//    //      Paint 各部分
//    //***********************
//
//    /**
//     * 重写paint方法
//     * 通过重复调用paint方法，实现游戏动画
//     *
//     * @param  g
//     */
//    @Override
//    public void paint(Graphics g) {
//        super.paint(g);
//
//        // 绘制背景,图片滚动
//        g.drawImage(ImageManager.BACKGROUND_IMAGE, 0, this.backGroundTop - Main.WINDOW_HEIGHT, null);
//        g.drawImage(ImageManager.BACKGROUND_IMAGE, 0, this.backGroundTop, null);
//        this.backGroundTop += 1;
//        if (this.backGroundTop == Main.WINDOW_HEIGHT) {
//            this.backGroundTop = 0;
//        }
//
//        // 先绘制子弹，后绘制飞机
//        // 这样子弹显示在飞机的下层
//        paintImageWithPositionRevised(g, enemyBullets);
//        paintImageWithPositionRevised(g, heroBullets);
//
//        paintImageWithPositionRevised(g, props);
//
//        paintImageWithPositionRevised(g, enemyAircrafts);
//
//
//
//        g.drawImage(ImageManager.HERO_IMAGE, heroAircraft.getLocationX() - ImageManager.HERO_IMAGE.getWidth() / 2,
//                heroAircraft.getLocationY() - ImageManager.HERO_IMAGE.getHeight() / 2, null);
//
//        //绘制得分和生命值
//        paintScoreAndLife(g);
//
//    }
//
//    private void paintImageWithPositionRevised(Graphics g, List<? extends AbstractFlyingObject> objects) {
//        if (objects.size() == 0) {
//            return;
//        }
//
//        for (AbstractFlyingObject object : objects) {
//            BufferedImage image = object.getImage();
//            assert image != null : objects.getClass().getName() + " has no image! ";
//            g.drawImage(image, object.getLocationX() - image.getWidth() / 2,
//                    object.getLocationY() - image.getHeight() / 2, null);
//        }
//    }
//
//    private void paintScoreAndLife(Graphics g) {
//        int x = 10;
//        int y = 25;
//        g.setColor(new Color(16711680));
//        g.setFont(new Font("SansSerif", Font.BOLD, 22));
//        g.drawString("SCORE:" + score, x, y);
//        y = y + 20;
//        g.drawString("LIFE:" + this.heroAircraft.getHp(), x, y);
//    }
//
//    @Override
//    public boolean hasBoss() {
//        return false;
//    }
//
//    @Override
//    public boolean isBossHpUp() {
//        return false;
//    }
//
//    @Override
//    public int setScoreThreshold() {
//        return Integer.MAX_VALUE;
//    }
//
//
//    @Override
//    public int setEnemyHp() {
//        return 10;
//    }
//
//    @Override
//    public void hardnessPrint() {
//        System.out.println("【难度提示】 EASY: 难度不随时间改变。");
//    }
//
//    @Override
//    public ShootStrategy setBossStrategy() {
//        return null;
//    }
//
//    @Override
//    public int setEnemyBulletPower() {
//        return 0;
//    }
//
//    @Override
//    public void setBackgroundImage() {
//        try {
//            ImageManager.BACKGROUND_IMAGE = ImageIO.read(new FileInputStream("src/images/bg2.jpg"));
//        } catch (IOException e) {
//            e.printStackTrace();
//            System.exit(-1);
//        }
//    }
//
//
//
//}
