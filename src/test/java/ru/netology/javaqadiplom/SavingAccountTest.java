package ru.netology.javaqadiplom;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

public class SavingAccountTest {

    // Тесткейсы на метод пополнения карты
    // 1. Успешное пополнение счета. Баланс после пополнения меньше максимального баланса.
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

    // 2. Проверка на успешность операции.
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

    // 3. Счет не пополняется, если сумма пополнения отрицательная.
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

    // 4. Проверка на безуспешное завершение операции.
    // 4.1 При сумме попонения раной 0.
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

    // 4.2 При отрицательной сумме попонения.
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

    // 4.3 Если полсле пополнения баланс больше максимального баланса.
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

    // 5. Безуспешное пополнение счета. Если баланс после пополнения больше максимального баланса.
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

    // Тесткейсы на ловлю исключений вида IllegalArgumentException при создании нового объекта сберегательного счета.
    // 1. Если ставка в процентах годовых на остаток отрицательная.
    @Test
    public void shouldShowErrorIfRateNegative() {

        Assertions.assertThrows(IllegalArgumentException.class,
                () -> new SavingAccount(2_000,1_000,10_000,-5));
    }

    // 2. Если минимальный баланс отрицательный.
    @Test
    public void shouldShowErrorIfMinBalanceLessZero() {

        Assertions.assertThrows(IllegalArgumentException.class,
                () -> new SavingAccount(2_000,-1_000,10_000,5));
    }

    // 3. Если минимальный баланс больше максимального.
    @Test
    public void shouldShowErrorIfMaxBalanceLessMinBalance() {

        Assertions.assertThrows(IllegalArgumentException.class,
                () -> new SavingAccount(2_000,10_000,1_000,5));
    }

    // 4. Если начальный баланс отрицательный.
    @Test
    public void shouldShowErrorIfInitialBalanceNegative() {

        Assertions.assertThrows(IllegalArgumentException.class,
                () -> new SavingAccount(-2_000,1_000,10_000,5));
    }

    // 5. Если начальный баланс меньше минимального баланса.
    @Test
    public void shouldShowErrorIfInitialBalanceLessMinBalance() {

        Assertions.assertThrows(IllegalArgumentException.class,
                () -> new SavingAccount(500,1_000,10_000,5));
    }

    // 6. Если начальный баланс больше максимального.
    @Test
    public void shouldShowErrorIfInitialBalanceMoreMaxBalance() {

        Assertions.assertThrows(IllegalArgumentException.class,
                () -> new SavingAccount(15_000,1_000,10_000,5));
    }

    // Тесткейсы на метод оплаты с карты на указанную сумму.
    // 1. Успешная оплата при сумме покупки меньше начального баланса.
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

    // 2. Безуспешная оплата при сумме покупки больше начального баланса.
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

    // 3. Безуспешная оплата если баланс после покупки станет меньше минимального баланса.
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

    // 4. Проверка на безуспешное завершение операции.
    // 4.1 При отрицательной сумме покупки.
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

    // 4.2 Если сумма покупки равна 0.
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

    // 4.3 Если баланс после покупки станен меньше минимального баланса.
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


    // 5. Проверка на успешность операции.
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



    // Тесткейс на метод операции расчёта процентов на остаток счёта.
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
