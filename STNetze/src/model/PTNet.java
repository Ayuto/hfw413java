package model;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;

public class PTNet {

	private Collection<Place> places;
	private Collection<Transition> transitions;
	private Collection<Relation> relations;
	private Map<Place, Integer> startMarkers;

	/**
	 * Creates a new P/T net.
	 */
	public PTNet() {
		this.places = new LinkedList<Place>();
		this.transitions = new LinkedList<Transition>();
		this.relations = new LinkedList<Relation>();
		this.startMarkers = new HashMap<Place, Integer>();
	}

	/**
	 * Adds the given place to the P/T net.
	 */
	public void addPlace(Place place) {
		this.places.add(place);
	}

	/**
	 * Adds the given relation to the P/T net.
	 */
	public void addRelation(Relation relation) {
		this.relations.add(relation);
	}

	/**
	 * Adds the given transition to the P/T net.
	 */
	public void addTransition(Transition transition) {
		this.transitions.add(transition);
	}

	/**
	 * Returns the amount of markers at the given place.
	 */
	public int getMarkers(Place place) {
		return this.startMarkers.get(place);
	}

	/**
	 * Sets the amount of markers at the given place.
	 */
	public void setMarkers(Place place, int value) {
		this.startMarkers.put(place, value);
	}

	/**
	 * Returns the pre-condition of the transition <transition> from the place
	 * <place>.
	 */
	private int getPre(Transition transition, Place place) {
		for (Relation current : this.relations) {
			if (current.getFrom().equals(place)
					&& current.getTo().equals(transition))
				return current.getQuantifier();
		}

		return 0;
	}

	/**
	 * Returns the post-condition of the transition <transition> to the place
	 * <place>.
	 */
	private int getPost(Transition transition, Place place) {
		for (Relation current : this.relations) {
			if (current.getFrom().equals(transition)
					&& current.getTo().equals(place))
				return current.getQuantifier();
		}

		return 0;
	}

	/**
	 * Returns the effect of the transition <transition> from the place <place>.
	 */
	private int getEffect(Transition transition, Place place) {
		return this.getPost(transition, place) - this.getPre(transition, place);
	}

	/**
	 * Returns true if the given transition is activated.
	 * 
	 * @param makers
	 *            The distribution of the markers that should be used to check
	 *            if the transition is activated.
	 */
	private boolean isActivated(Transition transition, Integer quantifier,
			Map<Place, Integer> markers) {
		for (Place place : this.places) {
			Integer temp = markers.get(place);
			int markersAtPlace = temp == null ? 0 : temp;
			int result = markersAtPlace - this.getPre(transition, place)
					* quantifier;

			if (result < 0) {
				return false;
			}
		}

		return true;
	}

	/**
	 * Does a single step for every transition in <vec> by using the current
	 * distribution of the markers.
	 * 
	 * @return The new distribution of the markers.
	 */
	public Map<Place, Integer> step(Map<Transition, Integer> vec,
			Map<Place, Integer> markers) {
		Map<Place, Integer> newMarkers = new HashMap<Place, Integer>();
		newMarkers.putAll(markers);

		for (Transition transition : vec.keySet()) {
			newMarkers.putAll(this.step(transition, vec.get(transition),
					newMarkers));
		}

		return newMarkers;
	}

	/**
	 * Does a single step for the given transition by using the current
	 * distribution of the markers.
	 * 
	 * @return The new distribution of the markers.
	 */
	private Map<Place, Integer> step(Transition transition, Integer quantifier,
			Map<Place, Integer> markers) {
		if (!this.isActivated(transition, quantifier, markers)) {
			throw new Error("Vector is not activated.");
		}

		Map<Place, Integer> newMarkers = new HashMap<Place, Integer>();

		for (Place place : this.places) {
			newMarkers.put(place,
					markers.get(place) + this.getEffect(transition, place)
							* quantifier);
		}

		return newMarkers;
	}

	/**
	 * Returns true if the P/T net is finite.
	 */
	public boolean isFinite() {
		for (Place place : this.places) {
			if (!this.startMarkers.containsKey(place)) {
				this.startMarkers.put(place, 0);
			}
		}
		Collection<Map<Place, Integer>> reachableStates = new LinkedList<Map<Place, Integer>>();
		reachableStates.add(this.startMarkers);
		Collection<Map<Place, Integer>> current = new LinkedList<Map<Place, Integer>>();
		current.add(this.startMarkers);

		while (!current.isEmpty()) {
			current = this.getNewSuccessors(current, reachableStates);
			reachableStates.addAll(current);
			if (this.checkMarkers(current))
				return false;
		}
		return true;
	}

	/**
	 * Returns true if the amount of start markers on every place is less than
	 * the the current amount of markers.
	 */
	private boolean checkMarkers(Collection<Map<Place, Integer>> markers) {
		boolean result = false;
		for (Map<Place, Integer> current : markers) {
			for (Place place : current.keySet()) {
				if (this.startMarkers.get(place) < current.get(place))
					result = true;
				else if (this.startMarkers.get(place) > current.get(place))
					return false;
			}
		}
		return result;
	}

	/**
	 * Returns true if the P/T net has cycles.
	 */
	public boolean hasCycle() {
		for (Place place : this.places) {
			if (!this.startMarkers.containsKey(place)) {
				this.startMarkers.put(place, 0);
			}
		}
		Collection<Map<Place, Integer>> reachableStates = new LinkedList<Map<Place, Integer>>();
		Collection<Map<Place, Integer>> current = new LinkedList<Map<Place, Integer>>();
		current.add(startMarkers);

		while (!current.isEmpty()
				&& !reachableStates.contains(this.startMarkers)) {
			current = this.getNewSuccessors(current, reachableStates);
			reachableStates.addAll(current);
		}
		return reachableStates.contains(this.startMarkers);
	}

	/**
	 * Calculates all new successors.
	 * 
	 * @param markers
	 *            The current distribution of markers.
	 * @param oldStates
	 *            The old states.
	 * @return A collection of new states.
	 */
	private Collection<Map<Place, Integer>> getNewSuccessors(
			Collection<Map<Place, Integer>> markers,
			Collection<Map<Place, Integer>> oldStates) {
		Collection<Map<Place, Integer>> result = new LinkedList<Map<Place, Integer>>();
		Collection<Map<Place, Integer>> tempOldStates = new LinkedList<Map<Place, Integer>>();
		tempOldStates.addAll(oldStates);
		for (Map<Place, Integer> marker : markers) {
			Collection<Map<Place, Integer>> temp = this.getAllNewSuccessors(
					marker, tempOldStates);
			result.addAll(temp);
			tempOldStates.addAll(temp);
		}
		return result;
	}

	/**
	 * Compares all successors and returns the collection of all new successors.
	 * 
	 * @param markers
	 *            The current distribution of markers.
	 * @param oldStates
	 *            The old states.
	 * @return A collection of new states.
	 */
	private Collection<Map<Place, Integer>> getAllNewSuccessors(
			Map<Place, Integer> markers,
			Collection<Map<Place, Integer>> oldStates) {
		Collection<Map<Place, Integer>> successors = this
				.getSuccessors(markers);
		Iterator<Map<Place, Integer>> iterator = successors.iterator();
		while (iterator.hasNext()) {
			if (oldStates.contains(iterator.next())) {
				iterator.remove();
			}
		}
		return successors;
	}

	/**
	 * Returns a collection of places after a single step from every activated
	 * transition.
	 * 
	 * @param marker
	 *            The current distribution of markers.
	 */
	private Collection<Map<Place, Integer>> getSuccessors(
			Map<Place, Integer> marker) {
		Collection<Map<Place, Integer>> successors = new LinkedList<Map<Place, Integer>>();
		for (Transition transition : this.getActivatedTransitions(marker)) {
			successors.add(this.step(transition, 1, marker));
		}
		return successors;
	}

	/**
	 * Returns a collection of activated transitions.
	 * 
	 * @param marker
	 *            The current distribution of markers.
	 */
	private Collection<Transition> getActivatedTransitions(
			Map<Place, Integer> markers) {
		Collection<Transition> result = new LinkedList<Transition>();

		for (Transition transition : this.transitions) {
			if (this.isActivated(transition, 1, markers)) {
				result.add(transition);
			}
		}

		return result;
	}
}
