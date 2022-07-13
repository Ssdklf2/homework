package w1d1;
/**Реализовать любой алгоритм сортировки массива, содержащего Integer с использованием циклов, ветвлений.
 При необходимости применять исключения.

 Требования к заданию:

 + Работоспособность кода
 + Документация (JavaDoc)
 + Следование Java Code Convention
 + Архитектурная корректность (ООП)
 */
public class Start{
    public static void main (String[] args) {
        RandomArray ObjArray = new RandomArray();
        ObjArray.randomNums(ObjArray.getArray());
        System.out.println(ObjArray.toString());

        Sort sort = new Sort();
        System.out.println("Сортировка массива:");
        sort.quickSort(ObjArray.getArray(), 0,ObjArray.getArray().length - 1);
        System.out.println(ObjArray.toString());
    }
}