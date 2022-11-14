### 消息队列作用
- 业务系统解耦
- 流量削峰
- 数据分发

### 消息队列需要考虑的问题
- 需要保证 MQ 的高可用
- 系统复杂度增加：消息重复消费、消息丢失、消息传递的顺序性
- 一致性问题：消息数据处理的一致性

### 安装 RocketMQ
#### 祥见官方文档 https://rocketmq.apache.org/zh/docs/quickStart/02quickstart/
- 安装 jdk
- 解压二进制文件
- 启动 NameServer: nohup sh bin/mqnamesrv &，查看启动日志：tail -f ~/logs/rocketmqlogs/namesrv.log
- 启动 Broker：nohub sh bin/mqbroker -n localhost:9876 &，查看启动日志：tail -f ~/logs/rocketmqlogs/broker.log
- 如果虚拟机内存不够，需要修改配置文件：vi runbroker.sh  vi runserver.sh
- 关闭mq：sh bin/mqshutdown namesrv  sh bin/mqshutdown broker
- 使用 Jps 查看是否启动成功