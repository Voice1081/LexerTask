import java.util.HashMap;

public class TraditionalCommentReader extends IReadable {

    public TraditionalCommentReader(){
        setType("tc");
        setPrefixes("/*");
        HashMap<Character, String> states = new HashMap<>();
        states.put('*', "possible ending");
        setStates(states);
    }

    @Override
    protected boolean changeState(Character ch) {
        if(getActiveState() == "possible ending") {
            if (ch == '/') {
                setActiveState("end");
                return true;
            } else {
                setActiveState("start");
                return true;
            }
        }
        if(ch == '*'){
            setActiveState("possible ending");
            return true;
        }
        return false;

    }

    @Override
    protected boolean isPart(Character ch) {
        return true;
    }

    @Override
    protected boolean isEnding(Character ch) {
        return false;
    }

    @Override
    protected Object makeValue(String token) {
        return token;
    }

    public static void main(String[] args){
        TraditionalCommentReader r = new TraditionalCommentReader();
        System.out.println(r.tryGetToken("/*sdfjsdfjsdf*/sdfjk*/"));
    }
}
