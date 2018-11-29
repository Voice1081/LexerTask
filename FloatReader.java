import java.util.*;

public class FloatReader extends IReadable {

    public FloatReader(){
        HashMap<String, HashMap<String, String>> statesMatrix = new HashMap<>();
        HashMap<String, String> startStates = new HashMap<>();
        startStates.put("digit", "read whole-part");
        startStates.put("sign", "read sign");
        startStates.put(".", "read fraction");
        statesMatrix.put("start", startStates);
        HashMap<String, String> signStates = new HashMap<>();
        signStates.put("digit", "read whole-part");
        signStates.put(".", "read fraction");
        statesMatrix.put("read sign", signStates);
        HashMap<String, String> wholePartStates = new HashMap<>();
        wholePartStates.put(".", "read fraction");
        wholePartStates.put("exp", "read exp");
        wholePartStates.put("suffix", "read suffix");
        wholePartStates.put("digit", "read whole-part");
        statesMatrix.put("read whole-part", wholePartStates);
        HashMap<String, String> fractionStates = new HashMap<>();
        fractionStates.put("digit", "read correct fraction");
        statesMatrix.put("read fraction", fractionStates);
        HashMap<String, String> correctFractionStates = new HashMap<>();
        correctFractionStates.put("digit", "read correct fraction");
        correctFractionStates.put("exp", "read exp");
        correctFractionStates.put("suffix", "read suffix");
        statesMatrix.put("read correct fraction", correctFractionStates);
        HashMap<String, String> expStates = new HashMap<>();
        expStates.put("digit", "read correct exp");
        expStates.put("sign", "read signed exp");
        statesMatrix.put("read exp", expStates);
        HashMap<String, String> signedExpStates = new HashMap<>();
        signedExpStates.put("digit", "read correct exp");
        statesMatrix.put("read signed exp", signedExpStates);
        HashMap<String, String> correctExpStates = new HashMap<>();
        correctExpStates.put("digit", "read correct exp");
        correctExpStates.put("suffix", "read suffix");
        statesMatrix.put("read correct exp", correctExpStates);
        statesMatrix.put("read suffix", new HashMap<>());
        setStatesMatrix(statesMatrix);
        HashSet<String> terminalStates = new HashSet<>();
        terminalStates.add("read whole-part");
        terminalStates.add("read correct fraction");
        terminalStates.add("read correct exp");
        terminalStates.add("read suffix");
        setTerminalStates(terminalStates);
        setType("float");
    }

    @Override
    protected Object makeValue(String token) {
        return Double.parseDouble(token);
    }

    @Override
    protected String interpretChar(Character ch) {
        if(Character.isDigit(ch)) return "digit";
        if(ch == 'd' || ch == 'D' || ch == 'f' || ch == 'F') return "suffix";
        if(ch == 'e' || ch == 'E') return "exp";
        if(ch == '+' || ch == '-') return "sign";
        if(ch == '.') return ".";
        return ch.toString();
    }
}
