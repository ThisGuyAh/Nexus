package com.codex.nexus.utility;

public class Arrays {

    private Arrays() {
    }

    public static float[] add(float[]... arrays) {
        int length = 0;

        for (var array : arrays) {
            length += array.length;
        }

        float[] output = new float[length];
        int index = 0;

        for (var array : arrays) {
            for (float value : array) {
                output[index++] = value;
            }
        }

        return output;
    }

    public static float[] interleave(int firstCopyInterval, int secondCopyInterval, float[] firstArray,
                                     float[] secondArray) {
        int firstLength = firstArray.length;
        int secondLength = secondArray.length;
        int totalLength = firstLength + secondLength;
        int firstIndex = 0;
        int secondIndex = 0;
        int outputIndex = 0;
        float[] output = new float[totalLength];

        for (int i = 0; i < totalLength; i++) {
            if (firstIndex < firstLength - 1) {
                for (int j = 0; j < firstCopyInterval; j++) {
                    output[outputIndex++] = firstArray[firstIndex++];
                }
            }
            if (secondIndex < secondLength - 1) {
                for (int j = 0; j < secondCopyInterval; j++) {
                    output[outputIndex++] = secondArray[secondIndex++];
                }
            }
        }

        return output;
    }

    public static float[] interleave(int firstCopyInterval, int secondCopyInterval, int thirdCopyInterval,
                                     float[] firstArray, float[] secondArray, float[] thirdArray) {
        int firstLength = firstArray.length;
        int secondLength = secondArray.length;
        int thirdLength = thirdArray.length;
        int totalLength = firstLength + secondLength + thirdLength;
        int firstIndex = 0;
        int secondIndex = 0;
        int thirdIndex = 0;
        int outputIndex = 0;
        float[] output = new float[totalLength];

        for (int i = 0; i < totalLength; i++) {
            if (firstIndex < firstLength - 1) {
                for (int j = 0; j < firstCopyInterval; j++) {
                    output[outputIndex++] = firstArray[firstIndex++];
                }
            }
            if (secondIndex < secondLength - 1) {
                for (int j = 0; j < secondCopyInterval; j++) {
                    output[outputIndex++] = secondArray[secondIndex++];
                }
            }
            if (thirdIndex < thirdLength - 1) {
                for (int j = 0; j < thirdCopyInterval; j++) {
                    output[outputIndex++] = thirdArray[thirdIndex++];
                }
            }
        }

        return output;
    }

    public static float[] interleave(int firstCopyInterval, int secondCopyInterval, int thirdCopyInterval,
                                     int fourthCopyInterval, float[] firstArray, float[] secondArray,
                                     float[] thirdArray, float[] fourthArray) {
        int firstLength = firstArray.length;
        int secondLength = secondArray.length;
        int thirdLength = thirdArray.length;
        int fourthLength = fourthArray.length;
        int totalLength = firstLength + secondLength + thirdLength + fourthLength;
        int firstIndex = 0;
        int secondIndex = 0;
        int thirdIndex = 0;
        int fourthIndex = 0;
        int outputIndex = 0;
        float[] output = new float[totalLength];

        for (int i = 0; i < totalLength; i++) {
            if (firstIndex < firstLength - 1) {
                for (int j = 0; j < firstCopyInterval; j++) {
                    output[outputIndex++] = firstArray[firstIndex++];
                }
            }
            if (secondIndex < secondLength - 1) {
                for (int j = 0; j < secondCopyInterval; j++) {
                    output[outputIndex++] = secondArray[secondIndex++];
                }
            }
            if (thirdIndex < thirdLength - 1) {
                for (int j = 0; j < thirdCopyInterval; j++) {
                    output[outputIndex++] = thirdArray[thirdIndex++];
                }
            }
            if (fourthIndex < fourthLength - 1) {
                for (int j = 0; j < fourthCopyInterval; j++) {
                    output[outputIndex++] = fourthArray[fourthIndex++];
                }
            }
        }

        return output;
    }

}
