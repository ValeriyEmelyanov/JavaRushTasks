package com.javarush.task.task27.task2712.ad;

public class Advertisement {
    private Object content;     // видео
    private String name;        // имя/название
    private long initialAmount; // начальная сумма, стоимость рекламы в копейках
    private int hits;           // количество оплаченных показов
    private int duration;       // продолжительность в секундах

    // стоимость одного показа рекламного объявления в копейках
    private long amountPerOneDisplaying;

    public Advertisement(Object content, String name, long initialAmount, int hits, int duration) {
        this.content = content;
        this.name = name;
        this.initialAmount = initialAmount;
        this.hits = hits;
        this.duration = duration;

        amountPerOneDisplaying = (hits != 0) ? initialAmount / hits : 0;
    }

    public String getName() {
        return name;
    }

    /**
     * Возвращает продолжительность рекламы в секунда.
     *
     * @return продолжительность рекламы в секунда
     */
    public int getDuration() {
        return duration;
    }

    /**
     * Возвращает стоимость одного показа рекламного объявления в копейках
     *
     * @return стоимость одного показа рекламного объявления в копейках
     */
    public long getAmountPerOneDisplaying() {
        return amountPerOneDisplaying;
    }

    public void revalidate() throws UnsupportedOperationException {
        hits--;
        if (hits < 0) {
            //throw new UnsupportedOperationException();
            throw new NoVideoAvailableException();
        }
    }

    public int getHits() {
        return hits;
    }
}
