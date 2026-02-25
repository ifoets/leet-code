package com.design.analysis.algo.shortest.path.medium;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class IShortestPathMediumTest {
    IShortestPathMedium ispm;

    @Before
    public void init()
    {
        ispm = new ShortestPathMediumImpl();
    }

    /**399. Evaluate Division**/
    @Test
    public void calcEquationTest()
    {
        List<List<String>> equations = List.of(List.of("a","b"),List.of("b","c"));
        double[] values = {2.0,3.0};
        List<List<String>> queries = List.of(List.of("a","c"),List.of("b","a"),List.of("a","e"),List.of("a","a"),List.of("x","x"));

        double[] exp= {6.0, 0.5, -1.0, 1.0, -1.0};
        double[] result = ispm.calcEquation(equations,values,queries);
        System.out.println(Arrays.toString(result));
       for(int i=0;i<result.length;i++) {
           Assert.assertEquals(exp[i], result[i], 1e-9);
       }
    }

    /**743. Network Delay Time**/
    @Test
    public void networkDelayTimeTest()
    {
      int[][] times = {{2,1,1},{2,3,1},{3,4,1}};
      int n = 4, k = 2;
      Assert.assertEquals(2,ispm.networkDelayTime(times,n,k));
    }

    /**787. Cheapest Flights Within K Stops**/
    @Test
    public void findCheapestPriceTest()
    {
        int n = 4;
        int[][] flights = {{0,1,100},{1,2,100},{2,0,100},{1,3,600},{2,3,200}};
        int src = 0, dst = 3, k = 1;
        int minDist = ispm.findCheapestPrice(n,flights,src,dst,k);
        Assert.assertEquals(700,minDist);
        int minDist1 = ispm.findCheapestPrice(n,flights,src,dst,2);
        Assert.assertEquals(400,minDist1);
    }
}
