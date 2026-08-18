class Solution {
    public int largestInteger(int[] nums, int k) {

        Map<Integer, Integer> count = new HashMap<>();

        for (int i = 0; i <= nums.length - k; i++) {

            Set<Integer> set = new HashSet<>();

            for (int j = i; j < i + k; j++) {
                set.add(nums[j]);
            }

            for (int num : set) {
                count.put(num, count.getOrDefault(num, 0) + 1);
            }
        }

        int answer = -1;

        for (int num : count.keySet()) {
            if (count.get(num) == 1) {
                answer = Math.max(answer, num);
            }
        }

        return answer;
    }
}

