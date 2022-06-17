package w1d5;

import org.w3c.dom.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Function implements InterfaceSerialize {

    /**
     * Сериалиазация объекта в файл
     *
     * @param object   объект, который сериализуется
     * @param filename путь к файлу
     */
    @Override
    public void serialize(Object object, String filename) {
        if (object == null) {
            throw new NullPointerException();
        }
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = factory.newDocumentBuilder();
            Document doc = documentBuilder.newDocument();
            Element classElement = doc.createElement(object.getClass().getName());
            doc.appendChild(classElement);
            createElements(object, doc, classElement);
            Transformer t = TransformerFactory.newInstance().newTransformer();
            t.setOutputProperty(OutputKeys.INDENT, "yes");
            t.transform(new DOMSource(doc), new StreamResult(Files.newOutputStream(Paths.get(filename))));
        } catch (ParserConfigurationException | IOException | TransformerException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Десериализация объекта
     *
     * @param filename путь к файлу
     * @return карта в которой ключи - названия полей, значения - значения этих полей
     */
    @Override
    public Object deserialize(String filename) {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        Object newObj;
        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(new File(filename));
            Node nodeObjectFirst = document.getFirstChild();
            newObj = getObject(document, nodeObjectFirst);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return newObj;
    }

    /**
     * Создает элементы полей класса в файле
     * @param object класс, поля которого будут записываться в документ
     * @param doc файл
     * @param classElement элемент с именем класса
     */
    private void createElements(Object object, Document doc, Element classElement) {
        Field[] fields = object.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            if (field.getType().getTypeName().startsWith("w1d5")) {
                Object objIn;
                try {
                    objIn = Class.forName(field.getType().getName()).newInstance();
                    setFieldsInObj(objIn, field.get(object).toString());
                } catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
                Element objInEl = doc.createElement(field.getType().getName());
                classElement.appendChild(objInEl);
                createElements(objIn, doc, objInEl);
                continue;
            }
            Element fieldElement = doc.createElement(field.getName());
            Text value;
            try {
                value = doc.createTextNode(field.get(object).toString());
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
            classElement.appendChild(fieldElement);
            fieldElement.appendChild(value);
        }

    }

    /**
     *Устанавливает значения в объект
     * @param objIn объект, в который подставляются значения
     * @param values значения
     */
    private void setFieldsInObj(Object objIn, String values) {
        String[] vals = values.split(",");
        List<Field> listF = Arrays.stream(objIn.getClass().getDeclaredFields())
                .filter(field -> !field.getName().equals("serialVersionUID"))
                .collect(Collectors.toList());
        for (int i = 0; i < listF.size(); i++) {
            for (int j = 0; j < vals.length; j++) {
                if (i != j) {
                    continue;
                }
                listF.get(i).setAccessible(true);
                try {
                    setField(objIn, listF.get(i), vals[j]);
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    /**
     * Получить объект из файла
     * @param document файл
     * @param nodeObjectName узел xml-файла
     * @return объект
     */
    private Object getObject(Document document, Node nodeObjectName) {
        Object newObj;
        try {
            newObj = Class.forName(nodeObjectName.getNodeName()).newInstance();
            Class<?> classObj = newObj.getClass();
            NodeList fieldElements = nodeObjectName.getChildNodes();
            for (int i = 0; i < fieldElements.getLength(); i++) {
                if (fieldElements.item(i).getNodeType() != Node.ELEMENT_NODE) {
                    continue;
                }
                if (fieldElements.item(i).getNodeName().equals("serialVersionUID")) {
                    continue;
                }
                if (fieldElements.item(i).getNodeName().startsWith("w1d5")) {
                    Node elObj = fieldElements.item(i);
                    Object objeIn = getObject(document, elObj);
                    String fieldName = fieldElements.item(i).getNodeName().substring(5).toLowerCase();
                    Field field = classObj.getDeclaredField(fieldName);
                    field.setAccessible(true);
                    field.set(newObj, objeIn);
                    continue;
                }
                Field field = classObj.getDeclaredField(fieldElements.item(i).getNodeName());
                field.setAccessible(true);
                String value = fieldElements.item(i).getTextContent();
                setField(newObj, field, value);
            }
        } catch (InstantiationException | IllegalAccessException | ClassNotFoundException | NoSuchFieldException e) {
            throw new RuntimeException(e);
        }
        return newObj;
    }

    /**
     * Подставляет значения по типу поля
     * @param object объект в который записываются значение
     * @param field поле объекта
     * @param value значение
     */
    private void setField(Object object, Field field, String value) throws IllegalAccessException {
        switch (field.getType().getName()) {
            case ("java.lang.String"):
                field.set(object, value);
                break;
            case ("int"):
                field.set(object, Integer.parseInt(value));
                break;
            case ("boolean"):
                field.set(object, Boolean.parseBoolean(value));
                break;
            case ("float"):
                field.set(object, Float.parseFloat(value));
                break;
            case ("double"):
                field.set(object, Double.parseDouble(value));
                break;
            case ("char"):
                field.set(object, value.charAt(0));
                break;
            case ("long"):
                field.set(object, Long.parseLong(value));
                break;
            case ("short"):
                field.set(object, Short.parseShort(value));
                break;
            case ("byte"):
                field.set(object, Byte.parseByte(value));
                break;
        }
    }
}
