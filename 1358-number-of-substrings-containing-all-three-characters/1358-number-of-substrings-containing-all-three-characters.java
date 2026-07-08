class Solution {
    public int numberOfSubstrings(String s) {

        int[] lastS={-1,-1,-1};
        int count=0;

        for(int i=0;i<s.length();i++){
            lastS[s.charAt(i)- 'a']=i;

            if(lastS[0]!=-1 && lastS[1]!=-1 && lastS[2]!=-1){

                int min= Math.min(lastS[0],Math.min(lastS[1], lastS[2]));

                count= count + min + 1;
            }
        }
        return count;
    }
}