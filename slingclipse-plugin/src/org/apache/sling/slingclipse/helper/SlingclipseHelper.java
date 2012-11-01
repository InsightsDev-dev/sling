/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.sling.slingclipse.helper;

public class SlingclipseHelper {
	
	public static final String JCR_ROOT= "jcr_root";
	
	public static boolean isValidSlingProjectPath(String path){
		//TODO verify only one occurrence of JCR_ROOT.		
		return path.indexOf(JCR_ROOT)!= -1;
	}
	
	public static String getSlingProjectPath(String path){
		int index= path.indexOf(JCR_ROOT)+JCR_ROOT.length();
        return path.substring(index);
	}
	
	public static boolean isFilePath(String path){
		return path.indexOf(".")!=-1;
	}
	
	public static boolean isFolderPath(String path){
		return path.indexOf(".")==-1;
	}

}
