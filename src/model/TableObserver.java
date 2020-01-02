package model;

import java.util.List;

public interface TableObserver {
    void updateTable(List<Students> students);
}
