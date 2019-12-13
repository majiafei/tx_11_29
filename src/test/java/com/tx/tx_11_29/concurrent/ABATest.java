package com.tx.tx_11_29.concurrent;

import org.junit.jupiter.api.Test;

import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicStampedReference;

public class ABATest {



    public static void main(String[] args) {
        AtomicReference<Integer> atomicReference = new AtomicReference<>(1);
        new Thread(() -> {
            atomicReference.compareAndSet(1, 2);
            atomicReference.compareAndSet(2, 1);
            System.out.println(atomicReference.get());
        }).start();

        new Thread(() -> {
            try {
                Thread.sleep(1000);
                atomicReference.compareAndSet(1, 3);
                System.out.println(atomicReference.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }).start();

        /**************************解决aba问题***************************/
        AtomicStampedReference<Integer> atomicStampedReference = new AtomicStampedReference<>(5, 1);
        new Thread(() -> {
            atomicStampedReference.compareAndSet(5, 6, 1, atomicStampedReference.getStamp() +1);
            atomicStampedReference.compareAndSet(6, 5, 2, atomicStampedReference.getStamp() +1);
        }).start();

        new Thread(() -> {
            try {
                Thread.sleep(1000);
                atomicStampedReference.compareAndSet(5, 7, 1, atomicStampedReference.getStamp() + 1);
                System.out.println(atomicStampedReference.getReference());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }).start();
    }

}
