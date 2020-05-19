package blokudoku;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

import static java.lang.String.format;

public class ScoresFile {
    private File file;
    private List<Object[]> cellsList;


    public Object[][] readScores(String filePath) {
        cellsList = new ArrayList<>();
        try {
            file = new File(filePath);
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                Object[] row = line.split(";");
                cellsList.add(row);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Object[][] cells = getCells();
        return cells;
    }

    public void saveScore(Object[] row) {
        prepareFile();
        cellsList.add(row);
        sortList();

        try {
            PrintWriter printWriter = new PrintWriter(file);
            for (int i = 0; i < cellsList.size(); i++)
                printWriter.println(format("%d;%s;%s;%s;%s", i + 1, cellsList.get(i)[1],
                        cellsList.get(i)[2], cellsList.get(i)[3], cellsList.get(i)[4]));

            printWriter.close();
        } catch (
                FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void prepareFile() {
        try {
            if (file.exists())
                file.delete();
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void sortList() {
        if (cellsList.size() > 2)
            Collections.sort(cellsList, new Comparator<Object[]>() {
                @Override
                public int compare(Object[] o1, Object[] o2) {
                    int value1 = Integer.parseInt(String.valueOf(o1[2]));
                    int value2 = Integer.parseInt(String.valueOf(o2[2]));
                    return value2 - value1;
                }
            });
    }

    public Object[][] getCells() {
        Object[][] cells = new Object[cellsList.size()][];
        for (int i = 0; i < cellsList.size(); i++) {
            cells[i] = cellsList.get(i);
        }
        return cells;
    }
}


