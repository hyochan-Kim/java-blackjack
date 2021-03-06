package domain.gambler;

import domain.gambler.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class PlayersTest {
    @DisplayName("생성자 NULL일 경우 예외")
    @Test
    void Players() {
        assertThatThrownBy(() -> new Players(null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Null");
    }

    @DisplayName("플레이어 최대인원 초과시 예외 발생")
    @Test
    void validMaximumPlayerCount() {
        Map<Name, Money> playerInfo = new LinkedHashMap<>();
        for (Name name : new Names("jamie1,jamie2,jamie3,jamie4,jamie5,jamie6,jamie7,jamie8")
                .getNames()) {
            playerInfo.put(name, Money.fromPositive("500"));
        }
        assertThatThrownBy(() -> new Players(playerInfo))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("range");
    }

    @DisplayName("플레이어들의 이름들을 받아오는 테스트")
    @Test
    void getPlayerNames() {
        Map<Name, Money> playerInfo = new LinkedHashMap<>();
        for (Name name : new Names("jamie1,jamie2").getNames()) {
            playerInfo.put(name, Money.fromPositive("500"));
        }
        Players players = new Players(playerInfo);
        assertThat(players.getPlayerNames().get(0)).isEqualTo("jamie1");
        assertThat(players.getPlayerNames().get(1)).isEqualTo("jamie2");
    }
}
