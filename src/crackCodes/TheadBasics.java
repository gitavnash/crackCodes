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
						sp.printEven(i);
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
					}
					i = i + 2;
				}
			}

		});

		tt.start();
		tt1.start();

		tt.join();

		tt1.join();
	}

}

class sharedprinter {
	static Semaphore odd = new Semaphore(0);;
	static Semaphore even = new Semaphore(1);;

	static void printOdd(int n) throws InterruptedException {
		odd.acquire();
		System.out.println(n + " ");
		even.release();
	}

	static void printEven(int n) throws InterruptedException {
		even.acquire();
		System.out.println(n + " ");
		odd.release();
	}
}