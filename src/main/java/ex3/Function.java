package ex3;

import java.io.*;
import java.util.*;

public class Function extends PlusFunction {

    /**
     * Создает файлы, которые будут содержать сгенеированный текст с указанными параметрами
     * @param path - путь файла
     * @param n - количество файлов
     * @param size - количество абзацев
     * @param words - массив, который заполнен сгенерированными словами
     * @param probability -  вероятность вхождения одного из слов этого массива в предложение
     */
    public void getFiles(String path, int n, int size, String[] words, int probability) {
        for (int i = 0; i < n; i++) {
            String fileName = path + i;
            try (FileWriter fileWriter = new FileWriter(new File(fileName))){
                fileWriter.write(createText(size, words, probability));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * Генерирует слово
     * n2 - количество букв (от 1 до 15) в слове
     * @return  случайное слово из n2 букв
     */
    public String randomWord() {
        StringBuilder newWord = new StringBuilder();
        int n2 = (int) ((Math.random() * 14) + 1);
        for (int i = 0; i < n2; i++) {
            char randomChar = (char) ('a' + new Random().nextInt('z' - 'a'));
            newWord.append(randomChar);
        }
        return newWord.toString();
    }

    /**
     *Кладет сгенерированные слова в массив
     * @return массив состоящий из 1000 слов
     */
    public String[] randomWordsInArray() {
        String[] words = new String[1000];
        for (int i = 0; i < 1000; i++) {
            words[i] = randomWord();
        }
        return words;
    }

    /**
     * Выбирает из массива words случайные слова и с определенной вероятностью var кладет их в новый массив newSentence
     * @param words - массив, который заполнен случайно сгенерированными словами
     * @param n1  - количество слов в предложении (от 1 до 15)
     * @param var - вероятность, с которой слово в предложении повториться
     * @return массив из новых слов, из которых будет строиться предложении
     */
    public String[] createNewArraySentence(String[] words, int n1, double var) {
        int indexOfWords = (int) ((Math.random() * 999)+1);
        String[] newSentence = new String[n1];
        for (int i = 0; i < n1; i++) {
            if(Math.random() <= var){
                newSentence[i] = words[indexOfWords];
            }
            else {
                int indexOfWordsNew = (int) ((Math.random() * 999)+1);
                String newWord = words[indexOfWordsNew];
                newSentence[i] = newWord;
            }
        }
        return newSentence;
    }

    /**
     * Генериует предложение
     * n1 - количество слов в предложении (от 1 до 15)
     * @param words - массив, который заполнен случайно сгенерированными словами
     * @param probability -  вероятность вхождения одного из слов этого массива в предложение
     * @return предложение, состоящее из n1 слов
     */
    public StringBuilder randomSentence(String[] words, int probability) {
        int n1 = (int) ((Math.random() * 14) + 1);
        StringBuilder sentence = new StringBuilder();
        double var = 1 / (double) probability;
        String[] newSentence = createNewArraySentence(words, n1, var);
        List<String> newSentenceL = new ArrayList<>(Arrays.asList(newSentence));
        Collections.shuffle(newSentenceL);
        for (int i = 1; i <= n1; i++) {
            sentence.append(newSentenceL.get(i-1));
            if(i!=n1){
                sentence.append(commaOrEmpty());
            }
            else {
                sentence.append(getPoint());
            }
        }
        return new StringBuilder(sentence.substring(0, 1).toUpperCase() + sentence.substring(1));
    }

    /**
     *Создает абзац, состоящий от 1 до 20 предложений
     * @return абзац
     */
    public StringBuilder createParagraph(String[] words, int probability) {
        int n3 = (int) ((Math.random() * 19) + 1);
        StringBuilder paragraph = new StringBuilder("    ");
        for (int i = 0; i < n3; i++) {
            paragraph.append(randomSentence(words, probability));
        }
        return paragraph;
    }

    /**
     *Создает текст из абзацев определенного размера
     * @return текст в виде строки
     */
    public String createText(int size, String[] words, int probability) {
        StringBuilder text = new StringBuilder();
        for (int i = 0; i < size; i++) {
            text.append(createParagraph(words, probability));
            text.append("\n");
        }
        return text.toString();
    }
}

