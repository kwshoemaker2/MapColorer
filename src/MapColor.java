public enum MapColor {
    RED("red"),
    BLUE("blue"),
    GREEN("green"),
    YELLOW("yellow");

    private final String colorId;

    public MapColor(String colorStr) {
        colorId = colorStr;
    }

    public String getId() {
        return colorId;
    }
}
