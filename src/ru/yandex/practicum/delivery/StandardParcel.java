package ru.yandex.practicum.delivery;

import static ru.yandex.practicum.delivery.DeliveryApp.scanner;

public class StandardParcel extends Parcel {
    protected static final int BASE_COST = 2;

    public StandardParcel(String description, int weight, String deliveryAddress, int sendDay) {
        super(description, weight, deliveryAddress, sendDay);
    }

    @Override
    public int calculateDeliveryCost() {
        return BASE_COST * weight;
    }

    public static StandardParcel createStandardParcel() {
        System.out.println("Укажите описание посылки:");
        String description = scanner.nextLine();

        System.out.println("Укажите вес посылки:");
        int weight;
        while (true) {
            if (scanner.hasNextInt()) {
                weight = scanner.nextInt();
                scanner.nextLine();
                break;
            } else {
                System.out.println("Пожалуйста, введите корректное число.");
                scanner.nextLine();
            }
        }

        System.out.println("Укажите адрес посылки:");
        String deliveryAddress = scanner.nextLine();

        System.out.println("Укажите день отправки посылки:");
        int sendDay;
        while (true) {
            if (scanner.hasNextInt()) {
                sendDay = scanner.nextInt();
                scanner.nextLine();
                break;
            } else {
                System.out.println("Пожалуйста, введите корректное число.");
                scanner.nextLine();
            }
        }
        return new StandardParcel(description, weight, deliveryAddress, sendDay);
    }
}
