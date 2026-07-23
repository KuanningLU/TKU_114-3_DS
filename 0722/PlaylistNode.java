public class PlaylistNode {
    private String songCode;
    private String songName;
    PlaylistNode next;

    public PlaylistNode(String songCode, String songName) {
        this.songCode = songCode;
        this.songName = songName;
        this.next = null;
    }

    public String getSongCode() {
        return songCode;
    }

    public String getSongName() {
        return songName;
    }

    @Override
    public String toString() {
        return songCode + " - " + songName;
    }
}