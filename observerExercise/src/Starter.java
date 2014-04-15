import model.Counter;
import view.CounterObserver;


public class Starter {

	public static void main(String[] args) {
		final int InitialMargin = 50;
		final int MarginDelta = 100;
		int currentMargin = InitialMargin;
		Counter theCounter = Counter.createCounter();
		CounterObserver observer1 = CounterObserver.createCounterObserver(theCounter);
		observer1.setLocation(currentMargin, currentMargin);
		observer1.setVisible(true);
		currentMargin = currentMargin + MarginDelta;
		CounterObserver observer2 = CounterObserver.createCounterObserver(theCounter);
		observer2.setLocation(currentMargin, currentMargin);
		observer2.setVisible(true);
		currentMargin = currentMargin + MarginDelta;
		CounterObserver observer3 = CounterObserver.createCounterObserver(theCounter);
		observer3.setLocation(currentMargin, currentMargin);
		observer3.setVisible(true);
	}

}
