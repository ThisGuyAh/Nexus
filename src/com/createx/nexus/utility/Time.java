package com.createx.nexus.utility;

/**
 * {@code Time} defines methods used to retrieve time (in various measurements) and control its attributes.
 *
 * @author Christopher Ruley
 */
public class Time {

    /**
     * Used for syncing calculations.
     */
    private static long variableYieldTime;

    /**
     * Used for syncing calculations.
     */
    private static long markedTime;

    /**
     * Cannot construct {@code Time}.
     */
    private Time() {
    }

    /**
     * Gets the current time (in nanoseconds).
     */
    public static long getCurrentTimeNanoseconds() {
        return System.nanoTime();
    }

    /**
     * Gets the current time (in milliseconds).
     */
    public static double getCurrentTimeMilliseconds() {
        return getCurrentTimeNanoseconds() / 1e6;
    }

    /**
     * Gets the current time (in seconds).
     */
    public static double getCurrentTimeSeconds() {
        return getCurrentTimeNanoseconds() / 1e9;
    }

    /**
     * An accurate and system-adaptable sync method.
     *
     * @param fps The desired frames-per-second.
     */
    public static void sync(int fps) {
        if (fps <= 0) {
            return;
        }

        long sleepTime = (long) (1e9 / fps);
        long yieldTime = Math.min(sleepTime, variableYieldTime + sleepTime % (1000 * 1000));
        long oversleptTime = 0;

        try {
            while (true) {
                long t = getCurrentTimeNanoseconds() - markedTime;

                if (t < sleepTime - yieldTime) {
                    Thread.sleep(1L);
                }
                else if (t < sleepTime) {
                    Thread.yield();
                }
                else {
                    oversleptTime = t - sleepTime;
                    break;
                }
            }
        } catch (InterruptedException exception) {
            exception.printStackTrace();
        } finally {
            markedTime = getCurrentTimeNanoseconds() - Math.min(oversleptTime, sleepTime);

            if (oversleptTime > variableYieldTime) {
                variableYieldTime = Math.min(variableYieldTime + 200 * 1000, sleepTime);
            } else if (oversleptTime < variableYieldTime - 200 * 1000) {
                variableYieldTime = Math.max(variableYieldTime - 2 * 1000, 0);
            }
        }
    }

}
