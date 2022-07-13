package ex3;

public class PlusFunction {

    /**
     * Добавляет с вероятностью varPoint в конец предложения символы: . ! ?
     *
     * @return . или !  или ?
     */
    public StringBuilder getPoint() {
        int varPoint = (int) ((Math.random()) * 10);
        StringBuilder point = new StringBuilder();
        if (varPoint < 5) {
            point = new StringBuilder(". ");
        }
        if (5 <= varPoint && varPoint < 8) {
            point = new StringBuilder("! ");
        }
        if (varPoint >= 8) {
            point = new StringBuilder("? ");
        }
        return point;
    }

    /**
     * Генерирует пробел(с большей вероятностью) или запятую с пробелом
     *
     * @return запятую или пробел
     */
    public StringBuilder commaOrEmpty() {
        StringBuilder commaOrEmpty;
        if ((Math.random() * 10) <= 2) {
            commaOrEmpty = new StringBuilder((", "));
        } else {
            commaOrEmpty = new StringBuilder((" "));
        }
        return commaOrEmpty;
    }

}
