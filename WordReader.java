public class WordReader {
	private final String word;
	private final boolean ignoreCase;

	// TODO Задача 1: Добавить параметр ignoreCase — чувствительность к регистру
	public WordReader(String word, boolean ignoreCase) {
		super();
		this.word = word;
		this.ignoreCase = ignoreCase;
	}

	public Token tryReadToken(String input) {
	    if(input.length() < word.length()) return null;
	    if(ignoreCase){
	        if(input.toLowerCase().startsWith(word.toLowerCase()))
	            return new Token("kw", input.substring(0, word.length()));
	        return null;
        }
		if (input.startsWith(word))
			return new Token("kw", word);
		return null;
	}

	public static void main(String[] args) {
		WordReader reader = new WordReader("hello", true);
		System.out.println("Testing WordReader...");
		test(reader, "", null);
		test(reader, "H", null);
		test(reader, "Hello world", "Hello");
		test(reader, "hell", null);
		test(reader, "hello world", "hello");
		System.out.println("Testing finished");
	}

	public static void test(WordReader r, String input, String expected) {
		Token actualToken = r.tryReadToken(input);
		if (expected == null) {
			if (actualToken != null)
				System.out.println("ERROR: on " + input + " read "
						+ actualToken);
		} else {
			if (!expected.equals(actualToken.getText()))
				System.out.println("ERROR: on " + input + " expected "
						+ expected + " but was " + actualToken);
		}
	}

}
