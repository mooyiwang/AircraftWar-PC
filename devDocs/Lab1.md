# 《飞机大战》-- 开发记录（实验一）
## 0 版本记录 v1.0
1. 修改 结束条件：1. 英雄机血量为0；2.英雄机碰撞任意敌机
2. 新增 ”精英机“`eliteEnemy`    
血量: 30 hp    
子弹：方向：向下；伤害：15 hp；数量：按一定周期每次发射一颗    
运动: 左右摇摆向下   
奖励：击毁后+30分，并随机掉落道具
3. 新增 ”道具“`AbstractProp`   
触发方式：消灭精英机后随机掉落    
加血道具：英雄机触碰后增加血量50hp，无上限    
炸弹道具：英雄机触碰后终端打印”BombSupply Active!"    
火力道具：英雄机触碰后终端打印“FireSupply Active！”
4. 新增 “Boss机”类（具体功能未实现）

 ### 历史版本 v0.0
1. 英雄机    
   血量: 100 hp   
   子弹：方向：向上；伤害：30 hp；数量：按一定周期每次发射一颗   
   运动: 玩家鼠标控制
2. 普通敌机    
   血量: 30 hp   
   子弹：无   
   运动: 向下直线   
   奖励：击毁后+10分
3. 结束条件：英雄机碰撞任意敌机
## 1 本次实验完成的内容
1. 分析飞机大战系统功能
2. 导入飞机大战模板程序
3. 使用PlantUML插件绘制敌机和道具类的类图及继承关系   
详见 *uml\Inheritence_new.puml*
4. 在模板程序基础上，增加精英敌机和道具类的代码。         
    1. 敌机类（精英机，Boss机），道具类的创建，包括有关方法重写    
    2. 代码添加：
       1. Game.action():新敌机（精英机）产生
       2. Game.shootAction():敌机射击
       3. Game.crashCheckAction():敌机子弹射击英雄
       4. Game.crashCheckAction():精英机击毁后获得分数，产生道具
       5. Game.crashCheckAction():获得道具，道具生效
       6. Game.postProcessAction():后处理：删除无效的道具
       7. Game.paint():绘制道具
       8. ImageManager:敌机类（精英机，Boss机），道具类图像映射添加
       9. HeroAircraft.increaseHp():添加回血方法
