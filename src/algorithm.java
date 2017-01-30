import java.util.BitSet;

public class algorithm {

//    int Msg[] = new int[256];

    public algorithm() {
    }

    public int[] Hash(int[] msg, int hashbitlen, int msglen, Object o){
        ZerroFill(160, 2);
        System.out.println("hashbitlen: " + hashbitlen + "  msglen: " + msglen);
        int wholeByte = msglen / 8;
        int bitRemain = msglen % 8;
        System.out.println("wholeByte: " + wholeByte + "  bitRemain: " + bitRemain);
//        for (int x: msg) {
//            System.out.print(x + " ");
//        }
//        System.out.println("\n");
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return msg;
    }

    public int ZerroFill(int value, int bitlen){
        String repeated_1 = new String(new char[bitlen]).replace("\0", "1");
        String repeated_0 = new String(new char[8-bitlen]).replace("\0", "0");
        String mask = repeated_1 + repeated_0;
        int m = Integer.parseInt(mask, 2);
//        System.out.println("m: " + m);
        int masked_value = value & m;
//        System.out.println("masked_value: " + masked_value);
        return masked_value;
    }

}
