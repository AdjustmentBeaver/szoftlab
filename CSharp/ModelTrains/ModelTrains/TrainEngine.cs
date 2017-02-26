using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace ModelTrains {
    public class TrainEngine : TrainPart {

        public TrainEngine(int speed) {
            Speed = speed;
        }

        public int Speed {
            get;
            private set;
        }
    }
}