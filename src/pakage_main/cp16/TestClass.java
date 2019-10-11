package pakage_main.cp16;

import java.util.*;

public class TestClass {

    class Solution {
        public int solution(int[] ranks) {
            // write your code in Java SE 8

            int result = 0;

            Map<Integer, Integer> rankMap = new TreeMap<>();
            for (int rank : ranks) {
                if (rankMap.containsKey(rank)) {
                    // 증가
                    rankMap.put(rank, rankMap.get(rank) + 1);
                } else {
                    // 추가
                    rankMap.put(rank, 1);
                }
            }

            for (int key : rankMap.keySet()) {
                if (rankMap.containsKey(key + 1)) {
                    result += rankMap.get(key);
                }
            }
            return result;

        }
    }

    public int solution(int[] A) {
        // write your code in Java SE 8
        /** 가장 긴 값들을 가지는 그래프의 길이
         * 배열index = 정점, 배열 값 =  간선
         */
        if(A.length == 0){
            return 0;
        }
        int graphSize = 0;
        int result = 0;

        for(int i = 0 ; i< A.length;i++){
            if(A[i] == -1){
                continue;
            }

            graphSize = temp(A,i,A[i],0);

            if( graphSize > result){
                result = graphSize;
                if(result>A.length/2){ break; }
            }

        }

        return result;

    }

    public int temp(int[] A,int startVertex,int current,int cnt){
        cnt ++;
        if(startVertex == current){
            return cnt;
        }
        int next = A[current];

        A[current] = -1;

        return temp(A,startVertex,next,cnt);

    }


    public int solution(String[] B) {
        // write your code in Java SE 8
        String jPwan = "O";
        String aPwan = "X";

        int x = 0,y = 0;

        for(int i = 0;i<B.length;i++ ){
            if(B[i].contains(jPwan)){
                x = i;
                y = B[i].indexOf(jPwan);
                break;
            }
        }

        return temp(B,x,y,0);
    }

    public int temp(String[] B, int x, int y,int cnt){
        // 기준점을 벗어나는 값을 참조하려 하면 해당 좌표의 값을

        int left = cnt;
        int right = cnt;

        if(x<=0){return cnt;}
        if(y<=0){return cnt;}
        if(y >= B.length){return cnt;}


        if(B[x-1].indexOf(y-1) == 'X' || B[x-2].indexOf(y-2) == '.' ){
            left = temp(B, x-2,y-2,cnt++);
        }

        if(B[x-1].indexOf(y+1) == 'X' || B[x-2].indexOf(y+2) == '.' ){
            right = temp(B, x-2,y+2,cnt++);
        }

        if(left<right){
            return right;
        }else{
            return left;
        }
    }
}











