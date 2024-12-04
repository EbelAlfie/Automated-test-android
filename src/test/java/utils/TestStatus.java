package utils;

public enum TestStatus {
    FAILED("failed"),
    PASSED("passed");

    public final String value;
    TestStatus(String value) {this.value = value;}

    public static TestStatus getTestStatus(boolean status) {
        if (status) return PASSED;
        else return FAILED;
    }
}