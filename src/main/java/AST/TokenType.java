package AST;

public enum TokenType {

    PARENTHESIS("Скобка"),
    OPERATION("Операция"),
    NUMBER("Число");

    private final String title;

    TokenType(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}