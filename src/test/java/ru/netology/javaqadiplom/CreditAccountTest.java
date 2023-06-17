package ru.netology.javaqadiplom;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CreditAccountTest {

    // Создание счета: корректные параметры, граничные значения.
    //              Начальный баланс = 0 (тест № 1), Начальный баланс = 1 (тест № 2)

    // № 1.1             Кредитный лимит = 0
    @Test
    public void shouldCreateWithZeroLimit() {
        CreditAccount account = new CreditAccount(
                0,
                0,
                15
        );
        Assertions.assertEquals(0, account.getCreditLimit());

    }
    // № 1.2             Кредитный лимит = 1
    @Test
    public void shouldCreateWithLimitIs1() {
        CreditAccount account = new CreditAccount(
                0,
                1,
                15
        );
        Assertions.assertEquals(1, account.getCreditLimit());

    }

    // № 1.3           Ставка кредитования = 0
    @Test
    public void shouldCreateWithZeroRate() {
        CreditAccount account = new CreditAccount(
                0,
                1,
                0
        );
        Assertions.assertEquals(0, account.getRate());
    }

    // № 1.4           Ставка кредитования = 1
    @Test
    public void shouldCreateWithRateIs1() {
        CreditAccount account = new CreditAccount(
                1_000,
                15_000,
                1
        );
        Assertions.assertEquals(1, account.getRate());
    }

    // Создание счета: некорректные параметры.

    // № 1.5: Невозможность введения некорректного параметра - отрицательного изначального баланса
    @Test
    public void shouldNotCreateNegativeBalance() {

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            CreditAccount account = new CreditAccount(
                    -1,
                    5_000,
                    15
            );
        });
    }

    // № 1.6. Невозможность введения некорректного параметра - отрицательного кредитного лимита
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

    // № 1.7. Невозможность введения некорректного параметра - отрицательной ставки кредитования
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

    // № 2.1. сумма покупки > 0, конечный баланс > 0

    // № 2.2. сумма покупки > 0, конечный баланс = 0

    // № 2.3. сумма покупки > 0, конечный баланс < 0, < размера кредитного лимита

    // № 2.4. сумма покупки > 0, конечный баланс < 0, = размеру кредитного лимита

    // № 2.5. сумма покупки > 0, конечный баланс < 0, > размера кредитного лимита (невозможно)

    // № 2.6. невозможность проведения покупки с суммой = 0

    // № 2.7. невозможность проведения покупки с отрицательной суммой




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
    // (неотрицательные изначальный баланс, кредитный лимит и ставка кредитования)
    @Test
    public void shouldNotAddZeroSum() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            CreditAccount account = new CreditAccount(
                    0,
                    5_000,
                    15
            );
            account.add(0);
        });
    }

    // № 3.4: Невозможность добавить отрицательную сумму к счету с изначальными корректными параметрами
    // (неотрицательные изначальный баланс, кредитный лимит и ставка кредитования)
    @Test
    public void shouldNotAddNegativeSum() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            CreditAccount account = new CreditAccount(
                    0,
                    5_000,
                    15
            );
            account.add(-3_000);
        });
    }

    // 4. Расчет процентов
    // № 4.1. Расчет при отрицательном балансе: проценты начисляются, деление целочисленное
    // № 4.2. Нет начисления процентов при балансе = 0
    // № 4.3. Нет начисления при балансе > 0
}
