package AlarmClock;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
import java.util.zip.DataFormatException;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        LocalTime alarmTime =null;

        String filePath="C:\\Users\\HARSH RAI\\Downloads\\Belevier.wav";


        while (alarmTime==null) {
            try {
                System.out.print("Enter an alarm time (HH:mm:ss)");
                String inputTime = sc.nextLine();

                alarmTime = LocalTime.parse(inputTime, formatter);
                System.out.println("Alarm time is: " + alarmTime);
            }
            catch (DateTimeParseException e) {
                System.out.println("ENTER THE CORRECT FORMAT");
            }
        }
        AlarmClock alarmClock= new AlarmClock(alarmTime, filePath,sc);
        Thread alarmthread = new Thread(alarmClock);
        alarmthread.start();

    }
}
