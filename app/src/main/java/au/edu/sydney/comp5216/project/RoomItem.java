package au.edu.sydney.comp5216.project;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;
import java.util.UUID;

public class RoomItem implements Comparable<RoomItem> {
    private String roomId;
    private String ownerId;
    private String roomName;
    private String joinedUserNum;
    private String key;

    private ArrayList<String> joinedUserIDs;
    private String roomCreatedTime;

    public RoomItem(String ownerId, String roomName) {
        this.roomId = UUID.randomUUID().toString();
        this.ownerId = ownerId;
        this.roomName = roomName;
        this.joinedUserNum = "";
        this.joinedUserIDs = new ArrayList<String>();
        joinedUserIDs.add(ownerId);
        this.roomCreatedTime = getCurrentDateAndTimeString();
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public String getKey() { return key; }

    public void setKey(String key) { this.key = key; }

    public String getJoinedUserNum() {
        return joinedUserNum;
    }

    public void setJoinedUserNum(String joinedUserNum) {
        this.joinedUserNum = joinedUserNum;
    }

    public ArrayList<String> getJoinedUserIDs() {
        return joinedUserIDs;
    }

    public void setJoinedUserIDs(ArrayList<String> joinedUserIDs) {
        this.joinedUserIDs = joinedUserIDs;
    }

    public boolean addJoinedUserIdToRoom(String userId){
        if(userId != null){
            ArrayList<String> newUserAdd= getJoinedUserIDs();
            newUserAdd.add(userId);
            return true;
        }
        return  false;
    }

    public String getRoomCreatedTime() { return roomCreatedTime; }

    public void setRoomCreatedTime(String roomCreatedTime) { this.roomCreatedTime = roomCreatedTime; }

    /**
     * Get current date and time when item be edited or added
     *
     * @return current date and time in format "yyyy-MM-dd HH:mm:ss"
     */
    private String getCurrentDateAndTimeString() {
        //get current data and time string
        Date currentDate = Calendar.getInstance().getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        sdf.setTimeZone(TimeZone.getTimeZone("Australia/Sydney"));
        String currentTime = sdf.format(currentDate);
        return currentTime;
    }

    /**
     * Compare two items by date type. First need to get date and time string from item,
     * then parse the date and time strings to date type to compare them.
     * @param o Item to compare
     * @return compare result int
     */
    @Override
    public int compareTo(RoomItem o) {
        if (getRoomCreatedTime() == null || o.getRoomCreatedTime() == null)
            return 0;
        String cuTime1 = getRoomCreatedTime();
        Date date1 = convertStringToDate(cuTime1);
        String cuTime2 = o.getRoomCreatedTime();
        Date date2 = convertStringToDate(cuTime2);
        return date2.compareTo(date1);
    }

    /**
     * convert date and time string in item to date type
     * @param cuTime date and time string in format "yyyy-MM-dd HH:mm:ss"
     * @return return date type which corresponds to it's string
     */
    private Date convertStringToDate(String cuTime) {
        Date date = null;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            date = format.parse(cuTime);
            System.out.println(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

}
