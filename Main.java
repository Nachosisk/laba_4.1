import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class Main {

    public static void copyFile(String source, String destination) {
        try {
            Path sourcePath = Paths.get(source);
            Path destinationPath = Paths.get(destination);
            Files.copy(sourcePath, destinationPath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        String src1 = "file1.txt";
        String dest1 = "copy1.txt";
        String src2 = "file2.txt";
        String dest2 = "copy2.txt";

        long startTime = System.currentTimeMillis();

        Thread thread1 = new Thread(() -> copyFile(src1, dest1));
        Thread thread2 = new Thread(() -> copyFile(src2, dest2));

        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        long endTime = System.currentTimeMillis();
        System.out.println("Время выполнения: " + (endTime - startTime) + " мс");
    }
}