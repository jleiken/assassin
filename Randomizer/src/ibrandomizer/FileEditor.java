package ibrandomizer;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;

public class FileEditor {
    public void main() {
        try{
            BufferedReader bf = new BufferedReader(new FileReader("names.txt"));
            PrintWriter pw = new PrintWriter("unames.txt");
            while(bf.ready())
            {
                String line = bf.readLine();
                if(line.contains("\""))
                    line.replaceAll("\"", "");
                line = line.substring(0,line.indexOf("<"));
                pw.write(line);
                pw.print(System.lineSeparator());
            }
            bf.close();
            pw.close();
        }catch(Exception e){}
    }
}
