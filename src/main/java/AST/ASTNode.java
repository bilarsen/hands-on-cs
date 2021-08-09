package AST;

import java.util.regex.Pattern;

public class ASTNode {

    private final TokenType tokenType;

    private final String tokenValue;

    public ASTNode(TokenType tokenType, String tokenValue) {
        this.tokenType = tokenType;
        this.tokenValue = tokenValue;
    }

    public static ASTNode fromString(String string) {
        TokenType tokenType;
        if (Pattern.matches("[()]", string)) tokenType = TokenType.PARENTHESIS;
        else if (Pattern.matches("[+-/*]", string)) tokenType = TokenType.OPERATION;
        else tokenType = TokenType.NUMBER;
        return new ASTNode(tokenType, string);
    }

    public TokenType getTokenType() {
        return tokenType;
    }

    public String getTokenValue() {
        return tokenValue;
    }
}