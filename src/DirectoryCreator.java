import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class DirectoryCreator {

    private static final String GENERAL_PATH = "C:\\Users\\5beng\\Netology\\Games";
    private static final String LOG_FILE = GENERAL_PATH + "\\temp\\temp.txt";

    public static void main(String[] args) {
        StringBuilder log = new StringBuilder();

        ArrayList<String> directories = new ArrayList<>(List.of(
                GENERAL_PATH + "\\temp",
                GENERAL_PATH + "\\src\\main",
                GENERAL_PATH + "\\src\\test",
                GENERAL_PATH + "\\res\\drawables",
                GENERAL_PATH + "\\res\\vectors",
                GENERAL_PATH + "\\res\\icons",
                GENERAL_PATH + "\\savegames"
        ));

        for (String path : directories) {
            log.append(createDirectory(path)).append("\n");
        }

        List<String[]> files = List.of(
                new String[]{GENERAL_PATH + "\\src\\main", "Main.java"},
                new String[]{GENERAL_PATH + "\\src\\main", "Utils.java"}

        );

        for (String[] fileInfo : files) {
            String dirPath = fileInfo[0];
            String fileName = fileInfo[1];
            log.append(createFile(dirPath, fileName)).append("\n");
        }

        writeLogToFile(log.toString());
    }

    public static String createDirectory(String path) {
        File directory = new File(path);
        if (directory.exists()) {
            return "Директория уже существует: " + path;
        }
        boolean created = directory.mkdirs();
        return created
                ? "Директория успешно создана: " + path
                : "Не удалось создать директорию: " + path;
    }

    public static String createFile(String path, String fileName) {
        File file = new File(path, fileName);
        if (file.exists()) {
            return "Файл уже существует: " + file.getAbsolutePath();
        }
        try {
            boolean created = file.createNewFile();
            return created
                    ? "Файл успешно создан: " + file.getAbsolutePath()
                    : "Не удалось создать файл: " + file.getAbsolutePath();
        } catch (IOException e) {
            return "Ошибка при создании файла " + file.getAbsolutePath() + ". Причина: " + e.getMessage();
        }
    }

    private static void writeLogToFile(String logContent) {
        File logFile = new File(LOG_FILE);
        // Проверяем существование родительской папки и создаем ее, если нужно
        if (!logFile.getParentFile().exists()) {
            logFile.getParentFile().mkdirs();
        }

        try (FileWriter writer = new FileWriter(logFile)) {
            writer.write(logContent);
            System.out.println("Лог успешно записан в: " + logFile.getAbsolutePath());
        } catch (IOException e) {
            System.err.println("Не удалось записать лог в файл: " + e.getMessage());
        }
    }
}


