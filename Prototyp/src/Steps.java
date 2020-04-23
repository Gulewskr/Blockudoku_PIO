public class Steps extends Shape {
    public Steps(String PathBlueImage, String PathRedImage) {
        super(PathBlueImage, PathRedImage);
        posArr = new boolean[9];
        posArr[0] = posArr[3] = posArr[4] = true;
        numberOfField = 3;
    }
}
