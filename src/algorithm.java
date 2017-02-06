public class algorithm {

    int Msg[] = new int[256];

    public algorithm() {
    }

    public int[] Hash(int hashbitlen, int msglen, Object o){
        ZerroFill(160, 2);
        return Msg;
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

    public STATUS_CODES Init(STATUS_CODES state, int hashbitlen){
        return STATUS_CODES.KAT_SUCCESS;
    }

    public STATUS_CODES Update(STATUS_CODES state, String Text, int len) {
        return state;
    }

    public STATUS_CODES Final(STATUS_CODES state, int[] md) {
        return state;
    }
}
