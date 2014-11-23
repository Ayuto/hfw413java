package model;

/**
 * Only one Object can get this EBM. Afterwards the
 * EBM has to be put back first, before the next Object 
 * can get the EBM.
 */
public interface EBM {
	
	/**
	 * Tries to get the EBM. 
	 * If it's possible the EBM won't be available any longer.
	 * If someone else has the EBM the thread will wait
	 * until the EBM is available.
	 */
	void get();

	/**
	 * Tries to put back the EBM.
	 * If it's possible the EBM will be available afterwards.
	 * If the EBM is already available the thread will wait 
	 * until the EBM is not available.
	 */
	void put();
}
