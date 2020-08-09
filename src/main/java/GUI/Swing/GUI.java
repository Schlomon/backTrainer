package GUI.Swing;

import backTrainer.BackTrainer;
import backTrainer.FileIO;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import static java.lang.Thread.sleep;

import javax.swing.*;

public class GUI {

    BackTrainer backTrainer;

    Popup popUp = new Popup();

    public GUI() {
        this.backTrainer = new BackTrainer();
        Path p = Paths.get(FileIO.FILEPATH);

        if (Files.notExists(p)) {
            new Settings();
            this.backTrainer.setPopUpInNeed(true);
        }

        while (backTrainer.isPopUpInNeed()) {
            try {
                sleep(500);
            } catch (InterruptedException ex) {
                System.err.println(ex);
            }
        }

        backTrainer.start();

        this.popUp.setLocationRelativeTo(null);

        this.manage();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            String className = UIManager.getSystemLookAndFeelClassName();
            System.out.println(className);
            UIManager.setLookAndFeel(className);
        } catch (Exception e) {
            System.err.println(e);
        }
        GUI gui = new GUI();
    }

    private void manage() {
        while (BackTrainer.running) {
            while (!backTrainer.isPopUpInNeed()) {
                try {
                    sleep(100);
                } catch (InterruptedException ex) {
                    System.err.println(ex);
                }
            }

            this.popUp();

            backTrainer.setPopUpInNeed(false);

        }
    }

    private void popUp() {
        popUp.setVisible(true);

        while (!Popup.madePopUp) {
            try {
                sleep(100);
            } catch (InterruptedException ex) {
                System.err.println(ex);
            }
        }

    }

}
