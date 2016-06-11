# SnakeGameEx
## 单人模式逻辑
* 初始化: 屏幕左上角出现一条蛇,方向向右.
* 开始: 按住当前方向键可以加速,每吃到一个食物分数+10,蛇身颜色会随分数升高按光谱波长由短到长变化.到一定程度后蛇身颜色开始随机闪烁.
* 规则: 蛇不可穿墙,撞到墙或者吃到自己后游戏结束.
* 游戏结束: 点击重新开始可以恢复到初始化状态


## 单机双人游戏逻辑  
* 初始化: 画板出现两条蛇,P1在左上,方向向右;P2在右下,方向向左.随机产生食物
* 规则:
  * 用两个线程分别控制两条蛇,同一时间只出现一个食物.
  * 两条蛇身体可以重叠,但蛇头不能碰撞.
    * 蛇头先碰撞到另一方身体的算输.
    * 两个蛇头对撞,分数少的算输.
* 游戏结束: 点击重新开始,回到初始化状态.

## TODO:  
- [x] 单人模式
- [x] 单机双人模式
- [x] 关于  
- [ ] 装载游戏 
- [ ] 高分榜  
