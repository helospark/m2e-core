/*******************************************************************************
 * Copyright (c) 2010 Sonatype, Inc.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *      Sonatype, Inc. - initial API and implementation
 *******************************************************************************/
package org.eclipse.m2e.core.internal.embedder;

import java.util.List;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;

import org.apache.maven.artifact.Artifact;
import org.apache.maven.artifact.repository.ArtifactRepository;

import org.eclipse.m2e.core.embedder.IMavenConfiguration;

public class SynchronizedMavenImpl extends MavenImpl {
  private Object writeLock = new Object();

  public SynchronizedMavenImpl(IMavenConfiguration mavenConfiguration) {
    super(mavenConfiguration);
  }

  public Artifact resolve(Artifact artifact, List<ArtifactRepository> remoteRepositories, IProgressMonitor monitor)
      throws CoreException {
    synchronized(writeLock) {
      return super.resolve(artifact, remoteRepositories, monitor);
    }
  }

}
