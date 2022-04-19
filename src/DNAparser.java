import java.awt.*;
import  java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * 
 * @author 
 * @version 
 */
public class DNAparser {

    private File com;
    private MemoryManager memory;

    /**
     * @param c    file
     * @param m    memory
     * @param size size
     * @throws IOException will throw
     */
    public DNAparser(File c, String m, int size) throws IOException {
        com = c;
        memory = new MemoryManager(m, size);
    }

    /**
     * @return the memory manager
     */
    public MemoryManager getMemory() {
        return memory;
    }

    /**
     * Parses the file
     *
     * @return if the parsing is complete
     * @throws IOException
     */
    public boolean parse() throws IOException {

        // opens file and creates necessary variables
        boolean done = false;
        FileInputStream file = new FileInputStream(this.com);
        Scanner scnr = new Scanner(file);
        Scanner argParser;
        String input;
        ArrayList<String> args = new ArrayList<>();
        Queue<String> argQ = new LinkedList<>();

        // goes through file while there is another line
        while (scnr.hasNext()) {
            String methodCall = "";
            String argLine = scnr.nextLine(); //takes in first line from arg file
            argParser = new Scanner(argLine); //will parse through the line to get arguments

            // gets the instruction and stores it in methodCall
            while (argParser.hasNext()) {
                input = argParser.next();
                if (!input.equals(" ")) {
                    if (args.isEmpty()) {
                        methodCall = input;
                    }
                    args.add(input);
                    argQ.add(input);
                }
            }

            // depending on the instruction; do something
            switch (methodCall) {
                case "insert" :
                    System.out.println("insert");
                    argQ.remove();
                    //call insert       .insert(argQ.remove);
                    break;

                case "remove" :
                    System.out.println("Remove");
                    //call remove       .remove(argQ.remove);
                    break;

                case "search" :
                    System.out.println("Search");
                    break;

                case "print" :
                    System.out.println("Print");
                    break;

            }
            System.out.println("SIZE : " + args.size());
        }
    return done;

    }
}

