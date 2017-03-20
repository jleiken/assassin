package ibrandomizer;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.io.PrintWriter;

public class Driver {
    public static void main(String[] args) 
    {
        ArrayList<String> students = new ArrayList<String>();
        try
        {
            BufferedReader bf = new BufferedReader(new FileReader("unames.txt"));
            while(bf.ready())
            {
                students.add(bf.readLine());
            }
            bf.close();
        }catch(Exception e){System.out.println(e);}
        
        System.out.println("There are " + students.size() + " students\n");
        ArrayList<String> hitlist = new ArrayList<String>();
        ArrayList<String> nums = new ArrayList<String>();
        while(!students.isEmpty())
        {
            int currS = (int)(Math.random()*students.size());
            int hitIndex = (int)(Math.random()*999)+1;
            String hitStr = ((Integer)hitIndex).toString();
            if(hitIndex < 10)
                hitStr = "00" + ((Integer)hitIndex).toString();
            else if(hitIndex < 100)
                hitStr = "0" + ((Integer)hitIndex).toString();
            while(!nums.isEmpty() && nums.contains(hitStr))
            {
                hitIndex = (int)(Math.random()*999)+1;
                hitStr = ((Integer)hitIndex).toString();
                if(hitIndex < 10)
                    hitStr = "00" + ((Integer)hitIndex).toString();
                else if(hitIndex < 100)
                    hitStr = "0" + ((Integer)hitIndex).toString();
            }
            nums.add(hitStr);
            String student = students.get(currS);
            student = student.replace(" #", ",");
            String stud = student + "," + hitStr + " \\\\\\";
            System.out.println(stud);
            hitlist.add(stud);
            students.remove(currS);
        }
        String hit = hitlist.get(0);
        hitlist.add(hit);
        try{
            PrintWriter pw = new PrintWriter("hitlist.txt");
            for(String str : hitlist)
                pw.write(str + System.lineSeparator());
            pw.close();
        }catch(Exception e){System.out.println(e);}
    }
}
