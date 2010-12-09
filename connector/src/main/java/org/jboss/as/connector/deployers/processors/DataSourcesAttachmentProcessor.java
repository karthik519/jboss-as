/*
 * JBoss, Home of Professional Open Source.
 * Copyright 2010, Red Hat, Inc., and individual contributors
 * as indicated by the @author tags. See the copyright.txt file in the
 * distribution for a full listing of individual contributors.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */

package org.jboss.as.connector.deployers.processors;

import static org.jboss.as.connector.deployers.processors.DataSourcesAttachement.attachDataSources;

import static org.jboss.as.connector.deployers.processors.DataSourcesAttachement.detachDataSources;
import org.jboss.as.deployment.unit.DeploymentUnit;
import org.jboss.as.deployment.unit.DeploymentUnitProcessingException;
import org.jboss.as.deployment.unit.DeploymentUnitProcessor;
import org.jboss.as.deployment.unit.DeploymentPhaseContext;
import org.jboss.jca.common.api.metadata.ds.DataSources;

/**
 * Deployment processor used to attach the {@link org.jboss.jca.common.api.metadata.ds.DataSources} instance to the
 * current deployment context.
 *
 * @author John Bailey
 */
public class DataSourcesAttachmentProcessor implements DeploymentUnitProcessor {

    private final DataSources dataSources;

    /**
     * Create instance.
     *
     * @param dataSources Configured datasources
     */
    public DataSourcesAttachmentProcessor(DataSources dataSources) {
        this.dataSources = dataSources;
    }

    /** {@inheritDoc} */
    public void deploy(DeploymentPhaseContext phaseContext) throws DeploymentUnitProcessingException {
        attachDataSources(phaseContext.getDeploymentUnitContext(), dataSources);
    }

    public void undeploy(DeploymentUnit context) {
        detachDataSources(context);
    }
}
