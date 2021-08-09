package AST;

import java.util.ArrayList;
import java.util.List;

public class StringToTokenParser {

    private final List<ASTNode> tokens = new ArrayList<>();

    public List<ASTNode> parse(String expression) {
        String[] exprMembers = expression.split("");
        for (String member : exprMembers) {
            tokens.add(ASTNode.fromString(member));
        }
        return tokens;
    }
}
