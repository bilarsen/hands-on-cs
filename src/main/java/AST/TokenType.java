package AST;

public enum TokenType {

    LEFT_PARENTHESIS("Открывающая скобка"),
    RIGHT_PARENTHESIS("Закрывающая скобка"),
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