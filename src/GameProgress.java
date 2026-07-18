import java.io.*;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class GameProgress implements Serializable {
    private static final long serialVersionUID = 1L;

    private int health;
    private int weapons;
    private int lvl;
    private double distance;

    public GameProgress(int health, int weapons, int lvl, double distance) {
        this.health = health;
        this.weapons = weapons;
        this.lvl = lvl;
        this.distance = distance;
    }

    @Override
    public String toString() {
        return "GameProgress{" +
                "health=" + health +
                ", weapons=" + weapons +
                ", lvl=" + lvl +
                ", distance=" + distance +
                '}';
    }

    public class saveGame {

        // Метод для сохранения одного объекта GameProgress в файл
        public static void saveGame(String filePath, GameProgress gameProgress) throws IOException {
            File file = new File(filePath);
            // Создаём родительские директории
            if (!file.getParentFile().exists()) {
                boolean created = file.getParentFile().mkdirs();
                if (!created) {
                    throw new IOException("Не удалось создать директорию: " + file.getParent());
                }
            }

            // Используем try-with-resources для гарантированного закрытия потоков
            try (FileOutputStream fos = new FileOutputStream(file);
                 ObjectOutputStream oos = new ObjectOutputStream(fos)) {
                oos.writeObject(gameProgress);
            }
        }

        // Метод для упаковки списка файлов в ZIP-архив
        public static void zipFiles(String zipPath, List<String> filesToZip) throws IOException {
            try (FileOutputStream fos = new FileOutputStream(zipPath);
                 ZipOutputStream zos = new ZipOutputStream(fos)) {

                for (String filePath : filesToZip) {
                    File file = new File(filePath);
                    if (!file.exists()) {
                        System.out.println("Файл не найден, пропускаем: " + filePath);
                        continue;
                    }

                    // Имя внутри архива — только имя файла, без полного пути (опционально)
                    String entryName = file.getName();
                    ZipEntry entry = new ZipEntry(entryName);
                    zos.putNextEntry(entry);

                    try (FileInputStream fis = new FileInputStream(file)) {
                        byte[] buffer = new byte[1024];
                        int length;
                        while ((length = fis.read(buffer)) > 0) {
                            zos.write(buffer, 0, length);
                        }
                    } finally {
                        zos.closeEntry();
                    }
                }
            }
        }

        // Метод для удаления файлов из списка
        public static void deleteFiles(List<String> filesToDelete) {
            for (String filePath : filesToDelete) {
                File file = new File(filePath);
                if (file.exists() && file.isFile()) {
                    boolean deleted = file.delete();
                    if (deleted) {
                        System.out.println("Удален файл: " + filePath);
                    } else {
                        System.out.println("Не удалось удалить файл: " + filePath);
                    }
                } else {
                    System.out.println("Файл не существует или не является файлом: " + filePath);
                }
            }
        }
    }
}