package model;
import java.util.Vector;


public class Material extends ComponentCommon {


	private static final String UnstructuredMaterialMessage = "Materialien haben kein Struktur!";
	public static Material create(final String name) {
		return new Material(name);
	}
	public Material(final String name) {
		super(name);
	}
	@Override
	public void addPart(final Component part, final int amount) throws Exception {
		throw new Exception(UnstructuredMaterialMessage);
	}
	@Override
	public boolean contains(final Component component) {
		return this.equals(component);
	}
	@Override
	public Vector<QuantifiedComponent> getDirectParts() {
		return new Vector<QuantifiedComponent>();
	}
	@Override
	public int getNumberOfMaterials() {
		return 1;
	}

}
