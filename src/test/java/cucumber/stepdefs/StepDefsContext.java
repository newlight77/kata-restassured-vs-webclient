package cucumber.stepdefs;

import io.restassured.response.Response;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
public enum StepDefsContext {

    CONTEXT;

    private final ThreadLocal<Map<String, Object>> givenObjects = ThreadLocal.withInitial(HashMap::new);
    private final ThreadLocal<Map<String, Object>> thenObjects = ThreadLocal.withInitial(HashMap::new);
    private final ThreadLocal<Map<String, Object>> errorObjects = ThreadLocal.withInitial(HashMap::new);

    public <T> T givenObject(Class<T> clazz) {
        return clazz.cast(givenObjects.get()
                .get(clazz.getName()));
    }

    public <T> void givenObject(T object, Class<T> clazz) {
        givenObjects.get()
                .put(clazz.getName(), object);
    }

    public <T> T thenObject(Class<T> clazz) {
        return clazz.cast(thenObjects.get()
                .get(clazz.getName()));
    }

    public <T> void thenObject(T object, Class<T> clazz) {
        thenObjects.get()
                .put(clazz.getName(), object);
    }

    public <T> T errorObject(Class<T> clazz) {
        return clazz.cast(errorObjects.get()
                .get(clazz.getName()));
    }

    public <T> void errorObject(T object, Class<T> clazz) {
        errorObjects.get()
                .put(clazz.getName(), object);
    }


    public void reset() {
        givenObjects.get()
                .clear();
        thenObjects.get()
                .clear();
        errorObjects.get()
                .clear();
    }
}
