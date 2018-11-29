import java.util.*;

public class TraditionalCommentReader extends IReadable{

    public TraditionalCommentReader(){
        HashMap<String, HashMap<String, String>> statesMatrix = new HashMap<>();
        HashMap<String, String> startStates = new HashMap<>();
        startStates.put("/", "first slash");
        statesMatrix.put("start", startStates);
        HashMap<String, String> firstSlashStates = new HashMap<>();
        firstSlashStates.put("*", "beginning");
        statesMatrix.put("first slash", firstSlashStates);
        HashMap<String, String> beginningStates = new HashMap<>();
        beginningStates.put(".", "beginning");
        beginningStates.put("*", "possible ending");
        statesMatrix.put("beginning", beginningStates);
        HashMap<String, String> possibleEndingStates = new HashMap<>();
        possibleEndingStates.put(".", "beginning");
        possibleEndingStates.put("/", "end");
        statesMatrix.put("possible ending", possibleEndingStates);
        statesMatrix.put("end", new HashMap<>());
        setStatesMatrix(statesMatrix);
        HashSet<String> terminalStates = new HashSet<>();
        terminalStates.add("end");
        setTerminalStates(terminalStates);
        setType("tc");
    }

    @Override
    protected Object makeValue(String token) {
        return token;
    }

    @Override
    protected String interpretChar(Character ch) {
        if(ch != '*' && ch != '/') return ".";
        else return ch.toString();
    }
}
