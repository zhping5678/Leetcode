package array;

public class BinarySearch {

    public static void main(String[] args) {
        new BinarySearch().searchRange(new int[]{5,7,7,8,8,10}, 8);
    }

    /*
     * 给定一个按照升序排列的整数数组 nums，和一个目标值 target。找出给定目标值在数组中的开始位置和结束位置。
     * 你的算法时间复杂度必须是 O(log n) 级别。
     * 如果数组中不存在目标值，返回 [-1, -1]。
     *
     * 示例 1:
     *
     * 输入: nums = [5,7,7,8,8,10], target = 8
     * 输出: [3,4]
     * 示例 2:
     *
     * 输入: nums = [5,7,7,8,8,10], target = 6
     * 输出: [-1,-1]
    */
    private int[] searchRange(int[] nums, int target) {
        return new int[]{findLeftBound(nums,target), findRightBound(nums, target)};
    }

    private int findLeftBound(int[] nums, int target) {
        int right = nums.length;
        if (right == 0) {
            return -1;
        }
        int left = 0;
        while (left < right) {
            int mid = (left + right) / 2;
            if (nums[mid] == target) { // 因为要找的是边界，所以在找到目标值还要继续向左缩小范围
                right = mid;
            } else if (nums[mid] > target) {
                right = mid;
            } else if (nums[mid] < target){
                left = mid + 1;
            }
        }
        if (left == nums.length) {
            return -1;
        }
        return nums[left] == target ? left : -1;
    }

    private int findRightBound(int[] nums, int target) {
        int right = nums.length;
        if (right == 0) {
            return -1;
        }
        int left = 0;
        while (left < right) {
            int mid = (left + right) / 2;
            if (nums[mid] == target) {
                left = mid + 1;
            } else if (nums[mid] > target) {
                right = mid;
            } else if (nums[mid] < target) {
                left = mid + 1;
            }
        }
        if (left == 0) return -1;
        return nums[left-1] == target ? (left-1) : -1;
    }

    /*
     * 假设按照升序排序的数组在预先未知的某个点上进行了旋转。
     * ( 例如，数组 [0,1,2,4,5,6,7] 可能变为 [4,5,6,7,0,1,2] )。
     * 搜索一个给定的目标值，如果数组中存在这个目标值，则返回它的索引，否则返回 -1 。
     * 你可以假设数组中不存在重复的元素。
     * 你的算法时间复杂度必须是 O(log n) 级别。
     *
     * 示例 1:
     *
     * 输入: nums = [4,5,6,7,0,1,2], target = 0
     * 输出: 4
     * 示例 2:
     *
     * 输入: nums = [4,5,6,7,0,1,2], target = 3
     * 输出: -1
     */
    /**
     * if-else
     */
    private int search(int[] nums, int target) {
        int low = 0;
        int high = nums.length - 1;
        int mid;
        while (low <= high) {
            mid = (low + high) / 2;
            if (target == nums[mid]) {
                return mid;
            }
            // 说明 0-mid之间为升序
            if (nums[mid] >= nums[low]) {
                if (target >= nums[low] && target <= nums[mid]) { // 在前半部分
                    high = mid - 1;
                } else {
                    low = mid + 1;
                }
            } else { // nums[mid] < nums[low]
                // 说明 0-mid之间有转折
                if (target <= nums[mid] || target >= nums[low]) {
                    high = mid - 1;
                } else {
                    low = mid + 1;
                }
            }
        }
        return -1;
    }

    /**
     * 异或，和上面的分析过程一致，最后进行归纳总结
     *
     * nums[0] <= nums[mid]（0 - mid不包含旋转）且nums[0] <= target <= nums[mid] 时 high 向前规约；
     * nums[mid] < nums[0]（0 - mid包含旋转），target <= nums[mid] < nums[0] 时向前规约（target 在旋转位置到 mid 之间）
     * nums[mid] < nums[0]，nums[mid] < nums[0] <= target 时向前规约（target 在 0 到旋转位置之间）
     * 其他情况向后规约
     *
     * 等价于：nums[mid] < nums[0]，nums[0] > target，target > nums[mid] 三项均为真或者只有一项为真时向后规约
     */
    private int search2(int[] nums, int target) {
        int low = 0;
        int high = nums.length - 1;
        while (low < high) {
            int mid = (low + high) / 2;
            if ((nums[0] > target) ^ (nums[0] > nums[mid]) ^ (target > nums[mid])) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return low == high && nums[low] == target ? low : -1;
    }
}
