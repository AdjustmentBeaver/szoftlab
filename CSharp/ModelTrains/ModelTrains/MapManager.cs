using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace ModelTrains {
    public class MapManager {
        private Map map;

        public void newMap(string mapName) {
            MapBuilder mb = new MapBuilder(mapName);
            map = mb.loadMap();
        }
    }
}