package leetcode.p688;


import java.util.*;

class Solution {
  public double knightProbability(int N, int K, int r, int c) {
    if (r < 0 || r >= N || c < 0 || c >= N ) return 0;
    if (K == 0) return 1;
    double[][] field = new double[N][N];
    HashMap<Point,Double> hs = new HashMap<>();
    hs.put(new Point(r,c),1.0);
    return allmoves(N,field,hs,K);
  }

  public static void main(String[] args){
    Solution sol = new Solution();
    System.out.println(sol.knightProbability(3,2,0,0));
  }


  class Point {

    Integer x;
    Integer y;

    Point(Integer px, Integer py) {
      x = px;
      y = py;
    }

    @Override
    public boolean equals(Object obj) {
      //System.out.println("Hello-equals");

      if(obj instanceof Point){
        Point other = (Point) obj;
        if(other.x == x && other.y == y ){
          return true;
        }
        else{
          return false;
        }
      }
      return false;
    }



    @Override
    public int hashCode() {
      //System.out.println("Hello-hashcode");
      return Integer.hashCode(x) + Integer.hashCode(y) * 31;
    }


  }


    private void singlemove(int N, double[][] field, Point point, Double prob, HashMap<Point,Double> hs, int x, int y){
      int ox = point.x + x;
      int oy = point.y + y;
      if ( ox >= 0 && ox < N && oy >= 0 && oy < N){
        double newprob = prob*1.0d/8;
        if (ox == 0 && oy == 0)
          System.out.println("blub");
        field[ox][oy] = field[ox][oy] + newprob;
        Point np = new Point(ox,oy);
        hs.put(np,field[ox][oy]);
      }
    }

    public void move(int N, double[][] field, Point point, Double prob, HashMap<Point,Double> hs){
      singlemove(N, field,point,prob,hs,-2,1);
      singlemove(N, field,point,prob,hs,-1,2);
      singlemove(N, field,point,prob,hs,1,2);
      singlemove(N, field,point,prob,hs,2,1);
      singlemove(N, field,point,prob,hs,2,-1);
      singlemove(N, field,point,prob,hs,1,-2);
      singlemove(N, field,point,prob,hs,-1,-2);
      singlemove(N, field,point,prob,hs,-2,-1);
    }

    double allmoves(int N, double[][]field, HashMap<Point,Double> points, int K){
      if (K == 0){
         return points.keySet().stream().map(p -> field[p.x][p.y])
             .reduce(0.0d,(a,b) -> a + b);
      } else {
        double[][] scratch = new double[N][N];
        HashMap<Point,Double> nhs = new HashMap<>();
        points.keySet().stream().forEach(p -> {
          move(N, scratch,p,points.get(p),nhs);
        });
        return allmoves(N, scratch,nhs,K-1);
      }
    }
  }