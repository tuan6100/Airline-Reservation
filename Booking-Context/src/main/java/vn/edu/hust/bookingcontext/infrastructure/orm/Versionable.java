package vn.edu.hust.bookingmanagement.infrastructure.orm;

public interface Versionable <V> {

    V getVersion();

    void setVersion(V version);

    default void processOverflow() {
        if (this.getVersion() instanceof Number versionNumber) {
            if (versionNumber.longValue() >= Integer.MAX_VALUE - 0xffff) {
                this.setVersion((V) Integer.valueOf(0));
            }
        }
    }
}
