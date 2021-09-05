/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements. See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
 
 
 
package org.piangles.backbone.services.id;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.UUID;

import org.piangles.backbone.services.Locator;
import org.piangles.backbone.services.config.DefaultConfigProvider;
import org.piangles.backbone.services.id.dao.IdDAO;
import org.piangles.backbone.services.id.dao.IdDAOImpl;
import org.piangles.backbone.services.logging.LoggingService;
import org.piangles.core.dao.DAOException;
import org.piangles.core.util.abstractions.ConfigProvider;

public class IdServiceImpl implements IdService
{
	private static final String SERVICE_COMPONENT_ID = "630a792c-448d-495a-9f5e-6ed8070c6b42";
	private static final String DAO_COMPONENT_ID = "e38b40ff-5555-4cac-8ecf-b3491969beaa";
	private static final String ID_TYPE = "IdType:";
	
	private LoggingService logger = Locator.getInstance().getLoggingService();

	private Map<String, Integer> idTypeLengthMap = null;
	private IdDAO idDAO = null;
	
	public IdServiceImpl() throws Exception
	{
		ConfigProvider serviceConfigProvider = new DefaultConfigProvider(IdService.NAME, SERVICE_COMPONENT_ID);
		
		idDAO = new IdDAOImpl(new DefaultConfigProvider(IdService.NAME, DAO_COMPONENT_ID));
		idTypeLengthMap = new HashMap<>();

		/**
		 * Uniqueness of permutations of [0-9, a-f] for length:
		 * 5 is 525K
		 * 6 is 7.5MM
		 * 7 is 57MM
		 * 8 is 518MM
		 * 9 is 4B
		 * 10 is 29B
		 */
		Properties props = serviceConfigProvider.getProperties();
		Enumeration<Object> keys = props.keys();
		while (keys.hasMoreElements())
		{
			String key = (String)keys.nextElement();
			if (key.startsWith(ID_TYPE))
			{
				idTypeLengthMap.put(key.substring(ID_TYPE.length()), Integer.valueOf(props.getProperty(key)));				
			}
		}
	}
	
	@Override
	public Identifier getIdentifier(String idType) throws IdException
	{
		if (!idTypeLengthMap.containsKey(idType))
		{
			throw new IdException("Unrecognized IdType : " + idType);	
		}
		return generateIdAndPersist(idType, idTypeLengthMap.get(idType));
	}
	
	/**
	 * Sample UUID : dda0704d-7072-4081-9e97-d39e2e2dbfd1
	 * 
	 * @param idType
	 * @return
	 */
	private Identifier generateIdAndPersist(String idType, int length) throws IdException
	{
		Identifier id = null;

		int attempt = 0;
		for (int i=0; i < length; ++i)
		{
			String uuidAsStr = UUID.randomUUID().toString().replace("-", "");;
			String idAsStr = uuidAsStr.substring(0, length);  
			try
			{
				boolean result = idDAO.insertId(idType, idAsStr);
				if (result)
				{
					id = new Identifier(idType, idAsStr, System.currentTimeMillis());
					break;
				}
				attempt++;
			}
			catch (DAOException e)
			{
				logger.error("DAOException: " + e.getMessage(), e);
				throw new IdException(e.getMessage(), e);
			}
		}
		
		if (id == null)
		{
			String message = "Unable to generate an unique Id in " + length + " attempts. Consider larger length for IdType: " + idType + ".";
			logger.error(message);
			throw new IdException(message);
		}
		
		logger.info("Generating new Id for " + idType + ":" + id.getValue() + " in " + attempt + " attempts.");
		
		return id;
	}
}
