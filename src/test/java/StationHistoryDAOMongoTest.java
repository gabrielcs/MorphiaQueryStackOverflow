import static org.junit.Assert.*;

import java.util.List;

import org.bson.types.ObjectId;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;

public class StationHistoryDAOMongoTest {
	private String mongoURI = "mongodb://admin:admin@paulo.mongohq.com:10092/morphia-query";
	private String dbName = "morphia-query";
	private Datastore datastore;

	private static final ObjectId userId1 = new ObjectId(
			"525c10fa45690266809924ed");
	private static final ObjectId userId2 = new ObjectId(
			"525c379145691ceff24838d1");

	@Test
	public void test() {
		StationHistoryDAOMongo stationHistoryEntryDAO = new StationHistoryDAOMongo(
				datastore);

		// thumbs-up by user1
		SongFeedback songFeedback1 = new SongFeedback(
				SongFeedback.FeedbackType.THUMBS_UP, userId1);
		StationHistoryEntry stationHistoryEntry1 = new StationHistoryEntry();
		stationHistoryEntry1.addSongFeedback(songFeedback1);
		stationHistoryEntryDAO.save(stationHistoryEntry1);

		// star by user1 and thumbs-up by user1
		SongFeedback songFeedback2 = new SongFeedback(
				SongFeedback.FeedbackType.STAR, userId1);
		StationHistoryEntry stationHistoryEntry2 = new StationHistoryEntry();
		stationHistoryEntry2.addSongFeedback(songFeedback1);
		stationHistoryEntry2.addSongFeedback(songFeedback2);
		stationHistoryEntryDAO.save(stationHistoryEntry2);

		// star by user2 and thumbs-up by user1
		SongFeedback songFeedback3 = new SongFeedback(
				SongFeedback.FeedbackType.STAR, userId2);
		StationHistoryEntry stationHistoryEntry3 = new StationHistoryEntry();
		stationHistoryEntry3.addSongFeedback(songFeedback1);
		stationHistoryEntry3.addSongFeedback(songFeedback3);
		stationHistoryEntryDAO.save(stationHistoryEntry3);

		// query for StationHistoryEntry with star by user1
		List<StationHistoryEntry> stationHistoryEntryList = stationHistoryEntryDAO
				.findStarredByUserId(userId1);
		// System.out.println(stationHistoryEntryList);
		assertEquals(1, stationHistoryEntryList.size());
		assertEquals(stationHistoryEntry2, stationHistoryEntryList.get(0));
	}

	@Before
	public void setUp() throws Exception {
		MongoClientURI mongoClientURI = new MongoClientURI(mongoURI);
		MongoClient mongoClient = new MongoClient(mongoClientURI);
		datastore = new Morphia().createDatastore(mongoClient, dbName);
	}

	@After
	public void tearDown() throws Exception {
		if (datastore != null) {
			// deletes all StationHistoryEntry documents
			StationHistoryDAOMongo stationHistoryEntryDAO = new StationHistoryDAOMongo(
					datastore);
			stationHistoryEntryDAO.deleteByQuery(datastore
					.find(StationHistoryEntry.class));
		}
	}
}
