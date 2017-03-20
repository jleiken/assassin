package ibrandomizer;

public class ROBOTOT {
    public static void main(String[] arg)
    {
        MyFrame fr = new MyFrame();
        while(fr.isValid()&&fr.isOpen())
        {
            fr.main();
        }
        System.exit(0);
    }
}