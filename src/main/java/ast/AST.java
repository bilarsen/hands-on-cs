package ast;

import java.util.ArrayList;
import java.util.List;

public class AST {

    private final ASTNode root;

    public AST(ASTNode root) {
        this.root = root;
    }

    public AST() {
        this.root = new ASTNode();
    }

    public void build(List<ASTToken> tokens, ASTNode root) {
        if (tokens == null || tokens.isEmpty() || root == null) return;
        buildAST(new ArrayList<>(tokens), root);
    }

    public void interpret() {
        if (root == null) return;
        interpret(root);
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
                    currentNode.setNodeType(NodeType.VALUE);
                    currentNode = currentNode.getParent();
                    break;
                case OPERATION:
                    currentNode.setValue(currentToken.getTokenValue());
                    currentNode.setNodeType(NodeType.OPERATION);
                    currentNode = currentNode.createRightChild();
                    break;
            }
        }
    }

    private ASTNode interpret(ASTNode node) {
        if (node == null || node == root && node.getNodeType().equals(NodeType.VALUE)) return node;
        if (node.getLeftChild() == null && node.getRightChild() == null) {
            return node;
        } else if (node.getLeftChild() != null && node.getLeftChild().getNodeType().equals(NodeType.OPERATION)) {
            interpret(node.getLeftChild());
        } else if (node.getRightChild() != null && node.getRightChild().getNodeType().equals(NodeType.OPERATION)) {
            interpret(node.getRightChild());
        } else {
            int result = 0;
            int leftChildValue = Integer.parseInt(node.getLeftChild().getValue());
            int rightChildValue = Integer.parseInt(node.getRightChild().getValue());
            switch (node.getValue()) {
                case "/":
                    result = leftChildValue / rightChildValue;
                    break;
                case "*":
                    result = leftChildValue * rightChildValue;
                    break;
                case "-":
                    result = leftChildValue - rightChildValue;
                    break;
                case "+":
                    result = leftChildValue + rightChildValue;
            }
            node.setValue(String.valueOf(result));
            node.setExpression(String.format("(%d %s %d)", leftChildValue, node.getValue(), rightChildValue));
            node.setNodeType(NodeType.VALUE);
            node.setLeftChild(null);
            node.setRightChild(null);
            return interpret(node.getParent());
        }
        return node;
    }
}
