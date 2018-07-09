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

import java.io.File;
import java.util.List;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;

import org.apache.maven.artifact.Artifact;
import org.apache.maven.artifact.repository.ArtifactRepository;
import org.apache.maven.execution.MavenExecutionResult;
import org.apache.maven.execution.MavenSession;
import org.apache.maven.model.ConfigurationContainer;
import org.apache.maven.model.Plugin;
import org.apache.maven.plugin.MojoExecution;
import org.apache.maven.project.MavenProject;
import org.apache.maven.project.ProjectBuildingRequest;

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

  public MavenExecutionResult readMavenProject(File pomFile, ProjectBuildingRequest configuration)
      throws CoreException {
    synchronized(writeLock) {
      return super.readMavenProject(pomFile, configuration);
    }
  }

  public <T> T getMojoParameterValue(MavenSession session, MojoExecution mojoExecution, String parameter,
      Class<T> asType) throws CoreException {
    synchronized(writeLock) {
      return super.getMojoParameterValue(session, mojoExecution, parameter, asType);
    }
  }

  public <T> T getMojoParameterValue(String parameter, Class<T> type, MavenSession session, Plugin plugin,
      ConfigurationContainer configuration, String goal) throws CoreException {
    synchronized(writeLock) {
      return super.getMojoParameterValue(parameter, type, session, plugin, configuration, goal);
    }
  }

  public Artifact resolvePluginArtifact(Plugin plugin, List<ArtifactRepository> remoteRepositories,
      IProgressMonitor monitor) throws CoreException {
    synchronized(writeLock) {
      return super.resolvePluginArtifact(plugin, remoteRepositories, monitor);
    }
  }

  public MojoExecution setupMojoExecution(MavenSession session, MavenProject project, MojoExecution execution)
      throws CoreException {
    synchronized(writeLock) {
      return super.setupMojoExecution(session, project, execution);
    }
  }
}
