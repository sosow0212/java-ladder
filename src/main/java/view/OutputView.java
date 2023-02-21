package view;

import domain.Ladder;
import domain.LadderResult;
import domain.LadderResults;
import domain.LadderSymbol;
import domain.Player;
import domain.Players;
import java.util.Map;

public class OutputView {

    private static final int MAXIMUM_LENGTH_OF_NAME = 5;
    private static final int BOUNDARY_OF_NAME_LENGTH = 4;
    private static final String BLANK = " ";
    private static final String FOOTHOLD = "-";
    private static final String BAR = "|";
    private static final String DELIMITER = " : ";
    private static final String NEW_LINE = System.getProperty("line.separator");
    private static final StringBuilder ladderOutput = new StringBuilder();
    private static final StringBuilder ladderResultsOutput = new StringBuilder();
    private static final StringBuilder playerNamesOutput = new StringBuilder();
    private static final StringBuilder finallyResult = new StringBuilder();

    public void printLadderGameStatus(final Players players, final Ladder ladder, final LadderResults ladderResults) {
        System.out.println(NEW_LINE + "사다리 결과");

        finallyResult.append(makePlayerNamesOutput(players))
                .append(NEW_LINE)
                .append(makeLadderOutput(players, ladder))
                .append(makeLadderResults(ladderResults));

        System.out.println(finallyResult);
    }

    public StringBuilder makePlayerNamesOutput(final Players players) {
        int longestPlayerName = players.findLongestPlayerName();

        drawFirstPlayerName(players);

        for (int i = 1; i < players.getPlayers().size(); i++) {
            appendPlayerNames(longestPlayerName, players.getPlayers().get(i));
        }

        return playerNamesOutput;
    }

    private void drawFirstPlayerName(final Players players) {
        playerNamesOutput.append(players.findFirstPlayerName())
                .append(BLANK);
    }

    private void appendPlayerNames(final int longestName, final Player player) {
        if (isMaximumLengthOfPlayerName(player)) {
            drawPlayerNameWhenMaximumLength(player);
            return;
        }
        drawPlayerName(longestName, player);
    }

    private boolean isMaximumLengthOfPlayerName(final Player player) {
        return player.getLengthOfPlayerName() == MAXIMUM_LENGTH_OF_NAME;
    }

    private void drawPlayerNameWhenMaximumLength(final Player player) {
        playerNamesOutput.append(BLANK)
                .append(player.getName());
    }

    private void drawPlayerName(final int longestName, final Player player) {
        int numberOfBlank = longestName - player.getName().length();
        playerNamesOutput.append(LadderSymbol.draw(BLANK, numberOfBlank))
                .append(player.getName())
                .append(BLANK);
    }

    public StringBuilder makeLadderOutput(final Players players, final Ladder ladder) {
        for (int heightIndex = 0; heightIndex < ladder.findLadderHeight(); heightIndex++) {
            int lengthOfFirstPlayerName = players.findLengthOfFirstPlayerName();

            drawSpaceAtFirst(lengthOfFirstPlayerName);
            drawInnerLadder(players, ladder, heightIndex);
            drawBarAtLast();
        }

        return ladderOutput;
    }

    private void drawSpaceAtFirst(final int lengthOfFirstPlayerName) {
        if (isLengthOfNameMoreThanBoundary(lengthOfFirstPlayerName)) {
            ladderOutput.append(BLANK.repeat(BOUNDARY_OF_NAME_LENGTH));
            return;
        }
        ladderOutput.append(BLANK.repeat(lengthOfFirstPlayerName));
    }

    private boolean isLengthOfNameMoreThanBoundary(final int lengthOfFirstPlayerName) {
        return lengthOfFirstPlayerName >= BOUNDARY_OF_NAME_LENGTH;
    }

    private void drawInnerLadder(final Players players, final Ladder ladder, final int heightIndex) {
        for (Boolean isExistFoothold : ladder.getLines().findSelectedLine(heightIndex)) {
            ladderOutput.append(BAR);
            drawExistingFoothold(players, isExistFoothold);
        }
    }

    private void drawExistingFoothold(final Players players, final Boolean isExistFoothold) {
        if (isExistFoothold) {
            ladderOutput.append(FOOTHOLD.repeat(players.findLongestPlayerName()));
            return;
        }
        ladderOutput.append(BLANK.repeat(players.findLongestPlayerName()));
    }

    private void drawBarAtLast() {
        ladderOutput.append(BAR)
                .append(NEW_LINE);
    }

    public String makeLadderResults(final LadderResults ladderResults) {
        for (final LadderResult ladderResult : ladderResults.getLadderResults().values()) {
            ladderResultsOutput.append(
                    String.format("%-" + (MAXIMUM_LENGTH_OF_NAME + 1) + "s", ladderResult.getResult()));
        }

        ladderResultsOutput.append(NEW_LINE);
        return ladderResultsOutput.toString();
    }

    public void printResult(final Map<Player, LadderResult> gameResult, final String command) {
        System.out.println(NEW_LINE + "실행 결과");
        System.out.println(drawResult(gameResult, command) + NEW_LINE);
    }

    private String drawResult(final Map<Player, LadderResult> gameResult, final String command) {
        if (command.equals("all")) {
            return drawAllResults(gameResult);
        }

        Player player = gameResult.keySet().stream()
                .filter(player1 -> player1.getName().equals(command))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);

        return gameResult.get(player).getResult();
    }

    private String drawAllResults(final Map<Player, LadderResult> gameResult) {
        StringBuilder output = makePlayersResultOutput(gameResult);
        return output.toString();
    }

    private StringBuilder makePlayersResultOutput(final Map<Player, LadderResult> playersWithResult) {
        StringBuilder output = new StringBuilder();

        for (Player player : playersWithResult.keySet()) {
            output.append(player.getName())
                    .append(DELIMITER)
                    .append(playersWithResult.get(player).getResult())
                    .append(NEW_LINE);
        }

        return output;
    }
}
