package ru.netology.javaqadiplom;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

public class SavingAccountTest {

    @Test
    public void shouldAddLessThanMaxBalance() {
        SavingAccount account = new SavingAccount(
                2_000,
                1_000,
                10_000,
                5
        );

        account.add(3_000);

        Assertions.assertEquals(2_000 + 3_000, account.getBalance());
    }

    @Test
    public void testAddTrueIfResultLessMaxBalance() {
        SavingAccount account = new SavingAccount(
                2_000,
                1_000,
                10_000,
                5
        );


        Assertions.assertTrue(account.add(3000));
    }

    @Test
    public void shouldNotAddIfAmountNegative() {
        SavingAccount account = new SavingAccount(
                2_000,
                1_000,
                10_000,
                5
        );

        account.add(-3_000);

        Assertions.assertEquals(2_000, account.getBalance());
    }

    @Test
    public void testAddFalseIfAmountEqualsZero() {
        SavingAccount account = new SavingAccount(
                2_000,
                1_000,
                10_000,
                5
        );


        Assertions.assertFalse(account.add(0));
    }

    @Test
    public void testAddFalseIfAmountNegative() {
        SavingAccount account = new SavingAccount(
                2_000,
                1_000,
                10_000,
                5
        );


        Assertions.assertFalse(account.add(-1000));
    }

    @Test
    public void testAddFalseIfResultMoreMaxBalance() {
        SavingAccount account = new SavingAccount(
                2_000,
                1_000,
                10_000,
                5
        );


        Assertions.assertFalse(account.add(9_000));
    }

    @Test
    public void shouldNotAddIfResultMoreMaxBalance() {
        SavingAccount account = new SavingAccount(
                2_000,
                1_000,
                10_000,
                5
        );

        account.add(9_000);

        Assertions.assertEquals(2_000, account.getBalance());
    }

    @Test
    public void shouldShowErrorIfRateNegative() {

        Assertions.assertThrows(IllegalArgumentException.class,
                () -> new SavingAccount(2_000,1_000,10_000,-5));
    }

    @Test
    public void shouldShowErrorIfMinBalanceLessZero() {

        Assertions.assertThrows(IllegalArgumentException.class,
                () -> new SavingAccount(2_000,-1_000,10_000,5));
    }

    @Test
    public void shouldShowErrorIfMaxBalanceLessMinBalance() {

        Assertions.assertThrows(IllegalArgumentException.class,
                () -> new SavingAccount(2_000,10_000,1_000,5));
    }

    @Test
    public void shouldShowErrorIfInitialBalanceNegative() {

        Assertions.assertThrows(IllegalArgumentException.class,
                () -> new SavingAccount(-2_000,1_000,10_000,5));
    }

    @Test
    public void shouldShowErrorIfInitialBalanceLessMinBalance() {

        Assertions.assertThrows(IllegalArgumentException.class,
                () -> new SavingAccount(500,1_000,10_000,5));
    }

    @Test
    public void shouldShowErrorIfInitialBalanceMoreMaxBalance() {

        Assertions.assertThrows(IllegalArgumentException.class,
                () -> new SavingAccount(15_000,1_000,10_000,5));
    }

    @Test
    public void payIfAmountLessInitialBalance() {
        SavingAccount account = new SavingAccount(
                5_000,
                1_000,
                10_000,
                5
        );

        account.pay(3_000);

        Assertions.assertEquals(5_000 - 3_000, account.getBalance());
    }

    @Test
    public void payIfAmountMoreInitialBalance() {
        SavingAccount account = new SavingAccount(
                5_000,
                1_000,
                10_000,
                5
        );

        account.pay(6_000);

        Assertions.assertEquals(5_000, account.getBalance());
    }

    @Test
    public void payIfAmountLessInitialBalanceButResultLessMinBalance() {
        SavingAccount account = new SavingAccount(
                5_000,
                1_000,
                10_000,
                5
        );

        account.pay(4_500);

        Assertions.assertEquals(5_000, account.getBalance());
    }

    @Test
    public void testPayFalseIfAmountNegative() {
        SavingAccount account = new SavingAccount(
                5_000,
                1_000,
                10_000,
                5
        );


        Assertions.assertFalse(account.pay(-5_000));
    }

    @Test
    public void testPayFalseIfAmountEqualsZero() {
        SavingAccount account = new SavingAccount(
                5_000,
                1_000,
                10_000,
                5
        );


        Assertions.assertFalse(account.pay(0));
    }

    @Test
    public void testPayTrueIfAmountLessInitialBalance() {
        SavingAccount account = new SavingAccount(
                5_000,
                1_000,
                10_000,
                5
        );


        Assertions.assertTrue(account.pay(3_000));
    }

    @Test
    public void testPayFalseIfAmountLessInitialBalanceButResultLessMinBalance() {
        SavingAccount account = new SavingAccount(
                5_000,
                1_000,
                10_000,
                5
        );


        Assertions.assertFalse(account.pay(4_500));
    }

    @Test
    public void testYearChange() {
        SavingAccount account = new SavingAccount(
                5_330,
                1_000,
                10_000,
                5
        );

        Assertions.assertEquals(266, account.yearChange());
    }

}
