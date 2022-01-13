package FileWriter;



import java.io.*;


public class fileWriter {


    public static void main(String[] args) throws FileNotFoundException, IOException {
        File fil = new File("txt1.txt");
        FileOutputStream fout = new FileOutputStream(fil);
        String str = "Hello,this is file lab demo1";
        byte b[] = str.getBytes();
        //fout.write(b);
        // fout.close();
        FileInputStream fi = new FileInputStream(fil);
        int i = 0;
        while ((i = fi.read()) != -1) {


            System.out.print((char) i);
        }
        FileOutputStream fout2 = new FileOutputStream("txt2.txt");
        ByteArrayOutputStream bye = new ByteArrayOutputStream();
        bye.write(b);
        bye.writeTo(fout);
        bye.writeTo(fout2);
        bye.flush();
        fi.close();
System.out.println("Success");


    }

}
