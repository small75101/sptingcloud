Git，local，svn三种配置方式
1、config 默认Git加载
通过spring.cloud.config.server.git.uri指定配置信息存储的git地址，比如：https://github.com/xxx/config-repo

2、加载本地开发环境
spring.profiles.active=native
spring.cloud.config.server.native.searchLocations=classpath:/config

3、加载 本地物理环境
spring.profiles.active=native
spring.cloud.config.server.native.searchLocations= file:E:\\Java\\config

4、加载svn环境  http://localhost:8080/{application}/{profile}/{label}，比如：http://localhost:8080/dmeo/development/trunk
### config server svn
spring.cloud.config.server.svn.uri=http://localhost:8080/dmeo/development/trunk
spring.cloud.config.server.svn.username=xxx
spring.cloud.config.server.svn.password=xxx
spring.profiles.active=subversion
