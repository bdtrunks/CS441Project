import static org.junit.Assert.*;

import java.awt.Label;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import junit.extensions.jfcunit.JFCTestCase;
import junit.extensions.jfcunit.JFCTestHelper;
import junit.extensions.jfcunit.TestHelper;
import junit.extensions.jfcunit.eventdata.KeyEventData;
import junit.extensions.jfcunit.eventdata.MouseEventData;
import junit.extensions.jfcunit.eventdata.StringEventData;
import junit.extensions.jfcunit.finder.DialogFinder;
import junit.extensions.jfcunit.finder.NamedComponentFinder;
import junit.framework.Test;
import junit.framework.TestSuite;

/**
 * Tests GUI operation of 9 Men Morris game
 * @author Brian Dunn
 *
 */
public class NMMTest extends JFCTestCase{


	private NineMensMorris gameScreen = null;
	private TestHelper helper = null;

	public NMMTest(String name) {
		super(name);
	}

	protected void setUp() throws Exception {
		super.setUp();
		helper = new JFCTestHelper();
		gameScreen = new NineMensMorris();
		gameScreen.setVisible(true);
	}

	protected void tearDown() throws Exception {
		gameScreen = null;
		JFCTestHelper.cleanUp(this);
		super.tearDown();
	}

	@SuppressWarnings({ "unchecked", "unchecked" })
	public void testAllPartsExist() {
	    NamedComponentFinder finder = new NamedComponentFinder(JComponent.class, "newGameButton" );
	    JButton newGameButton = ( JButton ) finder.find(gameScreen, 0);
	    assertNotNull( "Could not find the new game button", newGameButton );
	    
	    finder.setName("playerLabel");
	    JLabel playerLabel = (JLabel) finder.find(gameScreen, 0);
	    assertNotNull( "Could not find the player label", playerLabel );

	    finder.setName("boardPanel");
	    JPanel boardPanel = (JPanel) finder.find(gameScreen, 0);
	    assertNotNull( "Could not find the board panel", boardPanel );
		try {
			long sleep = 999999999*99;
			//helper.enterClickAndLeave(new MouseEventData(this, newGameButton));
			MouseEventData data = new MouseEventData(this, boardPanel, 1, 1, false, sleep, new Point(436,436));
			System.out.println(data.prepareComponent());
			helper.enterClickAndLeave(data);
			//helper.enterClickAndLeave(new MouseEventData(this, boardPanel, 1, 1, false, sleep, new Point(252,435)));
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}

	}
	
	public static Test suite() {
		return new TestSuite(NMMTest.class);
	}

	public static void main(String args[]) {
		junit.textui.TestRunner.run(suite());
	}

}

