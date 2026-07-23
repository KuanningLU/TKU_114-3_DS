public class PlaylistLinkedList {
    private PlaylistNode head;

    public PlaylistLinkedList() {
        head = null;
    }

    public boolean addSong(String songCode, String songName) {
        if (contains(songCode)) {
            System.out.println("新增失敗，歌曲代碼不可重複：" + songCode);
            return false;
        }

        PlaylistNode newNode = new PlaylistNode(songCode, songName);

        if (head == null) {
            head = newNode;
        } else {
            PlaylistNode current = head;

            while (current.next != null) {
                current = current.next;
            }

            current.next = newNode;
        }

        System.out.println("新增歌曲：" + newNode);
        return true;
    }

    public boolean contains(String songCode) {
        PlaylistNode current = head;

        while (current != null) {
            if (current.getSongCode().equals(songCode)) {
                return true;
            }

            current = current.next;
        }

        return false;
    }

    public PlaylistNode search(String songCode) {
        PlaylistNode current = head;

        while (current != null) {
            if (current.getSongCode().equals(songCode)) {
                return current;
            }

            current = current.next;
        }

        return null;
    }

    public boolean removeSong(String songCode) {
        if (head == null) {
            System.out.println("播放清單為空，無法刪除。");
            return false;
        }

        if (head.getSongCode().equals(songCode)) {
            System.out.println("刪除歌曲：" + head);
            head = head.next;
            return true;
        }

        PlaylistNode current = head;

        while (current.next != null) {
            if (current.next.getSongCode().equals(songCode)) {
                PlaylistNode deletedNode = current.next;
                current.next = current.next.next;

                System.out.println("刪除歌曲：" + deletedNode);
                return true;
            }

            current = current.next;
        }

        System.out.println("找不到歌曲代碼：" + songCode);
        return false;
    }

    public void printPlaylist() {
        System.out.println("目前播放清單：");

        if (head == null) {
            System.out.println("播放清單為空。");
            return;
        }

        PlaylistNode current = head;
        int order = 1;

        while (current != null) {
            System.out.println(order + ". " + current);
            current = current.next;
            order++;
        }
    }
}