# ms-template

Here are the list of features provided in the library and the template show cases how to use these features with ease. 

## VIP management (local and global)
```xml
        <dependency>
            <groupId>com.services.micro.commons</groupId>
            <artifactId>commons-vip</artifactId>
            <version>1.0.0</version>
        </dependency>
```
```yaml
service:
  vip:
    enabled: false
```
* http://localhost:8080/demo/vipStatus
* http://localhost:8080/demo/vipLocalStatus
* http://localhost:8080/demo/vipGlobalStatus

## JSONP support
```xml
        <dependency>
            <groupId>com.services.micro.commons</groupId>
            <artifactId>commons-jsonp</artifactId>
            <version>1.0.0</version>
        </dependency>
```

```yaml
service:
  jsonp:
    enabled: true
```
* http://localhost:8080/demo/test?callback=abc

## Memcache integration
```xml
       <dependency>
                  <groupId>com.services.micro.commons</groupId>
                  <artifactId>commons-memcache</artifactId>
                  <version>1.0.0</version>
              </dependency>
```
```yaml
service:
  memcache:
    enabled: true
    configs:
      - ttl: 3600
        servers: localhost:11211
        name: default
      - ttl: 36000
        servers: localhost:11211
        name: autofill
```
        


## Telemetry support OOB (metrics ) 
```xml
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-actuator</artifactId>
        </dependency>
        <dependency>
            <groupId>com.services.micro.commons</groupId>
            <artifactId>commons-metric</artifactId>
            <version>1.0.0</version>
        </dependency>
```
```yaml
service:
  metrics:
    dropwizard:
      enabled: false
    prometheus:
      enabled: false
    jmx:
      enabled: true
```
*  http://localhost:8080/demo/metrics
*  http://localhost:8080/demo/dropMetrics
*  http://localhost:8080/demo/promMetrics

```java
    @Timed
    @ExceptionMetered
    @LogExecutionTime
    public ServiceResponse getResponse(String key) { .. }
```


## CircuitBreaker for dependencies 
```xml
       <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-hystrix</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-hystrix-dashboard</artifactId>
        </dependency>
```
* http://localhost:8080/demo/hystrix
* http://localhost:8080/demo/hystrix/monitor?stream=http%3A%2F%2Flocalhost%3A8080%2Fdemo%2Fhystrix.stream

## Service registration and discovery and load-balancing with Eureka . 
```xml
```
## Swagger integration for SDK generation and documentation
```xml
        <dependency>
            <groupId>com.services.micro.commons</groupId>
            <artifactId>commons-swagger</artifactId>
            <version>1.0.0</version>
        </dependency>
```
```yaml
service:
  swagger:
    enabled: true
#    base-package: com.micro.services.search.resource
```
*  http://localhost:8080/demo/swagger-ui.html
 
```java
   @ApiOperation(
            value = "Get search results ",
            notes = "Pass q and other parameters to get relevant suggestions back ",
            response = SearchServiceResponse.class
    )
    public SearchServiceResponse test() throws Exception {...}
``` 
 
## SCM (git) build status of the deployed service
```xml
        <dependency>
            <groupId>com.services.micro.commons</groupId>
            <artifactId>commons-git</artifactId>
            <version>1.0.0</version>
        </dependency>
```
```yaml
service:
  git:
    enabled: true
```
*  http://localhost:8080/demo/buildStatus
 
```xml
pom.xml
            <plugin>
                <groupId>pl.project13.maven</groupId>
                <artifactId>git-commit-id-plugin</artifactId>
                <version>2.2.1</version>
                <configuration>
                    <failOnNoGitDirectory>false</failOnNoGitDirectory>
                </configuration>
            </plugin>
```
 
## Auto configuration with GIT as backend server using spring cloud config server
```xml
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-config-server</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-config</artifactId>
        </dependency>
```

## Distributed log tracing and analysis
```xml
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-zipkin</artifactId>
        </dependency>
```
```yaml
spring:
  zipkin:
    base-url: http://localhost:8680/
    flush-interval: 15
    enabled: false
```
## Performance Logging
```xml
            <dependency>
                    <groupId>com.services.micro.commons</groupId>
                    <artifactId>commons-logging</artifactId>
                    <version>1.0.0</version>
                </dependency>
```
```yaml
service:
  logging:
    enabled: false
```
```java
    @LogExecutionTime
    public ServiceResponse getMessage() { ..}
```

## PMD integration
```xml
pom.xml
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-pmd-plugin</artifactId>
                <version>3.8</version>
                <configuration>
                    <printFailingErrors>true</printFailingErrors>
                    <!--<rulesets>-->
                    <!--<ruleset>example_pmd.xml</ruleset>-->
                    <!--</rulesets>-->
                </configuration>
                <executions>
                    <execution>
                        <goals>
                            <goal>check</goal>
                            <goal>cpd-check</goal>
                        </goals>
                    </execution>
                </executions>
            </plugin>
```

## Checkstyle integration
```xml
pom.xml
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-checkstyle-plugin</artifactId>
                <version>2.17</version>

                <executions>
                    <execution>
                        <id>check</id>
                        <phase>validate</phase>
                        <goals>
                            <goal>check</goal>
                            <!--<goal>checkstyle</goal>-->
                        </goals>
                        <configuration>
                            <configLocation>${basedir}/checkstyle.xml</configLocation>
                            <encoding>UTF-8</encoding>
                            <consoleOutput>true</consoleOutput>
                            <failsOnError>true</failsOnError>
                            <failOnViolation>true</failOnViolation>

                            <includeTestSourceDirectory>true</includeTestSourceDirectory>
                        </configuration>
                    </execution>
                </executions>
            </plugin>
```

## Cobetura integration
```xml
pom.xml
            <plugin>
                <groupId>org.codehaus.mojo</groupId>
                <artifactId>cobertura-maven-plugin</artifactId>
                <version>2.7</version>
                <configuration>
                    <check>
                        <haltOnFailure>true</haltOnFailure>
                        <!--<branchRate>95</branchRate>-->
                        <lineRate>0</lineRate>
                        <!--<totalBranchRate>5</totalBranchRate>-->
                        <totalLineRate>0</totalLineRate>
                    </check>
                </configuration>
                <executions>
                    <execution>
                        <goals>
                            <goal>clean</goal>
                            <goal>check</goal>
                        </goals>
                    </execution>
                </executions>
            </plugin>
```

## API artifacts
```xml
pom.xml
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-jar-plugin</artifactId>
                <version>3.0.2</version>
                <executions>
                    <execution>
                        <phase>package</phase>
                        <goals>
                            <goal>jar</goal>
                        </goals>
                        <configuration>
                            <classifier>client</classifier>
                            <includes>
                                <include>**/demo/api/**</include>
                                <include>**/demo/config/**</include>
                            </includes>
                        </configuration>
                    </execution>
                </executions>
            </plugin>
``` 
