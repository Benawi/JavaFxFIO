package seriallization;

import java.io.*;

public class deseialization {

    public deseialization() throws Exception {
        try (ObjectInputStream inObj = new ObjectInputStream(
                new FileInputStream("Bena.txt"))) {
            Student stud1 = (Student) inObj.readObject();
            System.out.println(stud1.id + " " + stud1.name);
        }
    }

}
