package ru.yandex.practicum;

import org.junit.jupiter.api.*;
import ru.yandex.practicum.delivery.FragileParcel;
import ru.yandex.practicum.delivery.ParcelBox;
import ru.yandex.practicum.delivery.PerishableParcel;
import ru.yandex.practicum.delivery.StandardParcel;

import static org.junit.jupiter.api.Assertions.*;

public class DeliveryCostTest {
    StandardParcel standardParcel = new StandardParcel("description", 5, "Address", 2);
    FragileParcel fragileParcel = new FragileParcel("description", 25, "Address", 3);
    PerishableParcel perishableParcel = new PerishableParcel("description", 20, "Address", 3, 10);

    ParcelBox<FragileParcel> fragileList = new ParcelBox<>(50);
    FragileParcel fragileParcel1 = new FragileParcel("description", 25, "Address", 3);
    FragileParcel fragileParcel2 = new FragileParcel("description", 25, "Address", 3);

    @Test
    public void calculateDeliveryCostTestStandard() {
        int actual = standardParcel.calculateDeliveryCost();
        int extend = 10;
        assertEquals(extend, actual, "Не верный результат для стандартной посылки");
    }

    @Test
    public void calculateDeliveryCostTestFragile() {
        int actual = fragileParcel.calculateDeliveryCost();
        int extend = 100;
        assertEquals(extend, actual, "Не верный результат для хрупкой посылки");
    }

    @Test
    public void calculateDeliveryCostTestPerishable() {
        int actual = perishableParcel.calculateDeliveryCost();
        int extend = 60;
        assertEquals(extend, actual, "Не верный результат для скоропортящейся посылки");
    }

    @Test
    public void isExpiredTestFalse4() {
        boolean value = perishableParcel.isExpired(4);
        Assertions.assertFalse(value);
    }

    @Test
    public void isExpiredTestFalse13() {
        boolean value = perishableParcel.isExpired(13);
        Assertions.assertFalse(value);
    }

    @Test
    public void isExpiredTestTrue14() {
        boolean value = perishableParcel.isExpired(14);
        Assertions.assertTrue(value);
    }

    @Test
    public void addParcelTestTrue50() {
        fragileList.addParcel(fragileParcel);
        boolean value = fragileList.addParcel(fragileParcel1);
        assertTrue(value);
    }

    @Test
    public void addParcelTestFalse50() {
        fragileList.addParcel(fragileParcel);
        fragileList.addParcel(fragileParcel1);
        boolean value = fragileList.addParcel(fragileParcel2);
        assertFalse(value);

    }
}
