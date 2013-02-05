package org.sas04225.wificonnection;

public interface RadioMapStorage {

	public void init() throws IOException;
 
  /*
   * Parameters:
   * location_id - unique number denoting the location id
   * location_tag - name/tag for the location where the scan is performed
   * bssid[] - list of visible BSSIDs, obtained from the scan
   * level[] - the signal level/strength of the BSSIDs in dbm
   * Return value:
   * true - if the location was added to the backing store (a file or a server)
   * false - if the location already exists
   */
 
  public boolean addLocation(long location_id, String location_tag, String[] bssid, int[] level);
  
  public void close() throws IOException();

}
