public class Boat extends Shape {
    public Boat(String PathBlueImage, String PathRedImage) {
        super(PathBlueImage, PathRedImage);
        posArr = new boolean[9];
        posArr[0] = posArr[2] = posArr[3] = posArr[4] = posArr[5] = true;
        numberOfField = 5;
    }
}
