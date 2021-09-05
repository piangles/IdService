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
 
 
package org.piangles.backbone.services.id.dao;

import org.piangles.core.dao.DAOException;
import org.piangles.core.dao.rdbms.AbstractDAO;
import org.piangles.core.resources.ResourceException;
import org.piangles.core.resources.ResourceManager;
import org.piangles.core.util.abstractions.ConfigProvider;

public class IdDAOImpl extends AbstractDAO implements IdDAO
{
	private static final String INSERT_ID_SP = "backbone.insert_id";
	
	public IdDAOImpl(ConfigProvider cp) throws ResourceException
	{
		super.init(ResourceManager.getInstance().getRDBMSDataStore(cp));
	}
	
	@Override
	public boolean insertId(String idType, String id) throws DAOException
	{
		Boolean insertResult = super.executeSPQuery(INSERT_ID_SP, 2, (call) -> {
			call.setString(1, idType);
			call.setString(2, id);
		}, (rs, call) -> {
			return rs.getBoolean(1); 
		});
		
		return insertResult;
	}

}
