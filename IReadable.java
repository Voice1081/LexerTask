import java.util.*;
public abstract class IReadable {
    private String type;
    private String activeState;
    private String[] prefixes;
    private HashSet<Character> suffixes;
    private HashMap<String, HashSet<Character>> parts;
    private HashMap<Character, String> states;

    protected void setType(String type){this.type = type;}
    protected String getType(){return type;}
    protected void setActiveState(String state){activeState = state;}
    protected String getActiveState(){return activeState;}
    protected void setPrefixes(String...prefixes){this.prefixes = prefixes;}
    protected String[] getPrefixes(){return prefixes;}
    protected void setSuffixes(Character...suffixes){this.suffixes = new HashSet<>(Arrays.asList(suffixes));}
    protected HashMap<String, HashSet<Character>> getParts(){return parts;}
    protected void setParts(HashMap<String, HashSet<Character>> parts){this.parts = parts;}
    protected HashMap<Character, String> getStates(){return states;}
    protected void setStates(HashMap<Character, String> states){this.states = states;}
    protected HashSet<Character> getSuffixes(){return suffixes;}

    protected boolean isStart(String input){
        for(String p: getPrefixes()){
            if (input.startsWith(p)){
                setActiveState("start");
                return true;
            }
        }
        return false;
    }
    protected boolean isEnding(Character ch){
        if(getSuffixes().contains(ch)){
            setActiveState("end");
            return true;
        }
        return false;
    }

    protected boolean isPart(Character ch){
        return getParts().get(getActiveState()).contains(ch);
    }

    protected boolean changeState(Character ch){
        if (states.containsKey(ch)){
            setActiveState(states.get(ch));
            return true;
        }
        return false;
    }

    protected abstract Object makeValue(String token);

    protected Token tryGetToken(String input){
        if(!isStart(input)) return null;
        int i = 0;
        StringBuilder token = new StringBuilder();
        while(getActiveState() != "end" && i < input.length()){
            Character ch = input.charAt(i);
            if(changeState(ch)){
                token.append(ch);
                i++;
                continue;
            }
            if(isEnding(ch)){
                break;
            }
            if(isPart(ch))
                token.append(ch);
            else return null;
            i++;
        }
        if(getActiveState() != "end") return null;
        String str = token.toString();
        return new Token(type, str, makeValue(str));
    }


}
