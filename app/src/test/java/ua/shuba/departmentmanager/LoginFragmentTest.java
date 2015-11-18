package ua.shuba.departmentmanager;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.support.v4.SupportFragmentTestUtil;

import ua.shuba.departmentmanager.fragment.LogInFragment;
import ua.shuba.departmentmanager.fragment.LogInFragment_;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */

@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21)
public class LoginFragmentTest {

    public LogInFragment mLoginFragment;

    @Before
    public final void init() {
        mLoginFragment = LogInFragment_.builder().build();
        SupportFragmentTestUtil.startFragment(mLoginFragment);
    }

    @Test
    public final void FragmentCreated_InitializedViews() {
        Assert.assertNotNull(mLoginFragment.getView());
    }
}