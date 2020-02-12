package array;

import java.util.*;

public class TraceBack {

    public static void main(String[] args) {
        new TraceBack().combinationSum(new int[]{2,3,5}, 8);
    }

    /**
     *给定一个没有重复数字的序列，返回其所有可能的全排列。
     * 示例:
     *
     * 输入: [1,2,3]
     * 输出:
     * [
     *   [1,2,3],
     *   [1,3,2],
     *   [2,1,3],
     *   [2,3,1],
     *   [3,1,2],
     *   [3,2,1]
     * ]
     */
    private List<List<Integer>> permuteResults = new ArrayList<>();
    private boolean[] used;
    private int length;
    public List<List<Integer>> permute(int[] nums) {
        if (nums == null || nums.length == 0) {
            return permuteResults;
        }
        length = nums.length;
        // 初始化状态数组，记录是否已经被选择过了
        used = new boolean[length];
        // 数组暂存已经选择的部分
        List<Integer> curr = new ArrayList<>();
        permuteHelper(nums, curr, 0);
        return permuteResults;
    }

    private void permuteHelper(int[] nums, List<Integer> current, int depth) {
        if (depth == length) {
            permuteResults.add(new ArrayList<>(current));
            return;
        }
        for (int i = 0;i < length;i++) { //挨个选择
            if (!used[i]) { // 如果没被选择过，就加到队列里面
                used[i] = true;
                current.add(nums[i]);
                permuteHelper(nums, current, depth + 1);
                used[i] = false;
                current.remove(depth);
            }

        }
    }

    /**
     * 给定一个无重复元素的数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
     * candidates 中的数字可以无限制重复被选取。
     * 说明：
     * 所有数字（包括 target）都是正整数。
     * 解集不能包含重复的组合。 
     * 示例 1:
     *
     * 输入: candidates = [2,3,6,7], target = 7,
     * 所求解集为:
     * [
     *   [7],
     *   [2,2,3]
     * ]
     * 示例 2:
     *
     * 输入: candidates = [2,3,5], target = 8,
     * 所求解集为:
     * [
     *   [2,2,2,2],
     *   [2,3,3],
     *   [3,5]
     * ]
     */
    private int len;
    private int[] candidates;
    private List<List<Integer>> result = new ArrayList<>();
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        int len = candidates.length;
        if (len == 0) {
            return result;
        }
        Arrays.sort(candidates);
        this.len = len;
        this.candidates = candidates;
        findCombination(target, 0, new Stack<>());
        return result;
    }

    private void findCombination(int remain,int start, Stack<Integer> cur) {
        if (remain == 0) {
            result.add(new ArrayList<>(cur));
            return;
        }
        for (int i = start;i < len && remain - candidates[i] >= 0;i++) {
            cur.add(candidates[i]);
            findCombination(remain - candidates[i], i, cur);
            cur.pop();
        }
    }
}
