package ast;

import java.util.regex.Pattern;

public class ASTToken {

    private final TokenType tokenType;

    private final String tokenValue;

    public ASTToken(TokenType tokenType, String tokenValue) {
        this.tokenType = tokenType;
        this.tokenValue = tokenValue;
    }

    public static ASTToken fromString(String string) {
        TokenType tokenType;
        if (string.equals("(")) tokenType = TokenType.LEFT_PARENTHESIS;
        else if (string.equals(")")) tokenType = TokenType.RIGHT_PARENTHESIS;
        else if (Pattern.matches("[+-/*]", string)) tokenType = TokenType.OPERATION;
        else tokenType = TokenType.NUMBER;
        return new ASTToken(tokenType, string);
    }

    public TokenType getTokenType() {
        return tokenType;
    }

    public String getTokenValue() {
        return tokenValue;
    }
}