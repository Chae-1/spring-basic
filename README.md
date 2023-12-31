스프링 기본편
======================
## 좋은 객체지향 프로그래밍
- 객체들이 서로 메세지를 주고 받으며, 데이터를 처리해야한다.
- 객체들의 모임으로 파악하고자 한다.
  - 절차지향에서 명령어들의 모임으로 파악하는 것이 아닌, 객체들의 협력관계로 파악.
- 프로그램을 유연하고, 변경에 용이하게 만든다.
  - 변경에 용이하다는 것은, 기존 컴포넌트를 쉽게 변경이 가능하다는 것을 의미.

## 다형성
- 역할과 구현을 구분하여 쉽게 구현을 바꿀 수 있다.
  - 자바에서는, 인터페이스를 활용해 역할을 만들어내고, 이를 구현한 클래스를 통해 구현을 만들 수 있다.

### 다형성의 장점
- 다형성을 활용하면, 유연해지고, 변경이 편리
  - 실행 시점에 구현 객체를 유연하게 변경할 수 있다.
- 클라이언트를 변경하지 않고, 서버의 구현 기능을 유연하게 변경가능하다.

## SOLID