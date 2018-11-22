public class EndOfLineCommentReader extends IReadable {

    public EndOfLineCommentReader() {
        setType("eof");
        setPrefixes("//");
        setSuffixes('\n');

    }

    @Override
    protected Object makeValue(String token) {
        return token;
    }

    @Override
    protected boolean isPart(Character ch) {
        return true;
    }

    @Override
    protected boolean changeState(Character ch) {
        return false;
    }

    @Override
    protected boolean isEnding(Character ch) {
        return super.isEnding(ch);
    }

    public static void main(String[] args){
        EndOfLineCommentReader r = new EndOfLineCommentReader();
        System.out.println(r.tryGetToken("//balb\nabla"));

    }
}
