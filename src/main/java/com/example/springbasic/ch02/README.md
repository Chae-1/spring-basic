# 관심사의 분리
기존 코드에서는 클라이언트 코드가 서버 코드를 직접 생성하고 사용한다.

이러한 이유로 DIP, OCP를 위배한다.
```java
public class OrderService {
  private MemberRepository memberRepository = new MemoryMemberRepository();
  //..
}
```
객체를 생성하는 부분과 사용하는 부분을 분리한다면 이 문제를 해결할 수 있다.

객체를 생성하는 책임을 맡는 클래스와 이를 사용하는 클래스로 구분하면 DIP, OCP를 준수할 수 있다.

```java

public class OrderService {
  private MemberRepository memberRepository;

  public OrderService(MemberRepository memberRepository) {
    this.memberRepository = memberRepository;
  }
  //..
}

public class Config {
  public MemberRepository memberRepository() {
    return new MemoryMemberRepository();
  }
  
  // ...
}
```

# IOC, DI, 컨테이너
## 제어의 역전 IOC(Inversion of Control)
- 클라이언트 구현 객체가 스스로 필요한 객체를 생성, 연결, 실행했다.
  - 이것은 자연스러운 흐름이지만, Config Class가 등장한 이후, 구현 객체를 보는 것으로 흐름을 알지 못한다.
- 프로그램 제어 흐름은 Config Class가 관리하고 있다.
- 즉, 클라이언트 코드는 어떤 구현체를 사용하는 지 알 수 없다.
- 제어 흐름을 직접 관리하지 않고 외부에서 관리하는 것을 제어의 역전이라 한다.
- 프레임워크는 제어의 역전의 대표적인 예지만, 라이브러리는 그렇지 않고 직접 실행시켜야 한다.

## 의존관계 주입 DI(Dependency Injection)
- 제어권이 없는 클라이언트 코드는 인터페이스에만 의존하기에, 어느 객체가 런타임에 실행되는지 알 수 없다.
- 그렇기에, 의존 관게는 런타임, 컴파일 타임 의존 관계를 분리해서 생각해야 한다.
  - 컴파일 타임 의존관계는 import 문을 보고 쉽게 판단 할 수 있지만, 런타임 타임 의존관계는 실행시점에 결정된다.
- 실행 시점에 외부에서 실제 구현 객체를 생성하여 클라이언트로 전달하여 의존관계가 연결 되는 것을 DI라 한다.

## IOC 컨테이너, DI 컨테이너
- Config Class 처럼 객체를 관리하면서 의존관계를 연결해주는 것 IOC 컨테이너라고 한다.

