public class kat {
    private static final int MAX_MARKER_LEN = 50;
    private static final int SUBMITTER_INFO_LEN = 128;

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
        char Msg[] = new char[256];
        char MD[] = new char[64];
        int msgbytelen;
        boolean done;
        ioFile io = new ioFile();
        String fileName = String.format("ShortMsgKAT_%d.txt", hashbitlen);

        if (!io.setPathRead("D:\\Java\\KAT-master\\input\\ShortMsgKAT.txt")) {
            return STATUS_CODES.KAT_FILE_OPEN_ERROR;
        }

        if (!io.setPathWrite("D:\\Java\\KAT-master\\output\\" + fileName)) {
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
            System.out.println("enShortMsg: Couldn't read Principal Submitter\n");
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

            System.out.println("MSG: " + Msg.toString());

            //HASH
//            MD = algorithm.Hash(hashbitlen, Integer.valueOf(line), this);

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
        char Msg[] = new char[4288];
        char MD[] = new char[64];
        int msgbytelen;
        boolean done;
        ioFile io = new ioFile();
        String fileName = String.format("LongMsgKAT_%d.txt", hashbitlen);

        if (!io.setPathRead("D:\\Java\\KAT-master\\input\\LongMsgKAT.txt")) {
            return STATUS_CODES.KAT_FILE_OPEN_ERROR;
        }

        if (!io.setPathWrite("D:\\Java\\KAT-master\\output\\" + fileName)) {
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

    public static void main(String[] args) {
        System.err.println("=== Start ====================================");
        new kat();
        System.err.println("=== Done =====================================");
    }
}
