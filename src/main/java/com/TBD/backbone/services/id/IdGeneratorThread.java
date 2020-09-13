package com.TBD.backbone.services.id;

public class IdGeneratorThread extends Thread
{
	/**
	 * String id = UUID.randomUUID().toString();
	 * return id.substring(0, id.indexOf('-'));
	 * 
	 * Use this as an alternative to generate Id instead of the
	 * complex AAAAnnn and filtering.
	 * 
	 * Make sure the length of the Id is still around 7 /8 or
	 * what was decided. And document the combination count.
	 *  
	 * Still will use the same logic of DB and getting next Id
	 * and water level. Take idea from minMark and maxMark from
	 * the ThreadPool.
	 */
	public void run()
	{
		
	}
}
