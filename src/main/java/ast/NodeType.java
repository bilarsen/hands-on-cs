package ast;

public enum NodeType {

    OPERATION("Операция"),
    VALUE("Значение");

    private final String title;

    NodeType(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
