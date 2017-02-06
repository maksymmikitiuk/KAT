import java.util.Arrays;

public class kat {
    public kat() {
        STATUS_CODES ret_val;
        int bitlens[] = {224, 256, 384, 512};

        if ((ret_val = genShortMsg(bitlens[1])) != STATUS_CODES.KAT_SUCCESS)
            System.out.println(ret_val + " <" + ret_val.getCode() + ">");
//        if ((ret_val = genLongMsg(bitlens[1])) != STATUS_CODES.KAT_SUCCESS)
//            System.out.println(ret_val + " <" + ret_val.getCode() + ">");
//        if ((ret_val = genExtremelyLongMsg(bitlens[1])) != STATUS_CODES.KAT_SUCCESS)
//            System.out.println(ret_val + " <" + ret_val.getCode() + ">");
//        if ((ret_val = genMonteCarlo(bitlens[1])) != STATUS_CODES.KAT_SUCCESS)
//            System.out.println(ret_val + " <" + ret_val.getCode() + ">");
    }

    private STATUS_CODES genShortMsg(int hashbitlen) {
        algorithm algorithm = new algorithm();
        String line = null;
        int Msg[] = new int[256];
        int MD[] = new int[64];
        int msgbytelen;
        boolean done;
        ioFile io = new ioFile();
        String fileName = String.format("ShortMsgKAT_%d.txt", hashbitlen);

        if (!io.setPathRead("F:\\Java\\KAT-master\\input\\ShortMsgKAT.txt")) {
            return STATUS_CODES.KAT_FILE_OPEN_ERROR;
        }

        if (!io.setPathWrite("F:\\Java\\KAT-master\\output\\" + fileName)) {
            return STATUS_CODES.KAT_FILE_OPEN_ERROR;
        }

        io.writeToFile("# %s\n", fileName);
        if ((line = io.find("# Algorithm Name:")) != null) {
            io.writeToFile("# Algorithm Name:%s\n", line);
        } else {
            System.out.println("genShortMsg: Couldn't read Algorithm Name\n");
            return STATUS_CODES.KAT_HEADER_ERROR;
        }

        if ((line = io.find("# Principal Submitter:")) != null) {
            io.writeToFile("# Principal Submitter:%s\n", line);
        } else {
            System.out.println("genShortMsg: Couldn't read Principal Submitter\n");
            return STATUS_CODES.KAT_HEADER_ERROR;
        }

        done = false;
        do {
            if ((line = io.find("Len = ")) == null) {
                done = true;
                break;
            }

            msgbytelen = (Integer.parseInt(line) + 7) / 8;

            Msg = io.ReadHEX(256, msgbytelen, "Msg = ");
            if (Msg.length == 0 || Msg == null) {
                System.out.println("ERROR: unable to read 'Msg' from <ShortMsgKAT.txt>");
                return STATUS_CODES.KAT_DATA_ERROR;
            }

//            for (int x: Msg) {
//                System.out.print(x + " ");
//            }
//            System.out.println("\n");
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }

            //HASH
            MD = algorithm.Hash(hashbitlen, Integer.valueOf(line), this);

            io.writeToFile("\nLen = %s\n", line);
            io.writeBToFile("Msg = ", Msg, msgbytelen);
            io.writeBToFile("MD = ", MD, hashbitlen / 8);
        } while (!done);
        System.out.println(String.format("finished ShortMsgKAT for <%d>\n", hashbitlen));

        io.closeFiles();

        return STATUS_CODES.KAT_SUCCESS;
    }

    private STATUS_CODES genLongMsg(int hashbitlen) {
        algorithm algorithm = new algorithm();
        String line = null;
        int Msg[] = new int[4288];
        int MD[] = new int[64];
        int msgbytelen;
        boolean done;
        ioFile io = new ioFile();
        String fileName = String.format("LongMsgKAT_%d.txt", hashbitlen);

        if (!io.setPathRead("F:\\Java\\KAT-master\\input\\LongMsgKAT.txt")) {
            return STATUS_CODES.KAT_FILE_OPEN_ERROR;
        }

        if (!io.setPathWrite("F:\\Java\\KAT-master\\output\\" + fileName)) {
            return STATUS_CODES.KAT_FILE_OPEN_ERROR;
        }

        io.writeToFile("# %s\n", fileName);
        if ((line = io.find("# Algorithm Name:")) != null) {
            io.writeToFile("# Algorithm Name:%s\n", line);
        } else {
            System.out.println("genLongMsg: Couldn't read Algorithm Name\n");
            return STATUS_CODES.KAT_HEADER_ERROR;
        }

        if ((line = io.find("# Principal Submitter:")) != null) {
            io.writeToFile("# Principal Submitter:%s\n", line);
        } else {
            System.out.println("genLongMsg: Couldn't read Principal Submitter\n");
            return STATUS_CODES.KAT_HEADER_ERROR;
        }

        done = false;
        do {
            if ((line = io.find("Len = ")) == null) {
                done = true;
                break;
            }
            msgbytelen = (Integer.parseInt(line) + 7) / 8;

            Msg = io.ReadHEX(4288, msgbytelen, "Msg = ");
            if (Msg.length == 0 || Msg == null) {
                System.out.println("ERROR: unable to read 'Msg' from <ShortMsgKAT.txt>");
                return STATUS_CODES.KAT_DATA_ERROR;
            }

            //HASH
            algorithm.Hash(hashbitlen, Integer.valueOf(line), this);

            io.writeToFile("\nLen = %s\n", line);
            io.writeBToFile("Msg = ", Msg, msgbytelen);
            io.writeBToFile("MD = ", MD, hashbitlen / 8);
        } while (!done);
        System.out.println(String.format("finished ShortMsgKAT for <%d>\n", hashbitlen));

        io.closeFiles();

        return STATUS_CODES.KAT_SUCCESS;
    }

    private STATUS_CODES genExtremelyLongMsg(int hashbitlen) {
        algorithm algorithm = new algorithm();
        String line = null, Text = null;
        int Msg[] = new int[4288];
        int MD[] = new int[64];
        int repeat = 0;
        STATUS_CODES retval, state = null;
        ioFile io = new ioFile();
        String fileName = String.format("ExtremelyLongMsgKAT_%d.txt", hashbitlen);

        if (!io.setPathRead("F:\\Java\\KAT-master\\input\\ExtremelyLongMsgKAT.txt")) {
            return STATUS_CODES.KAT_FILE_OPEN_ERROR;
        }

        if (!io.setPathWrite("F:\\Java\\KAT-master\\output\\" + fileName)) {
            return STATUS_CODES.KAT_FILE_OPEN_ERROR;
        }

        io.writeToFile("# %s\n", fileName);
        if ((line = io.find("# Algorithm Name:")) != null) {
            io.writeToFile("# Algorithm Name:%s\n", line);
        } else {
            System.out.println("genExtremelyLongMsg: Couldn't read Algorithm Name\n");
            return STATUS_CODES.KAT_HEADER_ERROR;
        }

        if ((line = io.find("# Principal Submitter:")) != null) {
            io.writeToFile("# Principal Submitter:%s\n", line);
        } else {
            System.out.println("genExtremelyLongMsg: Couldn't read Principal Submitter\n");
            return STATUS_CODES.KAT_HEADER_ERROR;
        }

        if ((repeat = Integer.parseInt(io.find("Repeat = "))) != 0) {
            System.out.println("ERROR: unable to read 'Repeat' from <ExtremelyLongMsgKAT.txt>");
            return STATUS_CODES.KAT_DATA_ERROR;
        }

        if ((repeat = Integer.parseInt(io.find("Text = "))) != 0) {
            System.out.println("ERROR: unable to read 'Text' from <ExtremelyLongMsgKAT.txt>");
            return STATUS_CODES.KAT_DATA_ERROR;
        }

        Text = Arrays.copyOf("abcdefghbcdefghicdefghijdefghijkefghijklfghijklmghijklmnhijklmno", 64);

        if ((retval = algorithm.Init(STATUS_CODES.KAT_DATA_ERROR, hashbitlen)) != STATUS_CODES.KAT_SUCCESS) {
            System.out.printf("Init  returned <%d> in genExtremelyLongMsg\n", retval.getCode());
            return STATUS_CODES.KAT_HASH_ERROR;
        }

        for (int i = 0; i < repeat; i++)
            if ((retval = algorithm.Update(state, Text, 512)) != STATUS_CODES.KAT_SUCCESS) {
                System.out.printf("Update returned <%d> in genExtremelyLongMsg\n", retval.getCode());
                return STATUS_CODES.KAT_HASH_ERROR;
            }

        if ((retval = algorithm.Final(state, MD)) != STATUS_CODES.KAT_SUCCESS) {
            System.out.printf("Final returned <%d> in genExtremelyLongMsg\n", retval.getCode());
            return STATUS_CODES.KAT_HASH_ERROR;
        }

        io.writeToFile("Repeat = %d\n", repeat);
        io.writeToFile("Text = %s\n", Text);
        io.writeBToFile("MD = ", MD, hashbitlen / 8);
        System.out.printf("finished ExtremelyLongMsgKAT for <%d>\n", hashbitlen);

        io.closeFiles();

        return STATUS_CODES.KAT_SUCCESS;
    }

    private STATUS_CODES genMonteCarlo(int hashbitlen){
        String line = null;
        int Msg[] = new int[128];
        int MD[] = new int[64];
        int Temp[] = new int[128];
        int Seed[] = new int[128];
        int bytelen;
        ioFile io = new ioFile();
        String fileName = String.format("MonteCarlo__%d.txt", hashbitlen);

        if (!io.setPathRead("F:\\Java\\KAT-master\\input\\MonteCarlo.txt")) {
            return STATUS_CODES.KAT_FILE_OPEN_ERROR;
        }

        if (!io.setPathWrite("F:\\Java\\KAT-master\\output\\" + fileName)) {
            return STATUS_CODES.KAT_FILE_OPEN_ERROR;
        }

        io.writeToFile("# %s\n", fileName);
        if ((line = io.find("# Algorithm Name:")) != null) {
            io.writeToFile("# Algorithm Name:%s\n", line);
        } else {
            System.out.println("genMonteCarlo: Couldn't read Algorithm Name\n");
            return STATUS_CODES.KAT_HEADER_ERROR;
        }

        if ((line = io.find("# Principal Submitter:")) != null) {
            io.writeToFile("# Principal Submitter:%s\n", line);
        } else {
            System.out.println("genMonteCarlo: Couldn't read Principal Submitter\n");
            return STATUS_CODES.KAT_HEADER_ERROR;
        }

        Seed = io.ReadHEX(128, 128, "Seed = ");
        if (Msg.length == 0 || Msg == null) {
            System.out.println("ERROR: unable to read 'Seed' from <MonteCarlo.txt>");
            return STATUS_CODES.KAT_DATA_ERROR;
        }

        bytelen = hashbitlen / 8;
        Msg = Arrays.copyOf(Seed, 128);
        io.writeBToFile("Seed = ", Seed, 128);
        for (int j=0; j<100; j++ ) {
            for (int i=0; i<1000; i++ ) {
                MD = algorithm.Hash(hashbitlen, 1024, this);
                Temp = Arrays.copyOf(Msg, 128 - bytelen);
                Msg = Arrays.copyOf(MD, bytelen);
                Msg+bytelen = Arrays.copyOf(Temp, 128 - bytelen);
            }
            io.writeToFile("\nj = %d\n", j);
            io.writeBToFile("MD = ", MD, bytelen);
        }

        System.out.println(String.format("finished MonteCarloKAT for <%d>\n", hashbitlen));

        io.closeFiles();

        return STATUS_CODES.KAT_SUCCESS;
    }

    public static void main(String[] args) {
        System.err.println("=== Start ===================================");
        new kat();
        System.err.println("=== Done ====================================");
    }
}
