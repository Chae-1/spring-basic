스프링 컨테이너, 스프링 빈
======================

## 1. 스프링 컨테이너 생성
```java
ApplicationContext ac = new AnnotationConfigApplicatinContext(Config.class);
```
- ApplicationContext를 스프링 컨테이너라 한다.
- @Configuration 어노테이션이 붙어 있는 설정 클래스를 활용해 생성된다.
- @Bean이 붙은 메서드를 스프링 빈이라고 한다.
- 빈 이름은 항상 다른 이름을 부여해야한다.
  - 기본적으로는 빈 메서드의 이름을 빈의 이름으로 갖는다.
- 설정 정보로 XML, 어노테이션을 활용하여 생성될 수 있다.

```java
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

```
- 빈들을 전부 컨테이너에 등록한 뒤, 의존관계를 주입한다.

## 2. 생성 과정

**1. 생성, 빈 등록**
```java
ApplicationContext ac = new AnnotationConfigApplicatinContext(Config.class);
```
- Config 클래스를 활용하여 빈 정보들을 읽어들인 후 이를 컨테이너에 저장한다.

**2. 의존 관계 매핑**
- 스프링 빈을 전부 생성하고 의존 관계를 주입한다.

## 3. 빈 조회
```java
// 1. 이름이 memberService 이고 타입이 MemberService인 빈을 조회한다.
MemberService memberService = ac.getBean("memberService", MemberService.class);

// 2. 타입이 MemberService인 빈을 조회한다.
MemberService memberService = ac.getBean(MemberService.class);

// 3. 구체 타입으로 조히 가능
MemberServiceImpl memberService = ac.getBean(MemberServiceImpl.class);
```