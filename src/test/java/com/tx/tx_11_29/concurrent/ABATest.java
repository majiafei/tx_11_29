package com.tx.tx_11_29.concurrent;

import com.google.common.collect.Lists;
import com.tx.tx_11_29.entity.TbUser;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicStampedReference;

public class ABATest {



/*    public static void main(String[] args) {
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

        *//**************************解决aba问题***************************//*
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
    }*/


    @Test
    public void testConcurrentHashMap() {
        ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap();
        concurrentHashMap.put("ha", "hxx");
    }

    @Test
    public void testSort() {
        List<Long> list1 = Lists.newArrayList(3L, 4L);

        TbUser user1 = new TbUser();
        user1.setUserId(1L);
        TbUser u2 = new TbUser();
        u2.setUserId(2L);
        TbUser u3 = new TbUser();
        u3.setUserId(3L);
        TbUser u4 = new TbUser();
        u4.setUserId(4L);

        LinkedList<TbUser> list = Lists.newLinkedList();
        list.add(u2);
        list.add(user1);
        list.add(u3);
        list.add(u4);

        Collections.sort(list, new Comparator<TbUser>(){
            @Override
            public int compare(TbUser p1, TbUser p2) {
               if (list1.contains(p1.getUserId()) && !list1.contains(p2.getUserId())) {
                   return -1;
               } else if (!list1.contains(p1.getUserId()) && list1.contains(p2.getUserId())){
                   return 1;
               } else {
                   return 0;
               }
            }
        });

        System.out.println(list);
    }

}
