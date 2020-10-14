package subjects;

import model.Node;

import java.util.List;

/* The purpose is to create the operations that are being
    provided by this hierarchical data structure
 */

public interface Operation {
    void create(String path, String data);

    void update(String path, String data);

    void delete(String path);

    String get(String path);

    List<Node> list(String path);
}
