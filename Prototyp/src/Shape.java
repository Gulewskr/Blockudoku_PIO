import javax.swing.Icon;
import javax.swing.ImageIcon;

public abstract class Shape {
    protected ImageIcon blueImage;
    protected ImageIcon redImage;
    protected boolean[] posArr;
    protected int numberOfField;

    public Shape(String PathBlueImage, String PathRedImage) {
        blueImage = new ImageIcon(PathBlueImage);
        redImage = new ImageIcon(PathRedImage);
    }

    public boolean checkShape(boolean[] isSetNow) {
        int firstIndex;
        for (firstIndex = 0; firstIndex < 81; firstIndex++) {
            if (isSetNow[firstIndex])
                break;
        }


        for (int j = 0; j < 3; j++) {
            for (int l = 0; l < 3; l++) {
                if (firstIndex + l + j * 9 < 81)
                    if (isSetNow[firstIndex + l + j * 9] != posArr[l + j * 3]) {
                        return false;
                    }
            }
        }
        return true;
    }

    public boolean checkReverseShape(boolean[] isSetNow, int x) {
        int firstIndex;
        for (firstIndex = 0; firstIndex < 81; firstIndex++) {
            if (isSetNow[firstIndex])
                break;
        }


        for (int j = 0; j < 3; j++) {
            for (int l = 0; l < 3; l++) {
                if (firstIndex + l + j * 9 < 81)
                    if (isSetNow[firstIndex + l + j * 9 - x] != posArr[l + j * 3]) {
                        return false;
                    }
            }
        }
        return true;
    }


    public ImageIcon getBlueImage() {
        return blueImage;
    }

    public Icon getRedImage() {
        return redImage;
    }

    public int getNumberOfField() {
        return numberOfField;
    }

}
