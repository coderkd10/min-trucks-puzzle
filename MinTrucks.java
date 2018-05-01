public class MinTrucks {
    static int maxInArray(int[] arr) {
        // utlity function to compute maximum in an array
        if (arr.length == 0) {
            throw new RuntimeException("input array must have atleast one element");
        }
        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }
        return max;
    }

    static int sumArray(int[] arr) {
        //utility function to compute the sum of elements of an array
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
        }
        return sum;
    }

    static int findMinTrucks(int[] transitTimeDays, int[] minTrucksLeaving) {
        // Computes the minimum number of trucks required
        // transitTimeDays -> T1, T2, ..., TN (as in doc)
        // minTrucksLeaving -> m1, m2, ..., mN (as in doc)

        // First compute the actual min trucks leaving each station
        // which we proved is the max of m1 ... mN
        int actualMinTrucksLeaving = maxInArray(minTrucksLeaving);
        // compute final answer
        return actualMinTrucksLeaving*sumArray(transitTimeDays);
    }

    // Driver program to test solution
    public static void main(String[] args) {
        int[] transitTimeDays = {1, 1, 2};
        int[] minTrucksLeaving = {1, 2, 3};
        int minTrucks = findMinTrucks(transitTimeDays, minTrucksLeaving);
        System.out.println("Minimum number of trucks required = " + minTrucks);
    }
};
