package ast;

public class ASTNode {

    private String value;

    private ASTNode leftChild;

    private ASTNode rightChild;

    private ASTNode parent;

    private String expression;

    private NodeType nodeType;

    public ASTNode() {
        this.value = null;
        this.leftChild = null;
        this.rightChild = null;
        this.parent = null;
        this.expression = null;
        this.nodeType = null;
    }

    public ASTNode(String value, NodeType nodeType) {
        this.value = value;
        this.nodeType = nodeType;
        this.leftChild = null;
        this.rightChild = null;
        this.parent = null;
        this.expression = null;
    }

    public ASTNode createLeftChild() {
        this.leftChild = new ASTNode();
        return leftChild;
    }

    public ASTNode createRightChild() {
        this.rightChild = new ASTNode();
        return rightChild;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public ASTNode getLeftChild() {
        return leftChild;
    }

    public void setLeftChild(ASTNode leftChild) {
        this.leftChild = leftChild;
    }

    public ASTNode getRightChild() {
        return rightChild;
    }

    public void setRightChild(ASTNode rightChild) {
        this.rightChild = rightChild;
    }

    public ASTNode getParent() {
        return parent;
    }

    public void setParent(ASTNode parent) {
        this.parent = parent;
    }

    public String getExpression() {
        return expression;
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }

    public NodeType getNodeType() {
        return nodeType;
    }

    public void setNodeType(NodeType nodeType) {
        this.nodeType = nodeType;
    }
}
