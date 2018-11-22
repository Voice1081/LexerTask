import java.util.ArrayList;

public class Lexer {
    private ArrayList<IReadable> readers = new ArrayList<>();

    public void register(IReadable reader){
        readers.add(reader);
    }

    public ArrayList<Token> tokenize(String s){
        if(readers.size() == 0) return null;
        ArrayList<Token> tokens = new ArrayList<>();
        int pos = 0;
        while(pos < s.length()) {
            int maxLength = 0;
            Token maxToken = null;
            for (IReadable reader : readers) {
                Token token = reader.tryGetToken(s.substring(pos));
                if (token != null)
                    if (token.getText().length() > maxLength) {
                        maxToken = token;
                        maxLength = token.getText().length();
                    }
            }
            if(maxLength != 0){
                pos += maxLength;
                tokens.add(maxToken);
            }
            else pos += 1;
        }
        return tokens;
    }

    public static void main(String[] args){
        Lexer lex = new Lexer();
        lex.register(new FloatReader());
        lex.register(new TraditionalCommentReader());
        lex.register(new EndOfLineCommentReader());
        lex.tokenize("334e1-0f");
        System.out.println(lex.tokenize("123+123f //sdfsdf"));
    }
}
