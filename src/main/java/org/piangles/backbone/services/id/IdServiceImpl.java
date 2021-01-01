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

import java.util.UUID;

import org.piangles.backbone.services.Locator;
import org.piangles.backbone.services.logging.LoggingService;

public class IdServiceImpl implements IdService
{
	private LoggingService logger = Locator.getInstance().getLoggingService();

	public IdServiceImpl()
	{
		
	}
	
	@Override
	public Identifier getNextIdentifier(String idType) throws IdException
	{
		String uuId = UUID.randomUUID().toString();
		String id = uuId.substring(0, uuId.indexOf('-'));
		logger.info("Generating new Id for " + idType + " :" + id);
		return new Identifier(id, System.currentTimeMillis());
	}
}
