package seriallization;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

public class Serialization {
    Serialization() {
        try {
            Student stud = new Student(2022, "Regular cs");
            FileOutputStream fout = new FileOutputStream(
                    "Bena.txt");
            try (ObjectOutputStream obj
                         = new ObjectOutputStream(fout)) {
                obj.writeObject(stud);
                obj.flush();
            }
        } catch (Exception e) {
        }
        System.out.println("successssssss");
    }
}