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
                <exclusion>
                    <artifactId>log4j</artifactId>
                    <groupId>log4j</groupId>
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
     
###直连测试
      服务端
      dubbo.registry.address=N/A
      dubbo.registry.register=false
      客户端
      dubbo.registry.address=zookeeper://127.0.0.1:2181(必须设置否则报错)
      dubbo.registry.register=false
      @Reference(url = "dubbo://localhost:20880",timeout = 6000)
   
###多版本控制
      当系统进行升级的时候，一般采用灰度发布（又称为金丝雀发布）过程。一部分调用新接口，一部分调用旧接口，并逐步替换成新街口。
      多版本控制就是实现灰度发布的。
      多个类实现同一个接口
      @DubboService(version = "2.0.0")
      @DubboReference(version = "2.0.0")
###服务分组
      同一接口下的不同逻辑，类似于工厂模式，但是不同于多版本控制，服务分组可以互相调用
      @DubboService(group = "pay.zhifubao")
      @DubboReference(id = "weixin",group = "pay.weixin")
      多版本控制与服务分组  这两属性用起来差不多
###多协议支持
 ####dubbo协议
    Dubbo默认协议；单连接，长连接  传输协议：TCP 传输方式：NIO异步传输  传参数据包较小，<100K 消费者个数>提供者个数 不能传输大文件或超大字符串
 ####rmi协议
    JDK标准实现；多连接、短连接   传输协议：TCP 传输方式:BIO同步传输  可传文件 消费者个数==提供者个数
 ####hession协议
    JDK标准实现；多连接、短连接   传输协议：HTTP 传输方式:BIO同步传输  可传文件 消费者个数<提供者个数
 ####同一服务支持多种协议或者不同服务使用不同协议
    #dubbo多协议配置
      dubbo.protocols.dubbo.name=dubbo
      dubbo.protocols.dubbo.port=20880
      dubbo.protocols.dubbo.server=netty
      dubbo.protocols.rmi.name=rmi
      dubbo.protocols.rmi.port=10880
      dubbo.protocols.rmi.server=netty
    @DubboService(protocol = {"dubbo","rmi"})
    @DubboReference(protocol = "rmi")
###负载均衡
    Dubbo内置的负载均衡算法  random随机算法、roundrobin轮询算法、leastactive最少活跃度调度算法
    配置文件  dubbo.provider.loadbalance=random
    提供者注解  @DubboService(loadbalance = "random")
    提供者方法注解  @DubboService(methods = {@Method(name = "get",loadbalance = "random")})
    消费者注解  @DubboReference(loadbalance = "random")
    消费者方法注解  @DubboReference(methods = {@Method(name="get",loadbalance = "random")})
###集群容错
    当消费者调用提供者集群时发生异常的处理方案
    Dubbo内置的容错策略
    failover
    故障转移策略，当消费者调用提供者集群中的某个服务失败时，其会自动尝试调用其他服务器。该策略通常用于读操作。
    failfast
    快速失败策略，消费者端只发起一次调用，若失败则立即报错。通常用于非幂等性的写操作
    failsafe
    失败安全策略，当消费者调用提供者出现异常时，直接忽略本次消费操作。
    failback
    失败自动恢复策略，消费者调用提供者失败后，dubbo会记录下该失败请求，然后定时自动重新发送该请求。用于实时性要求不高的操作
    forking
    并行策略，消费者对于同一个服务并行调用多个提供者服务器，只要一个成功即调用结束并返回结果。通常用于实时性要求比较高的读操作
####配置集群容错策略
    Dubbo默认的容错策略是故障转移策略failover，即允许失败后重试。
    设置重试次数
    @DubboService(retries = 1) 正常调用一次，重试一次
    @DubboService(methods = {@Method(name = "get",retries = 1)})
    @DubboReference(retries = 1)
    @DubboReference(methods = {@Method(name = "get",retries = 1)})
    设置容错策略
    @DubboService(cluster = "failfast")
    @DubboReference(cluster = "failfast")
###服务降级
    服务降级，当服务压力剧增的情况下，根据当前业务情况及流量对一些服务有策略的降低服务级别，以释放服务器资源，保证核心任务的正常运行。比如双十一
    修改收货地址或者查看历史订单
    Dubbo的服务降级采用Mock机制，其具有两种降级处理方式：Mock null降级处理；Class Mock降级处理
     1、Mock null降级处理
     @DubboReference(mock = "return null")
     2、Class Mock降级处理
     业务接口所在包中，定义一个类，此类的命名满足：业务接口简单类名+Mock
     实现业务接口，并实现降级所需处理方法
     @DubboReference(mock = "true")
     3、服务调用超时降级处理
     @DubboService(timeout = 3000)或者@DubboReference(mock = "true",timeout = 3000)
###服务限流
    为了防止某个消费者的QPS或是所有消费者QPS总和突然飙升而导致的重要服务的失效，系统可以对访问流量进行控制，这种对集群的保护措施称为服务限流。
####直接限流
#####executes限流--仅提供者端
     服务提供者每服务每方法最大可并行执行请求数
     @DubboService(executes = 10)
     @DubboService(methods = {@Method(name="get",executes = 10)})
#####accepts限流--仅提供者端
     限制当前提供者在使用dubbo协议时最多接受10个消费者连接
     dubbo.provider.accepts=10
     dubbo.provider.protocol=dubbo

      #限制当前提供者在使用dubbo协议时最多接受10个消费者连接
      dubbo.protocol.accepts=10
      #dubbo多协议配置
      dubbo.protocols.dubbo.accepts=10
      dubbo.protocols.rmi.accepts=10
#####actives限流--两端均可
     A.提供者端限流
      长连接：当前这个服务上的一个长连接最多能处理的请求个数，对长连接数量没有限制
      短链接：当前这个服务上可以同时处理的短链接数量
      @DubboService(executes = 10,actives = 10) 优先executes  官方文档服务端没有actives属性配置
      @DubboService(methods = {@Method(name = "get",actives = 10)})
      dubbo.provider.actives=10
    B.提供者端限流
      长连接：当前这个消费者的一个长连接最多能提交的请求个数，对长连接数量没有限制
      短链接：当前这个消费者可以同时提交的短链接数量
       @DubboReference(actives = 10)
      @DubboReference(methods = {@Method(name = "get",actives = 10)})
      dubbo.consumer.actives=10
#####connections限流--两端均可 通常与actives联用
     对每个提供者的最大连接数，rmi、http、hessian等短连接协议表示限制连接数，dubbo等长连接协表示建立的长连接个数
     @DubboService(connections = 100)
     dubbo.provider.connections=100
     每个服务对每个提供者的最大连接数，rmi、http、hessian等短连接协议支持此配置，dubbo协议长连接不支持此配置
     @DubboReference(connections = 100)
     dubbo.consumer.connections=100
####间接限流
    1、延迟连接(官方文档没有这个属性)
    只有消费者真正调用提供者方法时才创建长连接,仅作用于Dubbo服务暴露协议，用于减少长连接数量
    dubbo.consumer.lazy=true
    @DubboReference(lazy = false)
    2、粘连连接(官方文档没有这个属性)
    系统会尽量让同一个消费者调用同一个提供者，其可以限定流向。
    仅设置在消费端，仅作用于Dubbo服务暴露协议，用于减少长连接数量，粘连连接的开启将自动开启延迟连接
    @DubboReference(methods = {@Method(name="get",sticky = false)})
    @DubboReference(sticky = false)
    3、负载均衡配置
    @DubboService(loadbalance = "leastactive") leastactive最少活跃度调度算法可以限制流向
###声明式缓存（比较鸡肋）
    以调用参数为key，缓存返回结果，可选：lru, threadlocal, jcache等
    dubbo.consumer.cache=true
    @DubboReference(methods = {@Method(name="get",cache = true)})
    @DubboReference(cache = true)
###多注册中心
    @DubboService(registry = "abc,bcd")向多个注册中心注册
    abc、bcd是registry的id
    @DubboReference(registry = "abc=")向一个注册中心注册
###单功能注册中心
    是否向此注册中心订阅服务，如果设为false，将只注册，不订阅
    dubbo.registry.subscribe=true
    是否向此注册中心注册服务，如果设为false，将只订阅，不注册
    dubbo.registry.register=true
###服务延迟暴露
    @DubboService(delay = 0)
    dubbo.provider.delay=0
    正数：单位为毫秒，表示在提供者对象创建完毕后的指定时间后再发布服务
    0:默认值，表示在提供者对象创建完毕后马上向注册中心暴露服务
    -1:表示Spring容器初始化完毕后再向注册中心暴露服务
###消费者端的异步调用
    @DubboReference(async = true)
    @DubboReference(version = "1.0.0",methods = {@Method(name="get",async = true)})
####无返回值
    消费端直接调用服务端服务接口方法
####返回值同步阻塞
    Future<RpcExecuteResult> future = RpcContext.getContext().getFuture();
####返回值无阻塞
    服务端使用CompletableFuture<RpcExecuteResult>作为返回值
    客户端不设置async属性
    获取异步方法返回通知
    completableFuture.whenComplete(new BiConsumer<RpcExecuteResult, Throwable>() {
            @Override
            public void accept(RpcExecuteResult rpcExecuteResult, Throwable throwable) {
                
            }
        });
###提供者端的异步调用
    CompletableFuture<RpcExecuteResult> future = CompletableFuture.supplyAsync(new Supplier<RpcExecuteResult>() {
        @Override
        public RpcExecuteResult get() {
                DemoResultDto resultDto = new DemoResultDto();
                resultDto.setId(1);
                resultDto.setDescription("description");
                RpcExecuteResult<DemoResultDto> rpcExecuteResult = RpcExecuteResult.ok(resultDto);
                sleep("get2",start);
            return rpcExecuteResult;
        }
    });
###属性配置优先级
       方法级优先，接口次之，全局配置最后；
       如果级别一样，消费方优先，提供方次之
       dubbo.consumer.用于设置消费端的默认配置
       dubbo.provider.用于设置提供端的默认配置
   

     
     
    
    
   
 
   