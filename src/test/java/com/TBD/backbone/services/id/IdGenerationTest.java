package com.TBD.backbone.services.id;

import java.util.UUID;

public class IdGenerationTest
{

	public static void main(String[] args)
	{
		String id = UUID.randomUUID().toString();
		/**
		 * e20c1329-7d47-44a6-8a3d-37f8e2f595c6
		 * e20c1329 (8) 
		 */
		System.out.println(id);
		System.out.println(id.substring(0, id.indexOf('-')));		
	}

}
