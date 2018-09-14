## 入口

SpringApplication.run(String... args)

![](images/01_01.png)

### 315加载Listener

  #### 调用关系树
  
  ```
  - getRunListeners
    - getSpringFactoriesInstances
      - SpringFactoriesLoader.loadFactoryNames 
        - loadFactoryNames
          - loadSpringFactories  
  ```          
  这里回去查找所有 jar 下面的 `META-INF/spring.factories` 文件
  
  #### spring.factories文件解读
  ![](images/01_02.png)
  我们会发现很多 jar 下面有这个文件，
             