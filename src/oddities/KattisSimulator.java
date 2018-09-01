package oddities;

import java.io.*;
import java.util.HashMap;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/**
 * @author Torstein Strømme
 */
public class KattisSimulator {
    HashMap<String, StringBuilder> indata = new HashMap<>();
    HashMap<String, StringBuilder> utdata = new HashMap<>();

    public static void main(String[] args) throws IOException {
        KattisSimulator sim = new KattisSimulator();

        ///////////////////////////////////////////////////
        /////////////  EDIT BELOW IF NEEDED ///////////////
        ///////////////////////////////////////////////////

        sim.runZipTests("src/oddities/samples.zip");

        ///////////////////////////////////////////////////
        /////////////  EDIT ABOVE IF NEEDED ///////////////
        ///////////////////////////////////////////////////


    }

    private void runTests() {
        InputStream orgIn = System.in;
        PrintStream orgOut = System.out;

        for (String f : indata.keySet()) {
            System.out.printf("Testing %s... ", f);
            StringBuilder data = indata.get(f);
            System.setIn(new ByteArrayInputStream(data.toString().getBytes()));
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            System.setOut(new PrintStream(out));

            ///////////////////////////////////////////////////
            /////////////  EDIT BELOW IF NEEDED ///////////////
            ///////////////////////////////////////////////////

            Oddities.main(new String[0]);

            ///////////////////////////////////////////////////
            /////////////  EDIT ABOVE IF NEEDED ///////////////
            ///////////////////////////////////////////////////

            String res = out.toString();

            System.setIn(orgIn);
            System.setOut(orgOut);

            if (res.equals(utdata.get(f).toString())) {
                System.out.println("identical!");
            }
            else {
                System.out.println("hmm, I found:");
                System.out.println(res);
                System.out.printf("but %s.ans is:\n", f);
                System.out.println(utdata.get(f));
            }

        }
    }

    private void runZipTests(String zipfile) throws IOException {
        loadZipFile(zipfile);
        runTests();
    }

    private void loadZipFile(String zipfile) throws IOException {
        ZipInputStream zis = new ZipInputStream(new FileInputStream(zipfile));
        byte[] buffer = new byte[1024];
        ZipEntry zipEntry = zis.getNextEntry();

        while (zipEntry != null) {
            System.out.printf("Loading %s...", zipEntry.getName());

            StringBuilder s = new StringBuilder();
            int len;
            while ((len = zis.read(buffer)) > 0) {
                s.append(new String(buffer, 0, len));
            }

            if (zipEntry.getName().matches(".*\\.in$")) {
                indata.put(zipEntry.getName().substring(0, zipEntry.getName().length()-3), s);
            }
            else if (zipEntry.getName().matches(".*\\.ans$")) {
                utdata.put(zipEntry.getName().substring(0, zipEntry.getName().length()-4), s);
            }

            System.out.println("done!");
            zipEntry = zis.getNextEntry();
        }
    }
}
