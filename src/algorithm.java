import org.apache.log4j.*;

public class algorithm {

    private static final Logger log = Logger.getLogger(algorithm.class.getName());
    int newMsg[] = new int[256];

    public algorithm() {

    }

    public int[] Hash(int[] msg, int hashbitlen, int msglen, Object o){
        int wholeByte = msglen / 8;
        int bitRemain = msglen % 8;
//        System.out.println("\n\nhashbitlen: " + hashbitlen + "  msglen: " + msglen + "  wholeByte: " + wholeByte + "  bitRemain: " + bitRemain);
        log.info("\n\nhashbitlen: " + hashbitlen + "  msglen: " + msglen + "  wholeByte: " + wholeByte + "  bitRemain: " + bitRemain + "\n");

        System.arraycopy(msg, 0, newMsg, 0, wholeByte);
        newMsg[wholeByte] = ZerroFill(msg[wholeByte], bitRemain);
//        System.out.print("newMsg[wholeByte]: " + newMsg[wholeByte]);
//        System.out.print("\nOriginal Array: ");
//        log.fine("\nOriginal Array: ");
//        for (int x : msg) {
//            System.out.print(x + " ");
//        }
//        System.out.println("\nNew Array:      ");
        for (int y : newMsg) {
//            System.out.print(y + " ");
            log.info(y + " ");
        }
        for (int i = 0; i < 256; i++) {
            if (newMsg[i] - msg[i] < 0) System.err.print(newMsg[i] - msg[i] + " ");
        }
//        System.out.println("\n");
//        try {
//            Thread.sleep(500);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        return msg;
    }

    public int ZerroFill(int value, int bitlen){
        String repeated_1 = new String(new char[bitlen]).replace("\0", "1");
        String repeated_0 = new String(new char[8-bitlen]).replace("\0", "0");
        String mask = repeated_1 + repeated_0;
        int m = Integer.parseInt(mask, 2);
//        System.out.println("mask: "  + mask + "  m: " + m);
        int masked_value = value & m;
//        System.out.println("masked_value: " + masked_value);
        return masked_value;
    }

}
