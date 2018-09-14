## 入口

SpringApplication.run(String... args)

```Java
public ConfigurableApplicationContext run(String... args) {
// 01-01 加载 Listener
SpringApplicationRunListeners listeners = getRunListeners(args);
listeners.starting();
try {
    ApplicationArguments applicationArguments = new DefaultApplicationArguments(args);
    ConfigurableEnvironment environment = prepareEnvironment(listeners, applicationArguments);
    configureIgnoreBeanInfo(environment);
    Banner printedBanner = printBanner(environment);
    context = createApplicationContext();
    exceptionReporters = getSpringFactoriesInstances(
            SpringBootExceptionReporter.class,
            new Class[] { ConfigurableApplicationContext.class }, context);
    prepareContext(context, environment, listeners, applicationArguments,
            printedBanner);
    refreshContext(context);
    afterRefresh(context, applicationArguments);
    stopWatch.stop();
    if (this.logStartupInfo) {
        new StartupInfoLogger(this.mainApplicationClass)
                .logStarted(getApplicationLog(), stopWatch);
    }
    listeners.started(context);
    callRunners(context, applicationArguments);
}
catch (Throwable ex) {
    handleRunFailure(context, ex, exceptionReporters, listeners);
    throw new IllegalStateException(ex);
}

try {
    listeners.running(context);
}
catch (Throwable ex) {
    handleRunFailure(context, ex, exceptionReporters, null);
    throw new IllegalStateException(ex);
}
return context;
}
```

### 01-01 加载 `Listener`

  #### 调用关系树
  
  ```
  - getRunListeners
    - getSpringFactoriesInstances
      - SpringFactoriesLoader.loadFactoryNames 
        - loadFactoryNames
          - loadSpringFactories  
  ```          
  这里回去查找所有 jar 下面的 `META-INF/spring.factories` 文件
  
  #### spring.factories 文件解读
             