package model;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Vector;

import lock.Lock;
import observer.Observer;
import cache.CacheState;
import cache.NothingCached;
import events.Event;
import events.EventVisitor;
import events.PriceChangedEvent;
import events.StructureChangedEvent;

public class Product extends ComponentCommon implements Observer {

	private static final String CycleMessage = "Zyklen sind in der Aufbaustruktur nicht erlaubt!";

	/**
	 * Creates a new Product with the name <name>, the price <price> and without
	 * any subcomponents.
	 */
	public static Product create(final String name, final int price) {
		return new Product(name, price,
				new HashMap<String, QuantifiedComponent>());
	}

	private final HashMap<String, QuantifiedComponent> components;
	private CacheState cacheState;
	private final Lock finished;
	private MaterialList result;
	private final Lock mutex;
	private int threadCount;
	private int allStartedThreadCount;

	protected Product(final String name, final int price,
			final HashMap<String, QuantifiedComponent> components) {
		super(name, price);
		this.components = components;
		this.setCacheState(NothingCached.create(this));
		this.finished = new Lock(true);
		this.mutex = new Lock(false);
	}

	@Override
	public void addPart(final Component part, final int amount)
			throws Exception {
		if (part.contains(this)) {
			throw new Exception(Product.CycleMessage);
		}
		final String partName = part.getName();
		if (this.getComponents().containsKey(partName)) {
			final QuantifiedComponent oldQuantification = this.getComponents()
					.get(partName);
			oldQuantification.addQuantity(amount);
		} else {
			this.getComponents()
					.put(partName,
							QuantifiedComponent.createQuantifiedComponent(
									amount, part));
			part.register(this);
		}
		final Event event = StructureChangedEvent.create();
		this.notifyObservers(event);
		this.update(event);
	}

	private HashMap<String, QuantifiedComponent> getComponents() {
		return this.components;
	}

	@Override
	public boolean contains(final Component component) {
		if (this.equals(component)) {
			return true;
		}
		final Iterator<QuantifiedComponent> i = this.getComponents().values()
				.iterator();
		while (i.hasNext()) {
			final QuantifiedComponent current = i.next();
			if (current.contains(component)) {
				return true;
			}
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
		final Iterator<QuantifiedComponent> i = this.getComponents().values()
				.iterator();
		while (i.hasNext()) {
			final QuantifiedComponent current = i.next();
			result = result + current.getNumberOfMaterials();
		}
		return result;
	}

	/**
	 * Calculates the current price and returns it.
	 */
	public int calculatePrice() {
		int result = super.getPrice();
		final Iterator<QuantifiedComponent> subComponents = this
				.getComponents().values().iterator();
		while (subComponents.hasNext()) {
			final QuantifiedComponent current = subComponents.next();
			result += current.getPrice();
		}
		return result;
	}

	@Override
	public void setPrice(final int price) {
		super.setPrice(price);
		this.update(PriceChangedEvent.create());
	}

	@Override
	public int getPrice() {
		return this.getCacheState().getPrice();
	}

	/**
	 * Calculates the current list with all materials needed to build <this> and
	 * returns it.
	 */
	public synchronized MaterialList calculateMaterialList() {
		this.threadCount = 0;
		final Iterator<QuantifiedComponent> i = this.getDirectParts()
				.iterator();
		
		 this.result = MaterialList
				.create(new Vector<QuantifiedComponent>());
		
		boolean created = false;
		while (i.hasNext()) {
			created = true;
			final QuantifiedComponent current = i.next();
			this.threadCount++;
			this.allStartedThreadCount++;
			new MaterialListCalculator(this, current).start();
		}
		
		if (created) { 
			this.finished.lock();
		}
		return this.result;
	}
	
	public void threadEnds(final MaterialList result) {
		this.mutex.lock();
		this.result = this.result.add(result);
		
		this.threadCount--;
		if (this.threadCount == 0) {
			this.finished.unlock();
		}
		
		this.mutex.unlock();
	}

	@Override
	public MaterialList getMaterialList() {
		return this.getCacheState().getMaterialList();
	}

	@Override
	public boolean equals(final Object argument) {
		if (argument instanceof Product) {
			final Product argumentAsProduct = (Product) argument;
			return this.getComponents().equals(
					argumentAsProduct.getComponents())
					&& this.getName().equals(argumentAsProduct.getName())
					&& this.getPrice() == argumentAsProduct.getPrice();
		}
		return false;
	}

	@Override
	public void update(final Event event) {
		event.accept(new EventVisitor() {
			@Override
			public void handleStructureChangedEvent(final Event event) {
				Product.this.getCacheState().structureChanged();
			}

			@Override
			public void handlePriceChangedEvent(final Event event) {
				Product.this.getCacheState().priceChanged();
			}
		});
	}

	/**
	 * Returns the current cache state
	 */
	public CacheState getCacheState() {
		return this.cacheState;
	}

	/**
	 * Sets the current cache state to <cacheState>
	 */
	public void setCacheState(final CacheState cacheState) {
		this.cacheState = cacheState;
	}

	@Override
	public int containsHowOften(final Component component) {
		if (this.equals(component)) {
			return 1;
		}
		
		int result = 0;
		
		final Iterator<QuantifiedComponent> i = this.getComponents().values().iterator();
		while(i.hasNext())
		{
			final QuantifiedComponent current = i.next();
			result += current.containsHowOften(component);
		}
		
		return result;
	}

	public int getStartedThreadCount() {
		return this.allStartedThreadCount;
	}
}
