package domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PlayersTest {

    @Test
    @DisplayName("사람의 수가 1명 이하이면 예외를 던진다.")
    void throws_exception_number_of_player_under_two() {
        // given
        List<String> playerNames = List.of("pobi");

        // when & then
        assertThatThrownBy(() -> new Players(playerNames))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("참여 가능한 플레이어의 수는 2명이상 10명이하 입니다.");
    }

    @Test
    @DisplayName("사람의 수가 10명 보다 크면 예외를 던진다.")
    void throws_exception_number_of_players_over_ten() {
        // given
        List<String> playerNames = new ArrayList<>();
        for (int i = 0; i < 11; i++) {
            playerNames.add("abc" + i);
        }

        // when & then
        assertThatThrownBy(() -> new Players(playerNames))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("참여 가능한 플레이어의 수는 2명이상 10명이하 입니다.");
    }

    @Test
    @DisplayName("중복된 사람의 이름이 있으면 예외를 던진다.")
    void throws_exception_when_existing_duplicated_name() {
        // given
        String duplicatedName = "pobi";
        List<String> playerNames = List.of(duplicatedName, duplicatedName);

        // when & then
        assertThatThrownBy(() -> new Players(playerNames))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("참가한 플레이어의 이름 중 중복된 이름이 존재하면 안됩니다.");
    }

    @Test
    @DisplayName("플레이어 중 가장 긴 이름의 길이를 반환한다.")
    void returns_longest_length_of_player_name() {
        // given
        String player1 = "bob";
        String player2 = "popo";
        String playerHavingLongestName = "dolbi";
        List<String> playerNames = List.of(player1, player2, playerHavingLongestName);
        int expectedLongestLengthOfName = playerHavingLongestName.length();

        Players players = new Players(playerNames);

        // when
        int longestLengthOfName = players.findLongestPlayerName();

        // then
        assertThat(longestLengthOfName).isEqualTo(expectedLongestLengthOfName);
    }

    @Test
    @DisplayName("참여한 플레이어의 수를 반환하다.")
    void returns_number_of_players() {
        // given
        String player1 = "bob";
        String player2 = "popo";
        String playerHavingLongestName = "dolbi";
        List<String> playerNames = List.of(player1, player2, playerHavingLongestName);
        Players players = new Players(playerNames);
        int expectedResult = players.getPlayers().size();

        // when
        int numberOfPlayer = players.findNumberOfPlayers();

        // then
        assertThat(numberOfPlayer).isEqualTo(expectedResult);
    }

    @Test
    @DisplayName("참여한 플레이어들 중 첫번째 플레이어의 이름을 반환하다.")
    void returns_name_of_first_player() {
        // given
        String firstPlayerName = "bob";
        String normalPlayer = "popo";
        List<String> playerNames = List.of(firstPlayerName, normalPlayer);
        Players players = new Players(playerNames);

        // when
        String expectedResult = players.findFirstPlayerName();

        // then
        assertThat(expectedResult).isEqualTo(firstPlayerName);
    }

    @Test
    @DisplayName("참여한 플레이어들 중 첫번째 플레이어의 이름의 길이를 반환하다.")
    void returns_length_of_first_player_name() {
        String firstPlayerName = "bob";
        String normalPlayer = "popo";
        List<String> playerNames = List.of(firstPlayerName, normalPlayer);
        Players players = new Players(playerNames);

        // when
        int expectedResult = players.findLengthOfFirstPlayerName();

        // then
        assertThat(expectedResult).isEqualTo(firstPlayerName.length());
    }

    @Test
    @DisplayName("인자로 들어오는 이름을 가진 플레이어를 반환한다.")
    void returns_player_to_use_name() {
        // given
        String player1 = "pobi";
        String player2 = "crong";
        String player3 = "honux";

        Players players = new Players(List.of(player1, player2, player3));

        // when
        Player expectedPlayer = players.findPlayerByName(player1);

        // then
        assertThat(expectedPlayer.getName()).isEqualTo(player1);
    }

    @Test
    @DisplayName("해당하는 이름을 가진 플레이어가 있는지 결과를 반환해준다.")
    void returns_is_contained_name_of_player() {
        // given
        String command = "pobi";
        List<String> playerNames = List.of(command, "jay", "odo");
        Players players = new Players(playerNames);

        // when
        boolean expectedResult = players.isContainsPlayer(command);

        // then
        assertThat(expectedResult).isEqualTo(true);
    }
}
