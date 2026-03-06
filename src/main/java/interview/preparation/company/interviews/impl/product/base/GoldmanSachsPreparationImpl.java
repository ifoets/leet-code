package interview.preparation.company.interviews.impl.product.base;

import interview.preparation.company.interviews.question.INetcore;
import interview.preparation.company.interviews.question.product.base.IGoldmanSachsPreparation;
import org.checkerframework.checker.units.qual.Temperature;

import java.lang.reflect.Array;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Function;
import java.util.random.RandomGenerator;
import java.util.stream.Collectors;

public class GoldmanSachsPreparationImpl implements IGoldmanSachsPreparation {

        /**
         * Find the missing number from a list of numbers from 1 to N in a sequence
         **/
        @Override
        public int missingNo(int[] a) {

            int left = 0;
            int right = a.length - 1;

            while (left < right) {
                int mid = (left + right) / 2;
                if (a[mid] == mid + 2)
                    return mid + 1;
                if (a[mid] == mid + 1)
                    left = mid + 1;
                else right = mid - 1;
            }
            return -1;
        }

        @Override
        public int lenOfLongestSubStr(int[] nums, int k) {
            int start = 0, end = 0;
            int sum = 0;
            int max = 0;
            int x = 0;
            int y = 0;
            while (end < nums.length) {
                sum += nums[end];
                while (sum > k)
                    sum -= nums[start++];
                max = Math.max(max, end - start + 1);
                x = start;
                y = end;
                end++;
            }
            System.out.println(x + ", " + y);
            return max;
        }

        /**
         * 34. Find First and Last Position of Element in Sorted Array
         **/
        @Override
        public int[] searchRange(int[] nums, int target) {
            return new int[]{findLeft(nums, target), findRight(nums, target)};
        }

        public int findLeft(int[] nums, int target) {
            int l = 0, r = nums.length - 1;
            int ans = -1;
            while (l <= r) {
                int mid = (l + r) / 2;
                if (nums[mid] == target) {
                    ans = mid;
                    r = mid - 1;
                } else if (nums[mid] < target)
                    l = mid + 1;
                else r = mid - 1;
            }
            return ans;

        }

        public int findRight(int[] nums, int target) {
            int l = 0, r = nums.length - 1;
            int ans = -1;

            while (l <= r) {
                int mid = (l + r) / 2;

                if (nums[mid] == target) {
                    ans = mid;       // record candidate
                    l = mid + 1;    // move right to find later occurrence
                } else if (nums[mid] < target) {
                    l = mid + 1;
                } else {
                    r = mid - 1;
                }
            }
            return ans;
        }

        /**
         * 33. Search in Rotated Sorted Array
         **/
        @Override
        public int indexOfInRotatedArr(int[] nums, int target) {
            int l = 0, r = nums.length - 1;
            int ans = -1;

            while (l <= r) {
                int mid = (l + r) / 2;

                if (nums[mid] == target) {
                    return mid;
                }
                //if left half sorted
                if (nums[l] <= nums[mid]) {
                    if (nums[l] <= target && target < nums[mid])
                        r = mid - 1; //target in left sorted half
                    else l = mid + 1; //// target in right half
                }
                //right hafl is sorted
                else {
                    if (nums[mid] < target && target <= nums[r])
                        l = mid + 1;
                    else r = mid - 1; //// target in left half
                }
            }
            return ans;
        }

        /**
         * 81. Search in Rotated Sorted Array II
         **/
        @Override
        public boolean searchInRotatedDuplicateValArr(int[] nums, int target) {
            int l = 0, r = nums.length - 1;

            while (l <= r) {
                int mid = (l + r) / 2;

                if (nums[mid] == target) {
                    return true;
                }
                // Ambiguous case due to duplicates
                if (nums[l] == nums[mid] && nums[mid] == nums[r]) {
                    l++;
                    r--;
                } else if (nums[l] <= nums[mid]) {
                    if (nums[l] <= target && target < nums[mid])
                        r = mid - 1; //target in left sorted half
                    else l = mid + 1; //// target in right half
                }
                //right hafl is sorted
                else {
                    if (nums[mid] < target && target <= nums[r])
                        l = mid + 1;
                    else r = mid - 1; //// target in left half
                }
            }
            return false;
        }

        /**
         * 162. Find Peak Element
         **/
        @Override
        public int findPeakElement(int[] nums) {
            int l = 0, r = nums.length - 1;

            while (l <= r) {
                int mid = (l + r) / 2;
                if (l == r) return l;
                if (nums[mid] < nums[mid + 1])
                    l = mid + 1;
                else r = mid; //mid itself can be the peak
            }
            return -1;
        }

        /**
         * 875. Koko Eating Bananas
         **/
        @Override
        public int minEatingSpeed(int[] piles, int h) {
            int low = 1;
            int high = 0;

            for (int p : piles) {
                high = Math.max(high, p);
            }

            int ans = high;

            while (low <= high) {
                int mid = low + (high - low) / 2;

                if (canEatAll(piles, h, mid)) {
                    ans = mid;        // try slower speed
                    high = mid - 1;
                } else {
                    low = mid + 1;   // need faster speed
                }
            }
            return ans;
        }

        private boolean canEatAll(int[] piles, int h, int k) {
            long hours = 0;  // long to avoid overflow

            for (int p : piles) {
                hours += Math.ceilDiv(p, k);
                if (hours > h) return false;
            }
            return hours <= h;
        }

        /**
         * 1011. Capacity To Ship Packages Within D Days
         **/
        @Override
        public int shipWithinDays(int[] weights, int days) {
            int low = 0, high = 0;

            for (int w : weights) {
                low = Math.max(low, w);  // minimum capacity
                high += w;               // maximum capacity
            }

            int ans = high;

            while (low <= high) {
                int mid = low + (high - low) / 2;

                if (canShip(weights, days, mid)) {
                    ans = mid;          // try smaller capacity
                    high = mid - 1;
                } else {
                    low = mid + 1;      // need bigger ship
                }
            }

            return ans;
        }

        private boolean canShip(int[] weights, int days, int cap) {
            int usedDays = 1;
            int currentLoad = 0;

            for (int w : weights) {
                if (currentLoad + w > cap) {
                    usedDays++;
                    currentLoad = w;
                    if (usedDays > days) return false;
                } else {
                    currentLoad += w;
                }
            }
            return true;
        }

        /**
         * 1283. Find the Smallest Divisor Given a Threshold
         * //int smallestDivisor(int[] nums, int threshold);
         * <p>
         * /**1482. Minimum Number of Days to Make m Bouquets
         */
        @Override
        public int minDays(int[] bloomDay, int m, int k) {
            //m*k totol no of flower required
            if ((m * k) > bloomDay.length) return -1;
            int low = Integer.MAX_VALUE;
            int high = Integer.MIN_VALUE;

            for (int b : bloomDay) {
                low = Math.min(low, b);
                high = Math.max(high, b);
            }
            int ans = -1;
            while (low <= high) {
                int mid = (low + high) / 2;

                if (canMakeBouquets(bloomDay, m, k, mid)) {
                    ans = mid;
                    high = mid - 1;//try ealier
                } else low = mid + 1;//need more day
            }
            return ans;
        }

        public boolean canMakeBouquets(int[] boomDays, int m, int k, int day) {
            int bouquets = 0;
            int consecutive = 0;
            for (int boomday : boomDays) {
                if (day >= boomday) {
                    consecutive++;
                    if (consecutive == k) {
                        bouquets++;
                        consecutive = 0;
                        if (bouquets >= m)
                            return true;
                    }
                } else consecutive = 0;

            }
            return bouquets >= m;
        }

        /**
         * 167. Two Sum II - Input Array Is Sorted, it is 1 based indexed
         **/
        public int[] twoSum(int[] numbers, int target) {
            int low = 0, high = numbers.length - 1;

            while (low <= high) {
                int sum = numbers[low] + numbers[high];
                if (sum == target)
                    return new int[]{low + 1, high + 1};//=1, since 1 based indexed
                if (sum < target)
                    low++;
                else high--;
            }
            return new int[]{}; // guaranteed one solution as per problem
        }

        /**
         * 125. Valid Palindrome
         **/
        @Override
        public boolean isPalindrome(String s) {
            int l = 0, r = s.length() - 1;

            while (l < r) {
                char left = s.charAt(l);
                char right = s.charAt(r);

                if (!Character.isLetterOrDigit(left)) {
                    l++;
                } else if (!Character.isLetterOrDigit(right)) {
                    r--;
                } else {
                    if (Character.toLowerCase(left) != Character.toLowerCase(right)) {
                        return false;
                    }
                    l++;
                    r--;
                }
            }
            return true;
        }

        /**
         * 74. Search a 2D Matrix, O(log(m * n))
         **/
        @Override
        public boolean searchMatrix(int[][] matrix, int target) {
            int m = matrix.length;
            int n = matrix[0].length;
            int low = 0, high = m * n - 1;
            while (low <= high) {
                int mid = (low + high) / 2;
                //this r,c follow 0,1,2,3,,,in series form 0 to m*n-1;
                int r = mid / n;
                int c = mid % n;

                int val = matrix[r][c];
                if (val == target) return true;
                else if (val < target)
                    low = mid + 1;
                else high = mid - 1;
            }
            return false;
        }

        /**
         * 283. Move Zeroes
         **/
        @Override
        public void moveZeroes(int[] nums) {
            int slow = 0;

            for (int fast = 0; fast < nums.length; fast++) {
                if (nums[fast] != 0) {
                    // swap only when fast != slow to reduce unnecessary writes
                    if (slow != fast) {
                        int temp = nums[slow];
                        nums[slow] = nums[fast];
                        nums[fast] = temp;
                    }
                    slow++;
                }
            }
        }

        /**
         * 763. Partition Labels
         **/
        @Override
        public List<Integer> partitionLabels(String s) {
            List<Integer> rsList = new ArrayList<>();

            int[] lastIndex = new int[26];
            for (int i = 0; i < s.length(); i++)
                lastIndex[s.charAt(i) - 'a'] = i;

            int start = 0, end = 0;

            for (int i = 0; i < s.length(); i++) {
                end = Math.max(end, lastIndex[s.charAt(i) - 'a']);
                if (i == end) {
                    rsList.add(end - start + 1);
                    start = i + 1;
                }
            }
            return rsList;
        }

        /**
         * 881. Boats to Save People, only max two people can save in a boat
         */
        @Override
        public int numRescueBoats(int[] peopleWeights, int limit) {
            Arrays.sort(peopleWeights);
            int minBoat = 0;
            int i = 0, j = peopleWeights.length - 1;
            while (i <= j) {
                if (peopleWeights[j] == limit)
                    j--;
                else if (peopleWeights[i] + peopleWeights[j] <= limit) {
                    i++;
                    j--;
                } else j--;
                minBoat++;
            }
            return minBoat;
        }

        /**
         * 55. Jump Game
         **/
        @Override
        public boolean canJump(int[] nums) {
            int maxReach = 0;

            for (int i = 0; i < nums.length; i++) {
                if (i > maxReach) return false;   // can't even reach this index
                maxReach = Math.max(maxReach, i + nums[i]);
                if (maxReach >= nums.length - 1) return true;
            }
            return true;
        }

        /**
         * 45. Jump Game II, min steps
         **/
        @Override
        public int jump(int[] nums) {
            if (nums.length <= 1) return 0;
            int maxReach = 0;
            int ans = 0, end = 0;
            for (int i = 0; i < nums.length; i++) {
                if (i > maxReach) return -1;   // can't even reach this index
                maxReach = Math.max(maxReach, i + nums[i]);
                if (maxReach >= nums.length - 1) {
                    ans++;
                    break;
                }

                if (i == end) {
                    end = maxReach;
                    ans++;
                }
            }
            return ans;
        }

        /**
         * jumps path indexes, if jums in min steps
         */
        @Override
        public List<Integer> jumpPath(int[] nums) {
            if (nums.length <= 1) return new ArrayList<>();
            List<Integer> list = new ArrayList<>();
            int maxReach = 0;
            int end = 0;
            for (int i = 0; i < nums.length; i++) {
                if (i > maxReach) return new ArrayList<>();   // can't even reach this index
                maxReach = Math.max(maxReach, i + nums[i]);
                if (maxReach >= nums.length - 1) {
                    list.add(i);
                    break;
                }

                if (i == end) {
                    list.add(i);
                    end = maxReach;
                }
            }
            return list;
        }

        /**
         * 134. Gas Station
         **/
        @Override
        public int canCompleteCircuit(int[] gas, int[] cost) {
            int totalGas = 0, totalCost = 0;
            int tank = 0;
            int start = 0;

            for (int i = 0; i < gas.length; i++) {
                totalGas += gas[i];
                totalCost += cost[i];

                tank += gas[i] - cost[i];

                if (tank < 0) {
                    start = i + 1;  // reset start
                    tank = 0;       // reset tank
                }
            }

            return totalGas >= totalCost ? start : -1;
        }

        /**
         * 435. Non-overlapping Intervals
         **/
        @Override
        public int eraseOverlapIntervals(int[][] intervals) {
            Arrays.sort(intervals, Comparator.comparingInt((int[] x) -> x[0]).thenComparing(y -> y[1]));

            int[] prev = intervals[0];
            int count = 0;
            for (int i = 1; i < intervals.length; i++) {
                if (prev[1] > intervals[i][0]) {
                    count++;
                    //keep smallar one on overllaping
                    if (intervals[i][1] < prev[1])
                        prev = intervals[i];
                } else prev = intervals[i];
            }
            return count;
        }

        /**
         * 135. Candy
         **/
        @Override
        public int candy(int[] ratings) {
            int[] candy = new int[ratings.length];
            Arrays.fill(candy, 1);

            for (int i = 1; i < candy.length; i++) {
                if (ratings[i] > ratings[i - 1])
                    candy[i] = candy[i - 1] + 1;
            }
            for (int i = candy.length - 1; i > 0; i--) {
                if (ratings[i - 1] > ratings[i])
                    candy[i - 1] = Math.max(candy[i - 1], candy[i] + 1);
            }
            int total = 0;
            for (int c : candy) total += c;
            return total;
        }

        /*Given a string with consecutive repeating characters, compress it by replacing each group of consecutive identical characters with the character followed by its count.*/
        @Override
        public String compressConscutiveStr(String str) {
            StringBuilder sb = new StringBuilder();

            Map<Character, Long> map =
                str.chars().mapToObj(c -> (char) c)
                    .collect(Collectors.groupingBy(
                        Function.identity(),
                        Collectors.counting()
                    ));

            for (Map.Entry<Character, Long> entry : map.entrySet()) {
                System.out.println(entry.getKey());
                sb.append(entry.getKey());
                sb.append(entry.getValue());
            }
            return sb.toString();
        }

        @Override
        public void sidesZeroAtEnd(int[] a) {
            int j = 1;

            for (int i = 0; i < a.length && j < a.length; ) {
                if (a[i] != 0)
                    i++;
                if (a[j] == 0)
                    j++;
                if (a[i] == 0 && a[j] != 0) {
                    int temp = a[i];
                    a[i] = a[j];
                    a[j] = temp;
                    i++;
                    j++;

                }
            }
        }

        /**
         * Given two arrays start[] and finish[], representing the start and finish times of activities. A person can perform only one activity at a time, and an activity can be performed only if its start time is greater than the finish time of the last chosen activity.
         * Find the maximum number of activities that can be performed without overlapping.
         * <p>
         * Examples:
         * <p>
         * Input: start[] = [1, 3, 0, 5, 8, 5], finish[] = [2, 4, 6, 7, 9, 9]
         * Output: 4
         * Explanation: A person can perform at most four activities. The maximum set of activities that can be performed is {0, 1, 3, 4} (these are the indexes in the start[] and finish[] arrays).
         */
        @Override
        public int maxActivity(int[] start, int[] end) {
            int[][] t = new int[start.length][2];
            for (int i = 0; i < start.length; i++) {
                t[i][0] = start[i];
                t[i][1] = end[i];
            }

            Arrays.sort(t, Comparator.comparing(x -> x[1]));//

            int activit = 1;
            int endTime = t[0][1];
            for (int i = 1; i < t.length; i++)//n
            {
                if (t[i][0] > endTime) {
                    endTime = t[i][1];
                    activit++;
                }
            }
            return activit;
        }

    /**2. Longest uniform subsequence - Find start index and length of longest repeating substring**/
    /**Given a string s of size N. The task is to find the largest substring which consists of the same characters**/
    @Override
    public int[] longestConscutiveRepetativeStr(String str){
        int j=0;
        int maxLen=0;
        int start=0;

        for(int i=0;i<str.length()&&j<str.length();)
        {
            if(str.charAt(i)==str.charAt(j))
            {
                int len = j-i+1;
                if(maxLen<len)
                {
                    maxLen = len;
                    start=i;
                }
                j++;
            }else i++;
        }
        System.out.println(str.substring(start,maxLen+start));
        return new int[]{start,maxLen};
    }
    /**"Shuffle an Array Randomly
     Problem Statement:
     Write a Java program that takes an array of integers and shuffles its elements randomly. Each time the program runs, the order of elements in the array should be different.
     Implement the shuffle without using built-in shuffle methods like Collections.shuffle()"*/
    @Override
    public void suffleArrayRandom(int[] nums){
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            int j = ThreadLocalRandom.current().nextInt(i, n); // i..n-1
            int tmp = nums[i];
            nums[i] = nums[j];
            nums[j] = tmp;
        }
    }
    /**15. 3Sum**/
    @Override
    public List<List<Integer>> threeSum(int[] a){

        List<List<Integer>> threeSum = new ArrayList<>();
        Arrays.sort(a);
        int n= a.length;

        for(int i=0;i<n-2;i++)
        {
            if(i>0 && a[i]==a[i-1])continue;

            int l= i+1, r=n-1;

            while (l<r)
            {
                int sum = a[i]+a[l]+a[r];
                if(sum==0) {
                    threeSum.add(Arrays.asList(a[i], a[l], a[r]));
                    l++;
                    r--;

                    while (l<r&&a[l]==a[l-1])l++;
                    while (l<r&&a[r]==a[r+1])r--;
                }else if(sum<0)
                    l++;
                else r--;

            }
        }
        return threeSum;
    }

    /**1283. Find the Smallest Divisor Given a Threshold**/
    @Override
    public int smallestDivisor(int[] nums, int threshold){
        int lo=1;//since 1 <= nums[i] <= 106
        int hi=0;
        for(int i:nums)hi=Math.max(i,hi);

        int ans=hi;
        while (lo<=hi)
        {
            int mid=(lo+hi)/2;

            if(isValidSmallestDivisor(nums,mid,threshold))
            {
                ans = mid;
                hi=mid-1; //try smaller one
            }
            else lo=mid+1;//try bigger
        }
        return ans;
    }

    public boolean isValidSmallestDivisor(int[] nums,int divisor,int threshold)
    {
        int sum=0;
        for(int i:nums) {
            sum += Math.ceilDiv(i, divisor);
            if(sum>threshold)return false;
        }
        return true;
    }

    /**215. Kth Largest Element in an Array*/
    @Override
    public int kthLargest(int[] nums,int k){
        PriorityQueue<Integer> pq = new PriorityQueue<Integer>();

        for(int i:nums)
        {
            pq.offer(i);
            if(pq.size()>k)
                pq.poll();
        }
        return pq.isEmpty()?Integer.MAX_VALUE:pq.peek();
    }
    @Override
    public int kthSmallest(int[] nums,int k){
        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());
        for(int i:nums)
        {
            pq.offer(i);
            if(pq.size()>k)
                pq.poll();
        }
        return pq.isEmpty()?Integer.MIN_VALUE:pq.peek();
    }

    PriorityQueue<Integer> pq;
    int k;
    @Override
    public int KthLargestStream(int k, int[] nums){
        this.k=k;
        pq = new PriorityQueue<>();
        for(int i:nums)
        {
            pq.offer(i);
            if(pq.size()>k)
                pq.poll();
        }
        return pq.peek();
    }
    @Override
    public int add(int val){
        pq.add(val);
        if(pq.size()>k)
            pq.poll();
        return pq.peek();
    }

    /**347. Top K Frequent Elements*/
    @Override
    public int[] topKFrequent(int[] nums, int k){
        return
                Arrays.stream(nums).boxed()
                        .collect(
                        Collectors.groupingBy(
                                Function.identity(),
                                Collectors.counting()
                        )
                )
                        .entrySet()
                        .stream()
                        .sorted(
                                Map.Entry.<Integer,Long>comparingByValue().reversed()
                        )
                        .limit(k)
                        .mapToInt(Map.Entry::getKey)
                        .toArray();
    }

    @Override
    public int[] topKFrequentX(int[] nums, int k){
        Map<Integer, Integer> map = new HashMap<>();
        for (int n : nums) {
            map.put(n, map.getOrDefault(n, 0) + 1);
        }

        // Min-heap by frequency
        PriorityQueue<int[]> pq =
                new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));

        for (Map.Entry<Integer, Integer> e : map.entrySet()) {
            pq.offer(new int[]{e.getKey(), e.getValue()});
            if (pq.size() > k) {
                pq.poll();   // keep only top k frequent
            }
        }

        int[] res = new int[k];
        int i = 0;
        while (!pq.isEmpty()) {
            res[k-1-i++] = pq.poll()[0];
        }
        return res;
    }

    /**1046. Last Stone Weight**/
    @Override
    public int lastStoneWeight(int[] stones){
        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());

        for (int stone:stones)pq.offer(stone);
        while (pq.size()>1)
        {
            int y = pq.poll();
            int x = pq.poll();
            if(y!=x)pq.offer(y-x);
        }
        return pq.isEmpty()?0:pq.peek();
    }
    /**Minimum Swaps to Move Max to End and Min to Start on adjecent swap**/
    /**Each adjacent swap moves the element by exactly one position.
     So to move:

     min from minIndex → 0 → need minIndex swaps

     max from maxIndex → (n-1) → need (n - 1 - maxIndex) swaps*/
    @Override
    public int minSwapForMinAtFirstAndMaxLast(int[] nums){
        int minIdx=-1,maxIdx=-1;
        int min=Integer.MAX_VALUE;
        int max=Integer.MIN_VALUE;

        for(int i=0;i<nums.length;i++)
        {
            if(nums[i]<min)
            {
                min = nums[i];
                minIdx=i;
            }
            if(nums[i]>max)
            {
                max = nums[i];
                maxIdx = i;
            }
        }
        int adjacentSwap = minIdx + (nums.length-1-maxIdx);
        //min is towards end
        if(minIdx>maxIdx)
            adjacentSwap--; //since right is shifted one right due to left shifting
        /*
arr = [ 5, 2, 3, 1 ]
index   0  1  2  3
max = 5 at index 0
min = 1 at index 3   (min is to the RIGHT of max)
n = 4

[5, 2, 3, 1]
[5, 2, 1, 3]   // swap (1,3)
[5, 1, 2, 3]   // swap (1,2)
[1, 5, 2, 3]   // swap (1,5)


Now the array is:

[1, 5, 2, 3]
^
max (5)*/
        return adjacentSwap;
    }

    /**Find Missing Alphabets to Make a Pangram**/
    @Override
    public String findMissingAlphabets(String str){
        boolean[] present = new boolean[26];

        for(char c:str.toCharArray())present[c-'a']=true;

        StringBuilder sb = new StringBuilder();
        for(int i=0;i<26;i++)
            if(!present[i])sb.append((char)(i+'a'));
        return sb.toString();
    }

    /**1047. Remove All Adjacent Duplicates In String**/
    @Override
    public String removeDuplicates(String s)
    {
        Stack<Character> stack = new Stack<>();
        for(char c:s.toCharArray())
        {
            boolean pop=false;
            while (!stack.isEmpty()&&stack.peek()==c) {
                stack.pop();
                pop=true;
            }
            if(!pop) {
                stack.push(c);
            }
        }
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty())
            sb.append(stack.pop());
        return sb.reverse().toString();
    }
    @Override
    public int[] nextGreaterElem(int[] a) {
        int n = a.length;
        int[] res = new int[n];
        Stack<Integer> stack = new Stack<>();

        for (int i = n - 1; i >= 0; i--) {

            while (!stack.isEmpty() && stack.peek() <= a[i]) {
                stack.pop();
            }

            res[i] = stack.isEmpty() ? -1 : stack.peek();
            stack.push(a[i]);
        }
        return res;
    }
    /**1299. Replace Elements with Greatest Element on Right Side**/
    @Override
    public int[] replaceElements(int[] arr)
    {
        int[] nums=  new int[arr.length];
        int maxRight = Integer.MIN_VALUE;
        for(int i=arr.length-1;i>=0;i--)
        {
            if(i==arr.length-1) {
                maxRight = arr[i];
                nums[i]=-1;
            }
            else {
                nums[i]=maxRight;
                maxRight=Math.max(maxRight,arr[i]);
            }
        }
        return nums;
    }

    /**735. Asteroid Collision**/
    @Override
    public int[] asteroidCollision(int[] asteroids){
        Deque<Integer> stack = new ArrayDeque<>();

        for (int asteroid : asteroids) {
            boolean destroyed = false;

            while (!stack.isEmpty()
                    && stack.peekLast() > 0
                    && asteroid < 0) {

                int top = stack.peekLast();

                if (Math.abs(top) < Math.abs(asteroid)) {
                    stack.pollLast(); // top explodes
                } else if (Math.abs(top) == Math.abs(asteroid)) {
                    stack.pollLast(); // both explode
                    destroyed = true;
                    break;
                } else {
                    destroyed = true; // current explodes
                    break;
                }
            }

            if (!destroyed) {
                stack.addLast(asteroid);
            }
        }

        // Convert to array
        return stack.stream().mapToInt(Integer::intValue).toArray();
    }

    //TODO down to up is not completed
    /**741. Cherry Pickup**/
    @Override
    public int cherryPickup(int[][] grid){
        int m = grid.length;
        int n = grid[0].length;

        for(int c=1;c<n;c++)
        {
            if(grid[0][c-1]!=-1)
                grid[0][c]+=grid[0][c-1];
        }
        int i,j=0;
        //going right/down to right down corner
        for(i=1;i<m;i++)
        {
            //first column take just up column value only
            grid[i][0]+=grid[i-1][0];
            grid[i-1][0]=0;
            for(j=1;j<n;j++)
            {
                if(grid[i-1][j]==-1&&grid[i][j-1]==-1)
                    return 0;
                if(grid[i][j]!=-1) {
                    grid[i][j]+=Math.max(grid[i-1][j],grid[i][j-1]);
                    if(grid[i-1][j]>=grid[i][j-1])
                        grid[i-1][j]=0;
                    else grid[i][j-1]=0;
                }
            }
        }
        //returning left/up to left up corner
        //last row add all date from right to left
        for(int c=n-1;c>0;c--)
        {
            if(grid[m-1][c-1]!=-1)
                grid[m-1][c-1]+=grid[m-1][c];
        }

        for(i=m-2;i>=0;i--)
        {
            //c=0 take only bottom column value
            grid[i][0]+=grid[i+1][0];
            grid[i+1][0]=0;
            for(j=n-2;j>=0;j--)
            {
                if(grid[i][j]!=-1)
                {
                    grid[i][j]+=Math.max(grid[i+1][j],grid[i][j+1]);
                }
            }
        }

        return grid[0][0];
    }

    /**62. Unique Paths*/
    @Override
    public int uniquePaths(int m, int n){
        int[][] dp = new int[m][n];

        for(int i=0;i<m;i++)
            dp[i][0] = 1;

        for(int j=0;j<n;j++)
            dp[0][j] = 1;

        for(int i=1;i<m;i++){
            for(int j=1;j<n;j++){
                dp[i][j] = dp[i-1][j] + dp[i][j-1];
            }
        }

        return dp[m-1][n-1];
    }

    /**63. Unique Paths II**/
    @Override
    public int uniquePathsWithObstacles(int[][] obstacleGrid){
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        int[][] dp = new int[m][n];

        for(int i=0;i<m;i++) {
            if(obstacleGrid[i][0]!=1) {
                dp[i][0] = 1;
            }else break;
        }

        for(int j=0;j<n;j++) {
            if(obstacleGrid[0][j]!=1) {
                dp[0][j] = 1;
            }
            else break;
        }

        for(int i=1;i<m;i++){
            for(int j=1;j<n;j++){
                if(obstacleGrid[i][j]!=1) {
                    dp[i][j] = (dp[i - 1][j] + dp[i][j - 1]);
                }

            }
        }

        return dp[m-1][n-1];
    }

    /**621. Task Scheduler**/
    @Override
    public int leastInterval(char[] tasks, int n){
      return -1;
    }

    /**find index of left sum==right sum**/
    @Override
    public int pivotSumIndex(int[] nums){
       return -1;
    }

    static void main() {

        String s ="aabcddaaa";
        //TreeMap<Character,Integer> map = new TreeMap<>((k,v)->);
    }
}
