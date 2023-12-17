의존관계 자동 주입
==
## 의존 관계 주입
1. 생성자 주입
```java
@Component
public class OrderServiceImpl implements OrderService {

    private MemberRepository memberRepository;
    private DiscountPolicy discountPolicy;

    @Autowired
    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }
    
    //..
}
```
- 생성자를 통해서 의존 관계를 주입 받는 방법
  - 단 한번 호출되는 것을 보장한다. 
  - 그래서, 불변, 필수 의존 관계에 사용한다.
- 생성자가 하나라면, @Autowired 생략 
2. Setter 주입

```java

import org.springframework.beans.factory.annotation.Autowired;

@Component
public class OrderServiceImpl implements OrderService {

  private MemberRepository memberRepository;
  private DiscountPolicy discountPolicy;

  @Autowired
  public void setDiscountPolicy(DiscountPolicy discountPolicy) {
    this.discountPolicy = discountPolicy;
  }

  @Autowired
  public void setMemberRepository(MemberRepository memberRepository) {
    this.memberRepository = memberRepository;
  }
}
```
- 주입을 선택적으로 할 수 있다.
   - 주입이 필수가 아니라면 @Autowired(required = false) 로 지정
3. 필드 주입
```java
@Component
public class OrderServiceImpl implements OrderService {

    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private DiscountPolicy discountPolicy;
    
}
```
- 순수 자바 코드로 테스트를 못하게 된다.
- 컨테이너가 존재하지 않는다면, 아무것도 하지 못한다.
- 어플리케이션 코드에는 절대 사용하지 않는다.
- 테스트 코드에서는 사용해도 상관없다.

4. 일반 메서드 주입
- 아무런 메서드에 @Autowired 적용 가능

## 옵션 처리
주입할 스프링 빈이 없어도 동작해야 할 때도 있다.
자동 주입 대상을 옵션으로 처리하는 방법
- @Autowired(required = false) 주입 대상이 없으면 호출 안됨.
- org.springframework.lang.@Nullable 주입 대상이 없으면 null
- Optional<> : 주입 대상이 없으면, Optional.empty

## 생성자 주입
- 필드 주입은 당연하게 사용되면 안된다.
- setter, 일반 메서드는 객체를 테스트를 할 때, 힘들어질 수 있다.
- 그리고 final 키워드를 사용할 수 있다.
  - 이는, 주입할 데이터를 누락 했을 때, 오류를 찾기가 쉽게 해준다.
- 그래서, 생성자 주입을 사용하는 것이 좋다.


## 조회할 빈이 2개 이상이면?
- NoUniqueBeanDefinitionException이 당연히 발생한다.
  - 수동으로 빈을 등록하는 방법이 존재한다.
  - 이를 자동 빈 등록으로 해결하는 방법이 존재한다.


## @Qualifier, @Primary, @AutoWire("fieldName")
1. @Autowired 필드명 매칭
```java
@Component
public class FixDiscountPolicy implements DiscountPolicy {
}

@Component
public class RateDiscountPolicy implements DiscountPolicy {
}

@Component
public class OrderServiceImpl implements OrderService {

    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;
    // rateDiscountPolicy가 주입된다.
    
    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy rateDiscountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = rateDiscountPolicy;
    }
}

```
- 여러 개가 매칭될 때, 스프링 빈 이름으로 주입된다.
  - 타입 매칭 이후 
  - 두 개 이상 이면,파라미터 이름으로 매칭


2. @Qualifier
스프링 빈에 추가 구분자를 붙여주는 역할을 한다.
- 주입 시, @Qualifier를 붙여주는 것으로 구분한다.

```java
import org.springframework.beans.factory.annotation.Qualifier;

@Component
@Qualifier("mainDiscountPolicy")
public class RateDiscountPolicy implements DiscountPolicy {
}

@Component
@Qualifier("fixDiscountPolicy")
public class FixDiscountPolicy implements DiscountPolicy {
}

@Component
public class OrderServiceImpl implements OrderService {

  private final MemberRepository memberRepository;
  private final DiscountPolicy discountPolicy;
  // rateDiscountPolicy가 주입된다.

  public OrderServiceImpl(MemberRepository memberRepository,
                          @Qualifier("mainDiscountPolicy") DiscountPolicy discountPolicy) {
    this.memberRepository = memberRepository;
    this.discountPolicy = rateDiscountPolicy;
  }
}


```
- @Qualifier 로 매칭되는 빈을 주입한다.
- 위 코드에서는 RateDiscountPolicy가 주입 된다.
  - Qualifier를 찾는 용도로만 사용하자.
- Qualifier로 찾은 후 없다면, 빈 이름으로 매칭하고 없으면, 예외 발생한다.

3. @Primary
매칭되는 빈의 우선순위를 지정하는 어노테이션
@Primary가 붙은 빈을 우선적으로 매칭한다.

- @Primary 보다 @Qualifier 가 우선적으로 적용된다.
