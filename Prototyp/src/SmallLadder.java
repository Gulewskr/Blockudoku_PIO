public class SmallLadder extends Shape {
    public SmallLadder(String PathBlueImage, String PathRedImage) {
        super(PathBlueImage, PathRedImage);
        posArr = new boolean[9];
        posArr[1] = posArr[3] = true;
        numberOfField = 2;
    }

    @Override
    public boolean checkShape(boolean[] isSetNow) {
        return checkReverseShape(isSetNow, 1);
    }
}
