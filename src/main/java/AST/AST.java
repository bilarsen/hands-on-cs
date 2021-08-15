package AST;

import java.util.ArrayList;
import java.util.List;

public class AST {

    private final ASTNode root;

    public AST(ASTNode root) {
        this.root = root;
    }

    public void build(List<ASTToken> tokens, ASTNode root) {
        if (tokens == null || tokens.isEmpty() || root == null) return;
        buildAST(new ArrayList<>(tokens), root);
    }

    private void buildAST(List<ASTToken> tokens, ASTNode root) {
        ASTNode currentNode = root;
        ASTToken currentToken;
        while (!tokens.isEmpty()) {
            currentToken = tokens.remove(0);
            switch (currentToken.getTokenType()) {
                case LEFT_PARENTHESIS:
                    currentNode = currentNode.createLeftChild();
                    break;
                case RIGHT_PARENTHESIS:
                    currentNode = currentNode.getParent();
                    break;
                case NUMBER:
                    currentNode.setValue(currentToken.getTokenValue());
                    currentNode = currentNode.getParent();
                    break;
                case OPERATION:
                    currentNode.setValue(currentToken.getTokenValue());
                    currentNode = currentNode.createRightChild();
                    break;
            }
        }
    }
}
