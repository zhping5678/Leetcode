package tree;

import java.util.ArrayList;

/*
Given a binary tree, find its minimum depth.
The minimum depth is the number of nodes along the shortest path from the root node down to the nearest leaf node.
 */
public class MinimumDepthSolution {

    //层次遍历，遇到第一个左右节点都是null的，return
    public int getMinimumDepthByLevelVisit(TreeNode root){

        if(root==null) return 0;
        if(root.left==null && root.right==null) return 1;

        ArrayList<TreeNode> queue=new ArrayList<>();
        queue.add(root);
        int depth=0;
        while (!queue.isEmpty()){
            int count=queue.size();
            depth++;
            for (int i=0;i<count;i++){
                TreeNode node=queue.remove(0);
                if (node.left==null && node.right==null){
                    return depth;
                }
                if (node.left!=null){
                    queue.add(node.left);
                }
                if (node.right!=null){
                    queue.add(node.right);
                }
            }
        }
        return 0;
    }

    //递归
    //左子树为空，return右子树最小层数+1
    //右子树为空，return左子树最小层数+1
    //左右子树都不为空，return左右子树最小层数小的那个
    public int getMinimumDepthByRecursion(TreeNode root){
        if (root==null) return 0;
        if (root.left==null) return getMinimumDepthByRecursion(root.right)+1;
        if (root.right==null) return getMinimumDepthByRecursion(root.left)+1;

        int leftDepth=getMinimumDepthByRecursion(root.left);
        int rightDepth=getMinimumDepthByLevelVisit(root.right);
        return leftDepth>rightDepth? rightDepth+1: leftDepth+1;
    }
}
