import model.Counter;
import view.CounterObserver;


public class Starter {

	public static void main(String[] args) {
		final int InitialMargin = 50;
		final int MarginDelta = 200;
		int currentMargin = InitialMargin;
		Counter theCounter1 = Counter.createCounter();
		Counter theCounter2 = Counter.createCounter();
		CounterObserver observer1 = CounterObserver.createCounterObserver(theCounter1,theCounter2);
		observer1.setLocation(currentMargin, currentMargin);
		observer1.setVisible(true);
		currentMargin = currentMargin + MarginDelta;
		CounterObserver observer2 = CounterObserver.createCounterObserver(theCounter1,theCounter2);
		observer2.setLocation(currentMargin, currentMargin);
		observer2.setVisible(true);
		currentMargin = currentMargin + MarginDelta;
		CounterObserver observer3 = CounterObserver.createCounterObserver(theCounter1,theCounter2);
		observer3.setLocation(currentMargin, currentMargin);
		observer3.setVisible(true);
	}

}
