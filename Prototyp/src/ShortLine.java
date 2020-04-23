public class ShortLine extends Shape {
    public ShortLine(String PathBlueImage, String PathRedImage) {
        super(PathBlueImage, PathRedImage);
        posArr = new boolean[9];
        posArr[0] = posArr[1] = true;
        numberOfField = 2;

    }
}
