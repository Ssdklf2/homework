package w1d3;

public class Start {
    public static final String SRC_W_1_D_3_WORDS_TXT = "src\\w1d3\\words.txt";

    public static void main(String[] args) {
        Function function = new Function();
        String[] words = function.randomWordsInArray();
        function.getFiles(SRC_W_1_D_3_WORDS_TXT,2, 5, words, 5);
    }
}

