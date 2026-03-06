package interview.preparation.company.interviews.question;

import java.util.Map;

public interface IGoldmanSach {

    double shortestDistance(String document, String word1, String word2);

    int minJumToReachEndOfArray(int[] a);

/**GS-USD2 preparation**/

    /**
     * Given two arrays start[] and finish[], representing the start and finish times of activities. A person can perform only one activity at a time, and an activity can be performed only if its start time is greater than the finish time of the last chosen activity.
     * Find the maximum number of activities that can be performed without overlapping.
     */
    int maxActivity(int[] start, int[] end);

    /**
     * 151. Reverse Words in a String.
     **/
    String reverseWords(String str);

    /**
     * given a grid of numbers move right and up find max sum
     **/
    int maxSum(int[][] mat);

    /**
     * find least recently used ip,ie older used timed ip
     */
    String lruFromLog(String[] logs);

    /**Find Minimum number in rotated sorted array.**/

    /**
     * Best Average Grade
     **/
    int bestAverage(String[][] scores);

    /**
     * median of two sorted Array O(m+n)
     */
    double medianSortedArrayByMerge(int[] a, int[] b);

    /**
     * O(log(m+n))
     */
    double findMedianSortedArrays(int[] a, int[] b);

    /**
     * Your local Foyle's is running a scheme to promote reading amongst users. If you sign up for the scheme,
     * you can buy any books from the store at a fixed cost of c. You can also exchange x number of books to get 1 new book.
     * Write an algorithm to find the maximum number of books you can read for a given amount of money m.
     **/
    int maxBooksReads(int m, int c, int x);

    /**
     * Minimum Steps to Make All Elements 1
     * Every 0 becomes 1 if at least one of its neighbors is 1 (left or right).
     * 1 always stays 1
     **/
    int minStepsToAllOnes(int[] a);

    /**
     * Longest Repeating Substring
     **/
    int longestRepSubStr(String str);

    /**
     * min sliding window
     **/
    String minSlidingWindow(String s, String t);

    /**
     * Longest Increasing Subsequence in a String
     **/
    String longestIncreasingSubsequence(String str);
    /**Sliding window concept**/
    /**left = 0
     for right in 0..n-1:
     include right
     while window is invalid:
     remove left
     left++
     update answer
     **/

    /**
     * 2134. Minimum Swaps to Group All 1's Together II
     **/
    int minSwaps(int[] a);

    /**
     * Find the sum of minimum and maximum element of all subarrays of size K
     **/
    int[] sumOfMinAndMax(int[] nums, int k);

    /**
     * Frequency Map (Last Removed Element),
     * Rule: Reduce each key by 1 per round; remove when 0; answer = last removed key.
     * Input {c=1, b=5, d=2} → A: b
     */
    char lastRemovedElem(Map<Character, Integer> map);

    char lastRemovedElemX(Map<Character, Integer> map);

    /**
     * 41. First Missing Positive
     * /**Return smallest positive number that is not present in array*
     **/
    int firstMissingPositive(int[] nums);
    int firstMissingPositiveX(int[] nums);

    /**Reverse string without changing position of special char**/
    String reverseStrWithoutChangePosOfSpecialChar(String str);

    /**First Non-Repeating Character*/
    char fistNonRepeatingChar(String str);
    /** input can be ANY ASCII / Unicode*/
     char firstNonRepeatingCharStream(String s);

     /**Max Average score*/
     double maxAverageScore(String[][] students);

     /** Print even and odd numbers in increasing order using two threads in Java**/
      void printEvenOddTwoThread();

      int kthSmallestNo(int[] nums, int k);
      int kthlargestNo(int[] nums, int k);



}
