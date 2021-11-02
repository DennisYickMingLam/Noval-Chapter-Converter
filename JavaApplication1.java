
//import java.io.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JavaApplication1 {

   public static void main(String args[]) throws IOException 
   {  
        String outputFolder = "C:\\Novel\\output";
        String inputFolder = "C:\\Novel\\input"; 
       
        String inputFileName = "Game_Of_Thrones_vol_3";
        String outputFileName = "exampleOutputFile";
        
        String outputFileNameWithExtension = outputFileName + ".txt";
        String inputFileNameWithExtension = inputFileName + ".txt";
        
        String cPattern = "vol_3";
        String[] nameTags = {   "00.",
                                "01.", 
                                "02.", 
                                "03.", 
                                "04.", //"skip", "skip",
                                "05.", //"skip", "skip","skip", "skip", 
                                "06.", //"skip","skip","skip",
                                "07.", //"skip",
                                "08.", 
                                "09.", 
                                "10.",
                                "11.", 
                                "12.", //"skip",
                                "13.", 
                                "14.", "15.", 
                                "16.", "17.", "18.", "19.", //"skip",
                                "20.", "21.", "22.", "23.",
                                "24.", "25.", "26.", "27.",
                                "28.", "29.", "30.", "31.",
                                "32.", "33.", "34.", "35.",
                                "36.", "37.", "38.", "39.",
                                "40.", "41.", "42.", "43.",
                                "44.", "45.", "46.", "47.",
                                "48.", "49.", "50.", "51.", 
                                "52.", "53.", "54.", "55.", 
                                "56.", "57.", "58.", "59.", 
                                "60.", "61.", "62.", "63.", 
                                "64.", "65.", "66.", "67.",
                                "68.", "69.", "70.", "71.",
                                "72.", "73.", "74.", "75.",
                                "76.", "77.", "78.", "79.",
                                "80.", "81.", "82.", "83.",
                                "84.", "85.", "86.", "87.",
                                "88.", "89.", "90.", "91.",
                                "92.", "93.", "94.", "95.",
                                "96.", "97.", "98.", "99."
                            };
        
        String outputFolderPath = "";
        String inputFolderPath = "";
        Scanner userInput = new Scanner(System.in);
        
        while(true) {
            System.out.println("InputFileName: " + inputFileName);
            System.out.println("outputFileName: " + outputFileName);
            System.out.println("cPattern: " + cPattern);
            System.out.println("");
            System.out.println("Please select which Path you want to use: ");
            System.out.println("0.Exit");
            System.out.println("1.Test");
            System.out.println("2.dropbox");
            String pathNum = userInput.nextLine();
            if ("0".equals(pathNum)) {
                System.exit(0);
            } else if ("1".equals(pathNum)) {
                //C:\Users\Dennis\Documents\NetBeansProjects\JavaApplication1\Test
                outputFolderPath = "Test";
                inputFolderPath = "Test";
                System.out.println(inputFolderPath);
                break;
            } else if ("2".equals(pathNum)) {
                outputFolderPath = outputFolder;
                inputFolderPath = inputFolder;
                System.out.println(inputFolderPath);
                break;
            } else {
                System.out.println("Debug: Option don't exist");
            }    
        }
        
        
        
        
        while(true) {
            System.out.println("");
            System.out.println("Please select which function you want to run: ");
            System.out.println("0.Exit");
            System.out.println("1.printOutputFile");
            System.out.println("2.copyAndPaste");
            System.out.println("3.deleteOutputFile");
            System.out.println("4.testrenameOutputFiles");
            System.out.println("5.renameOutputFiles");
            System.out.println("6.printOutputFileNames");
            System.out.println("7.deleteInputFile");
            String fnNum = userInput.nextLine();
            if ("0".equals(fnNum)) {
                break;
            } else if ("1".equals(fnNum)) {
                printOutputFile(outputFolderPath, outputFileNameWithExtension);
            } else if ("2".equals(fnNum)) {
                copyAndPaste(outputFolderPath, inputFolderPath, outputFileNameWithExtension, inputFileNameWithExtension);
            } else if ("3".equals(fnNum)) {
                deleteOutputFile(outputFolderPath, outputFileNameWithExtension);
            } else if ("4".equals(fnNum)) {
                testrenameOutputFiles(outputFolderPath, inputFolderPath, outputFileNameWithExtension, inputFileNameWithExtension, cPattern, nameTags);
            } else if ("5".equals(fnNum)) {
                renameOutputFiles(outputFolderPath, inputFolderPath, outputFileNameWithExtension, inputFileNameWithExtension, cPattern, nameTags);
            } else if ("6".equals(fnNum)) {
                printOutputFileNames(outputFolderPath);
            } else if ("7".equals(fnNum)) {
                System.out.println("Are you sure?(y/n)");
                String ruSure = userInput.nextLine();
                if ("y".equalsIgnoreCase(ruSure)) {
                    deleteInputFile(inputFolderPath, inputFileNameWithExtension);
                }
            } else {
                System.out.println("Debug: Function don't exist");
            }
        }
    }
   
   static void printOutputFile(String outPath, String outFile) {
        try {
            File f = new File(outPath + "\\" + outFile);
            BufferedReader b = new BufferedReader(new FileReader(f));
            String readLine = "";
            System.out.print("Debug: Reading file using Buffered Reader\n");
            while ((readLine = b.readLine()) != null) {
                System.out.println(readLine);
            }
            b.close();
        }  
        catch (IOException e) {
            System.out.println(e);
            //e.printStackTrace();
        }
   }
   
   static void copyAndPaste(String outPath, String inPath, String outFile, String inFile) {
        FileInputStream in = null;
        FileOutputStream out = null;
        try {
            try {
                in = new FileInputStream(inPath + "\\" + inFile);
                out = new FileOutputStream(outPath + "\\" + outFile);

                int c;
                while ((c = in.read()) != -1) {
                    out.write(c);
                }
            } finally {
                if (in != null) {
                   in.close();
                   System.out.println("Debug: input file susccessfully read\n");
                }
                if (out != null) {
                   out.close();
                   System.out.println("Debug: Output file susccessfully created\n");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
   }
   
   static void deleteOutputFile(String outPath, String outFile) {
       try {
            Path path = Paths.get(outPath + "\\" + outFile);
            System.out.print("Debug: File exist in path for deletion: ");
            System.out.println(Files.deleteIfExists(path));
            System.out.println("");
       } catch (IOException e) {
            e.printStackTrace();
       }
   }
   
   static void testrenameOutputFiles(String outPath, String inPath, String outFile, String inFile, String p, String[] nTags) {
       try {
       
            File f = new File(inPath +"\\"+ inFile);
            BufferedReader b = new BufferedReader(new FileReader(f));

       
            FileWriter fw = new FileWriter(outPath + "\\" + outFile);

            String readLine = "";
            int numTag = 0;
            boolean underTagFlag = false;
            String tempLine = "";
            boolean firstCharFlag = true;
            
            Pattern pattern = Pattern.compile(p, Pattern.UNICODE_CASE);
            System.out.print("Accepted p: ");
            System.out.println(p);
            System.out.print("Pattern: ");
            System.out.println(pattern);
            System.out.println("");

            while ((readLine = b.readLine()) != null) {
                if (firstCharFlag) {
                    readLine = readLine.substring(1);
                    firstCharFlag = false;
                }
                Matcher matcher = pattern.matcher(readLine);    
                if (matcher.find()){
                    if (numTag >= nTags.length) {
                        System.out.println("Debug: The number of patterns found is greater than Tag number, " + numTag);
                        underTagFlag = true;
                    } else {
                        System.out.println("Debug: found match pattern");
                        if (nTags[numTag].equals("skip")) {
                            System.out.println("Skip tag");
                        } else {
                            //fw.close();
                        
                            tempLine = readLine.replaceFirst(p, "").trim();
                            //tempLine = tempLine.replaceFirst("^[0-9]+", "").trim();
                            tempLine = tempLine.replaceFirst("^\\.", "").trim();

                            String newFileName = nTags[numTag] + tempLine + ".txt";
                            //fw = new FileWriter(outPath +"\\"+ newFileName);
                            fw.write(newFileName + "\n");
                        }
                        
                    }
                    numTag+=1;
                }
                
                //fw.write(readLine + "\n");
                if (!underTagFlag) {
                    System.out.println(readLine);
                }
                
            }
            b.close();
            fw.close();
            System.out.println("Debug: Both file is closing\n");
            
       } catch (IOException e) {
           e.printStackTrace();
       }
   }
   
   static void renameOutputFiles(String outPath, String inPath, String outFile, String inFile, String p, String[] nTags) {
       try {
       
            File f = new File(inPath +"\\"+ inFile);
            BufferedReader b = new BufferedReader(new FileReader(f));

       
            FileWriter fw = new FileWriter(outPath + "\\" + outFile);

            String readLine = "";
            int numTag = 0;
            boolean underTagFlag = false;
            String tempLine = "";
            boolean firstCharFlag = true;
            
            Pattern pattern = Pattern.compile(p, Pattern.UNICODE_CASE);
            System.out.print("Accepted p: ");
            System.out.println(p);
            System.out.print("Pattern: ");
            System.out.println(pattern);
            System.out.println("");

            while ((readLine = b.readLine()) != null) {
                if (firstCharFlag) {
                    readLine = readLine.substring(1);
                    firstCharFlag = false;
                }
                Matcher matcher = pattern.matcher(readLine);    
                if (matcher.find()){
                    if (numTag >= nTags.length) {
                        System.out.println("Debug: The number of patterns found is greater than Tag number, " + numTag);
                        underTagFlag = true;
                    } else {
                        System.out.println("Debug: found match pattern");
                        if (nTags[numTag].equals("skip")) {
                            System.out.println("Skip tag");
                        } else {
                            fw.close();
                        
                            tempLine = readLine.replaceFirst(p, "").trim();
                            //tempLine = tempLine.replaceFirst("^[0-9]+", "").trim();
                            tempLine = tempLine.replaceFirst("^\\.", "").trim();

                            String newFileName = nTags[numTag] + tempLine + ".txt";
                            fw = new FileWriter(outPath +"\\"+ newFileName);
                        }
                        
                    }
                    numTag+=1;
                }
                
                fw.write(readLine + "\n");
                if (!underTagFlag) {
                    System.out.println(readLine);
                }
            }
            b.close();
            fw.close();
            System.out.println("Debug: Both file is closing\n");
            
       } catch (IOException e) {
           e.printStackTrace();
       }
   }
   
   static void printOutputFileNames(String outPath) {
       File f = new File(outPath);
       File[] listOfFiles = f.listFiles();
       for (File listOfFile : listOfFiles) {
           System.out.println(listOfFile);
       }
       System.out.println("");
   }
   
   static void deleteInputFile(String inPath, String inFile) {
       File file = new File(inPath + "\\" + inFile);
       if (file.delete()) {
           System.out.println("Deleted: " + inFile);
       } else {
           System.out.println("File doesn't exist");
       }
       
   }
}