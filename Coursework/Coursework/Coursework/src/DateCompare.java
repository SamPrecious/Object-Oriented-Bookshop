import java.util.Comparator;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateCompare implements Comparator<Activity>{  //This compares prices to see what is lower & higher
	public int compare(Activity ac1, Activity ac2) {
		if (ac1.getDate().compareTo(ac2.getDate()) < 0) return -1;
		if (ac1.getDate().compareTo(ac2.getDate()) > 0) return 1;
		else return 0;
		}		
}
