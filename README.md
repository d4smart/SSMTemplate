# SSMTemplate

SSM整合配置，基于配置实现了一个简单的功能示例

## 使用方法

1. 从github下载源代码，添加为maven项目（Add as maven project）
2. 修改数据库配置（jdbc.properties)，在数据库中建立[user表](./user.sql)
3. 配置服务器（tomcat），点击运行即可
4. 如果需要使用mybatis-generator，需要配置generatorConfig.xml和jdbc.properties中的db.driverLocation属性

## 说明

可以根据自己的需要修改代码和配置，项目在java目录下写了一个简单的功能示例，可以删除
