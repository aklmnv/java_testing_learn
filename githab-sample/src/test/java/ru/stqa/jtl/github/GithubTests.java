package ru.stqa.jtl.github;

import com.google.common.collect.ImmutableMap;
import com.jcabi.github.*;
import org.testng.annotations.Test;
import sun.security.ec.point.ProjectivePoint;

import java.io.IOException;

public class GithubTests {

    @Test
    public void testCommits() throws IOException {
        Github github = new RtGithub("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");
        RepoCommits commits = github.repos().get(new Coordinates.Simple("aklmnv", "java_testing_learn")).commits();
        for (RepoCommit commit : commits.iterate(new ImmutableMap.Builder<String, String>().build())){
            System.out.println(new RepoCommit.Smart(commit).message());
        }
    }
}
