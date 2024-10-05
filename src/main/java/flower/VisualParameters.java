package flower;

import java.util.HashMap;
import java.util.Map;

public class VisualParameters {
    private Map<String, String> parameters;

    public VisualParameters() {
        this.parameters = new HashMap<>();
    }

    public void addParameter(String type, String value) {
        this.parameters.put(type, value);
    }

    public String getParameter(String type) {
        return parameters.get(type);
    }

    public Map<String, String> getParameters() {
        return parameters;
    }

    @Override
    public String toString() {
        return "VisualParameters{" +
                "parameters=" + parameters +
                '}';
    }
}
