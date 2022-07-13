package w1d4;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Reader implements Callable<ConcurrentLinkedQueue<String>> {

    public static final String VOYNA_I_MIR_TXT = "src\\w1d4\\voyna-i-mir.txt";
    public String[] array = {"ружье", "здоровье", "туман", "кабинет", "год", "месяц", "лошадь"};
    String paragraph;
    ConcurrentLinkedQueue<String> stringConcurrentLinkedQueue = new ConcurrentLinkedQueue<>();

    /**
     * Читает из файла абзацы, и ищет в них подходящие предложения
     *
     * @param array    - массив слов, которые надо искать
     * @param fileName - имя файла
     * @return предложения, в которых есть слова из массива
     */
    public ConcurrentLinkedQueue<String> reade(String[] array, String fileName) {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            while ((paragraph = br.readLine()) != null) {
                extractSentence(array, paragraph, stringConcurrentLinkedQueue);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return stringConcurrentLinkedQueue;
    }

    /**
     * Находит предложения из текста
     *
     * @param array     - массив слов, которые надо искать
     * @param paragraph - абзац
     * @param strings   - предложение, в котором ищется слова из массива
     */
    private void extractSentence(String[] array, String paragraph,
                                 ConcurrentLinkedQueue<String> strings) {
        String sentence;
        Pattern pattern = Pattern.compile("[^.!?\\[]+[.!?]");
        Matcher matcher = pattern.matcher(paragraph);
        while (matcher.find()) {
            sentence = matcher.group();
            searchSentences(array, sentence, strings);
        }
    }

    /**
     * Ищет слова из массива в предложениях
     *
     * @param array    - массив слов, которые надо искать
     * @param sentence - предложения
     * @param strings  - добавляются подходящие предложения
     */
    private void searchSentences(String[] array, String sentence,
                                 ConcurrentLinkedQueue<String> strings) {
        for (String word : array) {
            if (sentence.contains(" " + word + " ")) {
                strings.add(sentence);
                break;
            }
        }
    }

    @Override
    public ConcurrentLinkedQueue<String> call() {
        return reade(array, VOYNA_I_MIR_TXT);
    }
}