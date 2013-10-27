import java.util.HashSet;
import java.util.Set;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Embedded;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

@Entity
public class StationHistoryEntry {
	@Id
	private ObjectId id;

	@Embedded
	private Set<SongFeedback> songFeedback = new HashSet<SongFeedback>();

	protected StationHistoryEntry() {
		super();
	}

	public Set<SongFeedback> getSongFeedback() {
		return songFeedback;
	}

	public void setSongFeedback(Set<SongFeedback> songFeedback) {
		this.songFeedback = songFeedback;
	}

	public boolean addSongFeedback(SongFeedback songFeedback) {
		return this.songFeedback.add(songFeedback);
	}

	public ObjectId getId() {
		return id;
	}

	@Override
	public String toString() {
		return "StationHistoryEntry [id=" + id + ", songFeedback="
				+ songFeedback + "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		StationHistoryEntry other = (StationHistoryEntry) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (songFeedback == null) {
			if (other.songFeedback != null)
				return false;
		} else if (!songFeedback.equals(other.songFeedback))
			return false;
		return true;
	}
}
