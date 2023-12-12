SOLID 원칙
========
## Single Responsibility Principle
- 한 클래스는 하나의 책임만 가져야 한다.

## Open Closed Principle
- 확장에는 열려 있으나 변경에는 닫혀 있어야 한다.
- 다형성을 활용하는 것으로 이를 가능하게 한다.
- ### 문제점
  - 다형성을 활용해도 클라이언트 코드를 변경해야한다.
  - 이 문제를 다형성만으로 해결할 수 없다.
  - Spring의 IOC 컨테이너가 이 역할을 수행함.

## Liskov Substitution Principle
- 다형성에서 상위 클래스에서 해야하는 역할을 하위 클래스가 그대로 수행해야한다.
- 즉, 변경이 일어나도 기능 전체에 변경이 없어야한다.

## Interface Segregation Principle
- 특정 클라이언트를 위한 인터페이스 여러 개가 범용 인터페이스 하나보다 낫다.
- 큰 인터페이스를 여러개의 범용 인터페이스로 분리하자.

## Dependency Inversion Principle
- 추상화에 의존하고, 구체화에 의존하면 안된다.
- 구현 클래스가 아닌, 인터페이스에 의존해야한다.

## 즉, 다형성만으로 OCP, DIP를 지킬 수 없다.
