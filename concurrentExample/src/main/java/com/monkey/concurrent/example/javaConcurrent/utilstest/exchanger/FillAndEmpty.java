package com.monkey.concurrent.example.javaConcurrent.utilstest.exchanger;

import java.nio.Buffer;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.Exchanger;

/**
 * FillAndEmpty
 *
 * @author yong.han
 * 2019/1/9
 */
public class FillAndEmpty {
    Exchanger<DataBuffer> exchanger = new Exchanger<>();

    int fillCounter = 0;

    DataBuffer initialEmptyBuffer = new DataBuffer(10);
    DataBuffer initialFullBuffer = new DataBuffer(10);


    public static void main(String[] args) {
//        start();
        new FillAndEmpty().start();
    }

    public void start() {
        new Thread(new FillingLoop()).start();
        new Thread(new EmptingLoop()).start();
    }



    private void addToBuffer(DataBuffer currentBuffer) {
        String add ="times : " +  this.fillCounter++;
        currentBuffer.add(add);
        System.out.println("add to buffer [" + add + "] : " + currentBuffer);
    }

    private void takeFromBuffer(DataBuffer currentBuffer) {
//        try {
//            Thread.sleep(20L);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

        System.out.println("pop from buffer [" + currentBuffer.pop() + "] : " + currentBuffer);
    }


    class FillingLoop implements Runnable {
        @Override
        public void run() {
            DataBuffer currentBuffer = initialEmptyBuffer;
            try {
                while (currentBuffer != null) {
                    addToBuffer(currentBuffer);
                    if (currentBuffer.isFull()) {
                        currentBuffer = exchanger.exchange(currentBuffer);
                        System.out.println("exchanged empty with fill");
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    class EmptingLoop implements Runnable {
        @Override
        public void run() {
            DataBuffer currentBuffer = initialFullBuffer;
            try {
                while (currentBuffer != null) {
                    takeFromBuffer(currentBuffer);
                    if (currentBuffer.isEmpty()) {
                        currentBuffer = exchanger.exchange(currentBuffer);
                        System.out.println("exchanged fill with empty");

                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static class DataBuffer {
        private Queue<String> queue;
        private int capacity;

        public DataBuffer(int capacity) {
            this.queue = new LinkedList<>();
            this.capacity = capacity;
        }
        public String pop() {
//            if (isEmpty()) {
//                throw new RuntimeException("DataBuffer is empty, cannot pop more element");
//            }
            return this.queue.poll();
        }
        public void add(String element) {
            if (isFull()) {
                throw new RuntimeException("DataBuffer is full, cannot add more element");
            }
            this.queue.add(element);
        }

        public boolean isFull() {
            return capacity == queue.size();
        }
        public boolean isEmpty() {
            return queue.size() == 0;
        }
    }



}
