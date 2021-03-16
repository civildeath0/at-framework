package ru.sberbank.smartoffice.at.visitors.creator;

import ru.sberbank.smartoffice.at.entities.objects.*;

public interface Creator {

    void manage(Airport.Creating airport);

    void manage(Assignment.Creating assignment);

    void manage(Company.Creating company);

    void manage(Conference.Creating conference);

    void manage(Flight.Creating flight);

    void manage(MaterialCategory.Creating materialCategory);

    void manage(Meeting.Creating meeting);

    void manage(News.Creating news);

    void manage(Contact.Creating contact);

    void manage(Material.Creating material);
}
