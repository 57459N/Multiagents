package flower;

public class Flower {
    private String id;
    private String name;
    private String soil;
    private String origin;
    private VisualParameters visualParameters;
    private GrowingTips growingTips;
    private String multiplying;


    public Flower(String id, String name, String soil, String origin, VisualParameters visualParameters, GrowingTips growingTips, String multiplying) {
        this.id = id;
        this.name = name;
        this.soil = soil;
        this.origin = origin;
        this.visualParameters = visualParameters;
        this.growingTips = growingTips;
        this.multiplying = multiplying;
    }

    public VisualParameters getVisualParameters() {
        return visualParameters;
    }

    @Override
    public String toString() {
        return "Flower{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", soil='" + soil + '\'' +
                ", origin='" + origin + '\'' +
                ", visualParameters=" + visualParameters +
                ", growingTips=" + growingTips +
                ", multiplying='" + multiplying + '\'' +
                '}';
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSoil(String soil) {
        this.soil = soil;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public void setVisualParameters(VisualParameters visualParameters) {
        this.visualParameters = visualParameters;
    }

    public void setGrowingTips(GrowingTips growingTips) {
        this.growingTips = growingTips;
    }

    public void setMultiplying(String multiplying) {
        this.multiplying = multiplying;
    }

    public GrowingTips getGrowingTips() {
        return growingTips;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSoil() {
        return soil;
    }

    public String getOrigin() {
        return origin;
    }

    public String getMultiplying() {
        return multiplying;
    }
}
