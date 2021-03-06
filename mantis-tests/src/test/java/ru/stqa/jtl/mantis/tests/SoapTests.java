package ru.stqa.jtl.mantis.tests;

import org.testng.annotations.Test;
import ru.stqa.jtl.mantis.model.Issue;
import ru.stqa.jtl.mantis.model.Project;

import javax.xml.rpc.ServiceException;
import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.util.Set;

import static org.testng.Assert.assertEquals;

public class SoapTests extends TestBase{

    @Test
    public void testGetProjects() throws MalformedURLException, ServiceException, RemoteException {
        Set<Project> projects = app.soap().getProjects();
        System.out.println(projects.size());
        for (Project project: projects){
            System.out.println(project.getName());
        }
    }

    @Test
    public void testCreateIssue() throws RemoteException, ServiceException, MalformedURLException {
        Set<Project> projects = app.soap().getProjects();
        Issue issue = new Issue().withSummary("Test Issue").withDescription("Test Issue Description").withProject(projects.iterator().next());
        Issue createdIssue = app.soap().addIssue(issue);
        assertEquals(issue.getSummary(), createdIssue.getSummary());
    }

    @Test
    public void testCheckSkiped() throws RemoteException, ServiceException, MalformedURLException {
        skipIfNotFixed(2);
    }
}
