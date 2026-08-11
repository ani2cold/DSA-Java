class Solution {
    public int missingInteger(int[] nums) {

        int sum = nums[0];

        int i = 1;

        while (i < nums.length && nums[i] == nums[i - 1] + 1) {
            sum += nums[i];
            i++;
        }

        int answer = sum;

        while (contains(nums, answer)) {
            answer++;
        }

        return answer;
    }

    private boolean contains(int[] nums, int target) {

        for (int num : nums) {
            if (num == target) {
                return true;
            }
        }

        return false;
    }
}