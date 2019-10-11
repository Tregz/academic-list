package com.tregz.mvc.main.user;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class UserUtilTest {

    @Test
    public void emailValidator_isCorrectDotCom() {
        assertTrue(UserUtil.isEmailValid("name@email.com"));
    }

    @Test
    public void emailValidator_isCorrectSubDomain() {
        assertTrue(UserUtil.isEmailValid("name@email.co.uk"));
    }

    @Test
    public void emailValidator_isCorrectNoDomain() {
        assertFalse(UserUtil.isEmailValid("name@email"));
    }

    @Test
    public void emailValidator_isCorrectDoubleDot() {
        assertFalse(UserUtil.isEmailValid("name@email..com"));
    }

    @Test
    public void emailValidator_isCorrectNoUser() {
        assertFalse(UserUtil.isEmailValid("@email.com"));
    }

    @Test
    public void emailValidator_isCorrectEmpty() {
        assertFalse(UserUtil.isEmailValid(""));
    }

    @Test
    public void personNameValidator_isCorrectEmpty() {
        assertFalse(UserUtil.isPersonNameValid(""));
    }

    @Test
    public void dateValidator_isCorrectEmpty() {
        assertFalse(UserUtil.isDateValid(""));
    }

    @Test
    public void dateValidator_isCorrectGoodFormat() {
        assertTrue(UserUtil.isDateValid("10/12/1199"));
    }

    @Test
    public void dateValidator_isCorrectBadFormat() {
        assertFalse(UserUtil.isDateValid("999/99/99"));
    }

}
