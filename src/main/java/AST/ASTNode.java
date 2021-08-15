package AST;

public class ASTNode {

    private String value;

    private ASTNode leftChild;

    private ASTNode rightChild;

    private ASTNode parent;

    public ASTNode() {
        value = null;
        leftChild = null;
        rightChild = null;
        parent = null;
    }

    public ASTNode(String value) {
        this.value = value;
        leftChild = null;
        rightChild = null;
        parent = null;
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
}
