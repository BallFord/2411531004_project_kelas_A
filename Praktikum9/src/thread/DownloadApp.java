package thread;

class DownloadTask extends Thread {
    private String fileName;

    public DownloadTask(String fileName) {
        this.fileName = fileName;
    }

    public void run() {
        for (int i = 10; i <= 100; i += 10) {
            System.out.println(fileName + " progress: " + i + "%");
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(fileName + " selesai diunduh!");
    }
}

public class DownloadApp {
    public static void main(String[] args) throws InterruptedException {
        Thread f1 = new DownloadTask("File-1");
        Thread f2 = new DownloadTask("File-2");
        Thread f3 = new DownloadTask("File-3");

        f1.start();
        f2.start();
        f3.start();

        System.out.println("\nDownloading...");
        
        f1.join();
        f2.join();
        f3.join();

        System.out.println("Semua file selesai diunduh!");
        System.out.println("\nStatus akhir:");
        System.out.println(f1.getName() + ":" + f1.getState());
        System.out.println(f2.getName() + ":" + f2.getState());
        System.out.println(f3.getName() + " " + f3.getState());
    }
}