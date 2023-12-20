컴포넌트 스캔
===
@Bean으로 일일이 등록하지 않고 자동으로 스프링 빈으로 등록하는 기능을 제공한다.
- @Bean으로 등록하는 반복작업이 없어지는 장점이 생긴다

```java
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
)
// @Component 가 붙은 모든 클래스를 빈으로 등록한다.
public class AutoAppConfig {
}

```
- @Component 가 붙은 클래스들을 모두 스프링 빈으로 등록한다.
  - @Configuration 도 내부에 @Component가 존재해서 컴포넌트 스캔의 대상이 된다.
- excludeFilters는 컴포넌트 스캔 대상에서 제외 시키키 위한 속성이다.
- 아무런 설정이 없을 시, 현재 존재하는 패키지부터 하위 패키지까지 스캔한다.
```java
@Component
public class RateDiscountPolicy implements DiscountPolicy {
    // ..
}

```
하지만, 이렇게 되면 스프링 빈들의 의존 관계를 주입할 수 없다.
그래서 @Autowired 가 존재한다.

## 등록 절차
### **1. @ComponentScan**
- @Component가 붙은 모든 클래스를 첫 글자가 소문자인 클래스의 이름으로 등록한다.

### **2. 의존관계 자동 주입**
- 생성자에 @AutoWired를 지정하면 스프링 컨테이너에서 자동으로 빈을 찾아서 매칭시킨다.
- 같은 타입의 빈을 찾아서 우선적으로 매핑시킨다.

## 탐색 범위
- basePackages -> 컴포넌트 스캔 시작 패키지를 지정한다.
- basePackageClasses -> 해당 클래스의 패키지를 시작위치로 지정한다.

탐색 시작 범위는, 결국 패키지를 대상으로 지정한다.

## 필터
- includeFilters: 컴포넌트 스캔 대상을 추가로 지정한다.
  - 즉, `@Component` 가 붙지 않은 클래스도 추가하겠다.
- excludeFilters: 컴포넌트 스캔에서 제외할 대상을 지정한다.
  - 컴포넌트 스캔의 대상이지만, 제외하고 싶은 목록을 지정한다.
- FilterOption
  - ANNOTATION : 기본값, 애노테이션을 인식해 동작.
  - ASSIGNABLE_TYPE : 지정한 타입과 자식 타입을 인식한다.
  - ASPECTJ : AspectJ 패턴 사용
  - REGEX : 정규 표현식

## 중복 등록, 충돌
같은 빈 이름을 등록하면?
1. 자동 빈 등록 vs 자동 빈 등록
   - ConflictingBeanDefinitionException 이 발생한다.
2. 자동 빈 등록 vs 수동 빈 등록
   - 수동 빈이 우선권을 갖는다.
   - 하지만, 의도하지 않게 등록되는 경우가 많기에 사용하지 말자.

