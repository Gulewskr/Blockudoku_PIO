public class ReversedL extends Shape {
    public ReversedL(String PathBlueImage, String PathRedImage) {
        super(PathBlueImage, PathRedImage);
        posArr = new boolean[9];
        posArr[2] = posArr[3] =posArr[4] = posArr[5] = true;
        numberOfField = 4;
    }

    @Override
    public boolean checkShape(boolean[] isSetNow) {
        return checkReverseShape(isSetNow, 2);
    }

}
