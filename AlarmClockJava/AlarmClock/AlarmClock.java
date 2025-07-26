package AlarmClock;

import javax.sound.sampled.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.time.LocalTime;
import java.util.Scanner;

public class AlarmClock implements Runnable{
    private final LocalTime alarmTime;
    private final String filepath;
    private final Scanner sc;


    AlarmClock(LocalTime alarmTime,String filepath,Scanner sc) {
        this.alarmTime = alarmTime;
        this.filepath=filepath;
        this.sc=sc;
    }

    @Override
    public void run() {
    //LocalTime now = LocalTime.now();
    while (LocalTime.now().isBefore(alarmTime)){
        try {
            Thread.sleep(1000);

            LocalTime now = LocalTime.now();

            int hours= LocalTime.now().getHour();
            int minutes= LocalTime.now().getMinute();
            int sec = LocalTime.now().getSecond();

            System.out.printf("\r%02d:%02d:%02d",
                    now.getHour(),
                    now.getMinute(),
                    now.getSecond());

        } catch (InterruptedException e) {
            System.out.println("Thread was Interrupted");
        }
    }
        System.out.println("\nALARM");
        //Toolkit.getDefaultToolkit().beep();
        PlaySound(filepath);
    }

    private void PlaySound(String filepath){
        File audiofile= new File(filepath);
//        AudioInputStream audioStream= AudioSystem.getAudioInputStream(audiofile);

        try ( AudioInputStream audioStream= AudioSystem.getAudioInputStream(audiofile)){
            Clip clip=AudioSystem.getClip();
            clip.open(audioStream);
            clip.start();
            System.out.println("Press *ENTER* to Stop the alarm: ");
            sc.nextLine();
            clip.stop();

            sc.close();


        }
        catch (UnsupportedAudioFileException e){
            System.out.println("Audio file not Supported");
        }
        catch (LineUnavailableException e){
            System.out.println("Not available");
        }
        catch (IOException e){
            System.out.println("Error reading audio file");
        }
        System.out.println("ThankYou");
    }
}
