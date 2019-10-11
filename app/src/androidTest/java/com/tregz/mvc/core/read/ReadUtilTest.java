package com.tregz.mvc.core.read;

import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

@RunWith(AndroidJUnit4ClassRunner.class)
public class ReadUtilTest {

    @Test
    public void parse_Letter() { assertNull(ReadUtil.parse("L")); }

}
