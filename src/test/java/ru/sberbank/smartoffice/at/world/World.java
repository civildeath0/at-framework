package ru.sberbank.smartoffice.at.world;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import ru.sberbank.smartoffice.at.entities.CreatedEntity;
import ru.sberbank.smartoffice.at.pageobjects.core.page.Page;
import ru.sberbank.smartoffice.at.users.SmartUser;

import java.util.LinkedList;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor @Getter
@FieldDefaults(level = PRIVATE)
public final class World {

    final LinkedList<CreatedEntity> createdEntities = new LinkedList<>();

    @Setter Page currentPage;
    @Setter SmartUser currentUser;

    public void addCreatedEntity(CreatedEntity createdEntity) {
        createdEntities.addFirst(createdEntity);
    }

    @NonNull
    public CreatedEntity getLastCreatedEntity() {
        return createdEntities.getLast();
    }
}
