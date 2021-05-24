# 毕业设计
基于数字签名的投票系统的设计与实现
## 1. 系统架构
- SSM框架
- Maven包管理
- MySQL数据库
- Tomcat服务器

## 2. 开发工具

- IDEA

## 3. 环境配置

![框架配置](README.assets/image-20210524133319870.png)

- resources里的配置文件用于配置SSM框架，包括映射、控制反转、工厂、JDBC等。
- web.xml进行网络配置，引入SSM框架。

![服务器配置](README.assets/image-20210524133402098.png)

- 使用IDEA自带的轻量级tomcat7运行，也可以添加自选服务器。

## 4.  系统运行

![系统运行](README.assets/image-20210524133540948.png)

## 5. 数据库

![数据库版本](README.assets/image-20210524133834903.png)

- 使用SQLyog（v12.3.1）创建和配置表，适配所有MySQL

![数据库配置](README.assets/image-20210524134102675.png)

- 对db.properties文件进行修改，配置成自己的JDBC和相应的数据库驱动