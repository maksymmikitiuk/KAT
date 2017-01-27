public enum STATUS_CODES {
    KAT_SUCCESS(0),
    KAT_FILE_OPEN_ERROR(1),
    KAT_HEADER_ERROR(2),
    KAT_DATA_ERROR(3),
    KAT_HASH_ERROR(4);

    private final int code;

    STATUS_CODES(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
