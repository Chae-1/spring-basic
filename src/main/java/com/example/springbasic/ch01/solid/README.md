SOLID 원칙
========
## 객체지향 설계에서 지켜야할 5가지의 원칙

## Single Responsibility Principle
- 한 클래스는 하나의 책임만 가져야 한다.
- 변경이 생길 때, 해당 클래스를 변경할 이유는 한 개 존재해야한다.

## Open Closed Principle
- 확장에는 열려 있으나 변경에는 닫혀 있어야 한다.
- 다형성을 활용해야 이를 가능하게 할 수 있다.
- ### 문제점
  - 다형성을 활용해도 클라이언트 코드를 변경해야한다.
  - 이 문제를 다형성만으로 해결할 수 없다.
  - Spring의 IOC 컨테이너가 이 역할을 수행함.

## Liskov Substitution Principle
- 다형성에서 상위 클래스에서 해야하는 역할을 하위 클래스가 그대로 수행해야한다.
- 즉, 변경이 일어나도 기능 전체에 변경이 없어야한다.

## Interface Segregation Principle
- 특정 클라이언트를 위한 인터페이스 여러 개가 범용 인터페이스 하나보다 낫다.
- 큰 인터페이스를 여러 개의 범용 인터페이스로 분리하자.

## Dependency Inversion Principle
- 추상화에 의존하고, 구체화에 의존하면 안된다.
- 구현 클래스가 아닌, 인터페이스에 의존해야한다.
  - 하지만, 실제 구현하는 부분에서는 구현과 인터페이스 둘다 의존한다.
  
```java
  public class LoginService {
    // 아래와 같이 인터페이스와 실제 구현이 함께 존재한다.
    // 클라이언트 코드를 변경해야 한다.
    // OCP를 준수하지 못한다.
    private MemberRepository memberRepository = new MemoryMemberRepository();
  }
```
## 즉, 다형성만으로 OCP, DIP를 지킬 수 없다.

