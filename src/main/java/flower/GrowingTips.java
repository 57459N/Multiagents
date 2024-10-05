package flower;

import java.util.HashMap;
import java.util.Map;

public class GrowingTips {
    private Map<String, String> tips;

    public GrowingTips() {
        this.tips = new HashMap<>();
    }

    public void addTip(String type, String value) {
        this.tips.put(type, value);
    }

    public String getTip(String type) {
        return tips.get(type);
    }

    public Map<String, String> getTips() {
        return tips;
    }

    @Override
    public String toString() {
        return "GrowingTips{" + "tips=" + tips + '}';
    }
}

