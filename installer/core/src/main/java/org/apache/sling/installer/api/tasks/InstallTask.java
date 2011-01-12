/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.apache.sling.installer.api.tasks;

import org.apache.sling.installer.core.impl.OsgiInstallerImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Base class for tasks that can be executed by the {@link OsgiInstallerImpl}
 */
public abstract class InstallTask implements Comparable<InstallTask> {

    private final RegisteredResourceGroup resourceGroup;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public InstallTask(final RegisteredResourceGroup erl) {
        this.resourceGroup = erl;
    }

    /**
     * Return the corresponding resource - depending on the task this might be null.
     */
    public TaskResource getResource() {
        if ( this.resourceGroup != null ) {
            return this.resourceGroup.getActiveResource();
        }
        return null;
    }

    /**
     * Return the corresponding resource - depending on the task this might be null.
     */
    public RegisteredResourceGroup getResourceGroup() {
        return this.resourceGroup;
    }

    public Logger getLogger() {
        return this.logger;
    }

    public abstract void execute(InstallationContext ctx);

	/** Tasks are sorted according to this key */
	public abstract String getSortKey();

	/** All comparisons are based on getSortKey() */
	public final int compareTo(InstallTask o) {
		return getSortKey().compareTo(o.getSortKey());
	}

	public void setFinishedState(final ResourceState state) {
	    this.resourceGroup.setFinishState(state);
	}

    @Override
    public String toString() {
        return getClass().getSimpleName() + ": " + this.getResource();
    }

    @Override
	public final boolean equals(Object o) {
		if(o instanceof InstallTask) {
			return getSortKey().equals(((InstallTask)o).getSortKey());
		}
		return false;
	}

	@Override
	public final int hashCode() {
		return getSortKey().hashCode();
	}
}