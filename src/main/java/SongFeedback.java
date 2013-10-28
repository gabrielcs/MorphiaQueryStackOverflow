import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Embedded;

@Embedded
public class SongFeedback {
	@Embedded
	private FeedbackType feedbackType;

	private ObjectId userId;

	public enum FeedbackType {
		THUMBS_UP, THUMBS_DOWN, STAR;
	}
	
	private double someOtherProperty = Math.random();

	protected SongFeedback() {
		super();
	}

	public SongFeedback(FeedbackType feedback, ObjectId userId) {
		this.userId = userId;
		this.feedbackType = feedback;
	}

	public FeedbackType getFeedbackType() {
		return feedbackType;
	}

	public void setFeedbackType(FeedbackType feedback) {
		this.feedbackType = feedback;
	}

	public ObjectId getUserId() {
		return userId;
	}

	public void setUserId(ObjectId userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "SongFeedback [feedbackType=" + feedbackType + ", userId="
				+ userId + ", someOtherProperty=" + someOtherProperty + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((feedbackType == null) ? 0 : feedbackType.hashCode());
		long temp;
		temp = Double.doubleToLongBits(someOtherProperty);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SongFeedback other = (SongFeedback) obj;
		if (feedbackType != other.feedbackType)
			return false;
		if (Double.doubleToLongBits(someOtherProperty) != Double
				.doubleToLongBits(other.someOtherProperty))
			return false;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		return true;
	}
}
