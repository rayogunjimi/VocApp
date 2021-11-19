package vocapp.beta;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class VocAppBeta {


    static String location = null;
    static String[][] aVocab;
    static String[] words;
    static File file;

    public static void main(String[] args) throws IOException, NullPointerException {

        location = System.getProperty("user.dir");
        location = location.substring(0, location.length() - 8);

        Stuff myListFile = new Stuff();


        myListFile.readListFile();
        myListFile.definitions();




        for (int ListIndex = 0; ListIndex < words.length; ListIndex++) {
            for (int DefIndex = 0; DefIndex < aVocab.length; DefIndex++) {
                if (words[ListIndex].equals(aVocab[DefIndex][0])) {
                    myListFile.makeDefinedFile(aVocab[DefIndex][0], aVocab[DefIndex][1]);
                    break;
                }
            }
        }


    }





    static class Stuff {



        public void readListFile() throws FileNotFoundException, IOException {

            Scanner lineScan = null;
            int numOfLines = 0;
            String currentLine = null;

            File listFile = new File(location + "\\ListFile\\List.txt");

            lineScan = new Scanner(listFile);

            while (lineScan.hasNext()) {
                numOfLines++;
                currentLine = lineScan.nextLine();
            }

            lineScan.close();

            words = new String[numOfLines];


            lineScan = new Scanner(listFile);

            for (int i = 0; i < numOfLines; i++) {

                words[i] = lineScan.nextLine();

            }


        }





        public void definitions() throws FileNotFoundException {


            Scanner lineScan = null;
            int numOfLines = 0;
            String currentLine = null;

            File sourceFile = new File(location + "\\SourceFile\\Source.txt");

            lineScan = new Scanner(sourceFile);

            while (lineScan.hasNext()) {
                numOfLines++;
                currentLine = lineScan.nextLine();
            }

            lineScan.close();
            lineScan = new Scanner(sourceFile);
            aVocab = new String[numOfLines][2];

            for (int i = 0; i < numOfLines; i++) {
                currentLine = lineScan.nextLine();
                aVocab[i][1] = currentLine.substring(currentLine.indexOf("-") + 2, currentLine.length());
                //aVocab[][] = the rest of the string
            }



            /*    
            aVocab = new String[3][2];
    
            aVocab[0][0] = "aword1";
            aVocab[0][1] = "something1";
    
            aVocab[1][0] = "aword2";
            aVocab[1][1] = "something2";
    
            aVocab[2][0] = "aword3";
            aVocab[2][1] = "something3";
            */




        }



        public void makeDefinedFile(String word, String definition) throws IOException {

            file = new File(location + "\\DefinedFile\\Defined.txt"); // if file exists add number to its name
            file.createNewFile();
            try (PrintWriter pw = new PrintWriter(new FileOutputStream(file, true))) {
                pw.println(word + "  -  " + definition);
                pw.print("\n");
            }

        }









    }

}
