import java.util.*;
public abstract class IReadable {
    private String type;
    private String currentState;
    private HashMap<String, HashMap<String, String>> statesMatrix;
    private HashSet<String> terminalStates;

    protected void setType(String type){this.type = type;}
    protected String getType(){return type;}

    protected void setCurrentState(String state){currentState = state;}
    protected String getCurrentState(){return currentState;}

    public HashMap<String, HashMap<String, String>> getStatesMatrix() { return statesMatrix; }
    public void setStatesMatrix(HashMap<String, HashMap<String, String>> statesMatrix) { this.statesMatrix = statesMatrix; }

    public HashSet<String> getTerminalStates() { return terminalStates; }
    public void setTerminalStates(HashSet<String> terminalStates) { this.terminalStates = terminalStates; }


    protected abstract Object makeValue(String token);

    protected abstract String interpretChar(Character ch);

    protected Token tryGetToken(String input){

        setCurrentState("start");
        int i = 0;
        StringBuilder token = new StringBuilder();
        while(i < input.length()) {
            Character ch = input.charAt(i);
            String interpreted = interpretChar(ch);
            if (getStatesMatrix().get(getCurrentState()).containsKey(interpreted)) {
                setCurrentState(getStatesMatrix().get(getCurrentState()).get(interpreted));
                token.append(ch);
            } else break;
            i++;
        }
        if(!getTerminalStates().contains(getCurrentState())) return null;
        String str = token.toString();
        return new Token(getType(), str, makeValue(str));
    }



}
