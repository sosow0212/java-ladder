package controller;

import domain.Game;
import domain.Height;
import domain.Ladder;
import domain.LadderResults;
import domain.Lines;
import domain.Players;
import java.util.List;
import utils.LadderResultsFactory;
import view.InputView;
import view.OutputView;

public class LadderGameController {

    private final InputView inputView;
    private final OutputView outputView;

    public LadderGameController() {
        this.inputView = new InputView();
        this.outputView = new OutputView();
    }

    public void run() {
        Players players = makePlayers();
        LadderResults ladderResults = makeLadderResults(players.findNumberOfPlayers());
        Ladder ladder = makeLadder(players.findNumberOfPlayers());
        Game game = new Game(players, ladder, ladderResults);

        outputView.printLadderGameStatus(players, ladder, ladderResults);

        printResultOfPlayer(game);
    }

    private void printResultOfPlayer(final Game game) {
        String command = "";

        while (!command.equals("all")) {
            command = makeCommand();
            outputView.printResult(game, command);
        }
    }

    private Players makePlayers() {
        try {
            List<String> playerNames = inputView.readPlayerNames();
            return new Players(playerNames);
        } catch (IllegalArgumentException exception) {
            System.out.println(exception.getMessage());
            return makePlayers();
        }
    }

    private LadderResults makeLadderResults(final int numberOfPlayer) {
        try {
            List<String> ladderResults = inputView.readLadderResults();
            return LadderResultsFactory.createLadderResults(ladderResults, numberOfPlayer);
        } catch (IllegalArgumentException exception) {
            System.out.println(exception.getMessage());
            return makeLadderResults(numberOfPlayer);
        }
    }

    private Ladder makeLadder(final int numberOfPlayers) {
        try {
            int ladderHeight = inputView.readHeight();
            Ladder ladder = new Ladder(new Lines(numberOfPlayers, ladderHeight), new Height(ladderHeight));
            return ladder;
        } catch (IllegalArgumentException exception) {
            System.out.println(exception.getMessage());
            return makeLadder(numberOfPlayers);
        }
    }

    private String makeCommand() {
        try {
            String command = inputView.readName();
            return command;
        } catch (IllegalArgumentException exception) {
            System.out.println(exception.getMessage());
            return makeCommand();
        }
    }
}
