# 테스트 주도 개발 시작하기

---

## To Do
- [X] Chapter 1. TDD 개발 준비
- [X] Chapter 2. TDD 시작
- [ ] Chapter 3. 테스트 코드 작성 순서
- [ ] Chapter 4. TDD • 기능 명세 • 설계
- [ ] Chapter 5. Junit 5 기초
- [ ] Chapter 6. 테스트 코드의 구성
- [ ] Chapter 7. 대역
- [ ] Chapter 8. 테스트 가능한 설계
- [ ] Chapter 9. 테스트 범위와 종류
- [ ] Chapter 10. 테스트 코드와 유지 보수
- [ ] Chapter 11. 마치며
- [ ] 부록 A. Junit 5 추가 내용
- [ ] 부록 B. Junit 4 기초
- [ ] 부록 C. Mockito 기초 사용법
- [ ] 부록 D. AssertJ 소개

---

## Chapter 2. TDD 시작

암호 강도 측정 기능을 TDD로 구현

1. 모든 규칙을 충족하는 암호 강도는 '강함'
2. 길이만 8글자 미만이고 나머지 규칙은 충족하는 암호의 강도는 '보통'
3. 숫자를 포함하지 않고 나머지 규칙은 충족하는 암호의 강도는 '보통'
4. 값이 없는 암호의 강도는 '유효하지 않음'
5. 대문자를 포함하지 않고 나머지 규칙은 충족하는 경우
6. 길이가 8글자 이상인 규칙만 충족하는 경우
7. 숫자 포함 규칙만 충족하는 경우
8. 대문자 포함 규칙만 충족하는 경우
9. 아무 규칙도 충족하지 않는 경우

### TDD 흐름
TDD는 테스트를 먼저 작성하고 테스트 통과시킬 만큼 코드를 작성하고 리팩토링으로 마무리 하는 과정을 반복한다

> 레드-그린-리팩터
TDD 사이클을 레드(Red)-그린(Green)-리팩터(Refactor)로 부르기도 함  
여기서 레드는 실패를 의미. 레드는 테스트 코드가 실패하면 빨간색을 이용해서 실패한 테스트를 보여주는 데서 비롯함.  
비슷하게 그린은 성공한 테스트를 의미. 즉 코드를 구현해서 실패하는 테스트를 통과시키는 것을 뜻함.  
마지막으로 리팩터는 이름 그대로 리팩토링 과정을 의미함.

### 테스트가 개발을 주도
테스트 코드를 먼저 작성하면 테스트가 개발을 주도하게 된다  

- 가장 먼저 통과해야할 테스트 작성
- 테스트를 작성하는 과정에서 구현을 생각하지 않음
- 해당 기능이 올바르게 동작하는지 검증할 수 있는 테스트 코드 작성
- 테스트 추가 후 테스트를 통과시킬 만큼 기능을 구현
- 아직 추가하지 않은 테스트를 고려해서 구현하지 않음
- 테스트 코드를 만들면 다음 개발 범위가 정해짐
- 테스트 코드가 추가되면서 검증하는 범위가 넓어질수록 구현도 점점 완성됨

### 지속적인 코드 정리
구현을 완료한 뒤에는 리팩토링을 진행

- 리팩토링할 대상이 눈에 들어오면 리팩토링을 진행해서 코드를 정리
- 당장 리팩토링할 대상이나 어떻게 리팩토링해야 할지 생각나지 않으면 다음 테스트 진행
- 테스트 코드 자체도 리팩토링 대상
- TDD는 개발 과정에서 지속적으로 크드를 정리하므로 코드 품질이 급격히 나빠지지 않게 막아주는 효과가 있음
- 이는 향후 유지보수 비용을 낮추는데 기여함

### 빠른 피드백
TDD가 주는 이점은 코드 수정에 대햔 피드백이 빠르다는 점이다

- 새로운 코드를 추가하거나 기존 코드를 수정하면 테스트를 돌려서 해당 코드가 올바른지 바로 확인할 수 있음
- 이는 잘못된 코드가 배포되는 것을 방지함

---

## Chapter 3. 테스트 코드 작성 순서

### 초반에 복잡한 테스트부터 시작하면 안 되는 이유
만약 초반 부터 다양한 조합을 검사하는 복잡한 상황을 테스트로 추가하면 해당 테스트를 통과 시키기 위해 한 번에 구현해야 할 코드가 많아진다

한 번에 완벽한 코드를 만들면 좋겠지만, 모두가 수퍼 개발자인 것은 아니다  
보통의 개발자는 한 번에 많은 코드를 만들다 보면 나도 모르게 버그를 만들고 나중에 버그를 잡기 위해 많은 시간을 허비하게 된다  
당연히 테스트 통과 시간도 길어진다  
그뿐만 아니라 코드 작성 시간이 길어지면 집중력도 떨어져서 흐름이 자주 끊기게 된다

### 구현하기 쉬운 테스트부터 시작하기
가장 구현하기 쉬운 경우부터 시작하면 빠르게 테스트를 통과시킬 수 있다  
보통 수 분에서 십여 분 이내에 구현을 완료해서 테스트를 통과시킬 수 있을 만큼 쉬운 것을 선택한다  
이를 통해 점진적으로 구현을 완성해 나갈 수 있다

한 번에 구현하는 시간이 짧아지면 디버깅할 때에 유리하다

### 예외 상황을 먼저 테스트해야 하는 이유
다양한 예외 상황은 복잡한 if-else 블록을 동반할 때가 많다  
예외 상황을 전혀 고려하지 않은 코드에 예외 상황을 반영하려면 코드의 구조를 뒤집거나 코드 중간에 예외 상황을 처리하기 위해 조건문을 중복해서 추가하는 일이 벌어진다  
이는 코드를 복잡하게 만들어 버그 발생 가능성을 높인다  

TDD를 하는 동안 예외 상황을 찾고 테스트에 반영하면 예외 상황을 처리하지 않아 발생하는 버그도 줄여준다  
예외 상황을 찾고 테스트하면 이런 문제가 발생할 가능성을 사전에 낮출 수 있다  

### 완급 조절
처음 TDD로 구현할 때 어려운 것 중 하나는 한 번에 얼마만큼의 코드를 작성할 것인가이다  
TDD를 처음 접할 때는 다음 단계에 따라 TDD를 익혀보자  

1. 정해진 값을 리턴
2. 값 비교를 이용해서 정해진 값을 리턴
3. 다양한 테스트를 추가하면서 구현을 일반화

TDD가 익숙해지면 상황에 따라 구현 속도를 조절할 수 있게 된다  
단순 덧셈이나 길이 비교와 같은 명백한 구현은 상수를 사용하지 않고 바로 구현하고 한 번에 구현을 시도했는데 잘 안되면 한발 물러서서 천천히 단계를 밟아 나가면 된다  

### 지속적인 리팩토링
테스트를 통과한 뒤에는 리팩토링을 진행한다  
매번 리팩토링을 진행해야 하는 것은 아니지만 적당한 후보가 보이면 리팩토링을 진행한다  
코드의 중복은 대표적인 리팩토링 대상이다  
코드가 길어지면 메서드 추출과 같은 기법을 사용해서 메서드 이름으로 코드의 의미를 표현할 수 있다  

TDD를 진행하는 과정에서 지속적으로 리팩토링을 진행하면 코드 가독성이 높아진다  
이는 코드 변경의 어려움을 줄여주어 향후 유지보수에 도움이 된다

> 일단 동작하는 코드를 만드는 능력은 중요하다. 코드가 동작하지 않으면 아무것도 소용없기 때문이다.  
> 하지만 소프트웨어의 생존 시간이 길어질수록 소프트웨어를 지속적으로 개선해야 한다.  
> 즉 코드를 변경해야 한다. 코드 변경이 어려우면 변화하는 요구를 제때 반영할 수 없게 되며 이는 소프트웨어의 생존과 직결된다.  
> 따라서 코드를 잘 변경할 수 있는 능력 또한 매우 중요하다  
> 
> 코드를 잘 변경하려면 변경하기 쉬운 구조를 가져야 하는데 이를 위한 것이 바로 리팩토링이다  
> 리팩토링을 통해 이해하고 변경하기 쉽게 코드를 개선함으로써 변화하는 요구 사항을 적은 비용으로 반영할 수 있다  
> 이는 소프트웨어의 생존 시간을 늘려준다

**테스트 대상 코드의 리팩토링 시점**
테스트 대상 코드에서 상수를 변수로 바꾸거나 변수 이름을 변경하는 것과 같은 작은 리팩토링은 발견하면 바로 실행한다.  
반면에 메서드 추출과 같이 메서드 구조에 영향을 주는 리팩토링은 큰 틀에서 구현 흐름이 눈에 들어오기 시작한 뒤에 진행한다.  

구현 초기에는 아직 구현의 전반적인 흐름을 모르기 때문에 메서드 추출과 같은 리팩토링을 진행하면 코드 구조를 잘못 잡을 가능성이 있다  
코드 구조가 잘못되면 다음 테스트를 통과시키는 과정에서 코드가 복잡해지고 구현을 더는 진행하지 못하고 막힐 수 있다.  
이런 상황이 오면 구현을 멈추고 메서드 추출 리팩토링을 되돌려야한다.  
리팩토링을 취소해서 코드를 원상 복구한 뒤에 다음 테스트를 진행한다.   
그런 뒤 코드의 의미가 구조가 더 명확해지면 그때 다시 리팩토링한다.  

### 테스트할 목록 정리하기
TDD를 시작할 때 테스트할 목록을 미리 정리하면 좋다  
예를 들어 만료일 계산 기능을 구현할 때에는 다음과 같이 테스트할 내용을 목록을 정리할 수 있다  

- 1만 원 납부하면 한 달 뒤가 만료일
- 달의 마지막 날에 납부하면 다음달 마지막 날이 만료일
- 2만 원 납부하면 2개월 뒤가 만료일
- 3만 원 납부하면 3개월 뒤가 만료일
- 10만 원을 납부하면 1년 뒤가 만료일

어떤 테스트가 구현이 쉬울지, 예외적인지 상상해본다  
구현의 난이도나 구조를 검토하면 다음 테스트를 선택할 때 도움이 된다  

테스트 과정에서 새로운 테스트 사례를 발견하면 목록에 추가  
처음부터 모든 사례를 정리하려면 시간도 오래 걸리고 쉽지 않음  

Jira or Trello 같은 시스템을 사용하면 해당 테스트 사례를 하위 작업으로 등록해서 테스트 통과 여부를 추적할 수 있음  
새로 발견한 테스트 사례를 실패하는 테스트로 등록  

테스트 목록을 적었다고 테스트를 한 번에 다 작성하면 안됨  
한번에 작성한 테스트코드가 많으면 구현 초기에도 리팩토링이 어려움  
수정할 코드가 많을수록 리팩토링에 대한 심리적 저항이 생김  

모든 테스트를 통과시키기 전까지는 계속해서 깨지는 테스트가 존재하므로 개발 리듬을 유지하는 데 도움이 안됨  
하나의 테스트 코드를 만들고 통과시키고, 리팩토링하는 과정은 비교적 짧은 리듬을 반복한다  
다루는 범위가 작고 개발 주기도 짧으므로 개발 집중력도 높아진다  

TDD는 리팩토링을 통해 지속해서 코드를 정리하는데 개발을 진행하다 보면 변경 범위가 매우큰 리팩토링 거리를 발견할 때도 있다  
범위가 큰 리팩토링은 시간이 오래 걸리므로 TDD 흐름을 깨기 쉽다  
이때는 리팩토링을 진행하지 말고 테스트를 통과시키는 데 집중한다  
대신 범위가 큰 리팩토링은 다음 할 일 목록에 추가해서 놓치지 않고 진행할 수 있게 한다  

> 리팩토링 범위가 크면 리팩토링에 실패할 수도 있다. 그러니 범위가 큰 리팩토링을 진행하기 전에는 코드를 커밋하는 것을 잊지 말자.  
> 또는 별도 브랜치에서 리팩토링을 진행한다. GIT과 같은 버전 관리 시스템을 사용하지 않는다면 어딘가에 원본이라도 복사해 두고 리팩토링을 진행한다.  
> 그래야 큰 범위의 리팩토링이 실패했을 때 다시 동작하는 마지막 상태로 쉽게 돌아올 수 있다  

### 시작이 안 될 때는 단언부터 고민
테스트 코드를 작성하다 보면 시작이 잘 안 될 때가 있음  
이럴 땐 검증하는 코드부터 작성하기 시작하면 도움이 된다  
예를 들어 만료일 계산 기능의 경우 만료일을 검증하는 코드부터 작성해 보는 것이다  

```
@Test
void 만원_납부하면_한달_뒤가_만료일이_됨() {
    // 처음 작성하는 코드
    assertEquals(기대하는만료일, 실제만료일);
}
```

먼저 만료일을 어떻게 표현할지 결정. 만료일이므로 날짜를 표현하는 타입을 선택하면 좋을 것 같음.  
자바 8 버전에는 LocalDate가 있으니 이 타입을 사용해서 값을 표현해보자.

```
assertEquals(LocalDate.of(2019,8,9), 실제만료일);
```

다음은 실제 만료일을 바꿀 차례. 이 값은 만료일을 실제로 계산한 결과값을 갖는 변수로 바꿀 수 있다.

```
LocalDate realExpiryDate = 계산하기
assertEquals(LocalDate.of(2019,8,9), realExpiryDate);
```

이제 realExpiryDate 변수를 구하는 코드를 작성할 차례. 만료일을 게산하는 기능이 필요하므로 다음과 같이 코드를 작성.

```
LocalDate realExpiryDate = cal.calculateExpiryDate(파라미터);
assertEquals(LocalDate.of(2019,8,9), realExpiryDate);
```

cal의 정확한 타입은 모르지만 어떤 객체의 메서드를 실행해서 계산 기능을 실행하도록 함.  
이제 두 가지를 정해야 함. cal의 타입과 파라미터 타입. 만료일을 계산하는데 납부일과 납부액이 있어야 만료일을 계산할 수 있으므로 파라미터에는 이 두 값을 전달한다.  
만원을 납부했을 때 한 달 뒤가 만료일이 되는지를 테스트 할 것이므로 납부일로 2019-07-09를 전달하고 납부액으로 10,000원을 전달하게 코드를 수정한다  

```
LocalDate realExpiryDate = cal.calculateExpiryDate(LocalDate.of(2019,7,9),10_000);
assertEquals(LocalDate.of(2019,8,9), realExpiryDate);
```

cal의 타입은 간단한 만료일 계산을 뜻하는 ExpiryDateCalculator로 정함. 이제 위 코드는 다음과 같이 바뀜.

```
ExpiryDateCalculator cal = new ExpiryDateCalculator();
LocalDate realExpiryDate = cal.calculateExpiryDate(LocalDate.of(2019,7,9),10_000);
assertEquals(LocalDate.of(2019,8,9), realExpiryDate);
```

이렇게 테스트 코드를 어떻게 작성할지 감을 못 잡겠다면 검증 코드부터 시작해보자. 테스트 코드를 작성할 때 많은 도움이 될 것이다.

### 구현이 막히면
TDD를 진행하다 보면 구현이 막힐 때가 있다. 어떻게 해야 할지 생각이 잘 나지 않거나 무언가 잘못한 것 같은 느낌이 들 것이다.  
이럴 땐 과감하게 코드를 지우고 미련 없이 다시 시작한다.  
어떤 순서로 테스트 코드를 작성했는지 돌이켜보고 순서를 바꿔서 다시 진행한다.  다시 진행할 때에는 다음을 상기한다.

- 쉬운 테스트, 예외적인 테스트
- 완급 조절

---

## Chapter 4. TDD • 기능 명세 • 설계

### 기능 명세

개발자는 코드를 작성하고 빌드하여 이를 상요자가 사용할 수 있게 배포함  
사용자는 배포한 소프트웨어를 이용해서 원하는 기능을 실행함  
우리가 코드를 작성하는 이유는 사용자가 사용할 어떤 기능을 제공하기 위함  

- 기능에 대한 명세는 다양한 형태로 존재함  
  - 파워포인트를 이용한 스토리보드 형태
  - 이메일과 같은 간단한 문장형태
  - 지라와 같은 이슈를 통해 추가할 기능의 명세를 받기도 함
  - 회의 자리에서 기능 명세를 구두로 전달받을 때도 있음
- 사용자에게 제공할 기능을 구현하려면 기능을 크게 두 가지로 나누어 생각해 볼 수 있음
  - 입력과 결과
  - 예: 로그인 기능
    - 입력: 아이디와 암호
      - 기능을 실행하는데 필요한 값
    - 결과: 아이디와 암호가 일치하면 성공
      - 로그인 기능의 결과는 성공 또는 실패
  - 예: 만료일 계산 기능
    ```mermaid
    stateDiagram-v2
        A: "회원 가입 기능"
        B: "DuplicateIdException 발생"
        C: "회원 일련 번호 리턴</br>회원 정보 DB 저장"
        direction LR
        [*] --> A: 아이디, 암호, 이름, 이메일
        A --> B: 동일 ID 존재
        A --> C: 동일 ID 없음
         
    ```
    - 입력: 첫 납부일, 납부일, 납부액
      - 보통 메서드의 파라미터로 전달
      ```
      // payData 인자: 만료일 계산 기능의 입력
      // 리턴값: 만료일 계산 기능의 결과
      LocalDate realExpiryDate = cal.calculateExpiryDate(payData);
      ```
    - 결과: 만료일
      - 결과 형식은 리턴값
      - 익셉션을 결과로 사용할 수도 있음
      ```
      public void login(String id, String pw) {
        User user = getUser(id);
        if (!user.matchPassword(pw)) {
            // 익셉션을 결과로 사용
            throw new IdPwNotMatchException();
        }
        ...;
      }
      ```
  - 기능 실행 결과에는 변경도 포함됨
    - 회원 가입 기능은 실행 결과로 DB에 회원 정보를 추가함
    - 상황에 따라 다른 결과를 가짐
      - ID가 존재하면 결과로 익셉션 발생
      - 동일한 ID가 존재하지 않아서 가입에 성공하면 결과로 회원 일련번호를 리턴 후 DB에 저장

- 설계는 기능 명세로부터 시작
  - 스토리보드를 포함한 다양한 형태의 요구사항 문서를 이용해서 기능 명세를 구체화
  - 기능 명세를 구체화하는 동안 입력과 결과를 도출하고 이렇게 도출한 기능 명세를 코드에 반영
  - 기능 명세의 입력과 결과를 코드에 반영하는 과정에서 기능의 이름, 파라미터, 리턴 타입 등이 결정됨
  - 이는 곧 기능에 대한 설계 과정과 연결됨

### 설계 과정을 지원하는 TDD
TDD는 테스트를 만드는 것부터 시작한다  
테스트 코드를 먼저 만들고 테스트를 통과시키기 위해 코드를 구현하고 리팩토링하는 과정을 반복함  

- 테스트 코드를 먼저 만들기 위해 필요한 것
  - 테스트할 기능
    - 테스트에서 실행할 수 있는 객체나 함수가 존재해야 함
  - 실행 결과를 검증
    - 테스트 대상이 되는 클래스와 메서드가 필요
    - 메서드를 실행할 때 사용할 인자와 타입과 개수를 결정
    ```mermaid
    flowchart LR
      A(테스트를 만들려면?)--> B(테스트할 기능 실행) & C[결과를 검증]
      B-->D(클래스, 메서드, 함수 이름) & E(파라미터)
      C-->F(리턴 값)
    ```
    - 예: 암호 강도 검사
      - 테스트 대상 클래스 이름: PasswordStrengthMeter
      ```
      // 이름을 선택한 뒤에 해당 이름을 사용한 테스트 클래스 작성
      public class PasswordStrengthMeterTest {
      }
      ```
      - 호출할 메서드: meter
      ```
      PasswordStrengthMeter meter = new PasswordStrenghMeter();
      // 테스트 코드에서 실행할 메서드의 이름과 파라미터를 결정
      값타입 결과 = meter.meter("ab12!@AB");
      ```
      - 실행 결과 검증
        - meter() 메서드 리턴 값을 이용해서 검증하므로 리턴 타입 고민
        - 암호 강도를 표현하기로 결정, 열거 타입을 이용해 검증
        ```
        // 결과를 검증하는 방법을 고민하는 과정에서 리턴 타입을 결정
        PasswordStrength result = meter.meter("ab12!@AB");
        assertEquals(PasswordStrength.STRONG, result);
        ```
      - 만료일 계산 기능을 의미하는 이름을 사용해서 클래스와 메서드 이름 선정
        - 필요한 입력 전달을 위해 파라미터 결정
        - 계산된 만료일을 검증하기 위해 리턴 타입 결정
      - 아래의 네가지를 결정
        - 클래스 이름
        - 메서드 이름
        - 메서드 파라미터
        - 실행 결과

TDD 자체가 설계는 아니지만, TDD를 하다 보면 테스트 코드를 작성하는 과정에서 일부 설계를 진행하게 됨

> 이름은 설계에서 매우 중요하다. 설계 과정에서 구현하는 기능을 정확하게 표현하는 이름을 사용하는 것만큼 중요한 것은 없다.  
> 잘못 지은 이름은 두고두고 개발자를 속인다.  
> 레거시 코드를 보면 이름에서 기대하는 것과 다르게 동작하는 코드를 종종 만나게 된다.  
> 이런 이름은 개발자를 속일 뿐만 아니라 코드를 분석하는 시간을 증가시켜 코드 수정을 만드는 원인이 된다.  
> 
> 테스트 코드는 시작부터 이름을 고민하게 만든다. 이 순간은 그래서 중요하다.  
> 시간이 다소 걸리더라도 알맞은 이름을 찾아야 한다. 이름을 고민하는 시간을 아까워하지 말자.


### 필요한 만큼 설계하기
TDD는 테스트를 통과할 만큼만 코드를 작성한다. 필요할 것으로 예측해서 미리 코드를 만들지 않는다.  
이는 설계에도 동일하게 적용된다. 필요할 것으로 예측해서 미리 설계를 유연하게 만들지 않는다.  
실제 테스트 사례를 추가하고 통과시키는 과정에서 필요한 만큼 설계를 변경한다.  

- 예: 만료일 계산
  ```
  // 최초 설계는 두 개의 파라미터를 사용
  LocalDate expiryDate = cal.calculateExpiryDate(billingDate, payAmount);
  
  // 테스트를 진행하는 과정에서 필요한 만큼 설계 변경
  PayDate payDate = PayDate.builder()
    .firstBillingDate(LocalDate.of(2019,1,31))
    .billingDate(LocalDate.of(2019,2,28))
    .payAmount(10_000)
    .build();
  LocalDate expiryDate = cal.calculateExpiryDate(payDate);
  ```
  - 테스트를 진행하는 과정에서 실제 익셉션이 필요한 시점에 익셉션을 도출
  - TDD로 개발하는 코드 비율이 높아질수록 지금 시점에서 필요한 설계만 코드에 반영할 가능성이 커짐
  - 유연한 설계는 필요한 시점에 추가
  - 이를 통해 설계가 불필요하게 복잡해지는 것을 방지
  - 요구사항을 분석하는 과정에서 당연히 설계를 진행
  - TDD는 미리 앞서서 코드를 만들지 않으므로 불필요한 구성 요소를 덜 만들게 됨

### 기능 명세 구체화
테스트 코드를 작성하기 위해 개발자는 기능 명세를 정리해야 함.  
보통 개발자는 기획자가 작성한 스토리보드나 와이어프레임과 같은 형태료 요구사항 명세를 전달받는다.  
이런 문서는 사용자나 기획자가 보기에는 적당할지 모르나 개발자가 기능을 구현하기에는 생략된 내용이 많다.  

- 예: 만료일 계산 기능 요구사항
  - 서비스를 사용하려면 매달 1만 원을 선불로 납부한다. 납부일 기준으로 한 달 뒤가 서비스 만료일이 된다.
  - 2개월 이상 요금을 납부할 수 있다.
  - 10만 원을 납부하면 서비스를 1년 제공한다.

테스트 작성을 위해 개발자는 한 달 뒤에 대한 정확한 예가 필요.  
이를 위해 개발자는 다음과 유사한 대화를 통해 구체적인 예를 찾게 됨.  

```
@Test
void 납부일과_한달_뒤_일자가_같지_않음() {
  assertExpiryDate(
        LocalDate.of(2019,1,31),10_000,
        LocalDate.of(2019,2,28));
  assertExpiryDate(
        LocalDate.of(2019,5,31),10_000,
        LocalDate.of(2019,6,30));
  assertExpiryDate(
        LocalDate.of(2019,1,31),10_000,
        LocalDate.of(2019,2,29));
}
```

테스트 사례를 추가하는 과정에서 첫 납부일에 따라 만료일이 다른 상황을 구체화함

```
@Test
void 첫_납부일과_만료일_일자가_다를때_이만원_이상_납부() {
  assertExpiryDate(
            PayData.builder()
                .firstBillingDate(LocalDate.of(2019,1,31))
                .billingDate(LocalDate.of(2019,2,28))
                .payAmount(20_000)
                .build(),
            LocalDate.of(2019,4,30));
}
```

모호한 상황을 만나면 이를 구체적인 예로 바꾸어 테스트 코드에 반영.  
즉 테스트 코드는 예를 이용한 구체적인 명세가 됨.  
구체적인 예는 개발자가 요구사항을 더 잘 이해할 수 있게 만든다.  
구체적인 예는 모호함을 없애 주어 개발자가 올바르게 동작하는 기능을 만들 수 있게 한다.  

테스트 코드는 바로 실행할 수 있다. 테스트 코드를 이용하면 구체적인 예를 이용해서 기능을 바로 실행해 볼 수 있음.  
이는 유지보수에 큰 도움이 된다. 특정 상황에서 코드가 어떻게 동작하는지 이해하고 싶다면 해당 상황을 검증하는 테스트를 실행하고 이해가 필요한 코드를 추적하면 된다.  
디버거를 이용해서 추적할 수 있고 콘솔에 출력되는 로그를 보면서 이해를 높일 수 있다

> 복잡한 로직을 구현해야 하는 것은 결국 개발자이므로 개발자는 최대한 예외적인 상황이나 복잡한 상황에 해당하는 구체적인 예를 끄집어내야 한다.  
> 이를 위한 가장 좋은 방법은 담당자와 대화를 하는 것이다. 대화 과정이 쉽지 않을 때도 있지만 대화를 하지 않으면 올바르게 원하는 결과물을 개발하지 못한다.

---

## Chapter 5. Junit 5 기초

Junit 5는 크게 세 개의 요소로 구성되어 있음

- JUnit 플랫폼: 테스팅 프레임워크를 구동하기 위한 런처와 테스트 엔진을 위한 API를 제공
- JUnit 주피터(Jupiter): JUnit 5를 위한 테스트 API와 실행 엔진을 제공
- JUnit 빈티지(Vintage): JUnit 3과 4로 작성된 테스트를 JUnit 5 플랫폼에서 실행하기 위한 모듈을 제공한다

### 주요 단언 메서드

Assertions 클래스가 제공하는 주요 단언 메서드

| 메서드                                            | 설명                                                         |
| ------------------------------------------------- | ------------------------------------------------------------ |
| `assertEquals(expected, actual)`                  | 실제 값(actual)이 기대하는 값(expected)과 같은지 검사한다    |
| `assertNotEquals(unexpected, actual)`             | 실제 값(actual)이 특정 값(unexpected)과 같지 않은지 검사한다 |
| `assertSame(Object expected, Object actual)`      | 두 객체가 동일한 객체인지 검사한다                           |
| `assertNotSame(Object unexpected, Object actual)` | 두 객체가 동일하지 않은 객체인지 검사한다                    |
| `assertTrue(boolean condition)`                   | 값이 true인지 검사한다                                       |
| `assertFalse(boolean condition)`                  | 값이 false인지 검사한다                                      |
| `assertNull(Object actual)`                       | 값이 null인지 검사한다                                       |
| `assertNotNull(Object actual)`                    | 값이 null이 아닌지 검사한다                                  |
| `fail()`                                          | 테스트를 실패 처리한다                                       |

Assertions가 제공하는 익셉션 발생 유무 검사 메서드

| 메서드                                                       | 설명                                                         |
| ------------------------------------------------------------ | ------------------------------------------------------------ |
| `assertThrows(Class<T> expectedType, Executable executable)` | executable을 실행한 결과로 지정한 타입의 익셉션이 발생하는지 검사한다 |
| `assertDoesNotThrow(Executable executable)`                  | executable을 실행한 결과로 익셉션이 발생하지 않는지 검사한다 |

참고로 `assertThrows()`와 `assertDoesNotThrow()` 메서드에서 사용하는 Executable 인터페이스는 다음과 같이 `execute()` 메서드를 가진 함수형 인터페이스이다

```java
package org.junit.jupiter.api.function;

public interface Executable {
    void execute() throws Throwable;
}
```

### 테스트 라이프 사이클

#### `@BeforeEach` 애노테이션과 `@AfterEach` 애노테이션
JUnit은 각 테스트 메서드마다 다음 순서대로 코드를 실행한다

1. 테스트 메서드를 포함한 객체 생성
2. (존재하면) `@BeforeEach` 애노테이션이 붙은 메서드 실행
3. `@Test` 애노테이션이 붙은 메서드 실행
4. (존재하면) `@AfterEach` 애노테이션이 붙은 메서드 실행

#### `@BeforeAll` 애노테이션과 `@AfterAll` 애노테이션
- `@BeforeAll`: 한 클래스의 모든 테스트 메서드가 실행되기 전에 특정 작업을 수행해야 할 때 사용
  - 정적 메서드에 붙여야되며 클래스의 모든 테스트 메서드를 실행하기 전에 한 번 실행된다
- `@AfterAll`: 반대로 클래스의 모든 테스트 메서드를 실행한 뒤에 실행된다
  - 이 메서드 역시 정적 메서드에 적용

### 테스트 메서드 간 실행 순서 의존과 필드 공유하지 않기
```java
public class BadTest {
    private FileOperator op = new FileOperator();
    private static File file; // 두 테스트가 데이터를 공유할 목적으로 필드 사용
    
    @Test
    void fileCreationTest() {
        File createdFile = op.createFile();
        assertTrue(createdFile.length() > 0);
        this.file = createdFile;
    }
    
    @Test
    void readFileTest() {
        long data = op.readData(file);
        assertTrue(data > 0);
    }
}
```

각 테스트 메서드는 서로 독립적으로 동작해야 한다. 한 테스트 메서드의 결과에 따라 다른 테스트 메서드의 실행 결과가 달라지면 안 된다.  
그런 의미에서 테스트 메서드가 서로 필드를 공유한다거나 실행 순서를 가정하고 테스트를 작성하지 말아야 한다.

### 추가 애노테이션: `@DisplayName`, `@Disabled`
- `@DisplayName`: 테스트에 표시 이름을 붙일 수 있음
- `@Disabled`: 특정 테스트를 실행하지 않고 싶을 때 사용

### 모든 테스트 실행하기
- mvn test (래퍼를 사용하는 경우 mvnw test)
- gradle test (래퍼를 사용하는 경우 gradlew test)

아래의 명령어를 실행하면 라이프사이클에 의해 테스트가 먼저 수행된다

- mvn package
- gradle build

인텔리J나 이클립스에서 `src/test/java` 폴더에서 테스트를 실행


---

## Chapter 6. 테스트 코드의 구성

### 기능에서의 상황

> 상황찾기
> 노련한 개발자는 어떤 상황이 실행 결과에 영향을 줄 수 있는지 찾기 위해 노력한다.  
> 결과에 영향을 줄 수 있는 상황을 고려하지 않고 작성한 코드는 버그를 포함할 가능성이 크기 때문이다.  
> 다양한 예외 상황을 찾아내고 이를 코드에 반영해야 기능이 비정상적으로 동작하는 것을 막을 수 있다.  
> 결과에 영향을 줄 수 있는 모든 상황을 완벽하게 찾기 힘들 수도 있지만 가능한 많은 예외 상황을 찾기 위해 노력해야 한다.  
> 그렇게 함으로써 개발한 소프트웨어의 품질을 높일 수 있다.  



