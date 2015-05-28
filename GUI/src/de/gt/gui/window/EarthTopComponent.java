/*//GEN-LINE:variables
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.gt.gui.window;

import de.gt.api.config.Config;
import de.gt.api.gps.GPSKey;
import gov.nasa.worldwind.BasicModel;
import gov.nasa.worldwind.avlist.AVKey;
import gov.nasa.worldwind.awt.WorldWindowGLCanvas;
import gov.nasa.worldwind.geom.Position;
import gov.nasa.worldwind.layers.RenderableLayer;
import gov.nasa.worldwind.render.Annotation;
import gov.nasa.worldwind.render.AnnotationAttributes;
import gov.nasa.worldwind.render.GlobeAnnotation;
import gov.nasa.worldwind.render.Polyline;
import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.swing.JCheckBox;
import org.openide.windows.TopComponent;
import org.openide.util.NbBundle.Messages;

@TopComponent.Description(
        preferredID = "LivePathEarthTopComponent",
        //iconBase="SET/PATH/TO/ICON/HERE", 
        persistenceType = TopComponent.PERSISTENCE_ALWAYS
)
@TopComponent.Registration(mode = "editor", openAtStartup = false)
@Messages({
    "CTL_LivePathEarthAction=LivePathEarth",
    "CTL_LivePathEarthTopComponent=LivePathEarth Window",
    "HINT_LivePathEarthTopComponent=This is a LivePathEarth window"
})
public final class EarthTopComponent extends DataReceiverComponent {

    private final List<Position> positions = new ArrayList<>();

    private final GPSKey k;

    private WorldWindowGLCanvas wwd;
    private RenderableLayer layer;
    private Polyline path;

    public EarthTopComponent(GPSKey k) {
        super();

        this.k = k;
        initComponents();
        setName("Map Visualisation");
        setToolTipText(Bundle.HINT_LivePathEarthTopComponent());
    }

    @Override
    public synchronized void receive(Map<String, Double> datum) {
        double latitude = datum.get(k.getLatitudeKey());
        double longitude = datum.get(k.getLongitudeKey());
        double altitude = datum.get(k.getAltitudeKey());
        Position p = Position.fromDegrees(latitude, longitude, altitude);
        positions.add(p);
        path.setPositions(positions);
    }

    /**
     * Diesen Code NICHT mit dem Designer berühren! Der Designer erlaubt es
     * nicht, komplexe Komponenten wie GL Canvasse einfach zu nutzen.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    private void initComponents() {
        wwd = new WorldWindowGLCanvas();
        BasicModel bm = new BasicModel();
        wwd.setModel(bm);
        layer = new RenderableLayer();
        path = new Polyline();
        layer.addRenderable(path);
        bm.getLayers().add(layer);
        setLayout(new BorderLayout(0, 0));
        add(wwd, BorderLayout.CENTER);
    }// </editor-fold
    // Diese fehlende Klammer sorgt dafür, dass der manuelle Code
    // nicht von NBP überschrieben werden kann

    @Override
    public void configChanged(Config newConfig) {
        //TODO: Close map is Gps is not available
    }

    @Override
    public void clearData() {
        //Traces entfernen
        layer.removeAllRenderables();
        //Polyline wieder aufbauen
        path = new Polyline();
        layer.addRenderable(path);
    }
}
