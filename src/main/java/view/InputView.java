package view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class InputView {

    private static final String SPLIT_DELIMITER = ",";
    private static final String NEW_LINE = System.getProperty("line.separator");

    private final Scanner reader = new Scanner(System.in);

    public List<String> readPlayerNames() {
        System.out.println("참여할 사람 이름을 입력하세요. (이름은 쉼표(,)로 구분하세요)");
        String playerNames = reader.next();
        return splitInput(playerNames);
    }

    public int readHeight() {
        System.out.println(NEW_LINE + "최대 사다리 높이는 몇 개인가요?");
        String height = reader.next();
        int ladderHeight = validateIntegerInput(height);
        return ladderHeight;
    }

    public List<String> readLadderResults() {
        System.out.println(NEW_LINE + "실행 결과를 입력하세요. (결과는 쉼표(,)로 구분하세요)");
        String ladderResults = reader.next();
        return splitInput(ladderResults);
    }

    public String readName() {
        System.out.println("결과를 보고 싶은 사람은?");
        return reader.next();
    }

    private int validateIntegerInput(final String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException exception) {
            throw new IllegalArgumentException("사다리 높이의 입력 값은 숫자만 가능합니다.");
        }
    }

    private List<String> splitInput(final String input) {
        return Arrays.stream(input.split(SPLIT_DELIMITER))
                .collect(Collectors.toList());
    }
}
