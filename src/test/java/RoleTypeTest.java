import model.RoleType;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import static org.junit.Assert.assertEquals;
import org.junit.runner.RunWith;

/**
 * Dummy för enhetstestning av rolltyper med Arquillian.
 */
@RunWith(Arquillian.class)
public class RoleTypeTest {

    RoleType instance;

    /**
     * Konstruktor för skapandet av enhetstest för rolltyper (RoleType).
     * @return JavaArchive
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addClass(RoleType.class)
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    /**
     * Testmetod för att lägga till och hämta en rolltyp.
     */
    @org.junit.Test
    public void testSetAndGetName() {
        instance = new RoleType();
        String name = "admin";
        String expResult = "admin";
        instance.setName(name);
        String result = instance.getName();
        assertEquals(expResult, result);
    }
}
