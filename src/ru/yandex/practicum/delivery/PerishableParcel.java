package ru.yandex.practicum.delivery;

import static ru.yandex.practicum.delivery.DeliveryApp.scanner;

public class PerishableParcel extends Parcel {
    private final int timeToLive; //срок хранения
    protected static final int BASE_COST = 3;

    public PerishableParcel(String description, int weight, String deliveryAddress, int sendDay, int timeToLive) {
        super(description, weight, deliveryAddress, sendDay);
        this.timeToLive = timeToLive;

    }

    public boolean isExpired(int currentDay) {
        int sum = sendDay + timeToLive;
        return sum < currentDay;
    }

    @Override
    public int calculateDeliveryCost() {
        return BASE_COST * weight;
    }

    public static PerishableParcel createPerishableParcel() {
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

        System.out.println("Укажите срок хранения посылки:");
        int timeLive;
        while (true) {
            if (scanner.hasNextInt()) {
                timeLive = scanner.nextInt();
                scanner.nextLine();
                break;
            } else {
                System.out.println("Пожалуйста, введите корректное число.");
                scanner.nextLine();
            }
        }
        return new PerishableParcel(description, weight, deliveryAddress, sendDay, timeLive);
    }

    @Override
    public String toString() {
        return super.toString().replace("}", "") +
                ", timeToLive=" + timeToLive +
                '}';
    }
}
