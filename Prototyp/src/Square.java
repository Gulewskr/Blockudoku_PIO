public class Square extends Shape {
    public Square(String PathBlueImage, String PathRedImage) {
        super(PathBlueImage, PathRedImage);
        posArr = new boolean[9];
        posArr[0] = posArr[1] = posArr[3] = posArr[4] = true;
        numberOfField = 4;
    }
}
