package w1d3;
/**
 * Создать генератор текстовых файлов, работающий по следующим правилам:
 *
 * 1. Предложение состоит из 1<=n1<=15 слов. В предложении после произвольных слов могут находиться запятые. +
 *
 * 2. Слово состоит из 1<=n2<=15 латинских букв +
 *
 * 3. Слова разделены одним пробелом +
 *
 * 4. Предложение начинается с заглавной буквы +
 *
 * 5. Предложение заканчивается (.|!|?)+" " +
 *
 * 6. Текст состоит из абзацев. в одном абзаце 1<=n3<=20 предложений. В конце абзаца стоит разрыв строки и перенос каретки. +
 *
 * 7. Есть массив слов 1<=n4<=1000. Есть вероятность probability вхождения одного из слов этого массива в следующее предложение (1/probability).
 *
 * Задача: написать метод void getFiles(String path, int n, int size, String[] words, int probability),
 * который создаст n файлов размером size в каталоге path. Words - массив слов из п. 7, probability - вероятность из п. 7.
 *
 * Требования к заданию:
 *
 *     Работоспособность кода
 *     Архитектурная корректность (ООП)
 *     Документация (JavaDoc)
 *     Следование Java Code Convention
 *     Предусмотреть возникновение исключительных ситуаций
 * */