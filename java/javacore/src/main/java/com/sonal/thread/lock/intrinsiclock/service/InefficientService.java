package com.sonal.thread.lock.intrinsiclock.service;

public class InefficientService {

    private int messageACount;

    private int messageBCount;

    public synchronized void sendMessageA() throws InterruptedException {
	System.out.println("Sending MessageA");
	Thread.sleep(3000);
	System.out.println("MessageA Sent");
	messageACount++;
    }

    public synchronized void sendMessageB() throws InterruptedException {
	System.out.println("Sending MessageB");
	Thread.sleep(3000);
	System.out.println("MessageB Sent");
	messageBCount++;
    }
    
    public int getMessageACount(){
	return messageACount;
    }
    
    public int getMessageBCount(){
   	return messageBCount;
       }

}
