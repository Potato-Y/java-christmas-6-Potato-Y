# 기능 구현 목록

**제목: 우테코 12월 이벤트 플래너**

- 메뉴 등록
    - 메뉴 타입
    - 메뉴 종류
- 배지 등록
- 예외 처리 추가
    - 사용자의 잘못된 입력
- 주문 추가
    - 이벤트 주문 규정 만족 검증
        - 음료만 주문 한 경우 검증
        - 최대 주문 갯수 검증
        - 중복된 메뉴 검증
    - 사용자 입력
    - 주문 금액 출력
- 주문 요청
    - 사용자 날짜 입력
    - 기본 날짜 검증 (1 ~ 31)
    - 주문 메뉴 입력
    - 주문 내역 출력
- 증정 이벤트
    - 할인 전 총주문 금액 검증
    - 샴페인 증정 여부
- 할인 이벤트
    - 할인 대상 검증 (주문 금액에 따른)
    - 할인 금액 계산
        - 크리스마스 디데이 할인
        - 평일 할인
        - 주말 할인
        - 특별 할인
        - 증정품 금액 할인 포함
    - 혜택 내역 출력
    - 총혜택 내역 출력
    - 할인 금액 적용
    - 총 할인 금액 출력
- 이밴트 배지
    - 할인 금액에 맞는 배지 반환
    - 배지 출력

# 요구사항 정리

## 판매 메뉴

- 에피타이저
    - 양송이수프 6,000원
    - 파타스 5,500원
    - 시저샐러드 8,000원
- 메인
    - 티본스테이크 55,000원
    - 바비큐립 54,000원
    - 해산물파스타 35,000원
    - 크리스마스파스타 25,000원
- 디저트
    - 초코케이크 15,000원
    - 아이스크림 5,000원
- 음료
    - 제로콜라 3,000원
    - 레드와인 60,000원
    - 샴페인 25,000원

##### 아래는 메뉴판 원본 데이터이다.

```
<애피타이저>
양송이수프(6,000), 타파스(5,500), 시저샐러드(8,000)

<메인>
티본스테이크(55,000), 바비큐립(54,000), 해산물파스타(35,000), 크리스마스파스타(25,000)

<디저트>
초코케이크(15,000), 아이스크림(5,000)

<음료>
제로콜라(3,000), 레드와인(60,000), 샴페인(25,000)
```

## 이벤트

- 이벤트 규정
    - 이벤트 기간은 2023.12.1 ~ 2023.12.31이다. (명시된 경우 예외)
    - 할인 중복이 **허용**된다.
    - **중복** 증정 제공이 **허용**된다.
    - 12월 이벤트 적용 기준
        - 총 주문 금액 10,000원 이상
    - 구매 제한 사항
        - **음료**만 주문 시 **주문 불가**
    - 최대 주문 가능 메뉴 갯수
        - 메뉴는 한번에 최대 20개까지 주문할 수 있다.
        - 갯수는 다음 예시를 따른다.
            - 시저샐러드-1, 티본스테이크-1, 크리스마스파스타-1, 제로콜라-3, 아이스크림-1의 총개수는 7개

- 할인 이벤트
    - 크리스마스 디데이 할인
        - 시행 기간: 2023.12.1 ~ 2023.12.25
        - 할인 액수: 할인 가격이 100원씩 증가하는 형태 (1일째 1,000원, 2일째 1,100원,...25일째 3,400원)
        - 적용 대상: 총주문 금액에서 해당하는 날에 맞는 할인 금액만큼 할인
        - 시작 할인 가격: 1000원
    - 평일 할인
        - 시행 기간: `일요일`, `월요일`, `화요일`, `수요일`, `목요일`
        - 적용 대상: 디저트 메뉴
        - 할인 액수: 1개당 2,023원
    - 주말 할인
        - 시행 기간: `금요일`, `토요일`
        - 적용 대상: 메인 메뉴
        - 할인 액수: 1개당 2,023원
    - 특별 할인
        - 시행 기간: 12월 3일, 10일, 17일, 24일, 25일, 31일
        - 적용 대상: 총주문 금액
        - 할인 액수: 1,000원
- 기타 이벤트
    - 증정 이벤트
        - 적용 대상: 할인 전 총주문 금액이 12만원 이상
        - 증정 내용: 샴페인 1개
    - 이밴트 배지
        - 할인 금액에 따라 이벤트 뱃지 부여
        - 배지 종류 및 기준
            - 별: 5,000원 이상
            - 트리: 10,000원 이상
            - 산타: 20,000원 이상

## 사용자(고객) 경험

- 데이터 입력
    - 방문할 날짜
        - 12월 중 방문할 날짜
        - 숫자 형식만 받는다.
        - 고객에게 다음의 내용을 출력한다.
            - `12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)`
        - 1 이상, 31 이하의 숫자가 아닌 경우 다음의 오류 메시지를 출력하고, 다시 입력받는다.
            - `[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.`
    - 메뉴 선택
        - 사용자가 구매할 메뉴를 입력받는다.
        - 고객에게 다음의 내용을 출력한다.
            - `12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)`
        - 아래의 형식으로 제한한다.
            - `해산물파스타-2,레드와인-1,초코케이크-1`
            - 매뉴는 `,` 으로 구분한다.
            - 갯수는 메뉴명 뒤(`-`)에 오는 숫자이다.
            - 형식과 다른 경우 다음의 오류 메시지를 출력하고, 다시 입력받는다.
                - `[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.`
        - 메뉴판에 없는 메뉴를 입력하는 경우 다음의 오류 메시지를 출력하고, 다시 입력받는다.
            - `[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.`
        - 메뉴의 갯수는 1 이상의 숫자만 입력한다.
            - 숫자가 아닌 경우 다음의 오류 메시지를 출력하고, 다시 입력받는다.
                - `[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.`
        - 중복된 메뉴를 입력한 경우 다음의 오류 메시지를 출력하고, 다시 입력받는다.
            - `[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.`
- 데이터 출력
    - 주문 메뉴의 정해진 출력 순서는 없다.
    - 총혜택 금액에 따라 맞는 뱃지의 이름을 출력한다.
    - 총혜택 금액은 다음과 같이 계산한다.
        - 총혜택 금액 = 할인 금액의 합계 + 증정 메뉴의 가격
    - 할인 후 예상 금액은 다음과 같이 계산한다.
        - 할인 후 예상 결제 금액 = 할인 전 총주문 금액 - 할인 금액
    - 증정 메뉴
        - 증정된 상품이 출력된다.
        - 증정된 상품이 없을 경우 `없음` 을 출력한다.
    - 혜택 내역
        - 고객에게 적용된 이벤트 내역만 출력된다.
        - 적용된 이벤트가 없다면 `없음`으로 출력된다.
        - 혜택 내역의 이벤트 이름의 출력 순서는 없다.
    - 이벤트 배지
        - 부여된 이밴트 배지가 출력된다.
        - 부여된 이벤트 배지가 없을 경우 `없음`으로 출력된다.

#### 예시 - 적용 이벤트가 하나도 없는 경우

```
안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.
12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)
26 
주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)
타파스-1,제로콜라-1 
12월 26일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!
 
<주문 메뉴>
타파스 1개
제로콜라 1개

<할인 전 총주문 금액>
8,500원
 
<증정 메뉴>
없음
 
<혜택 내역>
없음
 
<총혜택 금액>
0원
 
<할인 후 예상 결제 금액>
8,500원
 
<12월 이벤트 배지>
없음
```

#### 예시 - 이상적인 화면

```
안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.
12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)
3
주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)
티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1
12월 3일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!
 
<주문 메뉴>
티본스테이크 1개
바비큐립 1개
초코케이크 2개
제로콜라 1개
 
<할인 전 총주문 금액>
142,000원
 
<증정 메뉴>
샴페인 1개
 
<혜택 내역>
크리스마스 디데이 할인: -1,200원
평일 할인: -4,046원
특별 할인: -1,000원
증정 이벤트: -25,000원
 
<총혜택 금액>
-31,246원
 
<할인 후 예상 결제 금액>
135,754원
 
<12월 이벤트 배지>
산타
```

# 제약 사항

- JDK 17 버전에서 실행 가능해야 한다. JDK 17에서 정상적으로 동작하지 않을 경우 0점 처리한다.
- 프로그램 실행의 시작점은 Application의 main()이다.
- build.gradle 파일을 변경할 수 없고, 외부 라이브러리를 사용하지 않는다.
- Java 코드 컨벤션 가이드를 준수하며 프로그래밍한다.
- 프로그램 종료 시 System.exit()를 호출하지 않는다.
- 프로그램 구현이 완료되면 ApplicationTest의 모든 테스트가 성공해야 한다. 테스트가 실패할 경우 0점 처리한다.
- 프로그래밍 요구 사항에서 달리 명시하지 않는 한 파일, 패키지 이름을 수정하거나 이동하지 않는다.
- indent(인덴트, 들여쓰기) depth를 3이 넘지 않도록 구현한다. 2까지만 허용한다.
- 3항 연산자를 쓰지 않는다.
- 함수(또는 메서드)의 길이가 15라인을 넘어가지 않도록 구현한다.
- JUnit 5와 AssertJ를 이용하여 본인이 정리한 기능 목록이 정상 동작함을 테스트 코드로 확인한다.
- else 예약어를 쓰지 않는다.
- 도메인 로직에 단위 테스트를 구현해야 한다. 단, UI(System.out, System.in, Scanner) 로직은 제외한다.
    - 핵심 로직을 구현하는 코드와 UI를 담당하는 로직을 분리해 구현한다.
- 사용자가 잘못된 값을 입력할 경우 IllegalArgumentException를 발생시키고, "[ERROR]"로 시작하는 에러 메시지를 출력 후 그 부분부터 입력을 다시 받는다.
- camp.nextstep.edu.missionutils에서 제공하는 Console API를 사용하여 구현해야 한다.
    - 사용자가 입력하는 값은 camp.nextstep.edu.missionutils.Console의 readLine()을 활용한다.
