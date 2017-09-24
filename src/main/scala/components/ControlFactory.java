package components;


import javafx.event.ActionEvent;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableView;

import java.util.function.BiConsumer;


/**
 * Created by pawel_zaqkxkn on 24.09.2017.
 */
public class ControlFactory {

    public static <T> void tableRowContextMenu(final TableView<T> table,
                                               final String token,
                                               final BiConsumer<String, T> deleteService) {
        MenuItem delete = new MenuItem("Delete");
        delete.setOnAction((ActionEvent event) -> {
            T item = table.getSelectionModel().getSelectedItem();
            deleteService.accept(token, item);
        });

        ContextMenu menu = new ContextMenu();
        menu.getItems().add(delete);
        table.setContextMenu(menu);
    }
}
