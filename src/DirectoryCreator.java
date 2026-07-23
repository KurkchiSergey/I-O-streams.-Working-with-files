import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class DirectoryCreator {

    private static final String GENERAL_PATH = "C:\\Users\\user\\IdeaProjects\\I-O-streams.-Working-with-files";
    private static final String LOG_FILE = GENERAL_PATH + "\\temp\\temp.txt";

    public static void main(String[] args) {
        StringBuilder log = new StringBuilder();

        ArrayList<String> directories;
        directories = new ArrayList<>(List.of(
                GENERAL_PATH + "\\temp",
                GENERAL_PATH + "\\src\\main",
                GENERAL_PATH + "\\src\\test",
                GENERAL_PATH + "\\res\\drawables",
                GENERAL_PATH + "\\res\\vectors",
                GENERAL_PATH + "\\res\\icons",
                GENERAL_PATH + "\\savegames"
        ));

        for (String path : directories) {
            createDirectory(path, log);
        }

        List<String[]> files = List.of(
                new String[]{GENERAL_PATH + "\\src\\main", "Main.java"},
                new String[]{GENERAL_PATH + "\\src\\main", "Utils.java"}

        );

        for (String[] fileInfo : files) {
            String dirPath = fileInfo[0];
            String fileName = fileInfo[1];
            createFile(dirPath, fileName, log);
        }

        writeLogToFile(log.toString());
    }

    // создаем директорию с логированием
    public static void createDirectory(String path, StringBuilder log) {
        File directory = new File(path);
        if (directory.exists()) {
            log.append("Директория уже существует: ").append(path).append("\n");
            return;
        }
        boolean created = directory.mkdirs();
        if (created) {
            log.append("Директория успешно создана: ").append(path).append("\n");
        } else {
            log.append("Не удалось создать директорию: ").append(path).append("\n");
        }
    }

    // создаем файл с логированием
    public static void createFile(String path, String fileName, StringBuilder log) {
        File file = new File(path, fileName);
        if (file.exists()) {
            log.append("Файл уже существует: ").append(file.getAbsolutePath()).append("\n");
            return;
        }
        try {
            boolean created = file.createNewFile();
            if (created) {
                log.append("Файл успешно создан: ").append(file.getAbsolutePath()).append("\n");
            } else {
                log.append("Не удалось создать файл: ").append(file.getAbsolutePath()).append("\n");
            }
        } catch (IOException e) {
            log.append("Ошибка при создании файла ").append(file.getAbsolutePath())
                    .append(". Причина: ").append(e.getMessage()).append("\n");
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


