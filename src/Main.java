import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        File baseDir = new File("Games");

        File dirRes = new File(baseDir, "res");
        File dirSaveGames = new File(baseDir, "savegames");
        File dirTemp = new File(baseDir, "temp");
        File dirSrc = new File(baseDir, "src");

        StringBuilder log = new StringBuilder();

        // Создаём основные директории
        logDir(dirRes, "Папка res", log);
        logDir(dirSaveGames, "Папка saveGames", log);
        logDir(dirTemp, "Папка temp", log);
        logDir(dirSrc, "Папка src", log);

        // Поддиректории в src
        File main = new File(dirSrc, "main");
        File test = new File(dirSrc, "test");
        logDir(main, "Папка src/main", log);
        logDir(test, "Папка src/test", log);

        // Файлы в src/main
        File mainJavaFile = new File(main, "Main.java");
        File utilsJavaFile = new File(main, "Utils.java");
        logFile(mainJavaFile, "Файл src/main/Main.java", log);
        logFile(utilsJavaFile, "Файл src/main/Utils.java", log);

        // Директории в res
        File drawableDir = new File(dirRes, "drawable");
        File vectorsDir = new File(dirRes, "vectors");
        File iconsDir = new File(dirRes, "icons");
        logDir(drawableDir, "Папка res/drawable", log);
        logDir(vectorsDir, "Папка res/vectors", log);
        logDir(iconsDir, "Папка res/icons", log);

        // Файл temp.txt для лога
        File tempFile = new File(dirTemp, "temp.txt");
        try {
            if (tempFile.createNewFile()) {
                log.append("Файл temp/temp.txt: успешно создан\n");
            } else if (tempFile.exists()) {
                log.append("Файл temp/temp.txt: уже существует\n");
            }
        } catch (IOException e) {
            log.append("Ошибка создания temp/temp.txt: ").append(e.getMessage()).append("\n");
        }

        // Записываем весь лог в temp.txt
        try (FileWriter writer = new FileWriter(tempFile)) {
            writer.write(log.toString());
            System.out.println("Лог успешно записан в: " + tempFile.getAbsolutePath());
        } catch (IOException e) {
            System.err.println("Ошибка записи лога: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static void logDir(File dir, String description, StringBuilder log) {
        if (dir.mkdirs()) {
            log.append(description).append(": успешно создана\n");
        } else if (dir.exists()) {
            log.append(description).append(": уже существует\n");
        } else {
            log.append(description).append(": ошибка создания\n");
        }
    }

    private static void logFile(File file, String description, StringBuilder log) {
        try {
            if (file.createNewFile()) {
                log.append(description).append(": успешно создан\n");
            } else if (file.exists()) {
                log.append(description).append(": уже существует\n");
            } else {
                log.append(description).append(": ошибка создания\n");
            }
        } catch (IOException e) {
            log.append(description).append(": ошибка (IOException): ").append(e.getMessage()).append("\n");
        }
    }
}
        /*
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
*/