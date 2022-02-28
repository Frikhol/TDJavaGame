import jdk.internal.net.http.common.Pair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Parameter {
    float value;
    ArrayList<Pair<String,Float>> multipliers;

    public float getValue() {
        float m = 1;
        for(Pair<String,Float> p : multipliers)
            m*=p.second;
        return value*m;
    }
}
