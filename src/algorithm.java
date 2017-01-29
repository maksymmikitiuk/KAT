import java.util.BitSet;

public class algorithm {

    char Msg[] = new char[256];

    public algorithm() {
    }

    public char[] Hash(int hashbitlen, int msglen, Object o){
        Msg = "abcde".toCharArray();
        ZerroFill(160, 2);
        return Msg;
    }

    public int ZerroFill(int value, int bitlen){
        String repeated_1 = new String(new char[bitlen]).replace("\0", "1");
        String repeated_0 = new String(new char[8-bitlen]).replace("\0", "0");
        String mask = repeated_1 + repeated_0;
        int m = Integer.parseInt(mask, 2);
        System.out.println("m: " + m);
        int masked_value = value & m;
        System.out.println("masked_value: " + masked_value);
        return value;
    }

}
