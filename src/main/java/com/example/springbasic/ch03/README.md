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

1. 스프링의 모든 빈을 찾는 방법
```java
// 1. 스프링에 등록된 모든 빈의 이름을 조회하는 API
String[] beanDefinitionNames = ac.getBeanDefinitionNames();
// 2. BeanDefinition을 조회할 수 있다.
BeanDefinition beanDefinition = ac.getBeanDefinition(beanDefinitionNames[i]);
// 3. 빈 이름으로 조회한 빈 -> 타입을 지정하지 않으면, Object로 조회  
Object bean = ac.getBean(beanDefinitionNames[i]);
```

1. 동일 타입의 빈을 조회하는 방법
```java
// MemberRepository 타입의 모든 빈을 조회
// key는 빈의 이름, value는 실제 인스턴스
Map<String, MemberRepository> map = ac.getBeansOfType(MemberRepository.class);

```

**빈 조회 정리**
1. 해당 타입의 빈이 하나만 존재하면, 부모 타입으로 조회하거나 자식 타입으로 조회해도 문제 없다.
2. 둘 이상 존재한다면,
  - 빈 이름을 지정해야 예외가 발생하지 않는다.
  - 특정 하위 타입으로 조회하면 된다.
  - 모두 조회해서 필요한 빈을 찾는다.


## 스프링 빈 설정 정보 - BeanDefinition
- 스프링은 XML, 코드 등등 다양한 방법으로 빈을 설정할 수 있다. `BeanDefinition` 클래스가 이를 가능하게 한다.
  - `AnnotationConfigApplicationContext` 에서는 Config 클래스에 등록된 `@Bean`을 읽어들여 스프링 빈으로 등록한다.
  - `GenericXmlApplicationContext`는 xml 파일을 읽고 빈을 설정한다.
형식에 상관없이 설정정보를 등록할 수 있게 `BeanDefinition`을 가지고 있다.
- BeanDefinition은 해당 빈의 각종 정보를 가지고 있다.
- 
