package exhaustive;

import java.util.HashMap;
import java.util.Map;

/*
Given n points on a 2D plane, find the maximum number of points that lie on the same straight line.
solution: p1和p2斜率相同，p1和p3斜率也相同，则这三点是共线的
 */
public class MaxPointsOnLine {

    //note1:斜率由除法得到，有精度问题，所以这里用分数表示
    //note2:注意重合的点，也是共线的
    public int maxPoints(Point[] points) {
        if (points==null){
            return 0;
        }
        if (points.length<=2){
            return points.length;
        }
        Map<String, Integer> map=new HashMap<>();
        int result=0;
        for (int i=0;i<points.length;i++){
            map.clear();
            int curMax=0;
            int dup=1;//重合点个数，自己算一个
            for (int j=i+1;j<points.length;j++){
                int _x=points[i].x-points[j].x;
                int _y=points[i].y-points[j].y;
                if (_x==0 && _y==0){//点重合
                    dup++;
                    continue;
                }
                int gcd=calculateGCD(_x, _y);
                String key=(_y/gcd)+"/"+(_x/gcd);
                map.put(key, map.getOrDefault(key,0)+1);
                curMax=Math.max(curMax, map.get(key));
            }
            result=Math.max(result, curMax+dup);
        }
        return result;
    }

    private int calculateGCD(int a, int b){
        return b==0? a: calculateGCD(b, a%b);
    }
}
