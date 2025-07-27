package vn.edu.hust.common.domain.util;

import java.security.SecureRandom;
import java.util.UUID;
import com.fasterxml.uuid.Generators;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

public final class UUIDGenerator {

    private UUIDGenerator() {}

    public static UUID generate(@NonNull int version, @Nullable String data) {
        return switch (version) {
            case 1 -> Generators.timeBasedGenerator().generate();
            case 3, 5 -> Generators.nameBasedGenerator().generate(data);
            case 4 -> Generators.randomBasedGenerator().generate();
            case 6 -> Generators.timeBasedReorderedGenerator().generate();
            case 7 -> Generators.timeBasedEpochRandomGenerator().generate();
            case 8 -> generateUUIDv8();
            default -> throw new IllegalStateException("Unexpected version: " + version);
        };
    }

    private static UUID generateUUIDv8() {
        var random = new SecureRandom();
        long timestamp = System.currentTimeMillis();
        int randomA = random.nextInt(0x1000);
        long randomB = random.nextLong() & 0x3FFFFFFFFFFFFFFFL;
        long mostSigBits = (timestamp << 16) | 0x8000L | randomA;
        long leastSigBits = 0x8000000000000000L | randomB;
        return new UUID(mostSigBits, leastSigBits);
    }
}
