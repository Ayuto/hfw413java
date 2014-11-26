package model;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;

public class Net {

	private Collection<Position> positions;
	private Collection<Transition> transitions;
	private Collection<Relation> relations;
	private Map<Position, Integer> startMarkers;

	public Net() {
		this.positions = new LinkedList<Position>();
		this.transitions = new LinkedList<Transition>();
		this.relations = new LinkedList<Relation>();
		this.startMarkers = new HashMap<Position, Integer>();
	}

	public void addPosition(Position position) {
		this.positions.add(position);
	}

	public void addRelation(Relation relation) {
		this.relations.add(relation);
	}

	public void addTransition(Transition transition) {
		this.transitions.add(transition);
	}

	public int getMarkers(Position position) {
		return this.startMarkers.get(position);
	}

	public void setMarkers(Position position, int value) {
		this.startMarkers.put(position, value);
	}

	private int getPre(Transition transition, Position position) {
		for (Relation current : this.relations) {
			if (current.getFrom().equals(position)
					&& current.getTo().equals(transition))
				return current.getQuantifier();
		}

		return 0;
	}

	private int getPost(Transition transition, Position position) {
		for (Relation current : this.relations) {
			if (current.getFrom().equals(transition)
					&& current.getTo().equals(position))
				return current.getQuantifier();
		}

		return 0;
	}

	private int getEffect(Transition transition, Position position) {
		return this.getPost(transition, position)
				- this.getPre(transition, position);
	}

	private boolean isActivated(Map<Transition, Integer> vec,
			Map<Position, Integer> markers) {
		for (Transition transition : vec.keySet()) {
			if (!this.isActivated(transition, vec.get(transition), markers)) {
				return false;
			}
		}
		return true;
	}

	private boolean isActivated(Transition transition, Integer quantifier,
			Map<Position, Integer> markers) {
		for (Position position : this.positions) {
			Integer temp = markers.get(position);
			int markersAtPosition = temp == null ? 0 : temp;
			int result = markersAtPosition - this.getPre(transition, position)
					* quantifier;

			if (result < 0) {
				return false;
			}
		}

		return true;
	}

	public Map<Position, Integer> step(Map<Transition, Integer> vec,
			Map<Position, Integer> markers) {
		if (!this.isActivated(vec, markers)) {
			throw new Error("Vector is not activated.");
		}

		Map<Position, Integer> newMarkers = new HashMap<Position, Integer>();
		newMarkers.putAll(markers);

		for (Transition transition : vec.keySet()) {
			newMarkers.putAll(this.step(transition, vec.get(transition),
					newMarkers));
		}

		return newMarkers;
	}

	private Map<Position, Integer> step(Transition transition,
			Integer quantifier, Map<Position, Integer> markers) {
		Map<Position, Integer> newMarkers = new HashMap<Position, Integer>();

		for (Position position : this.positions) {
			newMarkers
					.put(position,
							markers.get(position)
									+ this.getEffect(transition, position)
									* quantifier);
		}

		return newMarkers;
	}

	public boolean isFinite() {
		for (Position position : this.positions) {
			if (!this.startMarkers.containsKey(position)) {
				this.startMarkers.put(position, 0);
			}
		}
		Collection<Map<Position, Integer>> reachableStates = new LinkedList<Map<Position, Integer>>();
		reachableStates.add(this.startMarkers);
		Collection<Map<Position, Integer>> current = new LinkedList<Map<Position, Integer>>();
		current.add(this.startMarkers);

		while (!current.isEmpty()) {
			current = this.getNewSuccessors(current, reachableStates);
			reachableStates.addAll(current);
			if (this.checkMarkers(current))
				return false;
		}
		return true;
	}

	private boolean checkMarkers(Collection<Map<Position, Integer>> markers) {
		boolean result = false;
		for (Map<Position, Integer> current : markers) {
			for (Position position : current.keySet()) {
				if (this.startMarkers.get(position) < current.get(position))
					result = true;
				else if (this.startMarkers.get(position) > current.get(position))
					return false;
			}
		}
		return result;
	}

	public boolean hasCycle() {
		for (Position position : this.positions) {
			if (!this.startMarkers.containsKey(position)) {
				this.startMarkers.put(position, 0);
			}
		}
		Collection<Map<Position, Integer>> reachableStates = new LinkedList<Map<Position, Integer>>();
		Collection<Map<Position, Integer>> current = new LinkedList<Map<Position, Integer>>();
		current.add(startMarkers);

		while (!current.isEmpty()
				&& !reachableStates.contains(this.startMarkers)) {
			current = this.getNewSuccessors(current, reachableStates);
			reachableStates.addAll(current);
		}
		return reachableStates.contains(this.startMarkers);
	}

	private Collection<Map<Position, Integer>> getNewSuccessors(
			Collection<Map<Position, Integer>> markers,
			Collection<Map<Position, Integer>> oldStates) {
		Collection<Map<Position, Integer>> result = new LinkedList<Map<Position, Integer>>();
		Collection<Map<Position, Integer>> tempOldStates = new LinkedList<Map<Position, Integer>>();
		tempOldStates.addAll(oldStates);
		for (Map<Position, Integer> marker : markers) {
			Collection<Map<Position, Integer>> temp = this.getAllNewSuccessors(
					marker, tempOldStates);
			result.addAll(temp);
			tempOldStates.addAll(temp);
		}
		return result;
	}

	private Collection<Map<Position, Integer>> getAllNewSuccessors(
			Map<Position, Integer> markers,
			Collection<Map<Position, Integer>> oldStates) {
		Collection<Map<Position, Integer>> successors = this
				.getSuccessors(markers);
		Iterator<Map<Position, Integer>> iterator = successors.iterator();
		while (iterator.hasNext()) {
			if (oldStates.contains(iterator.next())) {
				iterator.remove();
			}
		}
		return successors;
	}

	private Collection<Map<Position, Integer>> getSuccessors(
			Map<Position, Integer> marker) {
		Collection<Map<Position, Integer>> successors = new LinkedList<Map<Position, Integer>>();
		for (Transition transition : this.getActivatedTransitions(marker)) {
			successors.add(this.step(transition, 1, marker));
		}
		return successors;
	}

	private Collection<Transition> getActivatedTransitions(
			Map<Position, Integer> markers) {
		Collection<Transition> result = new LinkedList<Transition>();

		for (Transition transition : this.transitions) {
			if (this.isActivated(transition, 1, markers)) {
				result.add(transition);
			}
		}

		return result;
	}
}
