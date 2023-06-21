package ru.netology.javaqadiplom;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CreditAccountTest {

    // № 1. Создание счета: корректные параметры, граничные значения (1.1-1.7)
    //    № 1.1.  Начальный баланс = 0
    @Test
    public void shouldCreateWithZeroBalance() {
        CreditAccount account = new CreditAccount(
                0,
                1_000,
                15
        );
        Assertions.assertEquals(0, account.getBalance());
    }

    // № 1.2. Начальный баланс = 1
    @Test
    public void shouldCreateWithBalanceIs1() {
        CreditAccount account = new CreditAccount(
                1,
                1_000,
                15
        );
        Assertions.assertEquals(1, account.getBalance());
    }

    // № 1.3             Кредитный лимит = 0
    @Test
    public void shouldCreateWithZeroLimit() {
        CreditAccount account = new CreditAccount(
                0,
                0,
                15
        );
        Assertions.assertEquals(0, account.getCreditLimit());
    }

    // № 1.4             Кредитный лимит = 1
    @Test
    public void shouldCreateWithLimitIs1() {
        CreditAccount account = new CreditAccount(
                0,
                1,
                15
        );
        Assertions.assertEquals(1, account.getCreditLimit());

    }

    // № 1.5           Ставка кредитования = 0
    @Test
    public void shouldCreateWithZeroRate() {
        CreditAccount account = new CreditAccount(
                0,
                1,
                0
        );
        Assertions.assertEquals(0, account.getRate());
    }

    // № 1.6           Ставка кредитования = 1
    @Test
    public void shouldCreateWithRateIs1() {
        CreditAccount account = new CreditAccount(
                1_000,
                15_000,
                1
        );
        Assertions.assertEquals(1, account.getRate());
    }

    // Создание счета: некорректные параметры (1.7-1.9)

    // № 1.7: Невозможность введения некорректного параметра - отрицательного изначального баланса
    @Test
    public void shouldNotCreateWithNegativeBalance() {

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            CreditAccount account = new CreditAccount(
                    -1,
                    5_000,
                    15
            );
        });
    }

    // № 1.8. Невозможность введения некорректного параметра - отрицательного кредитного лимита
    @Test
    public void shouldNotCreateNegativeLimit() {

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            CreditAccount account = new CreditAccount(
                    0,
                    -5_000,
                    15
            );
        });
    }

    // № 1.9. Невозможность введения некорректного параметра - отрицательной ставки кредитования
    @Test
    public void shouldNotCreateNegativeRate() {

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            CreditAccount account = new CreditAccount(
                    0,
                    5_000,
                    -15
            );
        });
    }
    // 2. Оплата с карты

    // № 2.1. сумма покупки > 0, конечный баланс > 0, баланс уменьшается на сумму покупки
    @Test
    public void shouldPayWithPositiveSumAndEndingBalance() {
        CreditAccount account = new CreditAccount(
                3_000,
                1,
                15
        );
        account.pay(1_300);
        int actual = account.getBalance();
        Assertions.assertEquals(1_700, actual);
    }

    // № 2.2. сумма покупки > 0, конечный баланс = 0, баланс успешно уменьшается на сумму покупки

    @Test
    public void shouldPayWithPositiveSumAndZeroEndingBalance() {
        CreditAccount account = new CreditAccount(
                3_000,
                1,
                15
        );
        account.pay(3_000);
        int actual = account.getBalance();
        Assertions.assertEquals(0, actual);
    }

    // № 2.3. сумма покупки > 0, конечный баланс < 0, его размер < размера кредитного лимита
    @Test
    public void shouldPayWithPositiveSumAndEndingBalanceUpperLimit() {
        CreditAccount account = new CreditAccount(
                3_000,
                5_001,
                15
        );
        account.pay(8_000);
        int actual = account.getBalance();
        Assertions.assertEquals(-5_000, actual);
    }

    // № 2.4. сумма покупки > 0, конечный баланс < 0, = размеру кредитного лимита
    @Test
    public void shouldPayWithPositiveSumAndEndingBalanceEqualsLimit() {
        CreditAccount account = new CreditAccount(
                3_000,
                5_000,
                15
        );
        account.pay(8_000);
        int actual = account.getBalance();
        Assertions.assertEquals(-5_000, actual);
    }

    // № 2.5. сумма покупки > 0, конечный баланс < 0, его размер > размера кредитного лимита (невозможно)
    @Test
    public void shouldNotPayWithEndingBalanceUnderLimit() {
        CreditAccount account = new CreditAccount(
                3_000,
                4_999,
                15
        );
        Assertions.assertEquals(false, account.pay(8_000));
    }

    // № 2.6. невозможность проведения покупки с суммой = 0

    @Test
    public void shouldNotPayWithZeroAmount() {
        CreditAccount account = new CreditAccount(
                3_000,
                4_999,
                15
        );
        Assertions.assertEquals(false, account.pay(0));
    }

    // № 2.7. невозможность проведения покупки с отрицательной суммой
    @Test
    public void shouldNotPayWithNegativeAmount() {
        CreditAccount account = new CreditAccount(
                3_000,
                4_999,
                15
        );
        Assertions.assertEquals(false, account.pay(-2_000));
    }

    // 3. Пополнение карты
    // № 3.1: Успешно добавляет положительную сумму к счету с изначальными корректными параметрами
    // (неотрицательные изначальный баланс, кредитный лимит и ставка кредитования)
    // изначальный баланс = 0
    @Test
    public void shouldAddToPositiveBalanceFromZero() {
        CreditAccount account = new CreditAccount(
                0,
                5_000,
                15
        );

        account.add(3_000);

        Assertions.assertEquals(3_000, account.getBalance());
    }

    // № 3.2: Успешно добавляет положительную сумму к счету с изначальными корректными параметрами
    // (неотрицательные изначальный баланс, кредитный лимит и ставка кредитования)
    // изначальный баланс > 0
    @Test
    public void shouldAddToPositiveBalanceFromUpperZero() {
        CreditAccount account = new CreditAccount(
                1,
                5_000,
                15
        );

        account.add(3_000);

        Assertions.assertEquals(3_001, account.getBalance());
    }

    // № 3.3: Невозможность добавить нулевую сумму к счету с изначальными корректными параметрами
    // (неотрицательные изначальный баланс, кредитный лимит и ставка кредитования) - должен вернуть false
    @Test
    public void shouldNotAddZeroSum() {
        CreditAccount account = new CreditAccount(
                1,
                5_000,
                15
        );

        Assertions.assertEquals(false, account.add(0));
        int expected = 1;
        Assertions.assertEquals(expected, account.getBalance());
    }

    // № 3.4: Невозможность добавить отрицательную сумму к счету с изначальными корректными параметрами
    // (неотрицательные изначальный баланс, кредитный лимит и ставка кредитования) - должен вернуть false
    @Test
    public void shouldNotAddNegativeSum() {
        CreditAccount account = new CreditAccount(
                1,
                5_000,
                15
        );

        Assertions.assertEquals(false, account.add(-1));
        int expected = 1;
        Assertions.assertEquals(expected, account.getBalance());
    }

    // 4. Расчет процентов

    // № 4.1. Расчет при отрицательном балансе: проценты начисляются, деление целочисленное
    @Test
    public void shouldCalculateInterestWithNegativeBalance() {
        CreditAccount account = new CreditAccount(
                3_000,
                5_000,
                15
        );
        account.pay(3_200);
        int actual = account.yearChange();
        Assertions.assertEquals(-30, actual);
    }

    // № 4.2. Нет начисления процентов при балансе = 0
    @Test
    public void shouldNotCalculateInterestWithZeroBalance() {
        CreditAccount account = new CreditAccount(
                0,
                5_000,
                15
        );
        int actual = account.yearChange();
        Assertions.assertEquals(0, actual);
    }

    // № 4.3. Нет начисления процентов при балансе > 0, ставка кредитования = 15 %
    @Test
    public void shouldNotCalculateInterestWithPositiveBalanceRateIs15() {
        CreditAccount account = new CreditAccount(
                3_000,
                5_000,
                15
        );
        account.pay(2_800);
        int actual = account.yearChange();
        Assertions.assertEquals(0, actual);
    }

    // № 4.4. Нет начисления процентов при балансе > 0, ставка кредитования = 1 %
    @Test
    public void shouldNotCalculateInterestWithPositiveBalanceRateIs1() {
        CreditAccount account = new CreditAccount(
                3_000,
                5_000,
                1
        );
        account.pay(2_800);
        int actual = account.yearChange();
        Assertions.assertEquals(0, actual);
    }
}
