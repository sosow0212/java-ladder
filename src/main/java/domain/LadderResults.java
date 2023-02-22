package domain;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LadderResults {

    private final HashMap<Integer, LadderResult> ladderResults;

    public LadderResults(final List<String> results) {
        this.ladderResults = makeResults(results);
    }

    private HashMap<Integer, LadderResult> makeResults(final List<String> results) {
        HashMap<Integer, LadderResult> ladderResults = new HashMap<>();

        for (int i = 0; i < results.size(); i++) {
            ladderResults.put(i, new LadderResult(results.get(i)));
        }

        return ladderResults;
    }

    public String findFirstResult() {
        return this.ladderResults.get(0).getResult();
    }

    public int findLongestLadderResults() {
        return this.ladderResults.values().stream()
                .mapToInt(ladderResult -> ladderResult.getResult().length())
                .max()
                .orElseThrow(IllegalArgumentException::new);
    }

    public Map<Integer, LadderResult> getLadderResults() {
        return Collections.unmodifiableMap(this.ladderResults);
    }

    public LadderResult findLadderResultByIndex(final int index) {
        return this.ladderResults.get(index);
    }
}
