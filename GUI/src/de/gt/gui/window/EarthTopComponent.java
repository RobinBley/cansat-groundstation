/*//GEN-LINE:variables
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.gt.gui.window;

import de.gt.api.config.Config;
import de.gt.api.gps.GPSKey;
import de.gt.api.relay.Receiver;
import gov.nasa.worldwind.BasicModel;
import gov.nasa.worldwind.avlist.AVKey;
import gov.nasa.worldwind.awt.WorldWindowGLCanvas;
import gov.nasa.worldwind.geom.Position;
import gov.nasa.worldwind.layers.RenderableLayer;
import gov.nasa.worldwind.render.AnnotationAttributes;
import gov.nasa.worldwind.render.GlobeAnnotation;
import gov.nasa.worldwind.render.Polyline;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.openide.windows.TopComponent;
import org.openide.util.NbBundle.Messages;

/**
 * DO NOT TOUCH WITH THE DESIGNER!
 */
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

    private static final AnnotationAttributes ATTRIBS = new AnnotationAttributes();

    static {
        ATTRIBS.setAdjustWidthToText(AVKey.SIZE_FIT_TEXT);
    }

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

    private void add(Position p, GlobeAnnotation a) {
        positions.add(p);
        path.setPositions(positions);
        layer.addRenderable(a);
    }

    @Override
    public synchronized void receive(Map<String, Double> datum) {
        double latitude = datum.get(k.getLatitudeKey());
        double longitude = datum.get(k.getLongitudeKey());
        double altitude = datum.get(k.getAltitudeKey());
        Position p = Position.fromDegrees(latitude, longitude, altitude);
        StringBuilder b = new StringBuilder();
        datum.entrySet().stream()
                .map(e -> String.format("%s: %s\n", e.getKey(), e.getValue()))
                .forEach(b::append);
        GlobeAnnotation a = new GlobeAnnotation(b.toString(), p, ATTRIBS);
        add(p, a);
    }

    /**
     * DO NOT TOUCH DO NOT OPEN WITH EDITOR! NBP doesn't allow to add complex
     * custom components using the editor, manual code editing was needed.
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
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGap(0, 400, Short.MAX_VALUE)
                .addComponent(wwd, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGap(0, 300, Short.MAX_VALUE)
                .addComponent(wwd, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
        );
    }// </editor-fold

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
