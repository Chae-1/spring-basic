빈 스코프
==
빈이 존재할 수 있는 범위를 빈 스코프라고 한다.

지금까지의 스프링 빈은 시작부터 끝까지 생존하는 스코프를 가졌다.

**스프링은 다양한 스코프를 지원한다.**
- 싱글톤 스코프: 스프링 컨테이너의 시작과 종료까지 유지되는 넓은 범위의 스코프
- 프로토타입 스코프: 빈의 생성과 의존관계 주입만 관여하고 더는 관리하지 않는 짧은 범위의 스코프
  - 의존관계만 주입받는 일회용 객체의 느낌?
- 웹 관련 스코프
  - request: 웹 요청이 들어오고 나갈때 까지 유지되는 스코프
  - session: 웹 세션이 생성되고 종료될 때 까지 유지되는 스코프
  - application:웹의 서블릿 컨텍스트와 같은 범위로 유지되는 스코프

## 프로토타입 스코프
싱글톤 빈은 항상 같은 인스턴스의 스프링 빈을 반환한다. 반면 프로토타입 스코프를 조회하면 
항상 새로운 인스턴스를 반환해서 반환한다.

프로토타입 빈 요청
- 1. 프로토타입 스코프의 빈을 요청한다.
- 2. 스프링 컨테이너는 이 시점에 빈을 생성하고, 필요한 의존관계를 주입한다.
- 3. 생성된 빈을 클라이언트에게 반환하고 더 이상 관리하지 않는다.
- 4. 이후에 같은 요청이 오면 항상 새로운 프로토타입 빈을 생성해서 반환

스프링 컨테이너가 프로토타입 빈을 생성하고 의존관계를 주입, 초기화까지만 처리하고 이후 관리하지 않는다.
이제 기존 객체처럼 클라이언트가 관리하게 된다. 그래서 @PreDestroy 같은 종료 메서드가 호출되지 않는다.
```java
public class PrototypeTest {

    @Test
    void prototypeBeanFind() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class);
        System.out.println("find prototypeBean1");
        PrototypeBean bean1 = ac.getBean(PrototypeBean.class);
        System.out.println("find prototypeBean1");
        PrototypeBean bean2 = ac.getBean(PrototypeBean.class);
        assertThat(bean1).isNotSameAs(bean2);
        ac.close();
    }

    @Scope("prototype")
    static class PrototypeBean {
        @PostConstruct
        public void init() {
            System.out.println("PrototypeBean.init");
        }

        @PreDestroy
        public void close() {
            System.out.println("PrototypeBean.close");
        }
    }
}

```
- 싱글톤 빈은 스프링 컨테이너 생성 시점에 초기화 메서드가 실행되지만, 프로토타입 스코프의 빈은 조회 시점에 생성되고 초기화 메서드가 실행된다.
- 프로토타입 빈은 항상 새로운 빈을 생성하여 반환한다.
- 클라이언트가 관리하기때문에 `@PreDestroy` 종료 메서드가 호출이 안된다.

**싱글톤 빈과 함께 사용시 문제점**
- 싱글톤 빈이 생성되는 과정에서 프로토타입 빈을 주입받는다.
  - 한번 주입된 프로토타입 빈은 싱글톤 빈이 소멸될 때까지 유지가 된다.
- 여러 클라이언트가 싱글톤 빈을 조회해도 처음 주입받은 프로토타입 빈을 가지고 있다.

즉, 싱글톤 빈에서 프로토타입 빈을 필요로 할 때, 사용할 때마다 새로 생성되는 것이 아니라 주입 받은 시점에 한번 생성된다.

**프로토타입 스코프와 싱글톤 빈 함께 사용**

항상 새로운 프로토타입 빈을 싱글톤 빈에서 사용하고 싶을 때

1. 싱글톤 빈이 프로토타입 빈을 스프링 컨테이너에 요청해서 사용
```java
public class SingletonWithPrototypeTest {

    @Test
    void prototypeFind() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class);
        PrototypeBean prototypeBean1 = ac.getBean(PrototypeBean.class);
        prototypeBean1.addCount();
        assertThat(prototypeBean1.getCount()).isSameAs(1);

        PrototypeBean prototypeBean2 = ac.getBean(PrototypeBean.class);
        prototypeBean2.addCount();
        assertThat(prototypeBean2.getCount()).isSameAs(1);

    }

    @Test
    void singleTonClientUsePrototype() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(ClientBean.class, PrototypeBean.class);
        ClientBean clientBean1 = ac.getBean(ClientBean.class);
        int count1 = clientBean1.logic();
        assertThat(count1).isSameAs(1);

        ClientBean clientBean2 = ac.getBean(ClientBean.class);
        int count2 = clientBean2.logic();
        assertThat(count2).isSameAs(1);

    }


    static class ClientBean {
//        private final PrototypeBean prototypeBean; // 생성 시점에 주입

        @Autowired
        ApplicationContext ac;

        public ClientBean() {
//            this.prototypeBean = prototypeBean;
        }

        public int logic() {
            PrototypeBean prototypeBean = ac.getBean(PrototypeBean.class);
            prototypeBean.addCount();
            return prototypeBean.getCount();
        }
    }

    @Scope("prototype")
    static class PrototypeBean {
        private int count = 0;

        public void addCount() {
            count++;
        }

        public int getCount() {
            return count;
        }

        @PostConstruct
        public void init() {
            System.out.println("PrototypeBean.init : " + this);
        }

        @PreDestroy
        public void destroy() {
            System.out.println("PrototypeBean.destroy");
        }
    }
}
```
- 싱글톤 빈으로 등록된 시점에 의존관계 주입을 받으면, 소멸될 때까지 프로토타입 빈을 유지한다.
  - 이 때문에, 해당 빈이 필요한 시점에 스프링 컨테이너에 요청해서 새로운 빈에 요청하면 된다.
- 하지만, 스프링 컨테이너에 의존하게 되는 문제가 발생한다. 단위 테스트가 어려워지는 문제 발생

외부에서 의존관계를 주입받는 것이 아닌, 클라이언트 코드에서 필요한 의존관계를 탐색하는 것을 DL(Dependency Look up)이라한다.

스프링 컨테이너가 아닌, DL을 할 수 있는 수단을 통해 해결할 수 있다.

**ObjectProvider, ObjectFactory**
```java
static class ClientBean {

    @Autowired
    ObjectProvider<PrototypeBean> provider;
        
    public int logic() {
        PrototypeBean prototypeBean = provider.getObject();
        prototypeBean.addCount();
        return prototypeBean.getCount();
    }
}
```
- 스프링 컨테이너가 아닌, `ObjectProvider`가 존재한다.
  - 스프링 컨테이너를 통해 해당 빈을 찾아 반환한다.
  - 스프링 컨테이너를 직접 사용하는 것보다 기능이 단순하다.
  - `ObjectFactory` 인터페이스를 상속받고 있다.
  - `ObjectProvider`가 더 많은 기능을 내포하고 있다.


**Provider**
```java
static class ClientBean {

    Provider<PrototypeBean> provider;
    public int logic() {
        PrototypeBean prototypeBean = provider.get();
        prototypeBean.addCount();
        return prototypeBean.getCount();
    }
}
```
- `jakarta.inject.Provider` 사용하는 방법
- 자바 표준 스프링 컨테이너에서 빈을 탐색해서 반환하는 것은 똑같다.
- 기능이 단순하다.
- 필요한 수준의 DL만 제공한다.

**정리**
- 프로토타입 빈은 매번 사용할 때마다 새로운 객체가 필요할 때 사용한다.
- ObjectProvider, Provider는 DL이 필요한 경우 사용가능.

## 웹 스코프
- 웹 환경에서만 동작하는 스코프
- 프로토타입과 다르게 스프링이 해당 스코프의 종료시점까지 관리한다.

**종류**
- request: HTTP 요청 하나가 들어오고 나갈 때 까지 유지가 되는 스코프, HTTP 요청마다 인스턴스가 생성된다.
- session: HTTP Session과 동일한 생명주기를 가지는 스코프
- application: 서블릿 컨텍스트와 동일한 생명주기를 갖는다.

동시에 여러 HTTP 요청이 오면 정확히 어떤 요청이 남긴 로그인지 구분하기 어렵다.
- 이럴때 request 스코프를 사용한다.

```java
@Scope(value = "request")
@Component
public class MyLogger {

    private String uuid;
    private String requestURL;

    public void setRequestURL(String requestURL) {
        this.requestURL = requestURL;
    }

    public void log(String message) {
        System.out.println("[" + uuid + "]" + "[" + requestURL + "] " + message);
    }

    @PostConstruct
    public void init() {
        uuid = UUID.randomUUID().toString();
        System.out.println("[" + uuid + "] request scope bean create: " + this);
    }

    @PreDestroy
    public void close() {
        System.out.println("[" + uuid + "] request scope bean close: " + this);
    }
}
```
- `@Scope(value = request)` 를 통해 request 스코프로 지정. 
  - HTTP 요청 당 하나씩 생성되고, 끝나는 시점에 소멸된다.
- 빈이 생성되는 시점에, uuid를 생성하기 위해 @PostConstruct를 사용했다.
- requestURL 의 생성시점은 빈을 생성하는 시점에는 알 수없어 setter로 입력받는다.

```java
@Controller
@RequiredArgsConstructor
public class LogDemoController {

    private final LogDemoService logDemoService;
    private final MyLogger myLogger;

    @RequestMapping("/log-demo")
    @ResponseBody
    public String logDemo(HttpServletRequest request) {
        String requestURL = request.getRequestURL().toString();
        myLogger.setRequestURL(requestURL);

        myLogger.log("controller test");
        logDemoService.logic("testID");
        return "OK";
    }
}
```  
- `MyLogger`를 주입받는다.
  - MyLogger는 request 스코프를 갖는 빈이다. HTTP 요청시 생성되고 그 이전에는 생성이 불가능하다.
  - HTTP 요청 이전에는 빈 매칭이 불가능하다.
    - LogDemoController 클래스는 생성자 주입을 통해 빈으로 등록 되려고 하기 때문에, 예외가 발생한다. 
    - 이럴 때, Provider를 사용한다. 

**스코프와 Provider**
```java
@Controller
@RequiredArgsConstructor
public class LogDemoController {

    private final LogDemoService logDemoService;
    private final ObjectProvider<MyLogger> myLoggerProvider;

    @RequestMapping("/log-demo")
    @ResponseBody
    public String logDemo(HttpServletRequest request) {

        MyLogger myLogger = myLoggerProvider.getObject();

        String requestURL = request.getRequestURL().toString();
        myLogger.setRequestURL(requestURL);

        myLogger.log("controller test");
        logDemoService.logic("testID");
        return "OK";
    }
}
```
- request scope를 갖는 빈을 필요로 한다면, ObjectProvider 를 통해 생성하는 시점을 지연시킬 수 있다.
- provider.getObject()를 호출하는 시점에는 Http 요청을 확실히 보장할 수 있다.
즉, request scope는 http 요청이 들어오는 시점에 스프링 컨테이너에 빈이 등록된다.

**스코프와 프록시**
```java
@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
@Component
public class MyLogger {

    private String uuid;
    private String requestURL;

    public void setRequestURL(String requestURL) {
        this.requestURL = requestURL;
    }

    public void log(String message) {
        System.out.println("[" + uuid + "]" + "[" + requestURL + "] " + message);
    }

    @PostConstruct
    public void init() {
        uuid = UUID.randomUUID().toString();
        System.out.println("[" + uuid + "] request scope bean create: " + this);
    }

    @PreDestroy
    public void close() {
        System.out.println("[" + uuid + "] request scope bean close: " + this);
    }
}
```
**가짜 프록시 객체를 통해 Scope 해결**
- `@Scope`의 proxyMode = ScopedProxyMode.TARGET_CLASS를 설정하면 스프링에서 CGLIB라는 바이트 코드를 조작하는 라이브러리를 사용해,
가짜 프록시 객체를 생성한다.
- `@Configuration`과 같이 CGLIB를 사용하여 프록시를 사용한다.
- 메서드를 호출하면 그 때, 내부에서 진짜 빈에 요청을 한다.

**동작**
.