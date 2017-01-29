import java.io.*;
import java.util.Arrays;

public class ioFile {
    BufferedReader bufferedReader;
    BufferedWriter bufferedWriter;
    String pathRead, pathWrite;

    public static void main(String[] args) {
        //new ioFile("A:\\Java\\Java project\\hexTEST\\out\\production\\hexTEST\\ShortMsgKAT.txt", "").find("# Principal Submitter:");
    }

    public ioFile() {
    }

    public boolean setPathRead(String pathRead) {
        this.pathRead = pathRead;
        return openFileForReadFile();
    }

    public boolean setPathWrite(String pathWrite) {
        this.pathWrite = pathWrite;
        return openFileForWriteFile();
    }

    private boolean openFileForReadFile() {
        File file = new File(pathRead);
        try {
            bufferedReader = new BufferedReader(new FileReader(file));
            return true;
        } catch (FileNotFoundException e) {
            System.out.format("Couldn't open <%s> for read\n", file.getName());
            return false;
        }
    }

    private boolean openFileForWriteFile() {
        File file = new File(pathWrite);
        try {
            if (!file.exists()) {
                file.createNewFile();
            }

            FileWriter fw = new FileWriter(file);
            bufferedWriter = new BufferedWriter(fw);
            return true;
        } catch (IOException ioe) {
            System.out.format("Couldn't open <%s> for write\n", file.getName());
            return false;
        }
    }

    public String find(String tample) {
        String line;
        try {
            while ((line = bufferedReader.readLine()) != null) {
                if (line.indexOf(tample) != -1) {
                    return line.substring(tample.length());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public void writeToFile(String line, Object param) {
        try {
            bufferedWriter.write(String.format(line, param));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeBToFile(String line, int[] A, int L) {
        writeToFile("%s", line);

        for (int i = 0; i < L; i++)
            writeToFile("%02X", (byte) A[i]);

        if (L == 0)
            writeToFile("00", "");

        writeToFile("\n", "");
    }

    public int[] ReadHEX(int size, int Length, String str) {
        int A[] = new int[size];
        String line = null;
        int j = 0;

        if (Length == 0) {
            A[0] = 0x00;
            return A;
        }

        Arrays.fill(A, 0, Length, (char) 0x00);

        if ((line = find(str)) != null) {

            char lineToChar[] = line.trim().toCharArray();

            for (int i = 0; i < lineToChar.length; i += 2) {
                A[j] = Integer.parseInt("" + lineToChar[i] + lineToChar[i + 1], 16);
                j++;
            }
        }

        return A;
    }

    public boolean closeFiles() {
        try {
            if (bufferedWriter != null)
                bufferedWriter.close();
            if (bufferedReader != null)
                bufferedReader.close();
            return true;
        } catch (Exception ex) {
            System.out.println("Error in closing" + ex);
            return false;
        }
    }
}
