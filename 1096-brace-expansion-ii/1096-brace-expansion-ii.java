class Solution {
    int index = 0;

    public List<String> braceExpansionII(String expression) {
        Set<String> set = solve(expression);
        
        List<String> ans = new ArrayList<>(set);
        Collections.sort(ans);
        return ans;
    }

    Set<String> solve(String s) {
        Set<String> result = new HashSet<>();
        Set<String> current = new HashSet<>();
        current.add("");

        while (index < s.length() && s.charAt(index) != '}') {

            if (s.charAt(index) == ',') {
                result.addAll(current);
                current = new HashSet<>();
                current.add("");
                index++;
            }

            else if (s.charAt(index) == '{') {
                index++; // skip '{'

                Set<String> inside = solve(s);

                index++; // skip '}'

                current = multiply(current, inside);
            }

            else {
                // lowercase letter
                String letter = "" + s.charAt(index);
                Set<String> temp = new HashSet<>();
                
                for (String a : current) {
                    temp.add(a + letter);
                }

                current = temp;
                index++;
            }
        }

        result.addAll(current);
        return result;
    }

    Set<String> multiply(Set<String> a, Set<String> b) {
        Set<String> result = new HashSet<>();

        for (String x : a) {
            for (String y : b) {
                result.add(x + y);
            }
        }

        return result;
    }
}