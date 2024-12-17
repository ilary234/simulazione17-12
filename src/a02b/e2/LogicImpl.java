package a02b.e2;

import java.util.ArrayList;
import java.util.List;

public class LogicImpl implements Logic{

    private List<Position> selected = new ArrayList<>();
    private int size;

    public LogicImpl(final int size) {
        this.size = size;
    }

    @Override
    public void restart() {
        this.selected = new ArrayList<>();
    }
    
    @Override
    public boolean set(final Position position) {
        if (this.selected.contains(position)) {
            this.selected.remove(position);
            return false;
        }
        this.selected.add(position);
        return true;
    }

    @Override
    public List<Position> check() {
        var list = this.selected.stream().filter(p -> isInDiagonal(p)).toList();
        return list;
    }

    private boolean isInDiagonal(Position position) {
        int count = 0;
        for (var p : this.selected){
            if ( p.x() > position.x()) {
                if (diagonal(p, position)) {
                    count++;
                };
            } else {
                if (diagonal(position, p)) {
                    count++;
                };
            }
        }
        return count == 3;
    }

    private boolean diagonal(final Position p1, final Position p2) {
        final int dif = p1.x() - p2.x();
        return (( p1.y() - p2.y() ) == dif ) ? true : false;
    }
    
}
