//package common;
//
//import java.util.concurrent.TimeUnit;
//
///**
// *
// * @author jayle
// */
//public class InClass implements Runnable{
//    
//    private volatile boolean stopFlag = false;
//    
//    public void stop(){
//        stopFlag= true;
//        
//    }
//    
//    
//    @Override
//    public void run(){
//        while(!stopFlag){
//        
//        System.out.println(Thread.currentThread().getName());
//        }
//    }
//    public static void main(String[] args) {
//        InClass ic = new InClass();
//        Thread t0 = new Thread (ic,"T0");
//        t0.start();
//        Thread t1 = new Thread ( () -> {
//        try {
//            System.out.println(Thread.currentThread().getName());
//            TimeUnit.MINUTES.sleep (1);
//            ic.stop();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        }, "T1");
//        t1.start();
//        t1.join();
//    }
//
//}
