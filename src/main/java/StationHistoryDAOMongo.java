import java.util.List;

import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.dao.BasicDAO;
import org.mongodb.morphia.query.Query;

public class StationHistoryDAOMongo extends BasicDAO<StationHistoryEntry, ObjectId> {

	public StationHistoryDAOMongo(Datastore ds) {
		super(ds);
	}
	
	public List<StationHistoryEntry> findStarredByUserId(ObjectId userId) {
		Query<StationHistoryEntry> query = ds
				.createQuery(StationHistoryEntry.class);
		
		query.and(
				query.criteria("songFeedback.userId").equal(userId), 
				query.criteria("songFeedback.feedbackType").equal(SongFeedback.FeedbackType.STAR));
		
		return query.asList();
	}
}
