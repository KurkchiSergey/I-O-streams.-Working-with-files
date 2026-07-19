import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        // 1. Создаём три экземпляра GameProgress
        GameProgress gp1 = new GameProgress(100, 5, 1, 12.5);
        GameProgress gp2 = new GameProgress(80, 3, 2, 45.0);
        GameProgress gp3 = new GameProgress(60, 2, 3, 90.3);

        // Папка для сохранений (можно подставить свой путь)
        String baseDir = "savegames";
        String file1 = baseDir + File.separator + "save1.dat";
        String file2 = baseDir + File.separator + "save2.dat";
        String file3 = baseDir + File.separator + "save3.dat";
        String zipFile = baseDir + File.separator + "saves.zip";

        List<String> savedFiles = new ArrayList<>();
        savedFiles.add(file1);
        savedFiles.add(file2);
        savedFiles.add(file3);

        try {
            // 2. Сохраняем объекты в файлы
            GameProgress.saveGame.saveGame(file1, gp1);
            GameProgress.saveGame.saveGame(file2, gp2);
            GameProgress.saveGame.saveGame(file3, gp3);
            System.out.println("Файлы сохранений созданы.");

            // 3. Запаковываем файлы в ZIP
            GameProgress.saveGame.zipFiles(zipFile, savedFiles);
            System.out.println("Архив создан: " + zipFile);

            // 4. Удаляем исходные файлы сохранений
            GameProgress.saveGame.deleteFiles(savedFiles);
            System.out.println("Исходные файлы удалены.");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
