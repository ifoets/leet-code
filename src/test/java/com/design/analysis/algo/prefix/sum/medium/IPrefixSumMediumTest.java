package com.design.analysis.algo.prefix.sum.medium;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class IPrefixSumMediumTest {

    IPrefixSumMedium ipsm;

    @Before
    public void init()
    {
        ipsm = new PrefixSumMediumImpl();
    }

    /**209. Minimum Size Subarray Sum**/
    @Test
    public void  minSubArrayLenTest()
    {
        int[]nums = {2,3,1,2,4,3};
        Assert.assertEquals(2,ipsm.minSubArrayLen(7,nums));
        Assert.assertEquals(0,ipsm.minSubArrayLen(11,new int[]{1,1,1,1,1,1,1,1}));
    }
}
