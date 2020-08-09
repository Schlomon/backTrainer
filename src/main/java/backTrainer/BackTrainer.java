package backTrainer;

public class BackTrainer extends Thread implements Runnable {

    public static boolean running = false;

    public static boolean popUpInNeed = false;

    public BackTrainer() {
        running = true;
    }

    @Override
    public void run() {

        while (running) {

            popUpInNeed = true;

            while (popUpInNeed) {
                try {
                    Thread.sleep(250);
                } catch (InterruptedException ex) {
                    System.err.println(ex);
                }
            }

            try {
                sleep((FileIO.readTime() * 1000) * 60);
            } catch (InterruptedException ex) {
                System.err.println(ex);
            }
        }

    }

    public void exit() {
        running = false;
    }

    public boolean isPopUpInNeed() {
        return popUpInNeed;
    }

    public void setPopUpInNeed(boolean popUpInNeed) {
        BackTrainer.popUpInNeed = popUpInNeed;
    }

    public void setPauseInSec(int sec) {
        FileIO.writeTime(sec);
    }

    public void setPauseInMin(int min, int sec) {
        this.setPauseInSec(min * 60 + sec);
    }

    public void incraseWaitingTime(int sec) {
        this.setPauseInSec(FileIO.readTime() + sec);
    }

    public void decraseWaitingTime(int sec) {
        if ((FileIO.readTime() - sec) > 0) {
            this.setPauseInSec(FileIO.readTime() - sec);
        }
    }
}
