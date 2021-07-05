package huawei;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 * @Author: ZhanLiao
 * @Description:
 * @Date: 2021/6/30 19:04
 * @Version: 1.0
 */
public class Main {

    static int ans = Integer.MAX_VALUE;
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int m = scanner.nextInt();
        int star = scanner.nextInt();
        int n = scanner.nextInt();

        HashMap<Integer, ArrayList<edge>> map = new HashMap<Integer, ArrayList<edge>>();
        for (int i = 0; i < n; i++) {
            int s = scanner.nextInt();
            int e = scanner.nextInt();
            int d = scanner.nextInt();
            edge ee = new edge(e, d);
            if (map.containsKey(s)){
                map.get(s).add(ee);
            }else {
                ArrayList<edge> edges = new ArrayList<edge>();
                edges.add(ee);
                map.put(s, edges);
            }
            edge ss = new edge(s, d);
            if (map.containsKey(e)){
                map.get(e).add(ss);
            }else {
                ArrayList<edge> edges = new ArrayList<edge>();
                edges.add(ss);
                map.put(e, edges);
            }
        }
        int[] visit = new int[m];
        visit[star] = 1;
        dfs(star, 0, visit, map, 0);
        if (ans == Integer.MAX_VALUE){
            System.out.println(-1);
        }else {
            System.out.println(ans);
        }
    }

    private static void dfs(int cur, int tmp, int[] visit, HashMap<Integer, ArrayList<edge>> map, int step) {
        if (step == map.size()-1){
            ans = Math.min(ans, tmp);
            return;
        }
        for (edge edge : map.get(cur)) {
            if (visit[edge.point] == 1){
                continue;
            }
            visit[edge.point] = 1;
            tmp += edge.dis;
            dfs(edge.point, tmp, visit, map, step+1);
            tmp -= edge.dis;
            visit[edge.point] = 0;
        }
    }
}
class edge{
    int point;
    int dis;
    edge(){ }
    edge(int a, int b){
        point = a;
        dis = b;
    }
}
