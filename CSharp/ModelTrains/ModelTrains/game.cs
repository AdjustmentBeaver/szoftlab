using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ModelTrains {
    class Game {
        static private MapManager mapManager;

        static void Main(string[] args) {
            mapManager = new MapManager();
            mapManager.newMap("map1");
        }
    }
}
