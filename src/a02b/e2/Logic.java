package a02b.e2;

import java.util.List;

public interface Logic {
    
    boolean set (Position position);

    List<Position> check();

    void restart();
}
