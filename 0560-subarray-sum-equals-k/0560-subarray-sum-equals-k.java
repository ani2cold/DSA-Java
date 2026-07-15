class Solution {
    public int subarraySum(int[] nums, int k) {
        
        HashMap<Integer,Integer> map = new HashMap<>();
        int sum=0;
        int count=0;
        
        map.put(0,1);

        for(int num:nums){
            sum+=num;
            if(map.containsKey(sum-k))
                count+=map.get(sum-k);
            
            map.put(sum,map.getOrDefault(sum,0)+1);
        }
        
        return count;
    }
}

// Synced seamlessly with LeetHub Pro
// Pro features: https://bit.ly/leethubpro | Free version: https://bit.ly/leethubv4
// Get it here: https://chromewebstore.google.com/detail/bcilpkkbokcopmabingnndookdogmbna