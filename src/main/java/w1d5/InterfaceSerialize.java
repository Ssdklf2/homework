package w1d5;

public interface InterfaceSerialize {
    void serialize(Object object, String filename);

    Object deserialize(String file);
}
