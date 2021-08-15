package AST;

import java.util.ArrayList;
import java.util.List;

public class StringToTokenParser {

    private final List<ASTToken> tokens = new ArrayList<>();

    public List<ASTToken> parse(String expression) {
        String[] exprMembers = expression.split("");
        for (String member : exprMembers) {
            tokens.add(ASTToken.fromString(member));
        }
        return tokens;
    }
}
