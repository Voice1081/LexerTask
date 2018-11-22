import java.util.*;

public class FloatReader extends IReadable {

    public FloatReader(){
        setType("float");
        setSuffixes('f', 'F', 'd', 'D');
        setPrefixes(".", "+", "-");
        HashMap<Character, String> states = new HashMap<>();
        states.put('.', "fractional");
        states.put('e', "exponent");
        states.put('E', "exponent");
//        states.put('+', "signed");
//        states.put('-', "signed");
        setStates(states);
        HashMap<String, HashSet<Character>> parts = new HashMap<>();
        Character[] startParts = {'.', 'e', 'E', 'd', 'D', 'f', 'F'};
        Character[] fractionalParts = {'e', 'E', 'd', 'D', 'f', 'F'};
        //Character[] signedParts = {'.', 'e', 'E', 'd', 'D', 'f', 'F'};
        Character[] exponentParts = {'d', 'D', 'f', 'F'};
        //Character[] signedExponentParts = {'d', 'D', 'f', 'F'};
        parts.put("correct whole-part", new HashSet<>(Arrays.asList(startParts)));
        parts.put("correct fractional", new HashSet<>(Arrays.asList(fractionalParts)));
        //parts.put("signed", new HashSet<>(Arrays.asList(signedParts)));
        parts.put("correct exponent", new HashSet<>(Arrays.asList(exponentParts)));
        //parts.put("signed exponent", new HashSet<>(Arrays.asList(signedExponentParts)));
        setParts(parts);
    }

    @Override
    protected Object makeValue(String token) {
        return Double.parseDouble(token);
    }

    @Override
    protected boolean isStart(String input) {
        if(Character.isDigit(input.charAt(0))){
            setActiveState("start");
            return true;
        }
        return super.isStart(input);
    }

    @Override
    protected boolean isPart(Character ch) {
        if(getActiveState() == "incorrect") return false;
        return (Character.isDigit(ch) || super.isPart(ch));
    }

    @Override
    protected boolean isEnding(Character ch) {
        if(!isPart(ch) && (getActiveState().startsWith("correct"))){
            setActiveState("end");
            return true;
        }
        return super.isEnding(ch);
    }

    @Override
    protected boolean changeState(Character ch) {
        if(super.changeState(ch)) return super.changeState(ch);
        if(getActiveState() == "start"){
            if(ch == '+' || ch == '-'){
                setActiveState("signed");
                return true;
            }
            else{
                setActiveState("correct whole-part");
                return true;
            }
        }
        if(getActiveState() == "signed"){
            if(Character.isDigit(ch) || ch == '.') {
                setActiveState("correct whole-part");
                return true;
            }
            else{
                setActiveState("incorrect");
                return false;
            }
        }
        if(getActiveState() == "exponent") {
            if(ch == '+' || ch == '-'){
                setActiveState("signed exponent");
                return true;
            }
            if(Character.isDigit(ch)){
                setActiveState("correct exponent");
                return true;
            }
            else{
                setActiveState("incorrect");
                return false;
            }
        }
        if(getActiveState() == "signed exponent"){
            if(Character.isDigit(ch)) {
                setActiveState("correct exponent");
                return true;
            }
            else{
                setActiveState("incorrect");
                return false;
            }
        }

        if(getActiveState() == "fractional"){
            if(Character.isDigit(ch)) {
                setActiveState("correct fractional");
                return true;
            }
            else{
                setActiveState("incorrect fractional");
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args){
        FloatReader r = new FloatReader();
        r.tryGetToken("1+");
    }
}
