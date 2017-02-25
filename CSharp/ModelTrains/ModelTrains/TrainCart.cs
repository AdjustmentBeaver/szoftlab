using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace ModelTrains {
    public class TrainCart : TrainPart {
        public TrainCart(int color) {
            Color = color;
        }

        public int Color {
            get;
            private set;
        }

        public void unload() {
            throw new System.NotImplementedException();
        }
    }
}