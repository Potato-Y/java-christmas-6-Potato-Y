package christmas.badge.model;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class EventBadgeTest {

    @DisplayName("가격에 맞춰 배지가 발급된다: 없음")
    @Test
    void getNoneBadge() {
        EventBadge badge = EventBadge.find(1_000);

        assertThat(badge).isEqualTo(EventBadge.NONE);
    }

    @DisplayName("가격에 맞춰 배지가 발급된다: 별")
    @Test
    void getStarBadge() {
        EventBadge badge = EventBadge.find(6_500);

        assertThat(badge).isEqualTo(EventBadge.STAR);
    }

    @DisplayName("가격에 맞춰 배지가 발급된다: 트리")
    @Test
    void getTreeBadge() {
        EventBadge badge = EventBadge.find(11_000);

        assertThat(badge).isEqualTo(EventBadge.TREE);
    }

    @DisplayName("가격에 맞춰 배지가 발급된다: 산타")
    @Test
    void getSantaBadge() {
        EventBadge badge = EventBadge.find(21_000);

        assertThat(badge).isEqualTo(EventBadge.SANTA);
    }

}