// semantico

package a3.una;

import java.util.List;
import java.util.HashMap;
import java.util.Map;

public class MiniJavaSemanticAnalyzer {
    private List<String> tokens;

    public MiniJavaSemanticAnalyzer(List<String> tokens) {
        this.tokens = tokens;
    }

    public void analyze() {
        // Implement semantic analysis logic here
        // We'll use a simple symbol table to keep track of variables and their types
        SymbolTable symbolTable = new SymbolTable();
        analyzeProgram(symbolTable);
    }

    private void analyzeProgram(SymbolTable symbolTable) {
        // Program -> ClassDecl*
        while (tokens.size() > 0) {
            analyzeClassDecl(symbolTable);
        }
    }

    private void analyzeClassDecl(SymbolTable symbolTable) {
        // ClassDecl -> "class" Identifier "{" FieldDecl* "}"
        String className = tokens.get(1);
        symbolTable.addClass(className);
        tokens.remove(0); // Consume "class"
        tokens.remove(0); // Consume identifier
        if (tokens.get(0).equals("{")) {
            tokens.remove(0); // Consume "{"
            while (!tokens.get(0).equals("}")) {
                analyzeFieldDecl(symbolTable, className);
            }
            tokens.remove(0); // Consume "}"
        } else {
            throw new RuntimeException("Expected '{' after class declaration");
        }
    }

    private void analyzeFieldDecl(SymbolTable symbolTable, String className) {
        // FieldDecl -> Type Identifier ";"
        String type = tokens.get(0);
        String fieldName = tokens.get(1);
        symbolTable.addField(className, fieldName, type);
        tokens.remove(0); // Consume type
        tokens.remove(0); // Consume identifier
        if (tokens.get(0).equals(";")) {
            tokens.remove(0); // Consume ";"
        } else {
            throw new RuntimeException("Expected ';' after field declaration");
        }
    }
}


class SymbolTable {
    private Map<String, Map<String, String>> classes = new HashMap<>();

    public void addClass(String className) {
        classes.put(className, new HashMap<String, String>());
    }

    public void addField(String className, String fieldName, String type) {
        classes.get(className).put(fieldName, type);
    }
}