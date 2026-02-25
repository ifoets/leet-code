package com.design.analysis.ds.hash.table.hard;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class IHashTableHardTest {

    IHashTableHard ihth;

    @Before
    public void init()
    {
        ihth = new HashTableHardImpl();
    }

    /**30. Substring with Concatenation of All Words**/
    @Test
    public void findSubstringTest()
    {
        String s = "foobarfoobar";//"barfoothefoobarman";
        String[] words = {"foo","bar"};
        List<Integer> exp = List.of(0,3,6);

        List<Integer> result = ihth.findSubstring(s,words).stream().sorted().toList();
        System.out.println(result);
        Assert.assertEquals(exp,result);

    }

    /**76. Minimum Window Substring*/
    @Test
    public void minWindowTest()
    {
       String str = ihth.minWindow("ab","b");
    }
}
