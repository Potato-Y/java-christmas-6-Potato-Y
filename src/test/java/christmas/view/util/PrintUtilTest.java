package christmas.view.util;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import camp.nextstep.edu.missionutils.test.NsTest;
import org.junit.jupiter.api.Test;

class PrintUtilTest extends NsTest {

    @Test
    void printlnWon() {
        PrintUtil.printlnWon(66_500);

        assertThat(output()).isEqualTo("66,500원");
    }

    @Override
    protected void runMain() {
    }

}