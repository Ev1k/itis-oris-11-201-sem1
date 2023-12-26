package com.example.sqrltrbl.util;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class SleepyRunnable implements Runnable {
    private final int nanosPerUpdate;
    private final Runnable task;

    @Override
    public void run() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        while (true) {
            long start = System.nanoTime();

            task.run();

            long stop = System.nanoTime();
            long elapsed = stop - start;
            long sleep = nanosPerUpdate - elapsed;

            try {
                Thread.sleep(sleep / 1000000, (int)(sleep % 1000000));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
