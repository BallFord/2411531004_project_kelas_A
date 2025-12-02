package tugas;

public class DownloadTaskLambda {
    public static void main(String[] args) {
        Runnable downloadTask = () -> {
            String fileName = Thread.currentThread().getName();
            for (int i = 10; i <= 100; i += 10) {
                System.out.println(fileName + " progress: " + i + "%");
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(fileName + " selesai diunduh!");
        };

        Thread f1 = new Thread(downloadTask, "File-1");
        Thread f2 = new Thread(downloadTask, "File-2");
        Thread f3 = new Thread(downloadTask, "File-3");

        f1.start();
        f2.start();
        f3.start();
    }
}