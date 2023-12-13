# 스프링 컨테이너, 스프링 빈

## 1. 스프링 컨테이너 생성
```java
ApplicationContext ac = new AnnotationConfigApplicatinContext(Config.class);
```
- ApplicationContext를 스프링 컨테이너라 한다.
- @Configuration 어노테이션이 붙어 있는 설정 클래스를 활용해 생성된다.
- @Bean이 붙은 메서드를 스프링 빈이라고 한다.
- 빈 이름은 항상 다른 이름을 부여해야한다.

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
