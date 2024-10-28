package androidx.car.app.versioning;

import j$.util.Objects;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public final class CarAppApiLevels {
    private static final String CAR_API_LEVEL_FILE = "car-app-api.level";
    public static final int LEVEL_1 = 1;
    public static final int LEVEL_2 = 2;
    public static final int LEVEL_3 = 3;
    public static final int LEVEL_4 = 4;
    public static final int LEVEL_5 = 5;
    public static final int LEVEL_6 = 6;
    public static final int UNKNOWN = 0;

    public static int getOldest() {
        return 1;
    }

    public static boolean isValid(int i) {
        return i >= getOldest() && i <= getLatest();
    }

    public static int getLatest() {
        InputStream resourceAsStream = ((ClassLoader) Objects.requireNonNull(CarAppApiLevels.class.getClassLoader())).getResourceAsStream(CAR_API_LEVEL_FILE);
        if (resourceAsStream != null) {
            try {
                String readLine = new BufferedReader(new InputStreamReader(resourceAsStream)).readLine();
                int parseInt = Integer.parseInt(readLine);
                if (parseInt >= 1 && parseInt <= 6) {
                    return parseInt;
                }
                throw new IllegalStateException("Unrecognized Car API level: " + readLine);
            } catch (IOException unused) {
                throw new IllegalStateException("Unable to read Car API level file");
            }
        } else {
            throw new IllegalStateException(String.format("Car API level file %s not found", new Object[]{CAR_API_LEVEL_FILE}));
        }
    }

    private CarAppApiLevels() {
    }
}
