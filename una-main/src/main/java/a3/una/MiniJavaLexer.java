package a3.una;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MiniJavaLexer {
    private static final Pattern TOKEN_PATTERN = Pattern.compile(
            "\\s*" + // Optional whitespace
            "(" + // Start of token
            "(public|private|class|extends|implements|abstract|static|final|native|synchronized|volatile|transient|strictfp)" + // Keywords
            "|" + // OR
            "(int|boolean|char|byte|short|long|float|double|void)" + // Primitive types
            "|" + // OR
            "(true|false|null)" + // Literals
            "|" + // OR
            "[a-zA-Z_][a-zA-Z_0-9]*" + // Identifiers
            "|" + // OR
            "[0-9]+" + // Int literals
            "|" + // OR
            "'[^']*'" + // Char literals
            "|" + // OR
            "\"[^\"]*\"" + // String literals
            "|" + // OR
            "\\(" + // Left parenthesis
            "|" + // OR
            "\\)" + // Right parenthesis
            "|" + // OR
            "\\[" + // Left bracket
            "|" + // OR
            "\\]" + // Right bracket
            "|" + // OR
            "\\{" + // Left brace
            "|" + // OR
            "\\}" + // Right brace
            "|" + // OR
            ";" + // Semicolon
            "|" + // OR
            "," + // Comma
            "|" + // OR
            "\\." + // Dot
            "|" + // OR
            "\\+" + // Plus
            "|" + // OR
            "\\-" + // Minus
            "|" + // OR
            "\\*" + // Star
            "|" + // OR
            "/" + // Forward slash
            "|" + // OR
            "%" + // Percent
            "|" + // OR
            "==" + // Equal equal
            "|" + // OR
            "!=" + // Not equal
            "|" + // OR
            "<" + // Less than
            "|" + // OR
            ">" + // Greater than
            "|" + // OR
            "<=" + // Less than or equal
            "|" + // OR
            ">=" + // Greater than or equal
            "|" + // OR
            "&&" + // And and
            "|" + // OR
            "||" + // Or or
            "|" + // OR
            "!" + // Not
            "|" + // OR
            "=" + // Assign
            "|" + // OR
            "\\?" + // Question mark
            "|" + // OR
            ":" + // Colon
            ")" + // End of token
            "\\s*" // Optional whitespace
    );

    public static List<String> analyze(String filePath) throws IOException {
        List<String> tokens = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine())!= null) {
                Matcher matcher = TOKEN_PATTERN.matcher(line);
                while (matcher.find()) {
                    String token = matcher.group(1); // Get the first capturing group
                    tokens.add(token);
                }
            }
        }
        return tokens;
    }
}