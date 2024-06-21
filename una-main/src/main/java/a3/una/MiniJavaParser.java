package a3.una;

import java.util.List;

public class MiniJavaParser {
    private List<String> tokens;

    public MiniJavaParser(List<String> tokens) {
        this.tokens = tokens;
    }

    public void parse() {
        // Implement parsing logic here
        // We'll use a simple recursive descent parser
        parseProgram();
    }

    private void parseProgram() {
        // Program -> ClassDecl*
        while (tokens.size() > 0) {
            parseClassDecl();
        }
    }

    private void parseClassDecl() {
        // ClassDecl -> "class" Identifier "{" FieldDecl* "}"
        if (tokens.get(0).equals("class")) {
            tokens.remove(0); // Consume "class"
            String className = tokens.get(0);
            tokens.remove(0); // Consume identifier
            if (tokens.get(0).equals("{")) {
                tokens.remove(0); // Consume "{"
                while (!tokens.get(0).equals("}")) {
                    parseFieldDecl();
                }
                tokens.remove(0); // Consume "}"
            } else {
                throw new RuntimeException("Expected '{' after class declaration");
            }
        } else {
            throw new RuntimeException("Expected 'class' keyword");
        }
    }

    private void parseFieldDecl() {
        // FieldDecl -> Type Identifier ";"
        String type = parseType();
        String fieldName = tokens.get(0);
        tokens.remove(0); // Consume identifier
        if (tokens.get(0).equals(";")) {
            tokens.remove(0); // Consume ";"
        } else {
            throw new RuntimeException("Expected ';' after field declaration");
        }
    }

    private String parseType() {
        // Type -> "int" | "boolean" |...
        String type = tokens.get(0);
        tokens.remove(0); // Consume type
        return type;
    }
}