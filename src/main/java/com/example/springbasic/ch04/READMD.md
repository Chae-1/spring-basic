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
  -  
- 단점
  - 지저분한 코드가 들어간다. 
  - DIP, OCP를 위배하며, 테스트에 용이하지 않다.
  
## 싱글톤 컨테이너
- 스프링 컨테이너는 싱글톤 패턴의 단점을 해결, 객체를 싱글톤으로 관리한다.
- 빈으로 등록한 객체를 전부 싱글톤으로 관리한다.
- 이러한 기능을 싱글톤 레지스트리라고 한다.
