package model;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Vector;


public class Product extends ComponentCommon {

	private static final String CycleMessage = "Zyklen sind in der Aufbaustruktur nicht erlaubt!";

	public static Product create(final String name) {
		return new Product(name,new HashMap<String, QuantifiedComponent>());
	}
	private final HashMap<String,QuantifiedComponent> components;
	
	protected Product(final String name, final HashMap<String,QuantifiedComponent> components) {
		super(name);
		this.components = components;
	}

	@Override
	public void addPart(final Component part, final int amount) throws Exception{
		if (part.contains(this))throw new Exception(CycleMessage);
		final String partName = part.getName();
		if (this.getComponents().containsKey(partName)){
			final QuantifiedComponent oldQuantification = this.getComponents().get(partName); 
			oldQuantification.addQuantity(amount);
		}else{
			this.getComponents().put(partName, QuantifiedComponent.createQuantifiedComponent(amount, part));
		}
	}

	private HashMap<String,QuantifiedComponent> getComponents() {
		return this.components;
	}

	@Override
	public boolean contains(final Component component) {
		if (this.equals(component)) return true;
		final Iterator<QuantifiedComponent> i = this.getComponents().values().iterator();
		while (i.hasNext()){
			final QuantifiedComponent current = i.next();
			if (current.contains(component))return true;
		}
		return false;
	}

	@Override
	public Vector<QuantifiedComponent> getDirectParts() {
		return new Vector<QuantifiedComponent>(this.getComponents().values());
	}

	@Override
	public int getNumberOfMaterials() {
		int result = 0;
		final Iterator<QuantifiedComponent> i = this.getComponents().values().iterator();
		while (i.hasNext()){
			final QuantifiedComponent current = i.next();
			result = result + current.getNumberOfMaterials();
		}
		return result;
	}


}
