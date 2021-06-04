package crackCodes;

import java.util.concurrent.Semaphore;

public class TheadBasics {

	public static void main(String[] args) throws InterruptedException {

		sharedprinter sp = new sharedprinter();

		Thread tt = new Thread(new Runnable() {

			@Override
			public void run() {
				int i = 1;
				while (i < 25) {
					try {
						sp2.printEven(i);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} finally {
						i = i + 2;
					}

				}
			}

		});

		Thread tt1 = new Thread(new Runnable() {

			@Override
			public void run() {
				int i = 0;
				while (i < 25) {
					try {
						sp.printOdd(i + 2);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} finally {
						i = i + 2;
					}
					
				}
			}

		});

		tt.start();
		tt1.start();

		tt.join();

		tt1.join();
	}

}

class sharedprinter2 {
	boolean flag = false;
	
	 void printOdd(int n) throws InterruptedException {
		while(!flag)
		{
			  try {
	                wait();
	            } catch (InterruptedException e) {
	                Thread.currentThread().interrupt();
	            }
		}
		
		System.out.println(n + " ");
		flag = true; 
		notify();
	}

	void printEven(int n) throws InterruptedException {
		while(flag)
		{
			  try {
	                wait();
	            } catch (InterruptedException e) {
	                Thread.currentThread().interrupt();
	            }
		}
		
			System.out.println(n +" ");
	        flag = false;
	        notify();
		
	}
}