package gpxwrench.ui.pivot;

import java.awt.Color;
import java.awt.Font;

import org.apache.pivot.collections.Map;
import org.apache.pivot.wtk.Application;
import org.apache.pivot.wtk.DesktopApplicationContext;
import org.apache.pivot.wtk.Display;
import org.apache.pivot.wtk.HorizontalAlignment;
import org.apache.pivot.wtk.Label;
import org.apache.pivot.wtk.VerticalAlignment;
import org.apache.pivot.wtk.Window;

/**
 * Primary UI boot up.
 * @author Jason Andersen (andersen.jason@gmail.com)
 * @since Sep 5, 2013
 */
public class GpxWrenchMain implements Application {

    private Window window = null;

    /**
     * Boot strap from this class.
     * @param args
     */
    public static void main(String[] args) {
        DesktopApplicationContext.main(GpxWrenchMain.class, args);
    }

    /**
     * @see org.apache.pivot.wtk.Application#startup(org.apache.pivot.wtk.Display, org.apache.pivot.collections.Map)
     */
    @Override
    public void startup(Display display, Map<String, String> properties) throws Exception {
        //TODO implement

        /*
         * This is the standard Hello World example from the website.
         */
        window = new Window();

        Label label = new Label();
        label.setText("I like monkeys!");
        label.getStyles().put("font", new Font("Arial", Font.BOLD, 24));
        label.getStyles().put("color", Color.RED);
        label.getStyles().put("horizontalAlignment",
                HorizontalAlignment.CENTER);
        label.getStyles().put("verticalAlignment",
                VerticalAlignment.CENTER);

        window.setContent(label);
        window.setTitle("I like monkeys!");
        window.setMaximized(true);

        window.open(display);
    }

    /**
     * @see org.apache.pivot.wtk.Application#shutdown(boolean)
     */
    @Override
    public boolean shutdown(boolean optional) throws Exception {
        return false;
    }

    /**
     * @see org.apache.pivot.wtk.Application#suspend()
     */
    @Override
    public void suspend() throws Exception {
        //TODO implement
    }

    /**
     * @see org.apache.pivot.wtk.Application#resume()
     */
    @Override
    public void resume() throws Exception {
        //TODO implement
    }

    /*
     * 
     * An implementation based on BXML
     * 
     * 
    public void startup(Display display, Map<String, String> properties)
        throws Exception {
        BXMLSerializer bxmlSerializer = new BXMLSerializer();
        window = (Window)bxmlSerializer.readObject(GpxWrenchMain.class, "hello.bxml");
        window.open(display);
    }
    
    @Override
    public boolean shutdown(boolean optional) {
        if (window != null) {
            window.close();
        }
    
        return false;
    }
    
    @Override
    public void suspend() {
    }
    
    @Override
    public void resume() {
    }
    
     ***************
     *
     * BXML
     * 
     ***************
     
     <Window title="Hello BXML!" maximized="true"
      xmlns:bxml="http://pivot.apache.org/bxml"
      xmlns="org.apache.pivot.wtk">
    <Label text="Hello BXML!"
        styles="{font:'Arial bold 24', color:'#ff0000',
            horizontalAlignment:'center', verticalAlignment:'center'}"/>
    </Window>
     
     */

}
