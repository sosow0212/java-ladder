package domain;

public class LadderResult {

    private final String result;

    public LadderResult(final String result) {
        validate(result);
        this.result = result;
    }

    private void validate(final String result) {
        if (result.isEmpty()) {
            throw new IllegalArgumentException("보상을 입력해주세요.");
        }
    }

    public int findLengthOfLadderResult() {
        return this.result.length();
    }

    public String getResult() {
        return this.result;
    }
}
