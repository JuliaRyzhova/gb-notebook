package notebook.util.connector.impl;

import notebook.util.connector.DBConnectable;
import notebook.util.connector.TXTConnectable;

import java.io.File;

public class Connector implements TXTConnectable, DBConnectable {
    public static final String DB_PATH = "db.txt";

    public static void connectTxt() {
        try {
            File db = new File(DB_PATH);
            if (db.createNewFile()) {
                System.out.println("TXT-file created");
            } else {
                System.out.println("TXT-file already exists");
            }
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    public static void connectDB() {

    }
}
