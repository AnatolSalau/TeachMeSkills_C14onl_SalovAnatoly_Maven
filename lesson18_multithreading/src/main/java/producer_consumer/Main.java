package producer_consumer;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        ProducerConsumerCreator producerConsumerCreator =
                new ProducerConsumerCreator();
        producerConsumerCreator.start();
    }
}
