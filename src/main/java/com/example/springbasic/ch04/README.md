# 싱글톤 컨테이너
- 웹 어플리케이션에서는 보통 여러 고객이 동시 요청을 한다.
  - 기존 Config Class는 요청마다 새로운 객체를 생성한다.
  - 메모리 초과로 인해 서버가 죽을 수 있다.
- 그렇기에 이런 객체들을 딱 1개만 생성하게 하도록 설계하면 된다.

## 싱글톤 패턴
- 클래스의 인스턴스가 딱 1개가 생성되는 것을 보장한다.
- 그래서 객체 인스턴스를 2개 이상 생성하지 못하도록 막아야 한다.
  - private 생성자를 사용하여 외부에서 new 키워드를 사용하지 못하게 한다.
```java
public class SingleTonObject {
    private static SingleTonObject instance = new SingleTonObject();
    
    private SingleTonObject() {
    }
    
    public static SingleTonObject getInstance() {
        return instance;
    }
    
    // ...
}
```
1. static 영역에 미리 객체를 하나 생성한다.
2. 외부에서 객체를 생성하지 못하도록 기본 생성자를 private 으로 지정한다.
3. 해당 객체를 외부에서 사용할 수 있도록 getter를 만든다.

- 장점
  - 요청마다 객체가 생성되지 않으니, 효율적으로 메모리를 사용가능 
- 단점
  - 지저분한 코드가 들어간다. 
  - DIP, OCP를 위배하며, 테스트에 용이하지 않다.
  
## 싱글톤 컨테이너
- 스프링 컨테이너는 싱글톤 패턴의 단점을 해결, 객체를 싱글톤으로 관리한다.
- 빈으로 등록한 객체를 전부 싱글톤으로 관리한다.
- 이러한 기능을 싱글톤 레지스트리라고 한다.
- 모든 빈들이 싱글톤 으로 관리되는 것은 아니다.

## 싱글톤 방식의 주의점
싱글톤은 객체를 하나만 생성하여 여러 클라이언트가 동시에 사용한다.
- 여러 사용자가 동시에 상태를 변경할 수 있다.
  - stateless로 설계를 해야한다.
- 싱글톤 객체의 모든 필드가 공유되는 점으로 인해, 항상 조심해야한다.

## @Configuration
@Configuration 구성 객체에서 의존관계를 주입할 때, 새로운 객체를 생성하는 것 처럼 보인다.
```java
@Configuration
public class AppConfig {

        @Bean
        public MemberRepository memberRepository() {
            return new MemoryMemberRepository();
        }

        @Bean
        public MemberService memberService() {
            return new MemberServiceImpl(memberRepository());
        }

        @Bean
        public DiscountPolicy discountPolicy() {
            return new RateDiscountPolicy();
        }

        @Bean
        public OrderService orderService() {
            return new OrderServiceImpl(memberRepository(), discountPolicy());
        }   
}
``` 
- memberRepository()가 2번 호출된다. 객체가 2개 생성되는 것 처럼 보이지만 실제로 그렇지 않다.
- 결국 @Configuration 의 모든 빈 메서드들은 한번 호출된다.

## 바이트코드 조작
스프링 컨테이너는 싱글톤 레지스트리이기에, 빈들을 모두 싱글톤으로 생성해야한다.
- 컨테이너에 의해 등록된 빈들은 원래 객체가 아니다. SpringCGLIB라는 이름이 붙는다.
- CGLIB 라이브러리를 가지고 나의 코드를 등록한 것이다.
  - 빈이 이미 등록 되있다면 등록된 빈을 아니라면, 기존 코드를 호출하고 빈으로 등록한다.
- @Configuration 이 없다면, 순수 자바 코드가 작동하여 의존 객체의 싱글톤이 꺠진다.