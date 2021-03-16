package ru.sberbank.smartoffice.at.visitors.changer;

import ru.sberbank.smartoffice.at.entities.objects.*;

public interface Changer {

    void manage(Airport.Created airport);

    void manage(Assignment.Created assignment);

    void manage(Company.Created company);

    void manage(Conference.Created conference);

    void manage(Flight.Created flight);

    void manage(MaterialCategory.Created materialCategory);

    void manage(Meeting.Created meeting);

    void manage(News.Created news);

    void manage(Contact.Created contact);

    void manage(Material.Created created);
}
