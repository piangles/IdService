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
