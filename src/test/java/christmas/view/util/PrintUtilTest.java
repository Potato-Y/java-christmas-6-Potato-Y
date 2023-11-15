package christmas.view.util;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import camp.nextstep.edu.missionutils.test.NsTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PrintUtilTest extends NsTest {

    private static final String DASH = "-";

    /**
     * 본 코드는 Windows 운영체제에서 인코딩 문제로 통과하지 못할 수 있음. 설정 변경 필요
     */
    @DisplayName("한칸 건너뛰고 출력된다.")
    @Test
    void firstPrint() {
        System.out.print(DASH); // 시작 직후의 println()은 무시된다. 방지용 출력
        final String message = "우테코";
        PrintUtil.firstPrint(message);

        assertThat(output()).isEqualTo(DASH + "\n" + message);
    }

    @DisplayName("이름과 갯수가 출력된다.")
    @Test
    void printlnItemNumber() {
        final String name = "우테코";
        final int number = 1;
        PrintUtil.printlnItemNumber(name, number);

        assertThat(output()).isEqualTo(name + " " + number + "개");
    }

    @DisplayName("제목이 출력된다.")
    @Test
    void titlePrint() {
        final String title = "우테코";
        PrintUtil.titlePrint(title);

        assertThat(output()).isEqualTo("<" + title + ">");
    }

    /**
     * 본 코드는 Windows 운영체제에서 인코딩 문제로 통과하지 못할 수 있음. 설정 변경 필요
     */
    @DisplayName("출력 후 줄바꿈이 된다.")
    @Test
    void println() {
        final String message = "우테코";
        PrintUtil.println(message);
        System.out.println(DASH);

        assertThat(output()).isEqualTo(message + "\n" + DASH);
    }

    @DisplayName("문구가 이어서 출력된다.")
    @Test
    void print() {
        final String message = "우테코";
        PrintUtil.print(message);
        System.out.print(DASH);

        assertThat(output()).isEqualTo(message + DASH);
    }

    @DisplayName("원 단위로 출력이 된다.")
    @Test
    void printlnWon() {
        PrintUtil.printlnWon(66_500);

        assertThat(output()).isEqualTo("66,500원");
    }

    @DisplayName("마이너스 금액이 출력된다.")
    @Test
    void printlnMinusWon() {
        PrintUtil.printlnMinusWon(66_500);

        assertThat(output()).isEqualTo("-66,500원");
    }

    @Override
    protected void runMain() {
    }

}