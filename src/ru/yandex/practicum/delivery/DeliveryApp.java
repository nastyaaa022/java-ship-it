package ru.yandex.practicum.delivery;

import java.util.List;
import java.util.Scanner;

public class DeliveryApp {

    static final Scanner scanner = new Scanner(System.in);
    private static final ParcelBox<FragileParcel> fragileParcels = new ParcelBox<>(100);
    private static final ParcelBox<StandardParcel> standardParcel = new ParcelBox<>(100);
    private static final ParcelBox<PerishableParcel> perishableParcel = new ParcelBox<>(100);

    public static void main(String[] args) {
        List<FragileParcel> fragileParcelsList;
        boolean running = true;
        while (running) {
            showMenu();
            int choice;
            if (scanner.hasNextInt()) {
                choice = Integer.parseInt(scanner.nextLine());

                switch (choice) {
                    case 1:
                        addParcel();
                        break;
                    case 2:
                        sendParcels();
                        break;
                    case 3:
                        calculateCosts();
                        break;
                    case 4:
                        System.out.println("Введите локацию ");
                        String newLocation = scanner.nextLine();
                        fragileParcelsList = fragileParcels.getAllParcels();
                        if (fragileParcelsList.isEmpty()) {
                            System.out.println("Посылок для отслеживания пока нет.");
                        } else {
                            for (FragileParcel p : fragileParcelsList) {
                                p.reportStatus(newLocation);
                            }
                        }
                        break;
                    case 5:
                        System.out.println("Содержимое какой коробки вы хотите посмореть? Выберите номер:");
                        System.out.println("1. Стандартную");
                        System.out.println("2. Хрупкую");
                        System.out.println("3. Скоропортящуюся");
                        String number = scanner.nextLine();
                        switch (number) {
                            case "1":
                                List<StandardParcel> standardParcelsList = standardParcel.getAllParcels();
                                if (standardParcelsList.isEmpty()) {
                                    System.out.println("В коробке пока нет писем.");
                                } else {
                                    for (StandardParcel sp : standardParcelsList) {
                                        System.out.println(sp);
                                    }
                                }
                                break;
                            case "2":
                                fragileParcelsList = fragileParcels.getAllParcels();
                                if (fragileParcelsList.isEmpty()) {
                                    System.out.println("Посылок для отслеживания пока нет.");
                                } else {
                                    for (FragileParcel fp : fragileParcelsList) {
                                        System.out.println(fp);
                                    }
                                }
                                break;
                            case "3":
                                List<PerishableParcel> perishableParcelsList = perishableParcel.getAllParcels();
                                if (perishableParcelsList.isEmpty()) {
                                    System.out.println("Посылок для отслеживания пока нет.");
                                } else {
                                    for (PerishableParcel pp : perishableParcelsList) {
                                        System.out.println(pp);
                                    }
                                }
                                break;
                            default:
                                System.out.println("Такой коробки еще не существует!");
                                break;
                        }
                        break;
                    case 0:
                        System.out.println("До скорых встреч!");
                        running = false;
                        break;
                    default:
                        System.out.println("Такого пункта пока нет, попробуйте выбрать другой!");
                }
            } else {
                System.out.println("Пожалуйста, введите корректное число.");
                scanner.nextLine();
            }
        }
    }

    private static void showMenu() {
        System.out.println("Выберите действие:");
        System.out.println("1 — Добавить посылку");
        System.out.println("2 — Отправить все посылки");
        System.out.println("3 — Посчитать стоимость доставки");
        System.out.println("4 — Отследить треккинг доставки");
        System.out.println("5 — Показать содержимое коробки");
        System.out.println("0 — Завершить");
    }

    private static void addParcel() {
        System.out.println("Какую посылку вы хотите отправить? Выберите номер:");
        System.out.println("1. Стандартную");
        System.out.println("2. Хрупкую");
        System.out.println("3. Скоропортящуюся");

        int choice;
        if (scanner.hasNextInt()) {
            choice = Integer.parseInt(scanner.nextLine());
            Parcel parcel = createParcel(choice);

            if (parcel != null) {
                boolean added = false;
                switch (choice) {
                    case 1:
                        added = standardParcel.addParcel((StandardParcel) parcel);
                        break;
                    case 2:
                        added = fragileParcels.addParcel((FragileParcel) parcel);
                        break;
                    case 3:
                        added = perishableParcel.addParcel((PerishableParcel) parcel);
                        break;
                }
                if (added) {
                    System.out.println("Посылка добавлена.");
                } else {
                    System.out.println("Не удалось добавить посылку.");
                }
            } else {
                System.out.println("Такого вида посылки пока нет!");
            }
        } else {
            System.out.println("Пожалуйста, введите корректное число.");
            scanner.nextLine();
        }
    }

    private static Parcel createParcel(int choice) {
        switch (choice) {
            case 1:
                return StandardParcel.createStandardParcel();
            case 2:
                return FragileParcel.createFragileParcel();
            case 3:
                return PerishableParcel.createPerishableParcel();
            default:
                return null;
        }
    }

    private static void sendParcels() {
        for (StandardParcel p : standardParcel.getAllParcels()) {
            p.packageItem();
            p.deliver();
        }

        for (PerishableParcel p : perishableParcel.getAllParcels()) {
            p.packageItem();
            p.deliver();
        }

        for (FragileParcel p : fragileParcels.getAllParcels()) {
            p.packageItem();
            p.deliver();
        }
    }

    private static void calculateCosts() {
        int totalCost = 0;
        for (StandardParcel p : standardParcel.getAllParcels()) {
            totalCost += p.calculateDeliveryCost();
        }

        for (PerishableParcel p : perishableParcel.getAllParcels()) {
            totalCost += p.calculateDeliveryCost();
        }

        for (FragileParcel p : fragileParcels.getAllParcels()) {
            totalCost += p.calculateDeliveryCost();
        }
        System.out.println("Общая стоимость всех доставок: " + totalCost);
    }
}

