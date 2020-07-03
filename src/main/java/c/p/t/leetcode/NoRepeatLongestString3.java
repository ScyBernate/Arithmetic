package c.p.t.leetcode;

/**
 * @ClassName LengthOfLongestSubstring
 * @Description TODO 无重复字符的最长子串
 * 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
 * 示例 1:
 * 输入: "abcabcbb"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
 * 示例 2:
 * <p>
 * 输入: "bbbbb"
 * 输出: 1
 * 解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
 * 示例 3:
 * <p>
 * 输入: "pwwkew"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
 *              请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
 * @Author User
 * @DATE 2020/6/30 16:14
 * @Version 1.0
 **/
public class NoRepeatLongestString3 {

    /**
     * 滑动窗口
     *
     * @param s
     * @return
     * @throws InterruptedException
     */
    public int lengthOfLongestSubstring(String s) {
        int max = 0;
        String temp = "";
        for (char i : s.toCharArray()) {
            String idx = String.valueOf(i);
            if (temp.contains(idx)) {
                System.out.println("当前最长串:" + temp.toString());
                int currentSize = temp.length();
                max = currentSize > max ? currentSize : max;
                int findIdx = temp.indexOf(idx);
                if (findIdx == 0) {
                    temp = temp.substring(1, temp.length());
                } else if (findIdx == temp.length()) {
                    temp = "";
                } else {
                    temp = temp.substring(findIdx + 1, temp.length());
                }
            }
            temp += idx;
        }
        max = max < temp.length() ? temp.length() : max;
        return max;
    }

    public int lengthOfLongestSubstring2(String s) {
        // 记录字符上一次出现的位置
        int[] last = new int[128];
        for (int i = 0; i < 128; i++) {
            last[i] = -1;
        }
        int n = s.length();

        int res = 0;
        int start = 0; // 窗口开始位置
        for (int i = 0; i < n; i++) {
            int index = s.charAt(i);
            start = Math.max(start, last[index] + 1);  //判断是否是重复字符，如果是，start>0;+1是因为索引是从0开始的
            res = Math.max(res, i - start + 1);        //i-start+1，代表未重复的子集长度
            last[index] = i;
        }

        return res;
    }

    public static void main(String[] args) {
        NoRepeatLongestString3 noRepeatLongestString = new NoRepeatLongestString3();
        System.out.println(noRepeatLongestString.lengthOfLongestSubstring2("abcaa"));
    }

}
