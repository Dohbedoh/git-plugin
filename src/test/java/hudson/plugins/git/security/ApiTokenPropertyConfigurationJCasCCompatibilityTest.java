package hudson.plugins.git.security;

import hudson.plugins.git.ApiTokenPropertyConfiguration;
import io.jenkins.plugins.casc.misc.RoundTripAbstractTest;
import org.jvnet.hudson.test.RestartableJenkinsRule;

import java.util.Collection;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.junit.Assert.assertEquals;

public class ApiTokenPropertyConfigurationJCasCCompatibilityTest extends RoundTripAbstractTest {

    @Override
    protected void assertConfiguredAsExpected(RestartableJenkinsRule restartableJenkinsRule, String s) {
        final ApiTokenPropertyConfiguration descriptor = (ApiTokenPropertyConfiguration) restartableJenkinsRule.j.jenkins.getDescriptor(ApiTokenPropertyConfiguration.class);
        final Collection<ApiTokenPropertyConfiguration.HashedApiToken> apiTokens = descriptor.getApiTokens();
        assertThat(apiTokens, hasSize(1));
        ApiTokenPropertyConfiguration.HashedApiToken apiToken = apiTokens.iterator().next();
        assertEquals("my-token", apiToken.getName());
        assertEquals("d92dbba125121c3d3e6f8022548741e5bd42090274975a57dcd1ddde8483e5fa", apiToken.getHash());
        assertEquals("59852856-dc03-4f60-acd2-2b4073c9d533", apiToken.getUuid());
    }

    @Override
    protected String stringInLogExpected() {
        return "Setting class hudson.plugins.git.ApiTokenPropertyConfiguration$HashedApiToken.name = my-token";
    }

    @Override
    protected String configResource() {
        return "security-api-tokens.yaml";
    }

}
