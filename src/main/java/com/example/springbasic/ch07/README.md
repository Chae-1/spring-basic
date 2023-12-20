빈 생명주기 콜백
==
데이터베이스 커넥션 풀이나, 네트워크 소켓처럼 애플리케이션 시작 시점에 필요한 연결을 미리 해두고, 애플리케이션 종료 시점에 연결을 모두 종료하는 
작업을 진행하려면, 객체의 초기화와 종료 작업이 필요하다.
- 애플리케이션 시작 전에 필요 연결을 미리한다.
- 종료 시점에 연결을 모두 종료한다.
```java
public class NetworkClient {
    private String url;

    public NetworkClient() {
        this.url = url;
        connect();
        call("초기화 연결 메시지");
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void connect() {
        System.out.println("connect: " + url);
    }

    public void call(String message) {
        System.out.println("call: " + url + ", message: " + message);
    }

    public void disconnect() {
        System.out.println("close " + url);
    }
}

```
이 객체는 수정자 주입을 통해 url을 전달 받아야 사용이 가능하다.


스프링 빈은 이런 라이프사이클을 가진다.
객체 생성 -> 의존 관계 주입

스프링 빈을 사용하기 위해선, 의존관계 주입이 완료된 상태여야 한다.
스프링은 의존관계 주입이 완료된 시점을 알려주는 다양한 기능을 제공한다.

**스프링 빈의 라이프사이클**
스프링 컨테이너 생성 -> 스프링 빈 생성 -> 의존관계 주입 -> 초기화 콜백 -> 사용 -> 소멸전 콜백 -> 스프링 종료

- 초기화 콜백: 빈이 생성되고, 빈의 의존관계 주입이 완료된 후 호출
  - 생성자 주입을 제외한 수정자 주입, 필드 주입이 일어난다.
- 소멸전 콜백: 빈이 소멸되기 이전 호출

**객체의 생성과 초기화를 분리하자.**
- 생성자는 필수 정보를 받아서 객체를 생성하는 책임을 갖는다.
- 초기화는 외부 커넥션을 연결하는 등 무거운 동작을 수행한다.
그래서 명확하게 나누는 것이 좋다.

**세 가지 방법으로 생명주기 콜백을 지원한다**
- `InitializingBean`, `DisposableBean` 인터페이스
- 설정 정보에 초기화, 종료 메서드를 지정
- @PostConstruct, @PreDestroy



