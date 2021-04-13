package logic;

import java.lang.reflect.InvocationTargetException;


// Amr

public abstract class LogicFactory {

    // -PACKAGE : String = "logic."
    private static final String PACKAGE = "logic.";

    // -SUFFIX : String = "Logic"
    private static final String SUFFIX = "Logic";

    //-LogicFactory()
    private LogicFactory() {
    }

    // +getFor(entityName : String) : T
    public static <T> T getFor(String entityName) {
        try {
            String name = PACKAGE + entityName + SUFFIX;
            return (T) getFor(Class.forName(name));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    // +getFor(type : Class<R>) : R
    public static <R> R getFor(Class<R> type) {
        try {
            return (R) type.getDeclaredConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
        }
        return null;
    }
}
