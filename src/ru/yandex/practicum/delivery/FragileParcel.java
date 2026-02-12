package ru.yandex.practicum.delivery;

import static ru.yandex.practicum.delivery.DeliveryApp.scanner;

public class FragileParcel extends Parcel implements Trackable {

    protected static final int BASE_COST = 4;

    public FragileParcel(String description, int weight, String deliveryAddress, int sendDay) {
        super(description, weight, deliveryAddress, sendDay);
    }

    @Override
    public void packageItem() {
        System.out.println("Посылка: " + description + " обернута в защитную пленку.");
        super.packageItem();
    }

    @Override
    public int calculateDeliveryCost(int weight) {
        return BASE_COST * weight;
    }

    public static FragileParcel createFragileParcel() {
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
        return new FragileParcel(description, weight, deliveryAddress, sendDay);
    }

    @Override
    public void reportStatus(String newLocation) {
        System.out.println("Хрупкая посылка " + description + " изменила местоположение на " + newLocation);
    }
}
