package application;
import model.ConcreteObservee;
import view.ConcreteObserveeView;
import view.ConcreteObserverView;

public class Application {

	private static ConcreteObserveeView observeeView;
	private static ConcreteObservee observee;
	
	public static ConcreteObservee getObservee(){
		return observee;
	}
	
	public static void main(String[] args) {
		int leftMargin = 100;
		int topMargin = 100;
		int deltaTopMargin = 65;
		observeeView = new ConcreteObserveeView();
		observee = new ConcreteObservee();
		observeeView.setObservee(observee);
		observeeView.setLocation(leftMargin,topMargin);
		observeeView.setVisible(true);
		for(int i = 0; i < 4; i++){
			ConcreteObserverView observer = new ConcreteObserverView();
			topMargin = topMargin + deltaTopMargin;
			observer.setLocation(leftMargin,topMargin);
			observer.setVisible(true);
			observer.registerParallelCommand();
		}
		
	}
}
