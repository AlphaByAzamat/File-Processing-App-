import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.logging.*;

public class Main {
    private static final Logger logger = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) throws IOException {


        Handler handler = new FileHandler("output_logs/logs.txt", true);
        SimpleFormatter simpleFormatter = new SimpleFormatter();
        handler.setFormatter(simpleFormatter);
        handler.setLevel(Level.INFO);
        logger.addHandler(handler);
        int counter = 0;
        ArrayList<String> txtList = new ArrayList<>();
        txtList.add("input_files/file1.txt");
        txtList.add("input_files/file2.txt");
        txtList.add("input_files/file3.txt");
        txtList.add("input_files/file4.txt");
        for (String path : txtList) {
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    //System.out.println("it is working ");

                    try {
                        FileReader file = new FileReader(path);
                        BufferedReader reader = new BufferedReader(file);
                        String s;
                        StringBuffer fullTxt = new StringBuffer();
                        while ((s = reader.readLine()) != null) {
                            fullTxt.append(s);

                        }
                        int wordsCount = fullTxt.toString().split(" ").length;
                        int fullTxtsize = fullTxt.toString().toCharArray().length;
                        int avrgWordLength = (fullTxtsize + 1 - wordsCount) / wordsCount;

                        System.out.println("the number of words in " + path + " is : " + wordsCount);
                        System.out.println("average size of words in " + path + " is : " + avrgWordLength);


                    } catch (IOException e) {
                        logger.log(Level.WARNING, "IO EXCEPTION");
                    }
                }
            };
            ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(4);
            scheduledExecutorService.scheduleAtFixedRate(runnable, 1, 5, TimeUnit.SECONDS);


        }


    }
}




