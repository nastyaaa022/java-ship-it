package ru.yandex.practicum.delivery;

import java.util.ArrayList;
import java.util.List;

public class ParcelBox<T extends Parcel> {
    private final int maxWeight;
    private int currentWeight = 0;

    List<T> boxList = new ArrayList<>();

    public ParcelBox(int maxWeight) {
        this.maxWeight = maxWeight;
    }

    public boolean addParcel(T parcel) {
        boolean result;

        if ((currentWeight + parcel.getWeight()) <= maxWeight) {
            boxList.add(parcel);
            currentWeight += parcel.getWeight();
            result = true;
        } else {
            System.out.println("Превышен максимальный вес коробки.");
            result = false;
        }
        return result;
    }

    public List<T> getAllParcels() {
        return boxList;
    }
}
