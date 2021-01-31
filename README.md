# wys-dubbo-server
dubbo脚手架构建步骤

###第一步添加依赖
        <!--dubbo start-->
		<!-- dubbo的依赖 -->
		<dependency>
			<groupId>org.apache.dubbo</groupId>
			<artifactId>dubbo-spring-boot-starter</artifactId>
			<version>2.7.6</version>
		</dependency>

		<!-- zk的依赖 -->
		<dependency>
			<groupId>org.apache.dubbo</groupId>
			<artifactId>dubbo-dependencies-zookeeper</artifactId>
			<version>2.7.6</version>
			<type>pom</type>
			<exclusions>
				<exclusion>
					<groupId>org.slf4j</groupId>
					<artifactId>slf4j-log4j12</artifactId>
				</exclusion>
			</exclusions>
		</dependency>
		<!--dubbo end-->

###第二步设置远程服务类
     只用org.apache.dubbo.config.annotation.Service注解进行标记
###第三步dubbo配置
     #如果指定了spring应用名称，可以缺省dubbo的应用名称，这2个至少要配置1个。缺省dubbo的应用名称时默认值是spring的应用名称
     dubbo.application.name=wys-dubbo
     #指定dubbo使用的协议、端口
     dubbo.protocol.name=dubbo
     dubbo.protocol.port=20880
     dubbo.registry.address=zookeeper://127.0.0.1:2181
     dubbo.registry.timeout=6000
     dubbo.provider.threads=10
     dubbo.provider.threadpool=fixed
     dubbo.provider.loadbalance=random
     dubbo.provider.timeout=10000
     dubbo.provider.retries=0
     dubbo.consumer.timeout=10000
     

     #指定实现服务(提供服务）的包
     dubbo.scan.base-packages=com.wys.dubbo.service.dubbo
     这个配置可以用@EnableDubbo(scanBasePackages = "com.wys.dubbo.service.dubbo")替换