![laputa](laputa-master/laputa-foundation/laputa-foundation-web/src/main/webapp/static/log/logo.png.png)


QQ 群 724851867

sql : laputa-master/doc/sql/laputa-2018-04-05.sql

所有项目配置文件默认位置 :  resources/laputa-config/sys_config.properties

laputa.app.name : 程序名
laputa.app.ver : 版本
laputa.app.groups : 分组

上面3个配置主要为了从配置中心获取配置用

laputa.dubbo.zookeeper.address=127.0.0.1:2181

DB_CONN=jdbc:mysql://127.0.0.1:3306/laputa?useUnicode=true&characterEncoding=utf8&useSSL=false
DB_USERNAME=root
DB_PASSWORD=root


dubbo版 demo启动顺序:
com.laputa.foundation.web.rbac.LaputaRbacServiceDevApp 

laputa-demo-rbac-admin-by-dubbo mvn jetty run

127.0.0.1:9000/loginform [ admin / admin ]

demo 依赖的 laputa-foundation-rbac-manager-web 是普通的增删查改页面的1个web项目

laputa-foundation-rbac-manager-web 依赖的 laputa-foundation-web 则是基础,同样是1个
web项目

这些项目每个都可以独立运行,又可以互相继承,包括静态资源


